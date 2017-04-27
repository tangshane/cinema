package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.CinemaSystem;

/**
 * JUnit Test for Screen
 * @author Yuqian Li
 * @version v1.0
 */
public class ScreenTest {

	CinemaSystem cs = new CinemaSystem();
	
	@Before
	public void setUp() throws Exception {
		cs.readData(true);
	}
	
	@Test
	public void testSearchScreen() {
		try {
			assertEquals(true, cs.searchScreen("LOGAN", 1, "1800"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAvailableScreen() {
		try {
			assertNotEquals(0, cs.availableScreen("LOGAN", 1, "1800"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
