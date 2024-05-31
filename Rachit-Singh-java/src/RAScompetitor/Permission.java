package RAScompetitor;

public class Permission {
    private int permissionID;
    private String description;

    // Constructor
    public Permission(int permissionID, String description) {
        this.permissionID = permissionID;
        this.description = description;
    }

    // Getter and Setter for permissionID
    public int getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
