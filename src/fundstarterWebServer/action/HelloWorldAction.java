package fundstarterWebServer.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import fundstarterWebServer.model.MessageStore;

/**
 * Acts as a Struts 2 controller that responds
 * to a user action by setting the value
 * of the Message model class, and returns a String 
 * result.
 * @author Bruce Phillips
 *
 */
public class HelloWorldAction extends ActionSupport implements SessionAware, ParameterNameAware{

	private static final long serialVersionUID = 1L;
	
	private MessageStore messageStore;
	private Map<String, Object> userSession ;
	
	private static final String HELLO_COUNT = "helloCount";
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String execute() throws Exception {
		
		messageStore = new MessageStore() ;
		
		//Action included a query string parameter of userName
		//or a form field with name of userName
		if (userName != null) {
			messageStore.setMessage( messageStore.getMessage() + " " + userName);
		}
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		userSession = session ;
	}
	
	public boolean acceptableParameterName(String parameterName) {
		boolean allowedParameterName = true ;
		if ( parameterName.contains("session")  || parameterName.contains("request") ) {
			allowedParameterName = false ;
		} 
		return allowedParameterName;
	}
}