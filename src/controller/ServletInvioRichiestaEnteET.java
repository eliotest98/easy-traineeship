package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EnteConvenzionato;

/**
 * Servlet implementation class ServletInvioRichiestaEnteET
 */
@WebServlet("/ServletInvioRichiestaEnteET")
public class ServletInvioRichiestaEnteET extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final TirocinioDAO tirocinioDAO = new TirocinioDAO();  
	String mess= null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInvioRichiestaEnteET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		/**
		 * Controllo autenticazione tramite parametro in sessione (0 = Studente)
		 */
		String user = (String) request.getSession().getAttribute("user");
		if ((user == null) || (!user.equals("0")))
		{
			response.sendRedirect("login.jsp");
			return;
		}
		//Controllo nome ente
		String ente = request.getParameter("ente");
        if (ente.length() == 0)
        {
        	throw new IllegalArgumentException("Il campo Ente e' vuoto");
        }
        else if (ente.length() > 64)
        {
        	throw new IllegalArgumentException("Il campo Ente supera la lunghezza consentita");
        }
        else if (!ente.matches("^[ 0-9a-zA-Z\\.]+$"))
        {
        	throw new IllegalArgumentException("Il campo Ente non rispetta il formato");
        }
		//Controllo nome tirocinante
		String nome = request.getParameter("nome");
        if (nome.length() == 0)
        {
        	throw new IllegalArgumentException("Il campo Nome e' vuoto");
        }
        else if (nome.length() > 50)
        {
        	throw new IllegalArgumentException("Il campo Nome supera la lunghezza consentita");
        }
        else if (!nome.matches("^[ 0-9a-zA-Z\\.]+$"))
        {
        	throw new IllegalArgumentException("Il campo Nome non rispetta il formato");
        }
		//Controllo cognome tirocinante
		String cognome = request.getParameter("cognome");
        if (cognome.length() == 0)
        {
        	throw new IllegalArgumentException("Il campo Cognome e' vuoto");
        }
        else if (cognome.length() > 50)
        {
        	throw new IllegalArgumentException("Il campo Cognome supera la lunghezza consentita");
        }
        else if (!cognome.matches("^[ 0-9a-zA-Z\\.]+$"))
        {
        	throw new IllegalArgumentException("Il campo Cognome non rispetta il formato");
        }
		//Controllo facoltà
		String facolta = request.getParameter("facolta");
        if (facolta.length() == 0)
        {
        	throw new IllegalArgumentException("Il campo Facolta' e' vuoto");
        }
        else if (facolta.length() > 50)
        {
        	throw new IllegalArgumentException("Il campo Facolta' supera la lunghezza consentita");
        }
        else if (!facolta.matches("^[ a-zA-Z]+$"))
        {
        	throw new IllegalArgumentException("Il campo Facolta' non rispetta il formato");
        }
		//Controllo descrizione
		String descrizione = request.getParameter("descrizione");
        if (descrizione.length() == 0)
        {
        	throw new IllegalArgumentException("Il campo Descrizione e' vuoto");
        }
        else if (descrizione.length() > 256)
        {
        	throw new IllegalArgumentException("Il campo Descrizione supera la lunghezza consentita");
        }
        
        String matricola =(String) request.getSession().getAttribute("matricola");  //da capire se la passiamo in sessione
        try 
        {
        	if (!tirocinioDAO.aggiornaTirocinio(matricola,descrizione))  // controllare il metodo
			{
				throw new IllegalArgumentException("L'invio della richiesta non e' stato effettuato");
			}else 
			{ 
				request.setAttribute("L'invio della richiesta e' avvenuto con successo", mess);
				RequestDispatcher dispatcher = request.getRequestDispatcher("risultato.jsp"); //Controlla jsp
				dispatcher.forward(request, response);
			}
        }
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
