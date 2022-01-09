package rogue.game.world;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.game.combat.CombatManager;
import rogue.game.world.generation.RoomData;
import rogue.game.world.objects.Entity;
import rogue.game.world.objects.NPC;
import rogue.game.world.objects.PlayableCharacter;
import rogue.game.world.objects.SecondLayerObject;
import rogue.game.world.objects.Skill;
import rogue.game.world.objects.Skill.TargetType;
import rogue.game.world.objects.Tile;
import rogue.graphics.BaseActionContainer;
import rogue.graphics.EntityInformationContainer;
import util.Highlight;
import util.MovementOption;
import util.calc.path.AStarPathfinder;

public class Room {
	
	private RoomData data;
	private EntityInformationContainer activeNpcCanvas;
	private BaseActionContainer buttonPanel;

	private Connector connector;

	private PlayableCharacter activeCharacter;
	private NPC activeNpc;
	
	private ArrayList<Entity> entities;
	private SecondLayerObject[][] objects;
	private Highlight[][] highlights;
	private byte[][] sprites;
	
	private final static int xPlayerStart = 12;
	private final static int yPlayerStart = 12;
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	public Room(RoomData data, Connector connector) {
		this.data = data;
		this.connector = connector;
		this.entities = new ArrayList<Entity>();
		this.objects = new SecondLayerObject[data.getTileData().length][data.getTileData()[0].length];
		this.highlights = new Highlight[data.getTileData().length][data.getTileData()[0].length];
		this.activeNpcCanvas = new EntityInformationContainer(new Entity(), EntityInformationContainer.ENTITY_CONFIG, Resources.textEditorConfig, connector);
		this.buttonPanel = new BaseActionContainer(connector);
		this.sprites = new byte[data.getTileData().length][data.getTileData()[0].length];
		initEnemies();
	}
	
	public void update() {
		if(activeNpc!=null) {
			this.activeNpcCanvas.checkUdate(activeNpc);
		}
	}
	
