package ncsu.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ncsu.dao.ProfDao;
import ncsu.dao.TADao;

/**
 * Servlet implementation class TAController
 */
@WebServlet("/TAController")
public class TAController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TAController() {
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
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		
		TADao dao = new TADao();
		
		if(action.equals("viewProfile")) {
			System.out.println("TA ID : "+request.getParameter("USER_ID"));
			ResultSet rs = dao.fetchDetails(request.getParameter("USER_ID"));
			
			request.setAttribute("rs", rs);
			
			RequestDispatcher view = request.getRequestDispatcher("/jsp/profProfilePage.jsp");
			view.forward(request, response);
			
		}
		else if (action.equals("addStudentForm")) {
			  request.setAttribute("user_id",request.getParameter("USER_ID"));
			  request.setAttribute("ControllerType", "TA");
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
			  request.setAttribute("ControllerType", "TA");
			  RequestDispatcher view = request.getRequestDispatcher("/jsp/dropStudents.jsp");
				view.forward(request, response);
	    }
		else if(action.equals("dropStudent")) {
			boolean flag = dao.dropStudent(request.getParameter("studentID"), request.getParameter("courseID"));
			
			if (flag) {
				response.sendRedirect(request.getContextPath()+"/jsp/homepage.jsp");
			}
		}
		
	}

}
