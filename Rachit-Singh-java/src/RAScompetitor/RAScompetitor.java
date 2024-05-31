package RAScompetitor;

import java.util.Arrays;

public class RAScompetitor {
    private int competitorNumber;
    private String name;
    private int age;
    private String email;
    private String category;
    private String level;
    private String country;
    private int[] scores;

    // Constructor
    public RAScompetitor(int competitorNumber, String name, int age, String email, String category, String level, String country, int[] scores) {
        this.competitorNumber = competitorNumber;
        this.name = name;
        this.age = age;
        this.email = email;
        this.category = category;
        this.level = level;
        this.country = country;
        this.scores = scores;
    }

    // Getter and Setter for competitorNumber
    public int getCompetitorNumber() {
        return competitorNumber;
    }

    public void setCompetitorNumber(int competitorNumber) {
        this.competitorNumber = competitorNumber;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and Setter for level
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    // Getter and Setter for country
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Getter and Setter for scores
    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    // Method to register the competitor
    public void register() {
        // Registration logic here
        System.out.println(this.name + " has been registered.");
    }

    // Method to update competitor details
    public void updateDetails(String name, Integer age, String email, String category, String level, String country, int[] scores) {
        if (name != null) {
            this.name = name;
        }
        if (age != null) {
            this.age = age;
        }
        if (email != null) {
            this.email = email;
        }
        if (category != null) {
            this.category = category;
        }
        if (level != null) {
            this.level = level;
        }
        if (country != null) {
            this.country = country;
        }
        if (scores != null) {
            this.scores = scores;
        }
        System.out.println("Details updated successfully.");
    }

    // Method to get the overall score
    public double getOverallScore() {
        if (scores == null || scores.length == 0) {
            return 0.0;
        }

        // If there are fewer than 3 scores, use a simple average
        if (scores.length < 3) {
            return Arrays.stream(scores).average().orElse(0.0);
        }

        // Calculate the overall score, excluding the highest and lowest scores
        int sum = 0;
        int max = scores[0];
        int min = scores[0];
        for (int score : scores) {
            sum += score;
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
        }
        sum -= (max + min); // Remove the highest and lowest scores
        double overallScore = sum / (double) (scores.length - 2);
        return Math.min(Math.max(overallScore, 0.0), 5.0); // Ensure the score is between 0 and 5
    }

    // Method to get full details of the competitor
    public String getFullDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Competitor number ").append(competitorNumber)
                .append(", name ").append(name)
                .append(", country ").append(country)
                .append(".\n")
                .append(name).append(" is a ").append(level)
                .append(" aged ").append(age)
                .append(" and received these scores: ")
                .append(Arrays.toString(scores).replaceAll("[\\[\\]]", ""))
                .append(".\nThis gives an overall score of ")
                .append(getOverallScore())
                .append(".");
        return details.toString();
    }

    // Method to get short details of the competitor
    public String getShortDetails() {
        StringBuilder initials = new StringBuilder();
        for (String part : name.split(" ")) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }
        return "CN " + competitorNumber + " (" + initials + ") has an overall score of " + getOverallScore() + ".";
    }

    // Example usage
    public static void main(String[] args) {
        int[] initialScores = {5, 4, 5, 4, 3}; // Example scores between 0 and 5
        RAScompetitor competitor = new RAScompetitor(100, "Keith John Talbot", 21, "keith.john@example.com", "Senior", "Novice", "UK", initialScores);
        competitor.register();
        competitor.updateDetails("Keith John Smith", null, "keith.john.smith@example.com", null, null, null, null);

        // Using getter and setter methods
        System.out.println("Name: " + competitor.getName());
        competitor.setName("Jane Doe");
        System.out.println("Updated Name: " + competitor.getName());

        // Getting full and short details
        System.out.println(competitor.getFullDetails());
        System.out.println(competitor.getShortDetails());
    }
}
