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
 * Servlet implementation class ServletGestioneRichiesteSegreteriaET
 */
@WebServlet("/ServletGestioneRichiesteSegreteriaET")
public class ServletGestioneRichiesteSegreteriaET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestioneRichiesteSegreteriaET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TirocinioDAO tirocinio= new TirocinioDAO();  
		//Array list di Tirocini
		ArrayList<Tirocinio> listaTirocini=new ArrayList<Tirocinio>();
		//Ricerco tutti gli 'EntiConvenzionati' e li inserisco nella listaTirocini
		try {
			listaTirocini=tirocinio.allTirocinioByStato("In attesa della Segreteria");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//Controllo se la Lista non e' vuota
		
		if(listaTirocini!=null)
		{
			//Assegno alla richiesta la 'listaTirocini'
			request.setAttribute("listaTirocini", listaTirocini);
		}
		
		String pag = "_areaSecretary/VisualizzaRichiestaET.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
