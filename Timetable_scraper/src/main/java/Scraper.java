import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scraper {
    public static void main(String[] args) throws Exception {
        final Document document = Jsoup.connect("http://www.mpk.poznan.pl/component/transport/16/SZYM42/").get();

        for (Element row : document.select("table.timetable tr")){
            final String Hour = row.select(".MpkHours").text();
            final String Minutes = row.select(".MpkMinutes").text();
            if (Hour.isEmpty() || Minutes.isEmpty()){
                continue;
            }
            System.out.println(Hour + " : " + Minutes);
        }
    }
}