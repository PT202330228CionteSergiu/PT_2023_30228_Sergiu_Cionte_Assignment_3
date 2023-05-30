package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.logging.Logger;



/**
 * @author sergi
 *clasa pentru stabilirea conexiunii
 */
public class ConnectionFactory {
	//private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/schooldb";
	private static final String USER="root";
	//private static final String PASS="root";
	
	private static ConnectionFactory singleInstance=new ConnectionFactory();

	/**
	 * contructorul conexiunii
	 */
	public ConnectionFactory() {
		try {
			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * metoda care creeaza conexiunea cu baza de date
	 * @return
	 */
	public Connection createConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DBURL, USER,"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	/**
	 * metoda care returneaza conexiunea cu baza de date
	 * @return
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();

	}

	/**
	 * inchide conexiunea cu baza de date
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * inchide statementul
	 * @param statement
	 */
	public static void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * inchide resultset
	 * @param resultSet
	 */
	public static void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * testam in consola
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Connection con=DriverManager.getConnection(DBURL,USER, "");
		Statement st=con.createStatement();
		String query="select * from client";
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
			
		}
		con.close();
		
	}
}
