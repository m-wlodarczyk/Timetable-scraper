import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;

public class Scraper {
    public static void main(String[] args) throws Exception {
        final Document document = Jsoup.connect("http://www.mpk.poznan.pl/component/transport/16/SZYM42/").get();
        ArrayList<String> rows = new ArrayList<String>();
        for (Element row : document.select("table.timetable tr")){
            final String one_row = row.text();
            rows.add(one_row);
        }
    }
}