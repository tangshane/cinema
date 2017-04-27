package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.CinemaSystem;

/**
 * JUnit Test for Film
 * @author Zhenhao Li
 * @version v1.0
 */
public class FilmTest {
	CinemaSystem cs = new CinemaSystem();
	
	@Before
	public void setUp() throws Exception {
		cs.readData(true);
	}
	
	@Test
	public void testAddFilm() {
		try {
			assertEquals(true, cs.addFilm("Test", 120, "Test.jpg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchFilm() {
		try {
			assertNotEquals(-1, cs.searchFilm("LOGAN"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetFilm() {
		try {
			assertNotEquals(null, cs.getFilm("LOGAN"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteFilm() {
		try {
			assertEquals(false, cs.deleteFilm("NOT A FILM"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
