package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class AdminReport extends javax.swing.JFrame {
	private JLabel jLabel1;
	private CinemaSystem cs;
	private JButton logout;
	private JButton more;
	private JButton timetable;
	private JButton report;

	public AdminReport(CinemaSystem cs) {
		super();
		this.cs = cs;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Admin - Login");
			{
				jLabel1 = new JLabel("", SwingConstants.CENTER);
				jLabel1.setText("Welcome Admin, please manage system!");
			}
			{
				report = new JButton();
				report.setText("Statistic Report");
				report.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("report.actionPerformed, event="+evt);
						//TODO add your code for report.actionPerformed
						cs.gotoReport();
					}
				});
			}
			{
				timetable = new JButton();
				timetable.setText("Timetable");
				timetable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("timetable.actionPerformed, event="+evt);
						//TODO add your code for timetable.actionPerformed
						cs.gotoTimetable();
					}
				});
			}
			{
				more = new JButton();
				more.setText("More");
				more.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("more.actionPerformed, event="+evt);
						//TODO add your code for more.actionPerformed
						JOptionPane.showMessageDialog(null, "Still Working, more to come!", "Alert", JOptionPane.INFORMATION_MESSAGE); 			
					}
				});
			}
			{
				logout = new JButton();
				logout.setText("Log out");
				logout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("logout.actionPerformed, event="+evt);
						//TODO add your code for logout.actionPerformed
						cs.gotoAdmin();
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(19, 19)
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(38)
				.addComponent(timetable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(32)
				.addComponent(report, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(32)
				.addComponent(more, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(29)
				.addComponent(logout, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(25, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(40, 40)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 21, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(26)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(report, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 21, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(timetable, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 21, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(more, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 21, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(247)
				                .addComponent(logout, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 0, Short.MAX_VALUE)))))
				.addContainerGap(25, 25));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
