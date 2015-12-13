package fundstarterServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fundstarterServer.connectionTCPClient;

public class Main {
	public static Integer port;
	public static String iprmi;
	public static Integer rmiport;
	public static String ipupd;
	public static Integer udpport;
	
	
	//secondary, RMI, secondary port, rmi port
	

	private static void loadArguments(String[] arguments){
		System.out.println(arguments[0]+" " + arguments[1]+" "+arguments[2]+" "+arguments[3] +" "+ arguments[4]);
		port = Integer.parseInt(arguments[0]);
		ipupd = arguments[1];
		udpport = Integer.parseInt(arguments[2]);
		iprmi = arguments[3];
		rmiport = Integer.parseInt(arguments[4]);
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
		if(args.length == 5){
			loadArguments(args);
		}else{
			System.out.println("Number of arguments invalid, starting with default port value = 6000 on server side and 7000 for RMI with localhost addresses" );
			port = 6000;
			ipupd = "localhost";
			udpport = 6060;
			iprmi = "localhost";
			rmiport = 7000;
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
		connectionTCPClient connTCP= new connectionTCPClient(port);
	}
}
