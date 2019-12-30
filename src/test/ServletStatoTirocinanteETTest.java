package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import controller.ServletStatoTirocinanteET;

class ServletStatoTirocinanteETTest {
	Connection conn = new DbConnection().getInstance().getConn();
	//Creazione mock	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	ServletStatoTirocinanteET servletSecretaryMock = mock(ServletStatoTirocinanteET.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	@BeforeEach
	public void setUp() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userET")).thenReturn("0");
	}
	@Test
	void testReindirizzamento() throws ServletException, IOException {
		when(requestMock.getAttribute("matricola")).thenReturn("0512103333");
		when(requestMock.getRequestDispatcher("StatoProprioTirocinioET")).thenReturn(dispatcherMock);
		ServletStatoTirocinanteET test = new ServletStatoTirocinanteET();
		test.doPost(requestMock, responseMock);
	}
}
