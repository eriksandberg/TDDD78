package binding_of_erik_game;

/**
 * Created by wassing on 2016-04-04.
 */

import java.util.*;

public class Room {

	//TODO: Fix shooting. Question remains on how to instanciate the shots. Probably spawn as new entities. DONE
	//TODO: Fix trajectory of shots.
	//TODO: Fix transparent squares(tiles).
	//TODO: Fix some game logic. Life, different enemies, etc. What happens when it's gameover? Simple menu would be good.

	// 800 pixels, room 200 squares wide
	private static final int PIXELWIDTH_PER_TILE = 4;
	private static final int PIXELHEIGHT_PER_TILE = 4;
	private static final int FAR_EDGE = 210;
	private static final int ADJ_EDGE = -10;

	private TileType[][] board;
	private int height;
	private int width;

	private Player player = null;
	private Collection<GameObject> miscInRoom = new ArrayList<>();
	private Collection<Enemy> enemiesInRoom = new ArrayList<>();
	private Collection<Shot> shotsInRoom = new ArrayList<>();

	private final Collection<BoardListener> boardListenerArray = new ArrayList<>();

	@SuppressWarnings("SuspiciousGetterSetter")
	public int getPixelWidthPerTile() {
		return PIXELWIDTH_PER_TILE;
	} //used by painter

	@SuppressWarnings("SuspiciousGetterSetter")
	public int getPixelHeightPerTile() {
		return PIXELHEIGHT_PER_TILE;
	}  //maybe change name instead of supressing warning

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@SuppressWarnings("SuspiciousGetterSetter")
	public static int getFarEdge() {
		return FAR_EDGE;
	}

	@SuppressWarnings("SuspiciousGetterSetter")
	public static int getAdjEdge() {
		return ADJ_EDGE;
	}

	/**
	 * Custom constructor for a room. May not be used at all later, depends how we implement.
	 */
	public Room(Player player, int width, int height) {
		this.player = player;

		this.width = width; //800
		this.height = height; //800

		board = new TileType[width][height];
		for (int tileX = 0; tileX < width; tileX++) { //will loop for every "square" and assign a tile to it.
			for (int tileY = 0; tileY < height; tileY++) {
				board[tileX][tileY] = TileType.BLACK; // Space yo
			}
		}

		newRoom();
	}

	// Public because EventHandler must be able to spawn a new room due to testing
	public void newRoom() {
		clearRoom();   // Remove all shots (and enemies) left in an old room
		//noinspection MagicNumber, let the player spawn in the middle of the room
		spawnPlayer(100, 180);
		spawnEnemies();
		// Should probably pause for a couple of seconds, don't want to confuse the player
	}

	// Remove everything in the room but the player
	private void clearRoom() {
		enemiesInRoom.clear();
		shotsInRoom.clear();
		miscInRoom.clear();
	}

	// Basically a restart, public for the same reason as newRoom is
	public void resetRoom() {
		//player.resetSkill();
		player.resetHP();

		clearRoom();

		newRoom();
	}

	// Called to spawn the player at pos x, y when "entering" a new room
	public void spawnPlayer(int x, int y) {
		this.player.xCoord = x;
		this.player.yCoord = y;
		player.setDirection(player.getDirection()); //TODO: Change this later, atm fix to not fuck up heading on room reset
		notifyListeners();
	}

	// Randomize a number of enemies based on the players current skill level
	private void spawnEnemies() {
		Random rand = new Random();
		int i = player.getSkill();

		// Spawn 2 falling enemies if the player have reached skill lvl 4
	    if (i >= 4) {
		    enemiesInRoom.add(spawnEnemy(2, 1));   // These only have 1 power level
		    enemiesInRoom.add(spawnEnemy(2, 1));
			i -= 4;
	    }

		// Spawn normal enemies
		while (i > 0) {
			int e = rand.nextInt(i) + 1;
			enemiesInRoom.add(spawnEnemy(1, e));
			i -= e;
		}
		notifyListeners();
	}

	// Spawn an enemy of kind kind and power level power
	@SuppressWarnings("MagicNumber")    // 40 (in steps of 4) is the max power lvl of an enemy
	private Enemy spawnEnemy(int kind, int power) {
		Enemy newEnemy = GraphicsFactory.getInstance().getEnemy(kind);

		newEnemy.xCoord = enemySpawnPos(kind);

		// Set enemys power
		if (power >  10) {power = 10;}
		newEnemy.setShotCooldown(40 - power*4);

		return newEnemy;
	}

