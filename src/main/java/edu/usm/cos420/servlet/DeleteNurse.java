package edu.usm.cos420.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.usm.cos420.DB.NurseTableGateway;
import edu.usm.cos420.model.Nurse;

/**
 * Servlet implementation class AddNurse
 */
@WebServlet("/DeleteNurse")
public class DeleteNurse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNurse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession();
        ServletContext sc = getServletContext();
        RequestDispatcher rd;
		
        ArrayList<Nurse> nurses = new ArrayList<>();
		try {
			nurses = NurseTableGateway.getAllNurses();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        if(request.getParameter("nurseId") == null) {
        		request.setAttribute("nursesList", nurses);
        		rd = sc.getRequestDispatcher("/ListNursesToDelete.jsp");
        		rd.forward(request, response);
        }
        
		String strId=request.getParameter("nurseId");  
		int id=Integer.parseInt(strId.trim());
	
		
	    try {
			boolean success = NurseTableGateway.removeFromNursesTable(id);
			
			request.setAttribute("deletedSuccess", success);
			request.setAttribute("deletedNurse", nurses.get(id));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	   rd = sc.getRequestDispatcher("/DeleteNurse.jsp");

        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
