package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tirocinante;
import model.Tirocinio;
import model.DAO.EnteConvenzionatoDAO;
import model.DAO.TirocinanteDAO;

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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //Istanzio il Dao e il Tirocinio
    TirocinanteDAO dao = new TirocinanteDAO();
    Tirocinante tirocinante = new Tirocinante();
    //ricerco il tirocinio che ha una matricola specifica
    try {
      String matricola = request.getParameter("matricola");
      long matricolaLong = Long.parseLong(matricola); 
      tirocinante = dao.ricercaTirocinanteByMatricola(matricolaLong);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    //Controllo se il tirocinante è vuoto
    if(tirocinante != null)
    {
      //setto l'attributo tirocinante
      request.setAttribute("tirocinante", tirocinante);
    }
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("_areaSecretary/VisualizzaTirocinanteET.jsp");
    dispatcher.forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
