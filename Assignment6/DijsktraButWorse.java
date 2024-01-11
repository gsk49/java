import java.util.*;

public class DijsktraButWorse extends BLPClass {
    // Variables
    private Building destination;

    public List<BuildingLengthPair> listOfPaths = new ArrayList<BuildingLengthPair>();

    // Constructor
    public DijsktraButWorse(Collection<Building> buildingList, Building origin, Building dest) {
        destination = dest;
        originPoint = origin;
        System.out.println("Instantiating a new DijsktraButWorse class");
        // List<BuildingLengthPair> temp = new ArrayList<BuildingLengthPair>();
        for (Building building : buildingList) {

            if (building == origin) {
                buildLengthPairs.add(new BuildingLengthPair(building, origin, 0));
            } else {
                buildLengthPairs.add(new BuildingLengthPair(building, origin));
            }
        }
        System.out.println("Created DijsktraButWorse list with length: " + buildingList.size());
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
        Building[] connectedArray = getConnectedBuildings(pos.building);
        for (Building b : connectedArray) {
            if (b == destination) {
                System.out.println("Adding path: " + pos.building.name + b.name);

                listOfPaths.add(new BuildingLengthPair(b, pos.building, pos.length + b.getRoadTo(pos.building).length));
            }
            ensureSmallestPath(b, pos.building, pos.length);
        }
        sortBuildingListPairs();
    }

    // helper function, specific to Dijkstra but worse
    private BuildingLengthPair getBLP(Building b, Building from) {
        for (BuildingLengthPair buildingLengthPair : buildLengthPairs) {
            if ((buildingLengthPair.building == b) && (buildingLengthPair.path == from)) {
                return buildingLengthPair;
            }
            if ((buildingLengthPair.building == from) && (buildingLengthPair.path == b)) {
                return buildingLengthPair;
            }
        }
        return null;
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
    public void printDijkstraList() {
        System.out.println("Printing Dijkstra list of length " + buildLengthPairs.size());
        for (BuildingLengthPair buildingLengthPair : buildLengthPairs) {
            System.out.println(
                    "To Building: " + buildingLengthPair.building.name +
                            ", Length of: " + buildingLengthPair.length + ", Via path of "
                            + buildingLengthPair.path.name);
        }
    }

    // Revises the distance to a building
    // Revises ONLY IF the new distance is smaller.
    // Assumes that there is a road between Element and Path
    public void ensureSmallestPath(Building element, Building path, int pathLength) throws IllegalArgumentException {
        Road r = element.getRoadTo(path);
        if (r == null) {
            throw new IllegalArgumentException("There is no connected path!");
        }
        int totalLength = r.length + pathLength;
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
    }

    public int returnSecondShortest() {
        try {
            listOfPaths.forEach((n) -> System.out.println("SSS: " + n.length + n.building.name + n.path.name));
            listOfPaths.sort(new LengthSorter());
            return listOfPaths.get(1).length;
        } catch (Exception e) {

        }
        return -1;
    }
}
