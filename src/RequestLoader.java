import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class RequestLoader {
    //ATTRIBUTES
    private final File service_data_311;
    private final List<Neighborhood> neighborhoods;

    //METHODS
    public RequestLoader(File sd311) throws FileNotFoundException {
        this.service_data_311 = sd311;
        this.neighborhoods = new ArrayList<>();
    }

    public List<Neighborhood> load() throws IOException {
        CSVFormat format = Builder.create()
                                    .setHeader()
                                    .setDelimiter(',')
                                    .setQuote('"')
                                    .build(); //<- parse a CSV file where values are delimited with commas, that has a header row, that uses " quotes for literal cells....

        CSVParser parser = CSVParser.parse(service_data_311, StandardCharsets.UTF_8, format); //<- use that CSV format to read SOME_FILE, a UTF-8-coded .csv
    }
}
