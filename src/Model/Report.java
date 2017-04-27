package Model;

/**
 * Report Model
 * @author Yuqian Li
 * @version v1.0
 */
public class Report {
	private String[] film;
	private String[] type;
	private int[] salePerFilm;
	private int[] salePerType;
	private int totalTicketNumber;
	private double totalTicketAmount;
	
	public Report(int film, int type) {
		this.film = new String[film];
		this.type = new String[type];
		salePerFilm = new int[film];
		salePerType = new int[type];
		totalTicketNumber = 0;
		totalTicketAmount = 0;
	}
	
	public void setFilm(String[] film) {
		this.film = film;
	}
	public String[] getFilm() {
		return film;
	}
	
	public void setType(String[] type) {
		this.type = type;
	}
	public String[] getType() {
		return type;
	}
	
	public void setSalePerFilm(int[] salePerFilm) {
		this.salePerFilm = salePerFilm;
	}
	public int[] getSalePerFilm() {
		return salePerFilm;
	}
	
	public void setSalePerType(int[] salePerType) {
		this.salePerType = salePerType;
	}
	public int[] getSalePerType() {
		return salePerType;
	}
	
	public void setTotalTicketNumber(int totalTicketNumber) {
		this.totalTicketNumber = totalTicketNumber;
	}
	public int getTotalTicketNumber() {
		return totalTicketNumber;
	}
	
	public void setTotalTicketAmount(double totalTicketAmount) {
		this.totalTicketAmount = totalTicketAmount;
	}
	public double getTotalTicketAmount() {
		return totalTicketAmount;
	}
	
	public String toString() {
		String content = "The total sale of each film.\n";
		for(int i = 0; i<film.length; i++) {
			content = content + film[i] + ": " + salePerFilm[i] + ".\n";
		}
		content = content + "Each type of ticket sold:\n";
		for(int i = 0; i<type.length; i++) {
			content = content + type[i] + ": " + salePerType[i] + ".\n";
		}
		content = content + "Total number of tickets sold: " + totalTicketNumber + ".\n";
		content = content + "Total amount of money earned: " + totalTicketAmount + ".\n";
		return content;
	}
}
