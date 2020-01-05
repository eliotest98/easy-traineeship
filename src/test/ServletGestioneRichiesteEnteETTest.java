package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;

import controller.DbConnection;
import controller.ServletGestioneRichiesteEnteET;

class ServletGestioneRichiesteEnteETTest {
	
	Connection conn = new DbConnection().getInstance().getConn();
	//Creazione mock	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	ServletGestioneRichiesteEnteET servletSecretaryMock = mock(ServletGestioneRichiesteEnteET.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	@BeforeEach
	public void setUp() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userET")).thenReturn("3");
	}
	//Test Reindirizzamento (Flag 1)
	@Test
	void testReindirizzamento() throws ServletException, IOException {
		when(requestMock.getParameter("flag")).thenReturn("1");
		when(requestMock.getRequestDispatcher("_areaEnteET/VisualizzaRichiestaEnteET.jsp")).thenReturn(dispatcherMock);
		ServletGestioneRichiesteEnteET test = new ServletGestioneRichiesteEnteET();
		test.doGet(requestMock, responseMock);
		verify(dispatcherMock).forward(requestMock, responseMock);
	}
	//Test Accettazione (flag 2)
	@Test
	void testAccettazione() throws ServletException, IOException {
		when(requestMock.getParameter("flag")).thenReturn("2");
		when(requestMock.getParameter("codice")).thenReturn("45");
		when(requestMock.getRequestDispatcher("_areaEnteET/VisualizzaRichiestaEnteET.jsp")).thenReturn(dispatcherMock);
		ServletGestioneRichiesteEnteET test = new ServletGestioneRichiesteEnteET();
		test.doGet(requestMock, responseMock);
		verify(dispatcherMock).forward(requestMock, responseMock);
	}
	//Test Rifiuto (flag 3)
	@Test
	void testRifiuto() throws ServletException, IOException {
		when(requestMock.getParameter("flag")).thenReturn("3");
		when(requestMock.getParameter("codice")).thenReturn("45");
		when(requestMock.getParameter("Motivazione")).thenReturn("Hai scritto male");
		when(requestMock.getRequestDispatcher("_areaEnteET/VisualizzaRichiestaEnteET.jsp")).thenReturn(dispatcherMock);
		ServletGestioneRichiesteEnteET test = new ServletGestioneRichiesteEnteET();
		test.doGet(requestMock, responseMock);
		verify(dispatcherMock).forward(requestMock, responseMock);
	}
}

