package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;

import controller.DbConnection;
import controller.ServletSecretary;

class ServletSecretaryTest {
	
	Connection conn = new DbConnection().getInstance().getConn();
    String sql = "";
	
	//Creazione mock	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	ServletSecretary servletSecretaryMock = mock(ServletSecretary.class);
	
	
	@BeforeEach
	public void setUp() {
		when(requestMock.getParameter("idRequest")).thenReturn("1");
		try {
	    	Statement stmtSelect = conn.createStatement();
	    	sql = ("INSERT INTO request VALUES('1','1','B2','2018-05-25','2019-05-25','2019','6','457465719','3','a.serritiello7@studenti.unisa.it','7','2');");
	    	stmtSelect.executeUpdate(sql);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	@AfterEach
	void tearDown() {
		try {
	    	Statement stmtSelect = conn.createStatement();
	    	sql = ("DELETE FROM REQUEST;");
	    	stmtSelect.executeUpdate(sql);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	@Test
	void testNoRichieste() throws ServletException, IOException {
		try {
	    	Statement stmtSelect = conn.createStatement();
	    	sql = ("DELETE FROM REQUEST;");
	    	stmtSelect.executeUpdate(sql);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		when(requestMock.getParameter("flag")).thenReturn("1");
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(responseMock.getWriter()).thenReturn(writer);
		
		ServletSecretary test = new ServletSecretary();
		test.doPost(requestMock, responseMock);
		System.out.println(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("Nessuna Richiesta Presente"));
	}
	
	@Test
	void testRichiestePresenti() throws ServletException, IOException {
		when(requestMock.getParameter("flag")).thenReturn("1");
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(responseMock.getWriter()).thenReturn(writer);
		
		ServletSecretary test = new ServletSecretary();
		test.doPost(requestMock, responseMock);
		System.out.println(stringWriter.toString());
		assertTrue(stringWriter.toString().contains("\"result\":1,\"error\":\""));
	}
	
	@Test
	void testSetCfuPass() throws ServletException, IOException {
		when(requestMock.getParameter("flag")).thenReturn("2");
		when(requestMock.getParameter("cfu")).thenReturn("3");
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(responseMock.getWriter()).thenReturn(writer);
		
		ServletSecretary test = new ServletSecretary();
		test.doPost(requestMock, responseMock);
		System.out.println(stringWriter.toString());
		assertTrue((stringWriter.toString().contains("CFU aggiornati con successo.")));
	}
	
	@Test
	void testSetCfuFail() throws ServletException, IOException {
		when(requestMock.getParameter("flag")).thenReturn("2");
		when(requestMock.getParameter("cfu")).thenReturn("3");
		when(requestMock.getParameter("idRequest")).thenReturn("2");
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(responseMock.getWriter()).thenReturn(writer);
		
		ServletSecretary test = new ServletSecretary();
		test.doPost(requestMock, responseMock);
		System.out.println(stringWriter.toString());
		assertTrue(stringWriter.toString().contains(" Impossibile cambiare i CFU nella richiesta."));
	}

	@Test
	void testInoltraAdminPass() throws ServletException, IOException {
		when(requestMock.getParameter("flag")).thenReturn("3");
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(responseMock.getWriter()).thenReturn(writer);
		
		ServletSecretary test = new ServletSecretary();
		test.doPost(requestMock, responseMock);
		System.out.println(stringWriter.toString());
		assertTrue((stringWriter.toString().contains("Richiesta inoltrata all'amministratore con successo.")));
	}
	
	@Test
	void testInoltraAdminFail() throws ServletException, IOException {
		when(requestMock.getParameter("flag")).thenReturn("3");
		when(requestMock.getParameter("idRequest")).thenReturn("2");
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(responseMock.getWriter()).thenReturn(writer);
		
		ServletSecretary test = new ServletSecretary();
		test.doPost(requestMock, responseMock);
		System.out.println(stringWriter.toString());
		assertTrue((stringWriter.toString().contains(" Impossibile inoltrare la richiesta.")));
	}
}
