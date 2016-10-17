/**
 * Created by wassing on 2016-10-17.
 */
public class NumberTester
{
    private NumberTester() {}

    public static boolean isEven(int number) {
        int remainder = number % 2;
        return remainder == 0;
    }

    public static void main(String[] args) {
        System.out.println(isEven(17));
        System.out.println(isEven(42));
    }
}
