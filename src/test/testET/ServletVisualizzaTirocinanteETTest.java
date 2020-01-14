package test.testET;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import controller.DbConnection;
import controller.ServletGestioneRichiesteEnteET;
import controller.ServletVisualizzaTirocinanteET;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Secretary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServletVisualizzaTirocinanteETTest {
  Connection conn = new DbConnection().getInstance().getConn();
  // Creazione mock
  HttpServletRequest requestMock = mock(HttpServletRequest.class);
  HttpServletResponse responseMock = mock(HttpServletResponse.class);
  HttpSession sessionMock = mock(HttpSession.class);
  ServletGestioneRichiesteEnteET servletSecretaryMock = mock(ServletGestioneRichiesteEnteET.class);
  RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
  Date data = new Date();
  String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(data);


  @BeforeEach
  public void setUp() {
    Secretary user = new Secretary();
    user.setEmail("segreteria@unisa.it");
    when(requestMock.getSession()).thenReturn(sessionMock);
    when(sessionMock.getAttribute("userET")).thenReturn("1");
    when(sessionMock.getAttribute("user")).thenReturn(user);
    try {
      Statement stmtSelect = conn.createStatement();
      String sql4 =
          ("INSERT INTO User VALUES('t.tester@studenti.unisa.it',"
              + "'Tester','Amendola','M','salvo','0');");
      stmtSelect.executeUpdate(sql4);
      String sql2 = ("INSERT INTO tirocinante VALUES('4859','" + modifiedDate
          + "','Salerno','italiana','Salerno','rlaplg98a08i805e',"
          + "'3294475051','t.tester@studenti.unisa.it');");
      stmtSelect.executeUpdate(sql2);
      String sql5 =
          ("INSERT INTO User VALUES('test@test.test','Salvatore','Totti','M','pass98','3');");
      stmtSelect.executeUpdate(sql5);
      String sql3 =
          ("INSERT INTO enteconvenzionato VALUES('11111111111',"
              + "'Avellino','Salvatore Totti','0825519149','100',"
              + "'Michele Persico','Michele Porto','08/01/1977',"
              + "'esperti in siti web','test@test.test');");
      stmtSelect.executeUpdate(sql3);
      String sql1 = ("INSERT INTO tirocinio VALUES('999','" + modifiedDate
          + "','11','informatica','javascript','Java','Bene',"
          + "'In attesa della Segreteria','progettoformativa.pdf',"
          + "'ragazzo valido','4859','11111111111');");
      stmtSelect.executeUpdate(sql1);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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


  @Test
  void testVisualizzaTirocinante() throws ServletException, IOException {
    when(requestMock.getParameter("matricola")).thenReturn("4859");
    when(requestMock.getRequestDispatcher("_areaSecretary/VisualizzaTirocinanteET.jsp"))
        .thenReturn(dispatcherMock);
    ServletVisualizzaTirocinanteET test = new ServletVisualizzaTirocinanteET();
    test.doPost(requestMock, responseMock);
    verify(dispatcherMock).forward(requestMock, responseMock);
  }

}
