import java.util.*;

public class Student {
    private String name;
    private String group;
    private int course;
    private Map<String, Integer> grades;

    public Student(String name, String group, int course, Map<String, Integer> grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public double getAverageGrade() {
        return grades.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return name +
                ", " + group +
                ", Курс " + course +
                ", Оценки: " + grades;
    }
}

class StudentManager {
    public void removeUnderperformingStudents(Set<Student> students) {
        students.removeIf(student -> {
            if (student.getAverageGrade() < 3.0) {
                System.out.println("\nОтчисляем студента " + student.getName() + " (средний балл: " + student.getAverageGrade() + ")");
                return true;
            }
            return false;
        });
    }

    public void promoteStudents(Set<Student> students) {
        students.forEach(student -> {
            if (student.getAverageGrade() >= 3.0) {
                System.out.println("Переводим студента " + student.getName() + " на следующий курс");
                student.setCourse(student.getCourse() + 1);
            }
        });
    }

    public void printStudents(Set<Student> students, int course) {
        students.stream()
                .filter(student -> student.getCourse() == course)
                .forEach(student -> System.out.println("Курс " + course + ": " + student.getName()));
    }

    public static void main(String[] args) {
        List<String> subjects = Arrays.asList("Литература", "Математика", "Биология", "Творчество");

        Set<Student> students = new HashSet<>();
        students.add(new Student("Василий Васильев", "Группа 221", 2, createGrades(subjects, 4, 5, 3, 4)));
        students.add(new Student("Денис Денисов", "Группа 211", 2, createGrades(subjects, 5, 5, 5, 5)));
        students.add(new Student("Иван Иванов", "Группа 111", 1, createGrades(subjects, 4, 5, 3, 4)));
        students.add(new Student("Петр Петров", "Группа 121", 1, createGrades(subjects, 2, 3, 2, 3)));

        System.out.println("Исходный список студентов:");
        students.forEach(System.out::println);

        StudentManager manager = new StudentManager();
        manager.removeUnderperformingStudents(students);
        manager.promoteStudents(students);

        System.out.println("\nСписок студентов после сессии:");
        for (int course = 1; course <= 4; course++) {
            manager.printStudents(students, course);
        }
    }

    private static Map<String, Integer> createGrades(List<String> subjects, int... grades) {
        Map<String, Integer> gradeMap = new HashMap<>();
        for (int i = 0; i < subjects.size(); i++) {
            gradeMap.put(subjects.get(i), grades[i]);
        }
        return gradeMap;
    }
}