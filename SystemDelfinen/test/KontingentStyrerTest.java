import Controller.FilStyrer;
import Controller.KontingentStyrer;
import Model.Medlem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class KontingentStyrerTest {
    FilStyrer filStyrer;
    KontingentStyrer kontingentStyrer;

    @BeforeEach
    void setup() {
        filStyrer = new FilStyrer();
        kontingentStyrer = new KontingentStyrer();
    }

    @Test
    void testBeregnForventetIndkomst() {
        double forventet = 0.0;
        try {
            for (Medlem medlem : FilStyrer.læsAlleMedlemmer()) {
                forventet += medlem.getKontingent();
            }
            assertEquals(forventet, kontingentStyrer.beregnForventetIndkomst(FilStyrer.læsAlleMedlemmer()));
        } catch (IOException e) {
            System.out.println("Fil ikke fundet: " + filStyrer.getFilNavn());
        }
    }

    @Test
    void testEnkelteMedlemsberegninger() {
        try {
            ArrayList<Medlem> tilføjetListe = new ArrayList<>();
            tilføjetListe.addAll(FilStyrer.læsAlleMedlemmer());
            Medlem junior = new Medlem("Lille Per", "07/07/2020", "aktiv");
            Medlem senior = new Medlem("Far", "05/01/1967", "aktiv");
            Medlem olding = new Medlem("Onkel Anders", "10/10/1930", "aktiv");
            Medlem passiv = new Medlem("Ham-der-Alen", "19/02/1990", "passiv");

            testJunior(tilføjetListe, junior);
            testSenior(tilføjetListe, senior);
            testPensionist(tilføjetListe, olding);
            testPassiv(tilføjetListe, passiv);
        } catch (IOException e) {
            System.out.println("Fil ikke fundet: " + filStyrer.getFilNavn());;
        }
    }

    void testJunior(ArrayList<Medlem> medlemmer, Medlem medlem) throws IOException{
        medlemmer.add(medlem);
        assertEquals(kontingentStyrer.beregnForventetIndkomst(FilStyrer.læsAlleMedlemmer()) + 1000, kontingentStyrer.beregnForventetIndkomst(medlemmer));
        medlemmer.remove(medlem);
    }

    void testSenior(ArrayList<Medlem> medlemmer, Medlem medlem) throws IOException{
        medlemmer.add(medlem);
        assertEquals(kontingentStyrer.beregnForventetIndkomst(FilStyrer.læsAlleMedlemmer()) + 1600, kontingentStyrer.beregnForventetIndkomst(medlemmer));
        medlemmer.remove(medlem);
    }

    void testPensionist(ArrayList<Medlem> medlemmer, Medlem medlem) throws IOException{
        medlemmer.add(medlem);
        assertEquals(kontingentStyrer.beregnForventetIndkomst(FilStyrer.læsAlleMedlemmer()) + 1200, kontingentStyrer.beregnForventetIndkomst(medlemmer));
        medlemmer.remove(medlem);
    }

    void testPassiv(ArrayList<Medlem> medlemmer, Medlem medlem) throws IOException{
        medlemmer.add(medlem);
        assertEquals(kontingentStyrer.beregnForventetIndkomst(FilStyrer.læsAlleMedlemmer()) + 500, kontingentStyrer.beregnForventetIndkomst(medlemmer));
        medlemmer.remove(medlem);
    }

    @Test
    void testFindOverskredneBetalinger() {
        try {
            assertEquals(14, kontingentStyrer.findOverskredneBetalinger().size());
        } catch (IOException e) {
            System.out.println("Fil ikke fundet: " + filStyrer.getFilNavn());
        }
    }
}