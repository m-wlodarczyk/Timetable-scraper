import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class mainClass {
    public static Scraper scraper = new Scraper();

    static void foo() throws IOException {
        String line, stop;
        int dir;
        Map<String, String> tmp = new HashMap<String, String>();
        scraper.Lines();
        System.out.print("Choose line: ");
        Scanner s = new Scanner(System.in);
        line = s.nextLine();
        scraper.Station();
        System.out.println("1 - " + scraper.direction.get(0));
        System.out.println("2 - " + scraper.direction.get(1));
        dir = Integer.parseInt(s.nextLine());
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
            scraper.Timetable();
            //System.out.println(scraper.getMapValue(stop, scraper.Left));
        }
        else {
            System.out.println("Pick ur stop");
            stop = s.nextLine();
            String ending = line + "/" + scraper.getMapValue(stop, scraper.Right);
            System.out.println(ending);
            scraper.Timetable();
            //System.out.println(scraper.getMapValue(stop, scraper.Right));
        }
    }

    public static void main(String[] args) throws IOException {
        scraper.Lines();
        scraper.Station();
        scraper.Timetable();
    }
}
