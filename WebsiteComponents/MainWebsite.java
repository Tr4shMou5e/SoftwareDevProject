import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWebsite extends JFrame implements ActionListener {
    
    public static void main(String[] args)
    {
        // Creating instance of loginFrame
        JFrame loginFrame = new JFrame();
            Label l1 = new Label("Enter valid Empid and Password");
            l1.setBounds(100, 50, 120, 80);
            loginFrame.add(l1);
            JButton logonButton = new JButton("Login");
            text password = new text();
            text username = new text();
            logonButton.addActionListener(password, username);
            logonButton.setBounds(150, 200, 220, 50);
            loginFrame.add(logonButton);
            loginFrame.setSize(500, 600);
            loginFrame.setLayout(null);
            loginFrame.setVisible(true);

    
        
    }

    public void actionPerformed(ActionEvent e)
    {
        
    }
}
