package test.testET;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import controller.DbConnection;
import controller.ServletDocumentiTirocinioET;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EnteConvenzionato;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * 
 * 
 * Classe per il testing della ServletDocumentiTirocinioET .
 * Questa classe di test è stata scritta secondo la
 * metodologia WHITE BOX.
 * 
 * 
*/
class ServletDocumentiTirocinioETTest {

  Connection conn = new DbConnection().getInstance().getConn();
  // Creazione mock
  HttpServletRequest requestMock = mock(HttpServletRequest.class);
  HttpServletResponse responseMock = mock(HttpServletResponse.class);
  HttpSession sessionMock = mock(HttpSession.class);
  ServletDocumentiTirocinioET servletSecretaryMock = mock(ServletDocumentiTirocinioET.class);
  RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
  ServletDocumentiTirocinioET test = new ServletDocumentiTirocinioET();
  /*
   * Prima di ogni @test
   * Restituiamo in sessione il mock
   * e la pagina dispacherMock quando il dispatcher
   * cerca la pagina DocumentiET.jsp
   * 
   * */
  @BeforeEach
  void setUp() {
    when(requestMock.getSession()).thenReturn(sessionMock);
    when(requestMock.getRequestDispatcher("DocumentiET.jsp")).thenReturn(dispatcherMock);
  }
  
  /*
   * Test Reindirizzamento segreteria
   */
  @Test
  void testReindirizzamentoDocumentiSegreteria() throws ServletException, IOException {
    when(sessionMock.getAttribute("userET")).thenReturn("1");

    test.doGet(requestMock, responseMock);
    verify(dispatcherMock).forward(requestMock, responseMock);
  }
  /*
   * Test Reindirizzamento Admin
   */
  @Test
  void testReindirizzamentoDocumentiAdmin() throws ServletException, IOException {
    when(sessionMock.getAttribute("userET")).thenReturn("2");
    test.doGet(requestMock, responseMock);
    verify(dispatcherMock).forward(requestMock, responseMock);
  }

  @Test
  void testReindirizzamentoDocumentiEnte() throws ServletException, IOException {

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 =
          ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','3');");
      stmtSelect.executeUpdate(sql1);
      String sql2 =
          ("INSERT INTO EnteConvenzionato VALUES('99999999999',"
              + "'Salerno','Giacomo','3490000141','100',"
              + "'Carmine','Giuseppe','12/10/1975','Molto interessante','azienda@email.it');");
      stmtSelect.executeUpdate(sql2);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    EnteConvenzionato ente = new EnteConvenzionato();
    ente.setEmail("azienda@email.it");
    when(sessionMock.getAttribute("user")).thenReturn(ente);
    when(sessionMock.getAttribute("userET")).thenReturn("3");
    test.doGet(requestMock, responseMock);
    verify(dispatcherMock).forward(requestMock, responseMock);

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
      stmtSelect.executeUpdate(sql1);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  
  /*
   * Test Reindirizzamento studente.
   */

  @Test
  void testReindirizzamentoDocumentiStudente() throws ServletException, IOException {

    try {
      Statement stmtSelect = conn.createStatement();
      String sql4 =
          ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it',"
              + "'Pellegrino','Aurilia','M','pelle','0');");
      stmtSelect.executeUpdate(sql4);
      String sql2 = ("INSERT INTO tirocinante VALUES('4859','" + new Date(0)
          + "','Salerno','italiana','Salerno','rlaplg98a08i805e',"
          + "'3294475051','p.aurilia@studenti.unisa.it');");
      stmtSelect.executeUpdate(sql2);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Student student = new Student();
    student.setEmail("p.aurilia@studenti.unisa.it");
    when(sessionMock.getAttribute("user")).thenReturn(student);
    when(sessionMock.getAttribute("userET")).thenReturn("0");
    test.doGet(requestMock, responseMock);
    verify(dispatcherMock).forward(requestMock, responseMock);

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 = ("DELETE FROM User WHERE EMAIL='p.aurilia@studenti.unisa.it';");
      stmtSelect.executeUpdate(sql1);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
