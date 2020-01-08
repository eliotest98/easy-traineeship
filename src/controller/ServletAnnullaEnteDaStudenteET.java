package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tirocinio;
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletAnnullaEnteDaStudenteET
 */
@WebServlet("/ServletAnnullaEnteDaStudenteET")
public class ServletAnnullaEnteDaStudenteET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ServletAnnullaEnteDaStudenteET() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doPost(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    /**
     * Controllo autenticazione tramite parametro in sessione (0 = Studente).
     */
    String userET = (String) request.getSession().getAttribute("userET");
    if ((userET == null) || (!userET.equals("0"))) {
      response.sendRedirect("login.jsp");
      return;
    }
    // recupero il codice tirocinio
    int codTirocinio = Integer.parseInt(request.getParameter("enteEmail"));
    // istanzio un Tirocinio
    Tirocinio tirocinioAttivo = new Tirocinio();
    // istanzio un TirocinioDao
    TirocinioDAO tirocinio = new TirocinioDAO();
    // istanzio un booleano per sapere se la modifica è andata a buon fine
    boolean set = false;
    // ustanzio un booleano per sapere se l'inserimento è andato a buon fine
    boolean ins = false;
    try {
      // recupero il tirocinio prima della modifica
      tirocinioAttivo = tirocinio.TirocinioByCodTirocinio(codTirocinio);
      // modifico lo stato del tirocinio attuale
      set = tirocinio.modificaStatoTirocinio(codTirocinio, "Rifiutato");
    } catch (Exception e) {
      e.printStackTrace();
    }
    // controllo se la modifica è andata a buon fine
    if (set == true) {
      // Creo un nuovo Tirocinio per passarmi solo alcuni dati
      Tirocinio tirocinioTemp = new Tirocinio();
      tirocinioTemp.setDataInizioTirocinio(tirocinioAttivo.getDataInizioTirocinio());
      tirocinioTemp.setCfuPrevisti(tirocinioAttivo.getCfuPrevisti());
      tirocinioTemp.setCompetenze(tirocinioAttivo.getCompetenze());
      tirocinioTemp.setCompetenzeAcquisire(tirocinioAttivo.getCompetenzeAcquisire());
      tirocinioTemp.setAttivitaPreviste(tirocinioAttivo.getAttivitaPreviste());
      tirocinioTemp.setSvolgimentoTirocinio(tirocinioAttivo.getSvolgimentoTirocinio());
      tirocinioTemp.setMatricola(tirocinioAttivo.getMatricola());
      try {
        // inserisco il nuovo tirocinio con i dati
        ins = tirocinio.inserisciTirocinio(tirocinioTemp);
      } catch (Exception e) {
        e.printStackTrace();
      }
      // controllo se l'inserimento è andato a buon fine
      if (ins == true) {
        String pag = "_areaStudent/StoricoStudenteET.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
        dispatcher.forward(request, response);
      } else {
        System.out.println("inserimento non eseguito con successo");
      }
    } else {
      System.out.println("cambio dello stato non eseguito con successo");
    }
  }
}
