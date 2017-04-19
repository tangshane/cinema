package View;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import Controller.CinemaSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
public class CheckTicket extends javax.swing.JFrame {
	private JLabel jLabel1;
	private CinemaSystem cs;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JLabel jLabel3;
	public JTextField type;
	public JTextField number;
	public JTextField seat;
	public JTextField screen;
	public JTextField showtime;
	public JTextField film;
	public JCheckBox checkID;
	private JLabel jLabel2;
	private JButton back;
	private JButton ok;

	public CheckTicket(CinemaSystem cs) {
		super();
		initGUI();
		this.cs = cs;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Cinema - Checking Ticket");
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Ticket Information");
			}
			{
				ok = new JButton();
				ok.setText("OK");
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("ok.actionPerformed, event="+evt);
						//TODO add your code for ok.actionPerformed
						boolean check = false;
						if(checkID.isEnabled() == false) {
							check = true;
						} else {
							check = checkID.isSelected();
						}
						try {
							cs.useTicket(number.getText(), check);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						cs.gotoGate();
					}
				});
			}
			{
				checkID = new JCheckBox();
				checkID.setText("ID Required");
			}
			{
				showtime = new JTextField();
				showtime.setText("jTextField1");
				showtime.enable(false);
			}
			{
				screen = new JTextField();
				screen.setText("jTextField1");
				screen.enable(false);
			}
			{
				seat = new JTextField();
				seat.setText("jTextField1");
				seat.enable(false);
			}
			{
				type = new JTextField();
				type.setText("jTextField1");
				type.enable(false);
			}
			{
				film = new JTextField();
				film.setText("jTextField1");
				film.enable(false);
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("Ticket No.:");
			}
			{
				jLabel3 = new JLabel();
				jLabel3.setText("Type:");
			}
			{
				jLabel4 = new JLabel();
				jLabel4.setText("Film:");
			}
			{
				jLabel5 = new JLabel();
				jLabel5.setText("Showtime:");
			}
			{
				jLabel6 = new JLabel();
				jLabel6.setText("Screen:");
			}
			{
				jLabel7 = new JLabel();
				jLabel7.setText("Seat:");
			}
			{
				number = new JTextField();
				number.setText("jTextField1");
				number.enable(false);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(25, 25)
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(number, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(type, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(film, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(showtime, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(screen, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(seat, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel7, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(checkID, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(ok, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(back, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(16, 16));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(89, 89)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jLabel7, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				            .addComponent(jLabel6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				            .addComponent(jLabel4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				            .addComponent(jLabel3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
				        .addGap(0, 0, Short.MAX_VALUE)
				        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
				        .addGap(61))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGap(0, 0, Short.MAX_VALUE)
				                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
				        .addGap(0, 38, GroupLayout.PREFERRED_SIZE)
				        .addComponent(back, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addGap(49))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(25)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(ok, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				                .addGap(11)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(thisLayout.createSequentialGroup()
				                        .addComponent(seat, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				                    .addGroup(thisLayout.createSequentialGroup()
				                        .addComponent(screen, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				                    .addGroup(thisLayout.createSequentialGroup()
				                        .addComponent(showtime, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				                    .addGroup(thisLayout.createSequentialGroup()
				                        .addComponent(film, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				                    .addGroup(thisLayout.createSequentialGroup()
				                        .addComponent(type, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
				                    .addGroup(thisLayout.createSequentialGroup()
				                        .addComponent(number, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
				                .addGap(0, 0, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addPreferredGap(ok, checkID, LayoutStyle.ComponentPlacement.INDENT)
				                .addComponent(checkID, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 37, Short.MAX_VALUE)))))
				.addContainerGap(93, 93));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
