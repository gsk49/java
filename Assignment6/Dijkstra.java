import java.util.*;

public class Dijkstra extends BLPClass {
    // Constructors

    public Dijkstra(Collection<Building> buildingList, Building origin) {
        originPoint = origin;
        System.out.println("Instantiating a new Dijkstra class");
        for (Building building : buildingList) {
            if (building == origin) {
                buildLengthPairs.add(new BuildingLengthPair(building, origin, 0));
            } else {
                buildLengthPairs.add(new BuildingLengthPair(building, origin));
            }
        }
        // sort the buildingLengthPair list.
        sortBuildingListPairs();
    }

    // Methods

    // finds connected buildings, checks the distance on these buildings, and revises the distance.
    // Removes the origin building from the list, adds it to the "already scanned" list.

    public void fullPositionCalculate() {
        // traverses and modifies buildingPairList

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
    public void printDijkstraList() {
        System.out.println("Printing Dijkstra list of length " + buildLengthPairs.size());
        for (BuildingLengthPair buildingLengthPair : buildLengthPairs) {
            System.out.println("To Building: " + buildingLengthPair.building.name + ", Length of: " + buildingLengthPair.length + ", Via path of " + buildingLengthPair.path.name);
        }
    }
    // Revises the distance to a building
    // Revises ONLY IF the new distance is smaller.
    // Assumes that there is a road between Element and Path
    public void ensureSmallestPath(Building element, Building path, int pathLength) throws IllegalArgumentException {
        Road road = element.getRoadTo(path);
        if (road == null) {
            throw new IllegalArgumentException("There is no connected path!");
        }
        int totalLength = road.length + pathLength;
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
    }
}
