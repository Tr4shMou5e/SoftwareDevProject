public class EmployeeInfo {
    private int empID;
    private String Fname, LName, email;
    private double Salary;
    private String HireDate;

    public EmployeeInfo() {

    }

    public void addEmployee(int empID, String Fname, String LName, String email, double Salary, String HireDate) {
        this.empID = empID;
        this.Fname = Fname;
        this.LName = LName;
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

    public String getLName() {
        return LName;
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

