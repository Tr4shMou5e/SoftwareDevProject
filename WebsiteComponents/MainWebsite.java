import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWebsite extends JFrame implements ActionListener {

    public static void main(String[] args)
    {
        // everything indented is apart of LoginFrame
        JFrame loginFrame = new JFrame();

            JPanel p1 = new JPanel();
            p1.setLayout(new GridBagLayout()); // center everything
            GridBagConstraints gbc = new GridBagConstraints();

            JLabel l1 = new JLabel("Enter valid Empid and Password");
            JButton logonButton = new JButton("Login");
            JPasswordField password = new JPasswordField(10);
            JTextField username = new JTextField(10);

            gbc.insets = new Insets(10, 10, 10, 10); // spacing

            // Row 0 - Label
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            p1.add(l1, gbc);

            // Row 1 - Username
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            p1.add(new JLabel("Username:"), gbc);

            gbc.gridx = 1;
            p1.add(username, gbc);

            // Row 2 - Password
            gbc.gridx = 0;
            gbc.gridy = 2;
            p1.add(new JLabel("Password:"), gbc);

            gbc.gridx = 1;
            p1.add(password, gbc);

            // Row 3 - Button
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            p1.add(logonButton, gbc);

            loginFrame.add(p1);
            loginFrame.setSize(800, 900);
            loginFrame.setLocationRelativeTo(null); // center window
            loginFrame.setVisible(true);
    }
}
