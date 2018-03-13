import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class mainClass {
    public static Scraper scraper = new Scraper();

    public static void main(String[] args) throws IOException {
        String line, stop;
        int dir;
        Map<String, String> tmp = new HashMap<String, String>();
        scraper.Lines();
        System.out.print("Choose line: ");
        Scanner s = new Scanner(System.in);
        line = s.nextLine();
        scraper.Station(line);
        System.out.println("1 - " + scraper.direction.get(0));
        System.out.println("2 - " + scraper.direction.get(1));
        dir = s.nextInt();
        if (dir==1){
            scraper.printMap(scraper.Left);
            tmp = scraper.Left;
        } else if (dir==2){
            scraper.printMap(scraper.Right);
            tmp = scraper.Right;
        }
        if (dir==1){
            System.out.println("Pick ur stop");
            stop = s.next();
            String ending = line + "/" + scraper.getMapValue(stop, scraper.Left);
            System.out.println(ending);
            scraper.Timetable(ending, 0);
            //System.out.println(scraper.getMapValue(stop, scraper.Left));
        }
        else {
            System.out.println("Pick ur stop");
            stop = s.next();
            String ending = line + "/" + scraper.getMapValue(stop, scraper.Right);
            System.out.println(ending);
            scraper.Timetable(ending, 0);
            //System.out.println(scraper.getMapValue(stop, scraper.Right));
        }
    }
}
