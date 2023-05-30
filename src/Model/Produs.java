package Model;





/**
 * @author sergi
 * 
 * clasa pentru definirea produsului a atributelor sale precum si gettere si settere
 *
 */
public class Produs {
	
	private int idp;
	private String name;
	private int cantitate;
	private double pret;
	
	/**
	 * constructor
	 * @param idp
	 * @param name
	 * @param cantitate
	 * @param pret
	 */
	public Produs(int idp, String name, int cantitate, double pret) {
		super();
		this.idp = idp;
		this.name = name;
		this.cantitate = cantitate;
		this.pret = pret;
	}
	/**
	 * getter
	 * @return
	 */
	public int getIdp() {
		return idp;
	}
	/**
	 * setter
	 * @param idp
	 */
	public void setIdp(int idp) {
		this.idp = idp;
	}
	/**
	 * getter
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * stter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * getter
	 * @return
	 */
	public double getPret() {
		return pret;
	}
	/**
	 * setter
	 * @param pret
	 */
	public void setPret(double pret) {
		this.pret = pret;
	}
	@Override
	/**
	 * tostring
	 */
	public String toString() {
		return "Produs [idp=" + idp + ", name=" + name + ", cantitate=" + cantitate + ", pret=" + pret + "]";
	}
	
	

}
