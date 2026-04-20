

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GenEmployeeMainFrame extends JFrame {
    JPanel content = new JPanel();
    private  int loggedInEmpID;
    // methods for each component HR Admin needs for CRUD functionality
    void showSearchemp() {
        content.removeAll();

        // user can only ever search their empid
        content.add(new EMP_SearchPanel(loggedInEmpID, true), BorderLayout.CENTER);


        content.revalidate();
        content.repaint();
    }

    void showReports() {
        content.removeAll();


        content.add(new EMP_ReportsPanel(loggedInEmpID), BorderLayout.CENTER);


        content.revalidate();
        content.repaint();
    }
    
    public GenEmployeeMainFrame(int empID) {
        this.loggedInEmpID = empID;
        setTitle("Employee Dashboard");
        setSize(1200, 1200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(30, 30, 30));
        sidebar.setLayout(new GridLayout(6, 1, 0, 10));

        JButton searchEmpBtn = new JButton("Search Employee");
        JButton reportsBtn = new JButton("Reports");

        sidebar.add(searchEmpBtn);
        sidebar.add(reportsBtn);

        // Main Content Area
        
        content.setBackground(new Color(45, 45, 45));
        content.setLayout(new BorderLayout());

        JLabel label = new JLabel("Dashboard", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(Color.WHITE);

        content.add(label, BorderLayout.CENTER);

        
        searchEmpBtn.setActionCommand("SEARCHEMP");
        reportsBtn.setActionCommand("REPORTS");
        


        
        ActionListener menuHandler = e -> {
            switch (e.getActionCommand()) {
                case "SEARCHEMP":
                    showSearchemp();
                    break;
                case "REPORTS":
                    showReports();
                    break;
            }
        };

        searchEmpBtn.addActionListener(menuHandler);
        reportsBtn.addActionListener(menuHandler);
        
        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        setVisible(true);
    }
}
