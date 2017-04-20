package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.*;
import Model.*;
import javax.swing.*;

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
public class KioskTime extends javax.swing.JFrame {

	private CinemaSystem cs;
	private JLabel jLabel1;
	public JTable timetable;
	private JButton back;
	private JScrollPane jScrollPane1;
	public Film currentfilm;
	public TableModel timetableModel;
	
	public KioskTime(CinemaSystem cs) {
		super();
		this.cs = cs;
		this.currentfilm = new Film();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Kiosk - Choose a timetable");
			{
				jScrollPane1 = new JScrollPane();
				{
					timetableModel = new DefaultTableModel(new Object[][]{{"button 1", "foo", "foo"},
		                    {"button 2", "bar", "bar"}}, new Object[]{"Button", "String", "String"});  
					timetable = new JTable();
					timetable.getTableHeader().setReorderingAllowed(false);   //不可整列移动     
			        timetable.getTableHeader().setResizingAllowed(false);   
					jScrollPane1.setViewportView(timetable);
					timetable.setModel(timetableModel);
					timetable.setRowHeight(50);
				}
			}
			{
				back = new JButton();
				back.setText("Back");
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("back.actionPerformed, event="+evt);
						//TODO add your code for back.actionPerformed
						cs.gotoFilm();
					}
				});
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Please choose your timetable and click the right side button:");
				jLabel1.setFont(new java.awt.Font("Dialog",0,20));
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(34, 34)
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
				.addGap(28)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
				.addGap(31)
				.addComponent(back, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(29, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(92, 92)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 35, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 35, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(602)
				        .addComponent(back, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap(44, 44));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
