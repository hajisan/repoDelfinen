package Model;

import java.util.ArrayList;
import java.util.Comparator;

public class Svømmedisciplin {
    private DisciplinNavne disciplin; //Her bruges disciplinNavne enum klassen
    private ArrayList<Svømmetid> træningsTider; // Liste over træningstider
    private ArrayList<Stævnetid> stævneTider; // Liste over stævnetider
    private Svømmetid bedsteTid;


    //Constructor
    public Svømmedisciplin(DisciplinNavne disciplin) {
        this.disciplin = disciplin;
        this.træningsTider = new ArrayList<>();
        this.stævneTider = new ArrayList<>();
    }

    // Metode der registrerer en træningstid
    public void registrerTræningsTid(Svømmetid svømmetid) {
        træningsTider.add(svømmetid); //Kalder tilføj metoden får at gøre svømmetid en del af medlem
    }

    // Metode der registrerer en stævnetid
    public void registrerStævneTid(Stævnetid stævnetid) {
        stævneTider.add(stævnetid);
    }

    // Getters og setters
    public String getDisciplinNavn() {
        return disciplin.name();
    }

    public Svømmetid getBedsteTid() {
        return bedsteTid;
    }

    public void setBedsteTid(Svømmetid bedsteTid) {
        this.bedsteTid = bedsteTid;
    }

    // Returnerer de 5 bedste træningstider
    public ArrayList<Svømmetid> getTop5Træningstider() {
        træningsTider.sort(Comparator.comparing(Svømmetid::getTid));
        return new ArrayList<>(træningsTider.subList(0, Math.min(5, træningsTider.size())));
    }

    // Returnerer de 5 bedste stævnetider
    public ArrayList<Stævnetid> getTop5Stævnetider() {
        stævneTider.sort(Comparator.comparing(Svømmetid::getTid));
        return new ArrayList<>(stævneTider.subList(0, Math.min(5, stævneTider.size())));
    }

    public ArrayList<Stævnetid> getStævneTider() {
        return stævneTider;
    }

    public ArrayList<Svømmetid> getTræningsTider() {
        return træningsTider;
    }
}