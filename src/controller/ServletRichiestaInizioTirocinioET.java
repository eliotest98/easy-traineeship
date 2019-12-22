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
      
	    String userET = (String) request.getSession().getAttribute("userET");
	    
	    if ((userET == null) || (!userET.equals("0"))) 
	    {
	      response.sendRedirect("login.jsp");
	      return;
	    }
	   */
	    Tirocinante tirocinante = new Tirocinante();
	    Tirocinio tirocinio = new Tirocinio();
	    
	    tirocinante.setName(request.getParameter("nomeTirocinante"));
	    
	    tirocinante.setSurname(request.getParameter("cognomeTirocinante"));
	    
	    tirocinante.setMatricola(Long.parseLong(request.getParameter("matricolaTirocinante")));
	    
	    tirocinante.setFacolta(request.getParameter("facoltaTirocinante"));
	    
	    tirocinante.setLuogoNascita(request.getParameter("luogoDiNascita"));
	    
	    tirocinante.setCittadinanza(request.getParameter("cittadinanza"));
	    
	    tirocinante.setResidenza(request.getParameter("residenza"));
	    
	    tirocinante.setCodiceFiscale(request.getParameter("codiceFiscale"));
	    
	    tirocinante.setTelefono(Long.parseLong(request.getParameter("telefono")));
	    
	    tirocinante.setEmail(request.getParameter("email"));
	    
	    tirocinio.setCfuPrevisti(Short.parseShort(request.getParameter("cfu")));
	    
	    tirocinio.setCompetenze(request.getParameter("competenzePossedute"));
	    
	    tirocinio.setCompetenzeAcquisire(request.getParameter("competenzeDaAcquisire"));
	    
	    tirocinio.setSvolgimentoTirocinio(request.getParameter("modalitaTirocinio"));
	    
	    tirocinio.setAttivitaPreviste(request.getParameter("attivitaPreviste"));
	    
	    tirocinio.setMatricola(tirocinante.getMatricola());
	   
	    System.out.println("SALVATORE è DOWN");
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
