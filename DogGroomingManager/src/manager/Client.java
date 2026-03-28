package manager;

public class Client {

    private String firstName;
    private String lastName;
    private String email;
    private String dogName;
    private String dogBreed;
    private int dogAge;

    public Client(String firstName, String lastName, String email, String dogName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dogName = dogName;
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

    public String getDogName() {
        return dogName;
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

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName= " + firstName + '\'' +
                ", lastName= " + lastName + '\'' +
                ", email= " + email + '\'' +
                ", dogName= " + dogName + '\'' +
                '}';
    }   
}
