import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Authenticate extends JFrame implements ActionListener {

    private JLabel empIdLabel;
    private JLabel passwordLabel;

    private JTextField empIdField;
    private JPasswordField passwordField;

    private JButton createUserButton;
    private JButton loginButton;

    private UserPass dao;

    public Authenticate() {
        dao = new UserPass();

        setTitle("Employee Authentication System");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 2, 10, 10));

        empIdLabel = new JLabel("Employee ID:");
        passwordLabel = new JLabel("Password:");

        empIdField = new JTextField();
        passwordField = new JPasswordField();

        createUserButton = new JButton("Create User");
        loginButton = new JButton("Login");

        createUserButton.addActionListener(this);
        loginButton.addActionListener(this);

        add(empIdLabel);
        add(empIdField);
        add(passwordLabel);
        add(passwordField);
        add(createUserButton);
        add(loginButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String empIdText = empIdField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (empIdText.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employee ID and Password.");
            return;
        }

        int empId;

        try {
            empId = Integer.parseInt(empIdText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Employee ID must be a number.");
            return;
        }

        if (e.getSource() == createUserButton) {
            dao.createUser(empId, password);
            JOptionPane.showMessageDialog(this, "Create User button clicked.");

        } else if (e.getSource() == loginButton) {
            String storedHash = dao.getHashedPasswordByEmpId(empId);

            if (storedHash == null) {
                JOptionPane.showMessageDialog(this, "Employee ID not found.");
            } else {
                boolean valid = PasswordUtil.verifyPassword(password, storedHash);

                if (valid) {
                    if (PasswordUtil.isHRPassword(password)) {
                        JOptionPane.showMessageDialog(this, "Login successful. Access granted: HR Admin");
                    } else if (PasswordUtil.isGeneralEmployeePassword(password)) {
                        JOptionPane.showMessageDialog(this, "Login successful. Access granted: General Employee");
                    } else {
                        JOptionPane.showMessageDialog(this, "Password format invalid.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid login credentials.");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Authenticate();
    }
}