package test.testIntegrazioneET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import controller.ServletGestioneRichiesteEnteET;
import controller.ServletStatoTirocinioET;
import model.Tirocinio;

class ServletStatoTirocinioETTest {
	Connection conn = new DbConnection().getInstance().getConn();
	//Creazione mock	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	ServletStatoTirocinioET test = new ServletStatoTirocinioET();
	Tirocinio tirocinio = new Tirocinio();
	
	String sql1 = ("INSERT INTO User VALUES('t.tester@studenti.unisa.it','Tester','Amendola','M','salvo','0');");
	String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+new Date(0)+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','t.tester@studenti.unisa.it');");
	String sql3 = ("INSERT INTO User VALUES('test@test.test','Salvatore','Totti','M','pass98','3');");
	String sql4 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','test@test.test');");


	@BeforeEach
	void setUp() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getParameter("matricola")).thenReturn("4859");
		when(requestMock.getRequestDispatcher("/_areaSecretary/VisualizzaStatoTirocinioET.jsp")).thenReturn(dispatcherMock);
	}
	
	
	@AfterEach
	public void tearDown() {
		try 
		{

				Statement stmtSelect = conn.createStatement();
			    String sql1 = ("DELETE FROM tirocinio WHERE CODTIROCINIO='999';");
			    stmtSelect.executeUpdate(sql1);
			    String sql2 = ("DELETE FROM tirocinante WHERE matricola='4859';");
			    stmtSelect.executeUpdate(sql2);
			    String sql3 = ("DELETE FROM enteconvenzionato WHERE partitaIva='11111111111';");
			    stmtSelect.executeUpdate(sql3);
			    String sql4 = ("DELETE FROM User WHERE email='t.tester@studenti.unisa.it';");
			    stmtSelect.executeUpdate(sql4);
			    String sql5 = ("DELETE FROM User WHERE email='test@test.test';");
		    	stmtSelect.executeUpdate(sql5);
		    	conn.commit();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	//Test ServletStatoTirocinioET se lo studente non ha ancora selezionato un ente
	@Test
	void testReindirizzamentoSenzaEnte() throws ServletException, IOException {
		
		try 
		{
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	String sql5 = ("INSERT INTO tirocinio VALUES('999','"+new Date(0)+"','11','informatica','javascript','Java','Bene','In attesa Ente','progettoformativa.pdf','ragazzo valido','4859',"+tirocinio.getPartitaIva()+");");
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		test.doGet(requestMock, responseMock);
		verify(dispatcherMock).forward(requestMock, responseMock);
	}
	
	//Test ServletStatoTirocinioET se lo studente ha selezionato un ente
	@Test
	void testReindirizzamentoConEnte() throws ServletException, IOException {
		
		try 
		{
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	String sql5 = ("INSERT INTO tirocinio VALUES('999','"+new Date(0)+"','11','informatica','javascript','Java','Bene','In attesa Ente','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		test.doGet(requestMock, responseMock);
		verify(dispatcherMock).forward(requestMock, responseMock);
	}
	
}
