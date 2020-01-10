package test.testET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.ServletDownloadET;
import controller.ServletProgettoFormativoET;
import controller.ServletUploadET;
import model.Student;
import model.Tirocinio;
import model.DAO.TirocinioDAO;

	

class ServletDownloadETTest {
	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	//HttpServletResponse responseSpy = spy(HttpServletResponse.class);
	MockHttpServletResponse responseMock = new MockHttpServletResponse();
	HttpSession sessionMock = mock(HttpSession.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	ServletDownloadET test = new ServletDownloadET();
	InputStream is = mock(InputStream.class);
	
	
	//Metodo setUp()
	@BeforeEach 
	void setUp() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("userET")).thenReturn("3");
		//when(sessionMock.getAttribute("user")).thenReturn(user);
	}
	
	@Test
	void testBoh() {
		try {
			//ServletOutputStream sos = response.getOutputStream();
			//when(responseMock.getOutputStream()).thenReturn(responseMock.);
			Tirocinio test1=new Tirocinio();
			test1.setCodTirocinio(1);
			test1.setDataInizioTirocinio("1999-12-12");                  
			test1.setCfuPrevisti((short) 11);
			test1.setCompetenze("reti");
			test1.setCompetenzeAcquisire("javaScript");
			test1.setAttivitaPreviste("java");
			test1.setSvolgimentoTirocinio("Bene");
			test1.setProgettoFormativo("pdf8HW7XMXWC31009.pdf");
			test1.setDescrizioneEnte("Ragazzo valido");
			test1.setMatricola(4859);
			test1.setPartitaIva("11111111111");
			when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
			test.doPost(requestMock, responseMock);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
