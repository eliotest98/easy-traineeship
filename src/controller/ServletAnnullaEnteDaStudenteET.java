package controller;

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
 * Servlet implementation class ServletAnnullaEnteDaStudenteET.
 */
@WebServlet("/ServletAnnullaEnteDaStudenteET")
public class ServletAnnullaEnteDaStudenteET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletAnnullaEnteDaStudenteET() {
    super();
  }

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
    
    //Controllo autenticazione tramite parametro in sessione (0 = Studente).
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
    // istanzio un booleano per sapere se la modifica � andata a buon fine
    boolean set = false;
    // ustanzio un booleano per sapere se l'inserimento � andato a buon fine
    boolean ins = false;
    try {
      // recupero il tirocinio prima della modifica
      tirocinioAttivo = tirocinio.TirocinioByCodTirocinio(codTirocinio);
      // modifico lo stato del tirocinio attuale
      set = tirocinio.modificaStatoTirocinio(codTirocinio, "Rifiutato");
    } catch (Exception e) {
      e.printStackTrace();
    }
    // controllo se la modifica � andata a buon fine
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
      // controllo se l'inserimento � andato a buon fine
      if (ins == true) {
        String pag = "_areaStudent/StoricoStudenteET.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(pag);
        dispatcher.forward(request, response);
      } else {
    	  /*
    	   * se l'inserimento non e' andato
    	   * a buon fine lancia 
    	   * l'eccezione
    	   */
        throw new IllegalArgumentException("inserimento non eseguito con successo");
      }
    } else {
  	  /*
  	   * se il cambio stato non e' andato
  	   * a buon fine lancia 
  	   * l'eccezione
  	   */
      throw new IllegalArgumentException("cambio dello stato non eseguito con successo");
    }
  }
}
