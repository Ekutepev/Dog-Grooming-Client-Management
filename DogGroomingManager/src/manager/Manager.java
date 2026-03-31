package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Manager {

    private ArrayList<Client> clients;
    private ArrayList<Appointment> appointments;
    private static final String CLIENT_FILE = "DogGroomingManager/res/client.txt";
    private static final String APPOINTMENT_FILE = "DogGroomingManager/res/Appointment.txt";
    private Scanner userInput = new Scanner(System.in);
    
    public Manager() {
        clients = new ArrayList<>();
        appointments = new ArrayList<>();
        loadClients();
        loadAppointments();

        Boolean whileOn = true;
        int choice = 0;

        while (whileOn) {
            menu();
            System.out.println("Enter your choice: ");
            
            try {
                choice = Integer.valueOf(userInput.nextLine());
            
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
                    viewUpcomingAppointments();
                    break;
                case 5:
                    searchAppointmentByClient();
                    break;
                case 6:
                    updateClient();
                    break;
                case 7:
                    removeClient();
                    break;
                case 8:
                    whileOn = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, Please pick one of the options from the menu.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
    }

    public void menu() {
        System.out.println("Welcome to the Dog Grooming Manager!\n");
        System.out.println("1. Add Client");
        System.out.println("2. View Clients");
        System.out.println("3. Book Appointment");
        System.out.println("4. View Appointments");
        System.out.println("5. Search Appointment by Client");
        System.out.println("6. Update Client");
        System.out.println("7. Remove Client");
        System.out.println("8. Exit\n");
    }

    // Method to add a new client, need to fix infite loop when adding client in.
    public void addClient() {
        try {
            System.out.print("Enter client's first name: ");
            String firstName = userInput.nextLine();
            System.out.print("Enter client's last name: ");
            String lastName = userInput.nextLine();
            System.out.print("Enter client's email: ");
            String email = userInput.nextLine();

            if (clients.stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(email))) {
                System.out.println("A client with this email already exists. Please use a different email.\n");
                return;
            }
            
            List<Dog> dogs = new ArrayList<>();
            while(true) {
                System.out.print("Enter dog's name: ");
                String dogName = userInput.nextLine();
                System.out.print("Enter dog's breed: ");
                String dogBreed = userInput.nextLine();
                System.out.print("Enter dog's date of birth (YYYY-MM-DD): ");
                LocalDate dogDOB = LocalDate.parse(userInput.nextLine());
                dogs.add(new Dog(dogName, dogBreed, dogDOB));

                System.out.print("Add another dog for this client? (yes/no): ");
                String addAnother = userInput.nextLine();
                if (!addAnother.equalsIgnoreCase("yes")) {
                    break;
                }
            }

            Client newClient = new Client(firstName, lastName, email, dogs);
            clients.add(newClient);

            saveClients();
            System.out.println("Client added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }

    public void viewClients() {
        try {
            System.out.println("Search for client by Last name: ");
            String searchLastName = userInput.nextLine();
            for (Client client : clients) {
                if (client.getLastName().equalsIgnoreCase(searchLastName)) {
                    System.out.println(client.getFirstName() + " " + client.getLastName() + " - " + client.getEmail());
                    for (Dog dog : client.getDogs()) {
                        System.out.println(" | Dog: " + dog.getDogName() + ", " + dog.getDogBreed() + ", " + dog.getDogAge() + " years old");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing clients: " + e.getMessage());
        }
    }

    public void updateClient() {
        try {
            System.out.println("Enter client's Last name to update: ");
            String searchLastName = userInput.nextLine();  
            Client clientToUpdate = clients.stream()
                .filter(c -> c.getLastName().equalsIgnoreCase(searchLastName))
                .findFirst()
                .orElse(null); 
            if (clientToUpdate != null) {
                System.out.print("Enter new first name (leave blank to keep current): ");
                String newFirstName = userInput.nextLine();
                if (!newFirstName.isEmpty()) {
                    clientToUpdate.setFirstName(newFirstName);
                }

                System.out.print("Enter new last name (leave blank to keep current): ");
                String newLastName = userInput.nextLine();
                if (!newLastName.isEmpty()) {
                    clientToUpdate.setLastName(newLastName);
                }

                System.out.print("Enter new email (leave blank to keep current): ");
                String newEmail = userInput.nextLine();
                if (!newEmail.isEmpty()) {
                    clientToUpdate.setEmail(newEmail);
                }

                System.out.print("Do you want to update the client's dogs? (yes/no): ");
                String updateDogs = userInput.nextLine();
                if (updateDogs.equalsIgnoreCase("yes")) {
                    List<Dog> updatedDogs = new ArrayList<>();
                    while (true) {
                        System.out.println("Which dog do you want to update?: " + clientToUpdate.getDogs().stream().map(Dog::getDogName).toList());
                        System.out.print("Enter dog's name: ");
                        String dogName = userInput.nextLine();
                        if (clientToUpdate.getDogs().stream().noneMatch(d -> d.getDogName().equalsIgnoreCase(dogName))) {
                            System.out.println("Dog not found. Please enter a valid dog name.");
                            continue;
                        }
                        System.out.print("Enter dog's breed: ");
                        String dogBreed = userInput.nextLine();
                        System.out.print("Enter dog's age: ");
                        int dogAge = Integer.parseInt(userInput.nextLine());
                        LocalDate dogDOB = LocalDate.now().minusYears(dogAge);
                        updatedDogs.add(new Dog(dogName, dogBreed, dogDOB));

                        System.out.print("Add another dog for this client? (yes/no): ");
                        String addAnother = userInput.nextLine();
                        if (!addAnother.equalsIgnoreCase("yes")) {
                            break;
                        }
                    }
                    clientToUpdate.setDogs(updatedDogs);
                }

                saveClients();
                System.out.println("Client updated successfully!");
            } else {
                System.out.println("Client not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
    }

    public void bookAppointment() {
        try {
            File appointmentFile = ensureAppointmentFileExists();
            try (BufferedWriter br = new BufferedWriter(new FileWriter(appointmentFile, true))) {
                System.out.print("Enter client's Last name: ");
                String clientLastName = userInput.nextLine();
                if (clients.stream().anyMatch(c -> c.getLastName().equalsIgnoreCase(clientLastName))) {
                    System.out.println("Which dog is the appointment for?: " + clients.stream()
                        .filter(c -> c.getLastName().equalsIgnoreCase(clientLastName))
                        .flatMap(c -> c.getDogs().stream())
                        .map(Dog::getDogName)
                        .toList());
                    
                    System.out.print("Enter dog's name: ");
                    String dogName = userInput.nextLine();
                    if (clients.stream().noneMatch(c -> c.getLastName().equalsIgnoreCase(clientLastName) && c.getDogs().stream().anyMatch(d -> d.getDogName().equalsIgnoreCase(dogName)))) {
                        System.out.println("Dog not found for the specified client. Please try again.");
                        return;
                    }
                    System.out.print("Enter appointment date (YYYY-MM-DD): ");
                    String date = userInput.nextLine();
                    System.out.print("Enter appointment time (HH:MM): ");
                    String time = userInput.nextLine();
                    if (appointments.stream().anyMatch(a -> a.getDateTime().equals(LocalDateTime.parse(date + "T" + time)))) {
                        System.out.println("An appointment already exists at this date and time. Please choose a different slot.");
                        return;
                    }
                    LocalDateTime appointmentDateTime = LocalDateTime.parse(date + "T" + time);
                    if (appointmentDateTime.isBefore(LocalDateTime.now())) {
                        System.out.println("Warning: You have booked an appointment in the past. Please double-check the date and time.");
                        return;
                    }

                    br.write(clientLastName + "(" + dogName + ") - " + date + " " + time);
                    br.newLine();

                    appointments.add(new Appointment(clientLastName, appointmentDateTime));

                    System.out.println("\nAppointment booked successfully!\n");
                    } else {
                    System.out.println("Client not found. Please add the client first.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error booking appointment: " + e.getMessage());
        }
    }

    public void viewUpcomingAppointments() {
        try {
            LocalDateTime now = LocalDateTime.now();
            Boolean hasUpcoming = false;
            for (Appointment appointment : appointments) {
                if (appointment.getDateTime().isAfter(now)) {
                    System.out.println(appointment);
                    hasUpcoming = true;
                }
            }
            
            if (!hasUpcoming) {
                System.out.println("No upcoming appointments found.");
            }

        } catch (Exception e) {
        System.out.println("Error accessing appointments: " + e.getMessage());
        }
    }

    public void searchAppointmentByClient() {
        try {
            File appointmentFile = ensureAppointmentFileExists();
            if (!appointmentFile.exists()) {
                System.out.println("No appointments found.");
                return;
            }

            System.out.print("Enter client's Last name to search for appointments: ");
            String clientLastName = userInput.nextLine();

            try (BufferedReader br = new BufferedReader(new FileReader(appointmentFile))) {
                String line;
                boolean found = false;
                while ((line = br.readLine()) != null) {
                    if (line.toLowerCase().contains(clientLastName.toLowerCase())) {
                        System.out.println(line);
                        found = true;
                    }
                }

                if (!found) {
                System.out.println("No appointments found for client: " + clientLastName);
                }
            }
            
        } catch (Exception e) {
        System.out.println("Error searching appointments: " + e.getMessage());
        }
    }

    public void removeClient() {
        try {
            File clientFile = ensureClientFileExists();
            BufferedReader br = new BufferedReader(new FileReader(clientFile));
            System.out.print("Enter client's last name to remove: ");
            String clientLastName = userInput.nextLine();
            clients.removeIf(c -> c.getLastName().equalsIgnoreCase(clientLastName));
            saveClients();
            System.out.println("Client removed successfully!");
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
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(clientFile))) {
                for (Client client : clients) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(client.getFirstName().trim()).append(",").append(client.getLastName().trim()).append(",").append(client.getEmail().trim());
                    for (Dog dog : client.getDogs()) {
                        sb.append(",").append(dog.getDogName().trim()).append(":").append(dog.getDogBreed().trim()).append(":").append(dog.getDogDOB());
                }
                writer.write(sb.toString());
                writer.newLine();
            }
        }
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
                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String email = parts[2].trim();

                List<Dog> dogs = new ArrayList<>();

                for (int i = 3; i < parts.length; i++) {
                    String[] dogParts = parts[i].split(":");
                    if (dogParts.length == 3) {
                        String dogName = dogParts[0].trim();
                        String dogBreed = dogParts[1].trim();
                        LocalDate dogDOB = LocalDate.parse(dogParts[2].trim());

                        dogs.add(new Dog(dogName, dogBreed, dogDOB));
                    }
                }

                clients.add(new Client(firstName, lastName, email, dogs));
            }
        } catch (Exception e) {
            System.out.println("Error loading clients: " + e.getMessage());
        }
    }

    public void loadAppointments() {
        try {
            File appointmentFile = ensureAppointmentFileExists();

            try (BufferedReader br = new BufferedReader(new FileReader(appointmentFile))) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" - ");
                    if (parts.length < 2) {
                        continue;
                    } 

                    String clientLastName = parts[0].trim();
                    String[] dateTimeParts = parts[1].split(" ");
                    if (dateTimeParts.length < 2) {
                        continue;
                    }

                    LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTimeParts[0] + "T" + dateTimeParts[1]);
                    appointments.add(new Appointment(clientLastName, appointmentDateTime));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading appointments: " + e.getMessage());
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

    private File ensureAppointmentFileExists() throws IOException {
        File appointmentFile = new File(APPOINTMENT_FILE);
        File parent = appointmentFile.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        if (!appointmentFile.exists()) {
            appointmentFile.createNewFile();
        }

        return appointmentFile;
    }

    
}
