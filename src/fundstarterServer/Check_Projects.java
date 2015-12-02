package fundstarterServer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.TimerTask;

public class Check_Projects extends TimerTask   {
		  Thread myThreadObj;
		  Check_Projects (Thread t){
			  this.myThreadObj=t;
		  }
		  public void run() {
			  serverRequestTools serverTools= new serverRequestTools();
			  try {
				serverTools.rmi.finish_projects();
			} catch (RemoteException | InterruptedException | SQLException e) {
				serverTools.test_RMI();
			}
		 }
}
