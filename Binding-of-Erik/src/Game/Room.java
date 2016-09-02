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
    public boolean gameOver = false;
    private Entity playerEntity = null;
    private final static int SHOTSPEED = 5; //configurable
    private List<Entity> entitiesInRoom = new ArrayList<Entity>();
    private List<Entity> shotsInRoom = new ArrayList<Entity>();

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

    public Entity getPlayerEntity(){return playerEntity;}

    /**
     * This function is basically used by the paint component. It finds all entities on the board when asked for.
     * For a specific square it will determine what's on it. It first checks the player, then all possible enemies.
     */
    public TileType getSquare(int x, int y) {

        TileType square;
		if (playerEntity != null) {
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
                spawnPlayer(0,0);
            }
            else{
                //do nothing. Game is over. Maybe add an exit statement here or repaint screen.
            }
        }
        else { //major game logic goes here
            Iterator<Entity> i = entitiesInRoom.iterator();
            while (i.hasNext()){
                Entity oneEntity = i.next();
                //moveEntity(oneEntity); //move the enemies!
                if (oneEntity.shotCooldown == 0 && oneEntity.isEnemy) { //entity is enemy. Shots fired is a helper
                    spawnNormalEnemyShot(oneEntity.entityXCoord - 4, oneEntity.entityYCoord - 4); //can be spawned on top of the entity that spawned it.
                    oneEntity.shotCooldown = 5; //spawn one shot every 5 ticks, move this logic to enemy class
                } else if (oneEntity.shotCooldown > 0){
                    oneEntity.shotCooldown--;
                } else if (!oneEntity.isEnemy){ //entity is a shot
                    if (outOfBounds(oneEntity)){
                        i.remove(); //garbage collector
                    }
                    //need to rework shots later so they move differently depending on what enemy shoots them, think OO.
                    oneEntity.entityXCoordFloat += SHOTSPEED * oneEntity.xAngle;
                    oneEntity.entityXCoord = Math.round(oneEntity.entityXCoordFloat); //round the float to nearest real square

                    oneEntity.entityYCoordFloat += SHOTSPEED * oneEntity.yAngle;
                    oneEntity.entityYCoord = Math.round(oneEntity.entityYCoordFloat); //round the float to nearest real square
                }
            }
            for (int j = shotsInRoom.size()-1; j >= 0; j--){ //this extra forloop serves to avoid inf loops
                entitiesInRoom.add(shotsInRoom.get(j));
                shotsInRoom.remove(j);
            }
            //System.out.println("Entities on field: " + entitiesInRoom.size()); //debug
        }
        notifyListeners();
    }

    /**
     * Only used for shots. Pretty self explanatory. Helps free up memory.
     */
    private boolean outOfBounds(Entity oneEntity){
        if (oneEntity.entityXCoord < -10 || oneEntity.entityYCoord < -10 ||
            oneEntity.entityXCoord > 210 || oneEntity.entityYCoord > 210) {  // Where does this come from? Fix.
            return true;
        }
        return false;
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
        spawnInvaderEnemy(100, 100);
    }

    public void pickRandomRoom(){
        //pick from a pre-defined set of rooms, to be used way later in the project when we got that far.
    }

    /**
     * Fairly straightforward. Is called once per room. Possible to modify x,y depending on needs.
     */
    public void spawnPlayer(int x, int y){ //should only have to be called ONCE per room. x,y, modifiers are needed.
        this.playerEntity = GraphicsFactory.getInstance().getPlayer(); //could also be character
        this.playerEntity.entityXCoord = x;
	this.playerEntity.entityYCoord = y;
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

    public void spawnNormalEnemyShot(int x, int y){
        final Entity newShot = GraphicsFactory.getInstance().getLightShot();
        newShot.entityXCoord = x;
        newShot.entityYCoord = y;
        newShot.entityXCoordFloat = x;
        newShot.entityYCoordFloat = y;
        newShot.isEnemy = false; //it is not an enemey, it is a shot
        newShot.deltaXFloat = createShotXTrajectory(x);
        newShot.deltaYFloat = createShotYTrajectory(y);
        final float startHypotenuse = (float)Math.sqrt(newShot.deltaXFloat * newShot.deltaXFloat +
                                                       newShot.deltaYFloat * newShot.deltaYFloat);
        //inbuilt java math packages handle all 4 quadrants. Magic 100 is to reduce the number that is later used for speed
        newShot.xAngle = (float)Math.toDegrees(Math.asin((newShot.deltaXFloat/startHypotenuse)))/100;
        newShot.yAngle = (float)Math.toDegrees(Math.asin((newShot.deltaYFloat/startHypotenuse)))/100;
        shotsInRoom.add(newShot); //append the shot to all shots in the room.
        notifyListeners();
    }

    private float createShotXTrajectory(int spawnPosX){
        float deltaX = (playerEntity.entityXCoord - 4 - spawnPosX); //generate a number, negative or positive
        return deltaX;
    }

    private float createShotYTrajectory(int spawnPosY){
        float deltaY = (playerEntity.entityYCoord - 4 - spawnPosY);
        return deltaY;
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
