package Model;

/**
 * TicketInfo Model
 * @author Yuqian Li
 * @version v1.0
 */
public class TicketInfo {
	private int type; // 1-Child, 2-Adult, 3-Senior, 4-Student
	private String description;
	private boolean IDRequired; // true-ID, false-None
	private int discount;
	
	public TicketInfo() {
	}
	
	public TicketInfo(int type, String description, boolean IDRequired, int discount) {
		this.type = type;
		this.description = description;
		this.IDRequired = IDRequired;
		this.discount = discount;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setIDRequired(boolean IDRequired) {
		this.IDRequired = IDRequired;
	}
	
	public boolean isIDRequired() {
		return this.IDRequired;
	}
	
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public int getDiscount() {
		return this.discount;
	}
	
	/** 
	* @Title: toString
	* @Description: output data had the following format. 
	* @param  null
	* @return String
	* @throws null
	*/
	public String toString() {
		return this.type + "]]]]" + this.description + "]]]]" + this.IDRequired + "]]]]" + this.discount;
	}
	
}


