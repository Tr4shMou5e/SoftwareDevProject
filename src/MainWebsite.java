
import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class MainWebsite {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 350);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextField username = new JTextField(12);
        JPasswordField password = new JPasswordField(12);
        JButton loginButton = new JButton("Login");

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
        p1.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        p1.add(username, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        p1.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        p1.add(password, gbc);

        // Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        p1.add(loginButton, gbc);

        
        loginButton.addActionListener(e -> {
            if(username.getText().equals("1")){
                loginFrame.dispose(); // close login
            new HRAdminMainFrame(); // open dashboard}
            }
            else if(username.getText().equals("2")){
                loginFrame.dispose(); 
                 new GenEmployeeMainFrame(); 
            }
            
        });

        loginFrame.add(p1);
        loginFrame.setVisible(true);
    }
}
