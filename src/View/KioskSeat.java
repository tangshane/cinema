package View;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import Controller.*;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class KioskSeat extends javax.swing.JFrame {

	private CinemaSystem cs;
	
	public KioskSeat(CinemaSystem cs) {
		super();
		initGUI();
		this.cs = cs;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("Kiosk - Choose a Film");
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
