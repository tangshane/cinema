package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

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
public class KioskWelcome extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JButton start;
	private CinemaSystem cs;
	
	public KioskWelcome(CinemaSystem cs) {
		super();
		initGUI();
		this.cs = cs;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Kiosk - Choose a Film");
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Welcome");
				jLabel1.setFont(new java.awt.Font("Dialog",0,72));
			}
			{
				start = new JButton();
				start.setText("Start");
				start.setFont(new java.awt.Font("Dialog",0,72));
				start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("start.actionPerformed, event="+evt);
						//TODO add your code for start.actionPerformed
						cs.gotoFilm();
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(156, 156)
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
				.addGap(45)
				.addComponent(start, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(218, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(243, 243)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(start, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(229, Short.MAX_VALUE));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
