import java.time.Duration;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;

public class Svømmedisciplin {
    private disciplinNavne disciplin; //Her bruges disciplinNavne enum klassen
    protected ArrayList<Svømmetid> træningsTider; // Liste over træningstider
    protected ArrayList<Stævnetid> stævneTider; // Liste over stævnetider
    protected Svømmetid bedsteTid;


    // Constructor
    public Svømmedisciplin(disciplinNavne disciplin) {
        this.disciplin = disciplin;
        this.træningsTider = new ArrayList<>();
        this.stævneTider = new ArrayList<>();
    }

    //metode til at registrere træningstid
    public void registrerTræningsTid(Medlem medlem, Duration tid, LocalDate dato) {
        System.out.println("Registrerer tid for disciplinen: " + disciplin);
        Svømmetid nySvømmetid = new Svømmetid(disciplin, tid, dato);
        medlem.tilføjTræningstid(nySvømmetid); //Kalder tilføj metoden får at gøre svømmetid en del af medlem
        System.out.println("Registreret ny tid for " + disciplin + ": " + nySvømmetid);
    }

    //metode til at registrere stævnetid
    public void registrerStævneTid(Medlem medlem, Duration tid, LocalDate dato, String lokalitet) {
        System.out.println("Registrerer tid for disciplinen: " + disciplin);
        Stævnetid nyStævnetid = new Stævnetid(disciplin, tid, dato, lokalitet);
        medlem.tilføjStævnetid(nyStævnetid);
        System.out.println("Registreret ny tid for " + disciplin + ": " + nyStævnetid);
    }

    //metode til at få top 5
    public ArrayList<Svømmetid> getTopResultater(ArrayList<Medlem> medlemmer) {
        ArrayList<Svømmetid> alleTider = new ArrayList<>();

        // Gå igennem alle medlemmer og hent tider for den givne disciplin
        for (Medlem medlem : medlemmer) {
            for (Svømmetid tid : medlem.getSvømmetider()) {
                if (tid.getDisciplin().equals(disciplin)) {
                    alleTider.add(tid);
                }
            }
        }
        return (ArrayList<Svømmetid>) SortTop5.top5Svømmere(disciplin); //sortering af resultater
    }

    //metode til at registrere flere tider for et medlem
    public void registrerFlereTider(Medlem medlem) {
        if (medlem == null) {
            throw new IllegalArgumentException("Medlem må ikke være null.");
        }

        Scanner sc = new Scanner(System.in);
        boolean tilføjFlere = true;

        System.out.println("Registrerer flere tider for disciplin: " + disciplin + " for medlem: " + medlem.getNavn());
        while (tilføjFlere) {
            try {
                // Indtast tid
                System.out.println("Indtast tid i minutter:");
                long minutter = sc.nextLong();

                System.out.println("Indtast tid i sekunder:");
                long sekunder = sc.nextLong();
                sc.nextLine(); // Rens scanner buffer

                // Indtast dato
                System.out.println("Indtast dato (dd/mm/yyyy):");
                String datoInput = sc.nextLine();
                LocalDate dato = KonsolHandler.stringToLocalDate(datoInput);

                // Opret og tilføj svømmetid
                Svømmetid nySvømmetid = new Svømmetid(disciplin, Duration.ofMinutes(minutter).plusSeconds(sekunder), dato);
                medlem.getSvømmetider().add(nySvømmetid);

                System.out.println("Svømmetid tilføjet: " + formatDuration(nySvømmetid.getTid()) + " på dato: " + dato);

                /* Opdater bedste tid
                opdaterBedsteTid(medlem);
                 */

                // Spørg, om brugeren vil tilføje flere tider
                System.out.println("Vil du tilføje flere tider? (ja/nej)");
                String svar = sc.nextLine();
                tilføjFlere = svar.equalsIgnoreCase("ja");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Registrering af tider afsluttet for medlem: " + medlem.getNavn());
    }

//    //Tasks 7.3, som er blevet slettet, metoden er blevet udkommenteret
//    // Hjælpefunktion til at finde og opdatere den bedste tid
//    private void opdaterBedsteTid(Medlem medlem) {
//        Svømmetid bedsteTid = medlem.getSvømmetider().stream()
//                .filter(t -> t.getDisciplin().equalsIgnoreCase(disciplinNavn))
//                .min((t1, t2) -> t1.getTid().compareTo(t2.getTid()))
//                .orElse(null);
//    }

    // Hjælpefunktion til at formatere Duration som en læsbar streng
    private String formatDuration(Duration duration) {
        long minutter = duration.toMinutes();
        long sekunder = duration.minusMinutes(minutter).getSeconds();
        return String.format("%02d:%02d", minutter, sekunder);
    }

    // Returnerer disciplinens navn
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