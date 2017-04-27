package View;

import java.awt.event.*;
import Controller.*;
import javax.swing.*;

/**
 * Kiosk welcome frame, click to enter
 * @author Yunyao Liu
 * @version v1.0
 */
public class KioskWelcome extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JButton start;
	private CinemaSystem cs;
	
	public KioskWelcome(CinemaSystem cs) {
		super();
		initGUI();
		this.cs = cs;
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Kiosk - Welcome");
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Welcome");
				jLabel1.setFont(new java.awt.Font("Dialog",0,72));
			}
			{
				start = new JButton();
				start.setText("Start");
				start.setFont(new java.awt.Font("Dialog",0,72));
				start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("start.actionPerformed, event="+evt);
						//TODO add your code for start.actionPerformed
						cs.gotoFilm();
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(156, 156)
				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
				.addGap(45)
				.addComponent(start, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(218, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(243, 243)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(start, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(229, Short.MAX_VALUE));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
