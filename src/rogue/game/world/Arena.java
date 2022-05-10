package rogue.game.world;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import rogue.framework.eventhandling.Connector;
import rogue.framework.eventhandling.Event;
import rogue.framework.resources.Property;
import rogue.framework.resources.Resources;
import rogue.game.combat.CombatManager;
import rogue.game.combat.skills.Skill;
import rogue.game.combat.skills.Skill.Effect;
import rogue.game.combat.skills.Skill.Effect.EffectType;
import rogue.game.combat.skills.Skill.SkillType;
import rogue.game.combat.skills.Skill.TargetType;
import rogue.game.combat.skills.SkillLibrary;
import rogue.game.npc.NPCLibrary;
import rogue.game.pvp.Team;
import rogue.game.pvp.individualcharacters.Gimli;
import rogue.game.world.generation.RoomData;
import rogue.game.world.objects.BattleLog;
import rogue.game.world.objects.ObjectLibrary;
import rogue.game.world.objects.entities.Entity;
import rogue.game.world.objects.entities.NPC;
import rogue.game.world.objects.entities.PlayableCharacter;
import rogue.game.world.objects.tiles.Enhancement;
import rogue.game.world.objects.tiles.Enhancement.Level;
import rogue.game.world.objects.tiles.Tile;
import rogue.graphics.BaseActionContainer;
import rogue.graphics.EntityInformationContainer;
import util.CharacterCard;
import util.Highlight;
import util.MovementOption;
import util.MyColor;
import util.calc.path.AStarPathfinder;

public class Arena {
	
	private RoomData data;
	private Connector connector;
	private Point[][] teamPlacements=new Point[][] {
		null,
		new Point[] {new Point(10,6),new Point(11,6),new Point(9,6),new Point(8,6),new Point(7,6),new Point(12,6)}, 
		new Point[] {new Point(10,10),new Point(11,10),new Point(9,10),new Point(8,10),new Point(7,10),new Point(12,10)}
	};
	protected int xOffset = 0;
	protected int yOffset = 0;
	private int width=0,height=0;
	
