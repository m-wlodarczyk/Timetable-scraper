import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper {

    private String urlEnding;

    public Scraper(){
        urlEnding = new String();
    }

    static void Timetable(String station, int type) throws IOException {
        String url = "http://www.mpk.poznan.pl/component/transport/" + station + "/";
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

        /*System.out.println(workdays);
        System.out.println(saturdays);
        System.out.println(sundays);*/
        /*for (Map.Entry<String, String> entry : sundays.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }*/
        switch (type){
            case 0:
                for (int i=0; i<24; i++) {
                    System.out.println(i + " :  " + workdays.get(Integer.toString(i)));
                }
                break;
            case 1:
                for (int i=0; i<24; i++) {
                    System.out.println(i + " :  " + saturdays.get(Integer.toString(i)));
                }
                break;
            case 2:
                for (int i=0; i<24; i++) {
                    System.out.println(i + " :  " + sundays.get(Integer.toString(i)));
                }
                break;
            default:
                break;
        }
    }

    static void Station(String lineNr) throws IOException{
        int counter = 0;
        String url = "http://www.mpk.poznan.pl/component/transport/" + lineNr + "/";
        final Document document = Jsoup.connect(url).get();
        Map<String, String> Left = new HashMap<String, String>();
        Map<String, String> Right = new HashMap<String, String>();

        for (Element row : document.select(".box_t_0 li")){
            counter++;
            if (counter <= 2) {
                continue;
            }
            final String one_row = row.outerHtml();
            Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
            Matcher matcher = pattern.matcher(one_row);
            System.out.println(row.text());
            if (matcher.find()) {
                Left.put(row.text(), matcher.group(0));
            }
        }
        counter = 0;
        for (Element row : document.select(".box_t_1 li")){
            counter++;
            if (counter <= 2) {
                continue;
            }
            final String one_row = row.outerHtml();
            Pattern pattern = Pattern.compile("[A-Z]+[0-9]+");
            Matcher matcher = pattern.matcher(one_row);
            if (matcher.find()) {
                Right.put(row.text(), matcher.group(0));
            }
        }
        //System.out.println(Right);
    }

    void Line(){

    }

    public static void main(String[] args) throws IOException {
        Station("12");
        Timetable("12/SZYM42", 0);
    }
}