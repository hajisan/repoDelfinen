import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class Crawl extends Svømmedisciplin {

    // Konstruktør
    public Crawl() {
        this.disciplinNavn = "Rygcrawl";
    }


    /*
    @Override
    public void registrerTræningsTid(Medlem medlem, Duration tid, LocalDate dato) {
        System.out.println("Registrerer tid for disciplinen: " + disciplinNavn);
        Svømmetid nySvømmetid = new Svømmetid(disciplinNavn, tid, dato);
        medlem.tilføjTræningstid(nySvømmetid); //Kalder tilføj metoden får at gøre svømmetid en del af medlem
        System.out.println("Registreret ny tid for " + disciplinNavn + ": " + nySvømmetid);
    }

    public void registrerStævneTid(Medlem medlem, Duration tid, LocalDate dato, String lokalitet) {
        System.out.println("Registrerer tid for disciplinen: " + disciplinNavn);
        Stævnetid nyStævnetid = new Stævnetid(tid, disciplinNavn,  dato, lokalitet);
        medlem.tilføjStævnetid(nyStævnetid);
        System.out.println("Registreret ny tid for " + disciplinNavn + ": " + nyStævnetid);
    }



    @Override
    public ArrayList<Svømmetid> getTopResultater(ArrayList<Medlem> medlemmer) {
        ArrayList<Svømmetid> alleTider = new ArrayList<>();

        // Gå igennem alle medlemmer og hent tider for disciplinen "Rygcrawl"
        for (Medlem medlem : medlemmer) {
            for (Svømmetid tid : medlem.getSvømmetider()) {
                if (tid.getDisciplin().equals(disciplinNavn)) {
                    alleTider.add(tid);
                }
            }
        }
        return SortTop5.top5Svømmere(String disciplin);
    }

     */
}
