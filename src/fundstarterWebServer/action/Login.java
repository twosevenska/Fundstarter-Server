package fundstarterWebServer.action;

import java.util.Hashtable;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import fundstarterServer.serverRequestTools;
import globalClasses.Com_object;
import globalClasses.Com_object.operationtype;

public class Login extends ActionSupport implements SessionAware {
    
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> sessionMap;
	private String userName;
	private String password;
   
	   private static serverRequestTools ducktatorServer = new serverRequestTools("localhost", "7000");
	
	   public String login() {
	       String loggedUserName = null;
	
	       // if no userName stored in the session,
	       // check the entered userName and password
	       if (userName != null && password != null && loginUser(userName, password) != 0) {
	            
	           // add userName to the session
	           sessionMap.put("userName", userName);
	            
	           return SUCCESS; // return welcome page
	       }
	        
	       // in other cases, return login page
	       return INPUT;
	   }
	    
	   public String logout() {
	       // remove userName from the session
	       if (sessionMap.containsKey("userName")) {
	           sessionMap.remove("userName");
	       }
	       return SUCCESS;
	   }
	
	   @Override
	   public void setSession(Map<String, Object> sessionMap) {
	       this.sessionMap = sessionMap;
	   }
	    
	   public void setUserName(String userName) {
	       this.userName = userName;
	   }
	    
	   public void setPassword(String password) {
	       this.password = password;
	   }
	   
	   public static int loginUser(String username, String password){
			int userId = 0;
			Hashtable<String, String> loginData = new Hashtable<String, String>();
			Com_object userObject;
			
			loginData.put("username", username);
			loginData.put("password", password);
			
			userObject = ducktatorServer.loginUser(loginData);
			
			userId = Integer.parseInt(userObject.elements.get("userId"));
			
			System.out.println("userId = "+userId);
			
			return userId;
	   }
	}