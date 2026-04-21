import java.sql.*;

public class RemoveEmployee {

    public EmployeeInfo findEmployeeById(int empId) {
        String sql = "SELECT empID, Fname, Lname, email, HireDate, Salary " +
                     "FROM employees WHERE empID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EmployeeInfo emp = new EmployeeInfo();

                    emp.setEmpID(rs.getInt("empID"));
                    emp.setFname(rs.getString("Fname"));
                    emp.setLname(rs.getString("Lname"));
                    emp.setEmail(rs.getString("email"));
                    emp.setHireDate(rs.getString("HireDate"));
                    emp.setSalary(rs.getDouble("Salary"));

                    return emp;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteEmployeeById(int empId) {
        Connection conn = null;

        try {
            // check if employee exists
            if (findEmployeeById(empId) == null) {
                return false;
            }

            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // delete from child tables FIRST
            deleteByEmpId(conn, "DELETE FROM payroll WHERE empID = ?", empId);
            deleteByEmpId(conn, "DELETE FROM users WHERE emp_id = ?", empId);
            deleteByEmpId(conn, "DELETE FROM employee_division WHERE empID = ?", empId);
            deleteByEmpId(conn, "DELETE FROM employee_job_titles WHERE empID = ?", empId);

            // delete from employees
            String deleteEmployeeSql = "DELETE FROM employees WHERE empID = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteEmployeeSql)) {
                ps.setInt(1, empId);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected == 0) {
                    conn.rollback();
                    return false;
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return false;

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteByEmpId(Connection conn, String sql, int empId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ps.executeUpdate();
        }
    }
}
