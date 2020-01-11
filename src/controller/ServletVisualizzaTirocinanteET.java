package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tirocinante;
import model.Tirocinio;
import model.DAO.TirocinanteDAO;
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletVisualizzaTirocinanteET
 */
@WebServlet("/ServletVisualizzaTirocinanteET")
public class ServletVisualizzaTirocinanteET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ServletVisualizzaTirocinanteET() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

		 //Istanzio il Dao e il Tirocinio
	    TirocinanteDAO dao = new TirocinanteDAO();
	    TirocinioDAO daoTirocinio = new TirocinioDAO();
	    Tirocinante tirocinante = new Tirocinante();
	    Tirocinio tirocinio = new Tirocinio();
	    Tirocinio tempTirocinio = new Tirocinio();
		ArrayList<Tirocinio> listaRichiesteEnte=new ArrayList<Tirocinio>();
	    //ricerco il tirocinio che ha una matricola specifica
	    try {
	      String matricola = request.getParameter("matricola");
	      long matricolaLong = Long.parseLong(matricola); 
	      tirocinante = dao.ricercaTirocinanteByMatricola(matricolaLong);
	      listaRichiesteEnte = daoTirocinio.allTirocinioTirocinante(matricolaLong);
	      
	      int size = listaRichiesteEnte.size();
	      for (int j = 0; j < size; j++) {
	    	  tempTirocinio = listaRichiesteEnte.get(j);
	    	  if (tempTirocinio.getStatoTirocinio().equals("In attesa della Segreteria")) {
	    		  tirocinio=tempTirocinio;
	    	  }
	      }
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    
	    //Controllo se il tirocinante è vuoto
	    if(tirocinante != null)
	    {
	      //setto l'attributo tirocinante
	      request.getSession().setAttribute("tirocinante", tirocinante);
	      request.getSession().setAttribute("tirocinio", tirocinio);
	    }
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("_areaSecretary/VisualizzaTirocinanteET.jsp");
	    dispatcher.forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