	@SuppressWarnings("ReuseOfLocalVariable")   // I want them all named pos
	private int enemySpawnPos(int kind) {
		Random rand = new Random();
		int pos = 0;

		switch(kind) {
			case 1:
				// add random spawn based on boundaries
				pos = rand.nextInt(FAR_EDGE - 18);
				// Prevent enemies for spawning on top of each other
				while (!spaceXFree(pos)) {
					pos = rand.nextInt(FAR_EDGE - 18); //TODO: magic number n shit
				}
				return pos;
			case 2:
				pos = 10;   // Place first one at left edge
				if (!spaceXFree(pos)) {
					pos = 190;  // Second one at right edge
				}
				return pos;
			default:
				return pos;
		}
	}

	// Return true if a position (x-axis) is free from enemies
	private boolean spaceXFree(int pos) {
		for (Enemy enemy : enemiesInRoom) {
			if ((pos + 10) > enemy.xCoord && pos < (enemy.xCoord + enemy.size)) {
				return false;
			}
		}
		return true;
	}

	// Return true if a position (y-axis) is free from enemies
	private boolean spaceYFree(int pos) {
		for (Enemy enemy : enemiesInRoom) {
			if ((pos + 10) > enemy.yCoord && pos < (enemy.yCoord + enemy.size)) {
				return false;
			}
		}
		return true;
	}

	// Spawns background candy at a random position. Can be either star or galaxy
	private void spawnBackgroundGraphics() {
		Random rand = new Random();

		// We don't want to always spawn stars
		int i = rand.nextInt(24);
		if (i < 4) { //4 out of 24 possible scenarios spawn a star
			final Star newStar = GraphicsFactory.getInstance().getStar();
			newStar.xCoord = rand.nextInt(FAR_EDGE - 18);
			newStar.yCoord = 0;
			miscInRoom.add(newStar);
		} else if (i == 4) { //1 out of 24 possible scenarios spawn a galaxy
			final Galaxy newGalaxy = GraphicsFactory.getInstance().getGalaxy();
			newGalaxy.xCoord = rand.nextInt(FAR_EDGE - 18);
			newGalaxy.yCoord = 0;
			miscInRoom.add(newGalaxy);
	    	}
	}

	// Spawn a couple of sparks at orgin
	@SuppressWarnings("NestedAssignment") // 2 lines is better than 4
	private void spawnSparks(Agent origin, GameObject object) {
		Random rand = new Random();

		// Spawn between 3 and 6 sparks
		int m = rand.nextInt(4) + 3;
		for (int i = 0; i < m; i++) {
			final Spark newSpark = GraphicsFactory.getInstance().getSpark();

			newSpark.xCoordFloat = newSpark.xCoord = origin.xCoord - 4;    // supressed warning
			newSpark.yCoordFloat = newSpark.yCoord = origin.yCoord - 4;    // supressed warning

			// Adding i to the coordinate give the sparks a nice spread
			newSpark.calcAngle(object.xCoord + i, object.yCoord + i);

			miscInRoom.add(newSpark);
		}
		notifyListeners();
	}

	// Spawn a shot from enemy aimed at the player
	@SuppressWarnings("NestedAssignment") // 2 lines is better than 4
	private void spawnShot(Enemy enemy) {
		Shot newShot = GraphicsFactory.getInstance().getLightShot();
		newShot.xCoordFloat = newShot.xCoord = enemy.xCoord - 4;    // supressed warning
		newShot.yCoordFloat = newShot.yCoord = enemy.yCoord - 4;    // supressed warning

		// Calculate the trajectory of the shot
		newShot.calcAngle(player.xCoord, player.yCoord);

		// true = enemy
		newShot.setAlignment(true);

		// Add the shot to the room
		shotsInRoom.add(newShot);
		notifyListeners();
	}

	// Spawn a shot at the players position, traveling in the players direction
	// Public because it's called from EventHandler
	public void fireShot(String shotType) {
		switch(shotType){
			case ("StraightShot"):
				shotsInRoom.add(spawnPlayerShot(player.getDirection()));
				break;
			case ("StrafeShots"):
				char playerFacing = player.getDirection();
			    	if (playerFacing == 'N' || playerFacing == 'S'){
					shotsInRoom.add(spawnPlayerShot('E'));
					shotsInRoom.add(spawnPlayerShot('W'));
				} else {
					shotsInRoom.add(spawnPlayerShot('N'));
					shotsInRoom.add(spawnPlayerShot('S'));
				}
				break;
		    	case ("Big bad bomb"):
				break;
			default: break;
		}

		notifyListeners();
	}

