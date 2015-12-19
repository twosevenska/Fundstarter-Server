package fundstarterWebServer.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;


public class Redirect extends ActionSupport implements SessionAware {
    
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> sessionMap;
	   public String execute() throws Exception {
	
	       if (sessionMap.containsKey("userName")) {
	    	   return ERROR;
	       }
	        
	       return INPUT;
	   }
	
	   @Override
	   public void setSession(Map<String, Object> sessionMap) {
	       this.sessionMap = sessionMap;
	   }
	   
	}