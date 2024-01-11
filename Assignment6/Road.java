import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Building> connection = new ArrayList<Building>();
    public int length;
    
    // Constructor
    public Road(Building a, Building b, int l) {
        connection.add(a);
        connection.add(b);
        length = l;
    }

    // prevents the default construction.
    public Road() throws IllegalArgumentException {
        throw new IllegalArgumentException("The construction of Road requries two buildings as parameters");
    }

    // find connected building from building A
    public Building connectedTo(Building origin) {
        if (!connection.contains(origin)) {
            return null;
        }
        if (connection.get(0) == origin) {
            return connection.get(1);
        } else {
            return connection.get(0);
        }
    }
}
