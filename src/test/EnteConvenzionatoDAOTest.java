package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.sql.*;

import org.junit.jupiter.api.Test;

import controller.DbConnection;
import model.DAO.EnteConvenzionatoDAO;
import model.EnteConvenzionato;

class EnteConvenzionatoDAOTest {
	
	Connection conn  = new DbConnection().getInstance().getConn();
	EnteConvenzionatoDAO enteConDao = new EnteConvenzionatoDAO();
	
	//Test del metodo allEnte di EnteConvenzionatoDAO 
	@Test
	void testAllEnte() throws SQLException 
	{
		
		try 
		{
	    	Statement stmtSelect = conn.createStatement();
	    	String sql1 = ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','3');");
	    	stmtSelect.executeUpdate(sql1);
	    	String sql2 = ("INSERT INTO EnteConvenzionato VALUES('99999999999','Salerno','Giacomo','3490000141','100','Carmine','Francesco','12/10/1980','Molto interessante','azienda@email.it');");
	    	stmtSelect.executeUpdate(sql2);
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		ArrayList<EnteConvenzionato> listaEnti = enteConDao.allEnte();
		assertEquals(listaEnti.get(0).getName(),("Cap Gemini"));
		
		try 
		{
		    Statement stmtSelect = conn.createStatement();
		    String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
		    stmtSelect.executeUpdate(sql1);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	//Test del metodo InserisciEnte di EnteConvenzionatoDAO
	@Test
	void testInserisciEnte() throws SQLException
	{
		
		EnteConvenzionato ente = new EnteConvenzionato("azienda@email.it","Cap Gemini","NA",' ',"password",3,"25/12/1980","99999999999","Salerno","Giacomo","Carmine","3401414140",8,"Pino","TE","Molto interessante");
		assertEquals(enteConDao.inserisciEnte(ente),true);
		
		try {
		    Statement stmtSelect = conn.createStatement();
		    String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
		    stmtSelect.executeUpdate(sql1);
		    conn.commit();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	//Test del metodo ModificaEnte di EnteConvenzionatoDAO quando l'elemento da modificare non � presente nel database
	@Test
	void testModificaEnteCampoNonPresente() throws SQLException
	{
		
		EnteConvenzionato ente = new EnteConvenzionato("azienda@email.it","Cap Gemini","NA",' ',"password",3,"25/12/1980","99999999999","Salerno","Giacomo","Carmine","3401414140",8,"Pino","TE","Molto interessante");
		
		assertEquals(enteConDao.modificaEnte(ente),false);
		
		try 
		{
		    Statement stmtSelect = conn.createStatement();
		    String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
		    stmtSelect.executeUpdate(sql1);
		    conn.commit();
		}
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}

	//Test del metodo ModificaEnte di EnteConvenzionatoDAO quando l'elemento da modificare � presente nel database
	@Test
	void testModificaEnteCampoPresente() throws SQLException
	{
		
		try 
		{
	    	Statement stmtSelect = conn.createStatement();
	    	String sql1 = ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','3');");
	    	stmtSelect.executeUpdate(sql1);
	    	String sql2 = ("INSERT INTO EnteConvenzionato VALUES('99999999999','Salerno','Giacomo','3490000141','100','Carmine','Giuseppe','12/10/1975','Molto interessante','azienda@email.it');");
	    	stmtSelect.executeUpdate(sql2);
	    	conn.commit();
	    }
	    catch (Exception e) 
		{
	    	e.printStackTrace();
	    }
		
		EnteConvenzionato ente = new EnteConvenzionato("azienda@email.it","Cap Gemini","NA",' ',"password",3,"25/12/1980","99999999999","Salerno","Giacomo","Carmine","3401414140",8,"Pino","TE","Molto interessante");
		assertEquals(enteConDao.modificaEnte(ente),true);
		
		try 
		{
		    Statement stmtSelect = conn.createStatement();
		    String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
		    stmtSelect.executeUpdate(sql1);
		    conn.commit();
		}
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}
	
	// Test del metodo eliminaEnte di EnteConvenzionatoDAO
		@Test
		void testEliminaEnte() throws SQLException {

			try {
				Statement stmtSelect = conn.createStatement();
				String sql1 = ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','3');");
				stmtSelect.executeUpdate(sql1);
				String sql2 = ("INSERT INTO EnteConvenzionato VALUES('99999999999','Salerno','Giacomo','3490000141','100','Carmine','Giuseppe','12/10/1975','Molto interessante','azienda@email.it');");
				stmtSelect.executeUpdate(sql2);
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			EnteConvenzionato ente = new EnteConvenzionato("azienda@email.it", "Cap Gemini", "NA", ' ', "password", 3,
					"25/12/1980", "99999999999", "Salerno", "Giacomo", "Carmine", "3401414140", 8, "Pino", "TE",
					"Molto interessante");
			assertEquals(enteConDao.eliminaEnte(ente), true);

			try {
				Statement stmtSelect = conn.createStatement();
				String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
				stmtSelect.executeUpdate(sql1);
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	//Test del metodo updatePassword di EnteConvenzionatoDAO 
	@Test
	void testUpdatePassword() throws SQLException
	{
		
		try 
		{
	    	Statement stmtSelect = conn.createStatement();
	    	String sql1 = ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','3');");
	    	stmtSelect.executeUpdate(sql1);
	    	String sql2 = ("INSERT INTO EnteConvenzionato VALUES('99999999999','Salerno','Giacomo','3490000141','100','Carmine','Giuseppe','12/10/1975','Molto interessante','azienda@email.it');");
	    	stmtSelect.executeUpdate(sql2);
	    	conn.commit();
	    }
	    catch (Exception e) 
		{
	    	e.printStackTrace();
	    }
		
		assertEquals(enteConDao.updatePassword("azienda@email.it", "123456"),true);
		
		try 
		{
		    Statement stmtSelect = conn.createStatement();
		    String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
		    stmtSelect.executeUpdate(sql1);
		    conn.commit();
		}
		catch (Exception e) 
		{
		    e.printStackTrace();
		}
	}
}

