package tetris;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 2013-09-30
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class Poly {

    private SquareType[][] shape;
    private final int width;
    private final int height;

    public Poly(SquareType[][] shape, int width, int height) {
        this.shape = shape;
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public SquareType[][] getShape(){
        return shape;
    }

    /*public SquareType getPolyTypeAt(int x, int y) {
            return shape[y][x];
        }*/

    public void rotateRight(){
        if(height == 4 || width == 4){
            this.shape = new SquareType[][] { { this.shape[0][3], this.shape[1][3], this.shape[2][3], this.shape[3][3] },
                    { this.shape[0][2], this.shape[1][2], this.shape[2][2], this.shape[3][2] },
                    { this.shape[0][1], this.shape[1][1], this.shape[2][1], this.shape[3][1] },
                    { this.shape[0][0], this.shape[1][0], this.shape[2][0], this.shape[3][0] } };
        }else if(height == 3 || width == 3){
            this.shape = new SquareType[][] { { this.shape[0][2], this.shape[1][2], this.shape[2][2] },
                    { this.shape[0][1], this.shape[1][1], this.shape[2][1] },
                    { this.shape[0][0], this.shape[1][0], this.shape[2][0] } };
        }
    }

    public void rotate(boolean right){
        if(right){
            rotateRight();
        }else{  // left
            rotateRight();
            rotateRight();
            rotateRight();
        }
    }
}
