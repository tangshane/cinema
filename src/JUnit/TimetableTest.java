package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.CinemaSystem;

/**
 * JUnit Test for Timetable
 * @author Yuqin Cui
 * @version v1.0
 */
public class TimetableTest {

	CinemaSystem cs = new CinemaSystem();
	
	@Before
	public void setUp() throws Exception {
		cs.readData(true);
	}
	
	@Test
	public void testSearchTimetable() {
		try {
			assertEquals(-1, cs.searchTimetable(1, "Test", "1000"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTimetable() {
		try {
			assertNotEquals(null, cs.getTimetable(true));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddTimetable() {
		try {
			assertEquals(true, cs.addTimetable(3, "Test", "0000"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteTimetable() {
		try {
			assertEquals(false, cs.deleteTimetable(1, "Test", "0002"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateTimetable() {
		try {
			assertEquals(false, cs.updateTimetable(1, "Test", "0003"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
