import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {


        RequestLoader loader = new RequestLoader(new File("C:\\Users\\MassbayStudent\\Desktop\\CS200\\week7\\p2-parsing-alarrazabal-massbay\\resources\\311_requests.csv"));

        List<Neighborhood> neighborhoods = loader.load();

        for ( Neighborhood n : neighborhoods ) {
            String summary = String.format("%s - %d open / %d total, %d overdue (%.2f%%), %.2f avg. days to closure", 
                    n.getName(),
                    n.getOpenCases().size(),
                    n.getTotalRequestCount(),
                    n.getOverdueCases().size(),
                    n.getOverdueRate(),
                    n.getAverageDaysOpen()
            );

            System.out.println(summary);
        }

    }
}