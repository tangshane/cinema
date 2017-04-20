package View;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;
import Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
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
public class KioskFinish extends javax.swing.JFrame {

	private CinemaSystem cs;
	private JButton finish;
	private JButton same;
	private JLabel jLabel1;
	public JTable ticket;
	public TableModel ticketModel;

	public KioskFinish(CinemaSystem cs) {
		super();
		initGUI();
		this.cs = cs;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Kiosk - Get your ticket");
			{
				ticketModel = new DefaultTableModel();
				ticket = new JTable();
				ticket.setModel(ticketModel);
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Your order has been placed. Here is your ticket info:");
			}
			{
				same = new JButton();
				same.setText("<html>Order another ticket <br>at same time</html>");
				same.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("same.actionPerformed, event="+evt);
						//TODO add your code for same.actionPerformed
						cs.gotoSeat();
					}
				});
			}
			{
				finish = new JButton();
				finish.setText("Finish");
				finish.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("finish.actionPerformed, event="+evt);
						//TODO add your code for finish.actionPerformed
						cs.gotoWelcome();
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(31, 31)
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(23)
				.addComponent(ticket, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
				.addGap(80)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(same, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(finish, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(111, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(81, 81)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(ticket, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(51)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(same, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
				                .addGap(209)
				                .addComponent(finish, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 0, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(96)
				                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 12, Short.MAX_VALUE)))
				        .addGap(40)))
				.addContainerGap(86, 86));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
