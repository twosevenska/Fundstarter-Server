package fundstarterRMI;

import globalClasses.Com_object;
import globalClasses.Menu_list;

import java.rmi.RemoteException;
import java.sql.SQLException;


public interface ServerRMI extends java.rmi.Remote{
	public int finish_projects() throws InterruptedException, SQLException, RemoteException;
	public int register_id(String id) throws RemoteException, InterruptedException, SQLException;
	public Menu_list democracy(String id) throws InterruptedException, SQLException, RemoteException;
	public int check_id(String id) throws InterruptedException, SQLException, RemoteException;
	public Com_object get_sms_info(String id) throws InterruptedException, SQLException, RemoteException;
	public Menu_list see_sms_project(String id) throws InterruptedException, SQLException, RemoteException;
	public Menu_list see_my_rewards(String userID)throws InterruptedException, SQLException, RemoteException;
	public Menu_list see_all_vote(String id) throws InterruptedException, SQLException, RemoteException;
	public Com_object see_reward(String id, String userID)throws InterruptedException, SQLException, RemoteException;
	public int get_id_proj(String title)throws InterruptedException, SQLException, RemoteException;
	public int get_name_proj(String title)throws InterruptedException, SQLException, RemoteException;
	public int check_name_proj(String title)throws InterruptedException, SQLException, RemoteException;
	public int insert_tier(String id_project, String money, String description) throws InterruptedException, SQLException, RemoteException;
	public Com_object search_project(String id)throws InterruptedException, SQLException, RemoteException;
	public int register_user(String user, String password) throws RemoteException, InterruptedException, SQLException;
	public int search_client(String username, String password) throws InterruptedException, SQLException, RemoteException;
	public void checkRMI() throws RemoteException, InterruptedException;
	public Menu_list see_active_projects() throws InterruptedException, SQLException, RemoteException;
	public Menu_list see_noactive_projects() throws InterruptedException, SQLException, RemoteException;
	public Menu_list search_myprojects(String id) throws InterruptedException, SQLException, RemoteException;
	public int search_user(String user) throws InterruptedException, SQLException, RemoteException;
	public int insert_vote_option(String id_project, String operation, String description) throws InterruptedException, SQLException, RemoteException;
	public int insert_pledge(String id_project,String id_meta,String id_user,String id_vote,String money) throws InterruptedException, SQLException, RemoteException;
	public int insert_project(String title, String id_user, String description, String date_end, String money_needed) throws InterruptedException, SQLException, RemoteException;
	public Menu_list see_all_rewards(String id) throws InterruptedException, SQLException, RemoteException;
	public Menu_list see_sms(String id) throws InterruptedException, SQLException, RemoteException;
	public int update_money(String money, String id_user) throws InterruptedException, SQLException, RemoteException;
	public int insert_sms(String id_project, String id_user, String description, String title, String id_parent)throws InterruptedException, SQLException, RemoteException;
	public int check_admin_project(String id_user, String id_project) throws InterruptedException, SQLException, RemoteException;
	public int remove_project(String id_project) throws InterruptedException, SQLException, RemoteException;
	public int remove_reward(String id_meta) throws InterruptedException, SQLException, RemoteException;
	public float see_money(String id) throws InterruptedException, SQLException, RemoteException;
}
