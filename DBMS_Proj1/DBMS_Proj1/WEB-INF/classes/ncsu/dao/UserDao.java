package ncsu.dao;

import java.sql.*;

import ncsu.util.DBUtil;

public class UserDao {
	
	static Connection conn = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public ResultSet authenticate(String username, String password) {
		// TODO Auto-generated method stub
		
		conn = DBUtil.getConnection();
		
		if(conn != null) {
			//String sql = "select USER_TYPE from USERS where USER_ID='kogan' and PASSWD='kogan'";
			String sql = "select * from USERS where USER_ID=? and PASSWD=?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				
				rs = ps.executeQuery();
				
				if(rs != null) {
					return rs;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
			
		
		return null;
	}

	public String checkStudentType(String username) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		
		if(conn != null) {
			
			String sql = "select * from STUDENTS where USER_ID=?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				
				rs = ps.executeQuery();
				
				if(rs.next() && (rs.getInt("TYPE_ID") == 1) && (rs.getInt("IS_TA") == 1)) {
					return "ta";
				}
				else if(rs.getInt("TYPE_ID") == 1) {
					return "gradStudent";
				}
				else return "undergradStudent";
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "";
	}

}
