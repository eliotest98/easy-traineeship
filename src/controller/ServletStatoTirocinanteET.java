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
import model.DAO.TirocinioDAO;
import model.Student;
import model.Tirocinante;
import model.Tirocinio;

/**
 * Servlet implementation class ServletStatoTirocinanteET.
 */
@WebServlet("/ServletStatoTirocinanteET")
public class ServletStatoTirocinanteET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    TirocinioDAO tirocinioDao = new TirocinioDAO();
    TirocinanteDAO tirocinanteDao = new TirocinanteDAO();

    // Cerco lo studente in sessione
    Student s = (Student) request.getSession().getAttribute("user");
    // Se non esiste, lancia eccezione
    if (s == null) {
      throw new IllegalArgumentException("Lo Studente non esiste");
    }
    /* Cerco il tirocinante corrispondente */
    Tirocinante tirocinante = tirocinanteDao.ricercaTirocinanteByEmail(s.getEmail());

    if (tirocinante == null) {
      throw new IllegalArgumentException("Non esiste alcun Tirocinante.");
    }

    if (request.getParameter("pagina").equalsIgnoreCase("StatoProprioTirocinioET")) {
      if (tirocinante != null) {
        try {
          // Se non è null, setto informatica nella facoltà
          tirocinante.setFacolta("Informatica");
          // Cerco il tiocinio attivo del tirocinante
          Tirocinio tirocinio = tirocinioDao.tirocinioAttivo(tirocinante.getMatricola());
          // per sicurezza,risetto il tirocinante
          tirocinio.setTirocinante(tirocinante);
          // Metto il tirocinio in sessione
          request.getSession().setAttribute("Tirocinio", tirocinio);
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      // Metto il tirocinante in sessione
      request.getSession().setAttribute("Tirocinante", tirocinante);

      RequestDispatcher dispatcher =
          request.getRequestDispatcher("/_areaStudent/StatoProprioTirocinioET.jsp");
      dispatcher.forward(request, response);
    } else if (request.getParameter("pagina").equalsIgnoreCase("StoricoStudenteET")) {
      if (tirocinante != null) {
        // Se non è null, setto informatica nella facoltà
        tirocinante.setFacolta("Informatica");
        // Cerco il tiocinio attivo del tirocinante
        ArrayList<Tirocinio> listaTirocini =
            (ArrayList<Tirocinio>) tirocinioDao.allTirocinioTirocinante(tirocinante.getMatricola());
        // Metto la lisat dei tirocini in sessione
        request.getSession().setAttribute("listaTirocini", listaTirocini);
      }
      // Metto il tirocinante in sessione
      request.getSession().setAttribute("Tirocinante", tirocinante);
      // Vado alla pagina dello storico
      RequestDispatcher dispatcher =
          request.getRequestDispatcher("/_areaStudent/StoricoStudenteET.jsp");
      dispatcher.forward(request, response);
    } else {
      throw new IllegalArgumentException("Pagina non trovata");
    }
  }

}
