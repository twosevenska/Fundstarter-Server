package fundstarterServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UDP_starter extends Thread{
	int tipo = 0; 
	int porta ;
	int portaudp1=4567;
	String ipudp;
	
	public int get_tipo()
	{
		return tipo;
	}
	
	public void set_tipo(int tipo)
	{
		this.tipo = tipo;
	}
	
	public void set_ipudp(String idudp)
	{
		this.ipudp = idudp;
	}
	
	public void set_portaudp1(int portaudp1)
	{
		this.portaudp1 = portaudp1;
	}
	
	public void run()  {
		porta= portaudp1;//servidor1
		tipo=2;
		//servidor2
		try {
			if(cliente()==1)
			{
				try {
					servidor();
					tipo=1;
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void servidor() throws InterruptedException, SocketException
	{
		DatagramSocket aSocket = null;
		String s="";
		int counter=0;
		aSocket = new DatagramSocket(porta);
		tipo=1;
		while(true){
		try{
			
			
			while(true){
				byte[] buffer = new byte[1000]; 			
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				
				s=new String(request.getData(), 0, request.getLength());
				DatagramPacket reply = new DatagramPacket(request.getData(), 
						request.getLength(), request.getAddress(), request.getPort());
				aSocket.send(reply);
				aSocket.setSoTimeout(1100);
				counter=0;
				if (s.equals("secundario"));
				{
					tipo=1;
				}
				if (s.equals("primario"))
				{
					tipo=2;
				}
			}
			
		}catch (IOException e) {
			counter++;
	    	Thread.sleep(1000);
	    	
	    	if (counter==3 && s.equals("primario"))
	    	{	
	    		tipo=1;
	    	}
	    }
		}
	}
	
	private int cliente() throws InterruptedException{

		DatagramSocket aSocket = null;
		try {
			
		aSocket = new DatagramSocket();    
		String texto = "secundario";
		int counter=0;
			while(true){
				// READ STRING FROM KEYBOARD
				Thread.sleep(1000);
				byte [] m = texto.getBytes();
				InetAddress aHost = InetAddress.getByName(ipudp);
				int serverPort = porta;		                                                
				DatagramPacket request = new DatagramPacket(m,m.length,aHost,serverPort);
				aSocket.send(request);			                        
				byte[] buffer = new byte[1000];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
				aSocket.setSoTimeout((int)1100);
				try {
					aSocket.receive(reply);	
					counter=0;
				}
				catch (SocketTimeoutException e) {
				    	counter++;
				    	if (counter==3)
				    	{	
				    		texto = "primario";
				    		tipo=1;
				    		return(1);
				    	}
				}
			} // while
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
		return 0;
	}
}
