package RAScompetitor;

import java.util.Date;

public class Competition {
    private int competitionID;
    private String name;
    private String type;
    private Date startDate;
    private Date endDate;

    // Constructor
    public Competition(int competitionID, String name, String type, Date startDate, Date endDate) {
        this.competitionID = competitionID;
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter and Setter for competitionID
    public int getCompetitionID() {
        return competitionID;
    }

    public void setCompetitionID(int competitionID) {
        this.competitionID = competitionID;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for startDate
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // Getter and Setter for endDate
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Method to schedule the competition
    public void scheduleCompetition() {
        // Scheduling logic here
        System.out.println("Competition " + name + " has been scheduled from " + startDate + " to " + endDate + ".");
    }

    // Method to update competition details
    public void updateCompetitionDetails(String name, String type, Date startDate, Date endDate) {
        if (name != null) {
            this.name = name;
        }
        if (type != null) {
            this.type = type;
        }
        if (startDate != null) {
            this.startDate = startDate;
        }
        if (endDate != null) {
            this.endDate = endDate;
        }
        System.out.println("Competition details updated successfully.");
    }

    // Example usage
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date(start.getTime() + (1000 * 60 * 60 * 24 * 7)); // One week later

        Competition competition = new Competition(1, "Annual Sports Meet", "Athletics", start, end);
        competition.scheduleCompetition();
        competition.updateCompetitionDetails("Annual Sports Meet 2024", null, null, null);

        // Using getter and setter methods
        System.out.println("Name: " + competition.getName());
        competition.setName("International Sports Meet");
        System.out.println("Updated Name: " + competition.getName());
    }
}
