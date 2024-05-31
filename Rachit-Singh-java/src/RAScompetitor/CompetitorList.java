package RAScompetitor;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CompetitorList {
    private List<RAScompetitor> competitors;

    public CompetitorList() {
        competitors = new ArrayList<>();
    }

    public void addCompetitor(RAScompetitor competitor) {
        competitors.add(competitor);
    }

    public List<RAScompetitor> getCompetitors() {
        return competitors;
    }

    public void loadFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 8) continue; // Ensure there are enough columns

                try {
                    int competitorNumber = Integer.parseInt(values[0]);
                    String name = values[1];
                    int age = Integer.parseInt(values[2]);
                    String email = values[3];
                    String category = values[4];
                    String level = values[5];
                    String country = values[6];
                    int[] scores = new int[values.length - 7];
                    for (int i = 7; i < values.length; i++) {
                        scores[i - 7] = Integer.parseInt(values[i]);
                    }

                    RAScompetitor competitor = new RAScompetitor(competitorNumber, name, age, email, category, level, country, scores);
                    addCompetitor(competitor);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RAScompetitor getHighestScoringCompetitor() {
        return Collections.max(competitors, Comparator.comparing(RAScompetitor::getOverallScore));
    }

    public double getAverageOverallScore() {
        return competitors.stream()
                .mapToDouble(RAScompetitor::getOverallScore)
                .average()
                .orElse(0.0);
    }

    public double getMinOverallScore() {
        return competitors.stream()
                .mapToDouble(RAScompetitor::getOverallScore)
                .min()
                .orElse(0.0);
    }

    public double getMaxOverallScore() {
        return competitors.stream()
                .mapToDouble(RAScompetitor::getOverallScore)
                .max()
                .orElse(0.0);
    }

    public long getTotalCompetitors() {
        return competitors.size();
    }

    public Map<Integer, Long> getScoreFrequency() {
        Map<Integer, Long> frequencyMap = new HashMap<>();
        competitors.forEach(competitor -> {
            for (int score : competitor.getScores()) {
                frequencyMap.put(score, frequencyMap.getOrDefault(score, 0L) + 1);
            }
        });
        return frequencyMap;
    }

    public void generateReport(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("Competitors Report");
            writer.println("==================");

            // Competitors table
            writer.println("Table of Competitors:");
            for (RAScompetitor competitor : competitors) {
                writer.println(competitor.getFullDetails());
            }

            // Highest scoring competitor
            RAScompetitor highestScorer = getHighestScoringCompetitor();
            writer.println("\nCompetitor with the Highest Overall Score:");
            writer.println(highestScorer.getFullDetails());

            // Summary statistics
            writer.println("\nSummary Statistics:");
            writer.println("Total Competitors: " + getTotalCompetitors());
            writer.println("Average Overall Score: " + getAverageOverallScore());
            writer.println("Minimum Overall Score: " + getMinOverallScore());
            writer.println("Maximum Overall Score: " + getMaxOverallScore());

            // Frequency report
            writer.println("\nFrequency Report:");
            Map<Integer, Long> scoreFrequency = getScoreFrequency();
            scoreFrequency.forEach((score, frequency) -> writer.println("Score " + score + ": " + frequency + " times"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidCompetitorNumber(int competitorNumber) {
        return competitors.stream().anyMatch(c -> c.getCompetitorNumber() == competitorNumber);
    }

    public RAScompetitor getCompetitorByNumber(int competitorNumber) {
        return competitors.stream()
                .filter(c -> c.getCompetitorNumber() == competitorNumber)
                .findFirst()
                .orElse(null);
    }

    // Example usage
    public static void main(String[] args) {
        CompetitorList competitorList = new CompetitorList();
        competitorList.loadFromCSV("RunCompetitor.csv");

        for (RAScompetitor competitor : competitorList.getCompetitors()) {
            System.out.println(competitor.getFullDetails());
        }

        // Generate report
        competitorList.generateReport("CompetitorsReport.txt");

        // Check if a competitor number is valid
        int competitorNumber = 100;
        if (competitorList.isValidCompetitorNumber(competitorNumber)) {
            System.out.println("Valid competitor number: " + competitorNumber);
            System.out.println(competitorList.getCompetitorByNumber(competitorNumber).getShortDetails());
        } else {
            System.out.println("Invalid competitor number: " + competitorNumber);
        }
    }
}
