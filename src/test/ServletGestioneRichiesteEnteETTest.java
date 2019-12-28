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
	@Test
	void testReindirizzamento() {
		when(requestMock.getRequestDispatcher("VisualizzaRichiestaEnteET.jsp")).thenReturn(dispatcherMock);
		ServletGestioneRichiesteEnteET test = new ServletGestioneRichiesteEnteET();
		try {
			test.doGet(requestMock, responseMock);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

