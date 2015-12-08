package fundstarterServer;

import java.net.*;
import java.util.ArrayList;

import globalClasses.Com_object;

import java.io.*;

public class connectionTCPClient extends Thread{
	ArrayList<Connection> clientThreads;
	ServerSocket listenSocket;
	
	public connectionTCPClient(int port){
		clientThreads = new ArrayList<Connection>();
		this.start();
		try{
			int numero=0;
            int serverPort = port;
            System.out.println("Status: Listening on port "+ Main.port);
            listenSocket = new ServerSocket(serverPort);
            System.out.println("LISTEN SOCKET="+listenSocket);
            while(true){
            	numero++;
                Socket clientSocket = listenSocket.accept(); // BLOQUEANTE
                System.out.println("CLIENT_SOCKET (created at accept())="+clientSocket);
                Connection tempcon;
                tempcon = new Connection(clientSocket, numero);
                clientThreads.add(tempcon);
            }
        }catch(IOException e){
        	System.out.println("Listen:" + e.getMessage());
        }
	}
}

class Connection extends Thread{
	ObjectInputStream in;
	ObjectOutputStream out;
    Socket clientSocket;
    int thread_number;
    boolean verbose = false;
    
    public Connection (Socket aClientSocket, int numero) {
        thread_number = numero;
        try{
            clientSocket = aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.start();
        }catch(IOException e){System.out.println("Connection:" + e.getMessage());
        	}
    }

    public void run(){
        Com_object data;
        Com_object answer;
        try{
        	serverRequestTools serverRTools = new serverRequestTools();
            while(true){
            	if(verbose)
            		System.out.println("Caught a client");
            	data = (Com_object) in.readObject();
            	if(verbose)
            		System.out.println("T["+thread_number + "] Got user: "+data.elements.get("username"));
            	answer = serverRTools.serverCallOperation(data, data.idpackage,data.op, data.elements);
                out.writeObject(answer);
            }
        }catch(IOException | ClassNotFoundException e ){System.out.println("Cliente terminou");
        }
    }
}