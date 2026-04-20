
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
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("Login");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel p1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        empIdField = new JTextField(12);
        passwordField = new JPasswordField(12);

        loginButton = new JButton("Login");
        createUserButton = new JButton("Create User");

        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        p1.add(title, gbc);

        // Username
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        p1.add(new JLabel("Employee ID:"), gbc);

        gbc.gridx = 1;
        p1.add(empIdField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        p1.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        p1.add(passwordField, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        p1.add(loginButton, gbc);

        gbc.gridy = 4;
        p1.add(createUserButton, gbc);

        // Add panel
        add(p1);

        // listeners
        loginButton.addActionListener(this);
        createUserButton.addActionListener(this);

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
                    this.dispose();

                    if (PasswordUtil.isHRPassword(password)) {
                        new HRAdminMainFrame();
                    } 
                    else if (PasswordUtil.isGeneralEmployeePassword(password)) {
                        new GenEmployeeMainFrame(empId);
                    } 
                    else {
                        JOptionPane.showMessageDialog(this, "Password format invalid.");
                    }
                }
                else {
                JOptionPane.showMessageDialog(this, "Invalid login credentials.");
                 }
            }
        }
    }

    public static void main(String[] args) {
        new Authenticate();
    }
}