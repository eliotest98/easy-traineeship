package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.EnteConvenzionatoDAO;

/**
 * Servlet implementation class ServletEliminaEnteET
 * 
 * Questa Servlet implementa la funzionalit� di eliminazione di un ente in base alla sua email.
 */
@WebServlet("/ServletEliminaEnteET")
public class ServletEliminaEnteET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminaEnteET() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Controllo che il parametro non sia vuoto*/
		if(!request.getParameter("enteEmail").isEmpty()) {
			String emailEnte = request.getParameter("enteEmail");
		/*Se non ci sono stati problemi*/
		if(emailEnte!=null) {
			try {
				EnteConvenzionatoDAO enteDao = new EnteConvenzionatoDAO();
				boolean res = enteDao.eliminaEnte(emailEnte);
				if(res) {
					response.setStatus(HttpServletResponse.SC_OK);
					PrintWriter out = response.getWriter();
				    out.println(true);
				    out.close();
				    return;
				}
				else {
					response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
					PrintWriter out = response.getWriter();
				    out.println(false);
				    out.close();
				return;
				}
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
		/*Altrimenti segnalo l'errore*/
		PrintWriter out = response.getWriter();
		response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		out.println(false);
		out.close();
		return;
	}
}
