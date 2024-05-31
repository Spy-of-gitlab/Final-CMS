package RAScompetitor;


public class Score {
    private int scoreID;
    private int competitorNumber;
    private float value;
    private String type;

    // Constructor
    public Score(int scoreID, int competitorNumber, float value, String type) {
        this.scoreID = scoreID;
        this.competitorNumber = competitorNumber;
        this.value = value;
        this.type = type;
    }

    // Getter and Setter for scoreID
    public int getScoreID() {
        return scoreID;
    }

    public void setScoreID(int scoreID) {
        this.scoreID = scoreID;
    }

    // Getter and Setter for competitorNumber
    public int getCompetitorNumber() {
        return competitorNumber;
    }

    public void setCompetitorNumber(int competitorNumber) {
        this.competitorNumber = competitorNumber;
    }

    // Getter and Setter for value
    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Method to record a score
    public void recordScore(int competitorNumber, float score) {
        this.competitorNumber = competitorNumber;
        this.value = score;
        System.out.println("Score recorded successfully.");
    }

    // Method to manage a competition
    public void manageCompetition(int competitorNumber) {
        // Logic to manage competition for a competitor
        System.out.println("Managing competition for competitor number " + competitorNumber + ".");
    }

    // Method to generate a report
    public String generateReport() {
        // Logic to generate a report
        System.out.println("Report generated successfully.");
        return "Report Content"; // Returning a dummy report content for this example
    }

    // Method to print a report
    public void printReport() {
        String report = generateReport();
        // Logic to print a report
        System.out.println("Printing report: " + report);
    }

    // Example usage
    public static void main(String[] args) {
        Score score = new Score(1, 100, 95.5f, "Final");
        score.recordScore(100, 98.0f);
        score.manageCompetition(100);

        score.printReport();
    }
}
