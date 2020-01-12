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
import model.DAO.TirocinanteDAO;

/**
 * Servlet implementation class ServletVisualizzaListaTirocinantiET
 */
@WebServlet("/ServletVisualizzaListaTirocinantiET")
public class ServletVisualizzaListaTirocinantiET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ServletVisualizzaListaTirocinantiET() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // Controllo la sessione, se � la segreteria
    String u = (String) request.getSession().getAttribute("userET");

    if (!u.equalsIgnoreCase("1")) {
      throw new IllegalArgumentException("Errore nell'utilizzo della pagina");
    }
    TirocinanteDAO tirocinanteDAO = new TirocinanteDAO();

    // Prelevo tutti i tirocinanti
    ArrayList<Tirocinante> listaTirocinanti = tirocinanteDAO.allTirocinante();

    request.getSession().setAttribute("listaTirocinanti", listaTirocinanti);

    // Ritorno alla pagina
    RequestDispatcher disp =
        request.getRequestDispatcher("/_areaSecretary/VisualizzaListaTirocinantiET.jsp");
    disp.forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
