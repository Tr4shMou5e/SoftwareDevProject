

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HRAdminMainFrame extends JFrame {
    JPanel content = new JPanel();
    // methods for each component HR Admin needs for CRUD functionality

    void showCreateEmployee() {
        content.removeAll();

        content.add(new HR_CreateEmployeePanel(), BorderLayout.CENTER);

        content.revalidate();
        content.repaint();
    }
    void showSearchemp() {
        content.removeAll();


        content.add(new HR_SearchPanel(), BorderLayout.CENTER);


        content.revalidate();
        content.repaint();
    }


    void showSalary() {
        content.removeAll();


        content.add(new HR_SalaryPanel(), BorderLayout.CENTER);


        content.revalidate();
        content.repaint();
    }

    void showReports() {
        content.removeAll();


        content.add(new HR_ReportsPanel(), BorderLayout.CENTER);


        content.revalidate();
        content.repaint();
    }
    
    public HRAdminMainFrame() {
        setTitle("HR Admin Dashboard");
        setSize(1200, 1200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(30, 30, 30));
        sidebar.setLayout(new GridLayout(6, 1, 0, 10));

        JButton createEmpBtn = new JButton("Create Employee");
        JButton searchEmpBtn = new JButton("Search & Update Employee");
        JButton salaryBtn = new JButton("Salary Adjustment");
        JButton reportsBtn = new JButton("Reports");

        sidebar.add(createEmpBtn); 
        sidebar.add(searchEmpBtn);
        sidebar.add(salaryBtn);
        sidebar.add(reportsBtn);

        // Main Content Area
        
        content.setBackground(new Color(45, 45, 45));
        content.setLayout(new BorderLayout());

        JLabel label = new JLabel("Dashboard", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(Color.WHITE);

        content.add(label, BorderLayout.CENTER);

        createEmpBtn.setActionCommand("CREATEEMP");
        searchEmpBtn.setActionCommand("SEARCHEMP");
        salaryBtn.setActionCommand("SALARY");
        reportsBtn.setActionCommand("REPORTS");


        
        ActionListener menuHandler = e -> {
            switch (e.getActionCommand()) {
                case "SEARCHEMP":
                    showSearchemp();
                    break;
                case "SALARY":
                    showSalary();
                    break;
                case "REPORTS":
                    showReports();
                    break;
                case "CREATEEMP":
                    showCreateEmployee();
                    break;
            }
        };

        searchEmpBtn.addActionListener(menuHandler);
        salaryBtn.addActionListener(menuHandler);
        reportsBtn.addActionListener(menuHandler);
        createEmpBtn.addActionListener(menuHandler);
        
        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        setVisible(true);
    }
}
