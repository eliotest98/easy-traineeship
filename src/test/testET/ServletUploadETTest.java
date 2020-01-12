package test.testET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import controller.ServletProgettoFormativoET;
import controller.ServletUploadET;
import model.Student;
import model.DAO.TirocinioDAO;


class ServletUploadETTest {
	
	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	HttpServletResponse responseMock = mock(HttpServletResponse.class);
	HttpSession sessionMock = mock(HttpSession.class);
	RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
	ServletUploadET test = new ServletUploadET();
	byte[] content = null;
	
	@Test
	void test() {
		try {
			File provafile = new File("prova.pdf");
			//Part prova = new Part(provafile);
			//when(requestMock.getPart("file")).thenReturn(prova);
			//test.doPost(requestMock, responseMock);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
