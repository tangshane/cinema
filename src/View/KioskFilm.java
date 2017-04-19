package View;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import Controller.*;
import Model.Film;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerListModel;

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
public class KioskFilm extends javax.swing.JFrame {

	private CinemaSystem cs;
	private JButton ok;
	private JLabel jLabel1;
	private JSpinner filminfo;
	private JTable info;
	private JScrollPane jScrollPane1;
	private JButton back;

	public KioskFilm(CinemaSystem cs) {
		super();
		this.cs = cs;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setTitle("Kiosk - Choose a Film");
			{
				back = new JButton();
				back.setText("Back");
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//System.out.println("back.actionPerformed, event="+evt);
						//TODO add your code for back.actionPerformed
						cs.gotoWelcome();
					}
				});
			}
			{
				cs.readData();
				String[] content = new String[cs.filmInfoList.size()];
				for(int i = 0; i<cs.filmInfoList.size(); i++) {
					content[i] = cs.filmInfoList.get(i).getName();
				}
				SpinnerListModel filminfoModel = 
						new SpinnerListModel(content);
				filminfo = new JSpinner();
				filminfo.setModel(filminfoModel);
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setText("Please choose your film:");
			}
			{
				ok = new JButton();
				ok.setText("OK");
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					Vector<String> title = new Vector<String>();// 列名
					title.add("Film"); title.add("Runtime"); title.add("Poster");
					Vector<Vector<Object>> data = new Vector<Vector<Object>>();  
					cs.readData();
			        for (int i = 0; i < cs.filmInfoList.size(); i++) {  
			            Vector<Object> v = new Vector<Object>();  
			            Film f = cs.filmInfoList.get(i);  
			            ImageIcon icon = new ImageIcon("basic/poster/" + f.getPoster());//图片处理  
			            icon.setImage(icon.getImage().getScaledInstance(80,100,Image.SCALE_DEFAULT));  
			            Image img = icon.getImage();  
			            v.add(f.getName());  
			            v.add(f.getRuntime() + " min");
			            v.add(img);  
			            data.add(v);  
			        }  					
					TableModel infoModel = new DefaultTableModel(data, title);  
					info = new JTable(infoModel);
					info.getTableHeader().setReorderingAllowed(false);   //不可整列移动     
			        info.getTableHeader().setResizingAllowed(false);   
					jScrollPane1.setViewportView(info);
					info.setModel(infoModel);
					info.setRowHeight(100);
					info.enable(false);
					info.getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());
				}
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(49, 49)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
				.addGap(33)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(filminfo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(ok, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(35)
				.addComponent(back, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(29, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(72, 72)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 662, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 11, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addComponent(filminfo, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
				        .addGap(91)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(ok, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 51, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(51)
				                .addComponent(back, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(55, 55));
			pack();
			this.setSize(800, 600);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
