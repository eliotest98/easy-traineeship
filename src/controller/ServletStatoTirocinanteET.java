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
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletStatoTirocinanteET
 */
@WebServlet("/ServletStatoTirocinanteET")
public class ServletStatoTirocinanteET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // response.getWriter().append("Served at: ").append(request.getContextPath());
    doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int matricola = Integer.valueOf((String) request.getAttribute("matricola"));

    TirocinioDAO tirocinioDao = new TirocinioDAO();

    ArrayList<Tirocinante> tirocini = tirocinioDao.allTirocinioTirocinante(matricola);

    request.setAttribute("tirocini", tirocini);

    RequestDispatcher dispatcher = request.getRequestDispatcher("StatoProprioTirocinioET");
    dispatcher.forward(request, response);
  }

}
