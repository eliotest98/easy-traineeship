package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tirocinio;
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletAnnullaTirocinioET
 */
@WebServlet("/ServletAnnullaTirocinioET")
public class ServletAnnullaTirocinioET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAnnullaTirocinioET() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	  TirocinioDAO tirocinioDAO = new TirocinioDAO();
	  //Prelevo il codice del Tirocinio
	  int cod = Integer.parseInt(request.getParameter("cod"));
	  //Cerco il tirocinio con codice cod e settop lo stato in Annullato
	  if(tirocinioDAO.modificaStatoTirocinio(cod, "Annullato")==false)
	  {
	    request.getSession().setAttribute("risp", "no");
	  }
	  else
	  {
	    request.getSession().setAttribute("risp", "si");
	  }
	  //Rimando alla pagina 
	  RequestDispatcher disp = request.getRequestDispatcher("/_areaSecretary/VisualizzaStatoTirocinioET.jsp");
	  disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
