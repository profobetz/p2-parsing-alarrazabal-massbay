import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class RequestLoader {
    //ATTRIBUTES
    private final File service_data_311;
    private final List<Neighborhood> neighborhoods;

    //METHODS
    public RequestLoader(File sd311) {
        this.service_data_311 = sd311;
        this.neighborhoods = new ArrayList<>();
    }

    public List<Neighborhood> load() throws FileNotFoundException, IOException {
        CSVFormat format = CSVFormat.Builder.create()
                                    .setHeader()
                                    .setDelimiter(',')
                                    .setQuote('"')
                                    .build(); //<- parse a CSV file where values are delimited with commas, that has a header row, that uses " quotes for literal cells....

        CSVParser parser = CSVParser.parse(service_data_311, StandardCharsets.UTF_8, format); //<- use that CSV format to read SOME_FILE, a UTF-8-coded .csv
        
        for ( CSVRecord next_row : parser.getRecords() ) {
            LocalDate open_date = LocalDate.parse( next_row.get("open_dt") ); //<- if the top of the file has an "open_dt" column, this will get that column for this 'next_row');
            LocalDate closed_date = null;
            String status = next_row.get("case_status");
            String reason = next_row.get("reason");
            String location = next_row.get("neighborhood");
            String on_time = next_row.get("on_time");

            if ( !next_row.get("closed_dt").equals("") ){
                closed_date = LocalDate.parse( next_row.get("closed_dt") );
            }

            Neighborhood neighborhood = new Neighborhood( next_row.get("neighborhood") );
            ServiceRequest request = new ServiceRequest(open_date, closed_date, status, reason, location, on_time);

            if ( neighborhoods.contains(neighborhood) && neighborhood.getName().equals(request.getLocation()) ) {
                neighborhoods.get(neighborhoods.indexOf(neighborhood))
                             .addRequest(new ServiceRequest( open_date, closed_date, status, reason, location, on_time) );
            } else {  //<- Might need to add an else if statement 
                neighborhood.addRequest(request);
                neighborhoods.add( neighborhood );
            }

        }

        return neighborhoods;
    }
}
