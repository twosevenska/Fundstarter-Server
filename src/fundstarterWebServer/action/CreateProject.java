package fundstarterWebServer.action;

import java.util.Hashtable;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

import fundstarterWebServer.WebServerTools;
import fundstarterWebServer.model.CreateProjectStore;


public class CreateProject extends ActionSupport implements SessionAware {
    
	private static final long serialVersionUID = 1L;
	
	private WebServerTools wst;
	private CreateProjectStore createProjectStoreBean;
	private Map<String, Object> sessionMap;
	
	   public String execute() throws Exception {
		   
	      if (sessionMap.containsKey("userName")) {
	    	   //("projId")("reqAmmount")("description")
	    	   Hashtable<String,String> rewContent = new Hashtable<String,String>();
	    	   //("projName")("userId")("description")("endDate")("reqAmmount")
	    	   Hashtable<String,String> projContent = new Hashtable<String,String>();
	    	  
	    	   String result;
	    	   String projName = createProjectStoreBean.getProjName();
	    	   String description = createProjectStoreBean.getDescription();
	    	   String dateDay = createProjectStoreBean.getDateDay();
	    	   String dateMonth = createProjectStoreBean.getDateMonth();
	    	   String dateYear = createProjectStoreBean.getDateYear();
	    	   String amount =  createProjectStoreBean.getAmount();
	    	   String[] rewardDescriptions = {createProjectStoreBean.getRewardDescription1(),
	    			   createProjectStoreBean.getRewardDescription2(),
	    			   createProjectStoreBean.getRewardDescription3(),
	    			   createProjectStoreBean.getRewardDescription4()};
	    	   String[] rewardAmount = {createProjectStoreBean.getRewardAmount1(),
	    			   createProjectStoreBean.getRewardAmount2(),
	    			   createProjectStoreBean.getRewardAmount3(),
	    			   createProjectStoreBean.getRewardAmount4()};
	    	   String[] voteOptions = {createProjectStoreBean.getVoteOption1(),
	    			   createProjectStoreBean.getVoteOption2()};
	    	   
	    	   for(int i = 0; i < 2; i++){
	    		   if(!voteOptions[i].equals("")){
	    			   return INPUT;
	    		   }
	    	   }
	    	   
	    	   wst = new WebServerTools();
	    	  
	    	   projContent.put("projName", projName);
	    	   projContent.put("userId", (String) sessionMap.get("userId"));
	    	   projContent.put("description", description);
	    	   projContent.put("endDate", dateYear+"-"+dateMonth+"-"+dateDay);
	    	   projContent.put("reqAmmount", amount);
	    	   
	    	   result = wst.createproject(voteOptions, projContent);
	    	   
	    	   if (result.equals("-1")){
	    		   return INPUT;
	    	   }
	    	   
	    	   for(int i = 0; i < 4; i++){
	    		   if(!rewardDescriptions[i].equals("")){
		    		   rewContent.put("projId", result);
		    		   rewContent.put("reqAmmount", rewardAmount[i]);
		    		   rewContent.put("description", rewardDescriptions[i]);
		    		   wst.createreward(rewContent);
		    		   rewContent.clear();
	    		   }
	    	   }

	    	   return SUCCESS;
	       }
	        
	       return INPUT;
	   }

	public CreateProjectStore getCreateProjectStoreBean() {
		return createProjectStoreBean;
	}

	public void setCreateProjectStoreBean(CreateProjectStore createProjectStoreBean) {
		this.createProjectStoreBean = createProjectStoreBean;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	   
}