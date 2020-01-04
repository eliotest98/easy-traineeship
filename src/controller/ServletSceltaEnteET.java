package controller;

import java.io.IOException;
import java.util.ArrayList;

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
		
		String partitaIva = (String) request.getParameter("ente").substring(0, 11);
		System.out.println("PARTITAIVA: " + partitaIva);
		
		// Controllo nome ente
		String ente = request.getParameter("ente").substring(11);
		System.out.println("ENTE: " + ente);
		if (ente.length() == 0) {
			throw new IllegalArgumentException("Il campo Ente e' vuoto");
		} else if (ente.length() > 64) {
			throw new IllegalArgumentException("Il campo Ente supera la lunghezza consentita");
		} else if (!ente.matches("^[ 0-9a-zA-Z\\.]+$")) {
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
		Tirocinio tirocinio = tirocinioDAO.tirocinioAttivo();
		tirocinioDAO.modificaStatoTirocinio(tirocinio.getCodTirocinio(), "In Attesa Dell'Ente")
		
		long matricola = tirocinanteDao.ricercaTirocinanteByEmail(user.getEmail()).getMatricola();
		ArrayList<Tirocinio> listaTirocini = tirocinioDAO.allTirocinioTirocinante(matricola);  //creare un metodo che restituisca un solo codTirocinio
		try {
			Boolean prova1 = tirocinioDAO.richiestaEnte(listaTirocini.get(listaTirocini.size()-1).getCodTirocinio(), partitaIva, descrizione);
			System.out.println("prova1 " + prova1);
			Boolean prova2 = tirocinioDAO.modificaStatoTirocinio(listaTirocini.get(listaTirocini.size()-1).getCodTirocinio(),"in attesa dell Ente");
			System.out.println("prova2 " + prova2);
			if ((prova1==true) && (prova2==true)) {
				request.setAttribute("L'invio della richiesta e' avvenuto con successo", mess);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/_areaStudent/viewRequest.jsp");// Controlla jsp
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

