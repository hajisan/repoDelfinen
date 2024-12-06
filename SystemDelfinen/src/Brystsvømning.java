import java.util.ArrayList;

public class Brystsvømning extends Svømmedisciplin{

    // Konstruktør
    public Brystsvømning() {
        this.disciplinNavn = "Brystsvømning";
    }

    /*
    @Override
    public void registrerTræningsTid(Medlem medlem, double tid, String dato) {
        System.out.println("Registrerer tid for disciplinen: " + disciplinNavn);
        Svømmetid nySvømmetid = new Svømmetid(disciplinNavn, tid, dato);
        medlem.tilføjTræningstid(nySvømmetid);
        System.out.println("Registreret ny tid for " + disciplinNavn + ": " + nySvømmetid);
    }

    @Override
    public void registrerStævneTid(Medlem medlem, double tid, String dato) {
        System.out.println("Registrerer tid for disciplinen: " + disciplinNavn);
        Svømmetid nySvømmetid = new Svømmetid(disciplinNavn, tid, dato);
        medlem.tilføjStævnetid(nySvømmetid);
        System.out.println("Registreret ny tid for " + disciplinNavn + ": " + nySvømmetid);
    }



    @Override
    public ArrayList<Svømmetid> getTopResultater(ArrayList<Medlem> medlemmer) {
        ArrayList<Svømmetid> alleTider = new ArrayList<>();

        // Gå igennem alle medlemmer og hent tider for disciplinen "Brystsvømning"
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
