import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Route {

    private int lineNumber;
    private static Map<String, String> Left;
    private static Map<String, String> Right;
    private ArrayList<String> direction;
    private Type Type;

    public Route() {
        Left = new LinkedHashMap<String, String>();
        Right = new LinkedHashMap<String, String>();
        direction = new ArrayList<String>();
    }

    public Route(Type Type, String lineNumber){
        Left = new LinkedHashMap<String, String>();
        Right = new LinkedHashMap<String, String>();
        direction = new ArrayList<String>();
        this.Type = Type;
        this.lineNumber = Integer.parseInt(lineNumber);
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = Integer.parseInt(lineNumber);
        if (this.lineNumber < 29) {

        }
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
