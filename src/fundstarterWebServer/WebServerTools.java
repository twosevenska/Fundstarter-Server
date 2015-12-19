package fundstarterWebServer;

import java.util.ArrayList;
import java.util.Hashtable;

import fundstarterServer.serverRequestTools;
import globalClasses.Com_object;
import globalClasses.Com_object.operationtype;
import globalClasses.Menu_list;

public class WebServerTools {

	Hashtable <String, String> content = new Hashtable<String,String>();
	ArrayList <String> list = new ArrayList<String>();
	Com_object globalobject;
	serverRequestTools ducktatorServer = new serverRequestTools("localhost", "7000");
	
	public Boolean Register(String username, String password){
		content = new Hashtable<String,String>();
		
		content.put("username",username);
		content.put("password",password);
		
		globalobject = ducktatorServer.registerUser(content);
		if (globalobject.elements.get("status").equals("0")){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public String createproject(String[] voteOptions, Hashtable <String, String> externalcontent){
		String[] idList = {};
		Menu_list vote_options = new Menu_list(voteOptions, idList);
		
		return createprojectPut(vote_options, externalcontent);
		
	}
	
	private String createprojectPut(Menu_list vote_options, Hashtable <String, String> externalcontent)
	{
	
		globalobject = new Com_object(0,operationtype.check_wallet,externalcontent,vote_options);
		globalobject = ducktatorServer.createProject(globalobject, globalobject.elements);
		return globalobject.elements.get("projId");
	}
	
	public Boolean createreward(Hashtable <String, String> externalcontent)
	{
	
		globalobject = new Com_object(0,operationtype.check_wallet,externalcontent);
		globalobject = ducktatorServer.createTier(externalcontent);
		if (globalobject.elements.get("tierStatus") == "0")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String viewprojecttitle(String id)
	{
		content = new Hashtable<String,String>();
		content.put("projId", id);
		return ducktatorServer.getProjectData(content).elements.get("title");
	}
	
	public String viewprojectdescription(String id)
	{
		content = new Hashtable<String,String>();
		content.put("projId", id);
		return ducktatorServer.getProjectData(content).elements.get("description");
	}
}
