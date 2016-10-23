package binding_of_erik_game;

import java.io.IOException;
import java.util.*;

/**
 * Created by wassing on 2016-04-04.
 * Room keep track of everything currently happening on screen or in the currecnt "room".
 * Also keep the Player, stats, game over etc.
 */

public class Room {

	/**
	 * Size of tiles and board
	 */
	private static final int PIXELWIDTH_PER_TILE = 4;
	private static final int PIXELHEIGHT_PER_TILE = 4;
	private static final int FAR_EDGE = 210;
	private static final int FAR_EDGE_SPAWN = 190;
	private static final int ADJ_EDGE = -10;

	private TileType[][] board;
	private int height;
	private int width;

	private Player player = null;
	protected Score score = null;

	// Used when an area is cleared and the player/ship travel to the top
	private boolean roomOver = false;

	/**
	 * Constants for game over
	 */
	private static final String DIED = "You died!";
	private static final String WON = "You won!";
	private static final int MAX_LEVEL = 10;

	/**
	 * Lists of all objects in room
	 */
	private Collection<GameObject> miscInRoom = new ArrayList<>();
	private Collection<Enemy> enemiesInRoom = new ArrayList<>();
	private Collection<Shot> shotsInRoom = new ArrayList<>();

	private final Collection<BoardListener> boardListenerArray = new ArrayList<>();

	/**
	 * Additional constants
	 **/
	private static final int STAR_SPAWN_RATE = 24;
	private static final int ENEMY_DEFAULT_COOLDOWN = 40;

	@SuppressWarnings("SuspiciousGetterSetter")
	public int getPixelWidthPerTile() {
		return PIXELWIDTH_PER_TILE;
	} //used by painter

