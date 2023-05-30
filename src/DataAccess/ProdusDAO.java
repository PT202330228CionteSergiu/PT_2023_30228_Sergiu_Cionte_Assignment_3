package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionFactory;
import Model.Client;
import Model.Produs;






/**
 * @author sergi
 * 
 * clasa care contine metodele pentru insert update si delete a unui produs in baza de date
 *
 */
public class ProdusDAO {
	
	private static final String insertStr= "INSERT INTO produs (idp, name, cantitate, pret)" + " VALUES (?, ?, ?, ?)";
	private static final String deleteStrName = "DELETE FROM produs where name = ?";
	private static final String deleteStrID = "DELETE FROM produs where idp = ?";
	private static final String updateStrName="UPDATE produs SET name = ? WHERE idp = ?";
	private static final String updateStrCantitate="UPDATE produs SET cantitate = ? WHERE idp = ?";
	private static final String updateStrPret="UPDATE produs SET pret = ? WHERE idp = ?";
	private static final String afisString="SELECT * from produs";
	
	/**
	 * inserez un produs
	 * @param p
	 */
	public static void insert(Produs p) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(insertStr);
			stat.setInt(1, p.getIdp());
			stat.setString(2, p.getName());
			stat.setInt(3, p.getCantitate());
			stat.setDouble(4, p.getPret());
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
	 * delete dupa nume
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
	 * delete dupa id
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
	 * update la name
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
	 * update la cantitate
	 * @param id
	 * @param cantitate
	 */
	public static void updateCantitate(int id,int cantitate) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(updateStrCantitate);
			stat.setInt(2, id);
			stat.setInt(1, cantitate);
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
	 * update la pret
	 * @param id
	 * @param pret
	 */
	public static void updatePret(int id,double pret) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(updateStrPret);
			stat.setInt(2, id);
			stat.setDouble(1, pret);
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
	 * returneaza rezultatul unei comande sql
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
	
	/**
	 * creeaza un obiect produs din un produs din baza de date
	 * @param res
	 * @return
	 */
	public static Produs createObjectFromDB(ResultSet res) {
	    try {
	        int idp = res.getInt("idp");
	        String name = res.getString("name");
	        int cantitate = res.getInt("cantitate");
	        
	        double pret = res.getDouble("pret");
	        return new Produs(idp, name, cantitate, pret);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; // Return null or throw an exception based on your error handling strategy
	}
	
	/**
	 * genereaza un arraylist de produse
	 * @return
	 */
	public static ArrayList<Produs> generateList() {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<Produs> produse = new ArrayList<Produs>();
		try {
			stat = conn.prepareStatement(afisString);
			rs = stat.executeQuery();
			while (rs.next()) {
				Produs p = createObjectFromDB(rs);
				produse.add(p);
			}
			return produse;
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
		//Produs p=new Produs(4,"papuci",200,40);
		// insert(p);
		deleteName("papuci");
		deleteID(10);
		updateName(1,"adidasi");
		updateCantitate(1,100);
		updatePret(1,35);

	}

}
