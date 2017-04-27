package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.CinemaSystem;

/**
 * JUnit Test for Ticket
 * @author Zhekong Yang
 * @version v1.0
 */
public class TicketTest {
	CinemaSystem cs = new CinemaSystem();
	
	@Before
	public void setUp() throws Exception {
		cs.readData(true);
	}
	
	@Test
	public void testSearchTicket() {
		try {
			assertEquals(null, cs.searchTicket(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
