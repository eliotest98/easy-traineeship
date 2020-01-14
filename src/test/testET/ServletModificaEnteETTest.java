package test.testET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
import controller.ServletModificaEnteET;

class ServletModificaEnteETTest {

Connection conn = new DbConnection().getInstance().getConn();
	
	//Creazione mock	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	ServletModificaEnteET servletSecretaryMock = mock(ServletModificaEnteET.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	
	
	@BeforeEach
	public void setUp() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userET")).thenReturn("1");
		try 
		{
			Statement stmtSelect = conn.createStatement();
			String sql4 = ("INSERT INTO User VALUES('greentech@gmail.com','Tester','Amendola','M','salvo','0');");
	    	stmtSelect.executeUpdate(sql4);
	    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('12312312312','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','greentech@gmail.com');");
	    	stmtSelect.executeUpdate(sql3);
	    	
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	//Test case TC_GA_8.01: Campo Nome vuoto
	@Test
	void testCampoNomeVuoto() {
		when(requestMock.getParameter("name")).thenReturn("");
		ServletModificaEnteET test = new ServletModificaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo 'Nome Ente' &egrave vuoto",e.getMessage());
	}
	
	//Test case TC_GA_8.02: Campo Nome troppo lungo
	@Test
	void testCampoNomeTroppoLungo() {
		when(requestMock.getParameter("name")).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletModificaEnteET test = new ServletModificaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo 'Nome Ente' supera la lunghezza consentita",e.getMessage());
	}
	
	//Test case TC_GA_8.03: Campo Nome non rispetta il formato
	@Test
	void testCampoNomeFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech@");
		ServletModificaEnteET test = new ServletModificaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo 'Nome Ente' non rispetta il formato",e.getMessage());
	}
		
