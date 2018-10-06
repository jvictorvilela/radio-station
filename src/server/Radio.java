package server;

/**
 *
 * @author victor
 */
public class Radio {
    int numStations;
    String[] files;
    public Radio(String[] files) {
        this.files = files;
        numStations = files.length;
    }
    
    public int getNumStations() {
        return numStations;
    }
}
