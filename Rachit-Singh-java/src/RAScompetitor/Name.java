package RAScompetitor;

public class Name {
    private String firstName;
    private String lastName;

    // Constructor
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter and Setter for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Method to get the full name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Method to update the name
    public void updateName(String firstName, String lastName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
        if (lastName != null) {
            this.lastName = lastName;
        }
        System.out.println("Name updated successfully.");
    }

    // Example usage
    public static void main(String[] args) {
        Name name = new Name("Keith", "Talbot");
        System.out.println("Full Name: " + name.getFullName());

        name.updateName("John", "Smith");
        System.out.println("Updated Full Name: " + name.getFullName());

        // Using getter and setter methods
        System.out.println("First Name: " + name.getFirstName());
        name.setFirstName("Jane");
        System.out.println("Updated First Name: " + name.getFirstName());

        System.out.println("Last Name: " + name.getLastName());
        name.setLastName("Doe");
        System.out.println("Updated Last Name: " + name.getLastName());
    }
}
