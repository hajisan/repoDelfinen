import java.util.Comparator;

public class SortKøn implements Comparator<Medlem> {
    private String køn;

    public SortKøn(String køn) {
        this.køn = køn;
    }

    @Override
    public int compare(Medlem o1, Medlem o2) {
        if(o1.getKøn().equals(o2.getKøn())) return o1.getNavn().compareToIgnoreCase(o2.getNavn());
        else return o1.getKøn().compareToIgnoreCase(o2.getKøn());
    }

    public void setKøn(String Køn) {
        this.køn = køn;
    }

    public String getKøn() {
        return køn;
    }
}
