package ncsu.util;

import java.sql.*;

public class DBUtil {
	
	private static final String DRIVERNAME="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@orca.csc.ncsu.edu:1521/orcl.csc.ncsu.edu";
	private static final String USERNAME="kvaides";
	private static final String PASSWORD="200207321";
	
	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName(DRIVERNAME);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println(e);

		} catch (ClassNotFoundException e) {

			System.out.println(e);
		}
		return con;
	}

	public static void closeStatement(PreparedStatement pst) {

		try {
			if (pst != null)
				pst.close();
		} catch (SQLException e) {

			System.out.println(e);
		}
	}
	
	
	public static void closeConnection(Connection con) {

		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {

			System.out.println(e);
		}
	}
	
	

}
