package View;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import Controller.*;

/**
 * Manage timetable frame for administrator
 * @author Zhekong Yang
 * @version v1.0
 */
public class AdminTimetable extends javax.swing.JFrame {
	private JLabel jLabel1;
	private CinemaSystem cs;
	private JButton back;
	private JButton add;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	public ComboBoxModel screenModel;
	public JComboBox screen;
	public ComboBoxModel filmModel;
	public JComboBox film;
	public ComboBoxModel hourModel;
	public JComboBox hour;
	public ComboBoxModel minModel;
	public JComboBox min;
	public TableModel timetableModel;
	public JTable timetable;
	
	public AdminTimetable(CinemaSystem cs) {
		super();
		this.cs = cs;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Admin - Timetable");
			{
				jLabel1 = new JLabel("", SwingConstants.CENTER);
				jLabel1.setText("Current Timetable");
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					timetableModel = new DefaultTableModel();
					timetable = new JTable();
					jScrollPane1.setViewportView(timetable);
					timetable.setModel(timetableModel);
					timetable.setPreferredSize(new java.awt.Dimension(363, 417));
				}
			}
			{
				minModel = new DefaultComboBoxModel();
				min = new JComboBox();
				min.setModel(minModel);
			}
			{
				jLabel2 = new JLabel();
				jLabel2.setText("Screen:");
			}
			{
				hourModel = new DefaultComboBoxModel();
				hour = new JComboBox();
				hour.setModel(hourModel);
			}
			{
				screenModel = new DefaultComboBoxModel();
				screen = new JComboBox();
				screen.setModel(screenModel);
			}
			{
				jLabel3 = new JLabel();
				jLabel3.setText("Film:");
			}
			{
				jLabel4 = new JLabel();
				jLabel4.setText("Showtime:");
			}
			{
				filmModel = new DefaultComboBoxModel();
				film = new JComboBox();
				film.setModel(filmModel);
			}
			{
				add = new JButton();
				add.setText("Add");
				add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("add.actionPerformed, event="+evt);
						//TODO add your code for add.actionPerformed
						cs.gotoManage(2);
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
						cs.gotoManage(3);
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(screen, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(min, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(hour, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(film, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(back, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(add, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				                .addGap(12)))
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(film, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
				                .addGap(22))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(screen, 0, 72, Short.MAX_VALUE)
				                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
				                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
				                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				                .addComponent(hour, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(24)
				                .addComponent(add, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				                .addGap(51)
				                .addComponent(back, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
				                .addGap(34)))
				        .addGap(20)
				        .addComponent(min, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, 0, 381, Short.MAX_VALUE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(29)
				        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 38, Short.MAX_VALUE)))
				.addGap(7));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
