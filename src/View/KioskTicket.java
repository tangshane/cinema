package View;

import javax.swing.*;
import javax.swing.table.*;
import Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class KioskTicket extends javax.swing.JFrame {

	private CinemaSystem cs;
	private JButton ok;
	private JComboBox number;
	private JLabel jLabel2;
	public JComboBox type;
	public ComboBoxModel typeModel;
	private JLabel jLabel1;
	private JButton back;
	private JScrollPane jScrollPane1;
	public TableModel ticketinfoModel;
	public JTable ticketinfo;

	public KioskTicket(CinemaSystem cs) {
		super();
		this.cs = cs;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Kiosk - Choose Your Ticket Type");
			{
				jScrollPane1 = new JScrollPane();
				{
					ticketinfoModel = new DefaultTableModel();
					ticketinfo = new JTable();
					jScrollPane1.setViewportView(ticketinfo);
					ticketinfo.setModel(ticketinfoModel);
				}
			}
			{
				back = new JButton();
				back.setText("Back");
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("back.actionPerformed, event="+evt);
						//TODO add your code for back.actionPerformed
						cs.gotoSeat();
					}
				});
			}
			{
				ok = new JButton();
				ok.setText("OK");
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("ok.actionPerformed, event="+evt);
						//TODO add your code for ok.actionPerformed
						cs.gotoPay();
					}
				});
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Ticket Type:");
			}
			{
				typeModel = new DefaultComboBoxModel();
				type = new JComboBox();
				type.setModel(typeModel);
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("Number:");
			}
			{
				ComboBoxModel numberModel = new DefaultComboBoxModel(new String[] { "1" });
				number = new JComboBox();
				number.setModel(numberModel);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(83, 83)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
				.addGap(52)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(type, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(35)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addGap(7))
				    .addComponent(number, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(47)
				.addComponent(ok, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(43)
				.addComponent(back, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(24, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(87, 87)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 612, GroupLayout.PREFERRED_SIZE)
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(171)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
				                .addComponent(ok, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
				                .addGap(47)))
				        .addGap(38)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(number, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(type, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                .addGap(6)))
				        .addGap(224)))
				.addComponent(back, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(39, Short.MAX_VALUE));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
