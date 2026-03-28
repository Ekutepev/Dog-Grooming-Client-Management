package manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;


public class Manager {

    private ArrayList<Client> clients;
    

    public void menu() {
        System.out.println("Welcome to the Dog Grooming Manager!");
        System.out.println("1. Add Client");
        System.out.println("2. View Clients");
        System.out.println("3. Book Appointment");
        System.out.println("4. View Appointments");
        System.out.println("5. Remove Client");
        System.out.println("6. Exit");
    }

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
            Client client = new Client(firstName, lastName, email, dogName);
            clients.add(client);
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }

    public void viewClients() {
        try {
            Scanner scanner = new Scanner(new java.io.File("clients.txt"));
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
            BufferedWriter br = new BufferedWriter(new FileWriter("Appointment.") )
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    
}
