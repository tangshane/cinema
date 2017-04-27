package View;

import javax.swing.table.*;
import Controller.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Kiosk finish order frame
 * @author Zhekong Yang
 * @version v1.0
 */
public class KioskFinish extends javax.swing.JFrame {

	private CinemaSystem cs;
	private JScrollPane jScrollPane1;
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
				jScrollPane1 = new JScrollPane();
				{
					ticketModel = new DefaultTableModel();
					ticket = new JTable();
					jScrollPane1.setViewportView(ticket);
					ticket.setModel(ticketModel);
					ticket.setPreferredSize(new java.awt.Dimension(630, 223));
				}
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
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
				.addGap(25)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(finish, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(same, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(113, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(83, 83)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(49)
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
				        .addGap(42)))
				.addContainerGap(84, 84));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
