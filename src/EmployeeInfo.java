public class EmployeeInfo {
    private int empID;
    private String Fname, LName, email;
    private double Salary;
    private String HireDate;
    private int jobTitleId;
    private int divisionId;

    public EmployeeInfo() {

    }

    public void addEmployee(int empID, String Fname, String LName, String email, double Salary, String HireDate, int job_Title_Id, int div_Id) {
        this.empID = empID;
        this.Fname = Fname;
        this.LName = LName;
        this.email = email;
        this.Salary = Salary;
        this.HireDate = HireDate;
        this.jobTitleId = job_Title_Id;
        this.divisionId = div_Id;
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

    public int getJobTitleId() {
        return jobTitleId;
    }

    public int getDivisionId() {
        return divisionId;
    }
}

