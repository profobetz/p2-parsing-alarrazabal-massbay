import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class RequestLoader {
    //ATTRIBUTES
    private final File service_data_311;
    private final List<Neighborhood> neighborhoods_list;
    private final HashMap<String, Neighborhood> neighborhoods_hashmap;
    DateTimeFormatter localDateFormat  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //METHODS
    public RequestLoader(File sd311) {
        this.service_data_311 = sd311;
        this.neighborhoods_list = new ArrayList<>();
        this.neighborhoods_hashmap = new HashMap<>();
    }

    public List<Neighborhood> load() throws FileNotFoundException, IOException {
        CSVFormat format = CSVFormat.Builder.create()
                                    .setHeader()
                                    .setDelimiter(',')
                                    .setQuote('"')
                                    .build(); //<- parse a CSV file where values are delimited with commas, that has a header row, that uses " quotes for literal cells....

        CSVParser parser = CSVParser.parse(service_data_311, StandardCharsets.UTF_8, format); //<- use that CSV format to read SOME_FILE, a UTF-8-coded .csv
        
  
        for ( CSVRecord next_row : parser.getRecords() ) {
            LocalDateTime open_date_time = LocalDateTime.parse(next_row.get("open_dt"), localDateFormat); 
            LocalDate closed_date = null;
            if ( !next_row.get("closed_dt").equals("") ) {
                LocalDateTime closed_date_time = LocalDateTime.parse(next_row.get("closed_dt"), localDateFormat);
                closed_date = closed_date_time.toLocalDate();
            }
            String status = next_row.get("case_status");
            String reason = next_row.get("reason");
            String location = next_row.get("neighborhood");
            String on_time = next_row.get("on_time");
            ServiceRequest request = new ServiceRequest(open_date_time.toLocalDate(), closed_date, status, reason, location, on_time);

            if ( neighborhoods_hashmap.containsKey(location)) {
                Neighborhood neighborhood = neighborhoods_hashmap.get(location);
                neighborhood.addRequest(request);
            } else {
                Neighborhood neighborhood = new Neighborhood(location);
                neighborhood.addRequest(request);
                neighborhoods_list.add(neighborhood);
                neighborhoods_hashmap.put(location, neighborhood);
            }
        }

        return neighborhoods_list;
    }
}

