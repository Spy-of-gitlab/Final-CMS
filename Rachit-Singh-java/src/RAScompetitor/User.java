package RAScompetitor;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String role;

    // Constructor
    public User(int userID, String userName, String password, String role) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    // Getter and Setter for userID
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Getter and Setter for userName
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method to login
    public boolean login(String userName, String password) {
        if (this.userName.equals(userName) && this.password.equals(password)) {
            System.out.println("Login successful.");
            return true;
        }
        System.out.println("Login failed.");
        return false;
    }

    // Method to logout
    public void logout() {
        System.out.println("User logged out.");
    }

    // Method to update password
    public boolean updatePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            System.out.println("Password updated successfully.");
            return true;
        }
        System.out.println("Password update failed.");
        return false;
    }

    public static class Main {
        public static void main(String[] args) {
            // Example usage of the classes

            // Create permissions
            Permission viewScores = new Permission(1, "View Scores");
            Permission editScores = new Permission(2, "Edit Scores");

            // Create roles
            Role adminRole = new Role(1, "Admin");
            Role userRole = new Role(2, "User");

            // Define permissions for roles
            adminRole.definePermissions();
            userRole.definePermissions();

            // Create users
            User admin = new User(1, "admin", "password", "Admin");
            User user = new User(2, "user", "password", "User");

            // Create staff
            AccessControlManager.Staff staff = new AccessControlManager.Staff(1, 3, "staff", "password", "Staff");

            // Use AccessControlManager
            AccessControlManager acm = new AccessControlManager();
            acm.checkPermission(admin, "View Scores");
            acm.grantPermission(adminRole, viewScores);
            acm.revokePermission(userRole, editScores);

            // Staff entering and managing scores
            staff.enterScores(100, 95.5f);
            staff.manageCompetitors(100);
        }
    }
}
