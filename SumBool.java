public class SumBool {
    public static void main(String[] args) {
        System.out.println(checkSumInRange(3, 4));
        System.out.println(checkSumInRange(7, 8));
        System.out.println(checkSumInRange(11, 12));
    }

    public static boolean checkSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }
}