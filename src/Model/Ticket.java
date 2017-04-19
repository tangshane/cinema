package Model;

import java.util.Random;

public class Ticket {
	private static final int TICKET_DIGIT = 8;
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

	public int getType() {
		return this.type;
	}
	
	public boolean getIDRequired() {
		return this.IDRequired;
	}
	
	public String getFilm() {
		return this.film;
	}
	
	public String getShowtime() {
		return this.showtime;
	}
	
	public int getScreen() {
		return this.screen;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public boolean getAvailable() {
		return this.available;
	}
	
	public int generateTicketNumber() {
		int number = 0;
		Random random = new Random();
		for(int i = 0; i<TICKET_DIGIT; i++) {
			number = number * 10 + (random.nextInt(4)+1);
		}
		return number;
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
