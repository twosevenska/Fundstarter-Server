package fundstarterWebServer.model;


/**
 * Model class that stores a message.
 * @author Bruce Phillips
 *
 */
public class RegisterStore {
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		return "Username: "+getUsername() + "Password" + getPassword();
	}
}