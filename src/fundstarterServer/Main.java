package fundstarterServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fundstarterServer.connectionTCPClient;

public class Main {
	public static String iprmi = "localhost";
	public static Integer rmiport = 7000;
	public static String ipupd = "localhost";
	public static Integer udpport = 6000;
	
	//secondary, RMI, secondary port, rmi port
	

	private static void loadArguments(String[] arguments){
		ipupd = arguments[0];
		udpport = Integer.getInteger(arguments[1]);
		iprmi = arguments[2];
		rmiport = Integer.getInteger(arguments[3]);
	}
	
	/*
	private static void loadProperties(){
		Properties prop = new Properties();
		InputStream input = null;
		boolean valid = true;
		try {
			input = new FileInputStream("Sconfig.properties");

			// Load a properties file
			prop.load(input);

			// Get the properties, almost like a hash table
			String tempserver = prop.getProperty("server");
			String tempsudp = prop.getProperty("udpadress");
			String tempPort = prop.getProperty("udpport");
			String tempPort2 = prop.getProperty("serverPort");
			
			if( tempPort == null)
				valid = false;
			
			if(valid)
				if(tempPort != null && tempserver != null && tempsudp!=null && tempPort2 != null)
					rmiport =  Integer.getInteger(tempPort2) ;
					iprmi = tempserver;
					udpport = Integer.getInteger(tempPort);
					ipupd = (tempsudp);

		} catch (IOException ex) {
			//ex.printStackTrace();
		} finally {
			if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						System.out.println("Erro a fechar o ficheiro");
					}
			}
		}
	}	
		*/
    
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread());
		if(args.length == 4){
			loadArguments(args);
		}else{
			System.out.println("Number of arguments invalid, starting with default port value = 6000 on server side and 7000 for RMI with localhost addresses" );
		}
		
		
		
		System.out.println("The dawngate opens.");
		UDP_starter udp = new UDP_starter();
		udp.set_ipudp(ipupd);
		udp.set_tipo(2);
		udp.set_portaudp1(udpport);
		udp.start();
		
		while (udp.get_tipo()==2)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Erro a dormir no inicio do servidor");
			}
		}
		Time_stuff timestart= new Time_stuff();
		connectionTCPClient connTCP= new connectionTCPClient();
	}
}
