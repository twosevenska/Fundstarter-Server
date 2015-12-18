package fundstarterWebServer.action;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware {
    
   private Map<String, Object> sessionMap;
   private String userName;
   private String password;

   public String login() {
       String loggedUserName = null;

       // check if the userName is already stored in the session
       if (sessionMap.containsKey("userName")) {
           loggedUserName = (String) sessionMap.get("userName");
       }
       if (loggedUserName != null && loggedUserName.equals("admin")) {
           return SUCCESS; // return welcome page
       }
        
       // if no userName stored in the session,
       // check the entered userName and password
       if (userName != null && userName.equals("admin")
               && password != null && password.equals("admin")) {
            
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
}