package Model;

public class Film {
	private String name;
	private int runtime;
	private String poster;
	
	public Film() {
		
	}
	
	public Film(String name, int runtime, String poster) {
		this.name = name;
		this.runtime = runtime;
		this.poster = poster;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public int getRuntime() {
		return this.runtime;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getPoster() {
		return this.poster;
	}
	
	/** 
	* @Title: toString
	* @Description: output data had the following format. 
	* @param  null
	* @return String
	* @throws null
	*/
	public String toString() {
		return this.name + "]]]]" + this.runtime + "]]]]" + this.poster;
	}
	
	
}
