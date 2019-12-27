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
    public ServletRichiestaInizioTirocinioET() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
	  /**
	  * Controllo autenticazione tramite parametro in sessione (0 = Studente ET alias Tirocinante).
      */
  	    Tirocinante tirocinante = new Tirocinante();
        Tirocinio tirocinio = new Tirocinio();
	    String userET = (String) request.getSession().getAttribute("userET");
	    Student s = (Student)request.getSession().getAttribute("user");
	    
	    if ((userET == null) || (!userET.equals("0"))) 
	    {
	      response.sendRedirect("login.jsp");
	      return;
	    }
	    
	    /*Controllo TEST CASE, messaggi*/
	    //Controllo nome
	    if(s.getName() == null)
	    {
	      throw new IllegalArgumentException("Il campo nome è vuoto. L'invio della richiesta non viene effettuato.");
	    }
	    
	    if(s.getName().length() > 50)
        {
          throw new IllegalArgumentException("Il campo nome supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
	    
	    if(s.getName().matches("^[A-Z a-z]$"))
        {
          throw new IllegalArgumentException("Il campo nome non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }
	    //Controllo cognome
	    if(s.getSurname() == null)
        {
          throw new IllegalArgumentException("Il campo cognome è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
	    if(s.getSurname().length() > 50)
        {
          throw new IllegalArgumentException("Il campo cognome supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
	    
	    if(s.getName().matches("[A-Z a-z]"))
        {
          throw new IllegalArgumentException("Il campo cognome non rispetta il formato.. L'invio della richiesta non viene effettuato.");
        }
	    //Controllo matricola
	    tirocinante.setMatricola(Long.parseLong(request.getParameter("matricolaTirocinante")));
	    
	    if(request.getParameter("matricolaTirocinante") == null)
	    {
	      throw new IllegalArgumentException("Il campo matricola è vuoto. L'invio della richiesta non viene effettuato.");
	    }

	    if(request.getParameter("matricolaTirocinante").length()!=10)
        {
          throw new IllegalArgumentException("Il campo matricola supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
	    
	    if(request.getParameter("matricolaTirocinante").matches("[0-9]"))
	    {
	      throw new IllegalArgumentException("Il campo matricola non rispetta il formato. L'invio della richiesta non viene effettuato.");
	    }
	    //Controllo facoltà
	    tirocinante.setFacolta("Informatica");
	    
	    if(tirocinante.getFacolta() == null)
        {
          throw new IllegalArgumentException("Il campo facoltà è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getFacolta().length() > 50)
        {
          throw new IllegalArgumentException("Il campo facoltà supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getFacolta().matches("[A-Z a-z]"))
        {
          throw new IllegalArgumentException("Il campo facoltà non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }
        //Controllo data di nascita
        /*Ci ho messo tre anni, ma sono riuscita a convertire da string a DATE*/
 
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");
        try 
        {
          tirocinante.setDataNascita(formatter1.parse(request.getParameter("dataDiNascita")));
        } 
        catch (ParseException e) 
        {
          e.printStackTrace();
        }
        
        if(tirocinante.getDataNascita()==null)
        {
          throw new IllegalArgumentException("Il campo data di nascita è vuoto. L'invio della richiesta non viene effettuato.");
        }

        //Controllo luogo di nascita
	    tirocinante.setLuogoNascita(request.getParameter("luogoDiNascita"));
	    
	    if(tirocinante.getLuogoNascita()==null)
	    {
          throw new IllegalArgumentException("Il campo luogo di nascita è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getLuogoNascita().length() > 64)
        {
          throw new IllegalArgumentException("Il campo luogo di nascita supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getLuogoNascita().matches("[A-Z a-z]+"))
        {
          throw new IllegalArgumentException("Il campo luogo di nascita non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }
        //Controllo cittadinanza
	    tirocinante.setCittadinanza(request.getParameter("cittadinanza"));
	    
	    if(tirocinante.getCittadinanza()==null)
	    {
	      throw new IllegalArgumentException("Il campo cittadinanza è vuoto. L'invio della richiesta non viene effettuato.");
	    }
	    
	    if(tirocinante.getCittadinanza().length() > 64)
        {
          throw new IllegalArgumentException("Il campo cittadinanza supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getCittadinanza().matches("[A-Za-z]"))
        {
          throw new IllegalArgumentException("Il campo cittadinanza non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }
        //Controllo residenza
	    tirocinante.setResidenza(request.getParameter("residenza"));
	    
	    if(tirocinante.getResidenza()==null)
        {
          throw new IllegalArgumentException("Il campo residenza è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getResidenza().length() > 64)
        {
          throw new IllegalArgumentException("Il campo residenza supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getResidenza().matches("[A-Za-z]"))
        {
          throw new IllegalArgumentException("Il campo residenza non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }
	    //Controllo codice fiscale
	    tirocinante.setCodiceFiscale(request.getParameter("codiceFiscale"));
	    
	    if(tirocinante.getCodiceFiscale()==null)
        {
          throw new IllegalArgumentException("Il campo codice fiscale è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getCodiceFiscale().length()==16)
        {
          throw new IllegalArgumentException("Il campo codice fiscale supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinante.getCodiceFiscale().matches("[A-Z0-9]"))
        {
          throw new IllegalArgumentException("Il campo codice fiscale non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }
	    //Controllo telefono
	    tirocinante.setTelefono(Long.parseLong(request.getParameter("telefono")));
	    
	    if(request.getParameter("telefono")==null)
        {
          throw new IllegalArgumentException("Il campo telefono è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(request.getParameter("telefono").length()==10)
        {
          throw new IllegalArgumentException("Il campo telefono supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
        
        if(request.getParameter("telefono").matches("[0-9]"))
        {
          throw new IllegalArgumentException("Il campo telefono non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }
	    //Controllo email
	    tirocinante.setEmail(request.getParameter("email"));

	    String prefix = "";
        //Copiata da EV
	    if (s.getEmail().length() > 0) 
	    {
          prefix = s.getEmail().substring(0, s.getEmail().indexOf("@"));
        }
        if (s.getEmail().length() == 0 || 
            !s.getEmail().endsWith("@studenti.unisa.it") || 
            prefix.length() < 3 || 
            prefix.indexOf(".") == -1) 
        {
          throw new IllegalArgumentException("Il campo email non rispetta il formato.");
        }
        //Controllo cfu
	    tirocinio.setCfuPrevisti(Short.parseShort(request.getParameter("cfu")));

        if(request.getParameter("cfu")==null)
        {
          throw new IllegalArgumentException("Il campo cfu è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(request.getParameter("cfu").length()==3)
        {
          throw new IllegalArgumentException("Il campo cfu supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
        
        if(request.getParameter("cfu").matches("[0-9]"))
        {
          throw new IllegalArgumentException("Il campo cfu non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }	
        
        if(tirocinio.getCfuPrevisti() <= 180 && tirocinio.getCfuPrevisti() >= 0)
        {
          throw new IllegalArgumentException("Il campo cfu non rispetta il formato. L'invio della richiesta non viene effettuato.");
        }
        //Controllo competenze possedute
	    tirocinio.setCompetenze(request.getParameter("competenzePossedute"));
	    
	    if(tirocinio.getCompetenze()==null)
	    {
	      throw new IllegalArgumentException("Il campo competenze è vuoto. L'invio della richiesta non viene effettuato.");
	    }
	    
	    if(tirocinio.getCompetenze().length() > 256)
        {
          throw new IllegalArgumentException("Il campo competenze supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
	    //Controllo competenze acquisite
	    tirocinio.setCompetenzeAcquisire(request.getParameter("competenzeDaAcquisire"));
        
        if(tirocinio.getCompetenzeAcquisire()==null)
        {
          throw new IllegalArgumentException("Il campo competenze acquisite è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinio.getCompetenzeAcquisire().length() > 256)
        {
          throw new IllegalArgumentException("Il campo competenze acquisite supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
        //Controllo modalità tirocinio
	    tirocinio.setSvolgimentoTirocinio(request.getParameter("modalitaTirocinio"));
	    
	    if(tirocinio.getSvolgimentoTirocinio()==null)
        {
          throw new IllegalArgumentException("Il campo modalità tirocinio è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinio.getSvolgimentoTirocinio().length() > 256)
        {
          throw new IllegalArgumentException("Il campo modalità tirocinio supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
	    //Controllo attività
	    tirocinio.setAttivitaPreviste(request.getParameter("attivitaPreviste"));
	    
	    if(tirocinio.getAttivitaPreviste()==null)
        {
          throw new IllegalArgumentException("Il campo attività previste è vuoto. L'invio della richiesta non viene effettuato.");
        }
        
        if(tirocinio.getAttivitaPreviste().length() > 256)
        {
          throw new IllegalArgumentException("Il campo attività previste supera la lunghezza massima consentita. L'invio della richiesta non viene effettuato.");
        }
	    
	    tirocinio.setMatricola(tirocinante.getMatricola());
	   
	    TirocinanteDAO t = new TirocinanteDAO();
	    t.inserisciTirocinante(tirocinante);
	    
	    TirocinioDAO ti = new TirocinioDAO();
	    ti.inserisciTirocinio(tirocinio);
	    
	    RequestDispatcher d = request.getServletContext().getRequestDispatcher("/VisualizzaEnteET.jsp");
	    d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
