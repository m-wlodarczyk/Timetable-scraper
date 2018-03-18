import java.util.LinkedHashSet;
import java.util.Set;

public class Lines {

    private Set<String> tram_lines;
    private Set<String> bus_lines;

    public Lines() {
        tram_lines = new LinkedHashSet<String>();
        bus_lines = new LinkedHashSet<String>();
    }

    public Set<String> getTramLines(){
        return tram_lines;
    }

    public Set<String> getBusLines(){
        return bus_lines;
    }

    void addTram(String tram) {
        tram_lines.add(tram);
    }

    void addBus(String bus) {
        bus_lines.add(bus);
    }

    void printLines() {
        int i=0;
        System.out.println("Tram lines:");
        for (String s : tram_lines) {
            i++;
            if (i%8==0){
                System.out.println();
                i=0;
            }
            System.out.print(s + " ");
        }
        i=0;
        System.out.println("\nBus lines:");
        for (String s : bus_lines) {
            i++;
            if (i%8==0){
                System.out.println();
                i=0;
            }
            System.out.print(s + " ");
        }
        System.out.println();
    }

}

