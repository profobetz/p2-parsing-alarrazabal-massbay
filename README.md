[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/1c6NYIJH)

# Project 2: 311 Service Data


For this project, we will be looking at 311 Service Data from the city of Boston. The 311 service data file records all requests for city maintenance and ordinance violations logged by citizens and resolved by public works employees. In [the included 311_requests.csv file](resources/311_requests.csv) you can see all the data for the first quarter of 2025, from Jan 1 - March 31.

Your job this week will be to write a program that can parse this data to create ServiceRequest objects, along with Neighborhood objects that summarize all the requests made in a given neighborhood. To help you parsing the CSV file, the attached [build.gradle](build.gradle) has included a dependency to Apache Commons CSV (though you are also free to parse the file manually if you prefer.)


----------------------------


### Data Model

ServiceRequest objects should track the date they were opened, the date they were closed (if they were closed), whether they were closed on time, what reason was cited for the 311 request, and what neighborhood the request is located in. Additionally, they should have a method to compute how many days they were open based on their open and closed date.

Neighborhood objects should track the name of the Neighborhood, and the List of all ServiceRequests that have occurred in that neighborhood. Additionally, they should have methods to compute the average number of days a request was opened, the count of open cases, the count of overdue cases, and the percentage of cases which are overdue.



---------------------------

### Loading the Data

You should also create a RequestLoader class that stores a File object and has a method to load() that File which returns a List of Neighborhoods with all of the ServiceRequests populated for each neighborhood.  

If you are willing to run your program with Gradle, the Apache Commons CSV library can greatly simplify parsing the CSV data. To use it, you construct a CSVFormat object that describes the file structure, then give that format to a CSVParser alongside the location of a target file:

```java
    CSVFormat format = Builder.create()
                                .setHeader()
                                .setDelimiter(',')
                                .setQuote('"')
                                .build(); //<- parse a CSV file where values are delimited with commas, that has a header row, that uses " quotes for literal cells....

    CSVParser parser = CSVParser.parse(SOME_FILE, StandardCharsets.UTF_8, format); //<- use that CSV format to read SOME_FILE, a UTF-8-coded .csv
```

Then later on, you can have the parser parse the file and read each CSVRecord's columns based on the header names:

```java
   for ( CSVRecord next_row : parser.getRecords() ) {
            String this_rows_closed_date = next_row.get("closed_date"); //<- if the top of the file has a "closed_date" column, this will get that column for this 'next_row'
```

If you would prefer, you can instead use a Scanner and basic String splitting to read out the individual values, but in either case you will be responsible for converting each field to an appropriate datatype (e.g LocalDate for dates, booleans for binary values, etc....) Also make sure you handle any exceptions that may occur as a result of the file reading and parsing process using try-catch blocks.


---------------------------

### Main Method

Your main method for this week will simply construct a RequestLoader object, pass it the 311_requests.csv file, and then loop over and print out a summary of each Neighborhood object. For reference, when I attempted this lab I got the following results for my first three rows (your neighborhoods may appear in a different order, and that's fine!):

```
Brighton - 139 open / 557 total, 151 overdue (0.27%), 1.67 avg. days to closure
Allston / Brighton - 1358 open / 5791 total, 1415 overdue (0.24%), 1.90 avg. days to closure
Hyde Park - 566 open / 2386 total, 730 overdue (0.31%), 4.99 avg. days to closure
...
```

In the future, we will be cross-referencing this data with other datasources to create more interesting computational methods on our Neighborhood objects, so make sure you create useful functions that break down different subtasks you may want to repeat in the future!