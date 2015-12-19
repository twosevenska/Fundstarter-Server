package fundstarterWebServer.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import fundstarterWebServer.WebServerTools;


public class showProject extends ActionSupport{
    
	private static final long serialVersionUID = 1L;
	private String projId;
	private String projTitle;
	private String projDescri;
	private WebServerTools wst;
	
	public String execute() {
		wst = new WebServerTools();
		
		this.projTitle = wst.viewprojecttitle(projId);
		this.projDescri = wst.viewprojectdescription(projId);
		
		return SUCCESS;
   	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	public String getProjTitle() {
		return projTitle;
	}
	
	public String getProjDescri() {
		return projDescri;
	}
}