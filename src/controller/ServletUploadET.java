package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.TirocinioDAO;
import model.Tirocinio;

/**
 * Servlet implementation class ServletUploadET.
 */
@WebServlet("/ServletUploadET")
@MultipartConfig
public class ServletUploadET extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletUploadET() {
    super();
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    {
      // Prendo il file dalla form

      javax.servlet.http.Part filePart = request.getPart("file");
      InputStream fileContent = filePart.getInputStream();
      String fileName = filePart.getSubmittedFileName();

      // Crea una stringa da aggiungere al nome del file, per impedire sovrascritture dovute a nomi
      // duplicati
      String Alpha_Numeric_String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      StringBuilder builder = new StringBuilder();
      int count = 10;

      while (count != 0) {
        int character = (int) (Math.random() * Alpha_Numeric_String.length());
        builder.append(Alpha_Numeric_String.charAt(character));
        count = count - 1;
      }
      // Path del file
      String pdfPath = "test";
      if (fileName != null) {
        pdfPath = "pdf" + builder.toString() + fileName;
      }

      File prova =new File("C:\\ProgettoFormativo");
      if (!prova.exists()) {
    	  prova.mkdir();
      }
      
      String filePath = "C:\\ProgettoFormativo\\" + pdfPath;
      //String currentDirectory = System.getProperty("user.dir");
      //System.out.println(currentDirectory);
      //System.out.println(filePath);
      //System.out.println("path"+filePath);
      OutputStream os = null;

      try {
        File file = new File(filePath);
        file.createNewFile();
        os = new FileOutputStream(file, false);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fileContent.read(buffer)) > 0) {
          os.write(buffer, 0, length);
        }
      } finally {
        fileContent.close();
        os.close();
      }

      // Prelevo il tipo di userET
      int userET = Integer.parseInt((String) request.getSession().getAttribute("userET"));
      // String Stato progetto formativo
      String stato = "";
      // Tirocinio DAO
      TirocinioDAO tirocinioDaO = new TirocinioDAO();
      // Controllo utente
      if ((String) request.getSession().getAttribute("userET") == null) {
        throw new IllegalArgumentException("L'utente non esiste.");
      }

      // Se sei lo studente
      if (userET == 0) {
        // Prelevo il tirocinio dalla sessione
        Tirocinio tirocinio = (Tirocinio) request.getSession().getAttribute("Tirocinio");

        if (tirocinio == null) {
          throw new IllegalArgumentException("Il Tirocinio non � in sessione o non � esistente.");
        }

        // Lo setto all'interno del database
        if (tirocinioDaO.uploadProgettoFormativo(tirocinio.getCodTirocinio(), pdfPath) == false) {
          throw new IllegalArgumentException("Query upload non andata a buon fine");
        }

        if (tirocinioDaO.modificaStatoTirocinio(tirocinio.getCodTirocinio(),
            "Accettato e in attesa di firma Ente e Admin") == false) {
          throw new IllegalArgumentException("Query modifica stato non andata a buon fine");
        }
        /*
         * //Reindirizzo alla home di studente RequestDispatcher disp =
         * request.getRequestDispatcher("/_areaStudent/HomeStudente.jsp"); disp.forward(request,
         * response);
         */
      } else {
        // Prelevo il codice dall'id della tabella
        int codTirocinio = Integer.parseInt((String) request.getParameter("cod"));
        if ((String) request.getParameter("cod") == null) {
          throw new IllegalArgumentException("Codice tirocinio non valido.");
        }
        // Effettuo la query di upload
        if (tirocinioDaO.uploadProgettoFormativo(codTirocinio, pdfPath) == false) {
          throw new IllegalArgumentException("Query upload 2 non andata a buon fine.");
        } else if (userET == 3) {
          // Sei l'ente
          stato = "Accettato e in attesa di firma Admin";
        } else if (userET == 2) {
          // Sei l'admin
          stato = "Completo";
        } else {
          // Non sei nessuno
          throw new IllegalArgumentException("Errore.");
        }

        if (tirocinioDaO.modificaStatoTirocinio(codTirocinio, stato) == false) {
          throw new IllegalArgumentException("Query modifica stato non andata a buon fine");
        }
        // Reindirizzo a documenti da firmare
        RequestDispatcher disp = request.getRequestDispatcher("DocumentiET.jsp");
        disp.forward(request, response);
      }
    }
  }

}
