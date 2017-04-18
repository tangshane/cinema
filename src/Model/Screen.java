package Model;

public class Screen {
	private int number;
	private int row;
	private int col;
	private String[][] seat;
	
	public Screen() {
		
	}
	
	public Screen(int number, int row, int col, String[][] seat) {
		this.number = number;
		this.row = row;
		this.col = col;
		this.seat = seat;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return this.number;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	public int getRow() {
		return this.row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	public int getCol() {
		return this.col;
	}
	
	public void setSeat(String[][] seat) {
		this.seat = seat;
	}
	public String[][] getSeat() {
		return this.seat;
	}
	
	public String toString() {
		String seats = "";
		for(int i = 0; i<seat.length; i++) {
			for(int j = 0; j<seat[0].length; j++) {
				seats = seats + seat[i][j] + "]]]]";
			}
			seats = seats.substring(0, seats.length()-4) + "\n";
		}
		seats = seats.substring(0, seats.length()-1);
		return number + "]]]]" + row + "]]]]" + col + "\n" + seats;
	}
}
