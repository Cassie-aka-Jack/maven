public class CheckSum {
    public static void main(String[] args) {
        checkSumSign();
    }

    public static void checkSumSign() {
        int a = 9;
        int b = -10;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }
}