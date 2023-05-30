package BusinessLogic;

import java.sql.ResultSet;
import java.util.ArrayList;

import DataAccess.ComandaDAO;
import DataAccess.ProdusDAO;
import Model.Client;
import Model.Comanda;
import Model.Produs;





/**
 * @author sergi
 *clasa pentru logica comenzilor care contine metodele pentru insert,update si delete in baza de date
 */
public class ComandaBLL {
	
	/**
	 * metoda care creeaza o comanda
	 * @param c
	 * @param p
	 * @param cantitate
	 * @param idco
	 * @throws InvalidDataException
	 */
	
	public static void createComanda(Client c,Produs p,int cantitate,int idco) throws InvalidDataException {
		if(idco<0) {
			throw new InvalidDataException("invalid id");
			
		}
		else if(cantitate<0) {
			throw new InvalidDataException("invalid cantitate");
		}
		else if(cantitate>p.getCantitate()) {
			throw new InvalidDataException("nu este stock");
			
		}
		else {
			ComandaDAO.createComanda(c,p,cantitate,idco);
			ProdusBLL.updatedStock(p, cantitate);
			int cant=p.getCantitate();
			ProdusDAO.updateCantitate(p.getIdp(), cant);
		}
	}
	
	/**
	 * metoda care genereaza o lista de comenzi din tabelul comanda
	 * 
	 * @return
	 */
	
	public static ArrayList<Comanda> createList() {
		return ComandaDAO.generateList();
	}

	/**
	 * 
	 * metoda care genereaza o comanda folosind o comanda sql
	 * @param rs
	 * @return
	 */
	public static Comanda generateProdus(ResultSet rs) {
		return ComandaDAO.createObjectFromDB(rs);
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
		try {
		createComanda(c,p,6000,20);
		}catch(InvalidDataException e) {
			e.printStackTrace();
		}
	}

}
