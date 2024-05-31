package RAScompetitor;

public class Staff extends User {
    private int staffID;

    // Constructor
    public Staff(int staffID, int userID, String userName, String password, String role) {
        super(userID, userName, password, role);
        this.staffID = staffID;
    }

    // Getter and Setter for staffID
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    // Method to enter scores
    public void enterScores(int competitorNumber, float score) {
        System.out.println("Score " + score + " entered for competitor number " + competitorNumber + ".");
    }

    // Method to manage competitors
    public void manageCompetitors(int competitorNumber) {
        System.out.println("Managing competitor number " + competitorNumber + ".");
    }
}
