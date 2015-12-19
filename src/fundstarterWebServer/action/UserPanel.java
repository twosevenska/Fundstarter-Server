package fundstarterWebServer.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import fundstarterWebServer.WebServerTools;


public class UserPanel extends ActionSupport implements SessionAware {
    
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> sessionMap;
	private String userName;
	private String password;
	private String wallet;
	private WebServerTools wst;
	
	   public String checkPrivilege() {
		   
		   wst = new WebServerTools();
		   
		   this.wallet = wst.getWalletAmount((String) sessionMap.get("userId"));
		   
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

	public String getWallet() {
		return wallet;
	}

	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	}