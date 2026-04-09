import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Example usage of EmployeeService
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter employee ID: ");
                int empID = scanner.nextInt();
                if (empID == 0) {
                    System.out.println("Exiting...");
                    break;
                }
                EmployeeService.viewEmployee(empID); // View employee with ID 1
            }
        } finally {
            scanner.close();
            }
        }
    }
