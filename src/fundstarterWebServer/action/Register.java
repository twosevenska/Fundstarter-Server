package fundstarterWebServer.action;

import com.opensymphony.xwork2.ActionSupport;
import fundstarterWebServer.model.RegisterStore;
import fundstarterWebServer.WebServerTools;

public class Register extends ActionSupport{
  
	private static final long serialVersionUID = -8701660517806676894L;
	private RegisterStore registerBean;
	WebServerTools wtools = new WebServerTools();
	
	public String execute() throws Exception {
		
	   	String user = registerBean.getUsername();
	   	String pass = registerBean.getPassword();
	   	
	   	System.out.println("Username from bean: "+user);
	   	System.out.println("Password from bean: "+pass);
	   	
	   	if(wtools.Register(user, pass)){
	   		return SUCCESS;
	   	}
	   	return ERROR;
	}
   
	
	public RegisterStore getRegisterStore(){
	   return registerBean;
	}
   
	public void setRegisterStore(RegisterStore registerStore){
	   this.registerBean = registerStore;
	}

}