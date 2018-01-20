package ncsu.util;

import java.sql.*;



public class Tempmainclass {
	static Connection conn = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		conn = DBUtil.getConnection();
		
		if(conn != null) {
			String sql = "select * from USERS";
			
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getInt(1));
				}
				
				rs.close();
				DBUtil.closeStatement(ps);
				DBUtil.closeConnection(conn);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
