import java.util.LinkedHashMap;
import java.util.Map;

public class Lines {

    private Map<String, Type> lines;

    public Lines() {
        lines = new LinkedHashMap<String, Type>();
    }

    public Map<String, Type> getLines(){
        return lines;
    }

    void addLine(String tram, Type type) {
        lines.put(tram, type);
    }

    Type getType(String key) {
        return lines.get(key);
    }

    void printLines() {
        
    }

}

