package controller;

import java.io.IOException;
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
		  
		ArrayList<EnteConvenzionato> listaEnti=new ArrayList<EnteConvenzionato>();
		listaEnti=ente.allEnte();
		if(listaEnti!=null)
		{
			request.setAttribute("listaEnti", listaEnti);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("VisualizzaEnteET.jsp");
		dispatcher.forward(request, response);
		
	}
	  
	/**
	 * Method doPost(). * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		//doGet(request, response);
		
		
		  
	}
}
