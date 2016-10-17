/**
 * Created by wassing on 2016-10-17.
 */
public enum Mode
{
    MODE_NORMAL("NORMAL"), MODE_GHOST("GHOST"), MODE_INVULNERABLE("INVULNERABLE");

    private String modeName;

    Mode(String name){modeName = name;}

    @Override
    public String toString(){
	return modeName;
    }
}