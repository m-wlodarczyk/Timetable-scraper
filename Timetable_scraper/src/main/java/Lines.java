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

    Type checkType(String key) {
        return lines.get(key);
    }

    void printLines() {
        System.out.println("Tram lines: ");
        for (Map.Entry<String, Type> entry : lines.entrySet()) {
            if (entry.getValue()==Type.tram) {
                System.out.print(entry.getKey());
            }
        }
        System.out.println("Bus lines: ");
        for (Map.Entry<String, Type> entry : lines.entrySet()) {
            if (entry.getValue()==Type.bus) {
                System.out.print(entry.getKey());
            }
        }
    }

}

