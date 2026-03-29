package manager;

import java.time.LocalDate;
import java.time.Period;

public class Dog {

    private String dogName;
    private String dogBreed;
    private LocalDate dogDOB;

    public Dog(String dogName, String dogBreed, LocalDate dogDOB) {
        this.dogName = dogName;
        this.dogBreed = dogBreed;
        this.dogDOB = dogDOB;
    }

    public String getDogName() {
        return dogName;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public int getDogAge() {
        return Period.between(dogDOB, LocalDate.now()).getYears();
    }

    public LocalDate getDogDOB() {
        return dogDOB;  
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public void setDogDOB(LocalDate dogDOB) {
        this.dogDOB = dogDOB;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName= " + dogName + '\'' +
                ", dogBreed= " + dogBreed + '\'' +
                ", dogDOB= " + dogDOB +
                '}';
    }
}
