package fundstarterServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Hashtable;

import fundstarterRMI.ServerRMI;
import globalClasses.Com_object;
import globalClasses.Menu_list;
import globalClasses.Com_object.*;

public class serverRequestTools {
	public ServerRMI rmi;
	
	public serverRequestTools()
	{
		System.out.println(Main.iprmi+" "+Main.rmiport);
		try {
			//rmi = (ServerRMI) Naming.lookup("rmi://localhost:7000/rmi");
			
			rmi = (ServerRMI) Naming.lookup("rmi://"+Main.iprmi+":"+Main.rmiport+"/rmi");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Wrong URL");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			int i = 0;
			System.out.println("A tentar reconnectar");
			while(i<3)
			{
				try {
					rmi = (ServerRMI) Naming.lookup("rmi://"+Main.iprmi+":"+Main.rmiport+"/rmi");
					i=4;
				} catch (MalformedURLException | RemoteException
						| NotBoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("A tentar reconnectar");
					i++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						System.out.println("Erro ao dormir no serverRequestTools");
					}
				}
			}
			if (i==3)
			{
				System.out.println("Não foi possivel ligar ao RMI, o programa vai terminar");
				System.exit(0);
			}
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Binding não encontrado");
		}
	}
	
	public void test_RMI()
	{
		try {
			rmi.checkRMI();
		} catch (RemoteException | InterruptedException e) {
			// TODO Auto-generated catch block
			int i = 0;
			System.out.println("A tentar reconnectar");
			while(i<3)
			{
				try {
					rmi = (ServerRMI) Naming.lookup("rmi://"+Main.iprmi+":"+Main.rmiport+"/rmi");
					i=4;
				} catch (MalformedURLException | RemoteException
						| NotBoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("A tentar reconnectar");
					i++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						System.out.println("Erro ao dormir no serverRequestTools");
					}
				}
			}
			if (i==3)
			{
				System.out.println("Não foi possivel ligar ao RMI, o programa vai terminar");
				System.exit(0);
			}
		} 
	}
	
	public Com_object serverCallOperation(Com_object pack, String  id, operationtype op, Hashtable<String, String> content){
		Com_object answer = null;
		test_RMI();
		int status=0;
		System.out.println(id);
		try {
			status = rmi.check_id(id);
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		
		if (status==1)
			{
				switch(op){
				case see_all_proj_off: answer = getOldMenu(content);
					break;
				case see_all_proj_on: answer = getActiveMenu(content);
					break;
				case see_proj_responses: answer = getMessageBoard(content);
					break;
				case login: answer = loginUser(content);
					break;
				case register: answer = registerUser(content);
					break;
				case create_proj: answer = createProject(pack, content);
					break;
				case see_my_reward: answer = getMyRewards(content);
					break;
				case get_notification: answer = getNotification(content);
					break;
				case pledge_project: answer = addPledge(content);
					break;
				case create_message: answer = createNotification(content);
					break;
				case change_pledge: answer = changePledge(content);
					break;
				case remove_pledge: answer = removePledge(content);
					break;
				case see_reward: answer = getTierInfo(content);
					break;
				case see_all_rewards: answer = getRewardsMenu(content);
					break;
				case cancel_project: answer = nukeProject(content);
					break;
				case add_meta: answer = createTier(content);
					break;
				case remove_meta: answer = nukeTier(content);
					break;
				case check_wallet: answer = checkWallet(content);
					break;
				case add_wallet: answer = addMoneyWallet(content);
					break;
				case see_proj: answer = getProjectData(content);
					break;
				case check_admin: answer = checkAdmin(content);
					break;
				case see_vote_options: answer = getVoteOptions(content);
					break;
				case answer_message: answer = answerNotification(content);
					break;
				case check_proj_name: answer = checkProjectName(content);
					break;
				case get_proj_id: answer = getProjectID(content);
					break;
				case see_vote_results: answer = seeVoteResults(content);
					break;
				case see_my_projs: answer = getMyProjectsList(content);
					break;
				default: System.out.println("Err: serverCallOperation - Switch case not found for " + op);
				break;
				}
				try {
					rmi.register_id(id);
				} catch (RemoteException | InterruptedException | SQLException e) {
					test_RMI();
				}
			}
		return answer;
	}
	/*check*/
	public Com_object getProjectID(Hashtable<String, String> content)
	{
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		try {
			answerHash.put("projId", Integer.toString(rmi.get_id_proj(content.get("projectTitle")))); // 0 == Success, 1 == Failure
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		//Call function to create a reply to a message
		int id = 1337; // Fix this with something that actually matters.
		
		answer = new Com_object(id, operationtype.check_proj_name, answerHash);
		return answer;
	}
	/*check*/
	public Com_object loginUser(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		int id = 0;
		test_RMI();
		//Call function to fetch data from DB
		//int id = 1337; // Fix this with something that actually matters.
		
		try {
			id = rmi.search_client(content.get("username"),content.get("password"));
			
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		answerHash.put("userId", id+"");
		answer = new Com_object(id, operationtype.login, answerHash);
		
		return answer;
	}
	/*check*/
	public Com_object registerUser(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		int status=1;
		int id = 0;
		//Call function to check if the username is availabe in the DB
		try {
			status = rmi.search_user(content.get("username"));
		} catch (RemoteException | InterruptedException | SQLException e1) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		
		 // 0 == Success, 1 == Failure
		
		if (status == 1)
		{	
			answerHash.put("status", "0");
			try {
				id = rmi.register_user(content.get("username"),content.get("password"));
			} catch (RemoteException | InterruptedException | SQLException e) {
				// TODO Auto-generated catch block
				test_RMI();
			}
		}
		else
			answerHash.put("status", "1");
		
		answer = new Com_object(id, operationtype.register, answerHash);
		
		return answer;
	}
	/*check*/
	public Com_object checkWallet(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//Call function to fetch the Amount of money in the wallet of this userId
		int id = 1337; // Fix this with something that actually matters.
		try {
			answerHash.put("wallet", ""+rmi.see_money(content.get("userId")));
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		answer = new Com_object(id, operationtype.check_wallet, answerHash);
		
		return answer;
	}
	/*check*/
	public Com_object addMoneyWallet(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//Call function to check if it's possible to add the money in the DB
		int id = 1337; // Fix this with something that actually matters.
		try {
			answerHash.put("money", rmi.update_money(content.get("wallet"), content.get("userId"))+"");
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		} // 0 == Success, 1 == Failure
		answer = new Com_object(id, operationtype.add_wallet, answerHash);
		
		return answer;
	}
	/*check and add vote_options*/
	public Com_object createProject(Com_object pack, Hashtable<String, String> content){
		Com_object answer = null;
		String[] stuffdes= pack.menuList.menuString; 
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		int status = 0;
		int i;
		int var = 0;
		try {
			var = rmi.insert_project(content.get("projName"), content.get("userId"), content.get("description"), content.get("endDate"), content.get("reqAmmount"));
			
			for(i=0; i<pack.menuList.menuString.length; i++)
				rmi.insert_vote_option(""+var, Integer.toString(i+1), ""+stuffdes[i]);
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}		
		
		
		
		//Call function to check if it's possible to create project
		int id = 1337; // Fix this with something that actually matters.
		if (var != -1)
			answerHash.put("projectStatus", "0"); // 0 == Success, 1 == Failure
		else 
			answerHash.put("projectStatus", "1"); // 0 == Success, 1 == Failure
		
		answer = new Com_object(id, operationtype.create_proj, answerHash);
		
		return answer;
	}
	/*check*/
	public Com_object createTier(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//Call function to check if it's possible to create tier
		int id = 1337; // Fix this with something that actually matters.
	
		
		try {
			if (rmi.insert_tier(content.get("projId"),content.get("reqAmmount"),content.get("description"))!=0)
			{	
				answerHash.put("tierStatus", "0"); // 0 == Success, 1 == Failure
			}
			else
				answerHash.put("tierStatus", "1");
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		} // 0 == Success, 1 == Failure
		answer = new Com_object(id, operationtype.add_meta, answerHash);
		
		return answer;
	}
	/*check*/
	public Com_object getOldMenu(Hashtable<String, String> content){
		Com_object answer = null;
		Menu_list menuAnswer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//To fill menuList with project strings and idList with their respective ID
		int id = 1337; // Fix this with something that actually matters.
		answer = new Com_object(id, operationtype.see_all_proj_off, answerHash);
		
		try {
			menuAnswer = rmi.see_noactive_projects();
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		answer.menuList = menuAnswer;
		
		return answer;
	}
	/*check*/
	public Com_object getActiveMenu(Hashtable<String, String> content){
		Com_object answer = null;
		Menu_list menuAnswer = null;
		String[] menuList = {"Homestuck Adventure Game"};
		String[] idList = {"346"};
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//To fill menuList with project strings and idList with their respective ID
		int id = 1337; // Fix this with something that actually matters.
		answer = new Com_object(id, operationtype.see_all_proj_on, answerHash);
		try {
			menuAnswer = rmi.see_active_projects();
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		answer.menuList = menuAnswer;
		
		return answer;
	}
	/*check*/
	public Com_object getProjectData(Hashtable<String, String> content){
		Com_object answer = null;
		//Call function to get all the info needed.
		try {
			answer = rmi.search_project(content.get("projId"));
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		return answer;
	}
	/*check*/
	public Com_object checkAdmin(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		int status=1;
		//Call function to check if the user is admin
		int id = 1337; // Fix this with something that actually matters.
		try {
			status = rmi.check_admin_project(content.get("userId"), content.get("projId"));
			answerHash.put("admin", status+""); // 0 == Success, 1 == Failure
		} catch (RemoteException | InterruptedException | SQLException e) {
			test_RMI();
		}

		answer = new Com_object(id, operationtype.check_admin, answerHash);
		
		return answer;
	}
	/*need test*/
	public Com_object nukeProject(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//Call function to check if the user is admin
		//Call function to delete project
		int id = 1337; // Fix this with something that actually matters.
		try {
			answerHash.put("delete", ""+rmi.remove_project(content.get("projID")));
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		} // 0 == Success, 1 == Failure
		answer = new Com_object(id, operationtype.cancel_project, answerHash);
		
		return answer;
	}
	/*check*/
	public Com_object getRewardsMenu(Hashtable<String, String> content){
		Com_object answer = null;
		Menu_list menuAnswer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//To fill menuList with project strings and idList with their respective ID
		int id = 1337; // Fix this with something that actually matters.
		answer = new Com_object(id, operationtype.see_all_rewards, answerHash);
		try {
			menuAnswer = rmi.see_all_rewards(content.get("projId"));
			menuAnswer.menuString = formatRewList(menuAnswer.menuString);
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		answer.menuList = menuAnswer;
		
		return answer;
	}
	/*check*/
	private String[] formatRewList(String[] rewList){
		int resultLen = rewList.length;
		String[] result = new String[resultLen];
		int i = 0;
		
		for(String str: rewList){
			result[i] = "Pledge $".concat(str).concat("  or more");
			i++;
		}
		
		return result;
	}
	/*need test*/
	public Com_object nukeTier(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//Call function to check if the user is admin
		//Call function to delete tier
		int id = 1337; // Fix this with something that actually matters.
		
		try {
			answerHash.put("delete", rmi.remove_reward(content.get("rewID"))+"");
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		} // 0 == Success, 1 == Failure
		answer = new Com_object(id, operationtype.remove_meta, answerHash);
		
		return answer;
	}
	/*not complete*/
	public Com_object addPledge(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//Call function to add the pledge
		int id = 1337; // Fix this with something that actually matters.
		answer = new Com_object(id, operationtype.pledge_project, answerHash);
		try {
			answerHash.put("pledgeConfirm", ""+rmi.insert_pledge(""+content.get("projId"),""+content.get("rewId"),""+content.get("userId"),""+content.get("voteId"),""+content.get("ammount"))); // 0 == Success, 1 == Failure
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		return answer;
	}
	/*Goodbye changePledge*/
	public Com_object changePledge(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//Call function to add the pledge
		int id = 1337; // Fix this with something that actually matters.
		answerHash.put("pledgeChangeConfirm", "0"); // 0 == Success, 1 == Failure
		answer = new Com_object(id, operationtype.change_pledge, answerHash);
		
		return answer;
	}
	/*Goodbye removePledge*/
	public Com_object removePledge(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		/*
		//Call function to add the pledge
		int id = 1337; // Fix this with something that actually matters.
		answerHash.put("pledgeRemoveConfirm", "0"); // 0 == Success, 1 == Failure
		answer = new Com_object(id, operationtype.remove_pledge, answerHash);
		*/
		return answer;
	}
	/*need test*/	
	public Com_object getVoteOptions(Hashtable<String, String> content){
		Com_object answer = null;
		Menu_list menuAnswer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//To fill menuList with project strings and idList with their respective ID
		int id = 1337; // Fix this with something that actually matters.
		answer = new Com_object(id, operationtype.see_all_proj_off, answerHash);
		
		try {
			menuAnswer = rmi.see_all_vote(content.get("projId"));
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		answer.menuList = menuAnswer;
		
		return answer;
	}
	/*need test*/
	public Com_object getMyRewards(Hashtable<String, String> content){
		Com_object answer = null;
		Menu_list menuAnswer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//To fill menuAnswer with vote options strings and idList with their respective ID
		int id = 1337; // Fix this with something that actually matters.
		answer = new Com_object(id, operationtype.see_my_reward, answerHash);
		
		try {
			menuAnswer =rmi.see_my_rewards(content.get("userId"));
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		answer.menuList = menuAnswer;
		
		return answer;
	}
	/*need test*/
	public Com_object getMessageBoard(Hashtable<String, String> content){
		Com_object answer = null;
		Menu_list menuAnswer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//To fill menuList with project strings and idList with their respective ID
		int id = 1337; // Fix this with something that actually matters.
		answer = new Com_object(id, operationtype.see_all_proj_off, answerHash);
		
		try {
			menuAnswer = rmi. see_sms_project(content.get("projId"));
		} catch (RemoteException | InterruptedException | SQLException e) {
			test_RMI();
		}
		answer.menuList = menuAnswer;
		
		return answer;
	}
	/*noteId**/
	public Com_object getNotification(Hashtable<String, String> content){
		Com_object answer = null;
		try {
			answer = rmi.get_sms_info(content.get("notId"));
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		return answer;
	}
	//criar questao userId, projid, title, descri
	public Com_object createNotification(Hashtable<String, String> content){
		Com_object answer = null;
		int status=1;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		try {
			status = rmi.insert_sms(content.get("projId"), content.get("userId"), content.get("description"), content.get("title"),Integer.toString(0));
		} catch (RemoteException | InterruptedException | SQLException e) {
			test_RMI();
		}
		//Call function to create a message
		int id = 1337; // Fix this with something that actually matters.
		answerHash.put("createMessage", Integer.toString(status)); // 0 == Success, 1 == Failure
		answer = new Com_object(id, operationtype.create_message, answerHash);
		return answer;
	}
	//criar questao userId, projid, title, descri, notId
	public Com_object answerNotification(Hashtable<String, String> content){
		Com_object answer = null;
		int status=1;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		try {
			status = rmi.insert_sms(content.get("projId"), content.get("userId"), content.get("description"), content.get("title"),content.get("notId"));
		} catch (RemoteException | InterruptedException | SQLException e) {
			test_RMI();
		}
		//Call function to create a message
		int id = 1337; // Fix this with something that actually matters.
		answerHash.put("answered", Integer.toString(status)); // 0 == Success, 1 == Failure
		answer = new Com_object(id, operationtype.create_message, answerHash);
		return answer;
	}
	//contar os votos 
	public Com_object seeVoteResults(Hashtable<String, String> content){
		Hashtable<String, String> answerHash = new Hashtable<String, String>(); 
		Com_object answer=  new Com_object(0, operationtype.create_message, answerHash);
		try {
			answer.menuList= rmi.democracy(content.get("projId"));
			
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		
		return answer;
		// democracy(String id)
	}
	/*test needed*/
	public Com_object getMyProjectsList(Hashtable<String, String> content){
		Com_object answer = null;
		Menu_list menuAnswer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		
		//To fill menuList with project strings and idList with their respective ID
		int id = 1337; // Fix this with something that actually matters.
		answer = new Com_object(id, operationtype.see_my_projs, answerHash);
		
		try {
			menuAnswer =  rmi.search_myprojects(content.get("userId"));
		} catch (RemoteException | InterruptedException | SQLException e) {
			test_RMI();
		}
		answer.menuList = menuAnswer;
		
		return answer;
	}
	/*check*/
	public Com_object checkProjectName(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		try {
			answerHash.put("projectTitle", Integer.toString(rmi.check_name_proj(content.get("projectTitle")))); // 0 == Success, 1 == Failure
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		//Call function to create a reply to a message
		int id = 1337; // Fix this with something that actually matters.
		
		answer = new Com_object(id, operationtype.check_proj_name, answerHash);
		return answer;
	}
	/*check*/
	public Com_object getTierInfo(Hashtable<String, String> content){
		Com_object answer = null;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		try {
			answer = rmi.see_reward(content.get("rewId"),content.get("userId"));
		} catch (RemoteException | InterruptedException | SQLException e) {
			// TODO Auto-generated catch block
			test_RMI();
		}
		return answer;
	}
	
}