    	private StraightShot spawnPlayerShot(char direction){
	    	StraightShot newShot = GraphicsFactory.getInstance().getPlayerShot();
	    	newShot.xCoord = player.xCoord - 4;
		newShot.yCoord = player.yCoord - 4;
	    	newShot.setAlignment(false); //false != enemy
	    	newShot.setDirection(direction);
	    	return newShot;
	}

	// Move the player and notify listeners
	// Public because it's called from EventHandler
	public void movePlayer(char direction) {
	    	if (direction == 'X'){
		    player.move(direction); //actually does not move
		}
		player.rotate(direction, player.getDirection());
		player.move(direction);
		notifyListeners();
	}


	// Public because it's called GameFrame
	public void tick() {
		// Always called by the clock, handles enemies, shots and some game mechanics
		if (player.isDead()) {
			gameOver();
		} else if (!enemiesInRoom.isEmpty()) {
			// Handle enemies (if there are enemies, otherwise spawn a new room)
			Iterator<Enemy> e = enemiesInRoom.iterator();
			while (e.hasNext()) {
				Enemy enemy = e.next();
				if (enemy.isDead()) {
					e.remove();
				} else {
					enemy.move();
					// touching enemies hurt the player
					if (enemy.collision(player)) {
						spawnSparks(player, enemy);
						player.hp--;
					}
					if (enemy.readyToShoot()) {
						spawnShot(enemy);
					}
				}
			}
			// Handle shots
			Iterator<Shot> s = shotsInRoom.iterator();
			while (s.hasNext()) {
				Shot shot = s.next();
				// move() return true if the shot is moving out of the map
				if (shot.move()) {
					s.remove();
				}
				// Remove enemy shots that hit the player
				if (shot.isEnemy()) {
					if (shot.collision(player)) {
						spawnSparks(player, shot);
						s.remove();
						player.hp--;
					}
				} else {
					// Remove player shots that hit an enemy
					e = enemiesInRoom.iterator();
					while (e.hasNext()) {
						Enemy enemy = e.next();
						if (shot.collision(enemy)) {
							spawnSparks(enemy, shot);
							s.remove();
							enemy.hp--;
						}
					}
				}
			}
			// Handle graphical stuff
			Iterator<GameObject> misc = miscInRoom.iterator();
			while (misc.hasNext()) {
				GameObject gfx = misc.next();
				if (gfx.move()) {
					misc.remove();
				}
			}
			// Perhaps spawn a star
			spawnBackgroundGraphics();
		} else {
			// Room is empty, increment player skill and spawn a new room
			player.incSkill();
			newRoom();
		}
		notifyListeners();
	}

	private void gameOver() {
		//player = null; // Remove player?
		enemiesInRoom.clear();
		shotsInRoom.clear();
		System.out.println("binding_of_erik_game Over!");
	}

	/**
	 * This function is used by the paint component to find all objects on each specific square on the board.
	 * It first checks the player, then enemies, shots and other objects
	 */
	public TileType getSquare(int x, int y) {
		TileType square;

		// Get player
		if (player != null) {
			if (player.getTile(x, y) != null) {
				return player.getTile(x, y);
			}
		}
		// Get enemies
		for (Enemy enemy : enemiesInRoom) {
			if (enemy.getTile(x, y) != null) {
				return enemy.getTile(x, y);
			}
		}
		// Get shots
		for (Shot shot : shotsInRoom) {
			if (shot.getTile(x, y) != null) {
				return shot.getTile(x, y);
			}
		}
		// Get Sparks
		for (GameObject spark : miscInRoom) {
			if (spark.getTile(x, y) != null) {
				return spark.getTile(x, y);
			}
		}

		square = board[x][y];
		return square;
	}

	private void notifyListeners() {
		for (BoardListener boardListener : boardListenerArray) {
			boardListener.boardChanged();
		}
	}

	// Public because it's called from EventHandler
	public void addBoardListener(BoardListener bl) {
		boardListenerArray.add(bl);
	}
}
