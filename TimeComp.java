import java.util.Comparator;

public class TimeComp implements Comparator<Input> {
    public int compare(Input a, Input b) {
        return Integer.compare(a.time, b.time);
    }
}