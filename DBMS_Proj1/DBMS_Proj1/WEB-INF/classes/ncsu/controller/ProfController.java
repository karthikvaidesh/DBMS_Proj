package ncsu.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncsu.dao.ProfDao;
import ncsu.dao.UserDao;
import ncsu.dao.temp;

/**
 * Servlet implementation class ProfController
 */
@WebServlet("/ProfController")
public class ProfController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		System.out.println("COMING INTO MAIN");
		
		ProfDao dao = new ProfDao();
		
		if(action.equals("viewProfile")) {
			
				ResultSet rs = dao.fetchDetails(request.getParameter("USER_ID"));
				
				request.setAttribute("rs", rs);
				
				RequestDispatcher view = request.getRequestDispatcher("/jsp/profProfilePage.jsp");
				view.forward(request, response);
				
	    }
		else if (action.equals("addCourseForm")) {
			  request.setAttribute("user_id",request.getParameter("USER_ID"));
			  RequestDispatcher view = request.getRequestDispatcher("/jsp/addCourse.jsp");
				view.forward(request, response);
	    }
		else if (action.equals("createCourse")) {
//			  request.setAttribute("user_id",request.getParameter("USER_ID"));
//			  RequestDispatcher view = request.getRequestDispatcher("/jsp/addCourse.jsp");
//				view.forward(request, response);
			String topicArray[] = request.getParameter("topics").trim().split(",");
			String taArray[] = request.getParameter("tas").trim().split(",");
			boolean flag = dao.createCourse(request.getParameter("userid"), request.getParameter("courseid"), request.getParameter("coursename"), request.getParameter("startdate"),
					request.getParameter("enddate"), topicArray, request.getParameter("maxStudents"), taArray);
			if (flag) {
				
				response.sendRedirect(request.getContextPath()+"/jsp/homepage.jsp");
			}
			
	    }
		else if (action.equals("addStudentForm")) {
			  request.setAttribute("user_id",request.getParameter("USER_ID"));
			  request.setAttribute("ControllerType", "Prof");
			  RequestDispatcher view = request.getRequestDispatcher("/jsp/addStudent.jsp");
				view.forward(request, response);
	    }
		else if(action.equals("addStudent")) {
			boolean flag = dao.enrollStudent(request.getParameter("studentID"), request.getParameter("courseID"));
			
			if (flag) {
				response.sendRedirect(request.getContextPath()+"/jsp/homepage.jsp");
			}
		}
		else if (action.equals("dropStudentForm")) {
			  request.setAttribute("user_id",request.getParameter("USER_ID"));
			  request.setAttribute("ControllerType", "Prof");
			  RequestDispatcher view = request.getRequestDispatcher("/jsp/dropStudents.jsp");
				view.forward(request, response);
	    }
		else if(action.equals("dropStudent")) {
			boolean flag = dao.dropStudent(request.getParameter("studentID"), request.getParameter("courseID"));
			
			if (flag) {
				response.sendRedirect(request.getContextPath()+"/jsp/homepage.jsp");
			}
		}
		else if (action.equals("addTAForm")) {
			response.sendRedirect(request.getContextPath()+"/jsp/addTA.jsp");
	    }
		
		else if(action.equals("addTA")) {
			String msg = dao.addTA(request.getParameter("studentID"), request.getParameter("courseID"));
			response.sendRedirect(request.getContextPath()+"/jsp/error.jsp?msg="+msg);
		
		
		}
		else if (action.equals("addQuestionForm")) {
			request.setAttribute("user_id",request.getParameter("USER_ID"));
			HashMap<String, String> topics=ProfDao.fetchtopics();
			if(topics!=null)
				request.setAttribute("topics", topics);
			request.setAttribute("ControllerType", "Prof");
			  RequestDispatcher view = request.getRequestDispatcher("/jsp/addQuestions.jsp");
				view.forward(request, response);
			
	    }
		else if (action.equals("addQuestion")) {
			System.out.println("parameter1v="+request.getParameter("parameter1v1"));
			boolean flag = dao.addQuestion(request.getParameter("value1"),request.getParameter("value2"),request.getParameter("parameter1v1"),
										   request.getParameter("parameter2v1"),request.getParameter("parameter3v1"),request.getParameter("parameter4v1"),
										   request.getParameter("parameter5v1"),request.getParameter("parameter1v2"),request.getParameter("parameter2v2"),
										   request.getParameter("parameter3v2"),request.getParameter("parameter4v2"),request.getParameter("parameter5v2"),
										   request.getParameter("qid"),request.getParameter("qtype"),request.getParameter("qtext"),request.getParameter("hint"),
										   request.getParameter("diff"),request.getParameter("topics"),request.getParameter("ansid1"),request.getParameter("ansid2"),
										   request.getParameter("ansid3"),request.getParameter("ansid4"),request.getParameter("ansid5"),request.getParameter("ansid6"),
										   request.getParameter("ansid7"),request.getParameter("ansid8"),request.getParameter("anstext1"),request.getParameter("anstext2"),
										   request.getParameter("anstext3"),request.getParameter("anstext4"),request.getParameter("anstext5"),
										   request.getParameter("anstext6"),request.getParameter("anstext7"),request.getParameter("anstext8"),
										   request.getParameter("iscorrectans1"),request.getParameter("iscorrectans2"),request.getParameter("iscorrectans3"),request.getParameter("iscorrectans4"),
										   request.getParameter("iscorrectans5"),request.getParameter("iscorrectans6"),request.getParameter("iscorrectans7"),
										   request.getParameter("iscorrectans8"));
			
			if(flag) {
				response.sendRedirect(request.getContextPath()+"/jsp/homepage.jsp");
			} else {
				response.sendRedirect(request.getContextPath()+"/jsp/error.jsp");
			}
			
	    }
		else if(action.equals("createHWForm")) {
			if(request.getParameter("hw").equals("new")) {
				HashMap<String, String> topics=ProfDao.fetchtopics();
				request.setAttribute("topics", topics);
				RequestDispatcher view = request.getRequestDispatcher("/jsp/newHW.jsp");
				view.forward(request, response);
			}
			else {
				System.out.println(request);
				String courseID = request.getParameter("courseID");
				ArrayList<String> hwID = dao.fetchHW(courseID);
				HashMap<String,ArrayList<String>>questions=new HashMap<>();
				if(hwID == null) {
					response.sendRedirect(request.getContextPath()+"/jsp/error.jsp");
				}
				else {
					ArrayList<String> qSetList = new ArrayList<String>();
					for(String hwid : hwID) {
						qSetList = dao.fetchQSets(hwid);
						
						for(String qSetId:qSetList){
							ArrayList<String> qBanks=dao.fetchQuestions(qSetId,hwid);
							questions.put(qSetId,qBanks);
							
							System.out.println("questions :" + questions);
					}
					
//					System.out.println("hwd size : "+hwID.size()+" qsetlist size : "+qSetList.size());
//					for(String hwid:hwID) {
//						
//							System.out.println("hwid = "+hwid+" qsetid = "+qSetId);
//							
//						  
//						}
						
					}
					System.out.println("hashmap"+questions.get('1'));
					request.setAttribute("questions", questions);
					response.sendRedirect(request.getContextPath()+"/jsp/existingHW.jsp");
				}
			}
		}
		else if(action.equals("newHW")) {
			System.out.println("TOPICS : "+request.getParameter("topics"));
			String newHWParams[] = new String[50];
			HashMap<String, String> qList = ProfDao.getQuestions(request.getParameter("topics"));
			newHWParams[0] = request.getParameter("hwID");
			newHWParams[1] = request.getParameter("hwName");
			newHWParams[2] = request.getParameter("courseID");
			newHWParams[3] = request.getParameter("topics");
			newHWParams[4] = request.getParameter("noOfRetries");
			newHWParams[5] = request.getParameter("startDate");
			newHWParams[6] = request.getParameter("endDate");
			newHWParams[7] = request.getParameter("pointsPerQuestion");
			newHWParams[8] = request.getParameter("penaltyPoints");
			newHWParams[9] = request.getParameter("hwType");
			
			request.setAttribute("newHWParams", newHWParams);
			request.setAttribute("qList", qList);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/selectQuestions.jsp");
			view.forward(request, response);
		}
		else if(action.equals("addQuestionSet")) {
            String q_id[] = request.getParameterValues("question");
            System.out.println("QID1: "+q_id[0]);
            String newHWParams[] = request.getParameterValues("hiddenParams");
            for(String i : newHWParams) {
            		System.out.println("newHWParams: "+i);
            }
            
            if(!dao.existsInHWTable(newHWParams[0])) {
            		dao.insertHWTable(newHWParams[0],newHWParams[1],newHWParams[5],newHWParams[6],newHWParams[4],newHWParams[8],newHWParams[7],q_id.length,newHWParams[9]);
            		dao.insertCourseHWTable(newHWParams[0],newHWParams[2]);
            }
            int maxQsetNo = 0;
            if(dao.fetchMaxQsetID(newHWParams[0]) != -1) {
            		maxQsetNo = dao.fetchMaxQsetID(newHWParams[0]) + 1;
            }
            else {
            		maxQsetNo = 1;
            }
			dao.insertQsetTable(newHWParams[0],maxQsetNo);
			for(String qid : q_id) {
				dao.insertHWQsetQbank(newHWParams[0],maxQsetNo,qid);
			}
			response.sendRedirect(request.getContextPath()+"/jsp/homepage.jsp");
			
		}
		else if(action.equals("viewReport")){
			
			ResultSet rs=dao.viewEnrolledStudents(request.getParameter("c_id"));
			request.setAttribute("rs", rs);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/viewStudentListing.jsp");
			view.forward(request, response);
			
		}
		else if(action.equals("viewStudentReport")) {
			System.out.println(request.getParameter("u_id")+" "+request.getParameter("c_id"));
			ResultSet rs=dao.viewStudentReport(request.getParameter("u_id"),request.getParameter("c_id"));
			request.setAttribute("rs", rs);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/viewStudentReport.jsp");
			view.forward(request, response);
		}
		else if(action.equals("viewHomeworks")) {
			String c_id=request.getParameter("c_id");
			ResultSet rs=dao.viewHomeworks(c_id);
			request.setAttribute("rs", rs);
			request.setAttribute("c_id", c_id);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/viewExercises.jsp");
			view.forward(request, response);
		}
		else if(action.equals("viewHomeworksInside")) {
			String hw_id=request.getParameter("hw_ID");
			System.out.println("hwidfirsttttt"+hw_id);
			ArrayList<ResultSet> results = dao.fetchHWQuestions(hw_id);
			request.setAttribute("results", results);
			RequestDispatcher view = request.getRequestDispatcher("/jsp/viewHomeworksInside.jsp");
			view.forward(request, response);
			
		}
			
			
		}
	}


