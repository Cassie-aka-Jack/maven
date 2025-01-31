public class Employee {
    private String name;
    private String job_title;
    private String email;
    private String phone;
    private double salary;
    private int ages;

    public Employee(String name, String job_title, String email, String phone, double salary, int ages) {
        this.name = name;
        this.job_title = job_title;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.ages = ages;
    }

    public void Information() {
        System.out.println("Name: " + name);
        System.out.println("Job title: " + job_title);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Salary: " + salary);
        System.out.println("Ages: " + ages);
    }

    public static void main(String[] args) {
        Employee employee = new Employee("Gabe Newell", "developer", "email1@mail.com", "751943", 95000, 62);
        employee.Information();
    }
}