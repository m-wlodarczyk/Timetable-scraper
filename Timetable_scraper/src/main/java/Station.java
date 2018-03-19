import java.util.HashMap;
import java.util.Map;

public class Station extends Route {

    String stationName;
    Map<String, String> workdays;
    Map<String, String> saturdays;
    Map<String, String> sundays;

    public Station() {
        workdays = new HashMap<String, String>();
        saturdays = new HashMap<String, String>();
        sundays = new HashMap<String, String>();
    }

    public Station(Type Type, String lineNumber, String stationName) {
        super(Type, lineNumber);
        this.stationName = stationName;
        workdays = new HashMap<String, String>();
        saturdays = new HashMap<String, String>();
        sundays = new HashMap<String, String>();
    }

    public String getStationName() {
        return stationName;
    }

    public Map<String, String> getWorkdays() {
        return workdays;
    }

    public Map<String, String> getSaturdays() {
        return saturdays;
    }

    public Map<String, String> getSundays() {
        return sundays;
    }

    void workdaysPut(String Hour, String Minutes) {
        workdays.put(Hour, Minutes);
    }

    void saturdaysPut(String Hour, String Minutes) {
        saturdays.put(Hour, Minutes);
    }

    void sundaysPut(String Hour, String Minutes) {
        sundays.put(Hour, Minutes);
    }

}
