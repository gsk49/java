import java.util.Comparator;

public class LengthSorter implements Comparator<BuildingLengthPair> {

    public int compare(BuildingLengthPair a, BuildingLengthPair b) {
        if (a.length >= b.length) {
            return b.length;
        } else {
            return a.length;
        }
    }
}
