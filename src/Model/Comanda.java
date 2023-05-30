package Model;





/**
 * @author sergi
 * 
 * 
 * clasa pentru definirea comenzii a atributelor sale precum si gettere si settere
 *
 */
public class Comanda {
	
	private int idco;
	private String numeClient;
	private String numeProdus;
	private int cantitate;
	/**
	 * constructor
	 * @param idco
	 * @param numeClient
	 * @param numeProdus
	 * @param cantitate
	 */
	public Comanda(int idco, String numeClient, String numeProdus, int cantitate) {
		super();
		this.idco = idco;
		this.numeClient = numeClient;
		this.numeProdus = numeProdus;
		this.cantitate = cantitate;
	}
	/**
	 * getter
	 * @return
	 */
	public int getIdco() {
		return idco;
	}
	/**
	 * setter
	 * @param idco
	 */
	public void setIdco(int idco) {
		this.idco = idco;
	}
	/**
	 * getter
	 * @return
	 */
	public String getNumeClient() {
		return numeClient;
	}
	/**
	 * setter
	 * @param numeClient
	 */
	public void setNumeClient(String numeClient) {
		this.numeClient = numeClient;
	}
	/**
	 * getter
	 * @return
	 */
	public String getNumeProdus() {
		return numeProdus;
	}
	/**
	 * setter
	 * @param numeProdus
	 */
	public void setNumeProdus(String numeProdus) {
		this.numeProdus = numeProdus;
	}
	/**
	 * getter
	 * @return
	 */
	public int getCantitate() {
		return cantitate;
	}
	/**
	 * setter
	 * @param cantitate
	 */
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	@Override
	/**
	 * tostring
	 */
	public String toString() {
		return "Comanda [idco=" + idco + ", numeClient=" + numeClient + ", numeProdus=" + numeProdus + ", cantitate="
				+ cantitate + "]";
	}
	
	

}
