public class Employee {
    private int empID;
    private String Fname, LName, email;
    private double Salary;

    public Employee() {

    }

    public void addEmployee(int empID, String Fname, String LName, String email, double Salary) {
        this.empID = empID;
        this.Fname = Fname;
        this.LName = LName;
        this.email = email;
        this.Salary = Salary;
    }

    public int getEmpID() {
        return empID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLName() {
        return LName;
    }

    public String getEmail() {
        return email;
    }
    
    public double getSalary() {
        return Salary;
    }

}

