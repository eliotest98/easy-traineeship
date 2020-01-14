package controller;

import interfacce.UserInterface;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.EnteConvenzionatoDAO;
import model.DAO.TirocinioDAO;
import model.Tirocinio;

/**
 * Servlet implementation class ServletDocumentiTirocinioET.
 */
@WebServlet("/ServletDocumentiTirocinioET")
public class ServletDocumentiTirocinioET extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final TirocinioDAO tirocinioDaO = new TirocinioDAO();
  private final EnteConvenzionatoDAO enteconvenzionatoDaO = new EnteConvenzionatoDAO();

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletDocumentiTirocinioET() {
    super();
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String userET = (String) request.getSession().getAttribute("userET");
    if (userET == null) {
      response.sendRedirect("login.jsp");
      return;
    }

    ArrayList<Tirocinio> listaTirocinio = new ArrayList<Tirocinio>();
    String statoTirocinio = "";

    // Se sono l'admin
    if (userET.equals("2")) {
      statoTirocinio = "Accettato e in attesa di firma Admin";
      listaTirocinio.addAll(tirocinioDaO.allTirocinioByStato(statoTirocinio));
    }
    // Se sono ente
    if (userET.equals("3")) {
      UserInterface user = (UserInterface) request.getSession().getAttribute("user");
      String email = user.getEmail();
      String partitaIva = "";
      try {
        partitaIva = (enteconvenzionatoDaO.ricercaEnteByEmail(email)).getPartitaIva();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      listaTirocinio = tirocinioDaO.allDocumentiDaFirmareByEnte(partitaIva, statoTirocinio);
      statoTirocinio = "Accettato e in attesa di firma Ente e Admin";
      listaTirocinio.addAll(tirocinioDaO.allDocumentiDaFirmareByEnte(partitaIva, statoTirocinio));
    }
    request.setAttribute("listaTirocinio", listaTirocinio);

    RequestDispatcher dispatcher = request.getRequestDispatcher("DocumentiET.jsp");// Controlla jsp
    dispatcher.forward(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
