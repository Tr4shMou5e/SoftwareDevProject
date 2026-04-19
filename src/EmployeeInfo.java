
public class EmployeeInfo {
    private int empID;
    private String Fname, Lname, email;
    private double Salary;
    private String HireDate;

    public EmployeeInfo() {

    }

    public void addEmployee(int empID, String Fname, String Lname, String email, double Salary, String HireDate) {
        this.empID = empID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.email = email;
        this.Salary = Salary;
        this.HireDate = HireDate;
    }

    public int getEmpID() {
        return empID;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getEmail() {
        return email;
    }
    
    public double getSalary() {
        return Salary;
    }

    public String getHireDate() {
        return HireDate;
    }

}

