// User.java - Base class for all users
public abstract class User {
    protected int userId;
    protected String name;
    protected String email;
    protected String passwordHash;

    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = hashPassword(password);
    }

    private String hashPassword(String password) {
        // Simple hash simulation (in production, use proper hashing like BCrypt)
        return Integer.toString(password.hashCode());
    }

    public boolean login(String password) {
        return this.passwordHash.equals(hashPassword(password));
    }

    public void logout() {
        System.out.println(name + " has been logged out.");
    }

    // Getters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; } // For CSV file handling

    public abstract String getUserType();
}