import java.util.*;

class Map {
    // Variables
    public List<Building> buildingsOnMap = new LinkedList<Building>();

    public static void main(String[] args) {
        Map map = new Map();
        // add building
        map.addBuilding("A");
        map.addBuilding("B");
        map.addBuilding("C");
        map.addBuilding("D");
        map.addBuilding("E");
        map.addBuilding("F");
        map.addBuilding("G");

        map.addRoad("A", "B", 1);
        map.addRoad("A", "D", 1);
        map.addRoad("A", "F", 5);
        map.addRoad("A", "C", 2);
        map.addRoad("B", "C", 1);
        map.addRoad("D", "C", 2);
        map.addRoad("D", "F", 3);
        map.addRoad("D", "E", 2);
        map.addRoad("D", "G", 4);
        map.addRoad("C", "E", 3);
        map.addRoad("E", "G", 1);
        map.addRoad("F", "G", 3);
        /*
         * map.addRoads("D", tempList, 3);
         * map.printBuildingsRoads();
         * map.removeBuilding("D");
         * map.printBuildingsRoads();
         * map.removeRoad("A", "B");
         * map.printBuildingsRoads();
         * map.addBuilding("E");
         * map.addBuilding("F");
         * map.addBuilding("G");
         * map.addBuilding("H");
         * map.addBuilding("I");
         * map.addBuilding("J");
         * map.addBuilding("K");
         * map.addRoad("K", "J", 3);
         * map.addRoad("J", "I", 3);
         * map.addRoad("I", "H", 3);
         * map.addRoad("H", "A", 10);
         * map.addRoad("A", "B", 2);
         * map.addRoad("B", "C", 6);
         * map.addRoad("A", "J", 4);
         * map.addRoad("F", "G", 2);
         * map.addRoad("F", "E", 5);
         * map.addRoad("A", "F", 22);
         */
        map.printBuildingsRoads();
        System.out.println("About to run Dijsktra's, " + map.buildingsOnMap.size() + " Buildings on the map");
        map.buildingsOnMap.forEach((N) -> System.out.println(N.name));
        Dijkstra d = new Dijkstra(map.buildingsOnMap, map.searchBuilding("A"));
        d.printDijkstraList();
        d.fullPositionCalculate();
        d.printDijkstraList();
        System.out.println("About to run Prims's, " + map.buildingsOnMap.size() + " Buildings on the map");
        map.buildingsOnMap.forEach((N) -> System.out.println(N.name));
        Prim p = new Prim(map.buildingsOnMap, map.searchBuilding("A"));
        p.printPrimList();
        p.fullPositionCalculate();
        p.printPrimList();
        System.out.println("Printing Prim's minimum length: [SHOULD BE 7] " + map.minimumTotalLength());
        System.out.println(
                "Printing second shortest path from A to D: [SHOULD BE 4] " + map.secondShortestPath("A", "D"));
    }

    // Additional Methods
    public final Building searchBuilding(String name) {
        for (Building building : buildingsOnMap) {
            if (building.name.equals(name)) {
                return building;
            }
        }
        return null;
    }

    public final Building searchBuilding(Building building) {
        return searchBuilding(building.name);
    }


    public final boolean buildingIsOnMap(String name) {
        if (searchBuilding(name) != null) {
            return true;
        } else {
            return false;
        }
    }

    public final void printBuildingsRoads() {
        System.out.println("Printing all buildings and connections");
        for (Building building : buildingsOnMap) {
            List<Road> connections = building.roadList;
            System.out.print("Building: " + building.name + ", Connections: ");
            for (Road road : connections) {
                System.out.print("[" + road.connection.get(0).name + " TO " + road.connection.get(1).name + ", LENGTH: "
                        + road.length + "]");
            }
            System.out.println();
        }
    }

    // Required Methods
    // adds a single building with 'name' to the map.
    // check if there is already a building, if so, throw IllegalArgumentException
    public final boolean addBuilding(String name) throws IllegalArgumentException {
        // check if duplicate building.
        if ((searchBuilding(name) != null)) {
            throw new IllegalArgumentException();
        }
        // addBuilding.
        buildingsOnMap.add(new Building(name));
        return true;

    }

    public final boolean addRoad(String fromBuilding, String toBuilding, int length) throws IllegalArgumentException {
        // ensure both buildings exist.
        if (!(buildingIsOnMap(fromBuilding) && buildingIsOnMap(toBuilding))) {
            throw new IllegalArgumentException("At least one of the buildings does not exist");
        }
        Building fromBuild = searchBuilding(fromBuilding);
        Building toBuild = searchBuilding(toBuilding);
        // handle duplicate roads.
        for (Road road : fromBuild.roadList) {
            if (road.connection.contains(toBuild)) {
                return false;
            }
        }
        Road newRoad = new Road(fromBuild, toBuild, length);
        fromBuild.roadList.add(newRoad);
        toBuild.roadList.add(newRoad);
        return true;
    }


