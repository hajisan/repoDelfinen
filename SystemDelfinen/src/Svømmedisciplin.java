import java.time.Duration;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

abstract class Svømmedisciplin {
    private disciplinNavne disciplinNavne; //Her bruges disciplinNavne enum klassen
    protected ArrayList<Svømmetid> træningsTider; // Liste over træningstider
    protected ArrayList<Svømmetid> stævneTider; // Liste over stævnetider
    protected Svømmetid bedsteTid;

    // Constructor
    public Svømmedisciplin() {
        this.træningsTider = new ArrayList<>();
        this.stævneTider = new ArrayList<>();
    }

    public void registrerTræningsTid(Medlem medlem, Duration tid, LocalDate dato) {
        System.out.println("Registrerer tid for disciplinen: " + disciplinNavne);
        Svømmetid nySvømmetid = new Svømmetid(disciplinNavne, tid, dato);
        medlem.tilføjTræningstid(nySvømmetid); //Kalder tilføj metoden får at gøre svømmetid en del af medlem
        System.out.println("Registreret ny tid for " + disciplinNavne + ": " + nySvømmetid);
    }

    public void registrerStævneTid(Medlem medlem, Duration tid, LocalDate dato, String lokalitet) {
        System.out.println("Registrerer tid for disciplinen: " + disciplinNavne);
        Stævnetid nyStævnetid = new Stævnetid(tid, disciplinNavne,  dato, lokalitet);
        medlem.tilføjStævnetid(nyStævnetid);
        System.out.println("Registreret ny tid for " + disciplinNavne + ": " + nyStævnetid);
    }

    public ArrayList<Svømmetid> getTopResultater(ArrayList<Medlem> medlemmer) {
        ArrayList<Svømmetid> alleTider = new ArrayList<>();

        // Gå igennem alle medlemmer og hent tider for den givne disciplin
        for (Medlem medlem : medlemmer) {
            for (Svømmetid tid : medlem.getSvømmetider()) {
                if (tid.getDisciplin().equals(disciplinNavne)) {
                    alleTider.add(tid);
                }
            }
        }
        return Sort.top5Svømmere(String disciplinNavne);
    }

    public void registrerFlereTider(Medlem medlem) {
        if (medlem == null) {
            throw new IllegalArgumentException("Medlem må ikke være null.");
        }

        Scanner sc = new Scanner(System.in);
        boolean tilføjFlere = true;

        System.out.println("Registrerer flere tider for disciplin: " + disciplinNavne + " for medlem: " + medlem.getNavn());
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
                Svømmetid nySvømmetid = new Svømmetid(Duration.ofMinutes(minutter).plusSeconds(sekunder), disciplinNavne, dato);
                medlem.getSvømmetider().add(nySvømmetid);

                System.out.println("Svømmetid tilføjet: " + formatDuration(nySvømmetid.getTid()) + " på dato: " + dato);

                // Opdater bedste tid
                opdaterBedsteTid(medlem);

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
        return disciplinNavne;
    }

    public Svømmetid getBedsteTid() {
        return bedsteTid;
    }

    public void setBedsteTid(Svømmetid bedsteTid) {
        this.bedsteTid = bedsteTid;
    }
}