package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.stubbing.OngoingStubbing;

import controller.DbConnection;
import controller.ServletRichiestaInizioTirocinioET;
import controller.ServletSceltaEnteET;
import model.Student;

class ServletSceltaEnteETTest {
	
	
	Connection conn = new DbConnection().getInstance().getConn();
	Date data=new Date();
	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(data);
	
	//Creazione mock	
		HttpServletRequest requestMock = mock(HttpServletRequest.class);
		HttpServletResponse responseMock = mock(HttpServletResponse.class);
		HttpSession sessionMock = mock(HttpSession.class);
		ServletSceltaEnteET servletSecretaryMock = mock(ServletSceltaEnteET.class);
		RequestDispatcher dispatcherSpy = spy(RequestDispatcher.class);
		Student student=new Student("p.aurilia@studenti.unisa.it","Pellegrino","Aurilia",'M',"pel98",0);
		
		
		@BeforeEach
		public void setUp() {
			when(requestMock.getSession()).thenReturn(sessionMock);
			when(sessionMock.getAttribute("userET")).thenReturn("0");
			when(sessionMock.getAttribute("user")).thenReturn(student);
		}

		//TC_GR_7.01:Campo ente vuoto
	@Test
	void testCampoEnteVuoto(){
		when(requestMock.getParameter("ente")).thenReturn("");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Ente e' vuoto",e.getMessage());
	}
	
	//TC_GR_7.02:Campo ente troppo lungo
		@Test
		void testCampoEnteTroppoLungo(){
			when(requestMock.getParameter("ente")).thenReturn("Carlo SRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSRLSR");
			ServletSceltaEnteET test = new ServletSceltaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo Ente supera la lunghezza consentita",e.getMessage());
		}
		
	//TC_GR_7.03:Campo ente non rispetta il formato
	@Test
	void testCampoEnteNonRispettaFormato(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo %20%  SRL2310");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Ente non rispetta il formato",e.getMessage());
	}
				
	//TC_GR_7.04:Campo nome vuoto
	@Test
	void testCampoNomeVuoto(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Nome e' vuoto",e.getMessage());
	}
	
	//TC_GR_7.05:Campo nome è troppo lungo
	@Test
	void testCampoNomeTroppoLungo(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("MarioMarioMarioMarioMarioMarioMarioMarioMarioMarioMarioMario");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Nome supera la lunghezza consentita",e.getMessage());
	}
	
	//TC_GR_7.06:Campo nome non rispetta il formato
	@Test
	void testCampoNomeNonRispettaFormato(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario2310");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Nome non rispetta il formato",e.getMessage());
	}
	
	//TC_GR_7.07:Campo cognome è vuoto
	@Test
	void testCampoCognomeVuoto(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Cognome e' vuoto",e.getMessage());
	}

	//TC_GR_7.08:Campo cognome è troppo lungo
	@Test
	void testCampoCognomeTroppoLungo(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("RossiRossiRossiRossiRossiRossiRossiRossiRossiRossiRossiRossi");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Cognome supera la lunghezza consentita",e.getMessage());
	}
	
	//TC_GR_7.09:Campo cognome non rispetta il formato
	@Test
	void testCampoCognomeNonRispettaFormato(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("Rossi2310");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Cognome non rispetta il formato",e.getMessage());
	}
	
	//TC_GR_7.10:Campo facoltà vuoto
	@Test
	void testCampoFacoltaVuoto(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("Rossi");
		when(requestMock.getParameter("facolta")).thenReturn("");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Facolta' e' vuoto",e.getMessage());
	}	
		
	//TC_GR_7.11:Campo facoltà supera la lunghezza massima
	@Test
	void testCampoFacoltaTroppoLunga(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("Rossi");
		when(requestMock.getParameter("facolta")).thenReturn("InformaticaInformaticaInformaticaInformaticaInformatica");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Facolta' supera la lunghezza consentita",e.getMessage());
	}
	
	//TC_GR_7.12:Campo facoltà non rispetta formato
	@Test
	void testCampoFacoltaNonRispettaFormato(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("Rossi");
		when(requestMock.getParameter("facolta")).thenReturn("Informatica2310");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Facolta' non rispetta il formato",e.getMessage());
	}
	
	//TC_GR_7.13:Campo descrizione è vuoto
	@Test
	void testCampoDescrizioneVuoto(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("Rossi");
		when(requestMock.getParameter("facolta")).thenReturn("Informatica");
	    when(requestMock.getParameter("descrizione")).thenReturn("");
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Descrizione e' vuoto",e.getMessage());
	}
	
	//TC_GR_7.14:Campo descrizione troppo lungo
	@Test
	void testCampoDescrizioneTroppoLungo(){
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("Rossi");
		when(requestMock.getParameter("facolta")).thenReturn("Informatica");
	    when(requestMock.getParameter("descrizione")).thenReturn("La comprensione, nella pratica, di quali sono i contenuti e le logiche di un sistema di controllo di gestione: il sistema direzionale, l’efficacia, l’efficienza, nonché gli attori in gioco e l'utilità che reca tale funzione all'azienda nel suo complesso.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	    ServletSceltaEnteET test = new ServletSceltaEnteET();
	    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Descrizione supera la lunghezza consentita",e.getMessage());
	}
	
	//Test case TC_GA_7.15: Scelta Ente effettuata correttamente
	@Test
	void testSceltaEnte() throws ServletException, IOException, SQLException {
		
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
		catch (Exception e1) {
		    e1.printStackTrace();
		}
		
		try 
		{
			Statement stmtSelect = conn.createStatement();
			String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
	    	stmtSelect.executeUpdate(sql4);
			String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
	    	stmtSelect.executeUpdate(sql2);
	    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
	    	stmtSelect.executeUpdate(sql5);
	    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
	    	stmtSelect.executeUpdate(sql3);
	    	String sql1 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','Accettato','progettoformativa.pdf','ragazzo valido','4859',NULL);");
	    	stmtSelect.executeUpdate(sql1);
	    	conn.commit();
	    	when(requestMock.getParameter("partitaIva")).thenReturn("11111111111");
			when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
			when(requestMock.getParameter("name")).thenReturn("Mario");
			when(requestMock.getParameter("cognome")).thenReturn("Rossi");
			when(requestMock.getParameter("facolta")).thenReturn("Informatica");
			when(requestMock.getParameter("descrizione")).thenReturn("La comprensione, nella pratica, di quali sono i contenuti e le logiche di un sistema di controllo di gestione: il sistema direzionale, l’efficacia, l’efficienza, nonché gli attori in gioco e utilità che reca tale funzione all azienda nel suo complesso.");
			when(requestMock.getRequestDispatcher("/_areaStudent/viewRequest.jsp")).thenReturn(dispatcherSpy);
			ServletSceltaEnteET test = new ServletSceltaEnteET();
			test.doPost(requestMock, responseMock);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		verify(dispatcherSpy).forward(requestMock,responseMock);
		String mess = null;
		verify(requestMock).setAttribute("L'invio della richiesta e' avvenuto con successo", mess);
		
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
}