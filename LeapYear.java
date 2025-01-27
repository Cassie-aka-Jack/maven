public class LeapYear {
    public static void main(String[] args) {
        int year = 2025;
        System.out.println("Год " + year + " является високосным: " + isLeapYear(year));
    }

    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }
}