package edu.usm.cos420.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.usm.cos420.model.Nurse;

/**
 * Servlet implementation class DisplayNurses
 */
@WebServlet("/DisplayNurses")
public class DisplayNurses extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DisplayNurses() {
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		List<Nurse> nurseList = new ArrayList<Nurse>();
        Nurse nurse1 = new Nurse();
        nurse1.setId(1); nurse1.setLastName("Saviour");nurse1.setFirstName("Sally");nurse1.setCountryCode("Ghana");
        nurseList.add(nurse1);
        Nurse nurse2 = new Nurse();
        nurse2.setId(2); nurse2.setLastName("Nightengale");nurse2.setFirstName("Florence");nurse2.setCountryCode("Italy");
        nurseList.add(nurse2);
        HttpSession sess = request.getSession();
        ServletContext sc = getServletContext();
        request.setAttribute("nurseList", nurseList);
        request.setAttribute("hello", "world");
        sc.setAttribute("nurseList", nurseList);
        sc.setAttribute("hello", "world");
        sess.setAttribute("nurseList", nurseList);
        sess.setAttribute("hello", "world");
        RequestDispatcher rd = sc.getRequestDispatcher("/Nurses.jsp");
        rd.forward(request, response);	
     }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
		doGet(request, response);
	}

}
