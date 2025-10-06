// MenuManager.java - Base class for menu management
public abstract class MenuManager {
    protected User currentUser;

    public MenuManager(User currentUser) {
        this.currentUser = currentUser;
    }

    public abstract void displayMenu();

    public User getCurrentUser() {
        return currentUser;
    }
}