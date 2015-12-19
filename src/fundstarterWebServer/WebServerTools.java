package fundstarterWebServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.json.JSONObject;

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
	
	public String getWalletAmount(String id){
		Hashtable <String, String> externalcontent = new Hashtable <String, String>();
		externalcontent.put("userId", id);
		
		return ducktatorServer.checkWallet(externalcontent).elements.get("wallet");
	}
	
	public boolean deleteProject(String id){
		Hashtable <String, String> externalcontent = new Hashtable <String, String>();
		externalcontent.put("projID", id);
		
		ducktatorServer.nukeProject(externalcontent);
		return true;
	}
	
	public boolean checkAdmin(String userId, String projId){
		Hashtable <String, String> externalcontent = new Hashtable <String, String>();
		externalcontent.put("projID", projId);
		externalcontent.put("userId", userId);
		
		if (ducktatorServer.checkAdmin(content).elements.get("admin").equals("0")){
			return true;
		}else{
			return false;
		}
	}
	
	public String refreshallrewards(String id, String userid)
	{
		int i;
		String json="{\"columns\": [{\"field\": \"description\",\"title\": \"Tier\"},{\"field\": \"ammount\",\"title\": \"Money\"}],\"data\": [";
		Hashtable<String, String> content = new Hashtable<String, String>();
		content.put("projId", id);
		
		Com_object object = ducktatorServer.getRewardsMenu(content);
		
		if (object.menuList.menuID.length>0)
		{
			Hashtable<String, String> stuff = new Hashtable<String, String>();
			stuff.put("rewId" ,object.menuList.menuID[0]);
			stuff.put("userId" ,userid);
			Com_object getProjectData = ducktatorServer.getTierInfo(stuff);
			HashMap<String, String> map = new HashMap<String, String>(getProjectData.elements);
			JSONObject jsonobject = new JSONObject(map);
			json = json+jsonobject+"";
			for (i=1; i<object.menuList.menuID.length; i++)
			{
				json = json+",";
				stuff = new Hashtable<String, String>();
				System.out.print(object.menuList.menuID[i]);
				stuff.put("rewId" ,object.menuList.menuID[i]);
				stuff.put("userId" ,userid);
				getProjectData = ducktatorServer.getTierInfo(stuff);
				map = new HashMap<String, String>(getProjectData.elements);
				jsonobject = new JSONObject(map);
				json = json+" "+jsonobject;
				System.out.println(jsonobject);
			}
			
		}
		System.out.println("foi enviado o seguinte objecto JSON "+json);
		return(json+"]}");//envia para todos os users
	}
	
}
