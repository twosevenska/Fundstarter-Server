package fundstarterServer;
import java.io.IOException;

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
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/wb")
public class websocket {
    private static final AtomicInteger sequence = new AtomicInteger(1);
    private static final Set<websocket> users =
            new CopyOnWriteArraySet<>();
    private final String username;
    private Session session;
    
    public websocket() {
        username = "User" + sequence.getAndIncrement();
    }
	
	@OnMessage
    public void receiveMessage(String message) {
		// one should never trust the client, and sensitive HTML
        // characters should be replaced with &lt; &gt; &quot; &amp;
		
		// test values
		Hashtable<String, String> numbers = new Hashtable<String, String>();
		numbers.put("id", "1");
		numbers.put("name", "Stuff");
		numbers.put("status", "active");
		numbers.put("percentage", "99999");
		numbers.put("date", "31-02-2016");
		numbers.put("link", "Lorem");
		//tranformar hashtable para JSON
		HashMap<String, String> map = new HashMap<String, String>(numbers);
		JSONObject jsonobject = new JSONObject(map);
		sendMessage(" "+jsonobject);
		System.out.println("foi enviado o seguinte objecto JSON "+jsonobject);
    }
	
	@OnOpen
    public void start(Session session) throws InterruptedException  {
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
    		for(websocket w : users)
    		{
    			w.session.getBasicRemote().sendText(text);
    		}
			//this.session.getBasicRemote().sendText(text);
		} catch (IOException e) {
			users.remove(this);
			//this.session.close();
		}
    }
	
}
