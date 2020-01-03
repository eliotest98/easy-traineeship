package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;
import model.Tirocinante;
import model.Tirocinio;
import model.DAO.TirocinanteDAO;
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletStatoTirocinanteET
 */
@WebServlet("/ServletStatoTirocinanteET")
public class ServletStatoTirocinanteET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    TirocinioDAO tirocinioDao = new TirocinioDAO();
    TirocinanteDAO tirocinanteDao = new TirocinanteDAO();
    /* Cerco lo studente in sessione */
    Student s = (Student) request.getSession().getAttribute("user");
    if (s == null) {
      throw new IllegalArgumentException("Lo Studente non esiste");
    }
    /* Cerco il tirocinante corrispondente */
    Tirocinante tirocinante = tirocinanteDao.ricercaTirocinanteByEmail(s.getEmail());
    if (tirocinante != null) {
      tirocinante.setFacolta("Informatica");
      /* Cerco i Tirocini abbinati al Tirocinante in sessione */
      ArrayList<Tirocinio> tirocini = new ArrayList<Tirocinio>();
      tirocini =
          (ArrayList<Tirocinio>) tirocinioDao.allTirocinioTirocinante(tirocinante.getMatricola());
      request.getSession().setAttribute("Tirocinio", tirocini);
    }
    /* Schiaffo IL TIROCINANTE nella sessione */
    request.getSession().setAttribute("Tirocinante", tirocinante);

    RequestDispatcher dispatcher =
        request.getRequestDispatcher("/_areaStudent/StatoProprioTirocinioET.jsp");
    dispatcher.forward(request, response);
  }

}
