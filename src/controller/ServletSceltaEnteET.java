package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tirocinante;
import model.Tirocinio;
import model.DAO.TirocinanteDAO;
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletInvioRichiestaEnteET.
 */
@WebServlet("/ServletSceltaEnteET")
public class ServletSceltaEnteET extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TirocinioDAO tirocinioDAO = new TirocinioDAO();
	TirocinanteDAO tirocinanteDao = new TirocinanteDAO();
	String mess = null;

	public ServletSceltaEnteET() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * Controllo autenticazione tramite parametro in sessione (0 = Studente).
		 */
		String userET = (String) request.getSession().getAttribute("userET");
		if ((userET == null) || (!userET.equals("0"))) {
			response.sendRedirect("login.jsp");
			return;
		}
		// Controllo ente partita iva, perch� cliccando il nome si passa la chiave primaria (partita iva)
		String ente = request.getParameter("ente");
		
		if (ente==null) {
			throw new IllegalArgumentException("Il campo Ente � vuoto");
		} else if (ente.length() != 11) {
			throw new IllegalArgumentException("Il campo Ente non � di 11 cifre");
		} else if (!ente.matches("^[0-9]+$")) {
			throw new IllegalArgumentException("Il campo Ente non rispetta il formato");
		}
		// Controllo nome tirocinante
		String nome = request.getParameter("nome");
		
		if (nome.length() == 0) {
			throw new IllegalArgumentException("Il campo Nome e' vuoto");
		} else if (nome.length() > 50) {
			throw new IllegalArgumentException("Il campo Nome supera la lunghezza consentita");
		} else if (!nome.matches("^[a-zA-Z\\.]+$")) {
			throw new IllegalArgumentException("Il campo Nome non rispetta il formato");
		}
		// Controllo cognome tirocinante
		String cognome = request.getParameter("cognome");
		
		if (cognome.length() == 0) {
			throw new IllegalArgumentException("Il campo Cognome e' vuoto");
		} else if (cognome.length() > 50) {
			throw new IllegalArgumentException("Il campo Cognome supera la lunghezza consentita");
		} else if (!cognome.matches("^[a-zA-Z\\.]+$")) {
			throw new IllegalArgumentException("Il campo Cognome non rispetta il formato");
		}
		// Controllo facoltà
		String facolta = request.getParameter("facolta");
		
		if (facolta.length() == 0) {
			throw new IllegalArgumentException("Il campo Facolta' e' vuoto");
		} else if (facolta.length() > 50) {
			throw new IllegalArgumentException("Il campo Facolta' supera la lunghezza consentita");
		} else if (!facolta.matches("^[a-zA-Z]+$")) {
			throw new IllegalArgumentException("Il campo Facolta' non rispetta il formato");
		}
		// Controllo descrizione
		String descrizione = request.getParameter("descrizione");
		
		if (descrizione.length() == 0) {
			throw new IllegalArgumentException("Il campo Descrizione e' vuoto");
		} else if (descrizione.length() > 256) {
			throw new IllegalArgumentException("Il campo Descrizione supera la lunghezza consentita");
		}
		
		//Prelevo il tirocinante dalla sessione
		Tirocinante tirocinante = (Tirocinante) request.getSession().getAttribute("Tirocinante");
		if(tirocinante==null)
		{
		  throw new IllegalArgumentException("Il Tirocinante non esiste.");
		}
		
		//Prelevo il tirocinio di questo tirocinante
		Tirocinio tirocinio = tirocinioDAO.tirocinioAttivo(tirocinante.getMatricola());
		//Effettuo l'invio della richiesta
		Boolean risp = tirocinioDAO.richiestaEnte(tirocinio.getCodTirocinio(),ente,descrizione);
		//Se � andata a buon fine, setto lo stato, se no eccezione
		if(risp==false)
        {
          throw new IllegalArgumentException("La query di invio richiesta non � andata a buon fine.");
        }
		//Riprendo il tirocinio appena cambiato
		tirocinio = tirocinioDAO.tirocinioAttivo(tirocinante.getMatricola());
		
		//Risettiamo il tirocinante, per sicurezza
		tirocinante = tirocinio.getTirocinante();
		tirocinio.setTirocinante(tirocinante);
		
		//Setto TUTTO nella sessione per sovrascrivere la merda che c'era
		
		request.getSession().setAttribute("Tirocinante", tirocinante);
		request.getSession().setAttribute("Tirocinio", tirocinio);
		
		//Request dispatcher alla pagina
		RequestDispatcher dispatcher = request.getRequestDispatcher("/_areaStudent/HomeStudente.jsp");
		dispatcher.forward(request, response);
	}
}

