package rogue.game.world;

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
import rogue.game.world.objects.PlayableCharacter;
import rogue.game.world.objects.SecondLayerObject;
import rogue.game.world.objects.Tile;
import rogue.graphics.EntityInformationContainer;
import util.Highlight;
import util.MovementOption;

public class Room {
	
	private RoomData data;
	private EntityInformationContainer activeNpcCanvas;
	
	private Connector connector;

	private SecondLayerObject activeCharacter;
	private Entity activeNpc;
	
	private ArrayList<Entity> entities;
	private SecondLayerObject[][] objects;
	private Highlight[][] highlights;
	
	private final static int xPlayerStart = 2;
	private final static int yPlayerStart = 2;
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	public Room(RoomData data, Connector connector) {
		this.data = data;
		this.connector = connector;
		this.entities = new ArrayList<Entity>();
		this.objects = new SecondLayerObject[data.getTileData().length][data.getTileData()[0].length];
		this.highlights = new Highlight[data.getTileData().length][data.getTileData()[0].length];
		this.activeNpcCanvas = new EntityInformationContainer(new Entity(), connector);
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
	public void initEnemies() {
		Entity enemy = new Entity(4,5,Resources.SKELETON,this.connector,"skeleton",Resources.SKELETONMALE,false,MovementOption.ENEMY);
		enemy.setMeeleeDef1(5);
		entities.add(enemy);
		objects[enemy.getX()][enemy.getY()] = enemy;
		
		Entity enemy2 = new Entity(5,5,Resources.SKELETON,this.connector,"skeleton2",Resources.KNIGHTMALE,false,MovementOption.ENEMY);
		enemy2.setMeeleeDef1(5);
		entities.add(enemy2);
		objects[enemy2.getX()][enemy2.getY()] = enemy2;
		
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
		
		int[] npcInfo = new int[420*420];
		npcInfo = activeNpcCanvas.getPixels();
		compartments.add(npcInfo);
		
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
			if((e.getX()>=xOffset)&&(e.getX()<=xOffset+Property.ROOM_VIEW_TILE_COUNT) &&
				(e.getY()>=yOffset)&&(e.getY()<=yOffset+Property.ROOM_VIEW_TILE_COUNT)) {
				for(int y = 0; y < Property.TILE_SIZE; y++) {
					for(int x = 0; x < Property.TILE_SIZE; x++) {
						int relX = ((e.getX()+(-1)*xOffset)*Property.TILE_SIZE)+x;
						int relY = ((e.getY()+(-1)*yOffset)*Property.TILE_SIZE)+y;
						
						int color = Resources.TEXTURES.get(e.getId())[x+y*Property.TILE_SIZE];
						if(color!=-12450784) {
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
					this.objects[x][y] = null;
				}
			}
		}
	}
	
	//movement
	private void showMovementOptions(SecondLayerObject o) {
		int xGridPos = o.getX();
		int yGridPos = o.getY();
		
		for(int x = xGridPos-1; x <= xGridPos+1; x++) {
			for(int y = yGridPos-1; y <= yGridPos+1;y++) {
				if(!(x==xGridPos&&y==yGridPos)) {
					
					MovementOption move = getMovementViability(x,y);
					
					if(move.equals(MovementOption.VALID)) {
						highlightTile(x,y,Highlight.MVMNT_BLUE);
						Event e = new Event();
						e.setX(x);
						e.setY(y);
						e.setObject(o);
						e.setEventId(this.connector.MOVE_PLAYER);
						
						this.connector.addEvent(getRelationalX(x), getRelationalY(y), Property.TILE_SIZE, Property.TILE_SIZE, e);					
					}
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
		printHighlights();
	}
	private void moveObject(SecondLayerObject o, int x, int y) {
		int currentX = o.getX();
		int currentY = o.getY();
		
		o.setX(x);
		o.setY(y);
		
		for(Entity e : this.entities) {
			if(e.getX()==currentX && e.getY()==currentY) {
				e.setX(x);
				e.setY(y);
			}
		}
		objects[currentX][currentY] = null;
		objects[x][y] = o;
		removeMovements();
		setSelectPlayerEvent(o, x, y);
	}
	private void removeMovements() {
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null &&
						(highlights[x][y].equals(Highlight.MVMNT_BLUE)||highlights[x][y].equals(Highlight.CMBT_RED))) {
					highlights[x][y] = null;
				}
			}
		}
	}
	private MovementOption getMovementViability(int x, int y) {
		
		
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
			return e.getMovement();
		}
		return MovementOption.VALID;
	}
	private void highlightTile(int x, int y,Highlight h) {
		this.highlights[x][y] = h;
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
	private void selectEntity(SecondLayerObject obj) {
		if(Entity.class.isInstance(obj)) {
			Entity e = Entity.class.cast(obj);
			this.activeNpc = e;
		}
	}
	private void setSelectPlayerEvent(SecondLayerObject o, int x, int y) {

		Event selectPlayerEvent = new Event();
		selectPlayerEvent.setEventId(this.connector.SELECT_PLAYER);
		selectPlayerEvent.setObject(o);
		this.connector.addEvent(getRelationalX(x),getRelationalY(y),Property.TILE_SIZE,Property.TILE_SIZE,selectPlayerEvent);
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
		if(e.getEventId().equals(this.connector.SELECT_PLAYER)) {
			if(activeCharacter.getName().equals(e.getObject().getName())) {
				showMovementOptions(activeCharacter);
			}
		}
		if(e.getEventId().equals(this.connector.MOVE_PLAYER)) {
			this.connector.clearMovement(activeCharacter.getName());
			moveObject(e.getObject(),e.getX(),e.getY());
		}
		if(e.getEventId().equals(this.connector.ATTACK)) {
			CombatManager.normalMelee(this.activeCharacter,e.getObject());
			removeMovements();
			removeTheDead();
		}
		if(e.getEventId().equals(this.connector.INFO_ENTITY)) {
			selectEntity(e.getObject());
		}
	}
}
