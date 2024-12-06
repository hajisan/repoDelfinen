import java.util.Comparator;

public class SortAlder implements Comparator<Medlem> {

    public SortAlder() {

    }

    @Override
    public int compare(Medlem o1, Medlem o2) {
        return o1.getAlder() - o2.getAlder();
    }
}
