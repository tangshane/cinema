package JUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.CinemaSystem;

/**
 * JUnit Test for Report
 * @author Yuqian Li
 * @version v1.0
 */
public class ReportTest {
	CinemaSystem cs = new CinemaSystem();
	
	@Before
	public void setUp() throws Exception {
		cs.readData(true);
	}
	
	// No test available

}
