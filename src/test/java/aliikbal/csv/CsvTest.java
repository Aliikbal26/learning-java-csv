package aliikbal.csv;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

public class CsvTest {

    @Test
    void createCSV() throws IOException {
        StringWriter writer = new StringWriter();

        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
        printer.printRecord("Ali", "Ikbal", "23");
        printer.printRecord("Rino", "Aryanto", "21");
        printer.flush();

        System.out.println(writer.getBuffer().toString());
    }

    @Test
    void readCSV() throws IOException {
        Path path = Path.of("sample.csv");
        Reader reader = Files.newBufferedReader(path);

        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
        for (CSVRecord csvRecord : parser) {
            System.out.println("First Name : " + csvRecord.get(0));
            System.out.println("Last Name : " + csvRecord.get(1));
            System.out.println("Age : " + csvRecord.get(2));
        }
    }

    @Test
    void readWithHeader() throws IOException {
        Path path = Path.of("sample.csv");
        Reader reader = Files.newBufferedReader(path);

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();
        CSVParser parser = new CSVParser(reader, format);
        for (CSVRecord csvRecord : parser) {
            System.out.println("First Name : " + csvRecord.get("First Name"));
            System.out.println("Last Name : " + csvRecord.get("Last Name"));
            System.out.println("Age : " + csvRecord.get("Age"));
        }
    }

    @Test
    void createCsvWithHeader() throws IOException {

        StringWriter writer = new StringWriter();

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader("FIRST NAME", "LAST NAME", "AGE").build();

        CSVPrinter printer = new CSVPrinter(writer, format);
        printer.printRecord("Ali", "Ikbal", "23");
        printer.printRecord("Rino", "Aryanto", "21");
        printer.flush();

        System.out.println(writer.getBuffer().toString());
    }

    @Test
    void createCsvWithTAB() throws IOException {

        StringWriter writer = new StringWriter();

        CSVFormat format = CSVFormat.TDF.builder().setHeader("FIRST NAME", "LAST NAME", "AGE").build();

        CSVPrinter printer = new CSVPrinter(writer, format);
        printer.printRecord("Ali", "Ikbal", "23");
        printer.printRecord("Rino", "Aryanto", "21");
        printer.flush();

        System.out.println(writer.getBuffer().toString());
    }
}
