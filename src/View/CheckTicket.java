package View;
import javax.swing.*;
import Controller.*;
import java.awt.event.*;
import java.io.*;

/**
 * Check Gate II, show ticket information and check ID
 * @author Yuqin Cui
 * @version v1.0
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
			this.setTitle("Cinema - Select your Ticket Type");
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
						System.out.println(checkID.isEnabled());
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
				.addContainerGap(26, 26)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jLabel7, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel3, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(ok, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addGap(23)
				        .addComponent(back, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 100, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addPreferredGap(ok, checkID, LayoutStyle.ComponentPlacement.INDENT)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(checkID, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 91, Short.MAX_VALUE))
				            .addGroup(thisLayout.createSequentialGroup()
				                .addPreferredGap(checkID, jLabel1, LayoutStyle.ComponentPlacement.INDENT)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
				                        .addGap(0, 109, Short.MAX_VALUE))
				                    .addGroup(thisLayout.createSequentialGroup()
				                        .addPreferredGap(jLabel1, seat, LayoutStyle.ComponentPlacement.INDENT)
				                        .addGroup(thisLayout.createParallelGroup()
				                            .addGroup(thisLayout.createSequentialGroup()
				                                .addComponent(seat, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
				                                .addGap(0, 0, Short.MAX_VALUE))
				                            .addGroup(thisLayout.createSequentialGroup()
				                                .addComponent(screen, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
				                                .addGap(0, 0, Short.MAX_VALUE))
				                            .addGroup(thisLayout.createSequentialGroup()
				                                .addComponent(showtime, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
				                                .addGap(0, 0, Short.MAX_VALUE))
				                            .addGroup(thisLayout.createSequentialGroup()
				                                .addComponent(film, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
				                                .addGap(0, 0, Short.MAX_VALUE))
				                            .addGroup(thisLayout.createSequentialGroup()
				                                .addComponent(type, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
				                                .addGap(0, 0, Short.MAX_VALUE))
				                            .addComponent(number, GroupLayout.Alignment.LEADING, 0, 211, Short.MAX_VALUE))))))))
				.addContainerGap(41, 41));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
