package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;


public class Manager {

    private ArrayList<Client> clients;
    private static final String CLIENT_FILE = "DogGroomingManager/res/client.txt";
    
    public Manager() {
        clients = new ArrayList<>();
        loadClients();

        Boolean whileOn = true;
        int choice = 0;

        while (whileOn) {
            menu();
            System.out.println("Enter your choice: ");
            
            try {
                Scanner scanner = new Scanner(System.in);
                choice = Integer.valueOf(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    viewClients();
                    break;
                case 3:
                    bookAppointment();
                    break;
                case 4:
                    viewAppointments();
                    break;
                case 5:
                    removeClient();
                    break;
                case 6:
                    whileOn = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
    }

    public void menu() {
        System.out.println("Welcome to the Dog Grooming Manager!");
        System.out.println("1. Add Client");
        System.out.println("2. View Clients");
        System.out.println("3. Book Appointment");
        System.out.println("4. View Appointments");
        System.out.println("5. Remove Client");
        System.out.println("6. Exit");
    }

    // Method to add a new client, need to fix infite loop when adding client in.
    public void addClient() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter client's first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter client's last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter client's email: ");
            String email = scanner.nextLine();  
            System.out.print("Enter dog's name: ");
            String dogName = scanner.nextLine();
            System.out.print("Enter dog's breed: ");
            String dogBreed = scanner.nextLine();
            System.out.print("Enter dog's age: ");
            int dogAge = scanner.nextInt();
            Client client = new Client(firstName, lastName, email, dogName, dogBreed, dogAge);
            clients.add(client);
            saveClients();
            System.out.println("Client added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
        return;
    }

    public void viewClients() {
        try {
            File clientFile = ensureClientFileExists();

            Scanner scanner = new Scanner(clientFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error viewing clients: " + e.getMessage());
        }
    }

    public void bookAppointment() {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("./res/Appointment.txt", true));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter client's name: ");
            String clientName = scanner.nextLine();
            if (clients.stream().anyMatch(c -> c.getFirstName().equals(clientName))) {
                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Enter appointment time (HH:MM): ");
                String time = scanner.nextLine();
                br.write(clientName + " - " + date + " " + time);
                br.newLine();
                System.out.println("Appointment booked successfully!");
            } else {
                System.out.println("Client not found. Please add the client first.");
            }
            br.close();
            scanner.close();

        } catch (Exception e) {
            System.out.println("Error booking appointment: " + e.getMessage());
        }
    }

    public void viewAppointments() {
        try {
            File appointmentFile = new File("./res/Appointment.txt");
            if (!appointmentFile.exists()) {
                System.out.println("No appointments found.");
                return;
            }

            Scanner scanner = new Scanner(appointmentFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error viewing appointments: " + e.getMessage());
        }
    }

    public void removeClient() {
        

        try {
            File clientFile = ensureClientFileExists();
            BufferedReader br = new BufferedReader(new FileReader(clientFile));
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter client's last name to remove: ");
            String clientName = scanner.nextLine();
            clients.removeIf(c -> c.getLastName().equals(clientName));
            saveClients();
            System.out.println("Client removed successfully!");
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error removing client: " + e.getMessage());
        }
    }

    public void saveClients() {
        clients.sort(
            Comparator.comparing((Client c) -> c.getLastName().trim().toLowerCase())
            .thenComparing(c -> c.getFirstName().trim().toLowerCase())
            .thenComparing(c -> c.getEmail().trim().toLowerCase())
        );

        try {
            File clientFile = ensureClientFileExists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(clientFile));
            for (Client client : clients) {
                writer.write(client.getFirstName() + "," + 
                client.getLastName() + ", " + client.getEmail() + ", " + 
                client.getDogName() + ", " + client.getDogBreed() + ", " + 
                client.getDogAge());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving clients: " + e.getMessage());
        }
    }

    public void loadClients() {
        try {
            File clientFile = ensureClientFileExists();

            BufferedReader br = new BufferedReader(new FileReader(clientFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    try {
                        Client client = new Client(
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim(),
                            Integer.parseInt(parts[5].trim())
                        );
                        clients.add(client);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid dog age in client data: " + line);
                    }
                } else {
                    System.out.println("Invalid client data: " + line);
                }

            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error loading clients: " + e.getMessage());
        }
    }

    private File ensureClientFileExists() throws IOException {
        File clientFile = new File(CLIENT_FILE);
        File parent = clientFile.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        if (!clientFile.exists()) {
            clientFile.createNewFile();
        }

        return clientFile;
    }

    
}
