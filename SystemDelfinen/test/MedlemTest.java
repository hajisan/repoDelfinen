import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedlemTest {
    private Medlem medlem1;
    private Medlem medlem2;
    private Medlem medlem3;
    private Medlem medlem4;

    @BeforeEach
    void setUp() {
        this.medlem1 = new Medlem("Joakim", KonsolHandler.stringToLocalDate("07/05/1999"), "aktiv");
        this.medlem2 = new Medlem("Nima", KonsolHandler.stringToLocalDate("20/01/1903"), "aktiv");
        this.medlem3 = new Medlem("Andreas", KonsolHandler.stringToLocalDate("22/11/1998"), "aktiv");
        this.medlem4 = new Medlem("Sofie", KonsolHandler.stringToLocalDate("04/05/2001"), "aktiv");
    }

    @AfterEach
    void tearDown() {
        //Ingenting endnu
    }

    @Test
    void beregnKontingent() {
        //assertEquals();
    }

    @Test
    void tilføjTræningstid() {

    }

    @Test
    void tilføjStævnetid() {
    }

    @Test
    void opretMedlem() {
    }

    @Test
    void getNavn() {
        assertEquals("Joakim", medlem1.getNavn());
        assertEquals("Nima", medlem2.getNavn());
        assertEquals("Andreas", medlem3.getNavn());
        assertEquals("Sofie", medlem4.getNavn());
    }

    @Test
    void setNavn() {
    }

    @Test
    void setID() {
    }

    @Test
    void getFødselsDato() {
        assertEquals(KonsolHandler.stringToLocalDate("07/05/1999"), medlem1.getFødselsdato());
        assertEquals(KonsolHandler.stringToLocalDate("20/01/1903"), medlem2.getFødselsdato());
        assertEquals(KonsolHandler.stringToLocalDate("22/11/1998"), medlem3.getFødselsdato());
        assertEquals(KonsolHandler.stringToLocalDate("04/05/2001"), medlem4.getFødselsdato());
    }

    @Test
    void setFødselsDato() {
    }

    @Test
    void getMedlemsKategori() {

    }

    @Test
    void testToString() {
    }
}