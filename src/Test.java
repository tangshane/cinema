import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
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
		Date d = new Date();
		System.out.println(compareTime(1,0,1,1));
		System.out.println(compareTime(2,0,1,1));
		System.out.println(compareTime(1,0,1,0));
		
		
	}
	
	public static int compareTime(int hourA, int minA, String timetable) {
		int hourB = Integer.parseInt(timetable.substring(0,2));
		int minB = Integer.parseInt(timetable.substring(2));
		if(hourA < hourB) {
			return -1;			
		} else if(hourA == hourB){
			if(minA < minB)
				return -1;
			else 
				return 1;
		} else {
			return 1;
		}
	}	

}
