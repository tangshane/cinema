package View;

import javax.swing.*;
import Controller.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Timetable confirm button and delete button
 * @author Zhenhao Li
 * @version v1.0
 */
public class JButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private int row;
    private CinemaSystem cs;
    private boolean confirm;

    public JButtonEditor(JCheckBox checkBox, CinemaSystem cs, boolean confirm) {
        super(checkBox);
        this.confirm = confirm;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
        this.cs = cs;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
    		this.table = table;
    		this.row = row;
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
        		int screen = (int)table.getValueAt(row, 0);
            if(confirm) {
            		String showtime = cs.extractTime(table.getValueAt(row, 1).toString());
    				JOptionPane.showMessageDialog(button, label + ": Screen " +screen+ " on " + cs.convertTime(showtime) + "!");
                cs.setCurrentTimetable(new Timetable(screen, cs.getCurrentFilm().getName(), showtime));
                cs.gotoTicket();
            } else {
        			String showtime = cs.extractTime(table.getValueAt(row, 2).toString());
        			String filmName = table.getValueAt(row, 1).toString();
           		JOptionPane.showMessageDialog(button, label + ": Screen " +screen+ " Film:" + filmName + " on " + cs.convertTime(showtime) + "!");
           		try {
           			cs.deleteTimetable(screen, filmName, showtime);
				} catch (Exception e) {
					e.printStackTrace();
				}
           		cs.gotoManage(3);
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
