package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EnteConvenzionato;
import model.Tirocinante;
import model.Tirocinio;
import model.DAO.EnteConvenzionatoDAO;
import model.DAO.TirocinanteDAO;
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletStatoTirocinioET
 */
@WebServlet("/ServletStatoTirocinioET")
public class ServletStatoTirocinioET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletStatoTirocinioET() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TirocinioDAO tirocinioDao = new TirocinioDAO();
		TirocinanteDAO tirocinanteDao = new TirocinanteDAO();
		EnteConvenzionatoDAO enteConDao = new EnteConvenzionatoDAO();
		Tirocinio tirocinioattivo = null;
		Tirocinante tirocinante = null;
		EnteConvenzionato ente = null;
		
		try {
			 tirocinioattivo = tirocinioDao.tirocinioAttivo(Integer.valueOf(request.getParameter("matricola")));
			 tirocinante = tirocinanteDao.ricercaTirocinanteByMatricola(Integer.valueOf(request.getParameter("matricola")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (tirocinioattivo!=null) {
			if (tirocinioattivo.getPartitaIva()!=null) {
				try {
					ente = enteConDao.ricercaEnteByPartitaIva(tirocinioattivo.getPartitaIva());
					request.getSession().setAttribute("ente", ente);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			

			request.getSession().setAttribute("tirocinio", tirocinioattivo);
			request.getSession().setAttribute("tirocinante", tirocinante);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/_areaSecretary/VisualizzaStatoTirocinioET.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("/_areaSecretary/viewRequest.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
