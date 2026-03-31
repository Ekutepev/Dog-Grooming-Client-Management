package manager;

import java.util.List;

public class Client {

    private String firstName;
    private String lastName;
    private String email;
    private List<Dog> dogs;
    private String phoneNumber;

    public Client(String firstName, String lastName, String email, List<Dog> dogs, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dogs = dogs;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName= " + firstName + '\'' +
                ", lastName= " + lastName + '\'' +
                ", email= " + email + '\'' +
                ", dogs= " + dogs +
                ", phoneNumber= " + phoneNumber +
                '}';
    }   
}
