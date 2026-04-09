import java.util.Scanner;
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
                    System.out.print("Enter employee ID: ");
                    int empID = scanner.nextInt();
                    if (empID == 0) {
                        System.out.println("Exiting...");
                        break;
                    }
                    EmployeeService.viewEmployee(empID); // View employee with ID 1
                }
            //Employee view
            } else if (x == 2) {
                System.out.print("Enter your employee ID: ");
                int empID = scanner.nextInt();
                System.out.print("Enter your employee Name: ");
                String name = scanner.next();
                System.out.print("Enter your employee SSN: ");
                String ssn = scanner.next();
                EmployeeService.ViewInfo(empID, name, ssn); // View ID 1, Snoopy, 111-11-1111

            }
        } finally {
            scanner.close();
            }
        }
    }
