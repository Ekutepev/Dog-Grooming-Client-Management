package manager;

public class Client {

    private String firstName;
    private String lastName;
    private String email;
    private String dogName;
    private String dogBreed;
    private int dogAge;

    public Client(String firstName, String lastName, String email, String dogName, String dogBreed, int dogAge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dogName = dogName;
        this.dogBreed = dogBreed;
        this.dogAge = dogAge;
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

    public String getDogBreed() {
        return dogBreed;
    }

    public int getDogAge() {
        return dogAge;
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

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
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
