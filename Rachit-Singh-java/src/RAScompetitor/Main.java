package RAScompetitor;

public class Main {
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
        Staff staff = new Staff(1, 3, "staff", "password", "Staff");

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
