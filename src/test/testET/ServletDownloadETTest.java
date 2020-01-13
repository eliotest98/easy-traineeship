package test.testET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;


import controller.ServletDownloadET;
import model.Tirocinio;


	

class ServletDownloadETTest {
	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	MockHttpServletResponse responseMock = new MockHttpServletResponse();
	HttpSession sessionMock = mock(HttpSession.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	ServletDownloadET test = new ServletDownloadET();
	InputStream is = mock(InputStream.class);
	File prova  = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Documents\\GitHub\\easy-traineeship\\ProgettoFormativo\\test.pdf");
	
	//Metodo setUp()
	@BeforeEach 
	void setUp() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userET")).thenReturn("3");
		try {
			prova.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Metodo tearDown()
	@AfterEach
	void tearDown() {
		prova.delete();
	}
	
	@Test
	void testDownloadETFail() {
		try {
			when(sessionMock.getAttribute("Tirocinio")).thenReturn(null);
			when(requestMock.getParameter("cod")).thenReturn("1");
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()->test.doPost(requestMock,responseMock));
			assertEquals("Errore nel codice del tirocinio",e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testDownloadET() {
		try {
			Tirocinio test1=new Tirocinio();
			test1.setCodTirocinio(1);
			test1.setDataInizioTirocinio("1999-12-12");                  
			test1.setCfuPrevisti((short) 11);
			test1.setCompetenze("reti");
			test1.setCompetenzeAcquisire("javaScript");
			test1.setAttivitaPreviste("java");
			test1.setSvolgimentoTirocinio("Bene");
			test1.setProgettoFormativo("test.pdf");
			test1.setDescrizioneEnte("Ragazzo valido");
			test1.setMatricola(4859);
			test1.setPartitaIva("11111111111");
			when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
			test.doPost(requestMock, responseMock);
			assertEquals(prova.exists(),true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
