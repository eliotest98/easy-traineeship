package test.testET;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.sql.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import controller.DbConnection;
import model.DAO.EnteConvenzionatoDAO;
import model.EnteConvenzionato;

class EnteConvenzionatoDAOTest {
	
	Connection conn  = new DbConnection().getInstance().getConn();
	EnteConvenzionatoDAO enteConDao = new EnteConvenzionatoDAO();
	EnteConvenzionato ente = new EnteConvenzionato("azienda@email.it","Cap Gemini","NA",'N',"password",3,"25/12/1980","99999999999","Salerno","Giacomo","Carmine","3401414140",8,"Pino","TE","Molto interessante");
	
	
	//metodo tearDown per rimuovere i campi inseriti durante i test
	@AfterEach
	public void tearDown() {
		try 
		{
		    Statement stmtSelect = conn.createStatement();
		    String sql1 = ("DELETE FROM User WHERE EMAIL='azienda@email.it';");
		    stmtSelect.executeUpdate(sql1);
		    conn.commit();
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	//Test del metodo allEnte di EnteConvenzionatoDAO 
	@Test
	void testAllEnte() throws SQLException 
	{
		boolean inserito = false;
		int i;
		
		try 
		{
	    	Statement stmtSelect = conn.createStatement();
	    	String sql1 = ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','3');");
	    	stmtSelect.executeUpdate(sql1);
	    	String sql2 = ("INSERT INTO EnteConvenzionato VALUES('99999999999','Salerno','Giacomo','3490000141','100','Carmine','Francesco','12/10/1980','Molto interessante','azienda@email.it');");
	    	stmtSelect.executeUpdate(sql2);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		ArrayList<EnteConvenzionato> listaEnti = enteConDao.allEnte();
		for( i = 0; i < listaEnti.size(); i++) {
			if (listaEnti.get(i).getEmail().equals(ente.getEmail()) && listaEnti.get(i).getPartitaIva().equals(ente.getPartitaIva())) {
				inserito = true;
			}
		}
		assertEquals(inserito,true);
	}
	
	//Test del metodo RicercaEnteByPartitaIva() di EnteConvenzionatoDAO 
	@Test
	void testRicercaEnteByPartitaIva() throws SQLException 
	{
		boolean trovato = false;
		
		try 
		{
	    	Statement stmtSelect = conn.createStatement();
	    	String sql1 = ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','3');");
	    	stmtSelect.executeUpdate(sql1);
	    	String sql2 = ("INSERT INTO EnteConvenzionato VALUES('99999999999','Salerno','Giacomo','3490000141','100','Carmine','Francesco','12/10/1980','Molto interessante','azienda@email.it');");
	    	stmtSelect.executeUpdate(sql2);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		EnteConvenzionato ente = enteConDao.ricercaEnteByPartitaIva("99999999999");
		if (("azienda@email.it").equals(ente.getEmail()) && ("99999999999").equals(ente.getPartitaIva())) {
			trovato = true;
		}
			
		assertEquals(trovato,true);
	}
	
	//Test del metodo RicercaEnteByPartitaIva() di EnteConvenzionatoDAO 
	@Test
	void testRicercaEnteByEmail() throws SQLException 
	{
		boolean trovato = false;
		
		try 
		{
	    	Statement stmtSelect = conn.createStatement();
	    	String sql1 = ("INSERT INTO User VALUES('azienda@email.it','Cap Gemini','NA','N','password','3');");
	    	stmtSelect.executeUpdate(sql1);
	    	String sql2 = ("INSERT INTO EnteConvenzionato VALUES('99999999999','Salerno','Giacomo','3490000141','100','Carmine','Francesco','12/10/1980','Molto interessante','azienda@email.it');");
	    	stmtSelect.executeUpdate(sql2);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		EnteConvenzionato ente = enteConDao.ricercaEnteByEmail("azienda@email.it");
		if (("azienda@email.it").equals(ente.getEmail()) && ("99999999999").equals(ente.getPartitaIva())) {
			trovato = true;
		}
			
		assertEquals(trovato,true);
	}
		
		
	
	//Test del metodo InserisciEnte di EnteConvenzionatoDAO
	@Test
	void testInserisciEnte() throws SQLException
	{
		assertEquals(enteConDao.inserisciEnte(ente),true);
	}
	
	//Test del metodo ModificaEnte di EnteConvenzionatoDAO quando l'elemento da modificare non è presente nel database
	@Test
	void testModificaEnteCampoNonPresente() throws SQLException
	{	
		assertEquals(enteConDao.modificaEnte(ente),false);
	}

	//Test del metodo ModificaEnte di EnteConvenzionatoDAO quando l'elemento da modificare è presente nel database
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
		
		ente.setDescrizioneAttivita("Noioso");
		assertEquals(enteConDao.modificaEnte(ente),true);
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

			assertEquals(enteConDao.eliminaEnte(ente.getEmail()), true);
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
		
		ente.setPassword("123456");
		assertEquals(enteConDao.updatePassword(ente.getEmail(), ente.getPassword()),true);
	}
}

