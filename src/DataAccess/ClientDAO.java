package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;

import Model.Client;





/**
 * @author sergi
 * 
 * clasa care contine metodele pentru operatiile clientului de insert update si delete in baza de date
 *
 */
public class ClientDAO {
	
	private static final String insertStr= "INSERT INTO client (idc, name, address, email, age)" + " VALUES (?, ?, ?, ?, ?)";
	private static final String deleteStrName = "DELETE FROM client where name = ?";
	private static final String deleteStrID = "DELETE FROM client where idc = ?";
	private static final String updateStrName="UPDATE client SET name = ? WHERE idc = ?";
	private static final String updateStrAdress="UPDATE client SET address = ? WHERE idc = ?";
	private static final String updateStrEmail="UPDATE client SET email = ? WHERE idc = ?";
	private static final String updateStrAge="UPDATE client SET age = ? WHERE idc = ?";
	private static final String afisString="SELECT * from client";
	
	
	/**
	 * insereaza in baz de date
	 * @param c
	 */
	public static void insert(Client c) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(insertStr);
			stat.setInt(1, c.getIdc());
			stat.setString(2, c.getName());
			stat.setString(3, c.getAddress());
			stat.setString(4, c.getEmail());
			stat.setInt(5, c.getAge());
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ConnectionFactory.closeResultSet(rs);
		finally {
		ConnectionFactory.closeStatement(stat);
		ConnectionFactory.closeConnection(conn);
		}

	}

	/**
	 * face delete dupa nume
	 * @param name
	 */
	public static void deleteName(String name) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(deleteStrName);
			stat.setString(1, name);
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ConnectionFactory.closeResultSet(rs);
		finally {
		ConnectionFactory.closeStatement(stat);
		ConnectionFactory.closeConnection(conn);
		}
	}
	
	/**
	 * face delete dupa id
	 * @param id
	 */
	public static void deleteID(int id) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(deleteStrID);
			stat.setInt(1, id);
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ConnectionFactory.closeResultSet(rs);
		finally {
		ConnectionFactory.closeStatement(stat);
		ConnectionFactory.closeConnection(conn);
		}
	}
	
	/**
	 * 
	 * face update la name
	 * @param id
	 * @param name
	 */
	public static void updateName(int id,String name) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(updateStrName);
			stat.setInt(2, id);
			stat.setString(1, name);
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ConnectionFactory.closeResultSet(rs);
		finally {
		ConnectionFactory.closeStatement(stat);
		ConnectionFactory.closeConnection(conn);
		}
	}
	
	/**
	 * face update la address
	 * @param id
	 * @param adress
	 */
	public static void updateAdress(int id,String adress) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(updateStrAdress);
			stat.setInt(2, id);
			stat.setString(1, adress);
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ConnectionFactory.closeResultSet(rs);
		finally {
		ConnectionFactory.closeStatement(stat);
		ConnectionFactory.closeConnection(conn);
		}
	}
	
	/**
	 * face update la email
	 * @param id
	 * @param email
	 */
	public static void updateEmail(int id,String email) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(updateStrEmail);
			stat.setInt(2, id);
			stat.setString(1, email);
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ConnectionFactory.closeResultSet(rs);
		finally {
		ConnectionFactory.closeStatement(stat);
		ConnectionFactory.closeConnection(conn);
		}
	}
	
	/**
	 * face update la age
	 * @param id
	 * @param age
	 */
	public static void updateAge(int id,int age) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(updateStrAge);
			stat.setInt(2, id);
			stat.setInt(1, age);
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ConnectionFactory.closeResultSet(rs);
		finally {
		ConnectionFactory.closeStatement(stat);
		ConnectionFactory.closeConnection(conn);
		}
	}
	
	/**
	 * returneaza rezultatul unei comenzi sql
	 * @return
	 */
	public static ResultSet afis() {
	    Connection conn = ConnectionFactory.getConnection();
	    PreparedStatement stat = null;
	    ResultSet rs = null;
	    try {
	        stat = conn.prepareStatement(afisString);
	        rs = stat.executeQuery();
	        return rs;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionFactory.closeResultSet(rs);
	        ConnectionFactory.closeStatement(stat);
	        ConnectionFactory.closeConnection(conn);
	    }
	    
	    return null; // Default return value if an exception occurs
	}

		//return rs;
		//ConnectionFactory.closeResultSet(rs);
		
	/**
	 * creeaza un obiect client din un client din baza de date
	 * @param res
	 * @return
	 */
	public static Client createObjectFromDB(ResultSet res) {
	    try {
	        int idc = res.getInt("idc");
	        String name = res.getString("name");
	        String address = res.getString("address");
	        String email = res.getString("email");
	        int age = res.getInt("age");
	        return new Client(idc, name, address, email, age);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; // Return null or throw an exception based on your error handling strategy
	}
	
	/**
	 * genereaza un arraylist de clienti
	 * @return
	 */
	public static ArrayList<Client> generateList() {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<Client> clienti = new ArrayList<Client>();
		try {
			stat = conn.prepareStatement(afisString);
			rs = stat.executeQuery();
			while (rs.next()) {
				Client c = createObjectFromDB(rs);
				clienti.add(c);
			}
			return clienti;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeResultSet(rs);
			ConnectionFactory.closeStatement(stat);
			ConnectionFactory.closeConnection(conn);
		}
		return null;
	}


	/**
	 * testam in consola
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //Client c=new Client(1,"Pop","gara","pop@utcluj.ro",21);
		// insert(c);
		//deleteName("Ion");
		//deleteID(2);
		updateName(1,"Gica");
		updateAdress(1,"planete");
		updateEmail(1,"planete@utcluj.ro");
		updateAge(1,38);
		//System.out.println(afis());
		//System.out.println(generateList());
	}

}
