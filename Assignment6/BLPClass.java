import java.util.*;

public abstract class BLPClass {
    // common Variables
    public List<BuildingLengthPair> buildLengthPairs = new ArrayList<BuildingLengthPair>();
    public Building originPoint;

    // Standard Methods
    // Returns an arrayList of all the connected buildings.
    public Building[] getConnectedBuildings(Building building) {
        // creates a storage system for the buldings
        List<Building> buildingList = new ArrayList<Building>();
        // finds all the connected buildings, and stores into a List
        for (Road road : building.roadList) {
            buildingList.add(road.connectedTo(building));
        }
        // converts list to array.
        return buildingList.toArray(Building[]::new);
    }

    public int getRoadLength(Building from, Building to) throws IllegalArgumentException {
        // check to make sure there is a connection.
        Road road = from.getRoadTo(to);
        if (road == null) {
            throw new IllegalArgumentException("There is no connection between these buildings");
        }
        return road.length;
    }

    // call from string version
    public int getRoadLength(String from, String to) {
        return getRoadLength(from, to);
    }

    //
    public abstract void fullPositionCalculate();

    // Position Calculate
    public abstract void positionCalculate(BuildingLengthPair pos, Building origin);

    

    // returns the list of BLPairs
    public List<BuildingLengthPair> getBLPairs() {
        return buildLengthPairs;
    }
}
