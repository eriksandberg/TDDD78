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

    private static final int PIXELWIDTH_PER_TILE = 4;
    private static final int PIXELHEIGHT_PER_TILE = 4; //this would give us a room with 200x200 squares..

    private TileType[][] board;
    private int height;
    private int width;

    private Player player = null;
    private List<Entity> entitiesInRoom = new ArrayList<Entity>();
	private List<Shot> shotsInRoom = new ArrayList<>();

	public boolean gameOver = false;

    private final List<BoardListener> boardListenerArray = new ArrayList<BoardListener>();

    @SuppressWarnings("SuspiciousGetterSetter")
	public int getPixelWidthPerTile() {return PIXELWIDTH_PER_TILE;} //used by painter

    @SuppressWarnings("SuspiciousGetterSetter")
	public int getPixelHeightPerTile() {return PIXELHEIGHT_PER_TILE;}  //maybe change name instead of supressing warning

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public TileType[][] getBoard() {return board;}

    public int getColumns(){return (width/ PIXELWIDTH_PER_TILE);} //200

    public int getRows(){return (height/ PIXELHEIGHT_PER_TILE);} //200

    public Player getPlayer(){return player;}

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
		for(Entity enemy : entitiesInRoom){
			if(enemy.getTile(x,y)!=null){
				return enemy.getTile(x,y);
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

    public void tick(){

        //always called by the clock, does all the "machine" work, will call functions which in turn call the paint-components
        if (player == null){ //just an extra check
            if(!gameOver){
                System.out.println("HEY I SPAWNED BECAUSE APPARENTLY I DIDNT EXIST");
                spawnPlayer(0,0);
            }
            else{
				System.out.println("Game Over!");
			}
        }

        else { //major game logic goes here
            Iterator<Entity> i = entitiesInRoom.iterator();
            while (i.hasNext()){
                Entity oneEntity = i.next();
                //moveEntity(oneEntity); //move the enemies!
                if (oneEntity.shotCooldown == 0 && oneEntity.isEnemy) {
                    spawnShot(oneEntity.entityXCoord -4, oneEntity.entityYCoord - 4);
					oneEntity.shotCooldown = 10; //spawn one shot every 10 ticks, move this logic to enemy class
                } else if (oneEntity.shotCooldown > 0){
                    oneEntity.shotCooldown--;
                }
            }

			// Handle shots
			Iterator<Shot> s = shotsInRoom.iterator();
			while (s.hasNext()) {
				Shot oneShot = s.next();
				if (!oneShot.move()) {s.remove();}  // move() return false if the shot is moving out of the map (not yet implemented)
			}
        }
        notifyListeners();
    }

    /**
     * Custom constructor for a room. May not be used at all later, depends how we implement.
     */
	public Room(int width, int height){
	this.width = width; //800
	this.height = height; //800
	board = new TileType[width][height];
	for (int tileX = 0; tileX < width; tileX++){ //will loop for every "square" and assign a tile to it.
	    for (int tileY = 0; tileY < height; tileY++){
                board[tileX][tileY] = TileType.R; //grass, green
	    }
	}
		// Spawn player and enemies at positions x,y. I guess this is temporary but wanted to test how to supress warnings
		//noinspection MagicNumber
		spawnPlayer(30,30);
		//noinspection MagicNumber
        spawnNormalEnemy(70, 120);
    }

    public void pickRandomRoom(){
        //pick from a pre-defined set of rooms, to be used way later in the project when we got that far.
		// Or better, generate a random room according to difficulty
    }

    /**
     * Fairly straightforward. Is called once per room. Possible to modify x,y depending on needs.
     */
    public void spawnPlayer(int x, int y){ //should only have to be called ONCE per room. x,y, modifiers are needed.
        this.player = GraphicsFactory.getInstance().getPlayer(); //could also be character
        this.player.xCoord = x;
		this.player.yCoord = y;
	notifyListeners();
    }

	// Make these into one in the future
    public void spawnNormalEnemy(int x, int y){ //Currently only spawns regular enemy.
        Random rand = new Random(); //Randoms coordinates for spawn.
	final Entity newEntity = GraphicsFactory.getInstance().getNormalEnemy(); //right now just gets one default enemy.
        if (x == 0 && y == 0){ //hardcoded params to get a random number.
            newEntity.entityXCoord = rand.nextInt(200);
            newEntity.entityYCoord = rand.nextInt(200);
        } else { //use provided numbers
            newEntity.entityXCoord = x;
            newEntity.entityYCoord = y;
        }
        newEntity.isEnemy = true;
        newEntity.shotCooldown = 0;
        entitiesInRoom.add(newEntity); //append the enemy to all enemies in room.
	notifyListeners();
	}

	public void spawnInvaderEnemy(int x, int y){
		Random rand = new Random(); //Randoms coordinates for spawn.
		final Entity newEntity = GraphicsFactory.getInstance().getInvaderEnemy(); //right now just gets one default enemy.
		if (x == 0 && y == 0){ //hardcoded params to get a random number.
			newEntity.entityXCoord = rand.nextInt(200);
			newEntity.entityYCoord = rand.nextInt(200);
		} else { //use provided numbers
			newEntity.entityXCoord = x;
			newEntity.entityYCoord = y;
		}
		newEntity.isEnemy = true;
		newEntity.shotCooldown = 0;
		entitiesInRoom.add(newEntity); //append the enemy to all enemies in room.
		notifyListeners();
	}

	public void spawnShot(int x, int y) {
		final Shot newShot = GraphicsFactory.getInstance().getLightShot();
		newShot.xCoordFloat = newShot.xCoord = x;
		newShot.yCoordFloat = newShot.yCoord = y;

		// Calculate the trajectory of the shot
		newShot.calcAngle(player.xCoord, player.yCoord);

		// Add the shot to the room and ???
		shotsInRoom.add(newShot);
		notifyListeners();
	}

    public void moveAnywhere(String direction){
        if (gameOver){
            return; //can't move if we lost, prevents us from doing stupid things.
        }
        switch (direction){
            case "up": //go up
		player.yCoord -= 1;
                break;
            case "down": //go down
                player.yCoord += 1;
                break;
            case "right": //go right
		player.xCoord += 1;
                break;
            case "left": //go left
                player.xCoord -= 1;
                break;
        }
	if (!canWeMove(direction)){
	    //reset us if we went out of bounds. Checks after we try the new position
	}
        notifyListeners();
    }

    private boolean canWeMove(String direction){
    	//need to bordercheck. LATER. Look up tetris boundchecking.
    	return false;
    }

    public void moveEntity(Entity entity){ //moves entities at random. Smarter moving algorithm later.
        Random ran = new Random();
        int x = ran.nextInt(5);
        switch (x){
            case 0:
                entity.entityXCoord += 1;
                break;
            case 1:
                entity.entityXCoord -= 1;
                break;
            case 2:
                entity.entityYCoord += 1;
                break;
            case 3:
                entity.entityYCoord -= 1;
                break;
            case 4: //remain in position
                break;
        }
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
