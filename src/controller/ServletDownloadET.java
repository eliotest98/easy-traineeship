package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Tirocinio;
import model.DAO.TirocinioDAO;


/**
 * Servlet implementation class ServletDownloadET.
 */
@WebServlet("/ServletDownloadET")
public class ServletDownloadET extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @SuppressWarnings("unused")
  private File file;

  /**
   * constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletDownloadET() {
    super();
  }

  /**
   * method doGet.
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    OutputStream outputStream = null;
    InputStream in = null;

    // Prelevo il tirocinio dalla sessione
    Tirocinio tirocinio = (Tirocinio) request.getSession().getAttribute("Tirocinio");

    // Se è null, in sessione non c'è lo studente
    if (tirocinio == null) {
      // Prelevo il codice dalla risposta
      int cod = Integer.parseInt((String) request.getParameter("cod"));
      // Cerco il tirocinio
      TirocinioDAO t = new TirocinioDAO();
      tirocinio = t.TirocinioByCodTirocinio(cod);
      // Se non esiste, c'è un errore
      if (tirocinio == null) {
        throw new IllegalArgumentException("Errore nel codice del tirocinio");
      }
    }

    String filePath = "\\easy-traineeship\\WebContent\\ProgettoFormativo\\";

    try {
      in = new FileInputStream(filePath + tirocinio.getProgettoFormativo());
      byte[] buffer = new byte[1024];
      int bytesRead = 0;
      response.setHeader("Content-Disposition",
          "attachment;filename=\"" + tirocinio.getProgettoFormativo() + "\"");
      outputStream = response.getOutputStream();
      while (0 < (bytesRead = in.read(buffer))) {
        outputStream.write(buffer, 0, bytesRead);
      }
    } finally {
      if (null != in) {
        in.close();
      }
    }
  }

  /**
   * method doPost.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
