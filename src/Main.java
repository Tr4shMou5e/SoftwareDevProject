import java.util.Scanner;

//TESTING FUNCTIONS
public class Main {
    public static void main(String[] args) {
        // Example usage of EmployeeService
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter 1 for HR view, 2 for Employee view: ");
            int x = scanner.nextInt();
            //HR view
            if (x == 1) { 
                while (true) {
                    System.out.println("\n--- HR Menu ---");
                    System.out.println("1. View Employee Details");
                    System.out.println("2. View Total Pay by Division");
                    System.out.println("3. View Total Pay by Job Title");
                    System.out.println("0. Exit");
                    System.out.print("Select option: ");
                    int choice = scanner.nextInt();
                    
                    if (choice == 1) {
                        while (true) {
                        System.out.print("Enter employee ID: ");
                        int empID = scanner.nextInt();
                        if (empID == 0) {
                            System.out.println("Exiting...");
                            break;
                        }
                        EmployeeService.viewEmployee(empID);
                        }
                    } else if (choice == 2) {
                        System.out.println("\n--- Total Pay by Division ---");
                        TotalPayByDivision.viewTotalPayByDivision();
                        TotalMontlyPayByDivison.viewTotalMonthlyPayByDivision(1,2026);
                    } else if (choice == 3) {
                        System.out.println("\n--- Total Pay by Job Title ---");
                        TotalPayByJobTitle.viewTotalPayByJobTitle();
                        TotalMontlyPayByJobTitle.viewTotalMonthlyPayByJobTitle(1,2026);
                    } else if (choice == 0) {
                        System.out.println("Exiting HR view...");
                        break;
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                }
            //Employee view
            } else if (x == 2) {
                System.out.print("Enter your employee ID: ");
                int empID = scanner.nextInt();
                EmployeeService.ViewInfo(empID); // View ID 1, Snoopy, 111-11-1111
                PayStatement.viewPayStatement(empID);

            }
        } finally {
            scanner.close();
            }
        }
    }
