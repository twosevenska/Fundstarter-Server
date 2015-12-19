package fundstarterWebServer.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;


public class UserPanel extends ActionSupport implements SessionAware {
    
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> sessionMap;
	private String userName;
	private String password;
	
	   public String checkPrivilege() {
	
	       if (sessionMap.containsKey("userName")) {
	    	   return SUCCESS;
	       }
	        
	       return INPUT;
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
	}