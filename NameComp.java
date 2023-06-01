import java.util.Comparator;

public class NameComp implements Comparator<Input>{
    public int compare(Input a, Input b) {
        return Character.compare(a.sign, b.sign);
    }
}

