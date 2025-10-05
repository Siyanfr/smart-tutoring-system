// Admin.java - Administrator user class
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Admin extends User {
    private List<User> systemUsers;
    private Map<String, Object> systemData;

    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password);
        this.systemUsers = new ArrayList<>();
        this.systemData = new HashMap<>();
    }

    public void addUser(User user) {
        systemUsers.add(user);
        System.out.println("Admin " + name + " added user: " + user.getName() + " (" + user.getUserType() + ")");
    }

    public void removeUser(User user) {
        if (systemUsers.remove(user)) {
            System.out.println("Admin " + name + " removed user: " + user.getName());
        } else {
            System.out.println("User not found in system.");
        }
    }

    public void viewAllUsers() {
        System.out.println("\n=== System Users ===");
        for (User user : systemUsers) {
            System.out.println("- " + user.getName() + " (" + user.getUserType() + ") - " + user.getEmail());
        }
    }

    public void viewSystemData() {
        System.out.println("\n=== System Data ===");
        System.out.println("Total Users: " + systemUsers.size());

        long studentCount = systemUsers.stream().filter(u -> u instanceof Student).count();
        long tutorCount = systemUsers.stream().filter(u -> u instanceof Tutor).count();
        long adminCount = systemUsers.stream().filter(u -> u instanceof Admin).count();

        System.out.println("Students: " + studentCount);
        System.out.println("Tutors: " + tutorCount);
        System.out.println("Admins: " + adminCount);
    }

    // Getters
    public List<User> getSystemUsers() { return systemUsers; }
    public Map<String, Object> getSystemData() { return systemData; }

    @Override
    public String getUserType() {
        return "Admin";
    }
}