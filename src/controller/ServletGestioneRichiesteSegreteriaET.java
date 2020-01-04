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
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletGestioneRichiesteSegreteriaET
 */
@WebServlet("/ServletGestioneRichiesteSegreteriaET")
public class ServletGestioneRichiesteSegreteriaET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ServletGestioneRichiesteSegreteriaET() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int flag = Integer.parseInt(request.getParameter("flag"));
    if (flag == 1) {
      TirocinioDAO tirocinio = new TirocinioDAO();
      // Array list di Tirocini
      ArrayList<Tirocinio> listaTirocini = new ArrayList<Tirocinio>();
      // Ricerco tutti gli 'EntiConvenzionati' e li inserisco nella listaTirocini
      try {
        listaTirocini = tirocinio.allTirocinioByStato("In attesa della Segreteria");
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Controllo se la Lista non e' vuota

      if (listaTirocini != null) {
        // Assegno alla richiesta la 'listaTirocini'
        request.setAttribute("listaTirocini", listaTirocini);
      }

      String pag = "_areaSecretary/VisualizzaRichiestaET.jsp";

      RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
      dispatcher.forward(request, response);
    }
    if (flag == 2) {
      TirocinioDAO tirocinio = new TirocinioDAO();
      // Array con il 'tirocinio'
      ArrayList<Tirocinio> listaTirocini = new ArrayList<Tirocinio>();
      // Boolean per sapere se e stato settato o meno
      boolean set = false;
      // Update dello stato del tironio
      try {
        String matricola = request.getParameter("matricola");
        long matricolaLong = Long.parseLong(matricola);
        listaTirocini = tirocinio.allTirocinioTirocinante(matricolaLong);
        for (int i = 0; i < listaTirocini.size(); i++) {
          if (listaTirocini.get(i).getStatoTirocinio()
              .equalsIgnoreCase("In attesa della Segreteria")) {
            set = tirocinio.modificaStatoTirocinio(listaTirocini.get(i).getCodTirocinio(),
                "In attesa dell Ente");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Controllo se è stato effettuato
      if (set) {
        String pag = "_areaSecretary/VisualizzaRichiestaET.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
        dispatcher.forward(request, response);
      } else {
        String pag = "_areaSecretary/ModificaEnteET.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
        dispatcher.forward(request, response);
      }
    }
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
