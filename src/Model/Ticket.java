package Model;

import java.util.Date;

public class Ticket {
	private int number;
	private int type; //1-child, 2-adult, 3-senior, 4-student
	private boolean IDRequired;
	private String film;
	private String showtime;
	private int screen;
	private int row;
	private int col;
	private boolean available;
	
	public Ticket() {
		
	}
	
	public Ticket(int number, boolean available) {
		this.number = number;
		this.available = available;
	}
	
	public Ticket(int number, int type, boolean IDRequired, String film, String showtime, 
			int screen, int row, int col, boolean available) {
		this.number = number;
		this.type = type;
		this.IDRequired = IDRequired;
		this.film = film;
		this.showtime = showtime;
		this.screen = screen;
		this.row = row;
		this.col = col;
		this.available = available;
	}
	
	public int getNumber() {
		return this.number;
	}

	public boolean getAvailable() {
		return this.available;
	}
	
	public String toString() {
		if(type == 0) {
			return this.number + "]]]]" + this.available;
		} else {
			return this.number + "]]]]" + this.type + "]]]]" + this.IDRequired + "]]]]" +
					this.film + "]]]]" + this.showtime + "]]]]" + this.screen + "]]]]" +
					this.row + "]]]]" + this.col + "]]]]" + this.available;
		}
	}
}
