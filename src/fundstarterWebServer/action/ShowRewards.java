package fundstarterWebServer.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import fundstarterWebServer.WebServerTools;


public class ShowRewards extends ActionSupport{
    
	private static final long serialVersionUID = 1L;
	private String projId;
	
	public String execute() throws Exception {
    	   return SUCCESS;
	}
	
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	   
	}