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
	
	public Boolean createproject(Menu_list vote_options, Hashtable <String, String> externalcontent)
	{
	
		globalobject = new Com_object(0,operationtype.check_wallet,externalcontent,vote_options);
		globalobject = ducktatorServer.createProject(globalobject, globalobject.elements);
		if (globalobject.elements.get("projectStatus") == "0")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
