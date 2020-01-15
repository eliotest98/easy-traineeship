package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.EnteConvenzionatoDAO;
import model.EnteConvenzionato;

@WebServlet("/ServletListaEnteET")
public class ServletListaEnteET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ServletListaEnteET() {
    super();
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	//Istanzio EnteConvenzionatoDAO
    EnteConvenzionatoDAO ente = new EnteConvenzionatoDAO();
    // Array list di Enti convenzionati
    ArrayList<EnteConvenzionato> listaEnti = new ArrayList<EnteConvenzionato>();
    // Ricerco tutti gli 'EntiConvenzionati' e li inserisco nella listaEnti
    try {
      listaEnti = ente.allEnte();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Controllo se la Lista non e' vuota

    if (listaEnti != null) {
      // Assegno alla richiesta la 'listaEnti'
      request.setAttribute("listaEnti", listaEnti);
    }

    String pag = null;
    
    /*
     * Se la richiesta è null indirizza a: VisualizzaEnteET.jsp
     * altrimenti: InviaRichiestaEnteET.jsp
     */
    if (request.getParameter("richiestaEnte") != null) {
      pag = "_areaStudent/InviaRichiestaEnteET.jsp";
    } else {
      pag = "VisualizzaEnteET.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
    dispatcher.forward(request, response);
  }

  /**
   * Method doPost(). * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   * response)
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);

  }
}
