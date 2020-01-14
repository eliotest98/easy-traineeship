package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet("/ServletGestioneRichiesteSegreteriaET")
public class ServletGestioneRichiesteSegreteriaET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Construct.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletGestioneRichiesteSegreteriaET() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    //Controllo autenticazione tramite parametro in sessione (1 = Segreteria).
    String userET = (String) request.getSession().getAttribute("userET");
    if ((userET == null) || (!userET.equals("1"))) {
      response.sendRedirect("login.jsp");
      return;
    }

    // <-------- Sezione Lista ------------>
    TirocinioDAO tirocinio2 = new TirocinioDAO();
    // Array list di Tirocini
    ArrayList<Tirocinio> listaTirocini2 = new ArrayList<Tirocinio>();
    // Ricerco tutti gli 'EntiConvenzionati' e li inserisco nella listaTirocini
    try {
      listaTirocini2 = tirocinio2.allTirocinioByStato("In attesa della Segreteria");
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Controllo se la Lista non e' vuota

    if (listaTirocini2 != null) {
      // Assegno alla richiesta la 'listaTirocini'
      request.setAttribute("listaTirocini", listaTirocini2);
    }

    String pag = "_areaSecretary/VisualizzaRichiestaET.jsp";

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
    
    //Controllo autenticazione tramite parametro in sessione (1 = Segreteria).
    String userET = (String) request.getSession().getAttribute("userET");
    if ((userET == null) || (!userET.equals("1"))) {
      response.sendRedirect("login.jsp");
      return;
    }

    int flag = Integer.parseInt(request.getParameter("flag"));
    long matricola = Long.parseLong(request.getParameter("matricola"));
    // boolean che controlla se la modifica � stata fatta
    boolean set = false;
    boolean set2 = false;
    // <----------- Accetta Richiesta ----------->
    if (flag == 2) {
      TirocinioDAO tirocinio = new TirocinioDAO();
      // Array list di Tirocini
      ArrayList<Tirocinio> listaTirocini = new ArrayList<Tirocinio>();
      // Ricerco tutti gli 'EntiConvenzionati' e li inserisco nella listaTirocini
      try {
        // Prelevo la matricola
        listaTirocini = tirocinio.allTirocinioTirocinante(matricola);
        // Ricerco fra i Tirocini quelli In attesa della Segreteria con uno specifico codice
        // tirocinio
        for (int i = 0; i < listaTirocini.size(); i++) {
          if (listaTirocini.get(i).getStatoTirocinio()
              .equalsIgnoreCase("In attesa della Segreteria")) {
            // modifico lo stato se lo trovo
            set = tirocinio.modificaStatoTirocinio(listaTirocini.get(i).getCodTirocinio(),
                "In attesa Ente");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (set) {
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.println(true);
        out.close();
        return;
      } else {
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        PrintWriter out = response.getWriter();
        out.println(false);
        out.close();
        return;
      }
    }
    // <--------- Rifiuta Richiesta --------->
    if (flag == 3) {
      TirocinioDAO tirocinio3 = new TirocinioDAO();
      // Array list di Tirocini
      ArrayList<Tirocinio> listaTirocini3 = new ArrayList<Tirocinio>();
      // Ricerco tutti gli 'EntiConvenzionati' e li inserisco nella listaTirocini
      try {
        // Prelevo la motivazione
        String motivazione = request.getParameter("motivazione");
        listaTirocini3 = tirocinio3.allTirocinioTirocinante(matricola);
        // Ricerco fra i Tirocini quelli In attesa della Segreteria con uno specifico codice
        // tirocinio
        for (int i = 0; i < listaTirocini3.size(); i++) {
          if (listaTirocini3.get(i).getStatoTirocinio()
              .equalsIgnoreCase("In attesa della Segreteria")) {
            // Modifico lo stato in caso lo trovo
            set = tirocinio3.modificaStatoTirocinio(listaTirocini3.get(i).getCodTirocinio(),
                "Annullato");
            set2 = tirocinio3.modificaDescrizioneEnteTirocinio(
                listaTirocini3.get(i).getCodTirocinio(), motivazione);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (set) {
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.println(true);
        out.close();
        return;
      } else {
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        PrintWriter out = response.getWriter();
        out.println(false);
        out.close();
        return;
      }
    }
  }
}
