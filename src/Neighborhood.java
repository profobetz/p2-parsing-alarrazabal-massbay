import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
    //ATTRIBUTES
    private List<ServiceRequest> request;
    private String name;

    //METHODS
    public Neighborhood() {
        this.name = "";
        this.request = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<ServiceRequest> getOpenCases() {

    }

    public int getTotalRequestCount() {

    }

    public int getOverdueCases() {

    }

    public int getOverdueRate() {
        
    }

    public double getAverageDaysOpen() {

    }
}
