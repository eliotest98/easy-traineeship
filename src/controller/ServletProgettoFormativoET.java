package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfacce.UserInterface;
import model.Tirocinante;
import model.Tirocinio;
import model.DAO.TirocinioDAO;
import model.DAO.EnteConvenzionatoDAO;
import model.DAO.TirocinanteDAO;

/**
 * Servlet implementation class ServletGestioneRichiesteSegreteriaET
 */
@WebServlet("/ServletProgettoFormativoET")
public class ServletProgettoFormativoET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProgettoFormativoET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserInterface user = (UserInterface)request.getSession().getAttribute("user");
		//String stato=(String) request.getAttribute("stato");
		TirocinioDAO tirocinioDao= new TirocinioDAO();

		Tirocinio tirocinio=new Tirocinio();
		tirocinio= tirocinioDao.allTirocinioByDocumento(user.getEmail(), "Accettato e in attesa di firma");

		if(tirocinio!=null)
		{
			//Assegno alla richiesta la 'listaTirocini'
			request.setAttribute("tirocinio", tirocinio);
		}
		
		  
		
		String pag = "_areaStudent/UploadProgettoFormativoET.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
