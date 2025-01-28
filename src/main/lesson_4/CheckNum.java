public class CheckNum {
    public static void main(String[] args) {
        checkNumber(-13);
        checkNumber(0);
        checkNumber(13);
    }

    public static void checkNumber(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }
}