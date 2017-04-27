package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.CinemaSystem;

/**
 * JUnit Test for TicketInfo
 * @author Yuqian Li
 * @version v1.0
 */
public class TicketInfoTest {

	CinemaSystem cs = new CinemaSystem();
	
	@Before
	public void setUp() throws Exception {
		cs.readData(true);
	}
	
	@Test
	public void testAddTicketInfo() {
		try {
			assertEquals(true, cs.addTicketInfo(0, "test", true, 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchTicketInfo() {
		try {
			assertNotEquals(1, cs.searchTicketInfo(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTicketInfo() {
		try {
			assertEquals(false, cs.deleteTicketInfo(5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateTicketInfo() {
		try {
			assertEquals(false, cs.updateTicketInfo(5, "", false, 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
