import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper {

    private static Document document;
    private static String url;
    static ArrayList<String> direction;
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
        document = null;
    }

    static void getTimetable() throws IOException {
        document = Jsoup.connect(url).get();
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

    static void getStations(Station station, String lineNumber) throws IOException {
        int counter = 0;
        url = url + lineNumber + "/";
        document = Jsoup.connect(url).get();
        for (Element row : document.select(".box_t_0 li")){
            counter++;
            if (counter <= 2) {
                station.putDirection(row.text(), 0);
                continue;
            }
            final String one_row = row.outerHtml();
            Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
            Matcher matcher = pattern.matcher(one_row);
            if (matcher.find()) {
                String _row = row.text();
                station.putLeft(_row, matcher.group(0));
            }
        }
        counter = 0;
        for (Element row : document.select(".box_t_1 li")) {
            counter++;
            if (counter <= 2) {
                station.putDirection(row.text(), 1);
                continue;
            }
            final String one_row = row.outerHtml();
            Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
            Matcher matcher = pattern.matcher(one_row);
            if (matcher.find()) {
                station.putRight(row.text(), matcher.group(0));
            }
        }

    }

    void getLines(Lines lines) throws IOException{
        document = Jsoup.connect("http://www.mpk.poznan.pl/rozklad-jazdy").get();
        for (Element row : document.select(".box_trams a")){
            final String one_row = row.text();
            lines.addTram(one_row);
        }
        for (Element row : document.select(".box_buses a")){
            final String one_row = row.text();
            lines.addBus(one_row);
        }
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
}