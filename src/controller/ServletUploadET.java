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
import model.Tirocinio;
import model.DAO.TirocinioDAO;

/**
 * Servlet implementation class ServletUploadET
 */
@WebServlet("/ServletUploadET")
@MultipartConfig
public class ServletUploadET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUploadET() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	  doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	{
	    //Prendo il file dalla form
		System.out.println("SonoQui");
        javax.servlet.http.Part filePart = request.getPart("file");
        System.out.println(request.getPart("file"));
        InputStream fileContent = filePart.getInputStream();
        String fileName = filePart.getSubmittedFileName();
        System.out.println("file"+fileName);
       
        
        //Crea una stringa da aggiungere al nome del file, per impedire sovrascritture dovute a nomi duplicati
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        int count = 10;
        
        while (count != 0) 
        {
           int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
           builder.append(ALPHA_NUMERIC_STRING.charAt(character));
           count = count - 1;
        }
        //Path del file
        String pdfPath =  "pdf" + builder.toString() + fileName;
        String filePath = "C:\\Users\\simon\\Documents\\GitHub\\easy-traineeship\\ProgettoFormativo\\"+pdfPath;
        
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
        } 
        finally 
        {
            fileContent.close();
            os.close();
        }
	    
	      //Prelevo il tipo di userET
	      int userET = Integer.parseInt((String) request.getSession().getAttribute("userET"));
	      //String Stato progetto formativo
	      String stato = "";
	      //Tirocinio DAO
	      TirocinioDAO tirocinioDAO = new TirocinioDAO();   
	      //Controllo utente
	      if((String) request.getSession().getAttribute("userET")==null)
	      {
	        throw new IllegalArgumentException("L'utente non esiste.");
	      }
	      
	      //Se sei lo studente
	      if(userET==0)
	      {
	          //Prelevo il tirocinio dalla sessione
	          Tirocinio tirocinio = (Tirocinio) request.getSession().getAttribute("Tirocinio");
	          
	          if(tirocinio==null)
	          {
	            throw new IllegalArgumentException("Il Tirocinio non è in sessione o non è esistente.");
	          }
	          
	          //Lo setto all'interno del database
	          if(tirocinioDAO.uploadProgettoFormativo(tirocinio.getCodTirocinio(), pdfPath)==false)
	          {
	            throw new IllegalArgumentException("Query upload non andata a buon fine");
	          }
	          
	          if(tirocinioDAO.modificaStatoTirocinio(tirocinio.getCodTirocinio(), "Accettato e in attesa di firma della Segreteria, Ente e Admin")==false)
	          {    
	            throw new IllegalArgumentException("Query modifica stato non andata a buon fine");
	          }
	          /*//Reindirizzo alla home di studente
	          RequestDispatcher disp = request.getRequestDispatcher("/_areaStudent/HomeStudente.jsp");
	          disp.forward(request, response);*/
	      }
	      //Prelevo  il codice dall'id della tabella
	      else {
	      int codTirocinio = Integer.parseInt((String) request.getParameter("codTirocinio"));
	      if((String) request.getParameter("codTirocinio")==null)
	      {
	        throw new IllegalArgumentException("Codice tirocinio non valido.");
	      }
	      //Effettuo la query di upload
	      if(tirocinioDAO.uploadProgettoFormativo(codTirocinio, pdfPath)==false)
	      {
	        throw new IllegalArgumentException("Query upload 2 non andata a buon fine.");
	      }
	      //Sei la segreteria
	      if(userET==1)
	      {
	        stato = "Accettato e in attesa di firma dell' Ente e Admin";
	      }
	      //Sei l'admin
	      else if(userET==2)
	      {
	        stato = "Accettato e in attesa di firma dell' Admin";
	      }
	      //Sei l'ente
	      else if(userET==3)
	      {
	        stato = "Completata";
	      }
	      //Non sei nessuno
	      else
	      {
	        throw new IllegalArgumentException("Errore.");
	      }

	      if(tirocinioDAO.modificaStatoTirocinio(codTirocinio, "Accettato e in attesa di firma della Segreteria, Ente e Admin")==false)
	      {
	        throw new IllegalArgumentException("Query modifica stato non andata a buon fine");
	      }
	      //Reindirizzo a documenti da firmare
	      RequestDispatcher disp = request.getRequestDispatcher("DocumentiET.jsp");
	      disp.forward(request, response);
	    }
	  }
	}

}
