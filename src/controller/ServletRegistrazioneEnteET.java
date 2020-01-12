package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.EnteConvenzionatoDAO;
import model.EnteConvenzionato;

/**
 * Servlet implementation class ServletRegistazioneEnteET.
 */
@WebServlet("/ServletRegistrazioneEnteET")
public class ServletRegistrazioneEnteET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  EnteConvenzionatoDAO enteConDao = new EnteConvenzionatoDAO();
  String mess = null;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    /**
     * Controllo autenticazione tramite parametro in sessione (1 = Segreteria).
     */
    String userET = (String) request.getSession().getAttribute("userET");
    if ((userET == null) || (!userET.equals("1"))) {
      response.sendRedirect("login.jsp");
      return;
    }
    // Controllo nome ente
    String name = request.getParameter("name");
    if (name.length() == 0) {
      throw new IllegalArgumentException("Il campo 'Nome Ente' &egrave vuoto");
    } else if (name.length() > 64) {
      throw new IllegalArgumentException("Il campo 'Nome Ente' supera la lunghezza consentita");
    } else if (!name.matches("^[ 0-9a-zA-Z\\.]+$")) {
      throw new IllegalArgumentException("Il campo 'Nome Ente' non rispetta il formato");
    }
    // Controllo nome rappresentante
    String rappresentante = request.getParameter("rappresentante");
    if (rappresentante.length() == 0) {
      throw new IllegalArgumentException("Il campo 'Nome Rappresentante' &egrave vuoto");
    } else if (rappresentante.length() > 64) {
      throw new IllegalArgumentException("Il campo 'Nome Rappresentante' supera la lunghezza consentita");
    } else if (!rappresentante.matches("^[ a-zA-Z]+$")) {
      throw new IllegalArgumentException("Il campo 'Nome Rappresentante' non rispetta il formato");
    }
    // Controllo Data di nascita rappresentante
    GregorianCalendar g = new GregorianCalendar();
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
    String dat = request.getParameter("dataDiNascita");
    if (!dat.matches("^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))+$")) {
        throw new IllegalArgumentException("Il campo 'Data di Nascita' non rispetta il formato");
      }
    try {
        g.setTime((Date) formatter1.parseObject(dat));
        java.sql.Date data = new java.sql.Date(g.getTimeInMillis());
      } catch (ParseException e) {
        e.printStackTrace();
      }
    
    // Controllo Numero di Dipendenti
    String dipendenti = request.getParameter("dipendenti");
    if (!dipendenti.matches("^[0-9]+$")) {
      throw new IllegalArgumentException("Il campo 'Numero di Dipendenti' non rispetta il formato");
    }
    // Controllo Professore di riferimento
    String dotRiferimento = request.getParameter("dotRiferimento");
    if (dotRiferimento.length() == 0) {
      throw new IllegalArgumentException("Il campo 'Professore di Riferimento' &egrave vuoto");
    } else if (dotRiferimento.length() > 64) {
      throw new IllegalArgumentException("Il campo 'Professore di Riferimento' supera la lunghezza consentita");
    } else if (!dotRiferimento.matches("^[ a-zA-Z]+$")) {
      throw new IllegalArgumentException("Il campo 'Professore di Riferimento' non rispetta il formato");
    }

    // Controllo email
    String email = request.getParameter("email");
    String prefix = "";
    String postfix = "";
    if (email.length() > 0) {
      prefix = email.substring(0, email.indexOf("@"));
      postfix = email.substring(email.indexOf("@"), email.length());
    }
    if (email.length() == 0) {
    	throw new IllegalArgumentException("Il campo 'E-mail' &egrave vuoto");
    }
    if (email.length() > 64) {
    	throw new IllegalArgumentException("Il campo 'E-mail' supera la lunghezza consentita");
    }

    if (email.length() == 0
            || !postfix.matches("@[A-z0-9\\.\\_\\-]+\\.[A-z]{2,6}")
            || prefix.length() < 1) 
     {
        throw new IllegalArgumentException("Il campo 'E-mail' non rispetta il formato");
     }
    // Controllo Sede
    String sede = request.getParameter("sede");
    if (sede.length() == 0) {
      throw new IllegalArgumentException("Il campo 'Sede' &egrave vuoto");
    } else if (sede.length() > 64) {
      throw new IllegalArgumentException("Il campo 'Sede' supera la lunghezza consentita");
    } else if (!sede.matches("^[ 0-9a-zA-Z\\.\\,]+$")) {
      throw new IllegalArgumentException("Il campo 'Sede' non rispetta il formato");
    }
    // Controllo Referente tirocini
    String referente = request.getParameter("referente");
    if (referente.length() == 0) {
      throw new IllegalArgumentException("Il campo 'Referente Tirocini' &egrave vuoto");
    } else if (referente.length() > 64) {
      throw new IllegalArgumentException("Il campo 'Referente Tirocini' supera la lunghezza consentita");
    } else if (!referente.matches("^[ a-zA-Z]+$")) {
      throw new IllegalArgumentException("Il campo 'Referente Tirocini' non rispetta il formato");
    }
    // Controllo numero di telefono
    String telefono = request.getParameter("telefono");
    if (!telefono.matches("^\\d{10}")) {
      throw new IllegalArgumentException("Il campo 'Numero di Telefono' non rispetta il formato");
    }

    // Controllo Descrizione delle attivita'
    String descrizioneAttivita = request.getParameter("descrizioneAttivita");
    if (descrizioneAttivita.length() == 0) {
      throw new IllegalArgumentException("Il campo 'Descrizione delle Attivit&agrave' &egrave vuoto");
    } else if (descrizioneAttivita.length() > 256) {
      throw new IllegalArgumentException("Il campo 'Descrizione delle Attivit&agrave' supera la lunghezza consentita");
    }
    // Controllo Partita IVA
    String partitaIva = request.getParameter("partitaIva");
    if (!partitaIva.matches("^\\d{11}")) {
      throw new IllegalArgumentException("Il campo 'Partita IVA' non rispetta il formato");
    }
    
    /**
     * Genera password criptata
     */
    String password = new Utils().generatePwd("12345678");
    
    /**
     * Istanziazione dell'oggetto EnteConvezionato.
     */
    EnteConvenzionato enteCon = new EnteConvenzionato(email, name, "NA", 'N', password, 3,
        dat, partitaIva, sede, rappresentante, referente, telefono,
        Integer.parseInt(dipendenti), dotRiferimento, "TE", descrizioneAttivita);
    /**
     * Inserimento nel DB.
     */
    try {
    	enteConDao.inserisciEnte(enteCon);
    	request.setAttribute("La registrazione &egrave avvenuta con successo", mess);
        // Controlla jsp
        //RequestDispatcher dispatcher = request.getRequestDispatcher("VisualizzaEnteET.jsp"); 
        //dispatcher.forward(request, response);
        response.sendRedirect(request.getContextPath()+"/VisualizzaEnteET.jsp");
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}
