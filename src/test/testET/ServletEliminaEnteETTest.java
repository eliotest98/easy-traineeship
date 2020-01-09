package test.testET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import controller.ServletEliminaEnteET;
import controller.ServletGestioneRichiesteEnteET;


class ServletEliminaEnteETTest {

	Connection conn = new DbConnection().getInstance().getConn();
	//Creazione mock	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	ServletEliminaEnteET servletSecretaryMock = mock(ServletEliminaEnteET.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	
	@AfterEach
	public void tearDown() {
		try 
		{
		    Statement stmtSelect = conn.createStatement();
		    String sql3 = ("DELETE FROM enteconvenzionato WHERE partitaIva='11111111111';");
		    stmtSelect.executeUpdate(sql3);
		    String sql5 = ("DELETE FROM User WHERE email='green@gmail.com';");
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	@Test
	void testTrue() throws ServletException, IOException  {
		try {
		Statement stmtSelect = conn.createStatement();
		String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
    	stmtSelect.executeUpdate(sql5);
    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
    	stmtSelect.executeUpdate(sql3);
    	conn.commit();
    	when(requestMock.getParameter("enteEmail")).thenReturn("green@gmail.com");
     	when(requestMock.getRequestDispatcher("VisualizzaEnteET.jsp")).thenReturn(dispatcherMock);
     	ServletEliminaEnteET test = new ServletEliminaEnteET();
	
		test.doPost(requestMock,responseMock);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		
		/*
		String ris= (String) requestMock.getAttribute("result");           
		System.out.println(ris);
		b = Boolean.parseBoolean(ris);
		
		}*/
		catch (SQLException e) {
			e.printStackTrace();
	}
	
}
	
	@Test
	void testFalse() throws ServletException, IOException  {
		try {
		Statement stmtSelect = conn.createStatement();
		String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
    	stmtSelect.executeUpdate(sql5);
    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','');");
    	stmtSelect.executeUpdate(sql3);
    	conn.commit();
    	when(requestMock.getParameter("enteEmail")).thenReturn("green@gmail.com");
     	when(requestMock.getRequestDispatcher("VisualizzaEnteET.jsp")).thenReturn(dispatcherMock);
     	ServletEliminaEnteET test = new ServletEliminaEnteET();
	
		test.doPost(requestMock,responseMock);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
		
		/*
		String ris= (String) requestMock.getAttribute("result");           
		System.out.println(ris);
		b = Boolean.parseBoolean(ris);
		
		}*/
		catch (SQLException e) {
			e.printStackTrace();
	}
	
}
}
