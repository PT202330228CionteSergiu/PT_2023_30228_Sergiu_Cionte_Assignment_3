package BusinessLogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import Connection.ConnectionFactory;
import DataAccess.ClientDAO;
import DataAccess.GenericDAO;
import Model.Client;




/**
 * @author sergi
 * clasa pentru business logic care contine metodele pentru efectuarea operatiilor in baza de date
 *
 */
public class ClientBLL  {
	

	 Connection conn = ConnectionFactory.getConnection();
	 GenericDAO<Client> clientDAOO = new GenericDAO<>(conn);

	 
	 /**
	  * 
	  * 
	  * metoda pentru insert
	  * @param c
	  * @param dao
	  * @throws InvalidDataException
	  */
	 public static void insert(Client c, GenericDAO<Client> dao) throws InvalidDataException {
	     if (c.getIdc() < 0) {
	         throw new InvalidDataException("Invalid id");
	     } else if (c.getAge() < 0) {
	         throw new InvalidDataException("Invalid age");
	     } else {
	         try {
	             dao.createObject(c);
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }
	 }

	 

		
/**
 * 
 * metoda pentru delete dupa id
 * @param id
 */
	public static void deleteID(int id) {
		ClientDAO.deleteID(id);
	}
	/**
	 * 
	 * metoda pentru delete dupa nume
	 * @param name
	 */
	public static void deleteName(String name) {
		ClientDAO.deleteName(name);
	}
	/**
	 * 
	 * metoda pentru update la nume
	 * @param id
	 * @param name
	 */
	public static void updateName(int id,String name) {
		ClientDAO.updateName(id, name);
	}
	
	/**
	 * metoda pentru update la adresa
	 * @param id
	 * @param address
	 */
	public static void updateAddress(int id,String address) {
		ClientDAO.updateAdress(id, address);
	}
	
	
	/**
	 * 
	 * metoda pentru update la email
	 * @param id
	 * @param email
	 */
	public static void updateEmail(int id,String email) {
		ClientDAO.updateEmail(id, email);
	}

	/**
	 * metoda pentru update la age
	 * @param id
	 * @param age
	 */
	public static void updateAge(int id, int age) {
		ClientDAO.updateAge(id, age);
	}
	// ResultSet res=ClientDAO.afis();

	
	/**
	 * metoda care returneaza rezultatul unei comenzi sql
	 * @return
	 */
	public static ResultSet retRes() {
		ResultSet rs = ClientDAO.afis();
		return rs;

	}

	
	/**
	 * metoda care genereaza un arraylist de clienti din tabelul client 
	 * @return
	 */
	public static ArrayList<Client> createList() {
		return ClientDAO.generateList();
	}

	
	/**
	 * metoda care genereaza un obiect client din tabelul client
	 * @param rs
	 * @return
	 */
	public  static Client generateClient(ResultSet rs) {
		return ClientDAO.createObjectFromDB(rs);
	}

}
