package Model;

/**
 * Administrator Model
 * @author Yunyao Liu
 * @version v1.0
 */
public class Admin {
	private String id;
	private String password;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* ${tags} 
	*/
	public Admin(){
		
	}
	
	public Admin(String id, String password){
		this.id = id;
		this.password = password;
	}
	
	public void setID(String id){
		this.id = id;
	}
	public String getID(){
		return this.id;
	}

	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return this.password;
	}
	
	/** 
	* @Title toString
	* @Description output data had the following format. 
	* @param  null
	* @return String
	* @throws null
	*/
	public String toString(){
		return id + "]]]]" + password;
	}

}
