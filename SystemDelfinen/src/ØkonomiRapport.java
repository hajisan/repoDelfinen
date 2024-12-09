import java.util.List;

public class ØkonomiRapport extends Rapport {
    private List<Medlem> medlemmer;

    // Konstruktør
    public ØkonomiRapport(List<Medlem> medlemmer) {
        this.medlemmer = medlemmer;
    }

    // Metode til at beregne den totale indkomst
    public double beregnTotalIndkomst() {
        double totalIndkomst = 0;
        for (Medlem medlem : medlemmer) {
            totalIndkomst += medlem.getKontingent();
        }
        return totalIndkomst;
    }

    // Bruger Rapport-klassen, til at genere en Økonomi Rapport
    @Override
    public void generer() {
        System.out.println("Økonomi Rapport:");
        System.out.println("================");
        for (Medlem medlem : medlemmer) {
            System.out.println("Navn: " + medlem.getNavn() + ", Kontigent: " + medlem.getKontingent());
        }
        System.out.println("================");
        System.out.println("Total indkomst: " + beregnTotalIndkomst());
    }

}
