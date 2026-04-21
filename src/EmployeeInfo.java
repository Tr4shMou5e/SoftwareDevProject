
public class EmployeeInfo {
    private int empID;
    private String Fname, Lname, email;
    private double Salary;
    private String HireDate;
    private String division;
    private String jobTitle;

    public EmployeeInfo() {

    }

    public void addEmployee(int empID, String Fname, String Lname, String email, double Salary, String HireDate, String division, String jobTitle) {
        this.empID = empID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.email = email;
        this.Salary = Salary;
        this.HireDate = HireDate;
        this.division = division;
        this.jobTitle = jobTitle;
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

    public String getDivision() { 
        return division; 
    }

    public String getJobTitle() { 
        return jobTitle; 
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setFname(String fname) {
        this.Fname = fname;
    }

    public void setLname(String lname) {
        this.Lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(double salary) {
        this.Salary = salary;
    }

    public void setHireDate(String hireDate) {
        this.HireDate = hireDate;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}

