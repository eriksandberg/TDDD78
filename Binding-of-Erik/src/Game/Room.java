package Game;

/**
 * Created by wassing on 2016-04-04.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//public class Room extends JPanel {} //if we do jpanels in jframes, but wait with this.
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
    private List<Enemy> enemiesInRoom = new ArrayList<>();
	private List<Shot> shotsInRoom = new ArrayList<>();

    private final List<BoardListener> boardListenerArray = new ArrayList<BoardListener>();

    @SuppressWarnings("SuspiciousGetterSetter")
	public int getPixelWidthPerTile() {return PIXELWIDTH_PER_TILE;} //used by painter

    @SuppressWarnings("SuspiciousGetterSetter")
	public int getPixelHeightPerTile() {return PIXELHEIGHT_PER_TILE;}  //maybe change name instead of supressing warning

    public int getWidth(){return width;}

    public int getHeight(){return height;}

	public static int getFarEdge() {
		return FAR_EDGE;
	}

	public static int getAdjEdge() {
		return ADJ_EDGE;
	}

	/**
	 * Custom constructor for a room. May not be used at all later, depends how we implement.
	 */
	public Room(Player player, int width, int height){
		this.player = player;

		this.width = width; //800
		this.height = height; //800

		board = new TileType[width][height];
		for (int tileX = 0; tileX < width; tileX++){ //will loop for every "square" and assign a tile to it.
			for (int tileY = 0; tileY < height; tileY++){
				board[tileX][tileY] = TileType.BLACK; // Space yo
			}
		}

		newRoom();
	}

	// Public because EventHandler must be able to spawn a new room due to testing
	public void newRoom() {
		//noinspection MagicNumber, let the player spawn in the middle of the room
		spawnPlayer(90, 90);
		spawnEnemies();
		// Should probably pause for a couple of seconds, don't want to confuse the player
	}

	// Basically a restart, public for the same reason as newRoom is
	public void resetRoom() {
		player.resetSkill();
		player.resetHP();
		enemiesInRoom.clear();
		shotsInRoom.clear();
		newRoom();
	}

	// Called to spawn the player at pos x, y when "entering" a new room
	public void spawnPlayer(int x, int y) {
		this.player.xCoord = x;
		this.player.yCoord = y;
		player.setDirection('E');
		notifyListeners();
	}

	// Randomize a number of enemies based on the players current skill level
	public void spawnEnemies() {
		Random rand = new Random();

		//
		int i = player.getSkill();
		while (i > 0) {
			int e = rand.nextInt(i);
			enemiesInRoom.add(spawnEnemy(e));
			i =- e;
		}
		notifyListeners();
	}

	// Spawn an enemy of power level power
	public Enemy spawnEnemy(int power) {
		Enemy newEnemy = GraphicsFactory.getInstance().getNormalEnemy();
		newEnemy.setShotCooldown(10 - power);

		// add random spawn based on boundaries and the players position
		newEnemy.xCoord = 100;
		newEnemy.yCoord = 100;

		return newEnemy;
	}

	// Spawn a shot aimed at the player
	@SuppressWarnings("NestedAssignment") // 2 lines is better than 4
	public void spawnShot(int x, int y) {
		final Shot newShot = GraphicsFactory.getInstance().getLightShot();
		newShot.xCoordFloat = newShot.xCoord = x;	// supressed warning
		newShot.yCoordFloat = newShot.yCoord = y;	// supressed warning

		// Calculate the trajectory of the shot
		newShot.calcAngle(player.xCoord, player.yCoord);

		// true = enemy
		newShot.setAlignment(true);

		// Add the shot to the room
		shotsInRoom.add(newShot);
		notifyListeners();
	}

	// Spawn a shot at the players position, traveling in the players direction
	// Currently not actually working
	// Also, need to recognize if a shot is friendly or not. Either by handling them separate or by an identifyer
	public void fireShot() {
		final StraightShot newShot = GraphicsFactory.getInstance().getPlayerShot();
		newShot.xCoord = player.xCoord - 4;
		newShot.yCoord = player.yCoord - 4;

		newShot.setDirection(player.getDirection());

		// false = !enemy
		newShot.setAlignment(false);

		shotsInRoom.add(newShot);
		notifyListeners();
	}

	// Move the player and notify listeners
	// Maybe put this in tick()?
	public void movePlayer(String direction) {
		player.move(direction);
		notifyListeners();
	}

    public void tick(){
        // Always called by the clock, handles enemies, shots and some game mechanics
        if (player.isDead()){
			gameOver();
		} else if (!enemiesInRoom.isEmpty()) {
			// Handle enemies (if there are enemies, otherwise spawn a new room)
			for (Enemy enemy : enemiesInRoom) {
				// move enemies
				if (enemy.collision(player)) {
					player.hp--;
				}
				if (enemy.readyToShoot()) {
					spawnShot(enemy.xCoord - 4, enemy.yCoord - 4);
				}
			}
			// Handle shots, need to use iterator instead of foreach to be able to use .remove()...
			Iterator<Shot> s = shotsInRoom.iterator();
			while (s.hasNext()) {
				Shot shot = s.next();
				// move() return true if the shot is moving out of the map
				if (shot.move()) {s.remove();}
				// Remove shots that hit the player
				if (shot.isEnemy()) {
					if (shot.collision(player)) {
						s.remove();
						player.hp--;
					}
				} else {
					Iterator<Enemy> e = enemiesInRoom.iterator();
					while (e.hasNext()) {
						Enemy enemy = e.next();
						if (shot.collision(enemy)) {    // Collision detection seem to be quite dodgy
							s.remove();
							enemy.hp--;
							/*if (enemy.isDead()) {
								e.remove();
							}*/
							e.remove();   // OHK enemies for testing purposes
						}
					}
				}
			}
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
		System.out.println("Game Over!");
	}

	/**
	 * This function is used by the paint component to find all objects on each specific square on the board.
	 * It first checks the player, then enemies and last shots.
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
		for(Enemy enemy : enemiesInRoom){
			if(enemy.getTile(x, y)!=null){
				return enemy.getTile(x, y);
			}
		}
		// Get shots
		for (Shot shot : shotsInRoom){
			if (shot.getTile(x, y) != null) {
				return shot.getTile(x, y);
			}
		}

		square = board[x][y];
		return square;
	}

    private void notifyListeners(){
        for (BoardListener boardListener : boardListenerArray) {
            boardListener.boardChanged();
        }
    }

    public void addBoardListener(BoardListener bl){
        boardListenerArray.add(bl);
    }
}
