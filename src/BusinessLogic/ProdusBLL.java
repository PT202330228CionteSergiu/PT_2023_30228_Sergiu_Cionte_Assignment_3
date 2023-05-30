package BusinessLogic;

import java.sql.ResultSet;
import java.util.ArrayList;

import DataAccess.ClientDAO;
import DataAccess.ProdusDAO;
import Model.Client;
import Model.Produs;





/**
 * @author sergi
 *clasa care contine business logic-ul pentru produs 
 */
public class ProdusBLL {

	/**
	 * metoda care insereaza un produs in baza de date
	 * @param p
	 * @throws InvalidDataException
	 */
	public static void insert(Produs p) throws InvalidDataException {
		if (p.getIdp() < 0) {
			throw new InvalidDataException("invalid id");

		}
		else if (p.getCantitate() < 0) {
			throw new InvalidDataException("cantitate invalida");

		}
		else if (p.getPret() < 0) {
			throw new InvalidDataException("pret invalid");
		}
		else{
			ProdusDAO.insert(p);
		}
	}
	
	/**
	 * megtoda care face update la stock-ul produsului
	 * @param p
	 * @param cantitateDeVandut
	 */
	public static void updatedStock(Produs p, int cantitateDeVandut) {
		p.setCantitate(p.getCantitate() - cantitateDeVandut);
		//return p.getCantitate();
	}

	/**
	 * metoda care face delete dupa nume
	 * @param name
	 */
	public static void deleteName(String name) {
		ProdusDAO.deleteName(name);
	} 
	
	/**
	 * metoda care face delete dupa id
	 * @param id
	 */
	public static void deleteID(int id) {
		ProdusDAO.deleteID(id);
	}
	
	/**
	 * metoda care da update la nume
	 * @param id
	 * @param name
	 */
	public static void updateName(int id,String name) {
		ProdusDAO.updateName(id, name);
	}
	
	/**
	 * metoda care da update la cantitate
	 * @param id
	 * @param cantitate
	 */
	public static void updateCantitate(int id,int cantitate) {
		ProdusDAO.updateCantitate(id, cantitate);
	}
	
	/**
	 * metoda care da update la pret
	 * @param id
	 * @param pret
	 */
	public static void updatePret(int id,double pret) {
		ProdusDAO.updatePret(id, pret);
	}

	/**
	 * metoda care returneaza rezultatul unei comenzi sql
	 * @return
	 */
	public static ResultSet retRes() {
		ResultSet rs = ProdusDAO.afis();
		return rs;

	}
	
	/**
	 * metoda care genereaza un arraylist de produse dupa tabelul produs
	 * @return
	 */
	public static ArrayList<Produs> createList() {
		return ProdusDAO.generateList();
	}

	/**
	 * metoda care genereaza un obiect produs dupa un produs din tabel 
	 * @param rs
	 * @return
	 */
	public static Produs generateProdus(ResultSet rs) {
		return ProdusDAO.createObjectFromDB(rs);
	}

}
