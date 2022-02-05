package rogue.game.world;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.game.combat.CombatManager;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.SkillType;
import rogue.game.combat.skills.Skill.TargetType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.npc.NPCLibrary;
import rogue.game.pvp.Team;
import rogue.game.world.generation.RoomData;
import rogue.game.world.objects.BattleLog;
import rogue.game.world.objects.Entity;
import rogue.game.world.objects.NPC;
import rogue.game.world.objects.PlayableCharacter;
import rogue.game.world.objects.SecondLayerObject;
import rogue.game.world.objects.Tile;
import rogue.graphics.BaseActionContainer;
import rogue.graphics.EntityInformationContainer;
import util.Highlight;
import util.MovementOption;
import util.MyColor;

public class Arena {
	
	private RoomData data;
	private Connector connector;
	private Point[][] teamPlacements=new Point[][] {
		null,
		new Point[] {new Point(10,2),new Point(11,2),new Point(9,2)}, 
		new Point[] {new Point(10,13),new Point(11,13),new Point(9,13)}
	};
	protected int xOffset = 0;
	protected int yOffset = 0;
	private int width=0,height=0;
	
	private List<Entity> entities = new ArrayList<Entity>();;
	private Highlight[][] highlights;
	private SecondLayerObject[][] objects;
	private boolean[][] visionField;
	private int[][] sprites;

	private EntityInformationContainer largeCanvas;
	private EntityInformationContainer smallCanvas;
	private PlayableCharacter activeLarge;
	private Entity activeSmall;
	private int activeTeam=0;
	
	private BattleLog log;

