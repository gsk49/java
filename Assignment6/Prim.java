import java.util.*;

public class Prim extends BLPClass {
    // Variables
    private List<Building> visited = new ArrayList<Building>();

    // Constructors
    public Prim() throws IllegalArgumentException {
        throw new IllegalArgumentException("Cannot construct Prim without parameters");
    }

    public Prim(Collection<Building> buildingList, Building origin) {
        originPoint = origin;
        System.out.println("Instantiating a new Prim class");
        for (Building building : buildingList) {

            if (building == origin) {
                buildLengthPairs.add(new BuildingLengthPair(building, origin, 0));
            } else {
                buildLengthPairs.add(new BuildingLengthPair(building, origin));
            }
        }
        System.out.println("Created Prim list with length: " + buildingList.size());
        // sort the buildingLengthPair list.
        sortBuildingListPairs();
    }

    // Methods
    // finds connected buildings, checks the distance on these buildings, and
    // revises the distance.
    // Removes the origin building from the list, adds it to the "already scanned"
    // list.
    public void fullPositionCalculate() {
        // fully traverses and completes the modifications to buildingPairList

        List<BuildingLengthPair> temp = new ArrayList<BuildingLengthPair>(buildLengthPairs);
        int maxSize = temp.size();
        for (int i = 0; i < maxSize; i++) {
            positionCalculate(temp.get(0), originPoint);
            temp.remove(0);
        }

    }

    // For a single position, from an origin, re-calculate parts of the list.
    public void positionCalculate(BuildingLengthPair pos, Building origin) {
        visited.add(pos.building);
        Building[] connectedArray = getConnectedBuildings(pos.building);
        for (Building b : connectedArray) {
            if (!visited.contains(b))
                ensureSmallestPath(b, pos.building, pos.length);
        }
        sortBuildingListPairs();
    }

    // Returns the buildingListPair info on a particular building.
    public BuildingLengthPair findBLPair(Building b) {
        for (BuildingLengthPair buildingLengthPair : buildLengthPairs) {
            if (buildingLengthPair.building == b) {
                return buildingLengthPair;
            }
        }
        return null;
    }

    // Sorts the buildingList based on length
    public void sortBuildingListPairs() {
        buildLengthPairs.sort(new LengthSorter());
    }

    // prints the building-length pair
    public void printPrimList() {
        System.out.println("Printing Prim list of length " + buildLengthPairs.size());
        for (BuildingLengthPair buildingLengthPair : buildLengthPairs) {
            System.out.println(
                    "To Building: " + buildingLengthPair.building.name +
                            ", Length of: " + buildingLengthPair.length + ", Via path of "
                            + buildingLengthPair.path.name);
        }
    }

    public int minTotalLength() {
        int len = 0;
        for (BuildingLengthPair buildingLengthPair : buildLengthPairs) {
            if (!(buildingLengthPair.length >= 1073741823))
                len += buildingLengthPair.length;
        }
        return len;
    }

    // Revises the distance to a building
    // Revises ONLY IF the new distance is smaller.
    // Assumes that there is a road between Element and Path
    public void ensureSmallestPath(Building element, Building path, int pathLength) throws IllegalArgumentException {
        Road r = element.getRoadTo(path);
        if (r == null) {
            throw new IllegalArgumentException("There is no connected path!");
        }
        int totalLength = r.length;
        // buildLengthPairs.forEach();
        // Find the Building 'Element' in the Dijkstra list
        for (BuildingLengthPair buildingLengthPair : buildLengthPairs) {
            if (buildingLengthPair.building == element) {
                // Check to make sure the new path is smaller.
                if (buildingLengthPair.length > totalLength) {
                    buildingLengthPair.length = totalLength;
                    buildingLengthPair.path = path;
                }
                return;
            }
        }
        throw new IllegalArgumentException("Building provided does not exist in list.");
    }
}
