import java.sql.*;

public class EmployeeCreateService {

    public static int createEmployee(String fname, String lname, String email,
                                     double salary, String hireDate,
                                     String ssn, String dob) {

        String getMaxId = "SELECT MAX(empID) FROM employees";

        String insert = """
            INSERT INTO employees 
            (empID, Fname, Lname, email, Salary, HireDate, SSN, DOB, addressID)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps1 = conn.prepareStatement(getMaxId);
             ResultSet rs = ps1.executeQuery()) {

            int newID = 1;
            if (rs.next()) {
                newID = rs.getInt(1) + 1;
            }

            PreparedStatement ps2 = conn.prepareStatement(insert);

            ps2.setInt(1, newID);
            ps2.setString(2, fname);
            ps2.setString(3, lname);
            ps2.setString(4, email);
            ps2.setDouble(5, salary);
            ps2.setString(6, hireDate);
            ps2.setString(7, ssn);
            ps2.setString(8, dob);
            ps2.setInt(9, 1); // all employees newly created have a default address

            ps2.executeUpdate();

            return newID;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
