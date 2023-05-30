package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectionFactory;
import Model.Client;
import Model.Comanda;
import Model.Produs;




/**
 * @author sergi
 * 
 * clasa care contine metodele pentru insert update si delete pentru comanda in baza de date
 *
 */
public class ComandaDAO {

	private static final String insertStr = "INSERT INTO comanda (idco, numeClient, numeProdus, cantitate)"
			+ " VALUES (?, ?, ?, ?)";
	private static final String afisString="SELECT * from comanda";

	/**
	 * creeaza o comanda
	 * @param c
	 * @param p
	 * @param cantitate
	 * @param idco
	 */
	public static void createComanda(Client c, Produs p, int cantitate, int idco) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		// ResultSet rs=null;
		try {
			stat = conn.prepareStatement(insertStr);
			stat.setInt(1, idco);
			stat.setString(2, c.getName());
			stat.setString(3, p.getName());
			stat.setInt(4, cantitate);
			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ConnectionFactory.closeResultSet(rs);
		ConnectionFactory.closeStatement(stat);
		ConnectionFactory.closeConnection(conn);

	}
	
	/**
	 * creeaza un obiect comanda din o comanda din baza de date
	 * @param res
	 * @return
	 */
	public static Comanda createObjectFromDB(ResultSet res) {
	    try {
	        int idco = res.getInt("idco");
	        String nameClient = res.getString("numeClient");
	        String nameProdus=res.getString("numeProdus");
	        int cantitate = res.getInt("cantitate");
	        
	        //double pret = res.getDouble("pret");
	        return new Comanda(idco, nameClient, nameProdus, cantitate);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; // Return null or throw an exception based on your error handling strategy
	}
	
	/**
	 * genereaza un arraylist de comenzi 
	 * @return
	 */
	public static ArrayList<Comanda> generateList() {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<Comanda> comenzi = new ArrayList<Comanda>();
		try {
			stat = conn.prepareStatement(afisString);
			rs = stat.executeQuery();
			while (rs.next()) {
				Comanda co = createObjectFromDB(rs);
				comenzi.add(co);
			}
			return comenzi;
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
		Client c=new Client(2,"Ion","gara","ion@utcluj.ro",28);
		Produs p=new Produs(2,"slapi",500,14);
		createComanda(c,p,5,1);
	}
}
