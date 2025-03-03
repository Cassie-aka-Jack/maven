import java.util.*;
import java.util.regex.*;

class PhoneDirectory {
    private Map<String, List<String>> directory;

    public PhoneDirectory() {
        directory = new HashMap<>();
        add("Иванов", "8(921)1234567");
        add("Иванов", "8(921)7654321");
        add("Иванов", "8(921)1112233");
        add("Сидоров", "8(921)4445566");
        add("Сидоров", "8(921)7778899");
        add("Васильев", "8(921)9988776");
        add("Смирнов", "8(921)1122334");
        add("Кузнецов", "8(921)5566778");
        add("Петров", "8(921)9900112");
        add("Новиков", "8(921)3344556");
    }

    public boolean add(String surname, String phoneNumber) {
        if (!isValidSurname(surname)) {
            System.out.println("Некорректный формат фамилии: " + surname);
            return false; // Фамилия не соответствует формату
        }
        if (isValidPhoneNumber(phoneNumber)) {
            directory.computeIfAbsent(surname, k -> new ArrayList<>()).add(phoneNumber);
            return true; // Номер добавлен успешно
        } else {
            System.out.println("Некорректный формат номера телефона: " + phoneNumber);
            return false; // Номер не добавлен
        }
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, Collections.emptyList());
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^8\\(\\d{3}\\)\\d{7}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private boolean isValidSurname(String surname) {
        Pattern pattern = Pattern.compile("^[А-ЯЁ][а-яё]*$");
        Matcher matcher = pattern.matcher(surname);
        return matcher.matches();
    }

    public static void main(String[] args) {
        PhoneDirectory pd = new PhoneDirectory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВведите цифру необходимого действия:");
            System.out.println("1. Найти номер по фамилии.");
            System.out.println("2. Добавить новую запись.");
            System.out.println("3. Выйти.");
            System.out.print("Ваш выбор: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nНеверный выбор. Попробуйте снова.");
                scanner.next(); // Очистка буфера сканера
                continue; // Переход к следующей итерации цикла
            }
            scanner.nextLine(); // Очистка буфера после nextInt()

            if (choice == 1) {
                System.out.print("\nВведите фамилию для поиска: ");
                String surname = scanner.nextLine();
                List<String> phoneNumbers = pd.get(surname);
                if (phoneNumbers.isEmpty()) {
                    System.out.println("Номеров для фамилии " + surname + " не найдено.");
                } else {
                    System.out.println("Номера для фамилии " + surname + ": " + phoneNumbers);
                }
            } else if (choice == 2) {
                System.out.print("\nВведите фамилию на русском языке с заглавной буквы: ");
                String surname = scanner.nextLine();

                // Проверка фамилии
                if (!pd.isValidSurname(surname)) {
                    System.out.println("Некорректный формат фамилии: " + surname);
                    continue; // Прерываем текущую итерацию цикла
                }

                System.out.print("Введите номер телефона в формате 8(***)*******: ");
                String phoneNumber = scanner.nextLine();

                // Проверка номера телефона
                boolean isAdded = pd.add(surname, phoneNumber);
                if (isAdded) {
                    System.out.println("Запись добавлена.");
                }
            } else if (choice == 3) {
                System.out.println("Выход из программы.");
                break;
            } else {
                System.out.println("\nНеверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }
}