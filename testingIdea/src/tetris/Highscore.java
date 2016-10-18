package tetris;

/**
 * Created by wassing on 2016-10-18.
 */
public class Highscore
{
    private String name;
    private int score;

    public Highscore(String name, int score){
	this.name = name;
	this.score = score;
    }

    public String getName(){
	return name;
    }

    public int getScore(){
	return score;
    }
}
