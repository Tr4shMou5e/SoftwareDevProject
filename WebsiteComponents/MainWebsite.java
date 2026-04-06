import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWebsite extends JFrame implements ActionListener {
    
    public static void main(String[] args)
    {
        
        // Creating instance of loginFrame
        JFrame loginFrame = new JFrame();
            JLabel l1 = new JLabel("Enter valid Empid and Password");
            l1.setBounds(100, 50, 120, 80);
            loginFrame.add(l1);
            JButton logonButton = new JButton("Login");
            JTextField password = new JTextField();
            JTextField username = new JTextField();
            logonButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Logic handler
                }
            });
            logonButton.setBounds(150, 200, 220, 50);
            

            // Panel for layouts
            JPanel p1 = new JPanel();
            p1.add(password);
            p1.add(username);
            p1.add(logonButton);

            loginFrame.add(p1);


            loginFrame.setSize(700, 800);
            p1.setBounds(0,0,700,800);
            p1.setLayout(null);
            loginFrame.setVisible(true);

    
        
    }

}
