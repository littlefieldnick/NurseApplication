package edu.usm.cos420.DB;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ArrayList;

import edu.usm.cos420.model.Nurse;

/**
 * A Table Gateway class to access the Nurses table Pattern :
 * https://www.martinfowler.com/eaaCatalog/tableDataGateway.html
 * 
 * config.properties needs to point the right DB & have un/pw
 */
public class NurseTableGateway {
	private static Properties properties = new Properties();

	private static void loadProps() {
		InputStream in = NurseTableGateway.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			System.out.println("No config.properties were found.");
			e.printStackTrace();
		}
	}

	// make a connection to the Nurse table using the properties in
	// config.properties

	private static Connection createDBConnection() throws ClassNotFoundException, SQLException {
		loadProps();
		String strDBUser = properties.getProperty("jdbc.username"); // database
																	// login
																	// username
		String strDBPassword = properties.getProperty("jdbc.password");
		String strDBName = properties.getProperty("db.name");
		String portNumber = properties.getProperty("port");
		Connection con;

		System.out.println("Trying connection ");

		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:"+ portNumber + "/" + strDBName, strDBUser,
				strDBPassword);

		if (!con.isClosed())
			System.out.println("Successfully connected to Postgres server using TCP/IP...");

		return con;
	}

	/**
	 * Adds a nurse to the DB
	 * 
	 * @param n
	 *            (Nurse)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void addToNursesTable(Nurse n) throws ClassNotFoundException, SQLException {
		Statement st = null;
		Connection con = createDBConnection();
		if (con == null)
			throw new SQLException();

		st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		  ResultSet uprs = st.executeQuery("SELECT * FROM nurses");
		  
		  uprs.moveToInsertRow();
		  
		  uprs.updateInt("userId", n.getId());
		  uprs.updateString("firstName",n.getFirstName());
		  uprs.updateString("lastName", n.getLastName());
		  uprs.updateString("countryCode", n.getCountryCode());
		  
		  uprs.insertRow();

		/*
		 * An alternative way of updating the table ... have to know
		 * the column number and will need to change with changes in table
		 * 
		 * st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		 * ResultSet.CONCUR_UPDATABLE);
		 * 
		 * PreparedStatement ps =
		 * con.prepareStatement("insert into nurses values(?,?,?,?)");
		 * 
		 * ps.setInt(1, n.getId()); 
		 * ps.setString(2, n.getFirstName());
		 * ps.setString(3, n.getLastName()); 
		 * ps.setString(4, n.getCountryCode());
		 * 
		 * int i = ps.executeUpdate();
		 */

		 

		if (con != null)
			con.close();

	}

	/**
	 * Pulls all the nurses from the DB and returns an ArrayList of them
	 * 
	 * @return ArrayList<Nurse>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Nurse> getAllNurses() throws ClassNotFoundException, SQLException {
		Connection con = createDBConnection();
		if (con == null)
			throw new SQLException();

		Statement st = null;
		ResultSet rs = null;
		ArrayList<Nurse> nurseList = new ArrayList<Nurse>();

		st = con.createStatement();

		rs = st.executeQuery("SELECT userID, firstName, lastName, countryCode FROM nurses");

		while (rs.next()) {
			Nurse n = new Nurse();
			n.setId(rs.getInt(1));
			n.setFirstName(rs.getString(2));
			n.setLastName(rs.getString(3));
			n.setCountryCode(rs.getString(4));
			nurseList.add(n);
		}
		if (con != null)
			con.close();

		return nurseList;
	}

}