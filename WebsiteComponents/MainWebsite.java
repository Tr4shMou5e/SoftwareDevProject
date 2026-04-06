import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWebsite extends JFrame implements ActionListener {
    
    public static void main(String[] args)
    {
        
        // Creating instance of loginFrame
        JFrame loginFrame = new JFrame(); 
            JPanel p1 = new JPanel();
            JLabel l1 = new JLabel("Enter valid Empid and Password");
            l1.setBounds(120, 50, 220, 80);
            JButton logonButton = new JButton("Login");
            JTextField password = new JTextField();
            JTextField username = new JTextField();
            username.setBounds(150, 100, 220, 30);
            password.setBounds(150, 150, 220, 30);
            logonButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Logic handler idea
                    // if password and empid is valid, check if empid is for HRadmin or if it is for general employee; switch to either frames
                    // else return error and make user try to enter data in again
                }
            });
            logonButton.setBounds(150, 200, 220, 50);
            

            // Panel for layouts
           
            p1.add(password);
            p1.add(username);
            p1.add(logonButton);
            p1.add(l1);

            loginFrame.add(p1);


            loginFrame.setSize(700, 800);
            p1.setBounds(0,0,700,800);
            p1.setLayout(null);
            loginFrame.setVisible(true);

    
        
    }

}
