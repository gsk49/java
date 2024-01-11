import java.util.*;

public class Building {
    public String name;
    public List<Road> roadList = new LinkedList<Road>();

    public Building(String n) {
        name = n;
    }

    // Methods

    // Takes the name of the other building and returns with the reference to the
    // building.
    public Building findConnection(String otherBuilding) {
        for (Road road : roadList) {
            if (road.connectedTo(this).name == otherBuilding) {
                return road.connectedTo(this);
            }
        }
        return null;
    }

    // Takes the building reference and returns with the reference of the other
    // building if found.
    public Building findConnection(Building otherBuilding) {
        return findConnection(otherBuilding.name);
    }

    // returns the road between these two buildings
    public Road getRoadTo(Building otherBuilding) {
        for (Road road : roadList) {
            if (road.connectedTo(this) == otherBuilding) {
                return road;
            }
        }
        return null;
    }
}
