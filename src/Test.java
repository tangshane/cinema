import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;


public class Test {
	
	public static void main(String args[]) {
		int number = 0;
		Random random = new Random();
		for(int i = 0; i<8; i++) {
			number = number * 10 + (random.nextInt(4)+1);
		}
		char c = (char) 65;
		String s = "A";
		System.out.println(s.charAt(0) - 65);
		System.out.println(c);
	}

}
