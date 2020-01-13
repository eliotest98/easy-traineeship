package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import model.Tirocinante;
import model.Tirocinio;
import model.DAO.TirocinanteDAO;
import model.DAO.TirocinioDAO;
import model.Student;

/**
 * Servlet implementation class ServletRichiestaInizioTirocinio
 */
@WebServlet("/ServletRichiestaInizioTirocinioET")
public class ServletRichiestaInizioTirocinioET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ServletRichiestaInizioTirocinioET() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    /**
     * Controllo autenticazione tramite parametro in sessione (0 = Studente ET alias Tirocinante).
     */
    Tirocinante tirocinante = new Tirocinante();
    Tirocinio tirocinio = new Tirocinio();
    String userET = (String) request.getSession().getAttribute("userET");
    Student s = (Student) request.getSession().getAttribute("user");

    if ((userET == null) || (!userET.equals("0"))) {
      response.sendRedirect("login.jsp");
      return;
    }

    /* Controllo TEST CASE, messaggi */
    // Controllo nome
    tirocinante.setName(s.getName());
    if (s.getName() == null || s.getName().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Nome' &egrave vuoto");
    }

    if (s.getName().length() > 50) {
      throw new IllegalArgumentException("Il campo 'Nome' supera la lunghezza consentita");
    }

    if (!(s.getName().matches("^[A-Z a-z]+$"))) {
      throw new IllegalArgumentException("Il campo 'Nome' non rispetta il formato");
    }
    // Controllo cognome
    tirocinante.setSurname(s.getSurname());
    if (s.getSurname() == null || s.getSurname().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Cognome' &egrave vuoto");
    }

    if (s.getSurname().length() > 50) {
      throw new IllegalArgumentException("Il campo 'Cognome' supera la lunghezza consentita");
    }

    if (!(s.getSurname().matches("^[A-Z a-z]+$"))) {
      throw new IllegalArgumentException("Il campo 'Cognome' non rispetta il formato");
    }
    // Controllo matricola

    if (request.getParameter("matricolaTirocinante") == null
        || request.getParameter("matricolaTirocinante").length() == 0) {
      throw new IllegalArgumentException("Il campo 'Matricola' &egrave vuoto");
    }

    if (request.getParameter("matricolaTirocinante").length() != 10) {
      throw new IllegalArgumentException("Il campo 'Matricola' supera la lunghezza consentita");
    }

    if (!(request.getParameter("matricolaTirocinante").matches("^[0-9]+$"))) {
      throw new IllegalArgumentException("Il campo 'Matricola' non rispetta il formato");
    }
    tirocinante.setMatricola(Long.parseLong(request.getParameter("matricolaTirocinante")));
    // Controllo - assegnazione facolt�
    tirocinante.setFacolta("Informatica");
    /*
     * if(tirocinante.getFacolta() == null || tirocinante.getFacolta().length() == 0) { throw new
     * IllegalArgumentException("Il campo 'Facolt&agrave' &grave vuoto"); }
     * 
     * if(tirocinante.getFacolta().length() > 50) { throw new
     * IllegalArgumentException("Il campo facolt� supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato."
     * ); }
     * 
     * if(!(tirocinante.getFacolta().matches("^[A-Z a-z]+$"))) { throw new
     * IllegalArgumentException("Il campo facolt� non rispetta il formato. L'invio della richiesta non viene effettuato."
     * ); }
     */
    // Controllo data di nascita
    /* Ci ho messo tre anni, ma sono riuscita a convertire da string a DATE */
    GregorianCalendar g = new GregorianCalendar();
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
    String dat = request.getParameter("dataDiNascita");

    try {
      g.setTime((Date) formatter1.parseObject(dat));

      java.sql.Date data = new java.sql.Date(g.getTimeInMillis());
      tirocinante.setDataNascita(data);

    } catch (ParseException e) {
      e.printStackTrace();
    }

    if (tirocinante.getDataNascita() == null) {
      throw new IllegalArgumentException("Il campo 'Data di Nascita' &egrave vuoto");
    }

    // Controllo luogo di nascita
    tirocinante.setLuogoNascita(request.getParameter("luogoDiNascita"));
    if (tirocinante.getLuogoNascita() == null || tirocinante.getLuogoNascita().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Luogo di Nascita' &egrave vuoto");
    }

    if (tirocinante.getLuogoNascita().length() > 64) {
      throw new IllegalArgumentException(
          "Il campo 'Luogo di Nascita' supera la lunghezza consentita");
    }

    if (!(tirocinante.getLuogoNascita().matches("^[A-Z a-z]+$"))) {
      throw new IllegalArgumentException("Il campo 'Luogo di Nascita' non rispetta il formato");
    }
    // Controllo cittadinanza
    tirocinante.setCittadinanza(request.getParameter("cittadinanza"));
    if (tirocinante.getCittadinanza() == null || tirocinante.getCittadinanza().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Cittadinanza' &egrave vuoto");
    }

    if (tirocinante.getCittadinanza().length() > 64) {
      throw new IllegalArgumentException("Il campo 'Cittadinanza' supera la lunghezza consentita");
    }

    if (!(tirocinante.getCittadinanza().matches("^[A-Za-z]+$"))) {
      throw new IllegalArgumentException("Il campo 'Cittadinanza' non rispetta il formato");
    }
    // Controllo residenza
    tirocinante.setResidenza(request.getParameter("residenza"));
    if (tirocinante.getResidenza() == null || tirocinante.getResidenza().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Residenza' &egrave vuoto");
    }

    if (tirocinante.getResidenza().length() > 64) {
      throw new IllegalArgumentException("Il campo 'Residenza' supera la lunghezza consentita");
    }

    if (!(tirocinante.getResidenza().matches("^[A-Z a-z 0-9]+$"))) {
      throw new IllegalArgumentException("Il campo 'Residenza' non rispetta il formato");
    }
    // Controllo codice fiscale
    tirocinante.setCodiceFiscale(request.getParameter("codiceFiscale").toUpperCase());
    if (tirocinante.getCodiceFiscale() == null || tirocinante.getCodiceFiscale().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Codice Fiscale' &egrave vuoto");
    }

    if (tirocinante.getCodiceFiscale().length() != 16) {
      throw new IllegalArgumentException(
          "Il campo 'Codice Fiscale' supera la lunghezza consentita");
    }

    if (!(tirocinante.getCodiceFiscale().matches("^[A-Z0-9]+$"))) {
      throw new IllegalArgumentException("Il campo 'Codice Fiscale' non rispetta il formato");
    }
    // Controllo telefono
    if (request.getParameter("telefono") == null
        || request.getParameter("telefono").length() == 0) {
      throw new IllegalArgumentException("Il campo 'Telefono' &egrave vuoto");
    }

    if (request.getParameter("telefono").length() != 10) {
      throw new IllegalArgumentException("Il campo 'Telefono' supera la lunghezza consentita");
    }

    if (!(request.getParameter("telefono").matches("^[0-9]+$"))) {
      throw new IllegalArgumentException("Il campo 'Telefono' non rispetta il formato");
    }
    tirocinante.setTelefono(Long.parseLong(request.getParameter("telefono")));
    // Controllo email
    tirocinante.setEmail(s.getEmail());
    String prefix = "";
    // Copiata da EV
    if (s.getEmail().length() > 0) {
      prefix = s.getEmail().substring(0, s.getEmail().indexOf("@"));
    }
    if (s.getEmail().length() == 0) {
      throw new IllegalArgumentException("Il campo 'E-mail' &egrave vuoto");
    }
    if (s.getEmail().length() > 64) {
      throw new IllegalArgumentException("Il campo 'E-mail' supera la lunghezza consentita");
    }
    if (!s.getEmail().endsWith("@studenti.unisa.it") || prefix.length() < 3
        || prefix.indexOf(".") == -1) {
      throw new IllegalArgumentException("Il campo 'E-mail' non rispetta il formato");
    }
    // Controllo cfu
    if (request.getParameter("cfu") == null || request.getParameter("cfu").length() == 0) {
      throw new IllegalArgumentException("Il campo 'CFU' &egrave vuoto");
    }

    if (!(request.getParameter("cfu").matches("^[0-9]+$"))) {
      throw new IllegalArgumentException("Il campo 'CFU' non rispetta il formato");
    }

    tirocinio.setCfuPrevisti(Short.parseShort(request.getParameter("cfu")));
    if (tirocinio.getCfuPrevisti() > 180 || tirocinio.getCfuPrevisti() <= 0) {
      throw new IllegalArgumentException("Il campo 'CFU' supera la grandezza consentita");
    }

    // Controllo competenze possedute
    tirocinio.setCompetenze(request.getParameter("competenzePossedute"));
    if (tirocinio.getCompetenze() == null || tirocinio.getCompetenze().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Competenze' &egrave vuoto");
    }

    if (tirocinio.getCompetenze().length() > 256) {
      throw new IllegalArgumentException("Il campo 'Competenze' supera la lunghezza consentita");
    }
    // Controllo competenze acquisite
    tirocinio.setCompetenzeAcquisire(request.getParameter("competenzeDaAcquisire"));
    if (tirocinio.getCompetenzeAcquisire() == null
        || tirocinio.getCompetenzeAcquisire().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Competenze da Acquisire' &egrave vuoto");
    }

    if (tirocinio.getCompetenzeAcquisire().length() > 256) {
      throw new IllegalArgumentException(
          "Il campo 'Competenze da Acquisire' supera la lunghezza consentita");
    }
    // Controllo modalit� tirocinio
    tirocinio.setSvolgimentoTirocinio(request.getParameter("modalitaTirocinio"));

    if (tirocinio.getSvolgimentoTirocinio() == null
        || tirocinio.getSvolgimentoTirocinio().length() == 0) {
      throw new IllegalArgumentException(
          "Il campo 'Modalit&agrave svolgimento tirocinio' &egrave vuoto");
    }

    if (tirocinio.getSvolgimentoTirocinio().length() > 256) {
      throw new IllegalArgumentException(
          "Il campo 'Modalit&agrave svolgimento tirocinio' supera la lunghezza consentita");
    }
    // Controllo attivit�
    tirocinio.setAttivitaPreviste(request.getParameter("attivitaPreviste"));

    if (tirocinio.getAttivitaPreviste() == null || tirocinio.getAttivitaPreviste().length() == 0) {
      throw new IllegalArgumentException("Il campo 'Attivit&agrave Previste' &egrave vuoto");
    }

    if (tirocinio.getAttivitaPreviste().length() > 256) {
      throw new IllegalArgumentException(
          "Il campo 'Attivit&agrave Previste' supera la lunghezza consentita");
    }

    tirocinio.setMatricola(tirocinante.getMatricola());

    //Cerco il tirocinante nel database: se non esiste, lo inserisco
    TirocinanteDAO t = new TirocinanteDAO();
    Tirocinante tirtemp = t.ricercaTirocinanteByMatricola(tirocinante.getMatricola());
    if (tirtemp==null) {
    	t.inserisciTirocinante(tirocinante);
    }
    
    //Setto il tirocinio nel database
    TirocinioDAO ti = new TirocinioDAO();
    if(ti.inserisciTirocinio(tirocinio)==false)
    {
    	response.sendRedirect(request.getContextPath()+"/_areaStudent/HomeStudente.jsp?cod=2");
    }
    
    //Mi setto il tirocininante nel TIROCINIO
    tirocinio.setTirocinante(tirocinante);
    
    //Mi vado a riprendere il tirocinio e il tirocinante appena creati e li metto nella sessione
    tirocinio = ti.tirocinioAttivo(Long.valueOf(request.getParameter("matricolaTirocinante")));
    tirocinante = t.ricercaTirocinanteByMatricola(Long.valueOf(request.getParameter("matricolaTirocinante")));
    request.getSession().setAttribute("Tirocinante", tirocinante);
    request.getSession().setAttribute("Tirocinio", tirocinio); 
    
    //RequestDispatcher d = request.getRequestDispatcher("/_areaStudent/HomeStudente.jsp");
    //d.forward(request, response);
    response.sendRedirect(request.getContextPath()+"/_areaStudent/HomeStudente.jsp?cod=1");
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