	//init
	public void initiallyPlacePlayer(PlayableCharacter player) {
		player.setX(xPlayerStart);
		player.setY(yPlayerStart);
		entities.add(player);
		objects[xPlayerStart][yPlayerStart] = player;
		activeCharacter = player;
		setSelectPlayerEvent(player,xPlayerStart,yPlayerStart);
	}
	public void initiallyPlacePlayer(List<PlayableCharacter> team) {
		int i = 0;
		for(PlayableCharacter p : team) {
			p.setX(xPlayerStart+i);
			p.setY(yPlayerStart);
			entities.add(p);
			objects[xPlayerStart+i][yPlayerStart] = p;
			setSelectPlayerEvent(p,xPlayerStart+i,yPlayerStart);
			i+=4;
		}
		activeCharacter = team.get(0);
	}
	private void initEnemies() {
		NPC enemy = new NPC(4,5,Resources.SKELETON,"skeleton",Resources.SKELETONMALE,MovementOption.ENEMY,this.connector);
		enemy.setMeeleeDef1(5);
		entities.add(enemy);
		objects[enemy.getX()][enemy.getY()] = enemy;

		initEntityInformationEvents();
		
		
	}
	private void initEntityInformationEvents() {
		for(Entity e: this.entities) {
			Event event = new Event();
			event.setObject(e);
			event.setEventId(this.connector.INFO_ENTITY);
			event.setX(e.getX());
			event.setY(e.getY());
			this.connector.addContext(getRelationalX(e.getX()), getRelationalY(e.getY()), Property.TILE_SIZE, Property.TILE_SIZE, event);			
		}
	}
 
	
	//rendering
	public List<int[]> render() {
		
		List<int[]> compartments = new ArrayList<int[]>();
		
		int[] map = new int[Property.ROOM_SIZE*Property.ROOM_SIZE];
		map=renderRoom(map);
		map=renderEntities(map);
		map=renderHighlights(map);
		compartments.add(map);
		
		int[] npcInfo = new int[Property.ACTIVE_NPC_HEIGHT*Property.ACTIVE_NPC_WIDTH];
		npcInfo = activeNpcCanvas.getPixels();
		compartments.add(npcInfo);
		
		int[] buttons = new int[Property.BUTTON_PANEL_WIDTH*Property.BUTTON_PANEL_HEIGHT];
		buttons = buttonPanel.getPixels();
		compartments.add(buttons);
		
		return compartments;
	}
	private int[] renderRoom(int[]p) {
		for(int y = 0; y < Property.ROOM_SIZE; y++) {
			for(int x = 0; x < Property.ROOM_SIZE; x++) {
				int tileX = x/Property.TILE_SIZE;
				int tileY = y/Property.TILE_SIZE;
				int xInTile = x%Property.TILE_SIZE;
				int yInTile = y%Property.TILE_SIZE;
				Tile t = data.getTileData()[tileY+yOffset][tileX+xOffset];
				p[x + y*Property.ROOM_SIZE] = Resources.TEXTURES.get(t.getId())[xInTile+yInTile*Property.TILE_SIZE];
			}
		}
		
		return p;
	}
	private int[] renderEntities(int[] p) {
		
		for(Entity e : entities) {
			int i = 0;
			if((e.getX()>=xOffset)&&(e.getX()<=xOffset+Property.ROOM_VIEW_TILE_COUNT) &&
				(e.getY()>=yOffset)&&(e.getY()<=yOffset+Property.ROOM_VIEW_TILE_COUNT)) {
				for(int y = 0; y < Property.TILE_SIZE; y++) {
					for(int x = 0; x < Property.TILE_SIZE; x++) {
						int relX = ((e.getX()+(-1)*xOffset)*Property.TILE_SIZE)+x;
						int relY = ((e.getY()+(-1)*yOffset)*Property.TILE_SIZE)+y;
						
						int color = Resources.TEXTURES.get(e.getId())[x+y*Property.TILE_SIZE];
						if(i==0) {
							System.out.println(color);
							i++;
						}
						if(color!=-12450784 && color!=-3947581) {
							p[relX+relY*Property.ROOM_SIZE] = color;	
						}
					}
				}
			}
		}
		
		return p;
	}
	private int[] renderHighlights(int[] p) {
		for(int x = 0; x < highlights.length; x++) {
			for(int y =0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null) {
					highlights[x][y].printHighlight(p, x, y, xOffset, yOffset);

				}
			}
		}
		return p;
	}
	private void removeTheDead() {
		this.entities.removeIf(e -> e.getCurrentLife() < 1);
		for(int x  = 0; x < this.objects.length; x++) {
			for(int y = 0; y < this.objects[0].length; y++) {
				if(this.objects[x][y] != null &&
						Entity.class.isInstance(this.objects[x][y]) &&
						Entity.class.cast(this.objects[x][y]).getCurrentLife() < 1) {
					this.connector.removeContextOf(this.objects[x][y].getName());
					this.objects[x][y] = null;
				}
			}
		}
	}
	
	private void endTurn() {
		for(Entity e : this.entities) {
			boolean[][] mvmntMap = getMvmntMapFor(e.getName());
			System.out.println(e.getName()+"200");
			if(!e.getName().equals("player")) {
				Point firstStep = AStarPathfinder.calcPath(mvmntMap, e.getX(), e.getY(), this.activeCharacter.getX(), this.activeCharacter.getY());
				if(firstStep.x==-1||firstStep.y==-1)
					continue;
				moveObject(e, firstStep.x, firstStep.y);
			}
		}
		this.activeCharacter.refresh();
	}
	//movement
	private void showMovementOptions() {
		int xGridPos = this.activeCharacter.getX();
		int yGridPos = this.activeCharacter.getY();
		
		Entity character = (Entity)this.activeCharacter;
		
		int movementDistance = character.getCurrentMovement();
		
		if(movementDistance>0) {
			for(int x = xGridPos-movementDistance; x <= xGridPos+movementDistance; x++) {
				for(int y = yGridPos-movementDistance; y <= yGridPos+movementDistance;y++) {
					if(!(x==xGridPos&&y==yGridPos)) {
						
						MovementOption move = getMovementViabilityFor(x,y,"player");
						
						if(move.equals(MovementOption.VALID)) {
							highlightTile(x,y,Highlight.MVMNT_BLUE);
							Event e = new Event();
							e.setX(x);
							e.setY(y);
							e.setObject(this.activeCharacter);
							e.setEventId(this.connector.MOVE_PLAYER);
							
							this.connector.addEvent(getRelationalX(x), getRelationalY(y), Property.TILE_SIZE, Property.TILE_SIZE, e);					
						}
					}
				}
			}
			printHighlights();	
		}
	}
	private void showAttackOptions() {
		int xGridPos = this.activeCharacter.getX();
		int yGridPos = this.activeCharacter.getY();
		
		for(int x = xGridPos-1; x <= xGridPos+1; x++) {
			for(int y = yGridPos-1; y <= yGridPos+1;y++) {
				if(!(x==xGridPos&&y==yGridPos)) {
					MovementOption move = getMovementViabilityFor(x,y,"player");
					if(move.equals(MovementOption.ENEMY)) {
						highlightTile(x,y,Highlight.CMBT_RED);
						Event e = new Event();
						e.setX(x);
						e.setY(y);
						e.setObject(this.objects[x][y]);
						e.setEventId(this.connector.ATTACK);
						this.connector.addEvent(getRelationalX(x), getRelationalY(y), Property.TILE_SIZE, Property.TILE_SIZE, e);					
					}
				}
			}
		}
	}
	private void basicAttack(Event e) {
		if(this.activeCharacter.useAction(1)) {
			CombatManager.normalMelee(this.activeCharacter,e.getObject());
			if(activeNpc!=null) 
				this.activeNpcCanvas.checkUdate(activeNpc);
		}
		removeMovements();
		removeTheDead();
	}
	private boolean[][] getMvmntMapFor(String e){
		boolean[][] mvmntMap = new boolean[data.getTileData().length][data.getTileData()[0].length];
		for(int x = 0; x < this.data.getTileData().length; x++) {
			for(int y = 0; y < data.getTileData()[0].length; y++) {
				MovementOption m = getMovementViabilityFor(x, y,e);
				if(m==MovementOption.VALID||m==MovementOption.PLAYER||m==MovementOption.SELF) {
					mvmntMap[x][y]=true;
				}else {
					mvmntMap[x][y]=false;
				}
			}
		}
		return mvmntMap;
	}
	private void showAttackRange(Skill s) {
		int currentX = this.activeCharacter.getX();
		int currentY = this.activeCharacter.getY();
		int skillRange = s.getDistance();
		if(s.getTargetType().equals(TargetType.SURROUNDING)) {
			showSkillConfirm(s,currentX,currentY);
			return;
		}
		for(int x = currentX-skillRange; x <= currentX+skillRange; x++) {
			for(int y = currentY-skillRange; y <= currentY+skillRange;y++) {
				if(x>0 && y>0) {
					highlightTile(x,y,Highlight.SKLL_GREEN);
					Event e = new Event();
					e.setEventId("castSkillSelected"+x+""+y);
					e.setX(x);
					e.setY(y);
					e.setSkill(s);
					this.connector.addEvent(getRelationalX(x), getRelationalY(y), Property.TILE_SIZE, Property.TILE_SIZE, e);
				}
			}
		}
	}
	private void executeSkill(Event e) {
		CombatManager.executeSkill(this.activeCharacter,getAffectedTargets(),e.getSkill());
		if(activeNpc!=null) 
			this.activeNpcCanvas.checkUdate(activeNpc);
		addSprites(e.getSkill().getId());
		removeMovements();
		removeTheDead();
		this.buttonPanel.removeEvent(BaseActionContainer.CONFIRM);
		this.buttonPanel.removeEvent(BaseActionContainer.CANCEL);
	}
	private void cancelSkill(Event e) {
		removeMovements();
		this.buttonPanel.removeEvent(BaseActionContainer.CONFIRM);
		this.buttonPanel.removeEvent(BaseActionContainer.CANCEL);
	}
	
	private void showSkillConfirm(Skill s,int targetX,int targetY) {
		
		if(s.getTargetType()==null) {}
		
		
		switch (s.getTargetType()) {
		case SINGLE_TARGET:
			markSingleSkillSpot(s.getRadius(),targetX,targetY);
			break;
		case LINE:
			System.out.println("char x:"+this.activeCharacter.getX()+" y:"+this.activeCharacter.getY());
			System.out.println("target x:"+targetX+" y:"+targetY);
			markSkillLine(new Point(this.activeCharacter.getX(),this.activeCharacter.getY()),new Point(targetX,targetY));
			break;
		case SURROUNDING:
			markSkillSurrounding(s.getRadius(),s.getDistance());
			break;
		case NONE:
			
			break;
		default:
			break;
		}
		
		Event e = new Event();
		e.setEventId("confirmSkill");
		e.setSkill(s);
		
		Event e2 = new Event();
		e2.setEventId("cancelSkill");
		e2.setSkill(s);
		
		this.buttonPanel.addEvent(BaseActionContainer.CONFIRM, e, Resources.CONFIRM_ACTION);
		this.buttonPanel.addEvent(BaseActionContainer.CANCEL, e2, Resources.CANCEL_ACTION);
	}
	private void markSingleSkillSpot(int radius, int targetx, int targety) {
		for(int x = targetx-radius;x<=targetx+radius;x++) {
			for(int y = targety-radius;y<=targety+radius;y++) {
				System.out.println("highlihgt:"+x+""+y);
				if(x>0 && y>0) {
					highlightTile(x, y, Highlight.SKLL_GREEN);
				}
			}
		}
	}
	private void markSkillLine(Point a, Point b) {
		int dx = a.x - b.x;
		int dy = a.y - b.y;
		int xs = dx>0? -1:1;
		int ys = dy>0? -1:1;
		double m = dx==0?0:(double)dy/dx;
		
		if(dx==0||m<=-2||m>=2) {
			a.y+=ys;
		}else if(m>=-0.5&&m<=0.5) {
			a.x+=xs;
		}else {
			a.x+=xs;
			a.y+=ys;
		}
		
		highlightTile(a.x, a.y, Highlight.SKLL_GREEN);
		
		if(!a.equals(b)) {
			markSkillLine(a,b);
		}
	}
	private void markSkillSurrounding(int radius,int distance) {
		int cx = this.activeCharacter.getX();
		int cy = this.activeCharacter.getY();
		int horizon = radius+distance;
		for(int x = cx-horizon; x <= cx+horizon; x++) {
			for(int y = cy-horizon; y <= cy+horizon;y++) {
				if(!((x>(cx-distance)&&x<(cx+distance))&&((cy-distance)<y&&y<(cy+distance)))) {
					highlightTile(x, y, Highlight.SKLL_GREEN);
				}
			}
		}
	}
	private void moveObject(SecondLayerObject o, int x, int y) {
		int currentX = o.getX();
		int currentY = o.getY();
		
		o.setX(x);
		o.setY(y);
		
		int distance = calcDistance(currentX,currentY,x,y);
		
		for(Entity e : this.entities) {
			if(e.getX()==currentX && e.getY()==currentY) {
				e.setX(x);
				e.setY(y);
			}
		}
		objects[currentX][currentY] = null;
		objects[x][y] = o;
		if(o.getName().equals("player")) {
			Entity e = Entity.class.cast(o);
			e.useMovement(distance);
			removeMovements();
			setSelectPlayerEvent(o, x, y);			
		}else if(Entity.class.isInstance(o)){
			Entity e = Entity.class.cast(o);
			updateContext(e,x,y);
			e.useMovement(distance);
		}
		
		
	}
	private void removeMovements() {
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null &&
						(highlights[x][y].equals(Highlight.MVMNT_BLUE)||highlights[x][y].equals(Highlight.CMBT_RED)||highlights[x][y].equals(Highlight.SKLL_GREEN))) {
					highlights[x][y] = null;
				}
			}
		}
	}
	private MovementOption getMovementViabilityFor(int x, int y, String s) {
		
		
		if(data.getTileData()[y][x].getId()==Resources.WALL) {
			return MovementOption.OBSTACLE;
		}
		if(data.getTileData()[y][x].getId()==Resources.VOID) {
			return MovementOption.VOID;
		}
		if(data.getTileData()[y][x].getId()==Resources.ENDWALL) {
			return MovementOption.ENDWALL;
		}
		if(objects[x][y] != null &&
			Entity.class.isInstance(objects[x][y])) {
			Entity e = Entity.class.cast(objects[x][y]);
			if(e.getName().equals(s)) {
				return MovementOption.SELF;
			}
			return e.getMovement();
		}
		return MovementOption.VALID;
	}
	private void highlightTile(int x, int y,Highlight h) {
		if(x<0||y<0) {
			return;
		}
		this.highlights[x][y] = h;
	}
	private void addSprites(byte spriteId) {
		for(int i = 0; i < highlights.length; i++) {
			for(int j = 0; j < highlights[0].length; j++) {
				this.sprites[i][j] = spriteId;
			}
		}
	}
	
	//util
	private int getRelationalX(int x) {
		int result = x*Property.TILE_SIZE + Property.START_OF_ROOM_X;
		//result += xOffset*tileSize;
		return result;
	}
	private int getRelationalY(int y) {
		int result = y*Property.TILE_SIZE + Property.START_OF_ROOM_Y;
		//result += yOffset*tileSize;
		return result;
	}
	private Entity getPlayer(String name) {
		for(Entity e: entities) {
			if(e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}
	private void printHighlights() {
		for(int i = 0; i < highlights.length; i++) {
			for(int j = 0; j < highlights[0].length; j++) {
			}
		}
	}
	private List<SecondLayerObject> getAffectedTargets() {
		List<SecondLayerObject> obj=new ArrayList<>();
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null && highlights[x][y].equals(Highlight.SKLL_GREEN) && this.objects[x][y]!=null) {
					obj.add(this.objects[x][y]);
				}
			}
		}
		return obj;
	}
	private void selectNpc(SecondLayerObject obj) {
		if(NPC.class.isInstance(obj)) {
			NPC e = NPC.class.cast(obj);
			this.activeNpc = e;
		}
	}
	private void setSelectPlayerEvent(SecondLayerObject o, int x, int y) {

		Event selectPlayerEvent = new Event();
		selectPlayerEvent.setEventId(this.connector.SELECT_PLAYER);
		selectPlayerEvent.setObject(o);
		this.connector.addEvent(getRelationalX(x),getRelationalY(y),Property.TILE_SIZE,Property.TILE_SIZE,selectPlayerEvent);
	}
	private void clear() {
		removeMovements();
		this.connector.cleanMapEvents();
		setSelectPlayerEvent(this.activeCharacter,this.activeCharacter.getX(),this.activeCharacter.getY());
	}
	public boolean hasSprite() {
		for(int y = 0; y < this.sprites[0].length; y++) {
			for(int x = 0; x < this.sprites.length; x++) {
				if(this.sprites[x][y]!=0) {
					return true;
				}
			}
		}
		return false;
	}
	private void updateContext(Entity e, int newX, int newY) {
		this.connector.removeContextOf(e.getName());
		Event event = new Event();
		event.setObject(e);
		event.setEventId(this.connector.INFO_ENTITY);
		event.setX(e.getX());
		event.setY(e.getY());
		this.connector.addContext(getRelationalX(e.getX()), getRelationalY(e.getY()), Property.TILE_SIZE, Property.TILE_SIZE, event);			
	
	}
	private int calcDistance(int cx, int cy, int x, int y) {
		return Math.max((Math.abs(cx-x)),(Math.abs(cy-y)));
	}
	
	//eventhandling
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			if(yOffset>0) {
				yOffset--;
				this.connector.decrementYOffset();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			if(xOffset>0) {
				xOffset--;
				this.connector.decrementXOffset();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			if(yOffset<data.size()-Property.ROOM_VIEW_TILE_COUNT) {
				yOffset++;
				this.connector.incrementYOffset();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			if(xOffset<data.size()-Property.ROOM_VIEW_TILE_COUNT) {
				xOffset++;
				this.connector.incrementXOffset();
			}
		}
	}
	public void mouseClicked(Event e) {
		if(e.getEventId().equals(this.connector.SHOW_MOVEMENT)) {
			showMovementOptions();
		}
		if(e.getEventId().equals(this.connector.SHOW_ATTACK)) {
			showAttackOptions();
		}
		if(e.getEventId().equals(this.connector.MOVE_PLAYER)) {
			moveObject(e.getObject(),e.getX(),e.getY());
		}
		if(e.getEventId().equals(this.connector.ATTACK)) {
			basicAttack(e);
		}
		if(e.getEventId().equals(this.connector.INFO_ENTITY)) {
			selectNpc(e.getObject());
		}
		if(e.getEventId().equals("tabChange")) {
			if(this.activeNpc!=null)
				this.activeNpc.setActiveTab(e.getTab());
		}
		if(e.getEventId().equals("castSkill")) {
			clear();
			showAttackRange(e.getSkill());
		}
		if(e.getEventId().startsWith("castSkillSelected")) {
			clear();
			showSkillConfirm(e.getSkill(),e.getX(),e.getY());
		}
		if(e.getEventId().equals("confirmSkill")) {
			executeSkill(e);
		}
		if(e.getEventId().equals("cancelSkill")) {
			cancelSkill(e);
		}
		if(e.getEventId().equals(this.connector.END_TURN)) {
			removeMovements();
			endTurn();
		}
	}
}
