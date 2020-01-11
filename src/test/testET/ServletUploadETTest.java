package test.testET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import controller.ServletProgettoFormativoET;
import controller.ServletUploadET;
import model.Student;
import model.Tirocinio;
import model.DAO.TirocinioDAO;


class ServletUploadETTest {
	
	Connection conn = new DbConnection().getInstance().getConn();
	MockMultipartHttpServletRequest requestMock = new MockMultipartHttpServletRequest();
	MockHttpServletResponse responseMock = new MockHttpServletResponse();
	HttpSession sessionMock = mock(HttpSession.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	MockPart partMock = new MockPart("file", new byte[1024]);
	ServletUploadET test = new ServletUploadET();
	TirocinioDAO tirocinioDao = new TirocinioDAO();
	
	String sql1 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
	String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+new Date(0)+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
	String sql3 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
	String sql4 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
	String sql5 = ("INSERT INTO tirocinio VALUES('1','"+ new Date(0)+"','11','informatica','javascript','Java','Bene','In attesa dell Ente','','ragazzo valido','4859','11111111111');");

	
	@BeforeEach
	void setUp() {
		
		try {
			Statement stmtSelect = conn.createStatement();
			stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}
	
	//metodo tearDown per rimuovere i campi inseriti durante i test
	@AfterEach
	public void tearDown() {
		try 
		{
		    Statement stmtSelect = conn.createStatement();
		    String sql1 = ("DELETE FROM tirocinio WHERE CODTIROCINIO='1';");
		    stmtSelect.executeUpdate(sql1);
		    String sql2 = ("DELETE FROM tirocinante WHERE matricola='4859';");
		    stmtSelect.executeUpdate(sql2);
		    String sql3 = ("DELETE FROM enteconvenzionato WHERE partitaIva='11111111111';");
		    stmtSelect.executeUpdate(sql3);
		    String sql4 = ("DELETE FROM User WHERE email='p.aurilia@studenti.unisa.it';");
		    stmtSelect.executeUpdate(sql4);
		    String sql5 = ("DELETE FROM User WHERE email='green@gmail.com';");
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
		
	@Test
	void testUploadStudente() {
		
		Tirocinio test1=new Tirocinio();
		test1.setCodTirocinio(1);
		test1.setDataInizioTirocinio("1999-12-12");                  
		test1.setCfuPrevisti((short) 11);
		test1.setStatoTirocinio("In attesa Ente");
		test1.setCompetenze("reti");
		test1.setCompetenzeAcquisire("javaScript");
		test1.setAttivitaPreviste("java");
		test1.setSvolgimentoTirocinio("Bene");
		test1.setProgettoFormativo("");
		test1.setDescrizioneEnte("Ragazzo valido");
		test1.setMatricola(4859);
		test1.setPartitaIva("11111111111");
		
		try {

			when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
			requestMock.setSession(sessionMock);
			when(sessionMock.getAttribute("userET")).thenReturn("0");
			requestMock.addPart(partMock);
			
			test.doPost(requestMock, responseMock);
			assertEquals(tirocinioDao.tirocinioAttivo(4859).getProgettoFormativo(),"test");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUploadAdmin() {
		
		Tirocinio test1=new Tirocinio();
		test1.setCodTirocinio(1);
		test1.setDataInizioTirocinio("1999-12-12");                  
		test1.setCfuPrevisti((short) 11);
		test1.setStatoTirocinio("In attesa Ente");
		test1.setCompetenze("reti");
		test1.setCompetenzeAcquisire("javaScript");
		test1.setAttivitaPreviste("java");
		test1.setSvolgimentoTirocinio("Bene");
		test1.setProgettoFormativo("");
		test1.setDescrizioneEnte("Ragazzo valido");
		test1.setMatricola(4859);
		test1.setPartitaIva("11111111111");
		
		try {
			
			requestMock.addParameter("codTirocinio", "1");
			when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
			requestMock.setSession(sessionMock);
			when(sessionMock.getAttribute("userET")).thenReturn("2");
			requestMock.addPart(partMock);
			
			test.doPost(requestMock, responseMock);
			assertEquals(tirocinioDao.tirocinioAttivo(4859).getProgettoFormativo(),"test");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUploadSegreteria() {
		
		Tirocinio test1=new Tirocinio();
		test1.setCodTirocinio(1);
		test1.setDataInizioTirocinio("1999-12-12");                  
		test1.setCfuPrevisti((short) 11);
		test1.setStatoTirocinio("In attesa Ente");
		test1.setCompetenze("reti");
		test1.setCompetenzeAcquisire("javaScript");
		test1.setAttivitaPreviste("java");
		test1.setSvolgimentoTirocinio("Bene");
		test1.setProgettoFormativo("");
		test1.setDescrizioneEnte("Ragazzo valido");
		test1.setMatricola(4859);
		test1.setPartitaIva("11111111111");
		
		try {
			
			requestMock.addParameter("codTirocinio", "1");
			when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
			requestMock.setSession(sessionMock);
			when(sessionMock.getAttribute("userET")).thenReturn("1");
			requestMock.addPart(partMock);
			
			test.doPost(requestMock, responseMock);
			assertEquals(tirocinioDao.tirocinioAttivo(4859).getProgettoFormativo(),"test");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testUploadEnte() {
		
		Tirocinio test1=new Tirocinio();
		test1.setCodTirocinio(1);
		test1.setDataInizioTirocinio("1999-12-12");                  
		test1.setCfuPrevisti((short) 11);
		test1.setStatoTirocinio("In attesa Ente");
		test1.setCompetenze("reti");
		test1.setCompetenzeAcquisire("javaScript");
		test1.setAttivitaPreviste("java");
		test1.setSvolgimentoTirocinio("Bene");
		test1.setProgettoFormativo("");
		test1.setDescrizioneEnte("Ragazzo valido");
		test1.setMatricola(4859);
		test1.setPartitaIva("11111111111");
		
		try {

			requestMock.addParameter("codTirocinio", "1");
			when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
			requestMock.setSession(sessionMock);
			when(sessionMock.getAttribute("userET")).thenReturn("3");
			requestMock.addPart(partMock);
			
			test.doPost(requestMock, responseMock);
			assertEquals(tirocinioDao.tirocinioAttivo(4859).getProgettoFormativo(),"test");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



}
