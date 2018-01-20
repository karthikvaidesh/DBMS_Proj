package ncsu.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncsu.dao.StudDao;

/**
 * Servlet implementation class StudController
 */
@WebServlet("/StudController")
public class StudController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");

		StudDao dao = new StudDao();
		if(action.equals("viewProfile")) {
			String a[]=dao.profile(request.getParameter("USER_ID"));
			request.setAttribute("details",a);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/studentProfile.jsp");
			view.forward(request, response);
		}
		if(action.equals("viewEnrolledCourses")) {
			ResultSet rs=dao.fetchEnrolledCourses(request.getParameter("USER_ID"));
			request.setAttribute("rs", rs);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/enrolledCourses.jsp");
			view.forward(request, response);
			
		}

		if (action.equals("pendingHW")) {
			ResultSet rs = dao.fetchAllHW(request.getParameter("USER_ID"),request.getParameter("c_id"));
			System.out.println("resultset" + rs);
			request.setAttribute("rs", rs);
			String user_id = request.getParameter("USER_ID");
			request.setAttribute("user_id", user_id);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/pendingHW.jsp");
			view.forward(request, response);
		}
		if (action.equals("attemptHW")) {
			String hw_ID = request.getParameter("hw_ID");
			String hwType = request.getParameter("hw_type");
			String user_id = request.getParameter("user_id");
			if (hwType.equals("standard")) {
				ArrayList<ResultSet> results = dao.fetchHW(hw_ID);
				System.out.println("2nd" + results.size());
				request.setAttribute("results", results);
				request.setAttribute("hwID", hw_ID);
				request.setAttribute("user_id", user_id);

				RequestDispatcher view = request.getRequestDispatcher("/jsp/examPage.jsp");
				view.forward(request, response);
			} else {
				Boolean recordFound = false;
				ArrayList<ResultSet> results = dao.fetchHW(hw_ID);
				ResultSet std = (ResultSet) results.get(0);
				ResultSet param = (ResultSet) results.get(1);
				HashMap<String, ArrayList> outerMap = new HashMap<>();
				String qid = new String();
				try {
					int c = 0;
					while (std.next()) {
						request.setAttribute("user_id", user_id);
						request.setAttribute("qset_id", std.getString("QSET_ID"));
						request.setAttribute("hw_id", std.getString("HW_ID"));
						qid = std.getString("Q_ID");
						System.out.println("NOW QID : "+qid);
						if (Integer.parseInt(std.getString("difficulty_level")) == 3) {

							if (!outerMap.containsKey(std.getString("Q_ID"))) {
								if (c == 0) {
									recordFound = true;
									ArrayList outerList = new ArrayList();
									outerList.add(std.getString("Q_TEXT"));
									outerList.add(std.getString("HW_ID"));
									outerList.add(std.getString("QSET_ID"));
									outerList.add(std.getString("difficulty_level"));
									String ansid = std.getString("ANS_ID");
									String iscorrect = std.getString("IS_CORRECT");
									String text = std.getString("ANSWER_TEXT");
									HashMap<String, ArrayList> innerMap = new HashMap();
									ArrayList innerList = new ArrayList();
									innerList.add(text);
									innerList.add(iscorrect);
									innerMap.put(ansid, innerList);
									outerList.add(innerMap);
									outerMap.put(std.getString("Q_ID"), outerList);
									c++;
								}
							} else {
								ArrayList outer = outerMap.get(std.getString("Q_ID"));
								HashMap inner = (HashMap) outer.get(4);
								String ansid = std.getString("ANS_ID");
								String iscorrect = std.getString("IS_CORRECT");
								String text = std.getString("ANSWER_TEXT");
								ArrayList innerList = new ArrayList();
								innerList.add(text);
								innerList.add(iscorrect);
								inner.put(ansid, innerList);

							}

						}
						request.setAttribute("type", "std");
					}
					if (recordFound) {
						request.setAttribute("type", "std");
						request.setAttribute("qmap", outerMap);
						for(Map.Entry<String,ArrayList> mp :outerMap.entrySet())
							qid=mp.getKey();
						request.setAttribute("diff", outerMap.get(qid).get(3));

					}

					System.out.println("MAPPPPPPPPPPPP" + outerMap);
					HashMap<String, ArrayList> outerMapParam = new HashMap<>();
					Boolean recordFoundParam = false;
					while (param.next() && !recordFound) {
						request.setAttribute("user_id", user_id);
						request.setAttribute("qset_id", param.getString("QSET_ID"));
						request.setAttribute("hw_id", param.getString("HW_ID"));
						qid = param.getString("Q_ID");
						if (Integer.parseInt(param.getString("difficulty_level")) == 3) {

							if (!outerMapParam.containsKey(param.getString("Q_ID"))) {
								if (c == 0) {
									recordFoundParam = true;
									ArrayList outerList = new ArrayList();
									outerList.add(param.getString("Q_TEXT"));
									outerList.add(param.getString("HW_ID"));
									outerList.add(param.getString("QSET_ID"));
									outerList.add(param.getString("difficulty_level"));
									outerList.add(param.getString("P1"));
									outerList.add(param.getString("P2"));
									outerList.add(param.getString("P3"));
									outerList.add(param.getString("P4"));
									outerList.add(param.getString("P5"));

									String ansid = param.getString("ANS_ID");
									String iscorrect = param.getString("IS_CORRECT");
									String text = param.getString("ANSWER_TEXT");
									HashMap<String, ArrayList> innerMap = new HashMap();
									ArrayList innerList = new ArrayList();
									innerList.add(text);
									innerList.add(iscorrect);
									innerMap.put(ansid, innerList);
									outerList.add(innerMap);
									outerMapParam.put(param.getString("Q_ID"), outerList);
									c++;
								}
							} else {

								ArrayList outer = outerMapParam.get(param.getString("Q_ID"));
								HashMap inner = (HashMap) outer.get(9);
								String ansid = param.getString("ANS_ID");
								String iscorrect = param.getString("IS_CORRECT");
								String text = param.getString("ANSWER_TEXT");
								ArrayList innerList = new ArrayList();
								innerList.add(text);
								innerList.add(iscorrect);
								inner.put(ansid, innerList);
							}
						}
					}

					if (recordFoundParam) {
						request.setAttribute("type", "std");
						request.setAttribute("qmap", outerMapParam);
						for(Map.Entry<String,ArrayList> mp :outerMapParam.entrySet())
							qid=mp.getKey();
						request.setAttribute("diff", outerMapParam.get(qid).get(3));	
					}

					RequestDispatcher view = request.getRequestDispatcher("/jsp/adaptiveExamPage.jsp");
					view.forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		if (action.equals("submitExam")) {
			// ArrayList<ResultSet> results = request.getParameter("resultSets");
			List<String> paramNames = Collections.list(request.getParameterNames());
			ArrayList<String> qids = new ArrayList(), studAns = new ArrayList();
			String hwID = request.getParameter("hwID");
			int totPoints = 0;
			String userID = request.getParameter("user_id");
			ArrayList<String> markedAnsID = new ArrayList();
			String ansDetails[] = null;
            int k=7;
            if(request.getParameter("from") == null)
            	k=4;
			for (int i = k; i < paramNames.size(); i++) {
				System.out.println("value :" + request.getParameter(paramNames.get(i)));
				System.out.println("req param name : " + request.getParameter(paramNames.get(i)));
				ansDetails = request.getParameter(paramNames.get(i)).split("@");
				System.out.println("ansdetails : " + ansDetails[0] + ansDetails[1]);
				studAns.add(ansDetails[0]);
				markedAnsID.add(ansDetails[1]);
				if (paramNames.get(i).contains("option")) {
					qids.add(paramNames.get(i).substring(7, paramNames.get(i).length()));
					// System.out.println("paramNames : "+name);
				}

			}

			ArrayList penaltyAndPPQ = dao.getPenaltyAndPoints(hwID);
			ArrayList<Integer> isCorr = new ArrayList();
			ArrayList ppq = new ArrayList();

			for (int i = 0; i < qids.size(); i++) {
				String correctAns = dao.getCorrectAns(qids.get(i));
				System.out.println("Correct Ans : " + correctAns + " stud ans : " + studAns.get(i));
				if (correctAns.equals(studAns.get(i))) {
					totPoints += (int) penaltyAndPPQ.get(1);
					isCorr.add(1);
					ppq.add((int) penaltyAndPPQ.get(1));
				} else {
					totPoints -= (int) penaltyAndPPQ.get(0);
					isCorr.add(0);
					ppq.add(0 - (int) penaltyAndPPQ.get(0));
				}
			}

			if (totPoints < 0)
				totPoints = 0;
			//
			int attemptNo = dao.getAttemptNo(userID, hwID);

			if (request.getParameter("from") == null) {//for standard type hw
				for (int i = 0; i < qids.size(); i++) {
					dao.generateReport(userID, hwID, request.getParameter("qsetID"), qids.get(i), markedAnsID.get(i),
							attemptNo + 1, isCorr.get(i), (Integer) ppq.get(i), totPoints);
				}
				response.sendRedirect(request.getContextPath() + "/jsp/homepage.jsp");
			} else {//for adaptive type hw
				dao.generateReport(userID, hwID, request.getParameter("qsetID"), qids.get(0), markedAnsID.get(0),
						(int) Math.ceil((double) (attemptNo / 3)), isCorr.get(0), (Integer) ppq.get(0), totPoints);

				ArrayList<HashMap> questions = fetchquestion(hwID, dao);
				Integer diffLevel = 0;
				Integer count = Integer.parseInt(request.getParameter("count"));

				if(count < 3) {
					count++;
					if (isCorr.get(0) == 1) {
						diffLevel = Integer.parseInt(ansDetails[2]) + 1;
					} else
						diffLevel = Integer.parseInt(ansDetails[2]) - 1;
	
					HashMap<String, ArrayList> standard = questions.get(0);
					HashMap<String, ArrayList> param = questions.get(1);
					System.out.println("after first");
					System.out.println("stddddddddddddd"+standard);
					System.out.println("parammmmmmmmmm"+param);
					HashMap<String, ArrayList> qmap = new HashMap();
					
					request.setAttribute("type", "std");
	
					for (Map.Entry mp : standard.entrySet()) {
						if (((ArrayList) mp.getValue()).get(3) == diffLevel.toString()) {
							request.setAttribute("type", "std");
							qmap.put((String) mp.getKey(), (ArrayList) mp.getValue());
						}
					}
					for (Map.Entry mp : param.entrySet()) {
						if (((ArrayList) mp.getValue()).get(3) == diffLevel.toString()) {
							request.setAttribute("type", "param");
							qmap.put((String) mp.getKey(), (ArrayList) mp.getValue());
						}
					}
					System.out.println("KARTHIK std: " + standard);
					System.out.println("KARTHIK param: " + param);
					request.setAttribute("qmap", qmap);
					request.setAttribute("qCount", count.toString());
	
					RequestDispatcher view = request.getRequestDispatcher("/jsp/adaptiveExamPage.jsp");
					view.forward(request, response);
				}
				else {
					response.sendRedirect(request.getContextPath() + "/jsp/homepage.jsp");
				}
			}
		}
		
		if(action.equals("pastAttempts")) {
			System.out.println(request.getParameter("user_id"));
			System.out.println(request.getParameter("c_id"));
			ResultSet rs=dao.viewStudentReport(request.getParameter("user_id"),request.getParameter("c_id"));
			request.setAttribute("rs", rs);
			request.setAttribute("u_id",request.getParameter("user_id") );
			RequestDispatcher view = request.getRequestDispatcher("/jsp/viewPastAttempts.jsp");
			view.forward(request, response);
			
		}
		if(action.equals("viewDetailedReport")) {
			String u_id=request.getParameter("u_id");
			String h_id=request.getParameter("h_id");
			String a_no=request.getParameter("a_no");
			ResultSet rs=dao.viewDetailedReport(u_id,h_id,a_no);
			request.setAttribute("rs", rs);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/viewDetailedReport.jsp");
			view.forward(request, response);
			
		}
	}

	ArrayList fetchquestion(String hw_ID, StudDao dao) {
		Boolean recordFound = false;
		ArrayList<ResultSet> results = dao.fetchHW(hw_ID);
		ArrayList<HashMap> al = new ArrayList();
		ResultSet std = (ResultSet) results.get(0);
		ResultSet param = (ResultSet) results.get(1);
		HashMap<String, ArrayList> outerMap = new HashMap<>();
		try {
			// int c=0;
			while (std.next()) {

				if (Integer.parseInt(std.getString("difficulty_level")) == 3) {

					if (!outerMap.containsKey(std.getString("Q_ID"))) {

						// recordFound = true;
						ArrayList outerList = new ArrayList();
						outerList.add(std.getString("Q_TEXT"));
						outerList.add(std.getString("HW_ID"));
						outerList.add(std.getString("QSET_ID"));
						outerList.add(std.getString("difficulty_level"));
						String ansid = std.getString("ANS_ID");
						String iscorrect = std.getString("IS_CORRECT");
						String text = std.getString("ANSWER_TEXT");
						HashMap<String, ArrayList> innerMap = new HashMap();
						ArrayList innerList = new ArrayList();
						innerList.add(text);
						innerList.add(iscorrect);
						innerMap.put(ansid, innerList);
						outerList.add(innerMap);
						outerMap.put(std.getString("Q_ID"), outerList);

					} else {
						ArrayList outer = outerMap.get(std.getString("Q_ID"));
						HashMap inner = (HashMap) outer.get(4);
						String ansid = std.getString("ANS_ID");
						String iscorrect = std.getString("IS_CORRECT");
						String text = std.getString("ANSWER_TEXT");
						ArrayList innerList = new ArrayList();
						innerList.add(text);
						innerList.add(iscorrect);
						inner.put(ansid, innerList);

					}

				}
				// request.setAttribute("type", "std");
			}
			al.add(outerMap);

			System.out.println("MAPPPPPPPPPPPP from method" + outerMap);
			HashMap<String, ArrayList> outerMapParam = new HashMap<>();
			// Boolean recordFoundParam=false;
			while (param.next()) {
				// request.setAttribute("user_id", user_id);
				// request.setAttribute("qset_id", param.getString("QSET_ID"));
				// request.setAttribute("hw_id", param.getString("HW_ID"));
				if (Integer.parseInt(param.getString("difficulty_level")) == 3) {

					if (!outerMapParam.containsKey(param.getString("Q_ID"))) {

						// recordFoundParam = true;
						ArrayList outerList = new ArrayList();
						outerList.add(param.getString("Q_TEXT"));
						outerList.add(param.getString("HW_ID"));
						outerList.add(param.getString("QSET_ID"));
						outerList.add(param.getString("difficulty_level"));
						outerList.add(param.getString("P1"));
						outerList.add(param.getString("P2"));
						outerList.add(param.getString("P3"));
						outerList.add(param.getString("P4"));
						outerList.add(param.getString("P5"));

						String ansid = param.getString("ANS_ID");
						String iscorrect = param.getString("IS_CORRECT");
						String text = param.getString("ANSWER_TEXT");
						HashMap<String, ArrayList> innerMap = new HashMap();
						ArrayList innerList = new ArrayList();
						innerList.add(text);
						innerList.add(iscorrect);
						innerMap.put(ansid, innerList);
						outerList.add(innerMap);
						outerMapParam.put(param.getString("Q_ID"), outerList);

					} else {

						ArrayList outer = outerMapParam.get(param.getString("Q_ID"));
						HashMap inner = (HashMap) outer.get(9);
						String ansid = param.getString("ANS_ID");
						String iscorrect = param.getString("IS_CORRECT");
						String text = param.getString("ANSWER_TEXT");
						ArrayList innerList = new ArrayList();
						innerList.add(text);
						innerList.add(iscorrect);
						inner.put(ansid, innerList);
					}
				}
			}

			al.add(outerMapParam);
			// if(recordFoundParam) request.setAttribute("qmap", outerMapParam);

			// RequestDispatcher view =
			// request.getRequestDispatcher("/jsp/adaptiveExamPage.jsp");
			// view.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return al;
	}
	 
}
