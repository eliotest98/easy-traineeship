package controller;

import interfacce.UserInterface;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.TirocinioDAO;
import model.Tirocinio;

/**
 * Servlet implementation class ServletGestioneRichiesteSegreteriaET.
 */
@WebServlet("/ServletProgettoFormativoET")
public class ServletProgettoFormativoET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletProgettoFormativoET() {
    super();
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    UserInterface user = (UserInterface) request.getSession().getAttribute("user");
    // String stato=(String) request.getAttribute("stato");
    TirocinioDAO tirocinioDao = new TirocinioDAO();

    Tirocinio tirocinio = new Tirocinio();
    /*
     * Richiamo la funzione di tirocinioDAO:
     * allTirocinioByDocumento
     * e cerco i tirocini con lo stato
     * "Accettato e in attesa di firma"
     * */
    tirocinio =
        tirocinioDao.allTirocinioByDocumento(user.getEmail(), "Accettato e in attesa di firma");
    String pag = "";
    String result = "";
    if (tirocinio != null) {
      // Assegno alla richiesta la 'listaTirocini'
      request.setAttribute("tirocinio", tirocinio);
      result = "ok";
      request.setAttribute("result", result);

    } else {

      result = "nook";
      request.setAttribute("tirocinio", tirocinio);
      request.setAttribute("result", result);

    }

    pag = "_areaStudent/UploadProgettoFormativoET.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
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
