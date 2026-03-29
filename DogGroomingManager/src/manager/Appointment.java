package manager;

import java.time.LocalDateTime;

public class Appointment {
    private String clientLastName;
    private LocalDateTime dateTime;

    public Appointment(String clientLastName, LocalDateTime dateTime) {
        this.clientLastName = clientLastName;
        this.dateTime = dateTime;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return clientLastName + "," + dateTime.toString();
    }
}
