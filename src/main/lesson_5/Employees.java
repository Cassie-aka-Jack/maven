public class Employees {
    private String name;
    private String job_title;
    private String email;
    private String phone;
    private double salary;
    private int ages;

    public Employees(String name, String job_title, String email, String phone, double salary, int ages) {
        this.name = name;
        this.job_title = job_title;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.ages = ages;
    }

    public String getName() {
        return name;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public double getSalary() {
        return salary;
    }

    public int getAges() {
        return ages;
    }
}