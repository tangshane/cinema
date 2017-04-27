package JUnit;

import static org.junit.Assert.*;

import org.junit.*;

import Controller.*;

/**
 * JUnit Test for Admin
 * @author Yunyao Liu
 * @version v1.0
 */
public class AdminTest {
	CinemaSystem cs = new CinemaSystem();
	
	@Before
	public void setUp() throws Exception {
		cs.readData(true);
	}
	
	@Test
	public void testAdminLogin() {
		try {
			assertNotEquals(-1, cs.searchAdmin("1","1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
