package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.EnteConvenzionato;
import model.DAO.EnteConvenzionatoDAO;

@WebServlet("/ServletListaEnteET")
public class ServletListaEnteET extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public ServletListaEnteET()
	{
		super();
	}
	
	/**
	   * Method doGet().
	   * 
	   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	   */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintStream print = new PrintStream(System.out);
        print.println("aaaaa");
		EnteConvenzionatoDAO ente= new EnteConvenzionatoDAO();  
		//Array list di Enti convenzionati
		ArrayList<EnteConvenzionato> listaEnti=new ArrayList<EnteConvenzionato>();
		//Ricerco tutti gli 'EntiConvenzionati' e li inserisco nella listaEnti
		listaEnti=ente.allEnte();
		//Controllo se la Lista non � vuota
		System.out.println("servlet="+listaEnti);
		if(listaEnti!=null)
		{
			//Assegno alla richiesta la 'listaEnti'
			request.setAttribute("listaEnti", listaEnti);
		}
		String pagina= null;
		if(request.getParameter("richiestaEnte") != null) 
		{
			pagina= "InviaRichiestaEnteET.jsp";
//			RequestDispatcher dispatcher = request.getRequestDispatcher("InviaRichiestaEnteET.jsp");
//			dispatcher.forward(request, response);
		}else 
		{
			pagina = "VisualizzaEnteET.jsp";
//			RequestDispatcher dispatcher = request.getRequestDispatcher("VisualizzaEnteET.jsp");
//			dispatcher.forward(request, response);			
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
	  
	/**
	 * Method doPost(). * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
  
	}
}