	@SuppressWarnings("SuspiciousGetterSetter")
	public int getPixelHeightPerTile() {
		return PIXELHEIGHT_PER_TILE;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static int getFarEdge() {
		return FAR_EDGE;
	}

	public static int getFarEdgeSpawn() {
		return FAR_EDGE_SPAWN;
	}

	public static int getAdjEdge() {
		return ADJ_EDGE;
	}

	/**
	 * Custom constructor for a room.
	 */
	public Room(Player player, int width, int height) {
		this.player = player;
		try {
			score = new Score();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = width; //200
		this.height = height; //200

		board = new TileType[width][height];
		for (int tileX = 0; tileX < this.width; tileX++) { //will loop for every "square" and assign a tile to it.
			for (int tileY = 0; tileY < this.height; tileY++) {
				board[tileX][tileY] = TileType.BLACK; // Space yo
			}
		}
		newRoom();
	}

	// Public because EventHandler must be able to spawn a new room due to testing
	public void newRoom() {
		clearRoom();   // Remove all shots (and enemies) left in an old room
		spawnPlayer(player.xCoord, FAR_EDGE_SPAWN);
		spawnEnemies();
	}

	// Remove everything in the room but the player and background graphics
	private void clearRoom() {
		enemiesInRoom.clear();
		shotsInRoom.clear();
	}

	// Basically a restart, public for the same reason as newRoom is
	public void resetRoom() {
		player.resetHP();

		clearRoom();

		newRoom();
	}

	// Called to spawn the player at pos x, y when "entering" a new room
	public void spawnPlayer(int x, int y) {
		this.player.xCoord = x;
		this.player.yCoord = y;
		notifyListeners();
	}

	// Randomize a number of enemies based on the players current skill level
	private void spawnEnemies() {
		Random rand = new Random();
		int i = player.getSkill();

		// Spawn a boss every 5th level
		if (i % 5 == 0) {
			enemiesInRoom.add(spawnEnemy(4, 8)); //the big bad baus, has increased power.
		} else if (i >= 4) {
			//spawn 2 falling enemies if the player has reached skill lvl 4
			enemiesInRoom.add(spawnEnemy(2, 1)); // These only have 1 power level
			enemiesInRoom.add(spawnEnemy(2, 1));
		}

		// Spawn normal enemies as long as no boss is in the room.
		while (i > 0 && (i % 5 != 0)) {
			int e = rand.nextInt(i) + 1;
			enemiesInRoom.add(spawnEnemy(1, e));
			i -= e;
		}
		notifyListeners();
	}

	// Spawn an enemy of kind kind and power level power
	private Enemy spawnEnemy(int kind, int power) {
		Enemy newEnemy = GraphicsFactory.getInstance().getEnemy(kind);
		newEnemy.xCoord = enemySpawnPos(kind, newEnemy.getSize());
		if (kind == 4) {
			//we have a boss
			newEnemy.yCoord = newEnemy.getSize();
		}

		// Set enemys power
		if (power > 10) {
			power = 10;
		}
		newEnemy.setShotCooldown(ENEMY_DEFAULT_COOLDOWN - power * 4 - 1);   // We don't want enemies with cooldown = 0

		newEnemy.rotateThisMany(1);
		return newEnemy;
	}

	@SuppressWarnings("ReuseOfLocalVariable")   // I want them all named pos
	private int enemySpawnPos(int kind, int size) {
		Random rand = new Random();
		int pos = 0;
		switch (kind) {
			case 1:
				// add random spawn based on boundaries
				pos = rand.nextInt(FAR_EDGE_SPAWN);
				// Prevent enemies for spawning on top of each other
				while (!spaceXFree(pos)) {
					pos = rand.nextInt(FAR_EDGE_SPAWN);
				}
				return pos;
			case 2:
				pos = 0;   // Place first one at left edge
				if (!spaceXFree(pos)) {
					pos = FAR_EDGE_SPAWN;  // Second one at right edge
				}
				return pos;
			case 4:
				pos = (100 + size / 2); //always spawn boss in the middle, divided by
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

	// Return true if a position (y-axis) is free from enemies, we don't use this currently.
	/*private boolean spaceYFree(int pos) {
		for (Enemy enemy : enemiesInRoom) {
			if ((pos + 10) > enemy.yCoord && pos < (enemy.yCoord + enemy.size)) {
				return false;
			}
		}
		return true;
	}*/

	// Spawns background candy at a random position. Can be either star or galaxy
	private void spawnBackgroundGraphics() {
		Random rand = new Random();

		// We don't want to always spawn stars
		int i = rand.nextInt(STAR_SPAWN_RATE);
		if (i < 4) { //4 out of 24 possible scenarios spawn a star
			final Star newStar = GraphicsFactory.getInstance().getStar();
			newStar.xCoord = rand.nextInt(FAR_EDGE_SPAWN);
			newStar.yCoord = 0;
			miscInRoom.add(newStar);
		} else if (i == 4) { //1 out of 24 possible scenarios spawn a galaxy
			final Galaxy newGalaxy = GraphicsFactory.getInstance().getGalaxy();
			newGalaxy.xCoord = rand.nextInt(FAR_EDGE_SPAWN);
			newGalaxy.yCoord = 0;
			miscInRoom.add(newGalaxy);
		}
	}

	// Spawn a couple of sparks at origin
	@SuppressWarnings("NestedAssignment") // 2 lines is better than 4
	private void spawnSparks(Agent origin, GameObject object) {
		Random rand = new Random();

		// Spawn between 3 and 6 sparks
		int m = rand.nextInt(4) + 3;
		for (int i = 0; i < m; i++) {
			final Spark newSpark = GraphicsFactory.getInstance().getSpark();

			newSpark.xCoordFloat = newSpark.xCoord = origin.xCoord - origin.getSize() / 2;
			newSpark.yCoordFloat = newSpark.yCoord = origin.yCoord - origin.getSize() / 2;

			// Adding i to the coordinate to give the sparks a nice spread
			newSpark.calcAngle(object.xCoord + i, object.yCoord + i);

			miscInRoom.add(newSpark);
		}
		notifyListeners();
	}

	// Spawn one or several shots from enemy aimed at the player
	private void spawnShot(Enemy enemy) {
		shotsInRoom.addAll(enemy.shoot(player));
		notifyListeners();
	}

	// Spawn a shot at the players position, traveling in the players direction
	// Public because it's called from EventHandler
	public void fireShot(String shotType) {
		switch (shotType) {
			case ("StraightShot"):
				shotsInRoom.add(spawnPlayerShot(player.getDirection()));
				break;
			case ("StrafeShots"):
				Direction playerFacing = player.getDirection();
				if (playerFacing == Direction.NORTH || playerFacing == Direction.SOUTH) {
					shotsInRoom.add(spawnPlayerShot(Direction.EAST));
					shotsInRoom.add(spawnPlayerShot(Direction.WEST));
				} else {
					shotsInRoom.add(spawnPlayerShot(Direction.NORTH));
					shotsInRoom.add(spawnPlayerShot(Direction.SOUTH));
				}
				break;
			default:
				break;
		}
		notifyListeners();
	}

	private StraightShot spawnPlayerShot(Direction direction) {
		StraightShot newShot = GraphicsFactory.getInstance().getPlayerShot();
		newShot.xCoord = player.xCoord - 4;
		newShot.yCoord = player.yCoord - 4;
		newShot.setAlignment(false); //false != enemy
		newShot.setDirection(direction);
		return newShot;
	}

	// Move the player and notify listeners
	// Public because it's called from EventHandler
	public boolean movePlayer(Direction direction) {
		if (direction == Direction.OTHER) {
			player.move(direction); //actually does not move
		}
		player.rotate(direction, player.getDirection());
		boolean moved = player.move(direction);
		notifyListeners();
		return moved;
	}

	// Public because it's called by GameFrame
	// Called by the clock, handles enemies, shots and some game mechanics
	public void tick() {
		if (roomOver) {
			moveToTop();
		} else {
			if (player.isDead()) {
				gameOver(DIED);
			} else if (!enemiesInRoom.isEmpty()) {  // If no enemies, spawn new room
				handleEnemies();
				handleShots();
				handleGFK();
			} else {  // Room is empty, increment player skill and spawn a new room
				player.incSkill(); // Increase player level
				if (player.getSkill() == MAX_LEVEL + 1) {
					System.out.println(MAX_LEVEL);
					gameOver(WON);
				}
				roomOver = true;
				clearRoom();
			}
			notifyListeners();
		}
	}

	private void handleEnemies() {
		Iterator<Enemy> e = enemiesInRoom.iterator();
		while (e.hasNext()) {
			Enemy enemy = e.next();
			if (enemy.isDead()) {   // Killing an enemy
				score.addToCurrentScore(enemy.getWorth());  // Increase score
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
	}

	// tick() help method for spawning and moving stars and galaxies
	private void handleGFK() {
		Iterator<GameObject> misc = miscInRoom.iterator();
		while (misc.hasNext()) {
			GameObject gfx = misc.next();
			if (gfx.move()) {
				misc.remove();
			}
		}
		// Perhaps spawn a star
		spawnBackgroundGraphics();
	}

	// tick() help method that move the player "forward" and spawn a new room once we reach the edge
	private void moveToTop() {
		handleGFK();
		for (int r = 3; r > 0; --r) {
			if (!movePlayer(Direction.NORTH)) {
				roomOver = false;
				newRoom();
			}
		}
	}

	// tick() help method taking care of moving all shots and identifying hits
	private void handleShots() {
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
					score.subFromCurrentScore(100); // Getting hit is baaaaaad
				}
			} else {
				// Remove player shots that hit an enemy
				for (Enemy enemy : enemiesInRoom) {
					if (shot.collision(enemy)) {
						enemy.hp--;
						s.remove();
						spawnSparks(enemy, shot);
						score.addToCurrentScore(10);    // Some few points for hitting
					}
				}
			}
		}
	}

	private void gameOver(String reason) {
		GameFrame.gameOver();

		enemiesInRoom.clear();
		shotsInRoom.clear();

		score.saveScore();
		score.writeHighscore();
		System.out.println("Game Over: " + reason);
		System.out.println("Score: " + score.getCurrentScore());
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
