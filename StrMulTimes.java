public class StrMulTimes {
    public static void main(String[] args) {
        printStringMultipleTimes("Java", 5); // Пример вызова метода
    }

    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }
}