	//new and improved
	private Tile[][] tiles;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> theDead = new ArrayList<Entity>();
	private Highlight[][] highlights;
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
		this.visionField = new boolean[this.width][this.height];
		largeCanvas = new EntityInformationContainer(new Entity(), EntityInformationContainer.PLAYER_CONFIG, Resources.textEditorConfig, connector);
		smallCanvas = new EntityInformationContainer(new Entity(), EntityInformationContainer.ENTITY_CONFIG, Resources.textEditorConfig, connector);
		this.highlights = new Highlight[data.getTileData().length][data.getTileData()[0].length];
		this.buttonPanel = new BaseActionContainer(connector);
		this.sprites = new int[data.getTileData().length][data.getTileData()[0].length];
		this.log = new BattleLog(Resources.textEditorConfig,this.connector);
		this.tiles =data.getTileData();
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
				setSelectPlayerEvent(pc, pc.getX(), pc.getY(), pc.getX(), pc.getY());
				this.activeLarge=pc;
			}
			if(e.getTeam()!=nr/* && PlayableCharacter.class.isInstance(e)*/) {
				Event event = new Event();
				event.setEntity(e);
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
		map=renderEnhancements(map);
		map=renderEntities(map);
		map=renderHighlights(map);
		compartments.add(map);
		
		int[] largeCanvasPixels = new int[Property.ACTIVE_CHAR_HEIGHT*Property.ACTIVE_CHAR_WIDTH];
		largeCanvasPixels = this.largeCanvas.render();
		compartments.add(largeCanvasPixels);
		
		int[] smallCanvasPixels = new int[Property.ACTIVE_NPC_HEIGHT*Property.ACTIVE_NPC_WIDTH];
		smallCanvasPixels = this.smallCanvas.getPixels();
		compartments.add(smallCanvasPixels);
		
		int[] minimap = new int[Property.MINIMAP_HEIGHT*Property.MINIMAP_WIDTH];
		minimap = getMinimap(minimap);
		//compartments.add(minimap);
		compartments.add(null);
		
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
				
				int[] graphics = Resources.TEXTURES.get(getBaseTextureOf(t.getId()));
				
				if(this.visionField[tileX+xOffset][tileY+yOffset]) {
					p[x + y*Property.ROOM_SIZE] = graphics[xInTile+yInTile*Property.TILE_SIZE];
				}else {
					Color color = new Color(graphics[xInTile+yInTile*Property.TILE_SIZE]);
					Color darker = darken(color,0.2);
					p[x + y*Property.ROOM_SIZE] = darker.getRGB();
				}
				
			}
		}
		return p;
	}
	private int getBaseTextureOf(int id) {
		if(id==Resources.TREE || id==Resources.TALLGRASS) {return Resources.MEADOW;}
		return id;
	}
    private Color darken(final Color color, final double percentage) {
        if (percentage < 0.01 || percentage > 1.00) { throw new IllegalArgumentException("Percentage must be between [0.01 - 1.00]"); }
        
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
				if(e.getTeam()!=this.activeTeam && tarned(e)) {
					continue;
				}
				for(int y = 0; y < Property.TILE_SIZE; y++) {
					for(int x = 0; x < Property.TILE_SIZE; x++) {
						int relX = ((e.getX()+(-1)*xOffset)*Property.TILE_SIZE)+x;
						int relY = ((e.getY()+(-1)*yOffset)*Property.TILE_SIZE)+y;
						int id = e.getAppearance()!=null?e.getAppearance():e.getId();
						int color = Resources.CHARACTERS.get(id)[x+y*Property.TILE_SIZE];
						if(color!=-12450784 && color!=-3947581) {
							p[relX+relY*Property.ROOM_SIZE] = color;	
						}
					}
				}
			}
		}
		return p;
	}
	private int[] renderEnhancements(int[] p) {
		for(int x = 0; x < this.tiles.length;x++) {
			for(int y =0; y < this.tiles[0].length; y++) {
		
				Tile t = this.tiles[y][x];
				for(Enhancement e : t.getEnhancements()) {
					if(!e.isVisTeam()) {
						continue;
					}
					if(!e.isVisEnemy()&&e.getTeam()!=this.activeTeam) {
						continue;
					}
					for(int ty = 0; ty < Property.TILE_SIZE; ty++) {
						for(int tx = 0; tx < Property.TILE_SIZE; tx++) {
							int relX = (x*Property.TILE_SIZE)+tx;
							int relY = (y*Property.TILE_SIZE)+ty;
							Color color = new Color(Resources.TEXTURES.get(e.getId())[tx+ty*Property.TILE_SIZE]);

							if(color.getRGB()!=-12450784 && color.getRGB()!=-3947581) {
								if(!this.visionField[x][y]) {
									color=darken(color,0.2);
								}
								p[relX+relY*Property.ROOM_SIZE] = color.getRGB();	
							}
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
					highlights[x][y].printHighlight(p, x, y);
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
				if(e.hasAbility(SkillLibrary.TRUE_VISION)) {
					trueVision(e.getX(),e.getY(),e.getVisionDistance());}
				else {
					getVision(e.getX(),e.getY(),e.getVisionDistance());}
			}
		}
		print();
	}
	private void trueVision(int x, int y, int vis) {
		for(int i = x-vis; i <= x+vis; i++) {
			for(int j = y-vis; j <= y+vis; j++) {
				if(i>=0&&i<this.width  &&  j<this.height&&j>=0) {
					this.visionField[i][j]=true;
				}
			}
		}
	}
	private void getVision(int x, int y, int vis) {
		int[][] visionValues = getVisionValues();
		paint(visionValues);
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
		if(startx>=Property.ROOM_VIEW_TILE_COUNT||starty>=Property.ROOM_VIEW_TILE_COUNT)
			return;
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
			if(startx<Property.ROOM_VIEW_TILE_COUNT&&starty<Property.ROOM_VIEW_TILE_COUNT)
				checkVisionOf(x, y, startx, starty, visF, free, cx, cy);
		}
	}
	private int[][] getVisionValues(){
		int[][] visionValues = new int[this.width][this.height];
		for(int x = 0; x < visionValues.length; x++) {
			for(int y = 0; y < visionValues[0].length; y++) {
				if(data.getTileData()[y][x].isVisBlock()) {
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
		if(s.getTarget().equals(TargetType.ALL_ENEMY)) {
			for(Entity e : this.entities) {
				if(e.getTeam()!=this.activeTeam) {
					this.visionField[e.getX()][e.getY()] = true;
				}
			}
		}
		
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
	private boolean tarned(Entity e) {
		if(e.hasAbility(SkillLibrary.TARNING)) {
			for(Entity ent : this.entities.stream().filter(i->i.getTeam()==this.activeTeam).collect(Collectors.toList())) {
				if(proxCheck(e.getX(), e.getY(), ent.getX(), ent.getY())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------object-handling---------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	private void moveObject(Entity o, int x, int y,boolean manual, boolean tp) {
		int currentX = o.getX();
		int currentY = o.getY();
		this.tiles[currentX][currentY].onLeave(o);
		o.setX(x);
		o.setY(y);
		int distance = calcDistance(currentX,currentY,x,y);
		
		for(Entity e : this.entities) {
			if(e.getX()==currentX && e.getY()==currentY) {
				e.setX(x);
				e.setY(y);
			}
		}
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
		this.connector.removeContext(getRelationalX(currentX),getRelationalY(currentY),Property.TILE_SIZE,Property.TILE_SIZE);
		if(o.getTeam()!=this.activeTeam) {
			Event event = new Event();
			event.setEntity(o);
			event.setEventId(Connector.INFO_OBJECT);
			event.setX(x);
			event.setY(y);
			this.connector.addContext(getRelationalX(x),getRelationalY(y),Property.TILE_SIZE,Property.TILE_SIZE,event);
		}
		this.tiles[y][x].onEnter(o);
	}
	private void manageRelocations() {
		for(Entity e : this.entities) {
			Effect effect = e.getRelocation();
			if(effect!=null) {
				if(effect.getType().equals(EffectType.OBJECT_PULL)) {
					Point a = getNextPointTowards(new Point(e.getX(),e.getY()),new Point(this.activeLarge.getX(),this.activeLarge.getY()));
					if(getMovementViabilityFor(a.x, a.y, 1,e.isFlying()).equals(MovementOption.VALID)) {
						moveObject(e, a.x, a.y, false, false);
					}
				}else {
					Point a = getNextPointFrom(new Point(e.getX(),e.getY()),new Point(this.activeLarge.getX(),this.activeLarge.getY()));
					if(getMovementViabilityFor(a.x, a.y, 1,e.isFlying()).equals(MovementOption.VALID)) {
						moveObject(e, a.x, a.y, false, false);
					}
				}
				e.removeRelocations();
			}
			
		}
	}
	private void onMoveCharacter(int x, int y,boolean isTp) {
		int currentX = this.activeLarge.getX();
		int currentY = this.activeLarge.getY();
		this.tiles[currentY][currentX].onLeave(this.activeLarge);
		
		this.activeLarge.setX(x);
		this.activeLarge.setY(y);
		
		int dist = calcDistance(currentX, currentY, x, y);
		if(!isTp && !isFreeMovement(this.activeLarge))
			if(isFree(this.activeLarge,x,y)) {
				this.activeLarge.useMovement(0);
			}else {
				this.activeLarge.useMovement(dist);
			}
		removeMovements();
		highLightActive();
		setSelectPlayerEvent(PlayableCharacter.class.cast(this.activeLarge), x, y,currentX,currentY);
		this.tiles[y][x].onEnter(this.activeLarge);
		refreshVision();
	}
	private boolean isFree(Entity e, int x, int y) {
		if(e.hasAbility(SkillLibrary.WOOD_WALK)) {
			if(this.tiles[y][x].hasEnhancement(Resources.TALLGRASS)) {
				return true;
			}
		}
		return false;
	}
	private void removeTheDead() {
		List<Entity> operatedList = new ArrayList<>();
		this.entities.stream()
			.filter(e->e.getCurrentLife()<1)
			.forEach(e -> {
				this.connector.removeContextOf(e.getName());
				operatedList.add(e);
				this.log.formulateDeath(e.getName());
				});
		for(Entity e:operatedList) {
			deathEffect(e);
		}
		this.entities.removeAll(operatedList);
	}
	private void deathEffect(Entity e) {
		for(Skill s : e.getSkills()) {
			if(s.isOnDeath()) {
				executeOnDeathSkill(e, s);
			}
		}
	}
	private void showMovementOptions() {
		int xGridPos = this.activeLarge.getX();
		int yGridPos = this.activeLarge.getY();
		int movementDistance = Math.min(this.activeLarge.getCurrentMovement(),1);
		if(movementDistance>0) {
			for(int x = xGridPos-movementDistance; x <= xGridPos+movementDistance; x++) {
				for(int y = yGridPos-movementDistance; y <= yGridPos+movementDistance;y++) {
					if(!(x==xGridPos&&y==yGridPos) && x < Property.ROOM_VIEW_TILE_COUNT && y < Property.ROOM_VIEW_TILE_COUNT) {
						
						MovementOption move = getMovementViabilityFor(x,y,this.activeLarge.getTeam(),this.activeLarge.isFlying());
						
						if(move.equals(MovementOption.VALID) || (this.activeLarge.hasAbility(SkillLibrary.ALL_TERRAIN) && move.equals(MovementOption.WATER))) {
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
					MovementOption move = getMovementViabilityFor(x,y,this.activeLarge.getTeam(),this.activeLarge.isFlying());
					if(move.equals(MovementOption.ENEMY)) {
						highlightTile(x,y,Highlight.CMBT_RED);
						Event e = new Event();
						e.setX(x);
						e.setY(y);
						e.setEntity(getEntityAt(x,y));
						e.setEventId(Connector.ATTACK);
						this.connector.addEvent(getRelationalX(x), getRelationalY(y), Property.TILE_SIZE, Property.TILE_SIZE, e);					
					}
				}
			}
		}
	}
	private void basicAttack(Event e) {
		if(this.activeLarge.useAction(1)) {
			CombatManager.normalMelee(this.activeLarge,e.getEntity(),this.log);
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
	private void summonNpc(Skill s, List<Point> points, int team) {
		if(points==null) {
			points = new ArrayList<>();
			for(int x = 0; x < highlights.length; x++) {
				for(int y = 0; y < highlights[0].length; y++) {
					if(highlights[x][y]!=null &&
							(highlights[x][y].equals(Highlight.SKLL_GREEN)||
									highlights[x][y].equals(Highlight.SKILL_SELECT))) {
						if(getMovementViabilityFor(x, y, this.activeTeam,false).equals(MovementOption.VALID)) {
							points.add(new Point(x,y));
						}	
					}
				}
			}
		}
		for(Point p : points) {
			NPC npc = NPCLibrary.getNpc(s.getSummonedId());
			npc.setX(p.x);npc.setY(p.y);npc.setTeam(team);
			this.entities.add(npc);
			Event event = new Event();
			event.setEntity(npc);
			event.setEventId(Connector.INFO_OBJECT);
			event.setX(p.x);
			event.setY(p.y);
			this.connector.addContext(getRelationalX(p.x), getRelationalY(p.y), Property.TILE_SIZE, Property.TILE_SIZE, event);
		
		}
		refreshVision();
	}
	private void summonNpc(Skill s) {
		summonNpc(s,null,this.activeTeam);
	}
	private void summon(Skill s) {
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null &&
						(highlights[x][y].equals(Highlight.SKLL_GREEN)||
								highlights[x][y].equals(Highlight.SKILL_SELECT))) {
					Enhancement enh = ObjectLibrary.getEnhancement(s.getEnhancementId());
					enh.setTeam(activeTeam);
//					if(enh==null){
//						this.tiles[y][x] = new Tile(s.getEnhancementId());
//					}else 
						if(this.tiles[y][x].hasTop() && enh.getLevel().equals(Level.TOP)) {
						continue;
					}else {
						this.tiles[y][x].addEnhancement(enh);
					}
				}
			}
		}
		refreshVision();
	}
	//---------------------------------------------------------------------------------------------------------------------------------------//
	//---------------------------skill-handling----------------------------------------------------------------------------------------------//
	//---------------------------------------------------------------------------------------------------------------------------------------//
	private void onSkillChosen(Skill s) {
		clear();
		int currentX = this.activeLarge.getX();
		int currentY = this.activeLarge.getY();
		
		int skillRange = s.getDistance();
		if(s.getTarget()!=null && (s.getTarget().equals(TargetType.SURROUNDING)||s.getTarget().equals(TargetType.SELF)||s.getTarget().equals(TargetType.ALL_ENEMY))) {
			onTargetChosen(s,currentX,currentY);
			return;
		}
		for(int x = currentX-skillRange; x <= currentX+skillRange; x++) {
			for(int y = currentY-skillRange; y <= currentY+skillRange;y++) {
				if(x>=0 && y>=0 && x<16 && y<16) {
					if(s.getTarget().equals(TargetType.SINGLE_FREE) && 
							!getMovementViabilityFor(x, y, this.activeTeam,this.activeLarge.isFlying()).equals(MovementOption.VALID)) {
						continue;
					}
					if(checkHexproof(x,y)) {
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
	private boolean checkHexproof(int x, int y) {
		Entity e = getEntityAt(x, y);
		if(e==null) {return false;}
		if(e.hasAbility(SkillLibrary.HEXPROOF)) {
			return true;
		}
		return false;
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
		case LINE_PIERCING:
			markSkillLinePiercing(new Point(this.activeLarge.getX(),this.activeLarge.getY()),new Point(targetX,targetY));
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
		case ALL_ENEMY:
			markEnemies();
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
	private void markEnemies() {
		for(Entity e : this.entities) {
			if(e.getTeam()!=this.activeTeam && isVisible(e)) {
				highlightTile(e.getX(), e.getY(), Highlight.SKLL_GREEN);	
			}
		}
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
		a = getNextPointTowards(a, b);
		highlightTile(a.x, a.y, Highlight.SKLL_GREEN);
		
		if(getEntityAt(a.x, a.y)!=null) {
			return;
		}
		if(!a.equals(b)) {
			markSkillLine(a,b);
		}
	}
	private void markSkillLinePiercing(Point a, Point b) {
		a = getNextPointTowards(a, b);
		
		highlightTile(a.x, a.y, Highlight.SKLL_GREEN);
		
		if(!a.equals(b)) {
			markSkillLinePiercing(a,b);
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
	private void executeOnDeathSkill(Entity e, Skill s) {
		s = SkillLibrary.get(s.getId());
		List<Point> points = getOnDeathPoints(e,s);
		CombatManager.executeSkill(e, getEntitiesFromPoints(points),getTilesFromPoints(points), s, this.log);
		update();
		if(s.getSummonedId()!=0) {
			summonNpc(s,points,e.getTeam());
		}
	}
	private void onExecuteSkill(int skillId) {
		Skill s = SkillLibrary.get(skillId);
		boolean success = CombatManager.executeSkill(this.activeLarge,getAffectedTargets(s),getTilesFromHighight(),s,this.log);
		update();
		if(success) {

			if(s.getType()!=null && (s.getType().equals(SkillType.MOVEMENT) || 
					s.hasTP())){
				Point p = getSkillSelect();
				if(p!=null)
					onMoveCharacter(p.x, p.y, true);
				highLightActive();
			}
			if(s.getType()!=null && s.getType().equals(SkillType.VISION)) {
				vision(s); 
			}
			if(s.getSummonedId()!=0) {
				summonNpc(s);
			}
			if(s.getEnhancementId()!=0) {
				summon(s);
			}
			manageRelocations();
		}
//		addSprites(e.getSkill());
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
	private List<Entity> getAffectedTargets(Skill s) {
		if(s.getTarget().equals(TargetType.ALL_ENEMY)) {
			return this.entities.stream().filter(e->e.getTeam()!=this.activeTeam).collect(Collectors.toList());
		}
		List<Entity> obj=new ArrayList<>();
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null && (highlights[x][y].equals(Highlight.SKLL_GREEN)||highlights[x][y].equals(Highlight.SKILL_SELECT))) {
					if(getEntityAt(x, y)!=null) {
						obj.add(getEntityAt(x, y));
					}
				}
			}
		}
		return obj;
	}
	private List<Point> getOnDeathPoints(Entity e, Skill s){
		int x = e.getX();
		int y = e.getY();
		int radius = s.getRadius();
		List<Point> targets = new ArrayList<>();
		for(int i = x - radius; i <= x+radius; i++) {
			for(int j = y -radius; j <= y+radius; j++) {
				targets.add(new Point(i,j));
			}
		}
		return targets;
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
	private List<Entity> getEntitiesFromPoints(List<Point> points) {
		List<Entity> results = new ArrayList<>();
		for(Point p : points) {
			results.add(getEntityAt(p.x, p.y));
		}
		return results;
	}
	private List<Tile> getTilesFromHighight(){
		List<Tile> tiles = new ArrayList<>();
		for(int x = 0; x < highlights.length; x++) {
			for(int y = 0; y < highlights[0].length; y++) {
				if(highlights[x][y]!=null &&
						(highlights[x][y].equals(Highlight.SKLL_GREEN)||
								highlights[x][y].equals(Highlight.SKILL_SELECT))) {
					tiles.add(this.tiles[y][x]);
				}
			}
		}
		return tiles;
	}
	private List<Tile> getTilesFromPoints(List<Point> points){
		List<Tile> tiles = new ArrayList<>();
		for(Point p: points) {
			tiles.add(this.tiles[p.x][p.y]);
		}
		return tiles;
	}
 	private void setSelectPlayerEvent(PlayableCharacter pc, int x, int y,int oldx, int oldy) {
		Event selectPlayerEvent = new Event();
		selectPlayerEvent.setEventId(Connector.SELECT_PLAYER);
		selectPlayerEvent.setCharacter(pc);
		this.connector.removeEvent(getRelationalX(oldx),getRelationalY(oldy),Property.TILE_SIZE,Property.TILE_SIZE);
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
	private MovementOption getMovementViabilityFor(int x, int y, int teamNr,boolean flying) {
		if(x < Property.ROOM_VIEW_TILE_COUNT && y < Property.ROOM_VIEW_TILE_COUNT) {
			if(data.getTileData()[y][x].getId()==Resources.VOID) {
				return MovementOption.VOID;
			}
			if(data.getTileData()[y][x].getId()==Resources.ENDWALL) {
				return MovementOption.ENDWALL;
			}
			if(data.getTileData()[y][x].isObstacle()) {
				if(flying) {
					return MovementOption.VALID;
				}
				return MovementOption.OBSTACLE;
			}
			if(data.getTileData()[y][x].getId()==Resources.WATER) {
				if(flying) {
					return MovementOption.VALID;
				}
				return MovementOption.WATER;
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
		return null;
	}
	private boolean isFreeMovement(Entity c) {
		Tile t = this.data.getTileData()[c.getY()][c.getX()];
		if(t.getId()==(Resources.TALLGRASS) && c.isWoodWalk()) {
			return true;
		}
		return false;
	}
	private void highlightTile(int x, int y,Highlight h) {
		if(x<0||y<0||x>=Property.ROOM_VIEW_TILE_COUNT || y>=Property.ROOM_VIEW_TILE_COUNT) {
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
		turnsForNPC();
		for(Entity e: this.entities) {
			if(e.getTeam()==this.activeTeam) {
				e.endOfTurn(this.log);
			}
		}
		for(int x = 0; x < Property.ROOM_VIEW_TILE_COUNT; x++) {
			for(int y = 0; y < Property.ROOM_VIEW_TILE_COUNT; y++) {
				this.tiles[x][y].onEnd(this.getEntityAt(x, y));
				this.tiles[x][y].turn();
			}
		}
		removeTheDead();
	}
	private void checkWin() {
		List<Integer> remainingTeams = new ArrayList<>();
		for(Entity e : this.entities) {
			if(remainingTeams.contains(e.getTeam())) {
				continue;
			}
			remainingTeams.add(e.getTeam());
		}
		if(remainingTeams.size()<2) {
			Event teamWonRound = new Event();
			teamWonRound.setEventId(Connector.TEAM_WON_ROUND);
			teamWonRound.setTeamNr(remainingTeams.get(0));
			this.connector.fire(teamWonRound);
		}
	}

	private void turnsForNPC() {
		for(Entity e: this.entities) {
			if(NPC.class.isInstance(e)&&e.getTeam()==this.activeTeam) {
				NPC npc = NPC.class.cast(e);
				boolean[][] mvmntMap = getMvmntMapFor(e.getTeam(), e.getX(), e.getY(),npc.isFlying());
				Entity closestEnemy = getClosestEnemy(mvmntMap,npc.getX(),npc.getY());
				if(closestEnemy!=null) {
					if(proxCheck(npc.getX(), npc.getY(), closestEnemy.getX(), closestEnemy.getY())) {
						CombatManager.normalMelee(npc, closestEnemy, this.log);
					}else {
						Point step = AStarPathfinder.calcPath(mvmntMap, npc.getX(), npc.getY(), closestEnemy.getX(), closestEnemy.getY());
						moveObject(npc, step.x, step.y, false, false);
					}
				}
			}
		}
	}

	private Entity getClosestEnemy(boolean[][] mvmntMap,int npcx, int npcy) {
		List<Entity> enemies = this.entities.stream()
				.filter(e->e.getTeam()!=this.activeTeam)
				.filter(e->isVisible(e))
				.collect(Collectors.toList());
		Entity closest = null;
		int dist = 0;
		for(Entity e: enemies) {
			int temp = AStarPathfinder.getDist(mvmntMap, npcx, npcy, e.getX(), e.getY());
			if(dist==0||temp<dist){
				dist = temp;
				closest = e;
			};
		}
		return closest;
	} 
	private boolean isVisible(Entity e) {
		return this.visionField[e.getX()][e.getY()];
	}
	private boolean[][] getMvmntMapFor(int team, int selfx,int selfy,boolean isFlying){
		boolean[][] mvmntMap = new boolean[data.getTileData().length][data.getTileData()[0].length];
		for(int x = 0; x < this.data.getTileData().length; x++) {
			for(int y = 0; y < data.getTileData()[0].length; y++) {
				MovementOption m = getMovementViabilityFor(x, y,team,isFlying);
				if(x==selfx && y==selfy) {
					mvmntMap[x][y]=true;
				}else if(m==MovementOption.VALID||m==MovementOption.ENEMY) {
					mvmntMap[x][y]=true;
				}else {
					mvmntMap[x][y]=false;
				}
			}
		}
		return mvmntMap;
	}
	private int calcDistance(int cx, int cy, int x, int y) {
		return Math.max((Math.abs(cx-x)),(Math.abs(cy-y)));
	}
	private void clear() {
		removeMovements();
		this.connector.cleanMapEvents();
		setSelectPlayerEvent(PlayableCharacter.class.cast(this.activeLarge),this.activeLarge.getX(),this.activeLarge.getY(),this.activeLarge.getX(),this.activeLarge.getY());
	}
	private void endTurn() {
		endForTeam();
		//endForEnhancements();
		removeMovements();
		removeSelectPlayer();
		checkWin();
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
				setSelectPlayerEvent(pc, pc.getX(), pc.getY(), pc.getX(), pc.getY());
			}
		}
	}
	private Point getNextPointFrom(Point a,Point b) {
		int dx = a.x - b.x;
		int dy = a.y - b.y;
		int xs = dx>0? -1:1;
		int ys = dy>0? -1:1;
		double m = dx==0?0:(double)dy/dx;
		
		if(dx==0||m<=-2||m>=2) {
			a.y-=ys;
		}else if(m>=-0.5&&m<=0.5) {
			a.x-=xs;
		}else {
			a.x-=xs;
			a.y-=ys;
		}
		return a;
	}
	private Point getNextPointTowards(Point a,Point b) {
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
		return a;
	}

	private void paint(int[][] p) {
		for(int y = 0; y < p[0].length; y++) {
			for(int x= 0; x < p.length; x++) {
				System.out.print(p[x][y]+" ");
			}
			System.out.println();
		}
		System.out.println();System.out.println();
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
			onSelectSmallEntity(Entity.class.cast(e.getEntity()));
		}else if(e.getEventId().contains(	Connector.TAB_CHANGE)) {
			onTabChange(e);
		}else if(e.getEventId().equals(		Connector.SKILL_CHOSEN)) {
			onSkillChosen(SkillLibrary.get(e.getSkill()));
		}else if(e.getEventId().startsWith(	Connector.TARGET_CHOSEN)) {
			onTargetChosen(SkillLibrary.get(e.getSkill()),e.getX(),e.getY());
		}else if(e.getEventId().equals(		Connector.CONFIRM_SKILL)) {
			onExecuteSkill(e.getSkill());
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