	//Test case TC_GA_8.04: Campo Rappresentante vuoto
	@Test
	void testCampoRappresentanteVuoto() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("");
		ServletModificaEnteET test = new ServletModificaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo 'Nome Rappresentante' &egrave vuoto",e.getMessage());
	}
		
	//Test case TC_GA_8.05: Campo Rappresentante troppo lungo
	@Test
	void testCampoRappresentanteTroppoLungo() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ServletModificaEnteET test = new ServletModificaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo 'Nome Rappresentante' supera la lunghezza consentita",e.getMessage());
	}
		
	//Test case TC_GA_8.06: Campo Rappresentante non rispetta il formato
	@Test
	void testCampoRappresentanteFormatoErrato() {
		when(requestMock.getParameter("name")).thenReturn("GreenTech");
		when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano@");
		ServletModificaEnteET test = new ServletModificaEnteET();
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
		assertEquals("Il campo 'Nome Rappresentante' non rispetta il formato",e.getMessage());
	}
	
	//Test case TC_GA_8.07: Campo Campo Data di Nascita non rispetta il formato
		@Test
		void testCampoDataDiNascitaFormatoErrato() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("19798-12-04");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Data di Nascita' non rispetta il formato",e.getMessage());
		}
			
		//Test case TC_GA_8.08: Campo Numero Di Dipedenti troppo lungo
		@Test
		void testCampoNumeroDiDipendentiFormatoErrato() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100°");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Numero di Dipendenti' non rispetta il formato",e.getMessage());
		}
			
		//Test case TC_GA_8.09: Campo Professore di Riferimento vuoto
		@Test
		void testCampoProfessorediRiferimentoVuoto() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Professore di Riferimento' &egrave vuoto",e.getMessage());
		}
		
		//Test case TC_GA_8.10: Campo Professore di Riferimento troppo lungo
		@Test
		void testCampoProfessorediRiferimentoTroppoLungo() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Professore di Riferimento' supera la lunghezza consentita",e.getMessage());
		}
		//Test case TC_GA_8.11: Campo Professore di Riferimento non rispetta il formato
		@Test
		void testCampoProfessorediRiferimentoFormatoErrato() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carm43ne");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Professore di Riferimento' non rispetta il formato",e.getMessage());
		}
		
		//Test case TC_GA_8.12: Campo E-mail vuoto
		@Test
		void testCampoEmailVuoto() {
				when(requestMock.getParameter("name")).thenReturn("GreenTech");
				when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
				when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
				when(requestMock.getParameter("dipendenti")).thenReturn("100");
				when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
				when(requestMock.getParameter("email")).thenReturn("");
				ServletModificaEnteET test = new ServletModificaEnteET();
				IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
				assertEquals("Il campo 'E-mail' &egrave vuoto",e.getMessage());
			}
				
		//Test case TC_GA_8.13: Campo E-mail troppo lungo
		@Test
		void testCampoEmailTroppoLungo() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'E-mail' supera la lunghezza consentita",e.getMessage());
		}
		
		//Test case TC_GA_8.14: Campo E-mail non rispetta il formato
		@Test
		void testCampoEmailFormatoErrato() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@@gmail.com");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'E-mail' non rispetta il formato",e.getMessage());
		}
		
		//Test case TC_GA_8.15: Campo Sede vuoto
		@Test
		void testCampoSedeVuoto() {
				when(requestMock.getParameter("name")).thenReturn("GreenTech");
				when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
				when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
				when(requestMock.getParameter("dipendenti")).thenReturn("100");
				when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
				when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
				when(requestMock.getParameter("sede")).thenReturn("");
				ServletModificaEnteET test = new ServletModificaEnteET();
				IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
				assertEquals("Il campo 'Sede' &egrave vuoto",e.getMessage());
			}
						
		//Test case TC_GA_8.16: Campo Sede troppo lungo
		@Test
		void testCampoSedeTroppoLungo() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Sede' supera la lunghezza consentita",e.getMessage());
		}
		
		//Test case TC_GA_8.17: Campo Sede non rispetta il formato
		@Test
		void testCampoSedeFormatoErrato() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("Milano@");
			
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Sede' non rispetta il formato",e.getMessage());
		}
		
		//Test case TC_GA_8.18: Campo Referente Tirocini vuoto
		@Test
		void testCampoReferenteTirociniVuoto() {
				when(requestMock.getParameter("name")).thenReturn("GreenTech");
				when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
				when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
				when(requestMock.getParameter("dipendenti")).thenReturn("100");
				when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
				when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
				when(requestMock.getParameter("sede")).thenReturn("Milano");
				when(requestMock.getParameter("referente")).thenReturn("");
				ServletModificaEnteET test = new ServletModificaEnteET();
				IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
				assertEquals("Il campo 'Referente Tirocini' &egrave vuoto",e.getMessage());
			}
				
		//Test case TC_GA_8.19: Campo Referente Tirocini troppo lungo
		@Test
		void testCampoReferenteTirociniTroppoLungo() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("Milano");
			when(requestMock.getParameter("referente")).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Referente Tirocini' supera la lunghezza consentita",e.getMessage());
		}
		
		//Test case TC_GA_8.20: Campo Referente Tirocini non rispetta il formato
		@Test
		void testCampoReferenteTirociniFormatoErrato() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("Milano");
			when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori432");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Referente Tirocini' non rispetta il formato",e.getMessage());
		}
		
		//Test case TC_GA_8.21: Campo Telefono non rispetta il formato
		@Test
		void testCampoTelefonoFormatoErrato() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("Milano");
			when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
			when(requestMock.getParameter("telefono")).thenReturn("012345678910");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Numero di Telefono' non rispetta il formato",e.getMessage());
		}
				
		//Test case TC_GA_8.22: Campo Descrizione Attività vuoto
		@Test
		void testCampoDescrizioneAttivitaVuoto() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("Milano");
			when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
			when(requestMock.getParameter("telefono")).thenReturn("0123456789");
			when(requestMock.getParameter("descrizioneAttivita")).thenReturn("");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Descrizione delle Attivit&agrave' &egrave vuoto",e.getMessage());
		}
		
		//Test case TC_GA_8.23: Campo Descrizione Attività troppo lungo
		@Test
		void testCampoDescrizioneAttivitaTroppoLungo() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("Milano");
			when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
			when(requestMock.getParameter("telefono")).thenReturn("0123456789");
			when(requestMock.getParameter("descrizioneAttivita")).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Descrizione delle Attivit&agrave' supera la lunghezza consentita",e.getMessage());
		}
		
		//Test case TC_GA_8.24: Campo Partita IVA non rispetta il formato
		@Test
		void testCampoPartitaIvaFormatoErrato() {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("Milano");
			when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
			when(requestMock.getParameter("telefono")).thenReturn("0123456789");
			when(requestMock.getParameter("descrizioneAttivita")).thenReturn("L’ampia esperienza maturata e le capacità progettuali " +
					"sono gli strumenti che mettiamo a disposizione delle  aziende nostre clienti per aumentarne la " +  
					"produttività e l’efficienza interna, e per migliorarne sensibilmente il rapporto con la propria clientela");
			when(requestMock.getParameter("partitaIva")).thenReturn("01234");
			ServletModificaEnteET test = new ServletModificaEnteET();
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Il campo 'Partita IVA' non rispetta il formato",e.getMessage());
		}
		
		//Test case TC_GA_8.25: Modifica Ente effettuata correttamente
		@Test
		void testModificaCorretta() throws ServletException, IOException {
			when(requestMock.getParameter("name")).thenReturn("GreenTech");
			when(requestMock.getParameter("rappresentante")).thenReturn("Fabio Napolitano");
			when(requestMock.getParameter("dataDiNascita")).thenReturn("1979-12-04");
			when(requestMock.getParameter("dipendenti")).thenReturn("100");
			when(requestMock.getParameter("dotRiferimento")).thenReturn("Gravino Carmine");
			when(requestMock.getParameter("email")).thenReturn("greentech@gmail.com");
			when(requestMock.getParameter("sede")).thenReturn("Milano");
			when(requestMock.getParameter("referente")).thenReturn("Pasqualina Montuori");
			when(requestMock.getParameter("telefono")).thenReturn("0123456789");
			when(requestMock.getParameter("descrizioneAttivita")).thenReturn("L’ampia esperienza maturata e le capacità progettuali " +
					"sono gli strumenti che mettiamo a disposizione delle  aziende nostre clienti per aumentarne la " +  
					"produttività e l’efficienza interna, e per migliorarne sensibilmente il rapporto con la propria clientela");
			when(requestMock.getParameter("partitaIva")).thenReturn("12312312312");
			when(requestMock.getRequestDispatcher("VisualizzaEnteET.jsp")).thenReturn(dispatcherMock);
			ServletModificaEnteET test = new ServletModificaEnteET();
			test.doPost(requestMock, responseMock);
			verify(responseMock).sendRedirect(requestMock.getContextPath()+"/VisualizzaEnteET.jsp?cod=3");
			try {
				Statement stmtSelect = conn.createStatement();
		    	stmtSelect.executeUpdate("DELETE FROM User WHERE EMAIL='greentech@gmail.com';");
		    	conn.commit();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
}
