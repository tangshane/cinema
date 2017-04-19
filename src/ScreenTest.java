import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Model.*;

public class ScreenTest {
	
	public Screen seats;
	
	// check
	// choose
	// read
	// write
	public static void main(String args[]) {
		ScreenTest t = new ScreenTest();
//		Date date = new Date();
//		String path = String.format("%4d%02d%02d", 1900+date.getYear(), date.getMonth()+1, date.getDate());
//		System.out.println(date);
//		System.out.println(path);
		try {
			t.readScreen("Logan", "1530");
			System.out.println(t.seats);
			if(!t.checkSeatFull()) {
				t.chooseSeat(1,4);		
				t.updateSeat("Logan", "1530");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean readScreen(String name, String showtime) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(getCurrentDatePath()+"/"+name+"/"+showtime+".txt"));
		int line = 0;
		int row = 0;
		int col = 0;
		int number = 0;
		String[][] seat = null;
		while (reader.hasNext()) {
			String[] s = reader.nextLine().split("]]]]");
			if(line == 0) {
				number = Integer.parseInt(s[0]);
				row = Integer.parseInt(s[1]);
				col = Integer.parseInt(s[2]);
				seat = new String[row][col];
			}	
			else {
				for(int i = 0; i<s.length; i++) {
					seat[line-1][i] = s[i];
				}
			}
			line++;
			if(line == row + 1) {
				seats = new Screen(number, row, col, seat);		
				break;
			}
		}
		if(seats == null)
			return false;
		else
			return true;
	}
	
	public boolean chooseSeat(int row, int col) {
		String[][] seat = seats.getSeat();
		String status = seat[row-1][col-1];
		if(status.equals("0")) {
			seat[row][col] = "1";
			seats.setSeat(seat);
			System.out.println(seats);
			System.out.println("selected");
			return true;			
		} else if(status.equals("1")) {
			System.out.println("have been selected");
			return false;
		} else {
			System.out.println("Cannot be selected"); 
			return false;	
		}
	}
	
	public boolean checkSeatFull() {
		String[][] seat = seats.getSeat();
		for(int i = 0; i<seat.length; i++) {
			for(int j = 0; j<seat[0].length; j++) {
				if(seat[i][j].equals("0")) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean updateSeat(String name, String showtime) throws IOException {
		BufferedWriter output = new BufferedWriter(new FileWriter(getCurrentDatePath()+"/"+name+"/"+showtime+".txt"));
		output.write(seats.toString() + "\r\n");
		output.close();
		System.out.println("update");
		return true;
	}
	
	public String getCurrentDatePath() {
		Date date = new Date();
		String path = String.format("%4d%02d%02d", 1900+date.getYear(), date.getMonth()+1, date.getDate());
		return path;
	}
}
