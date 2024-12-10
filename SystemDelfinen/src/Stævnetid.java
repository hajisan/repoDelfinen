import java.time.Duration;
import java.time.LocalDate;

public class Stævnetid extends Svømmetid{
    private String lokalitet;

    //constructor der tager cunstructor fra dens super klasse og tilsætter en lokalitet i string format
    public Stævnetid(disciplinNavne disciplin, Duration tid, LocalDate dato, String lokalitet) {
        super(disciplin, tid, dato);
        this.lokalitet = lokalitet;
    }

    public String getLokalitet() {
        return lokalitet;
    }

    public String setLokalitet() {
        return lokalitet;
    }
}
