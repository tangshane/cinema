import javax.swing.SwingUtilities;

import View.CheckGate;
import View.CheckTicket;


public class CinemaMain {

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CheckGate inst = new CheckGate();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
}
