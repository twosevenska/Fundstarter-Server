package ws;


import fundstarterServer.serverRequestTools;
import globalClasses.Com_object;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.json.JSONObject;

import fundstarterRMI.ServerRMI;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint(value = "/ws")
public class Web {
	private ServerRMI rmi;
	private String id;
    private static final AtomicInteger sequence = new AtomicInteger(1);
    private String type;
    private static final Set<Web> users =
            new CopyOnWriteArraySet<>();
    private final String username;
    private Session session;
    serverRequestTools servertools;
    
    public Web() {
        username = "Anonymous" + sequence.getAndIncrement();
        servertools = new serverRequestTools("localhost", "7000");
    }
	
	@OnMessage
    public void receiveMessage(String message) {
		// one should never trust the client, and sensitive HTML
        // characters should be replaced with &lt; &gt; &quot; &amp;
		
		String[] parts = message.split("-");
		// test values
		
		if (message.equals("refreshallprojects"))
		{
			refreshAllProjects();
		}
		else if (parts[0].equals("projid"))
		{
			refreshproject(parts[1]);
		}
		else if (parts[0].equals("userId"))
		{
			refreshmyprojects(parts[1]);
		}
    }

	private void refreshmyprojects(String id)
	{
		int flag=0;
		type = "projectList";
		int i;
		String json="{\"columns\": [{\"field\": \"projId\", \"title\": \"id\"},{ \"field\": \"title\", \"title\": \"Project\"}, { \"field\": \"active\", \"title\": \"Current Status\"  }, {  \"field\": \"progress\", \"title\": \"Progress\" },{ \"field\": \"endDate\",  \"title\": \"Final Date\"  },{  \"field\": \"link\",  \"title\": \"Link\" }], \"data\":[";
		Hashtable<String, String> content = new Hashtable<String, String>();
		content.put("userId", id);
		Com_object object = servertools.getMyProjectsList(content);
		
		if (object.menuList.menuID.length>0)
		{
			flag = 1;
			Hashtable<String, String> stuff = new Hashtable<String, String>();
			stuff.put("projId" ,object.menuList.menuID[0]);
			Com_object getProjectData = servertools.getProjectData(stuff);
			HashMap<String, String> map = new HashMap<String, String>(getProjectData.elements);
			JSONObject jsonobject = new JSONObject(map);
			json = json+jsonobject+"";
			for (i=1; i<object.menuList.menuID.length; i++)
			{
				json = json+",";
				stuff = new Hashtable<String, String>();
				System.out.print(object.menuList.menuID[i]);
				stuff.put("projId" ,object.menuList.menuID[i]);
				getProjectData = servertools.getProjectData(stuff);
				map = new HashMap<String, String>(getProjectData.elements);
				jsonobject = new JSONObject(map);
				json = json+" "+jsonobject;
				System.out.println(jsonobject);
			}
			
		}
		System.out.println("foi enviado o seguinte objecto JSON "+json);
		sendMessage(json+"]}");//envia para todos os users
	}
	
	private void refreshproject(String message)
	{
		Hashtable<String, String> stuff = new Hashtable<String, String>();
		type = "refreshproject";
		id = message;
		

		stuff = new Hashtable<String, String>();
		stuff.put("projId" ,id);
		
		servertools.getProjectData(stuff);
		
		Com_object getProjectData = servertools.getProjectData(stuff);
		
		HashMap<String, String> map = new HashMap<String, String>(getProjectData.elements);

		JSONObject jsonobject = new JSONObject(map);
		
		try {
			session.getBasicRemote().sendText(jsonobject+"");
			
			System.out.println("7");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("erro na socket");
		}
	}
	
	private void refreshAllProjects()
	{
		int flag=0;
		type = "projectList";
		int i;
		String json="{\"columns\": [{\"field\": \"projId\", \"title\": \"id\"},{ \"field\": \"title\", \"title\": \"Project\"}, { \"field\": \"active\", \"title\": \"Current Status\"  }, {  \"field\": \"progress\", \"title\": \"Progress\" },{ \"field\": \"endDate\",  \"title\": \"Final Date\"  },{  \"field\": \"link\",  \"title\": \"Link\" }], \"data\":[";
		Com_object object = servertools.getActiveMenu(null);
		
		if (object.menuList.menuID.length>0)
		{
			flag = 1;
			Hashtable<String, String> stuff = new Hashtable<String, String>();
			stuff.put("projId" ,object.menuList.menuID[0]);
			Com_object getProjectData = servertools.getProjectData(stuff);
			HashMap<String, String> map = new HashMap<String, String>(getProjectData.elements);
			JSONObject jsonobject = new JSONObject(map);
			json = json+jsonobject+"";
			for (i=1; i<object.menuList.menuID.length; i++)
			{
				json = json+",";
				stuff = new Hashtable<String, String>();
				System.out.print(object.menuList.menuID[i]);
				stuff.put("projId" ,object.menuList.menuID[i]);
				getProjectData = servertools.getProjectData(stuff);
				map = new HashMap<String, String>(getProjectData.elements);
				jsonobject = new JSONObject(map);
				json = json+" "+jsonobject;
				System.out.println(jsonobject);
			}
		}
		object =  servertools.getOldMenu(null); 
		if (object.menuList.menuID.length>0)
		{
			if (flag==1)
			{
				json = json+",";
			}
			flag = 1;
			Hashtable<String, String> stuff = new Hashtable<String, String>();
			stuff.put("projId" ,object.menuList.menuID[0]);
			Com_object getProjectData = servertools.getProjectData(stuff);
			HashMap<String, String> map = new HashMap<String, String>(getProjectData.elements);
			JSONObject jsonobject = new JSONObject(map);
			json = json+jsonobject+"";
			for (i=1; i<object.menuList.menuID.length; i++)
			{
				json = json+",";
				stuff = new Hashtable<String, String>();
				System.out.print(object.menuList.menuID[i]);
				stuff.put("projId" ,object.menuList.menuID[i]);
				getProjectData = servertools.getProjectData(stuff);
				map = new HashMap<String, String>(getProjectData.elements);
				jsonobject = new JSONObject(map);
				json = json+" "+jsonobject;
				System.out.println(jsonobject);
			}	
		}
		System.out.println("foi enviado o seguinte objecto JSON "+json);
		sendMessage(json+"]}");//envia para todos os users
	}
	
	@OnOpen
    public void start(Session session) throws InterruptedException, MalformedURLException, RemoteException, NotBoundException  {
        this.session = session;
        String message = "*" + username + "* connected.";
        users.add(this);
        System.out.println(message);
       
        /*Json_obj stuff = new Json_obj();
        sendMessage("stuff");*/
    }
	
    @OnClose
    public void end() {
    	users.remove(this);
    	// clean up once the WebSocket connection is closed
    }
	
    @OnError
    public void handleError(Throwable t) {
    	t.printStackTrace();
    }
    
    private void sendMessage(String text) {
    	// uses *this* object's session to call sendText()
    	try {
    		for(Web w : users)
    		{
    			if (type.equals(w.type))
    				w.session.getBasicRemote().sendText(text);
    		}
			//this.session.getBasicRemote().sendText(text);
		} catch (IOException e) {
			users.remove(this);
			//this.session.close();
		}
    }
	
}
