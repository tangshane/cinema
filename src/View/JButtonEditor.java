package View;

import javax.swing.*;

import Controller.CinemaSystem;
import Model.Timetable;

import java.awt.*;
import java.awt.event.*;

public class JButtonEditor extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private int row;
    private CinemaSystem cs;

    public JButtonEditor(JCheckBox checkBox, CinemaSystem cs) {
        super(checkBox);
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
        		String showtime = ((String) table.getValueAt(row, 1)).substring(0,2) + ((String) table.getValueAt(row, 1)).substring(3);
            JOptionPane.showMessageDialog(button, label + ": Screen " +screen+ " " + showtime + " selected!");
            cs.setCurrentTimetable(new Timetable(screen, cs.getCurrentFilm().getName(), showtime));
            cs.gotoSeat();
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
