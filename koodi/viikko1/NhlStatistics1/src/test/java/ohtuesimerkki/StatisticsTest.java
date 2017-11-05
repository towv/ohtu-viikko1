
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author twviiala
 */
public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void testSearch() {
        //Yritetään pelaajalla jota ei ole statseissä
        Player Selanne = stats.search("Selanne");
        assertEquals(null, Selanne);
        
        //Ja pelaajalla joka on
        Player Kurri = stats.search("Kurri");
        assertEquals("Kurri", Kurri.getName());
    }
    
    @Test
    public void testTeam() {
        //Testataan Selännettä
        List joukkue = stats.team("Selanne");
        assertEquals(0, joukkue.size());
        
        //Testataan joukkuetta
        List joukkue2 = stats.team("EDM");
        assertEquals(3, joukkue2.size());
    }
    
    @Test
    public void testTopScoreres() {
        List pistemiehet = stats.topScorers(2);
        assertEquals(3, pistemiehet.size());
    }
}
