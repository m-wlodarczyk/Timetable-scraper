import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper {

    private static String url;
    static ArrayList<String> direction;
    Set<String> tram_lines;
    Set<String> bus_lines;
    static Map<String, String> Left;
    static Map<String, String> Right;

    static void printMap(Map<String, String> m){
        for (Map.Entry<String, String> entry : m.entrySet()) {
            System.out.println(entry.getKey()/*+" : "+entry.getValue()*/);
        }
    }

    static String getMapValue(String key, Map<String, String> m){
        return m.get(key);
    }

    public Scraper(){
        url = "http://www.mpk.poznan.pl/component/transport/";
        direction = new ArrayList<String>(2);
        tram_lines = new LinkedHashSet<String>();
        bus_lines = new LinkedHashSet<String>();
        Left = new LinkedHashMap<String, String>();
        Right = new LinkedHashMap<String, String>();
    }

    static void Timetable() throws IOException {
        final Document document = Jsoup.connect(url).get();
        ArrayList<String> Hours = new ArrayList<String>();
        ArrayList<String> Minutes = new ArrayList<String>();
        Map<String, String> workdays = new HashMap<String, String>();
        Map<String, String> saturdays = new HashMap<String, String>();
        Map<String, String> sundays = new HashMap<String, String>();

        for (Element row : document.select("td.MpkHours")){
            final String one_row = row.text();
            Hours.add(one_row);
        }
        for (Element row : document.select("td.MpkMinutes")){
            final String one_row = row.text();
            Minutes.add(one_row);
            //System.out.println(one_row);
        }
        for (int i=0, j=0 ; i<Hours.size(); i++, j++) {
            if (j>2){
                j=0;
            }
            switch (j){
                case 0:
                    workdays.put(Hours.get(i), Minutes.get(i));
                    break;
                case 1:
                    saturdays.put(Hours.get(i), Minutes.get(i));
                    break;
                case 2:
                    sundays.put(Hours.get(i), Minutes.get(i));
                    break;
            }
        }

        for (int i=0; i<24; i++) {
            System.out.println(i + " :  " + workdays.get(Integer.toString(i)));
        }

    }

    static void Station() throws IOException{
        int counter = 0;
        final Document document = Jsoup.connect(url).get();
        for (Element row : document.select(".box_t_0 li")){
            counter++;
            if (counter <= 2) {
                direction.add(0, row.text());
                continue;
            }
            final String one_row = row.outerHtml();
            Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
            Matcher matcher = pattern.matcher(one_row);
            if (matcher.find()) {
                Left.put(row.text(), matcher.group(0));
            }
        }
        counter = 0;
        for (Element row : document.select(".box_t_1 li")) {
            counter++;
            if (counter <= 2) {
                direction.add(1, row.text());
                continue;
            }
            final String one_row = row.outerHtml();
            Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
            Matcher matcher = pattern.matcher(one_row);
            if (matcher.find()) {
                Right.put(row.text(), matcher.group(0));
            }
        }
        System.out.println("1 - " + direction.get(0));
        System.out.println("2 - " + direction.get(1));
        int pick = chooseDir();
        showStops(pick);
        String stopName = pickStation();
        switch(pick){
            case 1:
                url = url + getMapValue(stopName, Left);
                System.out.println(url);
                break;
            case 2:
                url = url + getMapValue(stopName, Right);
                System.out.println(url);
                break;
            default:
                break;
        }
    }

    void Lines() throws IOException{
        final Document document = Jsoup.connect("http://www.mpk.poznan.pl/rozklad-jazdy").get();
        for (Element row : document.select(".box_trams a")){
            final String one_row = row.text();
            tram_lines.add(one_row);
        }
        System.out.println(tram_lines);
        for (Element row : document.select(".box_buses a")){
            final String one_row = row.text();
            bus_lines.add(one_row);
        }
        System.out.println(bus_lines);
        pickLine();
    }

    void pickLine(){
        System.out.println("Choose line: ");
        String chosenLine = new String();
        Scanner scanner = new Scanner(System.in);
        chosenLine = scanner.nextLine();
        url = url + chosenLine + "/";
    }

    static String pickStation(){
        System.out.println("Choose stop: ");
        String chosenStop = new String();
        Scanner scanner = new Scanner(System.in);
        chosenStop = scanner.nextLine();
        return chosenStop;
    }

    static int chooseDir(){
        int dir;
        Scanner scanner = new Scanner(System.in);
        dir = Integer.parseInt(scanner.nextLine());
        return dir;
    }

    static void showStops(int pick){
        switch(pick){
            case 1:
                printMap(Left);
                break;
            case 2:
                printMap(Right);
                break;
            default:
                break;
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}