package Model;




/**
 * @author sergi
 *
 *clasa imutabila care foloseste java records pentru obiectele de tip Bill
 *
 */
public record Bill(int id, String customerName, String productName, int quantity) {

	/**
	 * getter 
	 * @return
	 */
	public int id() {
		return id;
	}

	/**
	 * getter
	 * @return
	 */
	public String customerName() {
		return customerName;
	}

	/**
	 * getter
	 * @return
	 */
	public String productName() {
		return productName;
	}

	/**
	 * getter
	 * @return
	 */
	public int quantity() {
		return quantity;
	}
	
}
