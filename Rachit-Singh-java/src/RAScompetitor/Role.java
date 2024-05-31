package RAScompetitor;

public class Role {
    private int roleID;
    private String roleName;

    // Constructor
    public Role(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    // Getter and Setter for roleID
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    // Getter and Setter for roleName
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // Method to define permissions
    public void definePermissions() {
        System.out.println("Permissions defined for role " + roleName + ".");
    }
}
