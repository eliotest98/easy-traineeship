package test.testET;

import static org.junit.jupiter.api.Assertions.*;

import controller.DbConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import model.DAO.TirocinanteDAO;
import model.Tirocinante;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
/*
 * Classe per il testing del DAO TirocinanteDAO.
 * Questa classe di test � stata scritta secondo la
 * metodologia WHITE BOX.
*/
class TirocinanteDAOTest {

  Connection conn = new DbConnection().getInstance().getConn();
  TirocinanteDAO tirocinanteDao = new TirocinanteDAO();

  private java.sql.Date data = new java.sql.Date(0);
  private String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(data);

  // metodo tearDown per rimuovere i campi inseriti durante i test
  @AfterEach
  public void tearDown() {
    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
      stmtSelect.executeUpdate(sql1);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Test del metodo ricercaTirocinanteByMatricola di TirocinanteDAO
  @Test
  void testRicercaTirocinanteByMatricola() throws SQLException {

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 =
          ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','0');");
      stmtSelect.executeUpdate(sql1);
      String sql2 = ("INSERT INTO Tirocinante VALUES('0512109999','" + modifiedDate
          + "','Salerno','Italiana','Salerno','QVGXRV78A52H443B',"
          + "'3491494900','azienda@email.it');");
      stmtSelect.executeUpdate(sql2);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Tirocinante tirocinante = tirocinanteDao.ricercaTirocinanteByMatricola(512109999);
    assertEquals(tirocinante.getMatricola(), 512109999);
  }

  // Test del metodo ricercaTirocinanteByEmail di TirocinanteDAO
  @Test
  void testRicercaTirocinanteByEmail() throws SQLException {

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 =
          ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','0');");
      stmtSelect.executeUpdate(sql1);
      String sql2 = ("INSERT INTO Tirocinante VALUES('0512109999','" + modifiedDate
          + "','Salerno','Italiana','Salerno','QVGXRV78A52H443B',"
          + "'3491494900','azienda@email.it');");
      stmtSelect.executeUpdate(sql2);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Tirocinante tirocinante = tirocinanteDao.ricercaTirocinanteByEmail("azienda@email.it");
    assertEquals(tirocinante.getMatricola(), 512109999);
  }

  // Test del metodo allTirocinante di TirocinanteDAO
  @Test
  void testAllTirocinante() throws SQLException {
    boolean inserito = false;
    int i;

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 =
          ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','0');");
      stmtSelect.executeUpdate(sql1);
      String sql2 = ("INSERT INTO Tirocinante VALUES('0512109999','" + modifiedDate
          + "','Salerno','Italiana','Salerno','QVGXRV78A52H443B',"
          + "'3491494900','azienda@email.it');");
      stmtSelect.executeUpdate(sql2);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    ArrayList<Tirocinante> listaTirocinanti = tirocinanteDao.allTirocinante();
    for (i = 0; i < listaTirocinanti.size(); i++) {
      if (listaTirocinanti.get(i).getEmail().equals("azienda@email.it")) {
        inserito = true;
      }
    }
    assertEquals(inserito, true);
  }


  // Test del metodo inserisciTirocinante di TirocinanteDAO
  @Test
  void testInserisciTirocinante() throws SQLException {

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 =
          ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','0');");
      stmtSelect.executeUpdate(sql1);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Tirocinante tirocinante =
        new Tirocinante("azienda@email.it", "Cap Gemini", "NA", 'N', "password", 0, 0512103313,
            data, "Salerno", "Italiana", "Salerno", "QVGXRV78A52H443B", 3491494900L);
    assertEquals(tirocinanteDao.inserisciTirocinante(tirocinante), true);

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
      stmtSelect.executeUpdate(sql1);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Test del metodo updatePassword di TirocinanteDAO
  @Test
  void testUpdatePassword() throws SQLException {

    try {
      Statement stmtSelect = conn.createStatement();
      String sql1 =
          ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','0');");
      stmtSelect.executeUpdate(sql1);
      String sql2 = ("INSERT INTO Tirocinante VALUES('0512103313','" + modifiedDate
          + "','Salerno','Italiana','Salerno','QVGXRV78A52H443B',"
          + "'3491494900','azienda@email.it');");
      stmtSelect.executeUpdate(sql2);
      conn.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals(tirocinanteDao.updatePassword("azienda@email.it", "123456"), true);
  }
}
