package test;

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
import controller.ServletSceltaEnteET;

class ServletSceltaEnteETTest {
	
	
	Connection conn = new DbConnection().getInstance().getConn();
	
	//Creazione mock	
		HttpServletRequest requestMock = mock(HttpServletRequest.class);
		HttpServletResponse responseMock = mock(HttpServletResponse.class);
		HttpSession sessionMock = mock(HttpSession.class);
		ServletSceltaEnteET servletSecretaryMock = mock(ServletSceltaEnteET.class);
		RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
		
		
		@BeforeEach
		public void setUp() {
			when(requestMock.getSession()).thenReturn(sessionMock);
			when(sessionMock.getAttribute("userET")).thenReturn("0");
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
	    when(requestMock.getParameter("descrizione")).thenReturn("La comprensione, nella pratica, di quali sono i contenuti e le logiche di un sistema di controllo di gestione: il sistema direzionale, l’efficacia, l’efficienza, nonché gli attori in gioco e l'utilità che reca tale funzione all'azienda nel suo complesso. \r\n" + 
		"\r\n" + 
		"La comprensione, nella pratica, di quali sono i contenuti e le logiche di un sistema di controllo di gestione: il sistema direzionale, l’efficacia, l’efficienza, nonché gli attori in gioco e l'utilità che reca tale funzione all'azienda nel suo complesso…");
	    ServletSceltaEnteET test = new ServletSceltaEnteET();
	    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo Descrizione supera la lunghezza consentita",e.getMessage());
	}
	//Test case TC_GA_7.15: Scelta Ente effettuata correttamente
	@Test
	void testSceltaEnte() throws ServletException, IOException {
		when(requestMock.getParameter("ente")).thenReturn("Carlo SRL");
		when(requestMock.getParameter("name")).thenReturn("Mario");
		when(requestMock.getParameter("cognome")).thenReturn("Rossi");
		when(requestMock.getParameter("facolta")).thenReturn("Informatica");
		when(requestMock.getParameter("descrizione")).thenReturn("La comprensione, nella pratica, di quali sono i contenuti e le logiche di un sistema di controllo di gestione: il sistema direzionale, l’efficacia, l’efficienza, nonché gli attori in gioco e l'utilità che reca tale funzione all'azienda nel suo complesso.");
		when(requestMock.getRequestDispatcher("risultato.jsp")).thenReturn(dispatcherMock);
		ServletSceltaEnteET test = new ServletSceltaEnteET();
		test.doPost(requestMock, responseMock);
		assertEquals("L'invio della richiesta e' avvenuto con successo",requestMock.getParameter("L'invio della richiesta e' avvenuto con successo"));
	}
}