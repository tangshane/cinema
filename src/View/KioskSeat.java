package View;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

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
	private JLabel jLabel1;
	private JButton back;
	private JButton ok;
	public JPanel seats;

	public KioskSeat(CinemaSystem cs) {
		super();
		initGUI();
		this.cs = cs;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Kiosk - Choose a Seat");
			{
				seats = new JPanel();
			}
			{
				ok = new JButton();
				ok.setText("OK");
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("ok.actionPerformed, event="+evt);
						//TODO add your code for ok.actionPerformed
						cs.gotoTicket();
					}
				});
			}
			{
				back = new JButton();
				back.setText("Back");
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("back.actionPerformed, event="+evt);
						//TODO add your code for back.actionPerformed
						cs.gotoTime(cs.getCurrentFilm().getName());
					}
				});
			}
			{
				jLabel1 = new JLabel("",JLabel.CENTER);
				jLabel1.setText("Screen");
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(73, 73)
				.addComponent(seats, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
				.addGap(16)
				.addComponent(jLabel1, 0, 25, Short.MAX_VALUE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(ok, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(9)
				.addComponent(back, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(96, 96)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(seats, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 613, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel1, GroupLayout.Alignment.LEADING, 0, 613, Short.MAX_VALUE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(272)
				        .addComponent(ok, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addGap(279)))
				.addComponent(back, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(29, 29));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
