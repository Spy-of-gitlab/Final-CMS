package RAScompetitor;

public class EmergencyResponseService {
    private int serviceID;
    private String serviceName;
    private String contactDetails;

    // Constructor
    public EmergencyResponseService(int serviceID, String serviceName, String contactDetails) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.contactDetails = contactDetails;
    }

    // Getter and Setter for serviceID
    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    // Getter and Setter for serviceName
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    // Getter and Setter for contactDetails
    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    // Method to respond to an emergency
    public void respondToEmergency(String incidentDetails) {
        System.out.println("Responding to emergency: " + incidentDetails);
    }

    // Method to update availability status
    public void updateAvailabilityStatus(String status) {
        System.out.println("Updating availability status to: " + status);
    }

    // Method to record an incident
    public void recordIncident(String incidentReport) {
        System.out.println("Recording incident report: " + incidentReport);
    }

    // Example usage
    public static void main(String[] args) {
        EmergencyResponseService service = new EmergencyResponseService(1, "Fire Department", "123-456-7890");

        System.out.println("Service Name: " + service.getServiceName());
        System.out.println("Contact Details: " + service.getContactDetails());

        service.respondToEmergency("Fire at Main Street");
        service.updateAvailabilityStatus("Available");
        service.recordIncident("Incident reported and handled successfully.");
    }
}
