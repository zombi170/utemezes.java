import java.util.Comparator;

public class StartComp implements Comparator<Input> {
    public int compare(Input a, Input b) {
        return Integer.compare(a.start, b.start);
    }
}