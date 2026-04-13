import java.util.Scanner;

public class Authenticate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserPass dao = new UserPass();

        while (true) {
            System.out.println("\n1. Create User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                dao.createUser(empId, password);

            } else if (choice == 2) {
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Password: ");
                String inputPassword = scanner.nextLine();

                String storedHash = dao.getHashedPasswordByEmpId(empId);

                if (storedHash == null) {
                    System.out.println("Employee ID not found.");
                } else {
                    boolean valid = PasswordUtil.verifyPassword(inputPassword, storedHash);

                    if (valid) {
                        System.out.println("Login successful.");

                        if (PasswordUtil.isHRPassword(inputPassword)) {
                            System.out.println("Access granted: HR Admin");
                        } else if (PasswordUtil.isGeneralEmployeePassword(inputPassword)) {
                            System.out.println("Access granted: General Employee");
                        } else {
                            System.out.println("Password format invalid.");
                        }
                    } else {
                        System.out.println("Invalid login credentials.");
                    }
                }

            } else if (choice == 3) {
                System.out.println("Goodbye.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}