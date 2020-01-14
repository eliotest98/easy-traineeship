package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.TirocinanteDAO;
import model.Tirocinante;

/**
 * Servlet implementation class ServletVisualizzaListaTirocinantiET.
 */
@WebServlet("/ServletVisualizzaListaTirocinantiET")
public class ServletVisualizzaListaTirocinantiET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletVisualizzaListaTirocinantiET() {
    super();
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Controllo la sessione, se � la segreteria
    String u = (String) request.getSession().getAttribute("userET");

    if (!u.equalsIgnoreCase("1")) {
      throw new IllegalArgumentException("Errore nell'utilizzo della pagina");
    }
    TirocinanteDAO tirocinanteDaO = new TirocinanteDAO();

    // Prelevo tutti i tirocinanti
    ArrayList<Tirocinante> listaTirocinanti = tirocinanteDaO.allTirocinante();

    request.getSession().setAttribute("listaTirocinanti", listaTirocinanti);

    // Ritorno alla pagina
    RequestDispatcher disp =
        request.getRequestDispatcher("/_areaSecretary/VisualizzaListaTirocinantiET.jsp");
    disp.forward(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
