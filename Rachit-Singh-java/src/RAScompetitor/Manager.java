package RAScompetitor;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Manager {
    private String name;
    private CompetitorList competitorList;

    // Constructor
    public Manager(String name) {
        this.name = name;
        this.competitorList = new CompetitorList();
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void run() {
        // Load competitors from CSV
        competitorList.loadFromCSV("RunCompetitor.csv");


        // Display a menu to the user
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            printMainMenu();
            choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    displayCompetitors();
                    break;
                case 2:
                    displayCompetitorDetailsByNumber(scanner);
                    break;
                case 3:
                    writeFinalReportToFile("FinalReport.txt");
                    break;
                case 4:
                    System.out.println("More options can be added here.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private void printMainMenu() {
        System.out.println("==== Competitor Management System ====");
        System.out.println("1. Display Competitors");
        System.out.println("2. Display Competitor Details by Number");
        System.out.println("3. Write Final Report to File");
        System.out.println("4. Display Score Frequency Report");
        System.out.println("5. Add More Options");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice(Scanner scanner) {
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return choice;
    }

    private void displayCompetitors() {
        for (RAScompetitor competitor : competitorList.getCompetitors()) {
            System.out.println(competitor.getShortDetails());
        }
    }

    private void displayCompetitorDetailsByNumber(Scanner scanner) {
        System.out.print("Enter competitor number: ");
        int competitorNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        RAScompetitor competitor = competitorList.getCompetitorByNumber(competitorNumber);

        if (competitor != null) {
            System.out.println(competitor.getFullDetails());
        } else {
            System.out.println("Competitor not found.");
        }
    }


    private void writeFinalReportToFile(String fileName) {
        competitorList.generateReport(fileName);
        System.out.println("Final report written to file: " + fileName);
    }

    public static void main(String[] args) {
        Manager manager = new Manager("John Doe");
        manager.run();
    }
}
