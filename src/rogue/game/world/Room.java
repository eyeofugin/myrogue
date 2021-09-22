package rogue.game.world;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Resources;
import rogue.game.world.generation.RoomData;
import rogue.game.world.objects.PlayableCharacter;
import rogue.game.world.objects.Enemy;
import rogue.game.world.objects.Entity;
import rogue.game.world.objects.SecondLayerObject;
import rogue.game.world.objects.Tile;
import rogue.graphics.InformationContainer;

public class Room {

	//constants
	private final static int tileSize = 32;
	private final static int marginLeft = 431;
	private final static int marginTop = 12;

	
	private RoomData data;
	private InformationContainer activeNpcCanvas;
	
	private Connector connector;
	

	private SecondLayerObject activeCharacter;
	private SecondLayerObject activeNpc;
	
	private ArrayList<Entity> entities;
	private SecondLayerObject[][] objects;
	private boolean[][] highlights;
	
	private final static int xPlayerStart = 2;
	private final static int yPlayerStart = 2;
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	public Room(RoomData data, Connector connector) {
		this.data = data;
		this.connector = connector;
		this.entities = new ArrayList<Entity>();
		this.objects = new SecondLayerObject[data.getTileData().length][data.getTileData()[0].length];
		this.highlights = new boolean[data.getTileData().length][data.getTileData()[0].length];
		initEnemies();
	}
	
	//init
	public void initiallyPlacePlayer(PlayableCharacter player) {
		player.setX(xPlayerStart);
		player.setY(yPlayerStart);
		entities.add(player);
		objects[xPlayerStart][yPlayerStart] = player;
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
	}
	public void initEnemies() {
		Enemy enemy = new Enemy(10,10,(byte)4,"skeleton",(byte)1,this.connector);
		entities.add(enemy);
		objects[10][10] = enemy;
	}
	
	//rendering
	public List<int[]> render() {
		
		List<int[]> compartments = new ArrayList<int[]>();
		
		int[] map = new int[1056*1056];
		map=renderRoom(map);
		map=renderEntities(map);
		map=renderHighlights(map);
		compartments.add(map);
		
//		int[] npcInfo = new int[420*420];
//		npcInfo = activeNpcCanvas.getPixels();
//		compartments.add(npcInfo);
		
		return compartments;
	}
	private int[] renderRoom(int[]p) {
		for(int y = 0; y < 1056; y++) {
			for(int x = 0; x < 1056; x++) {
				int tileX = x/32;
				int tileY = y/32;
				int xInTile = x%32;
				int yInTile = y%32;
				Tile t = data.getTileData()[tileY+yOffset][tileX+xOffset];
				p[x + y*1056] = Resources.TEXTURES.get(t.getId())[xInTile+yInTile*32];
			}
		}
		
		return p;
	}
	private int[] renderEntities(int[] p) {
		
		for(Entity e : entities) {
			if((e.getX()+xOffset>0)&&(e.getX()+xOffset<33) &&
				(e.getY()+yOffset>0)&&(e.getY()+yOffset<33)) {
				for(int y = 0; y < 32; y++) {
					for(int x = 0; x < 32; x++) {
						int relX = ((e.getX()+(-1)*xOffset)*32)+x;
						int relY = ((e.getY()+(-1)*yOffset)*32)+y;
						
						int color = Resources.TEXTURES.get(e.getId())[x+y*32];
						if(color!=-12450784) {
							p[relX+relY*1056] = color;	
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
				if(highlights[x][y]) {
					for(int i = 1; i < 31; i++) {
						
						p[((x*32)+i+(-1)*xOffset*32)+((y*32)+1+(-1)*yOffset*32)*1056] = 342415;
						p[((x*32)+i+(-1)*xOffset*32)+((y*32)+30+(-1)*yOffset*32)*1056] = 342415;
						p[((x*32)+1+(-1)*xOffset*32)+((y*32)+i+(-1)*yOffset*32)*1056] = 342415;
						p[((x*32)+30+(-1)*xOffset*32)+((y*32)+i+(-1)*yOffset*32)*1056] = 342415;
					}
				}
			}
		}
		return p;
	}
	
	//movement
	private void showMovementOptions(SecondLayerObject o) {
		int xGridPos = o.getX();
		int yGridPos = o.getY();
		
		for(int x = xGridPos-1; x <= xGridPos+1; x++) {
			for(int y = yGridPos-1; y <= yGridPos+1;y++) {
				if(!(x==xGridPos&&y==yGridPos)) {
					
					boolean isViableMovement = getMovementViability(x,y);
					
					if(isViableMovement) {
						highlightTile(x,y);
						Event e = new Event();
						e.setX(x);
						e.setY(y);
						e.setObject(o);
						e.setEventId("characterMovement");
						
						this.connector.addEvent(getRelationalX(x), getRelationalY(y), tileSize, tileSize, e);					
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
		this.highlights = new boolean[this.highlights.length][this.highlights[0].length];
		setSelectPlayerEvent(o, x, y);
	}
	private boolean getMovementViability(int x, int y) {
		
		boolean result = true;
		
		if(data.getTileData()[y][x].getId()==Resources.WALL) {
			result = false;
		}
		if(objects[x][y] != null) {
			result = false;
		}
		return result;
	}
	private void highlightTile(int x, int y) {
		this.highlights[x][y] = true;
	}
	
	//util
	private int getRelationalX(int x) {
		int result = x*tileSize + marginLeft;
		//result += xOffset*tileSize;
		return result;
	}
	private int getRelationalY(int y) {
		int result = y*tileSize + marginTop;
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
				if(highlights[i][j]) {
				}
			}
		}
	}
	private void setSelectPlayerEvent(SecondLayerObject o, int x, int y) {

		Event selectPlayerEvent = new Event();
		selectPlayerEvent.setEventId("selectPlayerEvent");
		selectPlayerEvent.setObject(o);
		this.connector.addEvent(getRelationalX(x),getRelationalY(y),tileSize,tileSize,selectPlayerEvent);
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
			if(yOffset<1) {
				yOffset++;
				this.connector.incrementYOffset();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			if(xOffset<1) {
				xOffset++;
				this.connector.incrementXOffset();
			}
		}
	}
	public void mouseClicked(Event e) {
		if(e.getEventId().equals("selectPlayerEvent")) {
			
			activeCharacter = getPlayer(e.getObject().getName());
			if(activeCharacter==null) {
				return;
			}
			showMovementOptions(activeCharacter);
		}
		if(e.getEventId().equals("characterMovement")) {
			//this.connector.cleanMap(); //TODO do not clear everything obviously
			this.connector.clearMovement(activeCharacter.getName());
			moveObject(e.getObject(),e.getX(),e.getY());
		}
	}
}
