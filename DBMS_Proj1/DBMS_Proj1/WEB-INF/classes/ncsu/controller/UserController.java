package ncsu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import ncsu.dao.UserDao;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = {"", "/UserController"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
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
		
		UserDao dao = new UserDao();
		
		if(action == "" || action == null) {
			response.sendRedirect(request.getContextPath()+"/jsp/homepage.jsp");
		}
		else if(action.equals("add")) {
			response.sendRedirect(request.getContextPath()+"/jsp/addproduct.jsp");
		}
		else if(action.equals("login")) {
			System.out.println("COMING INTO TEACHERS 1");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			ResultSet rs = dao.authenticate(username, password);
			
			if(rs != null) {
				try {
					System.out.println("COMING INTO TEACHERS 2");
					if(rs.isBeforeFirst())
					{
						System.out.println("yessssssssssss");  
					while(rs.next()) {
						System.out.println(rs.getString(1));
						System.out.println(rs.getInt("USER_TYPE"));
						System.out.println(rs.getString("F_NAME"));
						System.out.println(rs.getString("L_NAME"));
						if(rs.getInt("USER_TYPE") == 1) {
							
							//go to teacher homepage
							System.out.println("COMING INTO TEACHERS 3");
							request.setAttribute("rs", rs);
							request.setAttribute("type", "Instructor");
							
							RequestDispatcher view = request.getRequestDispatcher("/jsp/profHomePage.jsp");
							view.forward(request, response);
							
						}
						else {
							String studType = dao.checkStudentType(username);
							
							System.out.println("STUDENT TYPE = " + studType);
							
							if(studType.equals("ta")) {
								System.out.println("COMING INTO TA");
								request.setAttribute("rs", rs);
								request.setAttribute("type", "TA");
								RequestDispatcher view = request.getRequestDispatcher("/jsp/taHomePage.jsp");
								view.forward(request, response);
							}
							//check TA
							else {
								request.setAttribute("rs", rs);
								request.setAttribute("type", "Student");
								RequestDispatcher view = request.getRequestDispatcher("/jsp/studHomePage.jsp");
								view.forward(request, response);
							}
						}
					}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	}

}
