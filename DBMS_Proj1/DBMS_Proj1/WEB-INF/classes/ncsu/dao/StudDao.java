package ncsu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import ncsu.util.DBUtil;

public class StudDao {

	static Connection conn = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static ResultSet rs1 = null;

	public ArrayList<ResultSet> fetchHW(String hwID) {
		// TODO Auto-generated method stub
		ResultSet quesSetID = fetchQueSet(hwID);
		ArrayList<Integer> al = new ArrayList<>();
		int random_id = 0;
	
		ArrayList<ResultSet> results = new ArrayList();
		try {
			while (quesSetID.next()) {
				al.add(quesSetID.getInt("QSET_ID"));
			}
             Object a[]=al.toArray();
              random_id=(int) a[(int) Math.floor(Math.random() *a.length)];
             System.out.println("random_id"+random_id);
//			int max = 0, min = Integer.MAX_VALUE;
			
//
//			if (quesSetID != null) {
//				while (quesSetID.next()) {
//					int temp = quesSetID.getInt("QSET_ID");
//					if (temp < min)
//						min = temp;
//					if (temp > max)
//						max = temp;
//				}
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//
//		Random random = new Random();
		int qsetID = random_id;

		String sql = "select s.Q_ID,s.Q_TEXT,qs.HW_ID,qs.QSET_ID,ans.ANS_ID,ans.IS_CORRECT,ans.ANSWER_TEXT,qb.difficulty_level from STD_QUESTIONS s,HW_QSET_QBANK hqq, QSET qs, ANSWERS ans,Qbank qb where s.q_id=hqq.q_id and s.q_id=qb.q_id and hqq.hw_id=qs.hw_id and hqq.hw_id=? AND qs.qset_id=? and hqq.q_id=ans.Q_ID";

		if (conn != null) {
			try {

				ps = conn.prepareStatement(sql);

				ps.setString(1, hwID);
				ps.setInt(2, qsetID);

				rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String sql1 = "select distinct hqq.hw_id,qs.qset_id,p.q_id,p.q_text,qp.p_id,qp.p1,qp.p2,qp.p3,qp.p4,qp.p5,ans.ans_id,ans.answer_text,ans.is_correct,qb.difficulty_level from PARAM_QUESTIONS p,HW_QSET_QBANK hqq , QSET qs, QPARAMETERS qp, ANSWERS ans,Qbank qb where p.q_id=hqq.q_id and p.q_id=qb.q_id  and UPPER(qp.p_id)=UPPER('v1') and hqq.hw_id=qs.hw_id and qs.hw_id=? AND qs.qset_id=? and hqq.q_id=qp.q_id and ans.q_id=hqq.q_id";

			try {

				ps = conn.prepareStatement(sql1);

				ps.setString(1, hwID);
				ps.setInt(2, qsetID);
            
				rs1 = ps.executeQuery();
//                 while(rs1.next()) {
//                	 System.out.println("rs1"+rs1.getString(1));
//                 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             System.out.println("before");
			results.add(rs);
			System.out.println("b/w");
			results.add(rs1);
			System.out.println("after");
           System.out.println("ADITYA" + results.size());
		}

		return results;
	}

	private ResultSet fetchQueSet(String hwID) {
		// TODO Auto-generated method stub

		String sql = "select QSET_ID from QSET where HW_ID=?";

		conn = DBUtil.getConnection();

		if (conn != null) {
			try {

				ps = conn.prepareStatement(sql);

				ps.setString(1, hwID);

				rs = ps.executeQuery();

				if (rs != null) {
					return rs;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public ResultSet fetchAllHW(String userID,String c_id) {
		// TODO Auto-generated method stub

		String sql = "select h.* from ENROLLED_IN e,COURSES_HOMEWORKS hc,HOMEWORKS h "
				+ "where e.USER_ID=? and e.COURSE_ID=hc.COURSE_ID and hc.HW_ID=h.HW_ID and e.course_id=? and h.HW_ENDDATETIME>=TO_CHAR(CURRENT_DATE, 'DD-MON-YY') "
				+ "and h.NO_OF_RETRIES>(select NVL((select max(ATTEMPT_NO) from RESPONSES r where r.USER_ID=e.USER_ID and r.HW_ID=h.HW_ID),0) from DUAL)";

		conn = DBUtil.getConnection();

		if (conn != null) {
			try {

				ps = conn.prepareStatement(sql);

				ps.setString(1, userID);
				ps.setString(2, c_id);

				rs = ps.executeQuery();

				if (rs.isBeforeFirst()) {
					return rs;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	public String getCorrectAns(String qid) {
		// TODO Auto-generated method stub
		
		String sql = "select ANSWER_TEXT from ANSWERS where Q_ID=? and IS_CORRECT=1";
		
		conn = DBUtil.getConnection();

		if (conn != null) {
			try {

				ps = conn.prepareStatement(sql);

				ps.setString(1, qid);
				
				rs = ps.executeQuery();

				if (rs.next()) {
					return rs.getString(1);
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public ArrayList getPenaltyAndPoints(String hwID) {
		// TODO Auto-generated method stub
		
		String sql = "select PENALTY_PER_QUESTION, POINTS_PER_QUESTION from HOMEWORKS where HW_ID=?";
		ArrayList penaltyAndPPQ = new ArrayList();
				
		conn = DBUtil.getConnection();

		if (conn != null) {
			try {

				ps = conn.prepareStatement(sql);

				ps.setString(1, hwID);
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					penaltyAndPPQ.add(rs.getInt(1));
					penaltyAndPPQ.add(rs.getInt(2));
					return penaltyAndPPQ;
				}
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
		return null;
	}

	public int getAttemptNo(String userID, String hwID) {
		// TODO Auto-generated method stub
		
		String sql = "select count(distinct attempt_no) from RESPONSES where USER_ID=? and HW_ID=?";
		
		conn = DBUtil.getConnection();

		if (conn != null) {
			try {

				ps = conn.prepareStatement(sql);

				ps.setString(1, userID);
				ps.setString(2, hwID);
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public void generateReport(String userID, String hwID, String qsetID, String qID, String ansID, int attemptNo,
			Integer isCorrect, Integer ppq, int totPoints) {
		// TODO Auto-generated method stub
		
		String sql = "insert into RESPONSES values (?,?,?,?,?,?,?,?)";
		
		conn = DBUtil.getConnection();

		if (conn != null) {
			try {

				ps = conn.prepareStatement(sql);

				ps.setString(1, userID);
				ps.setString(2, hwID);
				ps.setString(3, qsetID);
				ps.setString(4, qID);
				ps.setString(5, ansID);
				ps.setInt(6, attemptNo);
				ps.setInt(7, isCorrect);
				ps.setInt(8, ppq);
				
				int rows = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public ResultSet viewStudentReport(String u_id,String c_id) {
		conn = DBUtil.getConnection();
		String sql ="select r.hw_id, h.hw_name,r.attempt_no,sum(r.POINTS_SCORED_PER_QUESTION) as \"Marks_Obtained\" from RESPONSES r ,ENROLLED_IN e, COURSES_HOMEWORKS ch, HOMEWORKS h where e.user_id=r.user_id and r.hw_id=h.hw_id and e.course_id=ch.course_id and h.hw_id=ch.hw_id and e.user_id=?  and e.course_id=?  group by r.hw_id,h.hw_name,r.attempt_no order by hw_id asc";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

					ps.setString(1,u_id);
					ps.setString(2,c_id);
					System.out.println("before");
					ResultSet rs = ps.executeQuery();

					if (rs.isBeforeFirst()) {
						return rs;
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public ResultSet viewDetailedReport(String u_id, String h_id,String a_no) {
		conn = DBUtil.getConnection();
		String sql ="select r.q_id,(select s.q_text from STD_QUESTIONS s where r.q_id=s.q_id union select pq.q_text from PARAM_QUESTIONS pq where r.q_id=pq.q_id) as Q_TEXT ,r.attempt_no,r.is_correct, a.answer_text as marked_answer, " 
				+ "qb.detailed_answer as correct_answer_explanation,sum(r.points_scored_per_question) as points_per_question " 
				+ "from RESPONSES r " 
				+ "inner join ANSWERS a " 
				+ "on r.ans_id=a.ans_id and r.q_id=a.q_id and r.user_id=? and r.hw_id=? " 
				+ "inner join qbank qb " 
				+ "on  r.ans_id=a.ans_id and r.q_id=a.q_id  and a.q_id=qb.q_id and r.user_id=? and r.hw_id=? " 
				+ "inner join HOMEWORKS h " 
				+ "on r.hw_id=h.hw_id and r.ans_id=a.ans_id and r.q_id=a.q_id  and a.q_id=qb.q_id and r.user_id=? and r.hw_id=? " 
				+ "where r.attempt_no=? "
				+ "group by r.q_id,r.attempt_no,r.is_correct, a.answer_text, qb.detailed_answer " 
				+ "order by r.q_id,r.attempt_no asc ";
		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

					ps.setString(1,u_id);
					ps.setString(2,h_id);
					ps.setString(3,u_id);
					ps.setString(4,h_id);
					ps.setString(5,u_id);
					ps.setString(6,h_id);
					ps.setString(7,a_no);
					System.out.println("before");
					ResultSet rs = ps.executeQuery();

					if (rs.isBeforeFirst()) {
						return rs;
					}
					return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public String[] profile(String u_id) {
		conn = DBUtil.getConnection();
		String sql ="select user_id,f_name,l_name from users where user_id=?";
		String a[]=new String[3];
		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

					ps.setString(1,u_id);
			        ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                    	a[0]=rs.getString(1);
                    	a[1]=rs.getString(2);
                    	a[2]=rs.getString(3);
                    	return a;
                    	
                    }
                    return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
		
	}

	public ResultSet fetchEnrolledCourses(String u_id) {
		conn = DBUtil.getConnection();
		String sql ="select e.user_id,c.course_id,c.course_name from enrolled_in e,courses c where e.user_id=? and e.course_id=c.course_id";
		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

					ps.setString(1,u_id);
			        ResultSet rs = ps.executeQuery();
			        if (rs.isBeforeFirst()) {
						return rs;
					}
			        return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	

}
