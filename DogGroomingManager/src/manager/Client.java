package manager;

import java.util.List;

public class Client {

    private String firstName;
    private String lastName;
    private String email;
    private List<Dog> dogs;

    public Client(String firstName, String lastName, String email, List<Dog> dogs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dogs = dogs;
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

    @Override
    public String toString() {
        return "Client{" +
                "firstName= " + firstName + '\'' +
                ", lastName= " + lastName + '\'' +
                ", email= " + email + '\'' +
                ", dogs= " + dogs +
                '}';
    }   
}
