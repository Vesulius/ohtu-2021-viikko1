package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktorillaVoiLuodaAlkusaldon() {
        Varasto alkuVarasto = new Varasto(10, 5);
        assertEquals(5, alkuVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaSaaMerkkijonoesityksen() {
        assertTrue(varasto.toString() instanceof String);
    }
    
    @Test
    public void voiOttaaKaikkiVarastosta() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivista() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void voidaanLuodaKelvotonVarasto() {
        Varasto testivarasto = new Varasto(0);
        assertTrue(testivarasto instanceof Varasto);
    }
    
    @Test
    public void voidaanLuodaKelvotonVarastoAlkusaldolla() {
        Varasto testivarasto = new Varasto(0, 10);
        assertTrue(testivarasto instanceof Varasto);
    }
    
    @Test
    public void negatiivinenAlkusaldoOnNolla() {
        Varasto testivarasto = new Varasto(10, -5);
        assertEquals(0, testivarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataYliSaldon() {
        varasto.lisaaVarastoon(100);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
}