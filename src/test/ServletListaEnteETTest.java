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
import controller.ServletListaEnteET;

class ServletListaEnteETTest {
	
	Connection conn = new DbConnection().getInstance().getConn();
	//Creazione mock	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	ServletListaEnteET servletSecretaryMock = mock(ServletListaEnteET.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

	@Test
	void testReindirizzamentoVisualizzaListaEnte() {
		when(requestMock.getRequestDispatcher("VisualizzaEnteET.jsp")).thenReturn(dispatcherMock);
		ServletListaEnteET test = new ServletListaEnteET();
		try {
			test.doGet(requestMock, responseMock);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	void testReindirizzamentoInviaRichiestaEnte() {
		when(requestMock.getParameter("richiestaEnte")).thenReturn("1");
		when(requestMock.getRequestDispatcher("_areaStudent/InviaRichiestaEnteET.jsp")).thenReturn(dispatcherMock);
		ServletListaEnteET test = new ServletListaEnteET();
		try {
			test.doGet(requestMock, responseMock);
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

