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
		EnteConvenzionatoDAO ente= new EnteConvenzionatoDAO();  
		//Array list di Enti convenzionati
		ArrayList<EnteConvenzionato> listaEnti=new ArrayList<EnteConvenzionato>();
		//Ricerco tutti gli 'EntiConvenzionati' e li inserisco nella listaEnti
		listaEnti=ente.allEnte();
		//Controllo se la Lista non � vuota
		
		if(listaEnti!=null)
		{
			//Assegno alla richiesta la 'listaEnti'
			request.setAttribute("listaEnti", listaEnti);
		}
		
		String pag = null;
		
		System.out.println("PARAMETRO" + request.getParameter("richiestaEnte"));
		
		if(request.getParameter("richiestaEnte")!=null) 
		{
			pag = "_areaStudent/InviaRichiestaEnteET.jsp";
		}
		else
		{
		  pag = "VisualizzaEnteET.jsp";
		}	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
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
