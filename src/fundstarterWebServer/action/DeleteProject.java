package fundstarterWebServer.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import fundstarterWebServer.WebServerTools;


public class DeleteProject extends ActionSupport implements SessionAware {
    
	private static final long serialVersionUID = 1L;
	private String projId;
	private WebServerTools wst;
	
	private Map<String, Object> sessionMap;
	   public String execute() throws Exception {
	
	       if (sessionMap.containsKey("userName")) {
	    	   
	    	   wst = new WebServerTools();
	    	   
	    	   if(!wst.checkAdmin((String) sessionMap.get("userId"), projId)){
	    		   return ERROR;
	    	   }
	    	   
	    	   wst.deleteProject(projId);
	    	   
	    	   return SUCCESS;
	       }
	        
	       return INPUT;
	   }
	
	   @Override
	   public void setSession(Map<String, Object> sessionMap) {
	       this.sessionMap = sessionMap;
	   }

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	   
	}