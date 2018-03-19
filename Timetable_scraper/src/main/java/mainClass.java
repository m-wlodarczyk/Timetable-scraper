import java.io.IOException;
import java.util.Scanner;

public class mainClass {

    public static Scraper scraper = new Scraper();

    public static Lines lines = new Lines();

    public static Station station = new Station();

    public static String pickLine() {
        System.out.print("Choose line: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) throws IOException {
        scraper.getLines(lines);
        lines.printLines();
        scraper.getStations(station, pickLine());
    }
}
