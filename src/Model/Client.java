package Model;





/**
 * @author sergi
 *
 *clasa pentru definirea clientului si a atributelor sale precum si gettere si settere
 *
 */
public class Client {
	private int idc;
	private String name;
	private String address;
	private String email;
	private int age;
	/**
	 * constructor pentru client
	 * @param idc
	 * @param name
	 * @param address
	 * @param email
	 * @param age
	 */
	public Client(int idc, String name, String address, String email, int age) {
		super();
		this.idc = idc;
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}
	/**
	 * getter
	 * @return
	 */
	public int getIdc() {
		return idc;
	}
	/**
	 * setter
	 * @param idc
	 */
	public void setIdc(int idc) {
		this.idc = idc;
	}
	/**
	 * getter
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * setter
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * getter
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * setter
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * getter
	 * @return
	 */
	public int getAge() {
		return age;
	}
	/**
	 * setter
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	/**
	 * tostring
	 */
	public String toString() {
		return "Client [idc=" + idc + ", name=" + name + ", address=" + address + ", email=" + email + ", age=" + age
				+ "]";
	}
	
	

}
