import java.time.LocalDate;

public class ServiceRequest {
    //ATTRIBUTES
    private LocalDate date_opened;
    private LocalDate date_closed; //<- if they were closed
    private String status;
    private String reason;
    private String location;
    private String on_time;

    //METHODS

    public ServiceRequest(LocalDate date_opened, LocalDate date_closed, String status, String reason, String location, String on_time) {
        this.date_opened = date_opened;
        this.date_closed = date_closed;
        this.status = status;
        this.reason = reason;
        this.location = location;
        this.on_time = on_time;
    }

    public int daysOpen() {
        int days_open = 0;
        if (this.date_closed != null) {
            days_open += date_closed.getDayOfYear() - date_opened.getDayOfYear();
        } else {
            days_open += LocalDate.now().getDayOfYear() - date_opened.getDayOfYear();
        }
        return days_open;
    }

    public String getLocation() {
        return location;
    }

    public boolean isOpen() {
        boolean open = status.equals("Open"); 
        if (status.equals("Closed") ) {
            open = false;
        }
        return open;
    }

    public boolean isOnTime() {
        boolean early = on_time.equals("ONTIME");
        if (on_time.equals("OVERDUE")) {
            early = false;
        }
        return early;
    }
}