	protected BaseActionContainer buttonPanel;
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------c'tors------------------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	public Arena(RoomData data, Connector connector) {
		this.data = data; 
		this.connector = connector;
		this.width=data.getTileData().length;
		this.height=data.getTileData()[0].length;
		this.objects = new SecondLayerObject[this.width][this.height];
		this.visionField = new boolean[this.width][this.height];
		largeCanvas = new EntityInformationContainer(new Entity(), EntityInformationContainer.PLAYER_CONFIG, Resources.textEditorConfig, connector);
		smallCanvas = new EntityInformationContainer(new Entity(), EntityInformationContainer.ENTITY_CONFIG, Resources.textEditorConfig, connector);
		this.highlights = new Highlight[data.getTileData().length][data.getTileData()[0].length];
		this.buttonPanel = new BaseActionContainer(connector);
		this.sprites = new int[data.getTileData().length][data.getTileData()[0].length];
		this.log = new BattleLog(Resources.textEditorConfig,this.connector);
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------init--------------------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	public void initTeams(List<Team> teams) {
		for(Team team : teams) {
			int i = 0;
			int j = 0;
			for(PlayableCharacter c : team.getCharacters()) {
				c.setX(teamPlacements[team.getTeamNr()][i].x);
				c.setY(teamPlacements[team.getTeamNr()][i].y);
				entities.add(c);
				i++;
			}
			for(NPC npc : team.getMobs()) {
				npc.setX(teamPlacements[team.getTeamNr()][j].x);
				npc.setY(teamPlacements[team.getTeamNr()][j].y);
				entities.add(npc);
				j++;
			}
		}
	}
	public void openViewForTeamNr(int nr) {
		this.activeTeam=nr;
		removeSelectPlayer();
		for(Entity e : this.entities) {
			if(e.getTeam()==nr && PlayableCharacter.class.isInstance(e)) {
				PlayableCharacter pc = PlayableCharacter.class.cast(e);
				setSelectPlayerEvent(pc, pc.getX(), pc.getY());
				this.activeLarge=pc;
			}
			if(e.getTeam()!=nr && PlayableCharacter.class.isInstance(e)) {
				Event event = new Event();
				event.setObject(e);
				event.setEventId(Connector.INFO_OBJECT);
				event.setX(e.getX());
				event.setY(e.getY());
				this.connector.addContext(getRelationalX(e.getX()), getRelationalY(e.getY()), Property.TILE_SIZE, Property.TILE_SIZE, event);	
			}
		}
		highLightActive();
		this.activeSmall=new Entity();
		refreshVision();
	}
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------update------------------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	public void update() {
		if(this.activeLarge!=null) {
			this.largeCanvas.checkUdate(activeLarge);
		}
		if(this.activeSmall!=null) {
			this.smallCanvas.checkUdate(activeSmall);
		}
	}	
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------render------------------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	public List<int[]> render(){
		List <int[]> compartments = new ArrayList<int[]>();
		
		int[] map = new int[Property.ROOM_SIZE*Property.ROOM_SIZE];
		map=renderRoom(map);
		map=renderEntities(map);
		map=renderHighlights(map);
		compartments.add(map);
		
		int[] largeCanvasPixels = new int[Property.ACTIVE_CHAR_HEIGHT*Property.ACTIVE_CHAR_WIDTH];
		largeCanvasPixels = this.largeCanvas.getPixels();
		compartments.add(largeCanvasPixels);
		
		int[] smallCanvasPixels = new int[Property.ACTIVE_NPC_HEIGHT*Property.ACTIVE_NPC_WIDTH];
		smallCanvasPixels = this.smallCanvas.getPixels();
		compartments.add(smallCanvasPixels);
		
		int[] minimap = new int[Property.MINIMAP_HEIGHT*Property.MINIMAP_WIDTH];
		minimap = getMinimap(minimap);
		compartments.add(minimap);
		
		int[] buttons = new int[Property.BUTTON_PANEL_WIDTH*Property.BUTTON_PANEL_HEIGHT];
		buttons = buttonPanel.getPixels();
		compartments.add(buttons);
		
		int[] logs = new int[Property.LOG_WIDTH*Property.LOG_HEIGHT];
		logs = this.log.getPixels();
		compartments.add(logs);
		
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
				if(this.visionField[tileX+xOffset][tileY+yOffset]) {
					p[x + y*Property.ROOM_SIZE] = Resources.TEXTURES.get(t.getId())[xInTile+yInTile*Property.TILE_SIZE];
				}else {
					Color color = new Color(Resources.TEXTURES.get(t.getId())[xInTile+yInTile*Property.TILE_SIZE]);
					Color darker = darken(color,0.1);
					p[x + y*Property.ROOM_SIZE] = darker.getRGB();
				}
				
			}
		}
		return p;
	}
    private Color darken(final Color color, final double percentage) {
        if (percentage < 0.01 || percentage > 1.00) {
            throw new IllegalArgumentException("Percentage must be between [0.01 - 1.00]");
        }
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        r = (int) (r * (1.00 - percentage));
        g = (int) (g * (1.00 - percentage));
        b = (int) (b * (1.00 - percentage));
        return new Color(r, g, b, color.getAlpha());
    }
	private int[] renderEntities(int[] p) {
		for(Entity e : entities) {
			if((e.getX()>=xOffset)&&(e.getX()<=xOffset+Property.ROOM_VIEW_TILE_COUNT) &&
				(e.getY()>=yOffset)&&(e.getY()<=yOffset+Property.ROOM_VIEW_TILE_COUNT)) {
				
				if(e.getTeam()!=this.activeTeam && !this.visionField[e.getX()][e.getY()]) {
					continue;
				}
				for(int y = 0; y < Property.TILE_SIZE; y++) {
					for(int x = 0; x < Property.TILE_SIZE; x++) {
						int relX = ((e.getX()+(-1)*xOffset)*Property.TILE_SIZE)+x;
						int relY = ((e.getY()+(-1)*yOffset)*Property.TILE_SIZE)+y;
						int color = Resources.CHARACTERS.get(e.getId())[x+y*Property.TILE_SIZE];
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
				if(highlights[x][y]!=null
						&& x>=xOffset && y>=yOffset && x<16+xOffset && y<16+yOffset) {
					highlights[x][y].printHighlight(p, x, y, xOffset, yOffset);
				}
			}
		}
		return p;
	}
	protected int[] getMinimap(int[] map) {
		for(int x = 0 ; x < data.getTileData().length*4;x++) {
			for(int y = 0; y < data.getTileData()[0].length*4;y++) {
				map[x+y*Property.MINIMAP_WIDTH]=MyColor.getMinimapColorForTiles(data.getTileData()[y/4][x/4].getId()).VALUE;
				Entity e = getEntityAt(x/4,y/4);
				if(e!=null && this.visionField[e.getX()][e.getY()]) {
					map[x+y*Property.MINIMAP_WIDTH]=MyColor.getMinimapColorForTeam(e.getTeam()).VALUE;
				}
			}
		}
		return map;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------vision------------------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	private void refreshVision() {
		this.visionField = new boolean[this.width][this.height];
		for(Entity e : this.entities) {
			if(e.getTeam()==this.activeTeam) {
				getVision(e.getX(),e.getY(),e.getVisionDistance());
			}
		}
		//print();
	}
	private void getVision(int x, int y, int vis) {
		int[][] visionValues = getVisionValues();
		this.visionField[x][y]= true;
		for(int i = x-vis; i <= x+vis; i++) {
			for(int j = y-vis; j <= y+vis; j++) {
				if(i>=0&&i<this.width  &&  j<this.height&&j>=0) {
					checkVisionOf(i,j,x,y,visionValues,true,x,y);
				}
			}
		}
	}
	private void checkVisionOf(int x, int y, int startx, int starty, int[][] visF, boolean free, int cx, int cy) {
		int dy = starty-y;
		int dx = startx-x;
		int xs = dx>0? -1:1;
		int ys = dy>0? -1:1;
		double m = dx==0?0:(double)dy/dx;
		
		if(dx==0||m<=-2||m>=2) {
			starty+=ys;
		}else if(m>=-0.5&&m<=0.5) {
			startx+=xs;
		}else {
			startx+=xs;
			starty+=ys;
		}
		
		if(!free) {
			if(this.visionField[startx][starty]!=true)
				this.visionField[startx][starty] = false;
		}else {
			if(proxCheck(startx,starty,cx,cy)) {
				this.visionField[startx][starty]=true;
				if(1==check(startx,starty,visF,cx,cy))
					free=false;
			}else if(check(startx,starty,visF,cx,cy)!=0) {
				if(this.visionField[startx][starty]!=true) {
					this.visionField[startx][starty]=false;
					free=false;
				}
			}else {
				this.visionField[startx][starty]=true;
			}
		}
		if(startx!=x || starty!=y) {
			checkVisionOf(x, y, startx, starty, visF, free, cx, cy);
		}
	}
	private int[][] getVisionValues(){
		int[][] visionValues = new int[this.width][this.height];
		for(int x = 0; x < visionValues.length; x++) {
			for(int y = 0; y < visionValues[0].length; y++) {
				Tile t = data.getTileData()[y][x];
				if(t.getId()==Resources.TREE || t.getId()==Resources.TALLGRASS) {
					visionValues[x][y] = 1;
				}
			}
		}
		return visionValues;
	}
	private int check(int x, int y, int[][] visF, int cx, int cy) {
		return visF[x][y];
	}
	private boolean proxCheck(int x, int y, int x2, int y2) {
		if(Math.max(x2-x>=0?x2-x:x-x2,y2-y>=0?y2-y:y-y2)==1) {
			return true;
		}
		return false;
	}
	
	private void print() {
		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				String c = this.visionField[x][y]?".":"0";
				System.out.print(c);
			}
			System.out.println();
		}
	}
	private void vision(Skill s) {
		
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null &&
						(highlights[x][y].equals(Highlight.SKLL_GREEN)||
								highlights[x][y].equals(Highlight.SKILL_SELECT))) {
					this.visionField[x][y] = true;
				}
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------object-handling---------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	private void moveObject(SecondLayerObject o, int x, int y,boolean manual, boolean tp) {
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
		if(manual) {
			Entity e = Entity.class.cast(o);
			if(!tp)
				e.useMovement(distance);
			removeMovements();
//			setSelectPlayerEvent(o, x, y);			
		}else if(Entity.class.isInstance(o)){
			Entity e = Entity.class.cast(o);
//			updateContext(e,x,y);
			if(!tp)
				e.useMovement(distance);
		}
	}
	private void onMoveCharacter(int x, int y,boolean isTp) {
		int currentX = this.activeLarge.getX();
		int currentY = this.activeLarge.getY();
		
		this.activeLarge.setX(x);
		this.activeLarge.setY(y);
		
		int dist = calcDistance(currentX, currentY, x, y);
		if(!isTp)
			this.activeLarge.useMovement(dist);
		removeMovements();
		highLightActive();
		setSelectPlayerEvent(PlayableCharacter.class.cast(this.activeLarge), x, y);
		refreshVision();
		
	}
	private void removeTheDead() {
		List<Entity> operatedList = new ArrayList<>();
		this.entities.stream()
			.filter(e->e.getCurrentLife()<1)
			.forEach(e -> {
				this.connector.removeContextOf(e.getName());
				operatedList.add(e);
				});
		this.entities.removeAll(operatedList);
	}
	private void showMovementOptions() {
		int xGridPos = this.activeLarge.getX();
		int yGridPos = this.activeLarge.getY();
		int movementDistance = Math.min(this.activeLarge.getCurrentMovement(),1);
		if(movementDistance>0) {
			for(int x = xGridPos-movementDistance; x <= xGridPos+movementDistance; x++) {
				for(int y = yGridPos-movementDistance; y <= yGridPos+movementDistance;y++) {
					if(!(x==xGridPos&&y==yGridPos)) {
						
						MovementOption move = getMovementViabilityFor(x,y,this.activeLarge.getTeam());
						
						if(move.equals(MovementOption.VALID)) {
							highlightTile(x,y,Highlight.MVMNT_BLUE);
							Event e = new Event();
							e.setX(x);
							e.setY(y);
							e.setEventId(Connector.MOVE_PLAYER);
							
							this.connector.addEvent(getRelationalX(x), getRelationalY(y), Property.TILE_SIZE, Property.TILE_SIZE, e);					
						}
					}
				}
			}
		}
	}
	private void showAttackOptions() {
		int xGridPos = this.activeLarge.getX();
		int yGridPos = this.activeLarge.getY();
		
		for(int x = xGridPos-1; x <= xGridPos+1; x++) {
			for(int y = yGridPos-1; y <= yGridPos+1;y++) {
				if(!(x==xGridPos&&y==yGridPos)) {
					MovementOption move = getMovementViabilityFor(x,y,this.activeLarge.getTeam());
					if(move.equals(MovementOption.ENEMY)) {
						highlightTile(x,y,Highlight.CMBT_RED);
						Event e = new Event();
						e.setX(x);
						e.setY(y);
						e.setObject(getEntityAt(x, y)!=null?getEntityAt(x,y):this.objects[x][y]);
						e.setEventId(Connector.ATTACK);
						this.connector.addEvent(getRelationalX(x), getRelationalY(y), Property.TILE_SIZE, Property.TILE_SIZE, e);					
					}
				}
			}
		}
	}
	private void basicAttack(Event e) {
		if(this.activeLarge.useAction(1)) {
			CombatManager.normalMelee(this.activeLarge,e.getObject(),this.log);
			if(activeSmall!=null) 
				this.smallCanvas.checkUdate(activeSmall);
			if(activeLarge!=null)
				this.largeCanvas.checkUdate(activeLarge);
		}
		removeMovements();
		removeTheDead();
	}
	private void onSelectLargeEntity(PlayableCharacter pc) {
		if(pc.getTeam()!=this.activeTeam)
			return;
		this.activeLarge = pc;
		highLightActive();
	}
	private void onSelectSmallEntity(Entity e) {
		if(PlayableCharacter.class.isInstance(e)&&e.getTeam()==this.activeTeam)
			return;
		this.activeSmall = e;
	}
	private void onTabChange(Event e) {
		if(e.getEventId().startsWith(this.largeCanvas.getPrefix()) && this.activeLarge!=null) {
			this.activeLarge.setActiveTab(e.getTab());
		}else if(this.activeSmall!=null){
			this.activeSmall.setActiveTab(e.getTab());
		}
	}
	private void highLightActive() {
		removeHighlightsOfType(Highlight.SELECT_WHITE);
		highlightTile(this.activeLarge.getX(), this.activeLarge.getY(), Highlight.SELECT_WHITE);
	}
	private void summon(Skill s) {
		
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null &&
						(highlights[x][y].equals(Highlight.SKLL_GREEN)||
								highlights[x][y].equals(Highlight.SKILL_SELECT))) {
					
					NPC npc = NPCLibrary.getNpc(s.getSummonedId());
					npc.setX(x);npc.setY(y);
					this.entities.add(npc);
					Event event = new Event();
					event.setObject(npc);
					event.setEventId(Connector.INFO_OBJECT);
					event.setX(x);
					event.setY(y);
					this.connector.addContext(getRelationalX(x), getRelationalY(y), Property.TILE_SIZE, Property.TILE_SIZE, event);
				}
			}
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------skill-handling----------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	private void onSkillChosen(Skill s) {
		clear();
		if(s.getType().equals(SkillType.PASSIVE))
			return;
		int currentX = this.activeLarge.getX();
		int currentY = this.activeLarge.getY();
		
		int skillRange = s.getDistance();
		if(s.getTarget().equals(TargetType.SURROUNDING)||s.getTarget().equals(TargetType.SELF)) {
			onTargetChosen(s,currentX,currentY);
			return;
		}
		for(int x = currentX-skillRange; x <= currentX+skillRange; x++) {
			for(int y = currentY-skillRange; y <= currentY+skillRange;y++) {
				if(x>0 && y>0) {
					if(s.getTarget().equals(TargetType.SINGLE_FREE) && 
							!getMovementViabilityFor(x, y, this.activeTeam).equals(MovementOption.VALID)) {
						continue;
					}
					highlightTile(x,y,Highlight.SKLL_GREEN);
					if(x-xOffset<0||x-xOffset>15  ||  y-yOffset<0||y-yOffset>15) {
						continue;
					}
					Event e = new Event();
					e.setEventId(Connector.TARGET_CHOSEN+(x-xOffset)+""+(y-yOffset));
					e.setX(x-xOffset);
					e.setY(y-yOffset);
					e.setSkill(s.getId());
					this.connector.addEvent(getRelationalX(x-xOffset), getRelationalY(y-yOffset), Property.TILE_SIZE, Property.TILE_SIZE, e);
				}
			}
		}

	}

	private void onTargetChosen(Skill s,int targetX,int targetY) {
		clear();
		
		switch (s.getTarget()) {
		case SINGLE_TARGET:
			markSingleSkillSpot(s.getRadius(),targetX,targetY);
			break;
		case LINE:
			markSkillLine(new Point(this.activeLarge.getX(),this.activeLarge.getY()),new Point(targetX,targetY));
			break;
		case SURROUNDING:
			markSkillSurrounding(s.getRadius(),s.getDistance());
			break;
		case SINGLE_FREE:
			markSingleSkillSpot(s.getRadius(),targetX,targetY);
			break;
		case SELF:
			highlightTile(this.activeLarge.getX(),this.activeLarge.getY(),Highlight.SKLL_GREEN);
			break;
		case NONE:
			break;
		default:
			break;
		}
		Event e = new Event();
		e.setEventId(Connector.CONFIRM_SKILL);
		e.setSkill(s.getId());
		
		Event e2 = new Event();
		e2.setEventId(Connector.CANCEL_SKILL);
		e2.setSkill(s.getId());
		
		this.buttonPanel.addEvent(BaseActionContainer.CONFIRM, e, Resources.CONFIRM_ACTION);
		this.buttonPanel.addEvent(BaseActionContainer.CANCEL, e2, Resources.CANCEL_ACTION);
	}
	private void markSingleSkillSpot(int radius, int targetx, int targety) {
		System.out.println(targetx + " " + targety);
		for(int x = targetx-radius;x<=targetx+radius;x++) {
			for(int y = targety-radius;y<=targety+radius;y++) {
				if(x>0 && y>0) {
					highlightTile(x, y, Highlight.SKLL_GREEN);	
				}
			}
		}
		highlightTile(targetx,targety,Highlight.SKILL_SELECT);
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
		int cx = this.activeLarge.getX();
		int cy = this.activeLarge.getY();
		int horizon = radius+distance;
		for(int x = cx-horizon; x <= cx+horizon; x++) {
			for(int y = cy-horizon; y <= cy+horizon;y++) {
				if(!((x>(cx-distance)&&x<(cx+distance))&&((cy-distance)<y&&y<(cy+distance)))) {
					highlightTile(x, y, Highlight.SKLL_GREEN);
				}
			}
		}
	}
	private void onExecuteSkill(Event e) {
		Skill s = SkillLibrary.getSkill(e.getSkill());
		boolean success = CombatManager.executeSkill(this.activeLarge,getAffectedTargets(),s,this.log);
		update();
		if(success) {
			if((s.getType().equals(SkillType.MOVEMENT) || 
					s.hasTP())){
				Point p = getSkillSelect();
				if(p!=null)
					onMoveCharacter(p.x, p.y, true);
				highLightActive();
			}
			if(s.getType().equals(SkillType.SUMMON)) {
				summon(s);
			}
			if(s.getType().equals(SkillType.VISION)) {
				vision(s);
			}
		}
		addSprites(e.getSkill());
		removeMovements();
		removeTheDead();
		this.buttonPanel.removeEvent(BaseActionContainer.CONFIRM);
		this.buttonPanel.removeEvent(BaseActionContainer.CANCEL);
		resetSelectPlayerEvents();
	}
	private void onCancelSkill(Event e) {
		removeMovements();
		this.buttonPanel.removeEvent(BaseActionContainer.CONFIRM);
		this.buttonPanel.removeEvent(BaseActionContainer.CANCEL);
		resetSelectPlayerEvents();
	}
	private void addSprites(int spriteId) {
		for(int i = 0; i < highlights.length; i++) {
			for(int j = 0; j < highlights[0].length; j++) {
				this.sprites[i][j] = spriteId;
			}
		}
	}
	private List<SecondLayerObject> getAffectedTargets() {
		List<SecondLayerObject> obj=new ArrayList<>();
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null && (highlights[x][y].equals(Highlight.SKLL_GREEN)||highlights[x][y].equals(Highlight.SKILL_SELECT))) {
					if(this.objects[x][y]!=null) {
						obj.add(this.objects[x][y]);
					}
					if(getEntityAt(x, y)!=null) {
						obj.add(getEntityAt(x, y));
					}
				}
			}
		}
		return obj;
	}
	private Point getSkillSelect() {
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null && highlights[x][y].equals(Highlight.SKILL_SELECT)) {
					return new Point(x,y);
				}
			}
		}
		return null;
	}
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------utility-----------------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	private int getRelationalX(int x) {
		int result = x*Property.TILE_SIZE + Property.START_OF_ROOM_X;
		return result;
	}
	private int getRelationalY(int y) {
		int result = y*Property.TILE_SIZE + Property.START_OF_ROOM_Y;
		return result;
	}
	private void setSelectPlayerEvent(PlayableCharacter pc, int x, int y) {
		Event selectPlayerEvent = new Event();
		selectPlayerEvent.setEventId(Connector.SELECT_PLAYER);
		selectPlayerEvent.setCharacter(pc);
		this.connector.addEvent(getRelationalX(x),getRelationalY(y),Property.TILE_SIZE,Property.TILE_SIZE,selectPlayerEvent);
	}
	private void removeSelectPlayer() {
		this.connector.removeEvent(Connector.SELECT_PLAYER);
	}
	private Entity getEntityAt(int x, int y) {
		for(Entity e : this.entities) {
			if(e.getX()==x && e.getY()==y) {
				return e;
			}
		}
		return null;
	}
	private MovementOption getMovementViabilityFor(int x, int y, int teamNr) {
		if(data.getTileData()[y][x].getId()==Resources.WALL||data.getTileData()[y][x].getId()==Resources.TREE) {
			return MovementOption.OBSTACLE;
		}
		if(data.getTileData()[y][x].getId()==Resources.VOID) {
			return MovementOption.VOID;
		}
		if(data.getTileData()[y][x].getId()==Resources.ENDWALL) {
			return MovementOption.ENDWALL;
		}
		Entity e = getEntityAt(x, y);
		if(e != null) {
			if(e.getTeam() == teamNr) {
				return MovementOption.SELF;
			}else {
				return MovementOption.ENEMY;
			}
		}
		return MovementOption.VALID;
	}
	private void highlightTile(int x, int y,Highlight h) {
		if(x<0||y<0) {
			return;
		}
		this.highlights[x][y] = h;
	}
	private void removeMovements() {
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null &&
						(highlights[x][y].equals(Highlight.MVMNT_BLUE)||
								highlights[x][y].equals(Highlight.CMBT_RED)||
								highlights[x][y].equals(Highlight.SKLL_GREEN)||
								highlights[x][y].equals(Highlight.SKILL_SELECT))) {
					highlights[x][y] = null;
				}
			}
		}
	}
	private void endForTeam() {
		for(Entity e: this.entities) {
			if(e.getTeam()==this.activeTeam) {
				e.endOfTurn();
			}
		}
	}
	private int calcDistance(int cx, int cy, int x, int y) {
		return Math.max((Math.abs(cx-x)),(Math.abs(cy-y)));
	}
	private void clear() {
		removeMovements();
		this.connector.cleanMapEvents();
		setSelectPlayerEvent(PlayableCharacter.class.cast(this.activeLarge),this.activeLarge.getX(),this.activeLarge.getY());
	}
	private void endTurn() {
		endForTeam();
		removeMovements();
		removeSelectPlayer();
		this.activeLarge.refresh();
	}
	private void removeHighlightsOfType(Highlight h) {
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null && highlights[x][y].equals(h)) {
					highlights[x][y] = null;
				}
			}
		}
	}
	private void resetSelectPlayerEvents() {
		for(Entity e : this.entities) {
			if(PlayableCharacter.class.isInstance(e) && e.getTeam()==this.activeTeam) {
				PlayableCharacter pc  = PlayableCharacter.class.cast(e);
				setSelectPlayerEvent(pc, pc.getX(), pc.getY());
			}
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------events------------------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
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
		if(e.getEventId().equals(		Connector.SELECT_PLAYER)) {
			onSelectLargeEntity(e.getCharacter());
		}else if(e.getEventId().equals(			Connector.SHOW_MOVEMENT)) {
			showMovementOptions();
		}else if(e.getEventId().equals(		Connector.SHOW_ATTACK)) {
			showAttackOptions();
		}else if(e.getEventId().equals(		Connector.MOVE_PLAYER)) {
			onMoveCharacter(e.getX(),e.getY(),false);
		}else if(e.getEventId().equals(		Connector.ATTACK)) {
			basicAttack(e);
		}else if(e.getEventId().equals(		Connector.INFO_OBJECT)) {
			onSelectSmallEntity(Entity.class.cast(e.getObject()));
		}else if(e.getEventId().contains(	Connector.TAB_CHANGE)) {
			onTabChange(e);
		}else if(e.getEventId().equals(		Connector.SKILL_CHOSEN)) {
			onSkillChosen(SkillLibrary.getSkill(e.getSkill()));
		}else if(e.getEventId().startsWith(	Connector.TARGET_CHOSEN)) {
			onTargetChosen(SkillLibrary.getSkill(e.getSkill()),e.getX(),e.getY());
		}else if(e.getEventId().equals(		Connector.CONFIRM_SKILL)) {
			onExecuteSkill(e);
		}else if(e.getEventId().equals(		Connector.CANCEL_SKILL)) {
			onCancelSkill(e);
		}else if(e.getEventId().equals(		Connector.END_TURN)) {
			endTurn();
		}else if(e.getEventId().equals(Connector.LOG_UP) ||
				e.getEventId().equals(Connector.LOG_DOWN)) {
			this.log.mouseClicked(e);
		}
	}
}
