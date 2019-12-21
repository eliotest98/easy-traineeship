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
import org.junit.jupiter.api.*;
import controller.DbConnection;
import controller.ServletRegistrazioneEnteET;

class ServletRegistrazioneEnteETTest {

	Connection conn = new DbConnection().getInstance().getConn();

	// Creazione mock
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	ServletRegistrazioneEnteET servletSecretaryMock = mock(ServletRegistrazioneEnteET.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

	@BeforeEach
	public void setUp() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userET")).thenReturn("1");
	}

	// Test case TC_GA_7.01: Campo Nome vuoto
	@Test
	void testCampoNomeVuoto() {
		when(requestMock.getParameter("name")).thenReturn("");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Nome Ente' &egrave vuoto", e.getMessage());
	}

	// Test case TC_GA_7.02: Campo Nome troppo lungo
	@Test
	void testCampoNomeTroppoLungo() {
		when(requestMock.getParameter("name"))
				.thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Nome Ente' supera la lunghezza consentita", e.getMessage());
	}

	// Test case TC_GA_7.03: Campo Nome non rispetta il formato
	@Test
	void testCampoNomeFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech@");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Nome Ente' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.04: Campo Rappresentante vuoto
	@Test
	void testCampoRappresentanteVuoto() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Nome Rappresentante' &egrave vuoto", e.getMessage());
	}

	// Test case TC_GA_7.05: Campo Rappresentante troppo lungo
	@Test
	void testCampoRappresentanteTroppoLungo() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante"))
				.thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Nome Rappresentante' supera la lunghezza consentita", e.getMessage());
	}

	// Test case TC_GA_7.06: Campo Rappresentante non rispetta il formato
	@Test
	void testCampoRappresentanteFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano@");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Nome Rappresentante' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.07: Campo Campo Data di Nascita non rispetta il formato
	@Test
	void testCampoDataDiNascitaFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/19798");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Data di Nascita' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.08: Campo Numero Di Dipedenti troppo lungo
	@Test
	void testCampoNumeroDiDipendentiFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100°");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Numero di Dipendenti' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.09: Campo Professore di Riferimento vuoto
	@Test
	void testCampoProfessorediRiferimentoVuoto() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Professore di Riferimento' &egrave vuoto", e.getMessage());
	}

	// Test case TC_GA_7.10: Campo Professore di Riferimento troppo lungo
	@Test
	void testCampoProfessorediRiferimentoTroppoLungo() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento"))
				.thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Professore di Riferimento' supera la lunghezza consentita", e.getMessage());
	}

	// Test case TC_GA_7.11: Campo Professore di Riferimento non rispetta il formato
	@Test
	void testCampoProfessorediRiferimentoFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carm43ne");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Professore di Riferimento' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.12: Campo E-mail vuoto
	@Test
	void testCampoEmailVuoto() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'E-mail' &egrave vuoto", e.getMessage());
	}

	// Test case TC_GA_7.13: Campo E-mail troppo lungo
	@Test
	void testCampoEmailTroppoLungo() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email"))
				.thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'E-mail' supera la lunghezza consentita", e.getMessage());
	}

	// Test case TC_GA_7.14: Campo E-mail non rispetta il formato
	@Test
	void testCampoEmailFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@@gmail.com");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'E-mail' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.15: Campo Sede vuoto
	@Test
	void testCampoSedeVuoto() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Sede' &egrave vuoto", e.getMessage());
	}

	// Test case TC_GA_7.16: Campo Sede troppo lungo
	@Test
	void testCampoSedeTroppoLungo() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede"))
				.thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Sede' supera la lunghezza consentita", e.getMessage());
	}

	// Test case TC_GA_7.17: Campo Sede non rispetta il formato
	@Test
	void testCampoSedeFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano@");

		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Sede' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.18: Campo Referente Tirocini vuoto
	@Test
	void testCampoReferenteTirociniVuoto() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano");
		when(requestMock.getParameter("referente")).thenReturn("");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Referente Tirocini' &egrave vuoto", e.getMessage());
	}

	// Test case TC_GA_7.19: Campo Referente Tirocini troppo lungo
	@Test
	void testCampoReferenteTirociniTroppoLungo() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano");
		when(requestMock.getParameter("referente"))
				.thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Referente Tirocini' supera la lunghezza consentita", e.getMessage());
	}

	// Test case TC_GA_7.20: Campo Referente Tirocini non rispetta il formato
	@Test
	void testCampoReferenteTirociniFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano");
		when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori432");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Referente Tirocini' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.21: Campo Telefono non rispetta il formato
	@Test
	void testCampoTelefonoFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano");
		when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
		when(requestMock.getParameter("telefono")).thenReturn("012345678910");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Numero di Telefono' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.22: Campo Descrizione Attività vuoto
	@Test
	void testCampoDescrizioneAttivitaVuoto() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano");
		when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
		when(requestMock.getParameter("telefono")).thenReturn("0123456789");
		when(requestMock.getParameter("descrizioneAttivita")).thenReturn("");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Descrizione delle Attivit&agrave' &egrave vuoto", e.getMessage());
	}

	// Test case TC_GA_7.23: Campo Descrizione Attività troppo lungo
	@Test
	void testCampoDescrizioneAttivitaTroppoLungo() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano");
		when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
		when(requestMock.getParameter("telefono")).thenReturn("0123456789");
		when(requestMock.getParameter("descrizioneAttivita")).thenReturn(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Descrizione delle Attivit&agrave' supera la lunghezza consentita", e.getMessage());
	}

	// Test case TC_GA_7.24: Campo Partita IVA non rispetta il formato
	@Test
	void testCampoPartitaIvaFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano");
		when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
		when(requestMock.getParameter("telefono")).thenReturn("0123456789");
		when(requestMock.getParameter("descrizioneAttivita"))
				.thenReturn("L’ampia esperienza maturata e le capacità progettuali "
						+ "sono gli strumenti che mettiamo a disposizione delle  aziende nostre clienti per aumentarne la "
						+ "produttività e l’efficienza interna, e per migliorarne sensibilmente il rapporto con la propria clientela");
		when(requestMock.getParameter("partitaIva")).thenReturn("01234");
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> test.doPost(requestMock, responseMock));
		assertEquals("Il campo 'Partita IVA' non rispetta il formato", e.getMessage());
	}

	// Test case TC_GA_7.25: Registrazione Ente effettuata correttamente
	@Test
	void testRegistrazioneCorretta() throws ServletException, IOException {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
		when(requestMock.getParameter("dataDiNascita")).thenReturn("03/04/1979");
		when(requestMock.getParameter("dipendenti")).thenReturn("100");
		when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
		when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
		when(requestMock.getParameter("sede")).thenReturn("Milano");
		when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
		when(requestMock.getParameter("telefono")).thenReturn("0123456789");
		when(requestMock.getParameter("descrizioneAttivita"))
				.thenReturn("L’ampia esperienza maturata e le capacità progettuali "
						+ "sono gli strumenti che mettiamo a disposizione delle  aziende nostre clienti per aumentarne la "
						+ "produttività e l’efficienza interna, e per migliorarne sensibilmente il rapporto con la propria clientela");
		when(requestMock.getParameter("partitaIva")).thenReturn("12312312312");
		when(requestMock.getRequestDispatcher("risultato.jsp")).thenReturn(dispatcherMock);
		ServletRegistrazioneEnteET test = new ServletRegistrazioneEnteET();
		test.doPost(requestMock, responseMock);

		try {
			Statement stmtSelect = conn.createStatement();
			stmtSelect.executeUpdate("DELETE FROM User WHERE EMAIL='greentech@gmail.com';");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
