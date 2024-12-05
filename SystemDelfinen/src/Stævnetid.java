import java.time.Duration;
import java.time.LocalDate;

public class Stævnetid extends Svømmetid{
    private String lokalitet;

    public Stævnetid(Duration tid, String disciplin, LocalDate dato, String lokalitet) {
        super(tid, disciplin, dato);
        this.lokalitet = lokalitet;
    }

    public String getLokalitet() {
        return lokalitet;
    }

    public String setLokalitet() {
        return lokalitet;
    }
}
