import java.awt.*;
import javax.swing.*;

public class HR_CreateEmployeePanel extends JPanel {

    JTextField fnameField = new JTextField(10);
    JTextField lnameField = new JTextField(10);
    JTextField emailField = new JTextField(10);
    JTextField salaryField = new JTextField(10);
    JTextField hireDateField = new JTextField(10);

    JTextField ssnField = new JTextField(10);
    JTextField dobField = new JTextField(10);

    JPasswordField passwordField = new JPasswordField(10);

    JButton createBtn = new JButton("Create Employee + User");

    public HR_CreateEmployeePanel() {
        setLayout(new GridLayout(9, 2, 10, 10));

        add(new JLabel("First Name:"));
        add(fnameField);

        add(new JLabel("Last Name:"));
        add(lnameField);

        add(new JLabel("Email:"));
        add(emailField);

        add(new JLabel("Salary:"));
        add(salaryField);

        add(new JLabel("Hire Date (YYYY-MM-DD):"));
        add(hireDateField);

        add(new JLabel("SSN (XXX-XX-XXXX):"));
        add(ssnField);

        add(new JLabel("DOB (YYYY-MM-DD):"));
        add(dobField);

        add(new JLabel("Password:"));
        add(passwordField);

        add(new JLabel(""));
        add(createBtn);

        createBtn.addActionListener(e -> createEmployeeAndUser());
    }

   private void createEmployeeAndUser() {
        try {
            String fname = fnameField.getText().trim();
            String lname = lnameField.getText().trim();
            String email = emailField.getText().trim();
            String salaryText = salaryField.getText().trim();
            String hireDate = hireDateField.getText().trim();
            String ssn = ssnField.getText().trim();
            String dob = dobField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() ||
                salaryText.isEmpty() || hireDate.isEmpty() ||
                ssn.isEmpty() || dob.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            double salary;
            try {
                salary = Double.parseDouble(salaryText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Salary must be a number.");
                return;
            }

            int empID = EmployeeCreateService.createEmployee(
                fname, lname, email, salary, hireDate, ssn, dob
            );

            if (empID == -1) {
                JOptionPane.showMessageDialog(this, "Employee creation failed.");
                return;
            }

            UserPass dao = new UserPass();
            dao.createUser(empID, password);

            JOptionPane.showMessageDialog(this, "Employee created! ID: " + empID);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error creating employee.");
            ex.printStackTrace();
        }
    }
}
