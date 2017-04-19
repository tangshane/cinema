package View;
import java.awt.event.*;

import javax.swing.*;

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
public class CheckGate extends javax.swing.JFrame {
	private JButton verify;
	private JTextField ticketNumber;
	private JButton clear;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private CinemaSystem cs;
	
	public CheckGate(CinemaSystem cs) {
		super();
		initGUI();
		this.cs = cs;
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("Cinema - Checking Gate");
			{
				verify = new JButton();
				verify.setText("Verify");
				verify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("verify.actionPerformed, event="+evt);
						//TODO add your code for verify.actionPerformed
						cs.GateToTicket(ticketNumber.getText());
					}
				});
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("<html>Checking Gate<br>Please show your ticket to our cinema staff</html>");
			}
			{
				ticketNumber = new JTextField();
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("Ticket No.:");
			}
			{
				clear = new JButton();
				clear.setText("Clear");
				clear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("clear.actionPerformed, event="+evt);
						//TODO add your code for clear.actionPerformed
						ticketNumber.setText("");
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(19, 19)
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
				.addGap(59)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(ticketNumber, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(54)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(verify, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(clear, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(63, 63));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(46, 46)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jLabel1, GroupLayout.Alignment.LEADING, 0, 309, Short.MAX_VALUE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addComponent(ticketNumber, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(54)
				        .addComponent(verify, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
				        .addGap(57)
				        .addComponent(clear, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 76, Short.MAX_VALUE)))
				.addContainerGap(45, 45));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
