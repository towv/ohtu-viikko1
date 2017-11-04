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
    public void testNegatiivinenLisays() {
      varasto.lisaaVarastoon(-2);
      assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testKonstruktoriVirhe() {
        Varasto varasto2 = new Varasto(-3);
        assertEquals(0.0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void testToinenKonstruktori() {
        Varasto varasto2 = new Varasto(10, 4);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(4, varasto2.getSaldo(), vertailuTarkkuus);
        
        Varasto varasto3 = new Varasto(-10, -4);
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
        
        Varasto varasto4 = new Varasto(5, 6);
        assertEquals(5, varasto4.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varasto4.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testOttaminenToimiiOikein() {
        Varasto varasto2 = new Varasto(10, 8);
        varasto2.otaVarastosta(-2);
        assertEquals(8, varasto2.getSaldo(), vertailuTarkkuus);
        
        double otettu = varasto2.otaVarastosta(10);
        assertEquals(8, otettu, vertailuTarkkuus);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);     
    }
    
    @Test
    public void testLisaaminenToimiiTasanOikein() {
        Varasto varasto2 = new Varasto(10, 8);
        varasto2.lisaaVarastoon(2);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
        varasto2.lisaaVarastoon(2);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);   
    }
    
    @Test
    public void testToStringToimii() {
        varasto.lisaaVarastoon(4);
        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
        
}

}