// AdminMenuManager.java - Administrator menu interface
import java.util.Scanner;

public class AdminMenuManager extends MenuManager {

    public AdminMenuManager(User currentUser) {
        super(currentUser);
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║      ADMIN DASHBOARD               ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("Welcome, " + currentUser.getName() + "!");
            System.out.println("\n1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. View All Users");
            System.out.println("4. View System Data");
            System.out.println("5. Logout");
            System.out.print("\nSelect an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addUser(scanner);
                        break;
                    case 2:
                        removeUser(scanner);
                        break;
                    case 3:
                        viewAllUsers();
                        break;
                    case 4:
                        viewSystemData();
                        break;
                    case 5:
                        currentUser.logout();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void addUser(Scanner scanner) {
        System.out.println("\n=== Add New User ===");
        System.out.print("User type (1=Student, 2=Tutor, 3=Admin): ");
        int userType = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Admin admin = (Admin) currentUser;
        int newUserId = admin.getSystemUsers().size() + 100;

        User newUser = null;
        switch (userType) {
            case 1:
                System.out.print("Learning style (Visual/Auditory/Kinesthetic): ");
                String learningStyle = scanner.nextLine();
                newUser = new Student(newUserId, name, email, password, learningStyle);
                break;
            case 2:
                newUser = new Tutor(newUserId, name, email, password);
                break;
            case 3:
                newUser = new Admin(newUserId, name, email, password);
                break;
            default:
                System.out.println("Invalid user type.");
                return;
        }

        if (newUser != null) {
            admin.addUser(newUser);
        }
    }

    private void removeUser(Scanner scanner) {
        Admin admin = (Admin) currentUser;
        if (admin.getSystemUsers().isEmpty()) {
            System.out.println("No users in the system.");
            return;
        }

        System.out.println("\n=== Remove User ===");
        for (int i = 0; i < admin.getSystemUsers().size(); i++) {
            User user = admin.getSystemUsers().get(i);
            System.out.println((i + 1) + ". " + user.getName() + " (" + user.getUserType() + ")");
        }

        System.out.print("Select user number to remove (0 to cancel): ");
        int userNum = Integer.parseInt(scanner.nextLine());

        if (userNum > 0 && userNum <= admin.getSystemUsers().size()) {
            User userToRemove = admin.getSystemUsers().get(userNum - 1);
            admin.removeUser(userToRemove);
        }
    }

    private void viewAllUsers() {
        Admin admin = (Admin) currentUser;
        admin.viewAllUsers();
    }

    private void viewSystemData() {
        Admin admin = (Admin) currentUser;
        admin.viewSystemData();
    }
}