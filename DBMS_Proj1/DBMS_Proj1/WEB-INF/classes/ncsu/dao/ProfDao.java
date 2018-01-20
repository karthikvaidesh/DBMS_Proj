package ncsu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import ncsu.util.DBUtil;

public class ProfDao {

	static Connection conn = null;
	static ResultSet rs = null;
	static ResultSet rs1 = null;
	static PreparedStatement ps = null;

	public ResultSet fetchDetails(String username) {
		// TODO Auto-generated method stub

		conn = DBUtil.getConnection();

		if (conn != null) {
			// String sql = "select USER_TYPE from USERS where USER_ID='kogan' and
			// PASSWD='kogan'";
			String sql = "select * from TEACHES T, COURSES C where T.USER_ID=? and C.COURSE_ID = T.COURSE_ID";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);

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

	public boolean createCourse(String userid, String courseid, String coursename, String startdate, String enddate,
			String topicArray[], String maxStudents, String taArray[]) {
		conn = DBUtil.getConnection();

		System.out.print("COMING INTO createCourse" + (conn == null));

		if (conn != null) {
			System.out.println("CONNECTION IS NOT NULL");

			String sql = "INSERT INTO COURSES VALUES (?,?,?,?,?)";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, courseid);
				ps.setString(2, coursename);
				ps.setDate(3, java.sql.Date.valueOf(startdate));
				ps.setDate(4, java.sql.Date.valueOf(enddate));
				ps.setInt(5, Integer.parseInt(maxStudents));

				int rows = ps.executeUpdate();
				Integer next_id = 0;
				if (rows > 0) {
					System.out.println("INSERTED INTO COURSES");
					String sql2 = "INSERT INTO TEACHES VALUES (?,?)";
					ps = conn.prepareStatement(sql2);
					ps.setString(1, userid);
					ps.setString(2, courseid);
					int rows1 = ps.executeUpdate();
					if (rows1 > 0) {
						System.out.println("INSERTED INTO TEACHES");
						String sql3 = "SELECT MAX(CAST(TOPIC_ID AS INT)) FROM TOPICS";
						ps = conn.prepareStatement(sql3);
						rs = ps.executeQuery();
						if (rs.next()) {
							next_id = Integer.parseInt(rs.getString(1));
							System.out.println("next_id=" + next_id);
						}

						for (String topicName : topicArray) {
							next_id++;
							String sql4 = "INSERT INTO TOPICS VALUES(?,?)";
							ps = conn.prepareStatement(sql4);
							System.out.println(Integer.toString((next_id)));
							ps.setString(1, Integer.toString((next_id)));
							ps.setString(2, topicName);
							int rows4 = ps.executeUpdate();
							if (rows4 > 0) {
								System.out.println("INSERTED INTO TOPICS");
								String sql5 = "INSERT INTO COURSES_TOPICS VALUES(?,?)";
								ps = conn.prepareStatement(sql5);
								ps.setString(1, Integer.toString((next_id)));
								ps.setString(2, courseid);
								int rows5 = ps.executeUpdate();
								if (rows5 > 0) {
									System.out.println("INSERTED INTO COURSES_TOPICS");
									String sql6 = "UPDATE STUDENTS SET IS_TA = 1, TA_COURSE_ID = ? WHERE USER_ID = ?";

									ps = conn.prepareStatement(sql6);
									for (String ta : taArray) {
										ps.setString(1, courseid);
										ps.setString(2, ta);
										int rows6 = ps.executeUpdate();
									}

								}

							}
						}
					}
					return true;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

	public boolean enrollStudent(String studentID, String courseID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();

		if (conn != null) {

			String sql = "INSERT INTO ENROLLED_IN VALUES (?,?)";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, studentID);
				ps.setString(2, courseID);

				int rows = ps.executeUpdate();
				if (rows > 0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean dropStudent(String studentID, String courseID) {
		// TODO Auto-generated method stub

		conn = DBUtil.getConnection();

		if (conn != null) {

			String sql = "DELETE FROM ENROLLED_IN WHERE USER_ID=? AND COURSE_ID=?";

			try {
				//conn.setAutoCommit(true);
				ps = conn.prepareStatement(sql);
				ps.setString(1, studentID);
				ps.setString(2, courseID);

				int rows = ps.executeUpdate();
				System.out.println("rowsssssssssss"+rows);
				if (rows > 0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public String addTA(String studentID, String courseID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
        String msg="";
		if (conn != null) {
			String sql1 = "SELECT * FROM ENROLLED_IN where USER_ID=? AND COURSE_ID=?";
			String sql = "UPDATE STUDENTS SET IS_TA = 1, TA_COURSE_ID = ? WHERE USER_ID = ? ";
			String sql2="Select * from students where USER_ID = ? and type_id=0 ";

			try {
				ps = conn.prepareStatement(sql1);
				ps.setString(1, studentID);
				ps.setString(2, courseID);
				ResultSet rs = ps.executeQuery();
				if (!rs.next()) {
					ps=conn.prepareStatement(sql2);
					ps.setString(1, studentID);
					rs = ps.executeQuery();
					if(rs.next()) {
						msg="Undergraduate Student.Therefore can't be made TA.";
						return msg;
					}
					ps = conn.prepareStatement(sql);
					ps.setString(2, studentID);
					ps.setString(1, courseID);

					int rows = ps.executeUpdate();
					if (rows > 0) {
						msg="TA Added";
						return msg;
					}
				} else {
					msg="Student enrolled in course.Therefore can't be made TA.";
					return msg;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean addQuestion(String value1, String value2, String parameter1v1, String parameter2v1,
			String parameter3v1, String parameter4v1, String parameter5v1, String parameter1v2, String parameter2v2,
			String parameter3v2, String parameter4v2, String parameter5v2, String qid, String qtype, String qtext,
			String hint, String diff, String topic, String ansid1, String ansid2, String ansid3, String ansid4,
			String ansid5, String ansid6, String ansid7, String ansid8, String anstext1, String anstext2,
			String anstext3, String anstext4, String anstext5, String anstext6, String anstext7, String anstext8,
			String iscorrectans1, String iscorrectans2, String iscorrectans3, String iscorrectans4,
			String iscorrectans5, String iscorrectans6, String iscorrectans7, String iscorrectans8) {
		conn = DBUtil.getConnection();

		String answer[] = new String[8], isCorrect[] = new String[8], ansText[] = new String[8],
				parameter1[] = new String[5], parameter2[] = new String[5], pid[] = new String[2];

		System.out.println("Ansid1 = " + ansid1);

		answer[0] = ansid1;
		answer[1] = ansid2;
		answer[2] = ansid3;
		answer[3] = ansid4;
		answer[4] = ansid5;
		answer[5] = ansid6;
		answer[6] = ansid7;
		answer[7] = ansid8;

		isCorrect[0] = iscorrectans1;
		isCorrect[1] = iscorrectans2;
		isCorrect[2] = iscorrectans3;
		isCorrect[3] = iscorrectans4;
		isCorrect[4] = iscorrectans5;
		isCorrect[5] = iscorrectans6;
		isCorrect[6] = iscorrectans7;
		isCorrect[7] = iscorrectans8;

		ansText[0] = anstext1;
		ansText[2] = anstext3;
		ansText[1] = anstext2;
		ansText[3] = anstext4;
		ansText[4] = anstext5;
		ansText[5] = anstext6;
		ansText[6] = anstext7;
		ansText[7] = anstext8;

		pid[0] = "v1";
		pid[1] = "v2";

		if (conn != null) {
			String sql = "insert into QBANK (Q_ID,DETAILED_ANSWER,DIFFICULTY_LEVEL,HINT) VALUES (?,?,?,?)";

			try {
				ps = conn.prepareStatement(sql);

				ps.setString(1, qid);
				ps.setString(2, hint);
				ps.setString(3, diff);
				ps.setString(4, hint);

				int rows = ps.executeUpdate();
				int rows1 = 0;
				if (rows > 0) {
					String sql2 = "insert into ANSWERS (Q_ID,ANS_ID,IS_CORRECT,ANSWER_TEXT) VALUES (?,?,?,?)";

					try {
						ps = conn.prepareStatement(sql2);
						ps.setString(1, qid);

						for (int i = 0; i < 8; i++) {
							System.out.println("answer[i] = " + i + "   " + answer[i]);
							System.out.println(answer[i] != "" && answer[i] != null);
							if (answer[i] != "" && answer[i] != null) {
								ps.setString(2, answer[i]);
								System.out.println("before insertion" + answer[i]);
								ps.setString(3, isCorrect[i]);
								ps.setString(4, ansText[i]);

								rows1 = ps.executeUpdate();
							}
						}

						if (rows1 > 0) {
							String sql3 = "insert into QBANK_TOPICS (Q_ID,TOPIC_ID) VALUES (?,?)";
							ps = conn.prepareStatement(sql3);
							ps.setString(1, qid);
							ps.setString(2, topic);
							int rows2 = ps.executeUpdate();

						}
					}

					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (qtype.equals("standard")) {
				String sql4 = "insert into STD_QUESTIONS (Q_ID,Q_TEXT) VALUES (?,?)";

				try {
					ps = conn.prepareStatement(sql4);

					ps.setString(1, qid);
					ps.setString(2, qtext);

					int rows4 = ps.executeUpdate();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;

			}

			else {

				String sql5 = "insert into PARAM_QUESTIONS (Q_ID,Q_TEXT) VALUES (?,?)";
				try {
					ps = conn.prepareStatement(sql5);

					ps.setString(1, qid);
					ps.setString(2, qtext);

					int rows5 = ps.executeUpdate();

					if (rows5 > 0) {
						String sql6 = "insert into QPARAMETERS (Q_ID,P_ID,P1,P2,P3,P4,P5) VALUES (?,?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql6);
						ps.setString(1, qid);

						for (int i = 0; i < 2; i++) {
							if (i == 0) {
								System.out.println("test parameter " + parameter1v1);
								ps.setString(2, pid[i]);
								ps.setString(3, parameter1v1);
								ps.setString(4, parameter2v1);
								ps.setString(5, parameter3v1);
								ps.setString(6, parameter4v1);
								ps.setString(7, parameter5v1);
							} else {

								ps.setString(2, pid[i]);
								ps.setString(3, parameter1v2);
								ps.setString(4, parameter2v2);
								ps.setString(5, parameter3v2);
								ps.setString(6, parameter4v2);
								ps.setString(7, parameter5v2);

							}
							ps.executeUpdate();
						}

					}

					return true;

				}

				catch (SQLException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// TODO Auto-generated method stub

		}
		return false;
	}

	public static HashMap<String, String> fetchtopics() {
		conn = DBUtil.getConnection();
		HashMap<String, String> topics = new HashMap<String, String>();

		if (conn != null) {
			String sql1 = "SELECT * FROM Topics";

			try {
				ps = conn.prepareStatement(sql1);
				ResultSet rs = ps.executeQuery();
				if (rs != null) {

					while (rs.next()) {
						topics.put(rs.getString(1), rs.getString(2));
					}

					return topics;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public static HashMap<String, String> getQuestions(String topicID) {
		// TODO Auto-generated method stub
		String sql = "select * from QBANK_TOPICS qt,STD_QUESTIONS sq where qt.q_id=sq.q_id  and qt.topic_id=?";
		String sql1 = "select * from QBANK_TOPICS qt,PARAM_QUESTIONS pq where qt.q_id=pq.q_id  and qt.topic_id=?";
		conn = DBUtil.getConnection();
		HashMap<String, String> qList = new HashMap();

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, topicID);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					qList.put(rs.getString("q_id"), rs.getString("q_text"));
				}

				ps = conn.prepareStatement(sql1);
				ps.setString(1, topicID);
				rs = ps.executeQuery();
				while (rs.next()) {
					qList.put(rs.getString("q_id"), rs.getString("q_text"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return qList;
		}
		return null;
	}

	public boolean existsInHWTable(String hwID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "select * from HOMEWORKS where hw_id=?";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, hwID);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public void insertHWTable(String hwID, String hwName, String startDate, String endDate, String noOfRetries,
			String penaltyPerQues, String pointsPerQues, int noOfQues, String hwType) {
		// TODO Auto-generated method stub

		conn = DBUtil.getConnection();
		String sql = "insert into HOMEWORKS values(?,?,?,?,?,?,?,?,?)";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

				ps.setString(1, hwID);
				ps.setString(2, hwName);
				ps.setDate(3, java.sql.Date.valueOf(startDate));
				ps.setDate(4, java.sql.Date.valueOf(endDate));
				ps.setInt(5, Integer.parseInt(noOfRetries));
				ps.setFloat(6, Float.parseFloat(penaltyPerQues));
				ps.setInt(7, Integer.parseInt(pointsPerQues));
				ps.setInt(8, noOfQues);
				ps.setString(9, hwType);

				int rows = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void insertCourseHWTable(String hwID, String courseID) {
		// TODO Auto-generated method stub

		conn = DBUtil.getConnection();
		String sql = "insert into COURSES_HOMEWORKS values(?,?)";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

				ps.setString(1, hwID);
				ps.setString(2, courseID);

				int rows = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int fetchMaxQsetID(String hwID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "select MAX(QSET_ID) from QSET where HW_ID=?";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, hwID);

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	public void insertQsetTable(String hwID, int maxQsetNo) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "insert into QSET values(?,?)";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

				ps.setString(1, hwID);
				ps.setInt(2, maxQsetNo);

				int rows = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void insertHWQsetQbank(String hwID, int maxQsetNo, String qid) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "insert into HW_QSET_QBANK values(?,?,?)";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

				ps.setString(1, hwID);
				ps.setInt(2, maxQsetNo);
				ps.setString(3, qid);

				int rows = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> fetchHW(String courseID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "select * from COURSES_HOMEWORKS where COURSE_ID=?";
		System.out.println("COURSE ID : "+courseID);
		ArrayList<String> hwID = new ArrayList<String>();

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, courseID);
				System.out.println("ANUPPPPPPPPPPPPPP");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					System.out.println("HWID : "+rs.getString(1));
					hwID.add(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (hwID.isEmpty()) {
			return null;
		} else
			return hwID;
	}

	public ArrayList<String> fetchQSets(String hwID) {
		// TODO Auto-generated method stub
		conn = DBUtil.getConnection();
		String sql = "select QSET_ID from QSET where HW_ID=?";
		ArrayList<String> qsetID = new ArrayList<String>();

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

					ps.setString(1, hwID);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						qsetID.add(rs.getString(1));
					}

				if (!qsetID.isEmpty()) {
					return qsetID;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<String> fetchQuestions(String qSetId, String hwID) {

		conn = DBUtil.getConnection();
		String sql1 = "select a.Q_TEXT from PARAM_QUESTIONS a, HW_QSET_QBANK b where a.Q_ID=b.Q_ID and b.QSET_ID=? and b.HW_ID=?";
		ArrayList<String> qtext = new ArrayList<String>();

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql1);

				ps.setString(1, qSetId);
				ps.setString(2, hwID);

				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
					System.out.println("qtext : "+rs1.getString(1));
					qtext.add(rs1.getString(1));

				}
				String sql2 = "select a.Q_TEXT from STD_QUESTIONS a, HW_QSET_QBANK b where a.Q_ID=b.Q_ID and b.QSET_ID=? and b.HW_ID=?";

				if (conn != null) {

					ps = conn.prepareStatement(sql2);
					ps.setString(1, qSetId);
					ps.setString(2, hwID);

					ResultSet rs2 = ps.executeQuery();
					while (rs2.next()) {
						System.out.println("qtext : "+rs2.getString(1));
						qtext.add(rs2.getString(1));
					}

				}
			} catch (Exception e) {
			}
			// TODO Auto-generated method stub
			return qtext;
		}
		return null;
	}

	public ResultSet viewEnrolledStudents(String c_id) {
		conn = DBUtil.getConnection();
		String sql = "select u.user_id,u.f_name,u.l_name,e.course_id from ENROLLED_IN e,users u where u.user_id=e.user_id and e.course_id=?";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

					ps.setString(1, c_id);

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

	public ResultSet viewStudentReport(String u_id, String c_id) {
		conn = DBUtil.getConnection();
		String sql ="select r.hw_id, h.hw_name,r.attempt_no,sum(r.POINTS_SCORED_PER_QUESTION) as \"Marks_Obtained\" from RESPONSES r ,ENROLLED_IN e, COURSES_HOMEWORKS ch, HOMEWORKS h where e.user_id=r.user_id and r.hw_id=h.hw_id and e.course_id=ch.course_id and h.hw_id=ch.hw_id and e.user_id=? and e.course_id=? group by r.hw_id,h.hw_name,r.attempt_no order by hw_id asc";

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

	public ResultSet viewHomeworks(String c_id) {
		conn = DBUtil.getConnection();
		String sql ="select h.* from homeworks h,courses_homeworks ch where h.hw_id=ch.hw_id and ch.course_id=? ";

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);

					ps.setString(1,c_id);
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

	public ArrayList<ResultSet> fetchHWQuestions(String hw_id) {
		System.out.println("hhhhhwid"+hw_id);
		ResultSet quesSetID = fetchQueSet(hw_id);
		ArrayList<Integer> al = new ArrayList<>();
		int random_id = 0;
	
		ArrayList<ResultSet> results = new ArrayList();
		try {
			while (quesSetID.next()) {
				al.add(quesSetID.getInt("QSET_ID"));
			}
			System.out.println("allllllllllllll"+al);
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

				ps.setString(1, hw_id);
				ps.setInt(2, qsetID);

			rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String sql1 = "select distinct hqq.hw_id,qs.qset_id,p.q_id,p.q_text,qp.p_id,qp.p1,qp.p2,qp.p3,qp.p4,qp.p5,ans.ans_id,ans.answer_text,ans.is_correct,qb.difficulty_level from PARAM_QUESTIONS p,HW_QSET_QBANK hqq , QSET qs, QPARAMETERS qp, ANSWERS ans,Qbank qb where p.q_id=hqq.q_id and p.q_id=qb.q_id  and UPPER(qp.p_id)=UPPER('v1') and hqq.hw_id=qs.hw_id and qs.hw_id=? AND qs.qset_id=? and hqq.q_id=qp.q_id and ans.q_id=hqq.q_id";

			try {

				ps = conn.prepareStatement(sql1);

				ps.setString(1, hw_id);
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

}
