package Game;

/**
 * Created by wassing on 2016-04-04.
 */

import java.lang.String;
import java.util.ArrayList;
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
    public boolean gameOver = false;
    private Entity playerEntity = null;
    private final static int SHOTSPEED = 1; //configurable
    private List<Entity> entitiesInRoom = new ArrayList<Entity>();
    private List<Entity> shotsInRoom = new ArrayList<Entity>();

    private final List<BoardListener> boardListenerArray = new ArrayList<BoardListener>();

    public int getPixelWidthPerTile() {return PIXELWIDTH_PER_TILE;} //used by painter

    public int getPixelHeightPerTile() {return PIXELHEIGHT_PER_TILE;}

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public TileType[][] getBoard() {return board;}

    public int getColumns(){return (width/ PIXELWIDTH_PER_TILE);} //200

    public int getRows(){return (height/ PIXELHEIGHT_PER_TILE);} //200

    public Entity getPlayerEntity(){return playerEntity;}

    /**
     * This function is basically used by the paint component. It finds all entities on the board when asked for.
     * For a specific square it will determine what's on it. It first checks the player, then all possible enemies.
     */
    public TileType getSquare(int x, int y) {

        TileType square;
        if (this.getPlayerEntity() != null) {
            if (playerEntity.getTile(x, y) != null) {
                return playerEntity.getTile(x, y);
            } else {
                for(Entity enemy : entitiesInRoom){
                    if(enemy.getTile(x,y)!=null){
                        return enemy.getTile(x,y);
                    }
                }
            }
        }
        square = board[x][y];
        return square;
    }

    public void tick(){
        //always called by the clock, does all the "machine" work, will call functions which in turn call the paint-components
        if (playerEntity == null){ //just an extra check
            if(!gameOver){
                System.out.println("HEY I SPAWNED BECAUSE APPARENTLY I DIDNT EXIST");
                spawnPlayer();
            }
            else{
                //do nothing. Game is over. Maybe add an exit statement here or repaint screen.
            }
        }
        else { //major game logic goes here
            for (Entity oneEntity : entitiesInRoom){
                //only do the following if the looped entity is an actual enemy
                //moveEntity(oneEntity); //move the enemies!
                if (oneEntity.shotsFired < 1){ //can be used as debugger.
                    spawnNormalEnemyShot(oneEntity.entityXCoord - 4, oneEntity.entityYCoord - 4); //can be spawned on top of the entity that spawned it.
                }
            }
            for (Entity oneShot : shotsInRoom){
                oneShot.entityXCoordFloat += oneShot.shotXTrajectory; //is a float.
                oneShot.entityXCoord += Math.round(oneShot.entityXCoordFloat);
                oneShot.entityYCoordFloat += oneShot.shotYTrajectory; //is a float.
                oneShot.entityYCoord += Math.round(oneShot.entityYCoordFloat);
                System.out.println("Shot is at coordinates " + oneShot.entityXCoord + "," + oneShot.entityYCoord); //debug
            }
            //add all shots to the entity list. Remove all the shots afterwards.
            /*for (int i = shotsInRoom.size()-1; i >= 0; i--){
                entitiesInRoom.add(shotsInRoom.get(i));
                shotsInRoom.remove(i);
            }*/
        }
	notifyListeners();
    }

    public void pickRandomRoom(){
        //pick from a pre-defined set of rooms, to be used way later in the project when we got that far.
    }

    //custom constructor for a room, may not be used at all later. Depends how we implement.
    public Room(int width, int height){
	this.width = width; //800
	this.height = height; //800
	board = new TileType[width][height];
	for (int tileX = 0; tileX < width; tileX++){ //will loop for every "square" and assign a tile to it.
	    for (int tileY = 0; tileY < height; tileY++){
	    	board[tileX][tileY] = TileType.R; //grass, green
	    }
	}
        spawnPlayer();
        spawnEnemy();
        //spawnEnemy();
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
    };

    public void spawnPlayer(){ //should only have to be called ONCE per room. x,y, modifiers are needed.
        this.playerEntity = GraphicsFactory.getInstance().getPlayer(); //could also be character
        this.playerEntity.entityXCoord = 0;
	this.playerEntity.entityYCoord = 0;
	notifyListeners();
    }

    public void spawnEnemy(){ //should be used to spawn several enemy objects, must be stored in an array
        Random rand = new Random(); //if we want to get random enemies later.
	final Entity newEntity = GraphicsFactory.getInstance().getEnemy(); //right now just gets one default enemy.
        newEntity.entityXCoord = 100; //rand.nextInt(200);
        newEntity.entityYCoord = 100; //rand.nextInt(200);
        newEntity.isEnemy = true;
        newEntity.shotsFired = 0;
        entitiesInRoom.add(newEntity); //append the enemy to all enemies in room.
	notifyListeners();
    }

    public void spawnNormalEnemyShot(int x, int y){
        final Entity newShot = GraphicsFactory.getInstance().getLightShot();
        newShot.entityXCoord = x;
        newShot.entityYCoord = y;
        newShot.isEnemy = false; //it is not an enemey, it is a shot
        newShot.shotXTrajectory = createShotXTrajectory(x);
        newShot.shotYTrajectory = createShotYTrajectory(y);
        System.out.println("Created new shot at " + x + "," + y); //debug
        shotsInRoom.add(newShot);
        notifyListeners();
    }

    //math to calculate trajectory is based on the configured shot speed.
    private float createShotXTrajectory(int spawnPosX){
        float xTrajectory = (playerEntity.entityXCoord + 4 - spawnPosX)/SHOTSPEED;
        return xTrajectory;
    }

    private float createShotYTrajectory(int spawnPosY){
        float yTrajectory = (playerEntity.entityYCoord + 4 - spawnPosY)/SHOTSPEED;
        return yTrajectory;
    }

    public void addBoardListener(BoardListener bl){
	boardListenerArray.add(bl);
    }

    private void notifyListeners(){
        for (BoardListener boardListener : boardListenerArray) {
            boardListener.BoardChanged();
        }
    }

    private boolean canWeMove(String direction){
	//need to bordercheck. LATER. Look up tetris boundchecking.
	return false;
    }

    public void moveAnywhere(String direction){
        if (gameOver){
            return; //can't move if we lost, prevents us from doing stupid things.
        }
        switch (direction){
            case "up": //go up
		playerEntity.entityYCoord -= 1;
                break;
            case "down": //go down
                playerEntity.entityYCoord += 1;
                break;
            case "right": //go right
		playerEntity.entityXCoord += 1;
                break;
            case "left": //go left
                playerEntity.entityXCoord -= 1;
                break;
        }
	if (!canWeMove(direction)){
	    //reset us if we went out of bounds. Checks after we try the new position
	}
        notifyListeners();
    }

    /*public void insertEntity(Entity entity){
            for (int i = 0; i < entity.getWidth(); i++){
                for (int j = 0; j < entity.getHeight(); j++){
                    TileType type = entity.getShape()[i][j]; //looping through every "square" that a player or monster has
                    if (type != TileType.EMPTY){ //make sure that there are no empty squares. We don't store those.
                        //board[i+entityXCoord][j+entityYCoord] = type; //store the actual square.
                    }
                }
            }
        }*/
}
