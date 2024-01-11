import java.util.*;

public class BuildingLengthPair {
    public Building building;  // Should never be null.
    public int length = Integer.MAX_VALUE / 2;
    public Building path;

    public BuildingLengthPair(Building b, Building p) {
        building = b;
        path = p;
    }

    public BuildingLengthPair(Building b, Building p, int l) {
        building = b;
        path = p;
        length = l;
    }

}