    public final boolean addRoads(String fromBuilding, Collection<String> toBuildings, int length)
            throws IllegalArgumentException {
        if (!buildingIsOnMap(fromBuilding)) {
            throw new IllegalArgumentException("At least one of the buildings does not exist");
        }
        for (String s : toBuildings) {
            if (!buildingIsOnMap(s)) {
                throw new IllegalArgumentException("Building: " + s + " does not exist on the map");
            }
        }
        // Building fromBuild = searchBuilding(fromBuilding);
        for (String s : toBuildings) {
            addRoad(fromBuilding, s, length);
        }
        return true;
    }


    public final boolean removeBuilding(String name) throws IllegalArgumentException {
        // ensure that the building exists
        if (!buildingIsOnMap(name)) {
            throw new IllegalArgumentException("Building does not exist");
        }
        // get a list of the buildings it has roads to
        Building delBuilding = searchBuilding(name);
        System.out.println("Removing building: " + delBuilding.name);
        List<Building> connectedBuildings = new ArrayList<Building>();
        // check all roads associated with the building to be deleted.
        for (Road road : delBuilding.roadList) {
            Building tempBuilding = road.connectedTo(delBuilding);
            // if any of these roads return a non-null
            if (tempBuilding != null)
                connectedBuildings.add(tempBuilding);
        }
        // remove the relevant connection for each of these buildings
        for (Building building : connectedBuildings) {
            // find the road that is connected
            for (Road road : building.roadList) {
                if (road.connection.contains(delBuilding)) {
                    building.roadList.remove(road);
                }
            }
        }
        // remove this building from the map.
        buildingsOnMap.remove(delBuilding);
        return true;
    }


    public final boolean removeRoad(String fromBuilding, String toBuilding) throws IllegalArgumentException {
        if (!(buildingIsOnMap(fromBuilding) && buildingIsOnMap(toBuilding))) {
            throw new IllegalArgumentException("At least one of the buildings does not exist");
        }
        Building fromBuild = searchBuilding(fromBuilding);
        Building toBuild = searchBuilding(toBuilding);
        System.out.println("Removing road: " + fromBuild.name + " to " + toBuild.name);
        for (Road r : fromBuild.roadList) {
            if (r.connectedTo(fromBuild) == toBuild) {
                fromBuild.roadList.remove(r);
            }
        }
        for (Road r : toBuild.roadList) {
            if (r.connectedTo(toBuild) == fromBuild) {
                toBuild.roadList.remove(r);
            }
        }
        return true;
    }


    // Uses Dijkstra's shortest path algorithm
    // Uses heap
    public final int shortestLength(String source, String destination) throws IllegalArgumentException {
        if (!(buildingIsOnMap(source) && buildingIsOnMap(destination))) {
            throw new IllegalArgumentException("At least one of the buildings does not exist");
        }
        Dijkstra d = new Dijkstra(buildingsOnMap, this.searchBuilding(source));
        d.fullPositionCalculate();
        BuildingLengthPair blp = d.findBLPair(this.searchBuilding(destination));
        if (blp == null) {
            throw new IllegalArgumentException("Could not find destination in the Dijkstra list");
        }
        return blp.length; // unimplemented
    }


    public final List<String> shortestPathList(String source, String destination) throws IllegalArgumentException {
        if (!(buildingIsOnMap(source) && buildingIsOnMap(destination))) {
            throw new IllegalArgumentException("At least one of the buildings does not exist");
        }
        Dijkstra d = new Dijkstra(buildingsOnMap, this.searchBuilding(source));
        d.fullPositionCalculate();
        List<String> temp = new ArrayList<String>();
        d.getBLPairs().removeIf(n -> (n.length == 2147483647));
        d.getBLPairs().forEach(n -> temp.add(n.building.name));
        return temp;
    }


    // Uses Prim's Algorithm
    public final int minimumTotalLength() {
        Prim p = new Prim(buildingsOnMap, searchBuilding("A"));
        p.fullPositionCalculate();
        return p.minTotalLength();
    }

    public final int secondShortestPath(String source, String destination) throws IllegalArgumentException {
        if (!(buildingIsOnMap(source) && buildingIsOnMap(destination))) {
            throw new IllegalArgumentException("At least one of the buildings does not exist");
        }
        DijsktraButWorse dbw = new DijsktraButWorse(buildingsOnMap, searchBuilding(source),
                searchBuilding(destination));
        dbw.fullPositionCalculate();
        return dbw.returnSecondShortest();// unimplemented
    }

}
