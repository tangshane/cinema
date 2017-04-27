package View;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Film Poster Cell to show poster 
 * @author Yunyao Liu
 * @version v1.0
 */
public class ImageRenderer implements TableCellRenderer
{
	@SuppressWarnings("unchecked")
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
								boolean hasFocus, int rowIndex, int columnIndex) {
	    	if( value instanceof Image ){
	    		JLabel jLabel = new JLabel();
	    		jLabel.setLayout(new BorderLayout());
	    		jLabel.setIcon(new ImageIcon((Image)value));
	    		return jLabel;
	    } else if( value instanceof File ) {
	    		try {
	    	  		return new JLabel(new ImageIcon(ImageIO.read((File)value)));
	      	} catch(IOException ex) {
	        throw new RuntimeException(ex.getMessage(), ex);
	      	}
	    } else {
	    		String val = String.valueOf(value);
	    		System.out.println(val);
	      	try {
	    	  		return new JLabel(new ImageIcon(ImageIO.read(new File(val))));
	      	} catch(IOException ex) {
	    	  		throw new RuntimeException(ex.getMessage(), ex);
	      	}
	    }
	}
}
