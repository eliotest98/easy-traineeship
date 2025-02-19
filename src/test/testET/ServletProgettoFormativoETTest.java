package test.testET;

import static org.mockito.Mockito.*;

import controller.DbConnection;
import controller.ServletProgettoFormativoET;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO.TirocinioDAO;
import model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * 
 * 
 * Classe per il testing della ServletProgettoFormativoET .
 * Questa classe di test � stata scritta secondo la
 * metodologia WHITE BOX.
 * 
 * 
*/
class ServletProgettoFormativoETTest {

  Connection conn = new DbConnection().getInstance().getConn();
  // Creazione mock
  HttpServletRequest requestMock = mock(HttpServletRequest.class);
  HttpServletResponse responseMock = mock(HttpServletResponse.class);
  HttpSession sessionMock = mock(HttpSession.class);
  RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
  Student user = new Student("t.tester@studenti.unisa.it", "Tester", "Amendola", 'M', "salvo", 0);
  ServletProgettoFormativoET test = new ServletProgettoFormativoET();
  TirocinioDAO tirocinioDaoMock = mock(TirocinioDAO.class);

  /*
   *  Metodo setUp()
   *  Prima di ogni test, simuliamo una sessione di tipo ENTE CONVENZIONATO (3).
   *  
   */
  @BeforeEach
  void setUp() {
    when(requestMock.getSession()).thenReturn(sessionMock);
    when(sessionMock.getAttribute("userET")).thenReturn("3");
    when(sessionMock.getAttribute("user")).thenReturn(user);
  }

  /*
   *  Metodo tearDown()
   *  Dopo ogni test, eliminiamo le righe aggiunte nelle tabelle:
   *  Tirocinio
   *  Tirocinante
   *  EnteConvenzionato
   *  Utente (studente)
   *  Utente (ente)
   */
  @AfterEach
  public void tearDown() {
    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 = ("DELETE FROM tirocinio WHERE CODTIROCINIO='999';");
      stmtSelect.executeUpdate(sql1);
      String sql2 = ("DELETE FROM tirocinante WHERE matricola='4859';");
      stmtSelect.executeUpdate(sql2);
      String sql3 = ("DELETE FROM enteconvenzionato WHERE partitaIva='11111111111';");
      stmtSelect.executeUpdate(sql3);
      String sql4 = ("DELETE FROM User WHERE email='t.tester@studenti.unisa.it';");
      stmtSelect.executeUpdate(sql4);
      String sql5 = ("DELETE FROM User WHERE email='test@test.test';");
      stmtSelect.executeUpdate(sql5);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /*
   * Inserisco nel db un:
   * Utente (studente)
   * tirocinante
   * Utente (enteConvenzionato)
   * enteConvenzionato
   * tirocinio
   */
  @Test
  void testProgettoFormativoCorretto() {
    try {
      Statement stmtSelect = conn.createStatement();
      stmtSelect.executeUpdate(
          "INSERT INTO User VALUES('t.tester@studenti.unisa.it',"
          + "'Tester','Amendola','M','salvo','0');");
      stmtSelect.executeUpdate("INSERT INTO tirocinante VALUES('4859','" + new Date(0)
          + "','Salerno','italiana','Salerno','rlaplg98a08i805e',"
          + "'3294475051','t.tester@studenti.unisa.it');");
      stmtSelect.executeUpdate(
          "INSERT INTO User VALUES('test@test.test','Salvatore','Totti','M','pass98','3');");
      stmtSelect.executeUpdate(
          "INSERT INTO enteconvenzionato VALUES('11111111111',"
          + "'Avellino','Salvatore Totti','0825519149','100',"
          + "'Michele Persico','Michele Porto','08/01/1977',"
          + "'esperti in siti web','test@test.test');");
      stmtSelect.executeUpdate("INSERT INTO tirocinio VALUES('999','" + new Date(0)
          + "','11','informatica','javascript','Java','Bene',"
          + "'Accettato e in attesa di firma','progettoformativa.pdf',"
          + "'ragazzo valido','4859','11111111111');");
      conn.commit();

      when(requestMock.getRequestDispatcher("_areaStudent/UploadProgettoFormativoET.jsp"))
          .thenReturn(dispatcherMock);
      test.doPost(requestMock, responseMock);
      verify(dispatcherMock).forward(requestMock, responseMock);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /*
   * Test quando non esiste alcun progetto formativo
   */
  @Test
  void testProgettoFormativoTirocinioNull() {
    try {
      when(requestMock.getRequestDispatcher("_areaStudent/UploadProgettoFormativoET.jsp"))
          .thenReturn(dispatcherMock);
      test.doPost(requestMock, responseMock);
      verify(dispatcherMock).forward(requestMock, responseMock);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
