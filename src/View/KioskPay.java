package View;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;
import Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
public class KioskPay extends javax.swing.JFrame {

	private CinemaSystem cs;
	private JButton ok;
	private JTextField cvv;
	private JLabel jLabel5;
	private JComboBox year;
	private JComboBox month;
	private JLabel jLabel4;
	private JTextField card;
	private JLabel jLabel3;
	private JScrollPane jScrollPane1;
	private JButton back;
	private JTextField name;
	private JLabel jLabel2;
	public JTable ticket;
	public TableModel ticketModel;
	private JLabel jLabel1;

	public KioskPay(CinemaSystem cs) {
		super();
		initGUI();
		this.cs = cs;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Kiosk - Check info and Pay");
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Please check your information, then pay by debit/credit card");
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("Name on Card");
			}
			{
				name = new JTextField();
			}
			{
				jLabel3 = new JLabel();
				jLabel3.setText("Card Number");
			}
			{
				card = new JTextField();
			}
			{
				jLabel4 = new JLabel();
				jLabel4.setText("Expires");
			}
			{
				ComboBoxModel monthModel = 
						new DefaultComboBoxModel(
								new String[] { "Month", "01-January", "02-February", "03-March", 
										"04-April", "05-May", "06-June", "07-July", "08-August",
										"09-September", "10-October", "11-November", "12-December" });
				month = new JComboBox();
				month.setModel(monthModel);
			}
			{
				ComboBoxModel yearModel = 
						new DefaultComboBoxModel(
								new String[] { "Year", "2017", "2018", "2019", "2020", "2021",
										"2022", "2023", "2024", "2025", "2026"});
				year = new JComboBox();
				year.setModel(yearModel);
			}
			{
				jLabel5 = new JLabel();
				jLabel5.setText("Security Code");
			}
			{
				cvv = new JTextField();
			}
			{
				ok = new JButton();
				ok.setText("<html>PLACE <br>MY <br>ORDER</html>");
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("ok.actionPerformed, event="+evt);
						//TODO add your code for ok.actionPerformed
						cs.gotoFinish();
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
						cs.gotoTicket();
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					ticketModel = new DefaultTableModel();
					ticket = new JTable();
					jScrollPane1.setViewportView(ticket);
					ticket.setModel(ticketModel);
					ticket.setPreferredSize(new java.awt.Dimension(661, 258));
				}
			}
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(68, 68)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jScrollPane1, 0, 664, Short.MAX_VALUE)
				        .addGap(28))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(24)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
				                    .addComponent(jLabel3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
				                    .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
				                .addGap(41)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(cvv, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
				                        .addGap(12))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(month, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                        .addGap(44)
				                        .addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(card, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
				                        .addGap(12))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
				                        .addGap(12)))
				                .addGap(87)
				                .addComponent(ok, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
				                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
				                .addGap(68)))
				        .addGap(0, 11, Short.MAX_VALUE)
				        .addComponent(back, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(40, 40));
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(52, 52)
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
				.addComponent(jScrollPane1, 0, 262, Short.MAX_VALUE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 36, GroupLayout.PREFERRED_SIZE)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(name, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
				.addGap(17)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(card, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addGap(20)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				                .addGap(8))
				            .addComponent(month, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(year, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addGap(18))
				    .addComponent(ok, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(cvv, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(back, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(25, 25));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
