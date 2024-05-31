package RAScompetitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompetitorGUI extends JFrame {

    private CompetitorList competitorList;

    public CompetitorGUI() {
        this.competitorList = new CompetitorList();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setTitle("Competitor Management System");

        // Welcome screen
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Competitor Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);


        JButton viewCompetitorsButton = createStyledButton("View Competitors");
        JButton viewAndAlterScoresButton = createStyledButton("View and Amend Scores");
        JButton viewTableSortedButton = createStyledButton("View Table Sorted");
        JButton viewDetailsButton = createStyledButton("Search Details");
        JButton viewFullDetailsButton = createStyledButton("Display Full Details");
        JButton editDetailsButton = createStyledButton("Edit Details");
        JButton removeCompetitorButton = createStyledButton("Remove Competitor");

        viewCompetitorsButton.addActionListener(e -> showCompetitorsTable());
        viewAndAlterScoresButton.addActionListener(e -> viewAndAlterScores());
        viewTableSortedButton.addActionListener(e -> viewTableSorted());
        viewDetailsButton.addActionListener(e -> SearchDetails());
        viewFullDetailsButton.addActionListener(e -> displayFullDetails());
        editDetailsButton.addActionListener(e -> editDetails());
        removeCompetitorButton.addActionListener(e -> deleteCompetitor());


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 20, 20));
        buttonPanel.setBackground(Color.white); // Set background color

        buttonPanel.add(viewCompetitorsButton);
        buttonPanel.add(viewAndAlterScoresButton);
        buttonPanel.add(viewTableSortedButton);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(viewFullDetailsButton);
        buttonPanel.add(editDetailsButton);
        buttonPanel.add(removeCompetitorButton);

        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        getContentPane().add(welcomePanel);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(70, 130, 180)); // Set background color
        button.setForeground(Color.white); // Set text color
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        button.setPreferredSize(new Dimension(250, 50)); // Set button size
        return button;
    }

    private void showCompetitorsTable() {
        // Load competitors from CSV file
        competitorList.loadFromCSV("RunCompetitor.csv");

        // Get the list of competitors from CompetitorList
        List<RAScompetitor> competitors = competitorList.getCompetitors();

        // Create a 2D array to store data for the JTable
        Object[][] data = new Object[competitors.size()][8]; // Assuming 8 columns

        for (int i = 0; i < competitors.size(); i++) {
            RAScompetitor competitor = competitors.get(i);
            data[i][0] = competitor.getCompetitorNumber();
            data[i][1] = competitor.getName();
            data[i][2] = competitor.getAge();
            data[i][3] = competitor.getEmail();
            data[i][4] = competitor.getCategory();
            data[i][5] = competitor.getLevel();
            data[i][6] = competitor.getCountry();
            data[i][7] = competitor.getOverallScore();
        }

        // Create column names
        String[] columnNames = {"Competitor Number", "Name", "Age", "Email", "Category", "Level", "Country", "Overall Score"};

        // Create a DefaultTableModel with the data and column names
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        // Create a JTable
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true); // Enable sorting
        table.setRowHeight(30); // Set table row height for better visibility
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for table
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Set font for table header
        table.getTableHeader().setBackground(new Color(70, 130, 180)); // Set background color for table header
        table.getTableHeader().setForeground(Color.white); // Set text color for table header

        // Create a JScrollPane with improved aesthetics
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Create a new JFrame to display the table
        JFrame tableFrame = new JFrame("Competitor Table");
        tableFrame.getContentPane().setBackground(Color.white); // Set background color
        tableFrame.getContentPane().setLayout(new BorderLayout());
        tableFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        tableFrame.setSize(1000, 600); // Set frame size
        tableFrame.setLocationRelativeTo(null); // Center the frame
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to avoid closing the main frame
        tableFrame.setVisible(true);
    }

    private void viewAndAlterScores() {
        // Load competitors from CSV file
        competitorList.loadFromCSV("RunCompetitor.csv");

        // Get the list of competitors from CompetitorList
        List<RAScompetitor> competitors = competitorList.getCompetitors();

        // Create an array of competitor names for the user to choose
        String[] competitorNames = new String[competitors.size()];
        for (int i = 0; i < competitors.size(); i++) {
            competitorNames[i] = competitors.get(i).getName();
        }

        // Create a custom JPanel to enhance the input dialog
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a label to display the instruction
        JLabel instructionLabel = new JLabel("Choose a competitor to view and alter scores:");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(instructionLabel, BorderLayout.NORTH);

        // Create a JComboBox to display the competitor names
        JComboBox<String> competitorComboBox = new JComboBox<>(competitorNames);
        competitorComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(competitorComboBox, BorderLayout.CENTER);

        // Show the input dialog with the custom panel
        int option = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "View and Alter Scores",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        // Check if the user clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Find the selected competitor
            String selectedCompetitor = (String) competitorComboBox.getSelectedItem();
            RAScompetitor selectedCompetitorObj = competitors.stream()
                    .filter(competitor -> competitor.getName().equals(selectedCompetitor))
                    .findFirst()
                    .orElse(null);

            if (selectedCompetitorObj != null) {
                // Display the current scores
                StringBuilder scoresMessage = new StringBuilder("Current Scores:\n");
                for (int score : selectedCompetitorObj.getScores()) {
                    scoresMessage.append(score).append("\n");
                }

                // Create a panel to hold the input components
                JPanel inputComponentsPanel = new JPanel(new GridLayout(5, 2, 5, 5));

                // Add labels and text fields for each score
                for (int i = 0; i < selectedCompetitorObj.getScores().length; i++) {
                    JLabel scoreLabel = new JLabel("Score " + (i + 1) + ":");
                    JTextField scoreField = new JTextField(String.valueOf(selectedCompetitorObj.getScores()[i]));

                    inputComponentsPanel.add(scoreLabel);
                    inputComponentsPanel.add(scoreField);
                }

                // Show the input dialog with the custom panel
                int alterOption = JOptionPane.showConfirmDialog(
                        this,
                        inputComponentsPanel,
                        "Alter Scores",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                // Check if the user clicked "OK" in the alter dialog
                if (alterOption == JOptionPane.OK_OPTION) {
                    // Update the competitor's scores based on user input
                    for (int i = 0; i < selectedCompetitorObj.getScores().length; i++) {
                        JTextField scoreField = (JTextField) inputComponentsPanel.getComponent(i * 2 + 1);
                        try {
                            int newScore = Integer.parseInt(scoreField.getText().trim());
                            selectedCompetitorObj.getScores()[i] = newScore;
                        } catch (NumberFormatException e) {
                            // Handle invalid input
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Invalid input for scores. Please enter valid integers.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    // Inform the user about the update
                    JOptionPane.showMessageDialog(
                            this,
                            "Scores updated successfully!",
                            "Scores Updated",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Handle if the selected competitor is not found
                JOptionPane.showMessageDialog(
                        this,
                        "Selected competitor not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewTableSorted() {
        // Load competitors from CSV file
        competitorList.loadFromCSV("RunCompetitor.csv");

        // Get the list of competitors from CompetitorList
        List<RAScompetitor> competitors = competitorList.getCompetitors();

        // Create an array of sorting options for the user to choose
        String[] sortingOptions = {"Competitor Number", "Name", "Overall Score"};

        // Display a dialog for the user to choose a sorting option
        String selectedSortingOption = (String) JOptionPane.showInputDialog(
                this,
                "Choose a sorting option:",
                "View Table Sorted",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sortingOptions,
                sortingOptions[0]);

        if (selectedSortingOption != null) {
            // Sort the competitors based on the selected option
            switch (selectedSortingOption) {
                case "Competitor Number":
                    competitors.sort(Comparator.comparingInt(RAScompetitor::getCompetitorNumber));
                    break;
                case "Name":
                    competitors.sort(Comparator.comparing(RAScompetitor::getName));
                    break;
                case "Overall Score":
                    competitors.sort(Comparator.comparingDouble(RAScompetitor::getOverallScore));
                    break;
            }

            // Create a 2D array to store data for the JTable
            Object[][] data = new Object[competitors.size()][8]; // Assuming 8 columns

            for (int i = 0; i < competitors.size(); i++) {
                RAScompetitor competitor = competitors.get(i);
                data[i][0] = competitor.getCompetitorNumber();
                data[i][1] = competitor.getName();
                data[i][2] = competitor.getAge();
                data[i][3] = competitor.getEmail();
                data[i][4] = competitor.getCategory();
                data[i][5] = competitor.getLevel();
                data[i][6] = competitor.getCountry();
                data[i][7] = competitor.getOverallScore();
            }

            // Create column names
            String[] columnNames = {"Competitor Number", "Name", "Age", "Email", "Category", "Level", "Country", "Overall Score"};

            // Create a DefaultTableModel with the data and column names
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

            // Create a JTable
            JTable table = new JTable(tableModel);
            table.setAutoCreateRowSorter(true); // Enable sorting
            table.setRowHeight(30); // Set table row height for better visibility
            table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for table
            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Set font for table header
            table.getTableHeader().setBackground(new Color(70, 130, 180)); // Set background color for table header
            table.getTableHeader().setForeground(Color.white); // Set text color for table header

            // Create a JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

            // Create a new JFrame to display the sorted table
            JFrame sortedTableFrame = new JFrame("Sorted Competitor Table");
            sortedTableFrame.getContentPane().setBackground(Color.white); // Set background color
            sortedTableFrame.getContentPane().setLayout(new BorderLayout());
            sortedTableFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

            sortedTableFrame.setSize(1000, 600); // Set frame size
            sortedTableFrame.setLocationRelativeTo(null); // Center the frame
            sortedTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to avoid closing the main frame
            sortedTableFrame.setVisible(true);
        }
    }

    private void SearchDetails() {
        // Load competitors from CSV file
        competitorList.loadFromCSV("RunCompetitor.csv");

        // Create an array of competitor numbers for the user to choose
        List<RAScompetitor> competitors = competitorList.getCompetitors();
        String[] competitorNumbers = competitors.stream()
                .map(RAScompetitor::getCompetitorNumber)
                .map(String::valueOf)
                .toArray(String[]::new);

        // Create a custom JPanel to enhance the input dialog
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a label to display the instruction
        JLabel instructionLabel = new JLabel("Choose Competitor Number:");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(instructionLabel, BorderLayout.NORTH);

        // Create a JComboBox to display the competitor numbers
        JComboBox<String> competitorComboBox = new JComboBox<>(competitorNumbers);
        competitorComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(competitorComboBox, BorderLayout.CENTER);


        // Show the input dialog with the custom panel
        int option = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "View Competitor Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        // Check if the user clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Find the selected competitor
            String selectedCompetitorNumber = (String) competitorComboBox.getSelectedItem();
            try {
                int competitorNumber = Integer.parseInt(selectedCompetitorNumber);
                RAScompetitor selectedCompetitor = competitors.stream()
                        .filter(comp -> comp.getCompetitorNumber() == competitorNumber)
                        .findFirst()
                        .orElse(null);

                if (selectedCompetitor != null) {
                    // Display the full details of the selected competitor
                    JPanel detailsPanel = new JPanel(new BorderLayout(10, 10));
                    detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    detailsPanel.setBackground(Color.white);

                    // Create a label for each piece of information
                    JLabel nameLabel = new JLabel("Name: " + selectedCompetitor.getName());
                    nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    JLabel ageLabel = new JLabel("Age: " + selectedCompetitor.getAge());
                    ageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel emailLabel = new JLabel("Email: " + selectedCompetitor.getEmail());
                    emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel categoryLabel = new JLabel("Category: " + selectedCompetitor.getCategory());
                    categoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel levelLabel = new JLabel("Level: " + selectedCompetitor.getLevel());
                    levelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel countryLabel = new JLabel("Country: " + selectedCompetitor.getCountry());
                    countryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel scoresLabel = new JLabel("Scores: " + Arrays.toString(selectedCompetitor.getScores()).replaceAll("[\\[\\]]", ""));
                    scoresLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    JLabel overallScoreLabel = new JLabel("Overall Score: " + selectedCompetitor.getOverallScore());
                    overallScoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                    // Add the labels to the panel
                    detailsPanel.add(nameLabel, BorderLayout.NORTH);
                    JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5));
                    infoPanel.setBackground(Color.white);
                    infoPanel.add(ageLabel);
                    infoPanel.add(emailLabel);
                    infoPanel.add(categoryLabel);
                    infoPanel.add(levelLabel);
                    infoPanel.add(countryLabel);
                    infoPanel.add(scoresLabel);
                    infoPanel.add(overallScoreLabel);
                    detailsPanel.add(infoPanel, BorderLayout.CENTER);

                    JOptionPane.showMessageDialog(
                            this,
                            detailsPanel,
                            "Competitor Details",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Competitor not found.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid competitor number. Please enter a valid number.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayFullDetails() {
        // Load competitors from CSV file
        competitorList.loadFromCSV("RunCompetitor.csv");

        // Get the list of competitors from CompetitorList
        List<RAScompetitor> competitors = competitorList.getCompetitors();

        // Create an array of competitor names for the user to choose
        String[] competitorNames = new String[competitors.size()];
        for (int i = 0; i < competitors.size(); i++) {
            competitorNames[i] = competitors.get(i).getName();
        }

        // Create a custom JPanel to enhance the input dialog
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a label to display the instruction
        JLabel instructionLabel = new JLabel("Drag and drop to select a competitor:");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(instructionLabel, BorderLayout.NORTH);

        // Create a JComboBox to display the competitor names
        JComboBox<String> competitorComboBox = new JComboBox<>(competitorNames);
        competitorComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(competitorComboBox, BorderLayout.CENTER);

        // Show the input dialog with the custom panel
        int option = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Select Competitor",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        // Check if the user clicked "OK"
        if (option == JOptionPane.OK_OPTION) {
            // Find the selected competitor
            String selectedCompetitorName = (String) competitorComboBox.getSelectedItem();
            RAScompetitor selectedCompetitor = competitors.stream()
                    .filter(comp -> comp.getName().equals(selectedCompetitorName))
                    .findFirst()
                    .orElse(null);

            if (selectedCompetitor != null) {
                // Create a panel to display competitor details
                JPanel detailsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
                detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                detailsPanel.setBackground(Color.white);

                // Create and add labels for each piece of information
                JLabel nameLabel = new JLabel("Name: " + selectedCompetitor.getName());
                nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
                JLabel numberLabel = new JLabel("Competitor Number: " + selectedCompetitor.getCompetitorNumber());
                numberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel ageLabel = new JLabel("Age: " + selectedCompetitor.getAge());
                ageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel emailLabel = new JLabel("Email: " + selectedCompetitor.getEmail());
                emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel categoryLabel = new JLabel("Category: " + selectedCompetitor.getCategory());
                categoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel levelLabel = new JLabel("Level: " + selectedCompetitor.getLevel());
                levelLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel countryLabel = new JLabel("Country: " + selectedCompetitor.getCountry());
                countryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel scoresLabel = new JLabel("Scores: " + Arrays.toString(selectedCompetitor.getScores()).replaceAll("[\\[\\]]", ""));
                scoresLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                JLabel overallScoreLabel = new JLabel("Overall Score: " + selectedCompetitor.getOverallScore());
                overallScoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                detailsPanel.add(nameLabel);
                detailsPanel.add(numberLabel);
                detailsPanel.add(ageLabel);
                detailsPanel.add(emailLabel);
                detailsPanel.add(categoryLabel);
                detailsPanel.add(levelLabel);
                detailsPanel.add(countryLabel);
                detailsPanel.add(scoresLabel);
                detailsPanel.add(overallScoreLabel);

                // Create a new JFrame to display the details panel
                JFrame detailsFrame = new JFrame("Competitor Full Details");
                detailsFrame.getContentPane().setBackground(Color.white);
                detailsFrame.getContentPane().setLayout(new BorderLayout());
                detailsFrame.getContentPane().add(detailsPanel, BorderLayout.CENTER);

                detailsFrame.setSize(400, 400);
                detailsFrame.setLocationRelativeTo(null); // Center the frame
                detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close to avoid closing the main frame
                detailsFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Competitor not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editDetails() {
        // Load competitors from CSV file
        competitorList.loadFromCSV("RunCompetitor.csv");

        // Get the list of competitors from CompetitorList
        List<RAScompetitor> competitors = competitorList.getCompetitors();

        // Create column names
        String[] columnNames = {"Competitor Number", "Name", "Age", "Email", "Category", "Level", "Country", "Overall Score"};

        // Create a DefaultTableModel with the data and column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Populate the table model with data from competitors
        for (RAScompetitor competitor : competitors) {
            Object[] rowData = {
                    competitor.getCompetitorNumber(),
                    competitor.getName(),
                    competitor.getAge(),
                    competitor.getEmail(),
                    competitor.getCategory(),
                    competitor.getLevel(),
                    competitor.getCountry(),
                    competitor.getOverallScore()
            };
            tableModel.addRow(rowData);
        }

        // Create a JTable with improved aesthetics
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true); // Enable sorting
        table.setRowHeight(30); // Set table row height for better visibility
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for table
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Set font for table header
        table.getTableHeader().setBackground(new Color(70, 130, 180)); // Set background color for table header
        table.getTableHeader().setForeground(Color.white); // Set text color for table header

        // Make specific columns editable
        int[] editableColumns = {1, 2, 3, 4, 5, 6}; // Assuming you want to make these columns editable
        for (int columnIndex : editableColumns) {
            table.getColumnModel().getColumn(columnIndex).setCellEditor(new DefaultCellEditor(new JTextField()));
        }

        // Create a JScrollPane with improved aesthetics
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Show a confirmation dialog
        int result = JOptionPane.showConfirmDialog(
                this,
                scrollPane,
                "Edit Competitor Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Update competitor details based on the edited table
            updateCompetitorDetails(tableModel, competitors);
        }
    }

    private void updateCompetitorDetails(DefaultTableModel tableModel, List<RAScompetitor> competitors) {
        // You might want to perform validation and error handling here
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            RAScompetitor competitor = competitors.get(i);
            competitor.setName((String) tableModel.getValueAt(i, 1));
            competitor.setAge(Integer.parseInt(tableModel.getValueAt(i, 2).toString()));
            competitor.setEmail((String) tableModel.getValueAt(i, 3));
            competitor.setCategory((String) tableModel.getValueAt(i, 4));
            competitor.setLevel((String) tableModel.getValueAt(i, 5));
            competitor.setCountry((String) tableModel.getValueAt(i, 6));
            competitor.setScores(new int[]{
                    Integer.parseInt(tableModel.getValueAt(i, 7).toString())
            });
        }

        JOptionPane.showMessageDialog(this, "Competitor details updated successfully.");
    }

    private void deleteCompetitor() {
        // Load competitors from CSV file
        competitorList.loadFromCSV("RunCompetitor.csv");

        // Get the list of competitors from CompetitorList
        List<RAScompetitor> competitors = competitorList.getCompetitors();

        // Create column names
        String[] columnNames = {"Competitor Number", "Name", "Age", "Email", "Category", "Level", "Country", "Overall Score"};

        // Create a DefaultTableModel with the data and column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Populate the table model with data from competitors
        for (RAScompetitor competitor : competitors) {
            Object[] rowData = {
                    competitor.getCompetitorNumber(),
                    competitor.getName(),
                    competitor.getAge(),
                    competitor.getEmail(),
                    competitor.getCategory(),
                    competitor.getLevel(),
                    competitor.getCountry(),
                    competitor.getOverallScore()
            };
            tableModel.addRow(rowData);
        }

        // Create a JTable with improved aesthetics
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true); // Enable sorting
        table.setRowHeight(30); // Set table row height for better visibility
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for table
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Set font for table header
        table.getTableHeader().setBackground(new Color(70, 130, 180)); // Set background color for table header
        table.getTableHeader().setForeground(Color.white); // Set text color for table header

        // Allow selection in the JTable
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create a JScrollPane with improved aesthetics
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Show a confirmation dialog
        int result = JOptionPane.showConfirmDialog(
                this,
                scrollPane,
                "Remove Competitor",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Remove the selected competitor from the list
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int confirmResult = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to remove the selected competitor?",
                        "Confirm Removal",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (confirmResult == JOptionPane.YES_OPTION) {
                    competitors.remove(selectedRow);
                    JOptionPane.showMessageDialog(this, "Competitor removed successfully.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No competitor selected. Please select a competitor to remove.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CompetitorGUI().setVisible(true));
    }
}
