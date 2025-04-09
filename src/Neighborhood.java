import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
    //ATTRIBUTES
    private List<ServiceRequest> requests;
    private String name;

    //METHODS
    public Neighborhood(String name) {
        this.name = name;
        this.requests = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<ServiceRequest> getOpenCases() {
        List<ServiceRequest> open_cases = new ArrayList<>();
        for ( ServiceRequest case_request : requests ) {
            if ( case_request.isOpen() ) {
                open_cases.add(case_request);
            }
        }
        return open_cases;
    }

    public int getTotalRequestCount() {
        return requests.size();
    }

    public List<ServiceRequest> getOverdueCases() {
        List<ServiceRequest> overdue_cases = new ArrayList<>();
        for ( ServiceRequest case_request : requests ) {
            if ( !case_request.isOnTime() ) {
                overdue_cases.add(case_request);
            }
        }
        return overdue_cases;
    }

    public double getOverdueRate() {
        double avg_rate;
        double overdue_count = 0;
        for (ServiceRequest case_request : requests) {
            if ( !case_request.isOnTime() ) {
                overdue_count++;
            }
        }
        return avg_rate = overdue_count / requests.size();
    }

    public double getAverageDaysOpen() {
        double total_days = 0;
        double days_opened = 0;
        for (ServiceRequest case_request : requests ) {
            days_opened += case_request.daysOpen();
        }
        return days_opened / 365 ; 
    }

    public void addRequest(ServiceRequest request) {
        requests.add(request);
    }
}
