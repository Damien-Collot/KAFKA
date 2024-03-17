package org.example;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    private static final String BASE_URL = "http://localhost:8080/patient";
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Get All Patients");
            System.out.println("2. Get Patient By ID");
            System.out.println("3. Get Patient By Name");
            System.out.println("4. Get Patient Stay By PID");
            System.out.println("5. Get Patient Movements By SID");
            System.out.println("6. Export Data");
            System.out.println("7. Display Help");
            System.out.println("8. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    sendRequest("/getAllPatients");
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    sendRequest("/getPatient/" + id);
                    break;
                case 3:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    sendRequest("/getPatientByName/" + name);
                    break;
                case 4:
                    System.out.print("Enter PID: ");
                    String pid = scanner.nextLine();
                    sendRequest("/getPatientStayByPid/" + pid);
                    break;
                case 5:
                    System.out.print("Enter SID: ");
                    String sid = scanner.nextLine();
                    sendRequest("/getPatientMovementsBySid/" + sid);
                    break;
                case 6:
                    sendRequest("/exportData");
                    break;
                case 7:
                    System.out.println("Available commands: \n" +
                            "- GetAllPatients: Returns all patients identity.\n" +
                            "- GetPatientById/{id}: Returns the identity of a patient by their id.\n" +
                            "- GetPatientByName/{name}: Returns the identity of a patient by their name.\n" +
                            "- GetPatientStayByPid/{pid}: Returns the stays of a patient by their PID.\n" +
                            "- GetPatientMovementsBySid/{sid}: Returns all movements of a patient by stay ID.\n" +
                            "- ExportData: Exports database data to a JSON file.\n" +
                            "- Help: Displays this list of commands.");
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void
    sendRequest(String path) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response: " + response.body());
        } catch (Exception e) {
            System.out.println("Error while sending request: " + e.getMessage());
        }
    }
}