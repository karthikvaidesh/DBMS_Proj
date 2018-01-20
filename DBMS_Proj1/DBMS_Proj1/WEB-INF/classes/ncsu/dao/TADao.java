package ncsu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ncsu.util.DBUtil;

public class TADao {
	
	static Connection conn = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	public ResultSet fetchDetails(String username) {
		// TODO Auto-generated method stub
		
		conn = DBUtil.getConnection();
		
		if(conn != null) {
			String sql = "select * from STUDENTS S, COURSES C where S.USER_ID=? and S.TA_COURSE_ID=C.COURSE_ID";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				
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
	
	public boolean enrollStudent(String studentID, String courseID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		
		if(conn != null) {
			
			String sql = "INSERT INTO ENROLLED_IN VALUES (?,?)";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, studentID);
				ps.setString(2, courseID);
				
				int rows = ps.executeUpdate();
				if(rows > 0) {
					return true;
				}
			}
			catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		
		return false;
	}

	public boolean dropStudent(String studentID, String courseID) {
		// TODO Auto-generated method stub
		
		conn = DBUtil.getConnection();
		
		if(conn != null) {
			
			String sql = "DELETE FROM ENROLLED_IN WHERE USER_ID=? AND COURSE_ID=?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, studentID);
				ps.setString(2, courseID);
				
				int rows = ps.executeUpdate();
				if(rows > 0) {
					return true;
				}
			}
			catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
			
		return false;
	}

}
