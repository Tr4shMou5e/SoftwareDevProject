
import java.sql.*;
import java.util.ArrayList;

public class EmployeeService {
    //HR View
    //Update: HR AND EMPLOYEE VIEW
    public static ArrayList<EmployeeInfo> searchEmployees(
    String fname, String dob, String ssn, String empid
) {
    ArrayList<EmployeeInfo> list = new ArrayList<>();

    String query = """
        SELECT 
            e.empID,
            e.Fname,
            e.Lname,
            e.email,
            e.Salary,
            e.HireDate,
            d.Name AS division,
            jt.job_title AS jobTitle
        FROM employees e
        LEFT JOIN employee_division ed ON e.empid = ed.empid
        LEFT JOIN division d ON ed.div_ID = d.ID
        LEFT JOIN employee_job_titles ejt ON e.empid = ejt.empid
        LEFT JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id
        WHERE 1=1
    """;

    if (!fname.isEmpty()) query += " AND e.Fname = ?";
    if (!dob.isEmpty()) query += " AND e.DOB = ?";
    if (!ssn.isEmpty()) query += " AND e.SSN = ?";
    if (!empid.isEmpty()) query += " AND e.empID = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        int index = 1;

        if (!fname.isEmpty()) ps.setString(index++, fname);
        if (!dob.isEmpty()) ps.setString(index++, dob);
        if (!ssn.isEmpty()) ps.setString(index++, ssn);
        if (!empid.isEmpty()) ps.setInt(index++, Integer.parseInt(empid));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EmployeeInfo e = new EmployeeInfo();
            e.addEmployee(
                rs.getInt("empID"),
                rs.getString("Fname"),
                rs.getString("Lname"),
                rs.getString("email"),
                rs.getDouble("Salary"),
                rs.getString("HireDate"),
                rs.getString("division"),
                rs.getString("jobTitle")
            );

            list.add(e);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    // salary update
    public static void updateSalary(double start, double end, double percent) {

        String query = """
            UPDATE employees
            SET Salary = Salary + (Salary * ? / 100)
            WHERE Salary BETWEEN ? AND ?
        """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setDouble(1, percent);
            ps.setDouble(2, start);
            ps.setDouble(3, end);

            int rows = ps.executeUpdate();

            System.out.println(rows + " employees updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<EmployeeInfo> getEmployeesInRange(double start, double end) {

    ArrayList<EmployeeInfo> list = new ArrayList<>();

    String query = "SELECT empID, Fname, LName, Salary FROM employees WHERE Salary BETWEEN ? AND ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setDouble(1, start);
        ps.setDouble(2, end);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EmployeeInfo e = new EmployeeInfo();
            e.addEmployee(
                 rs.getInt("empID"),
                rs.getString("Fname"),
                rs.getString("Lname"),
                "",                         
                rs.getDouble("Salary"),
                "",                         
                "N/A",                      
                "N/A" 
            );
            list.add(e);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public static ArrayList<String> getAllJobTitles() {
    ArrayList<String> list = new ArrayList<>();
    String sql = "SELECT job_title FROM job_titles";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("job_title"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public static ArrayList<String> getAllDivisions() {
    ArrayList<String> list = new ArrayList<>();
    String sql = "SELECT Name FROM division";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("Name"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
}