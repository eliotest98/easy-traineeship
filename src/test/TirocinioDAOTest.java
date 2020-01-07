package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

import controller.DbConnection;
import controller.Utils;
import model.EnteConvenzionato;
import model.Tirocinio;
import model.DAO.EnteConvenzionatoDAO;
import model.DAO.TirocinioDAO;

class TirocinioDAOTest {

	Connection conn = new DbConnection().getInstance().getConn();
	TirocinioDAO tirocinioDao = new TirocinioDAO();
	Date data=new Date();
	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(data);
	
	String sql1 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
	String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
	String sql3 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
	String sql4 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
	String sql5 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','In attesa dell Ente','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
	
	//metodo tearDown per rimuovere i campi inseriti durante i test
	@AfterEach
	public void tearDown() {
		try 
		{
		    Statement stmtSelect = conn.createStatement();
		    String sql1 = ("DELETE FROM tirocinio WHERE CODTIROCINIO='1';");
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
		}
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	//Test del metodo allTirocinio di TirocinioDAO 
	@Test
	void testAllTirocinio() 
	{
		boolean trovato = false;
		int i;
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		ArrayList<Tirocinio> listaTirocini =tirocinioDao.allTirocinio();
		for( i = 0; i < listaTirocini.size(); i++) {
			if (listaTirocini.get(i).getCodTirocinio()==1 && listaTirocini.get(i).getMatricola()==4859) {
				trovato = true;
			}
		}
        assertEquals(trovato,true);
	}
	
	//Test del metodo TirocinioAttivo di TirocinioDAO 
		@Test
		void testTirocinioAttivo() 
		{
			boolean trovato = false;
			
			try 
			{
				
				Statement stmtSelect = conn.createStatement();
		    	stmtSelect.executeUpdate(sql1);
		    	stmtSelect.executeUpdate(sql2);
		    	stmtSelect.executeUpdate(sql3);
		    	stmtSelect.executeUpdate(sql4);
		    	stmtSelect.executeUpdate(sql5);
		    	conn.commit();
		    	
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
			
			Tirocinio tirocinio = tirocinioDao.tirocinioAttivo(4859);
				if (tirocinio.getCodTirocinio()==1 && tirocinio.getMatricola()==4859) {
					trovato = true;
				}
	        assertEquals(trovato,true);
		}
	
	//Test del metodo allTirocinioByStato di TirocinioDAO 
	@Test
	void allTirocinioByStato() 
	{
		boolean trovato = false;
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		ArrayList<Tirocinio> listaTirocini = tirocinioDao.allTirocinioByStato("In attesa dell Ente");
		for( int i = 0; i < listaTirocini.size(); i++) {
			if (listaTirocini.get(i).getStatoTirocinio().equals("In attesa dell Ente") && listaTirocini.get(i).getMatricola()==(4859)) {
				trovato = true;
			}
	}
		assertEquals(trovato,true);
	}
	
	//Test del metodo allTirocinioByEnte di TirocinioDAO 
	@Test
	void allTirocinioByEnte() 
	{
		boolean trovato = false;
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		ArrayList<Tirocinio> listaTirocini = tirocinioDao.allTirocinioByEnte("green@gmail.com");
		for( int i = 0; i < listaTirocini.size(); i++) {
			if (listaTirocini.get(i).getCodTirocinio()==(1) && listaTirocini.get(i).getMatricola()==(4859) && listaTirocini.get(i).getPartitaIva().equals("11111111111")) {
				trovato = true;
			}
	}
		assertEquals(trovato,true);
	}
	
	
	//Test del metodo allTirocinioTirocinante di TirocinioDAO 
	@Test
	void allTirocinioTirocinante() 
	{
		boolean trovato = false;
		int mat=4859;
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
			stmtSelect.executeUpdate(sql1);
			stmtSelect.executeUpdate(sql2);
			stmtSelect.executeUpdate(sql3);
			stmtSelect.executeUpdate(sql4);
			stmtSelect.executeUpdate(sql5);
			conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		ArrayList<Tirocinio> listaTirocini = tirocinioDao.allTirocinioTirocinante(mat);
		for( int i = 0; i < listaTirocini.size(); i++) {
			if (listaTirocini.get(i).getMatricola()==mat);
				trovato = true;
			}

		assertEquals(trovato,true);
	}
		
	//Test del metodo allTirocinioByDocumento di TirocinioDAO 
		@Test
		void testAllTirocinioByDocumento() 
		{
			boolean trovato = false;
			int mat=4859;
			
			try 
			{
				
				Statement stmtSelect = conn.createStatement();
		    	stmtSelect.executeUpdate(sql1);
		    	stmtSelect.executeUpdate(sql2);
		    	stmtSelect.executeUpdate(sql3);
		    	stmtSelect.executeUpdate(sql4);
		    	stmtSelect.executeUpdate(sql5);
		    	conn.commit();
		    	
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
			
			Tirocinio tirocinio = tirocinioDao.allTirocinioByDocumento("p.aurilia@studenti.unisa.it", "In attesa dell Ente");
				if (tirocinio.getMatricola()==mat && tirocinio.getPartitaIva().equals("11111111111") && tirocinio.getCodTirocinio()==1) {
					trovato = true;
				}

			assertEquals(trovato,true);
		}
	
	//Test del metodo inserisciTirocinio di TirocinioDAO 
	@Test
	void inserisciTirocinio() throws SQLException 
	{
		Statement stmtSelect = conn.createStatement();
    	stmtSelect.executeUpdate(sql1);
    	stmtSelect.executeUpdate(sql2);
    	stmtSelect.executeUpdate(sql3);
    	stmtSelect.executeUpdate(sql4);
    	conn.commit();
		Tirocinio test1=new Tirocinio();
		test1.setCodTirocinio(1);
		test1.setDataInizioTirocinio(modifiedDate);                  
		test1.setCfuPrevisti((short) 11);
		test1.setCompetenze("reti");
		test1.setCompetenzeAcquisire("javaScript");
		test1.setAttivitaPreviste("java");
		test1.setSvolgimentoTirocinio("Bene");
		test1.setProgettoFormativo("progettoformativo.pdf");
		test1.setDescrizioneEnte("Ragazzo valido");
		test1.setMatricola(4859);
		test1.setPartitaIva("11111111111");
		
		
		assertEquals(tirocinioDao.inserisciTirocinio(test1),true);
	}
	
	//Test del metodo richiestaEnte di TirocinioDAO 
	@Test
	void richiestaEnte() 
	{
		boolean trovato = false;
		int mat=4859;
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		assertEquals(tirocinioDao.richiestaEnte(1,"11111111111","Male"),true);
	}
	
	//Test del metodo modificaStatoTirocinio di TirocinioDAO 
	@Test
	void modificaStatoTirocinio() 
	{
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		assertEquals(tirocinioDao.modificaStatoTirocinio(1,"Bloccato"),true);
	}
		
	

	//Test del metodo modificaTirocinio di TirocinioDAO 
	@Test
	void modificaTirocinio() 
	{
		
		Tirocinio nuovo=new Tirocinio();
		nuovo.setCodTirocinio(1);
		nuovo.setDataInizioTirocinio(modifiedDate);
		nuovo.setCfuPrevisti((short)8);
		nuovo.setCompetenze("java");
		nuovo.setCompetenzeAcquisire("javascript");
		nuovo.setAttivitaPreviste("css");
		nuovo.setSvolgimentoTirocinio("Bene");
		nuovo.setStatoTirocinio("Annullato");
		nuovo.setProgettoFormativo("progettoformativo.pdf");
		nuovo.setDescrizioneEnte("Ragazzo valido");
		nuovo.setMatricola(4859);
		nuovo.setPartitaIva("11111111111");
		
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		assertEquals(tirocinioDao.modificaTirocinio(nuovo),true);
	}
				
	//Test del metodo uploadProgettoFormativo di TirocinioDAO 
	@Test
	void uploadProgettoFormativo() 
	{			
		
		try 
		{
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		assertEquals(tirocinioDao.uploadProgettoFormativo(1,"ProgettoFormativo2.pdf"),true);
	}
				
				
	//Test del metodo downloadProgettoFormativo di TirocinioDAO 
	@Test
	void downloadProgettoFormativo() 
	{				
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
			stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		assertEquals(tirocinioDao.downloadProgettoFormativo(1),"progettoformativa.pdf");
	}
	
		//Test del metodo AllDocumentiDaFirmareByStudente di TirocinioDAO 
		@Test
		void testAllDocumentiDaFirmareByStudente() 
		{	
			boolean trovato = false;
			int i;
			
			try 
			{
				Statement stmtSelect = conn.createStatement();
		    	stmtSelect.executeUpdate(sql1);
		    	stmtSelect.executeUpdate(sql2);
		    	stmtSelect.executeUpdate(sql3);
		    	stmtSelect.executeUpdate(sql4);
		    	stmtSelect.executeUpdate(sql5);
		    	conn.commit();
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
			
			ArrayList<Tirocinio> listaTirocini = tirocinioDao.allDocumentiDaFirmareByStudente(4859, "In attesa dell Ente");
			for( i = 0; i < listaTirocini.size(); i++) {
				if (listaTirocini.get(i).getCodTirocinio()==1 && listaTirocini.get(i).getMatricola()==4859 && listaTirocini.get(i).getStatoTirocinio().contentEquals("In attesa dell Ente")) {
					trovato = true;
				}
			}
	        assertEquals(trovato,true);
		}
		
		//Test del metodo AllDocumentiDaFirmareByEnte di TirocinioDAO 
		@Test
		void testAllDocumentiDaFirmareByEnte() 
		{	
			boolean trovato = false;
			int i;
			
			try 
			{
				Statement stmtSelect = conn.createStatement();
		    	stmtSelect.executeUpdate(sql1);
		    	stmtSelect.executeUpdate(sql2);
		    	stmtSelect.executeUpdate(sql3);
		    	stmtSelect.executeUpdate(sql4);
		    	stmtSelect.executeUpdate(sql5);
		    	conn.commit();
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
			
			ArrayList<Tirocinio> listaTirocini = tirocinioDao.allDocumentiDaFirmareByEnte("11111111111", "In attesa dell Ente");
			for( i = 0; i < listaTirocini.size(); i++) {
				if (listaTirocini.get(i).getCodTirocinio()==1 && listaTirocini.get(i).getPartitaIva().equals("11111111111") && listaTirocini.get(i).getStatoTirocinio().contentEquals("In attesa dell Ente")) {
					trovato = true;
				}
			}
	        assertEquals(trovato,true);
		}

	//Test del metodo allTirocinioByStato di TirocinioDAO 
	@Test
	void allTirocinioByCodTirocinio() 
	{
		boolean trovato = false;
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
	    	stmtSelect.executeUpdate(sql1);
	    	stmtSelect.executeUpdate(sql2);
	    	stmtSelect.executeUpdate(sql3);
	    	stmtSelect.executeUpdate(sql4);
	    	stmtSelect.executeUpdate(sql5);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		Tirocinio tirocinio = tirocinioDao.TirocinioByCodTirocinio(1);
		if (tirocinio.getStatoTirocinio().equals("In attesa dell Ente") && tirocinio.getMatricola()==(4859)) {
			trovato = true;
		}
		assertEquals(trovato,true);
	}
	
}