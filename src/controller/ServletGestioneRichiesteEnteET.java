package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tirocinio;
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class SerlvetGestioneRichiesteEnteET
 */
@WebServlet("/ServletGestioneRichiesteEnteET")
public class ServletGestioneRichiesteEnteET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestioneRichiesteEnteET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /**
	     * Controllo autenticazione tramite parametro in sessione (3 = EnteConvenzionato).
	     */
	    String userET = (String) request.getSession().getAttribute("userET");
	    if ((userET == null) || (!userET.equals("3"))) {
	      response.sendRedirect("login.jsp");
	      return;
	    }
	    
		//Visualizza Lista Richieste
		TirocinioDAO richiestaEnte= new TirocinioDAO();  
		//Array list di Tirocinio
		ArrayList<Tirocinio> listaRichiesteEnte=new ArrayList<Tirocinio>();
		//Ricerco tutte le richieste all'ente e li inserisco nella listaRichiesteEnte
		try {
			listaRichiesteEnte=richiestaEnte.allTirocinioByStato("In attesa dell'Ente");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//Controllo se la Lista non e' vuota
		
		if(listaRichiesteEnte!=null)
		{
			//Assegno alla richiesta la 'listaEnti'
			request.setAttribute("listaRichiesteEnte", listaRichiesteEnte);
		}
		
		String pag = "VisualizzaRichiestaEnteET.jsp";
		
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
