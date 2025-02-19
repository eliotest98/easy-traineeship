package test.testET;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import controller.DbConnection;
import controller.ServletUploadET;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import model.DAO.TirocinioDAO;
import model.Tirocinio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.mock.web.MockPart;

class ServletUploadETTest {

  Connection conn = new DbConnection().getInstance().getConn();
  MockMultipartHttpServletRequest requestMock = new MockMultipartHttpServletRequest();
  MockHttpServletResponse responseMock = new MockHttpServletResponse();
  HttpSession sessionMock = mock(HttpSession.class);
  RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);
  MockPart partMock = new MockPart("file", new byte[1024]);
  ServletUploadET test = new ServletUploadET();
  TirocinioDAO tirocinioDao = new TirocinioDAO();

  String sql1 =
      ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it',"
          + "'Pellegrino','Aurilia','M','pelle','0');");
  String sql2 = ("INSERT INTO tirocinante VALUES('4859','" + new Date(0)
      + "','Salerno','italiana','Salerno','rlaplg98a08i805e',"
      + "'3294475051','p.aurilia@studenti.unisa.it');");
  String sql3 =
      ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
  String sql4 =
      ("INSERT INTO enteconvenzionato VALUES('11111111111',"
          + "'Avellino','Salvatore Totti','0825519149','100',"
          + "'Michele Persico','Michele Porto','08/01/1977',"
          + "'esperti in siti web','green@gmail.com');");
  String sql5 = ("INSERT INTO tirocinio VALUES('999','" + new Date(0)
      + "','11','informatica','javascript','Java','Bene',"
      + "'In attesa dell Ente','','ragazzo valido','4859','11111111111');");


  @BeforeEach
  void setUp() {

    try {
      Statement stmtSelect = conn.createStatement();
      stmtSelect.executeUpdate(sql1);
      stmtSelect.executeUpdate(sql2);
      stmtSelect.executeUpdate(sql3);
      stmtSelect.executeUpdate(sql4);
      stmtSelect.executeUpdate(sql5);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // metodo tearDown per rimuovere i campi inseriti durante i test
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
      String sql4 = ("DELETE FROM User WHERE email='p.aurilia@studenti.unisa.it';");
      stmtSelect.executeUpdate(sql4);
      String sql5 = ("DELETE FROM User WHERE email='green@gmail.com';");
      stmtSelect.executeUpdate(sql5);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testUploadStudente() {

    Tirocinio test1 = new Tirocinio();
    test1.setCodTirocinio(999);
    test1.setDataInizioTirocinio("1999-12-12");
    test1.setCfuPrevisti((short) 11);
    test1.setStatoTirocinio("In attesa Ente");
    test1.setCompetenze("reti");
    test1.setCompetenzeAcquisire("javaScript");
    test1.setAttivitaPreviste("java");
    test1.setSvolgimentoTirocinio("Bene");
    test1.setProgettoFormativo("");
    test1.setDescrizioneEnte("Ragazzo valido");
    test1.setMatricola(4859);
    test1.setPartitaIva("11111111111");

    try {

      when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
      requestMock.setSession(sessionMock);
      when(sessionMock.getAttribute("userET")).thenReturn("0");
      requestMock.addPart(partMock);

      test.doPost(requestMock, responseMock);
      assertEquals(tirocinioDao.tirocinioAttivo(4859).getProgettoFormativo(), "test");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testUploadAdmin() {

    Tirocinio test1 = new Tirocinio();
    test1.setCodTirocinio(999);
    test1.setDataInizioTirocinio("1999-12-12");
    test1.setCfuPrevisti((short) 11);
    test1.setStatoTirocinio("In attesa Ente");
    test1.setCompetenze("reti");
    test1.setCompetenzeAcquisire("javaScript");
    test1.setAttivitaPreviste("java");
    test1.setSvolgimentoTirocinio("Bene");
    test1.setProgettoFormativo("");
    test1.setDescrizioneEnte("Ragazzo valido");
    test1.setMatricola(4859);
    test1.setPartitaIva("11111111111");

    try {

      requestMock.addParameter("codTirocinio", "999");
      when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
      requestMock.setSession(sessionMock);
      when(sessionMock.getAttribute("userET")).thenReturn("2");
      requestMock.addPart(partMock);

      test.doPost(requestMock, responseMock);
      assertEquals(tirocinioDao.tirocinioAttivo(4859).getProgettoFormativo(), "test");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testUploadSegreteria() {

    Tirocinio test1 = new Tirocinio();
    test1.setCodTirocinio(999);
    test1.setDataInizioTirocinio("1999-12-12");
    test1.setCfuPrevisti((short) 11);
    test1.setStatoTirocinio("In attesa Ente");
    test1.setCompetenze("reti");
    test1.setCompetenzeAcquisire("javaScript");
    test1.setAttivitaPreviste("java");
    test1.setSvolgimentoTirocinio("Bene");
    test1.setProgettoFormativo("");
    test1.setDescrizioneEnte("Ragazzo valido");
    test1.setMatricola(4859);
    test1.setPartitaIva("11111111111");

    try {

      requestMock.addParameter("codTirocinio", "999");
      requestMock.setSession(sessionMock);
      when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
      when(sessionMock.getAttribute("userET")).thenReturn("1");
      requestMock.addPart(partMock);

      test.doPost(requestMock, responseMock);
      assertEquals(tirocinioDao.tirocinioAttivo(4859).getProgettoFormativo(), "test");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testUploadEnte() {

    Tirocinio test1 = new Tirocinio();
    test1.setCodTirocinio(999);
    test1.setDataInizioTirocinio("1999-12-12");
    test1.setCfuPrevisti((short) 11);
    test1.setStatoTirocinio("In attesa Ente");
    test1.setCompetenze("reti");
    test1.setCompetenzeAcquisire("javaScript");
    test1.setAttivitaPreviste("java");
    test1.setSvolgimentoTirocinio("Bene");
    test1.setProgettoFormativo("");
    test1.setDescrizioneEnte("Ragazzo valido");
    test1.setMatricola(4859);
    test1.setPartitaIva("11111111111");

    try {

      requestMock.addParameter("codTirocinio", "999");
      when(sessionMock.getAttribute("Tirocinio")).thenReturn(test1);
      requestMock.setSession(sessionMock);
      when(sessionMock.getAttribute("userET")).thenReturn("3");
      requestMock.addPart(partMock);

      test.doPost(requestMock, responseMock);
      assertEquals(tirocinioDao.tirocinioAttivo(4859).getProgettoFormativo(), "test");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



}
