import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Route {

    private String lineNumber;
    private static Map<String, String> Left;
    private static Map<String, String> Right;
    private ArrayList<String> direction;
    private type Type;

    public Route() {
        Left = new LinkedHashMap<String, String>();
        Right = new LinkedHashMap<String, String>();
        direction = new ArrayList<String>();
    }

    public Route(type Type, String lineNumber){
        Left = new LinkedHashMap<String, String>();
        Right = new LinkedHashMap<String, String>();
        direction = new ArrayList<String>();
        this.Type = Type;
        this.lineNumber = lineNumber;
    }

    public void putDirection(String dir, int index) {
        direction.add(index, dir);
    }

    public ArrayList<String> getDirection() {
        return direction;
    }

    public Map<String, String> getLeft() {
        return Left;
    }

    public Map<String, String> getRight() {
        return Right;
    }

    public static void putLeft(String key, String value) {
        Left.put(key, value);
    }

    public static void putRight(String key, String value) {
        Right.put(key, value);
    }

}
