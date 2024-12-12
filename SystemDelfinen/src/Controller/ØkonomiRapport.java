package Controller;

import Model.Medlem;

import java.util.List;

public class ØkonomiRapport extends Rapport {
    private List<Medlem> medlemmer;

    // Constructor
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

    // Bruger Controller.Rapport-klassen, til at genere en Økonomi Controller.Rapport
    @Override
    public void generer() {
        System.out.println("Økonomi Controller.Rapport:");
        System.out.println("================");
        for (Medlem medlem : medlemmer) {
            System.out.println("Navn: " + medlem.getNavn() + ", Kontigent: " + medlem.getKontingent());
        }
        System.out.println("================");
        System.out.println("Total indkomst: " + beregnTotalIndkomst());
    }
}
