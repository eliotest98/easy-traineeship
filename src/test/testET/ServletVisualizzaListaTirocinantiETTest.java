package test.testET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import controller.ServletListaEnteET;
import controller.ServletVisualizzaListaTirocinantiET;

class ServletVisualizzaListaTirocinantiETTest {

	Connection conn = new DbConnection().getInstance().getConn();
	//Creazione mock	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	ServletVisualizzaListaTirocinantiET servletSecretaryMock = mock(ServletVisualizzaListaTirocinantiET.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	@BeforeEach
	public void setUp() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userET")).thenReturn("1");
	}
	@Test
	void testReindirizzamentoVisualizzaListaTirocinanti() throws ServletException, IOException {
		when(requestMock.getRequestDispatcher("/_areaSecretary/VisualizzaListaTirocinantiET.jsp")).thenReturn(dispatcherMock);
		ServletVisualizzaListaTirocinantiET test = new ServletVisualizzaListaTirocinantiET();
		test.doGet(requestMock, responseMock);
		verify(dispatcherMock).forward(requestMock, responseMock);
	}
	
}
