package fundstarterRMI;
import globalClasses.Com_object;
import globalClasses.Menu_list;
import globalClasses.Com_object.operationtype;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Scanner;



@SuppressWarnings("deprecation")
public class ServerRMIrunner extends java.rmi.server.UnicastRemoteObject
	implements ServerRMI
	{
		static String portaRMI="7000";
		static String ipaddress = "192.168.40.128";
		private static final long serialVersionUID = 1L;
		static Connection conn = null;
		static Properties connectionProps = new Properties();
		//construtor da classe
		protected ServerRMIrunner() throws RemoteException {
			super();
	}
		
		private static void loadArguments(String[] arguments){
			portaRMI = arguments[0];
			ipaddress = arguments[1];
		}
		
		private static void loadProperties(){
			Properties prop = new Properties();
			InputStream input = null;
			try {
				input = new FileInputStream("Rconfig.properties");

				// Load a properties file
				prop.load(input);

				// Get the properties, almost like a hash table
				String tempPort = prop.getProperty("serverPort");
				String tempIp = prop.getProperty("bdIp");
				if( tempPort != null && tempIp != null)
				{
					portaRMI = tempPort;
					ipaddress = tempIp;
				}
						

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
	

	public static void main(String[] args){
		if (args.length == 0){
			loadProperties();
		}else if(args.length == 2){
			loadArguments(args);
		}else{
			System.out.println("Number of arguments invalid, starting with default port value = 7000 on our external db" );
		}
		
		System.getProperties().put("java.security.policy","security.policy") ;
		System.setSecurityManager(new RMISecurityManager());
		System.setProperty("java.security.policy","file:///urs/lib/jvm/java-7-openjdk-i386/jre/lib/security/java.policy");
                
		try {
			//cenas rmi
			java.rmi.registry.LocateRegistry.createRegistry(Integer.parseInt(portaRMI)); 
			ServerRMIrunner rmifich = new ServerRMIrunner();
			Naming.rebind("rmi://localhost:"+portaRMI+"/rmi", rmifich);
			//fim de cenas rmi
			
			
			String url = "jdbc:postgresql://"+ipaddress+"/fundstarter";
			connectionProps.setProperty("user","fundstarter");
			connectionProps.setProperty("password","shutup");
			conn = DriverManager.getConnection(url, connectionProps);
			System.out.println("Connected to database "+ conn);
		}
		catch (SQLException e) {
			System.out.println("Erro no servidor sql");
			System.exit(1);
		}catch (Exception e) {
			System.out.println("Erro: porta "+ portaRMI +" em uso ");
			System.exit(1);
		}
		System.out.println("Write something and press enter to end RMI");
		Scanner sc = new Scanner(System.in);
	    sc.next();
	    System.exit(0);
	}
	
	
	public int search_client(String username, String password) throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM users WHERE username = ? AND password = ?;")) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
	        }else
				return 0;
	    } catch (SQLException e) {
	    	
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
	    	return -1;
	    }
	}	
	
	public int register_id(String id) throws RemoteException, InterruptedException, SQLException
	{
		conn.setAutoCommit(false);
		try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO ids (id) VALUES (?);")) {
			preparedStatement.setString(1, (id));
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
		}
		conn.setAutoCommit(true);
		return 0;
	}
			
	public int check_id(String id) throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM ids WHERE id = ?;")) {
			preparedStatement.setString(1, (id));
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return 0;
	        }else
				return 1;
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    	return -1;
	    }
	}
	
	public float see_money(String id) throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT money FROM users WHERE id = ?;")) {
			preparedStatement.setFloat(1, Float.parseFloat(id));
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return rs.getFloat("money");
	        }else
				return 0;
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    	return -1;
	    }
	}	
	
	public int check_name_proj(String title)throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM projects WHERE title = ?;")) {
			preparedStatement.setString(1, title);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return 1;
	        }else
				return 0;
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    	return 1;
	    }
	}
	
	public int get_id_proj(String title)throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM projects WHERE title = ?;")) {
			preparedStatement.setString(1, title);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
	        }else
				return 0;
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    	return 1;
	    }
	}
	
	public int get_name_proj(String title)throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM projects WHERE title = ?;")) {
			preparedStatement.setString(1, title);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
	        }else
				return 0;
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    	return 1;
	    }
	}
	
	public int search_user(String user)throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM users WHERE username = ?;")) {
			preparedStatement.setString(1, user);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return 0;
	        }else
				return 1;
	    
		} catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    	return 1;
	    }
	}
	
	public int register_user(String user, String password) throws RemoteException, InterruptedException, SQLException
	{
		conn.setAutoCommit(false);
		try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO users (username,password, money) VALUES (?,?, 100);")) {
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			conn.commit();
			conn.setAutoCommit(true);
			if(rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
		}
		conn.setAutoCommit(true);
		return 0;
	}
	
	public int insert_vote_option(String id_project, String operation, String description)throws InterruptedException, SQLException, RemoteException
	{
		conn.setAutoCommit(false);
		try (PreparedStatement preparedStatement = conn.prepareStatement("insert into vote_options(id_project, num_op, description) values (?, ?, ?);")) {
			preparedStatement.setInt(1, Integer.parseInt(id_project));
			preparedStatement.setString(2, operation);
			preparedStatement.setString(3, description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			conn.commit();
			conn.setAutoCommit(true);
			return rs.getInt(1);
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
		}
		conn.setAutoCommit(true);
		return -1;
	}
	
	public int insert_pledge(String id_project,String id_meta,String id_user,String id_vote,String money)throws InterruptedException, SQLException, RemoteException
	{
		conn.setAutoCommit(false);
		if (id_vote.equals("0"))
		{
			id_vote="10";
		}
		String todb = "insert into pledges(id_project, id_meta, "
				+ "id_user, id_vote, money, deleted) values (?, "
				+ "?, ?, ?, ? , 0);";
		try (PreparedStatement preparedStatement = conn.prepareStatement(todb)) {
			preparedStatement.setInt(1, Integer.parseInt(id_project));
			preparedStatement.setInt(2, Integer.parseInt(id_meta));
			preparedStatement.setInt(3, Integer.parseInt(id_user));
			preparedStatement.setInt(4, Integer.parseInt(id_vote));
			preparedStatement.setFloat(5, Float.parseFloat(money));
			preparedStatement.executeUpdate();
			
			todb = "update users set money = money - ? where id=?; ";
			try (PreparedStatement preparedStatement2 = conn.prepareStatement(todb)) {
				preparedStatement2.setFloat(1, Float.parseFloat(money));
				preparedStatement2.setInt(2, Integer.parseInt(id_user));
				preparedStatement2.executeUpdate();
				
				todb = "update projects set money_reached=money_reached+? where id = ?;";
				try (PreparedStatement preparedStatement3 = conn.prepareStatement(todb)) {	
					preparedStatement3.setFloat(1, Float.parseFloat(money));
					preparedStatement3.setInt(2, Integer.parseInt(id_project));
					preparedStatement3.executeUpdate();
					conn.commit();
					conn.setAutoCommit(true);
					return 0;
				}
			}
		} catch (SQLException e) {
			conn.rollback();
			
			System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
		}
		conn.setAutoCommit(true);
		return 1;
	}
	
	public int insert_project(String title, String id_user, String description, String date_end, String money_needed) throws InterruptedException, SQLException, RemoteException
	{
		String todb = "Insert into projects(title, id_user, description, "
				+ "date_post, date_end, money_needed, money_reached, deleted)"
				+ " values (?, ?, ?, now(), '"+date_end+"', ?, 0, 0);";
		try (PreparedStatement preparedStatement = conn.prepareStatement(todb)) {
			preparedStatement.setString(1, title);
			preparedStatement.setInt(2, Integer.parseInt(id_user));
			preparedStatement.setString(3, description);
			preparedStatement.setFloat(4, Float.parseFloat(money_needed));	
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			return 0;
		} catch (SQLException e) {
			System.out.println("Erro no sql, a terminar o RMI "+e);
			System.exit(1);
		}
		return -1;
	}
	
	public Menu_list see_active_projects() throws InterruptedException, SQLException, RemoteException
	{
		String[] projectNames = {};
		String[] projectID = {};
		ArrayList<String> templist = new ArrayList<String>();
		ArrayList<String> templistid = new ArrayList<String>();
		//select * from projects where date_end >= now() AND deleted = 0;
		
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from projects where date_end >= now() AND deleted = 0;");
			while (rs.next()) {
				templistid.add(rs.getString("id"));
				templist.add(rs.getString("title"));
	        }
			projectNames = templist.toArray(new String[templist.size()]);
			projectID = templistid.toArray(new String[templist.size()]);
			
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		return new Menu_list(projectNames, projectID);
	}
	
	public Menu_list see_noactive_projects() throws InterruptedException, SQLException, RemoteException
	{
		String[] projectNames = {};
		String[] projectID = {};
		ArrayList<String> templist = new ArrayList<String>();
		ArrayList<String> templistid = new ArrayList<String>();
		
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from projects where date_end < now();");
			while (rs.next()) {
				templistid.add(rs.getString("id"));
				templist.add(rs.getString("title"));
	        }
			projectNames = templist.toArray(new String[templist.size()]);
			projectID = templistid.toArray(new String[templist.size()]);
			
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		return new Menu_list(projectNames, projectID);
	}
	
	public Menu_list search_myprojects(String id) throws InterruptedException, SQLException, RemoteException
	{
		String[] projectNames = {};
		String[] projectID = {};
		ArrayList<String> templist = new ArrayList<String>();
		ArrayList<String> templistid = new ArrayList<String>();
		
		try (PreparedStatement preparedStatement = conn.prepareStatement("select * from projects where id_user = ?;")) {
			preparedStatement.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				templistid.add(rs.getString("id"));
				templist.add(rs.getString("title"));
	        }
			projectNames = templist.toArray(new String[templist.size()]);
			projectID = templistid.toArray(new String[templist.size()]);
			
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
	    }
		return new Menu_list(projectNames, projectID);
	}
	
	public Com_object search_project(String id)throws InterruptedException, SQLException, RemoteException
	{
		Com_object answer;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM projects WHERE id = ?")) {
			preparedStatement.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				answerHash.put("title",rs.getString("title"));
				answerHash.put("projId",rs.getString("id"));
				answerHash.put("progress", Integer.toString(Math.round(rs.getInt("money_reached")*100)/rs.getInt("money_needed")));
				answerHash.put("description",rs.getString("description"));
				answerHash.put("endDate",rs.getString("date_end"));
				answerHash.put("active", rs.getString("deleted"));
			}
			else
				return null;
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		answer = new Com_object(0, operationtype.see_proj, answerHash);
		return answer;
	}
	
	public Menu_list see_all_rewards(String id) throws InterruptedException, SQLException, RemoteException
	{
		String[] rewards = {};
		String[] rewardsID = {};
		ArrayList<String> templist = new ArrayList<String>();
		ArrayList<String> templistid = new ArrayList<String>();
		try (PreparedStatement preparedStatement = conn.prepareStatement("select * from rewards where id_project = ? AND disabled = 0;")) {
			preparedStatement.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				templistid.add(rs.getString("id"));
				templist.add(rs.getString("money"));
	        }
			rewards = templist.toArray(new String[templist.size()]);
			rewardsID = templistid.toArray(new String[templist.size()]);
			
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		return new Menu_list(rewards, rewardsID);
	}
	
	public Menu_list see_all_vote(String id) throws InterruptedException, SQLException, RemoteException
	{
		String[] rewards = {};
		String[] rewardsID = {};
		ArrayList<String> templist = new ArrayList<String>();
		ArrayList<String> templistid = new ArrayList<String>();
		
		try (PreparedStatement preparedStatement = conn.prepareStatement("select * from vote_options where id_project = ? AND id!=10;")) {
			preparedStatement.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				templistid.add(rs.getString("id"));
				templist.add(rs.getString("description"));
	        }
			rewards = templist.toArray(new String[templist.size()]);
			rewardsID = templistid.toArray(new String[templist.size()]);
			
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		return new Menu_list(rewards, rewardsID);
	}
	
	public Menu_list see_sms(String id)throws InterruptedException, SQLException, RemoteException
	{
		String[] projectNames = {};
		String[] projectID = {};
		ArrayList<String> templist = new ArrayList<String>();
		ArrayList<String> templistid = new ArrayList<String>();
		
		try (PreparedStatement preparedStatement = conn.prepareStatement("select * from sms where  = ?;")) {
			preparedStatement.setInt(1, Integer.parseInt(id));
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				templistid.add(rs.getString("id"));
				templist.add(rs.getString("description"));
	        }
			projectNames = templist.toArray(new String[templist.size()]);
			projectID = templistid.toArray(new String[templist.size()]);
			
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		return new Menu_list(projectNames, projectID);
	}
	
	public int update_money(String money, String id_user) throws InterruptedException, SQLException, RemoteException
	{
		String todo = "update users set money = money + ? where id=?;";
		try (PreparedStatement preparedStatement = conn.prepareStatement(todo)) {
			preparedStatement.setInt(2, Integer.parseInt(id_user));
			preparedStatement.setFloat(1, Float.parseFloat(money));
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			return 0;
		} catch (SQLException e) {
			System.out.println("Erro no sql, a terminar o RMI"+e); System.exit(1);
		}
		return 1;
	}
	
	public int insert_sms(String id_project, String id_user, String description, String title, String id_parent)throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("insert into sms(id_project, id_user, description, id_parent, title) values (?, ?, ?, ?, ?);")) {
			preparedStatement.setInt(1, Integer.parseInt(id_project));
			preparedStatement.setInt(2, Integer.parseInt(id_user));
			preparedStatement.setString(3, description);
			preparedStatement.setInt(4, Integer.parseInt(id_parent));
			preparedStatement.setString(5, title);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			return 0;
		} catch (SQLException e) {
			System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
		}
		return 1;
	}
	
	public int check_admin_project(String id_user, String id_project)throws InterruptedException, SQLException, RemoteException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement("select * from projects where id_user = ? AND id = ?")) {
			preparedStatement.setString(1, id_user);
			preparedStatement.setString(2, id_project);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return 0;
	        }else
				return 1;
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
	    	return -1;
	    }                     
	}
		
	public int remove_project(String id_project) throws InterruptedException, SQLException, RemoteException
	{
		int i;
		ArrayList <String> templist = new ArrayList<String>();
		ArrayList <String> templistid = new ArrayList<String>();
		try (Statement stmt = conn.createStatement()) {
			conn.setAutoCommit(false);
			ResultSet rs = stmt.executeQuery("select * from pledges where id_project = "+id_project+";");
			while (rs.next()) {
				templistid.add(rs.getString("id"));
				templist.add(rs.getString("money"));
	        }
			
			for (i=0; i<templist.size(); i++)
			{
				String todb = "update users set money = money - "+templist.get(i)+" where id="+templistid.get(i)+";";
				stmt.executeUpdate(todb);
			}
			stmt.executeUpdate("update pledges set deleted = 1 where id_project = "+id_project+";");
			stmt.executeUpdate("update rewards set disabled = 1 where id_project = "+id_project+";");
			stmt.executeUpdate("update projects set deleted = 1 where id = "+id_project+";");
			
			conn.commit();
			conn.setAutoCommit(true);
			return 0;
		} catch (SQLException e) {
			System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
		}
		conn.setAutoCommit(true);
		return 1;
	}
	
	/*fazer a partir daqui e verificar os rollbacks*/
	
	public int finish_projects() throws InterruptedException, SQLException, RemoteException
	{
		int i,j;
		ArrayList <String> templist = new ArrayList<String>();
		ArrayList <String> templistid = new ArrayList<String>();
		ArrayList <String> templistid2 = new ArrayList<String>();
		
		String projectString;
		try (Statement stmt = conn.createStatement()) {
			conn.setAutoCommit(false);
			projectString = ("select * from projects where date_end < now() AND deleted = 0"); 
			ResultSet rs =  stmt.executeQuery(projectString);
			while(rs.next())
				templistid2.add(rs.getString("id"));
			
			for (i=0; i< templistid2.size(); i++)
			{
				rs = stmt.executeQuery("select * from pledges where id_project = "+templistid2.get(i)+";");
				while (rs.next()) {
					templistid.add(rs.getString("id"));
					templist.add(rs.getString("money"));
		        }
				
				for (j=0; j<templist.size(); j++)
				{
					String todb = "update users set money = money - "+templist.get(j)+" where id="+templistid.get(j)+";";
					stmt.executeUpdate(todb);
				}
				stmt.executeUpdate("update pledges set deleted = 1 where id_project = "+templistid2.get(i)+";");
				stmt.executeUpdate("update rewards set disabled = 1 where id_project = "+templistid2.get(i)+";");
				stmt.executeUpdate("update projects set deleted = 1 where id = "+templistid2.get(i)+";");
			}
			conn.commit();
			conn.setAutoCommit(true);
			return 0;
		} catch (SQLException e) {
			System.out.println("Erro no sql, a terminar o RMI"+e); System.exit(1);
		}
		return 1;
	}
	
	public int remove_reward(String id_meta) throws InterruptedException, SQLException, RemoteException
	{
		int i;
		ArrayList <String> templist = new ArrayList<String>();
		ArrayList <String> templistid = new ArrayList<String>();
		try (Statement stmt = conn.createStatement()) {
			conn.setAutoCommit(false);
			ResultSet rs = stmt.executeQuery("select * from pledges where id_meta = "+id_meta+";");
			while (rs.next()) {
				templistid.add(rs.getString("id_user"));
				templist.add(rs.getString("money"));
	        }
			
			for (i=0; i<templist.size(); i++)
			{
				String todb = "update users set money = money + "+templist.get(i)+" where id="+templistid.get(i)+";";
				stmt.executeUpdate(todb);
			}
			stmt.executeUpdate("update pledges set deleted = 1 where id_meta = "+id_meta+";");
			
			stmt.executeUpdate("update rewards set disabled = 1 where id = "+id_meta+";");
			
			conn.commit();
			conn.setAutoCommit(true);
			return 0;
		} catch (SQLException e) {
			System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
		}
		return 1;
	}
	
	public int insert_tier(String id_project, String money, String description) throws InterruptedException, SQLException, RemoteException
	{
		try (Statement stmt = conn.createStatement()) {
			
			String todb = "Insert into rewards(id_project, money, description) values ("+id_project+", "+money+", '"+description+"')";
			stmt.executeUpdate(todb,  Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
		}
		return 0;
	}
	
	public Menu_list see_my_rewards(String userID)throws InterruptedException, SQLException, RemoteException
	{
		int i;
		ArrayList <String> templistidrewards = new ArrayList<String>();
		ArrayList <String> templistidproject = new ArrayList<String>();
		
		ArrayList <String> templistproject = new ArrayList<String>();
		ArrayList <String> templistrewards = new ArrayList<String>();
		
		try (Statement stmt = conn.createStatement()) {
			conn.setAutoCommit(false);
			ResultSet rs = stmt.executeQuery("select*  from pledges where id_user = "+userID+";");
			while (rs.next()) {
				templistidrewards.add(rs.getString("id_meta"));
				templistidproject.add(rs.getString("id_project"));
	        }
			for(i=0; i<templistidrewards.size(); i++)
			{
				rs = stmt.executeQuery("select * from rewards where id = "+templistidrewards.get(i)+";");
				while (rs.next()) {
					templistrewards.add(rs.getString("description"));
		        }
			}
			for(i=0; i<templistidproject.size(); i++)
			{
				rs = stmt.executeQuery("select * from projects where id = "+templistidproject.get(i)+";");
				while (rs.next()) {
					templistproject.add(rs.getString("title"));
		        }
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
		}
	    String[] comboList = myRewardsPrettier(templistrewards.toArray(new String[templistrewards.size()]),templistproject.toArray(new String[templistproject.size()]));
		return new Menu_list(comboList, null);
	}
	
	private String[] myRewardsPrettier(String[] descriptionList, String[] projTitle){
		String[] result = new String[descriptionList.length];
		int i=0;
		
		for(i=0; i<descriptionList.length;i++){
			result[i] = "Project: ".concat(projTitle[i].concat("\nReward: ".concat(descriptionList[i])));
		}
		return result;
	}
	
	public Com_object see_reward(String id, String userID)throws InterruptedException, SQLException, RemoteException
	{   
		Com_object answer;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		String idreward;
		float money=0;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM rewards WHERE id = '"+id+"' AND disabled = 0;");
			if (rs.next()) {
				answerHash.put("active",rs.getString("disabled"));
				answerHash.put("ammount",rs.getString("money"));
				answerHash.put("description",rs.getString("description"));
				idreward = rs.getString("id");
				
				ResultSet rs2 = stmt.executeQuery("SELECT * FROM pledges WHERE id_user = '"+userID+"' AND id_meta = '"+id+"';");

				while(rs2.next())
				{
					money = money+rs2.getFloat("money");
				}
				answerHash.put("pledgeAmmount",money+"");
			}
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		answer = new Com_object(0, operationtype.see_proj, answerHash);
		return answer;
	}
	
	public Menu_list see_sms_project(String id) throws InterruptedException, SQLException, RemoteException
	{
		String[] rewards = {};
		String[] rewardsID = {};
		ArrayList<String> templist = new ArrayList<String>();
		ArrayList<String> templistid = new ArrayList<String>();
		
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select * from sms where id_project = "+id+" AND title!='null';");
			while (rs.next()) {
				templistid.add(rs.getString("id"));
				templist.add(rs.getString("title"));
	        }
			rewards = templist.toArray(new String[templist.size()]);
			rewardsID = templistid.toArray(new String[templist.size()]);
			
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		return new Menu_list(rewards, rewardsID);
	}
	
	public Com_object get_sms_info(String id) throws InterruptedException, SQLException, RemoteException
	{
		Com_object answer;
		Hashtable<String, String> answerHash = new Hashtable<String, String>();
		String idparent; 
		String iduser;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM sms WHERE id = "+id+";");
			if (rs.next()) {
				answerHash.put("title",rs.getString("title"));
				answerHash.put("description",rs.getString("description"));
				iduser = rs.getString("id_user");
				answerHash.put("user",rs.getString("title"));
				idparent = rs.getString("id");
				rs = stmt.executeQuery("SELECT * FROM sms WHERE  id_parent = "+idparent+";");
				if(rs.next())
				{
					answerHash.put("notAnswer",rs.getString("description"));
					answerHash.put("answered", "0"); // 0 == Answered, 1 == Unanswered
				}
				else
				{
					answerHash.put("answered", "1"); // 0 == Answered, 1 == Unanswered
				}
				rs = stmt.executeQuery("SELECT * FROM users WHERE  id = "+iduser+";");
				rs.next();
				answerHash.put("user",rs.getString("username"));
			}
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
			
	    }
		answer = new Com_object(0, operationtype.get_notification, answerHash);
		return answer;

	}

	public Menu_list democracy(String id) throws InterruptedException, SQLException, RemoteException
	{
		int i;
		ArrayList<String> ids = new ArrayList<String>();
		ArrayList<String> descriptions = new ArrayList<String>();
		ArrayList <String> counters = new ArrayList<String>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM vote_options where id_project = "+id+" AND id!=10;");
			while  (rs.next()) {
				ids.add(rs.getString("id"));
				descriptions.add(rs.getString("description"));
	        }
			for(i=0; i<ids.size(); i++)
			{
				rs = stmt.executeQuery("SELECT count(*) FROM pledges where id_vote = "+ids.get(i)+";");
				rs.next();
				counters.add(rs.getString("count"));
			}
	    } catch (SQLException e) {
	    	System.out.println("Erro no sql, a terminar o RMI"); System.exit(1);
	    }
		return new Menu_list(descriptions.toArray(new String[descriptions.size()]), counters.toArray(new String[counters.size()]));
	
	}
		//SELECT count(*) FROM pledges where id_vote = x;
	
	public void checkRMI() throws InterruptedException, RemoteException {}
	
}
