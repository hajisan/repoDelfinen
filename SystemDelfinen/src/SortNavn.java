import java.util.Comparator;

public class SortNavn implements Comparator<Medlem> {

    public SortNavn() {

    }

    @Override
    public int compare(Medlem o1, Medlem o2) {
        return o1.getNavn().compareToIgnoreCase(o2.getNavn());
    }
}
