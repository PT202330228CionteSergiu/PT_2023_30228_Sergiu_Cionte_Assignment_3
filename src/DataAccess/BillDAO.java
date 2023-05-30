package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Bill;
import Model.Comanda;





/**
 * @author sergi
 *clasa care genereaza un obiect de tip Bill dintr-o comanda si o lista de obiecte bill pe care o insereaza in baza de date
 */
public class BillDAO {
	
	/**
	 * genereaza un obiect de tip bill dintr o comanda
	 * @param comanda
	 * @return
	 */
    private static Bill createBillFromComanda(Comanda comanda) {
        int id = comanda.getIdco();
        String customerName = comanda.getNumeClient();
        String productName = comanda.getNumeProdus();
        int quantity = comanda.getCantitate();
        
        return new Bill(id, customerName, productName, quantity);
    }
	
    /**
     * genereaza un arraylist de obiecte bill
     * @return
     */
	  public static ArrayList<Bill> generateBills() {
	        ArrayList<Bill> bills = new ArrayList<>();
	        
	        try (Connection connection = ConnectionFactory.getConnection()) {
	            String query = "SELECT * FROM Comanda";
	            try (PreparedStatement statement = connection.prepareStatement(query);
	                 ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    int idco = resultSet.getInt("idco");
	                    String numeClient = resultSet.getString("numeClient");
	                    String numeProdus = resultSet.getString("numeProdus");
	                    int cantitate = resultSet.getInt("cantitate");
	                    
	                    Comanda comanda = new Comanda(idco, numeClient, numeProdus, cantitate);
	                    Bill bill = createBillFromComanda(comanda);
	                    
	                    bills.add(bill);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return bills;
	    }
	  
	  /**
	   * insereaza obiectele de tip bill in log table din baza de date
	   * @param bills
	   */
	   public static void insertBills(List<Bill> bills) {
	        try (Connection connection = ConnectionFactory.getConnection()) {
	            String query = "INSERT INTO log (idco, numeClient, numeProdus, cantitate) VALUES (?, ?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(query)) {
	                for (Bill bill : bills) {
	                    statement.setInt(1, bill.id());
	                    statement.setString(2, bill.customerName());
	                    statement.setString(3, bill.productName());
	                    statement.setInt(4, bill.quantity());
	                    statement.executeUpdate();
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
