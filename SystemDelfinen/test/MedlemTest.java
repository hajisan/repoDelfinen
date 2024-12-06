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
        this.medlem1 = Medlem.opretMedlem("Joakim", KonsolHandler.stringToLocalDate("07/05/1999"), "aktiv");
        this.medlem2 = Medlem.opretMedlem("Nima", KonsolHandler.stringToLocalDate("20/01/1903"), "aktiv");
        this.medlem3 = Medlem.opretMedlem("Andreas", KonsolHandler.stringToLocalDate("22/11/1998"), "aktiv");
        this.medlem4 = Medlem.opretMedlem("Sofie", KonsolHandler.stringToLocalDate("04/05/2001"), "aktiv");
        medlem1.setID("DSK1");
        medlem2.setID("DSK2");
        medlem3.setID("DSK3");
        medlem4.setID("DSK4");
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
    void getID() {
        assertEquals("DSK1", medlem1.getID());
        assertEquals("DSK2", medlem2.getID());
        assertEquals("DSK3", medlem3.getID());
        assertEquals("DSK4", medlem4.getID());
    }

    @Test
    void setID() {
    }

    @Test
    void getFødselsDato() {
        assertEquals(KonsolHandler.stringToLocalDate("07/05/1999"), medlem1.getFødselsDato());
        assertEquals(KonsolHandler.stringToLocalDate("20/01/1903"), medlem2.getFødselsDato());
        assertEquals(KonsolHandler.stringToLocalDate("22/11/1998"), medlem3.getFødselsDato());
        assertEquals(KonsolHandler.stringToLocalDate("04/05/2001"), medlem4.getFødselsDato());
    }

    @Test
    void setFødselsDato() {
    }

    @Test
    void getMedlemsKategori() {

    }

    @Test
    void setMedlemsKategori() {
        assertEquals("", medlem1.getID());
        assertEquals("", medlem2.getID());
        assertEquals("", medlem3.getID());
        assertEquals("", medlem4.getID());
    }

    @Test
    void testToString() {
    }
}