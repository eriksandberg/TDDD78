/**
 * Created by wassing on 2016-10-17.
 */
public enum Speed
{
    SPEED_SLOW("SLOW"), SPEED_MEDIUM("MEDIUM"), SPEED_FAST("FAST");

    private String speedName;

    Speed(String name){speedName = name;}

    @Override
    public String toString(){
	return speedName;
    }
}
