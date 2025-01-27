public class CheckBool {
    public static void main(String[] args) {
        System.out.println(isNegative(-13));
        System.out.println(isNegative(0));
        System.out.println(isNegative(13));
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }
}