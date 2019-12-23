package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import controller.DbConnection;
import model.EnteConvenzionato;
import model.Tirocinio;
import model.DAO.EnteConvenzionatoDAO;
import model.DAO.TirocinioDAO;

class TirocinioDAOTest {

	Connection conn = new DbConnection().getInstance().getConn();
	TirocinioDAO tirocinioDao = new TirocinioDAO();
	Date data=new Date();
	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(data);
	
	//Test del metodo allTirocinioByStato di TirocinioDAO 
	@Test
	void allTirocinioByStato() 
	{
		boolean inserito = false;
		String statotirocinio="inviataAllaSegreteria"; 
		
		try 
		{
			
			Statement stmtSelect = conn.createStatement();
			String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
	    	stmtSelect.executeUpdate(sql4);
			String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
	    	stmtSelect.executeUpdate(sql2);
	    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
	    	stmtSelect.executeUpdate(sql5);
	    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
	    	stmtSelect.executeUpdate(sql3);
	    	String sql1 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','Accettato','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
	    	stmtSelect.executeUpdate(sql1);
	    	conn.commit();
	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		ArrayList<Tirocinio> listaTirocini = tirocinioDao.allTirocinioByStato("Accettato");
		for( int i = 0; i < listaTirocini.size(); i++) {
			if (listaTirocini.get(i).getStatoTirocinio().equals("Accettato")) {
				inserito = true;
			}
	}
		assertEquals(inserito,true);
		
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
	
	
	//Test del metodo allTirocinioTirocinante di TirocinioDAO 
		@Test
		void allTirocinioTirocinante() 
		{
			boolean inserito = false;
			int mat=4859;
			
			try 
			{
				
				Statement stmtSelect = conn.createStatement();
				String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
		    	stmtSelect.executeUpdate(sql4);
				String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
		    	stmtSelect.executeUpdate(sql2);
		    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
		    	stmtSelect.executeUpdate(sql5);
		    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
		    	stmtSelect.executeUpdate(sql3);
		    	String sql1 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','Accettato','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
		    	stmtSelect.executeUpdate(sql1);
		    	conn.commit();
		    	
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
			
			ArrayList<Tirocinio> listaTirocini = tirocinioDao.allTirocinioTirocinante(mat);
			for( int i = 0; i < listaTirocini.size(); i++) {
				if (listaTirocini.get(i).getMatricola()==mat);
					inserito = true;
				}

			assertEquals(inserito,true);
			
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
		
	
	

		
		//Test del metodo inserisciTirocinio di TirocinioDAO 
		@Test
		void inserisciTirocinio() throws SQLException 
		{
			Statement stmtSelect = conn.createStatement();
			String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
	    	stmtSelect.executeUpdate(sql4);
			String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
	    	stmtSelect.executeUpdate(sql2);
	    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
	    	stmtSelect.executeUpdate(sql5);
	    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
	    	stmtSelect.executeUpdate(sql3);
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
			try 
			{
				    String sql7 = ("DELETE FROM tirocinio WHERE CODTIROCINIO='1';");
				    stmtSelect.executeUpdate(sql7);
				    String sql8 = ("DELETE FROM tirocinante WHERE matricola='4859';");
				    stmtSelect.executeUpdate(sql8);
				    String sql9 = ("DELETE FROM enteconvenzionato WHERE partitaIva='11111111111';");
				    stmtSelect.executeUpdate(sql9);
				    String sql10 = ("DELETE FROM User WHERE email='p.aurilia@studenti.unisa.it';");
				    stmtSelect.executeUpdate(sql10);
				    String sql11 = ("DELETE FROM User WHERE email='green@gmail.com';");
			    	stmtSelect.executeUpdate(sql11);
			    	conn.commit();
			}
			catch (Exception e) {
			    e.printStackTrace();
			}
		}
		
		//Test del metodo modificaStatoTirocinio di TirocinioDAO 
		@Test
		void modificaStatoTirocinio() 
		{
			boolean inserito = false;
			
			try 
			{
				
				Statement stmtSelect = conn.createStatement();
				String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
		    	stmtSelect.executeUpdate(sql4);
				String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
		    	stmtSelect.executeUpdate(sql2);
		    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
		    	stmtSelect.executeUpdate(sql5);
		    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
		    	stmtSelect.executeUpdate(sql3);
		    	String sql1 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','Accettato','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
		    	stmtSelect.executeUpdate(sql1);
		    	conn.commit();
		    	
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
			
			assertEquals(tirocinioDao.modificaStatoTirocinio(1,"Bloccato"),true);
			
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
		
		//Test del metodo richiestaEnte di TirocinioDAO 
				@Test
				void richiestaEnte() 
				{
					boolean inserito = false;
					int mat=4859;
					
					try 
					{
						
						Statement stmtSelect = conn.createStatement();
						String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
				    	stmtSelect.executeUpdate(sql4);
						String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
				    	stmtSelect.executeUpdate(sql2);
				    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
				    	stmtSelect.executeUpdate(sql5);
				    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
				    	stmtSelect.executeUpdate(sql3);
				    	String sql1 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','Accettato','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
				    	stmtSelect.executeUpdate(sql1);
				    	conn.commit();
				    	
				    }
				    catch (Exception e) {
				    	e.printStackTrace();
				    }
					
					assertEquals(tirocinioDao.richiestaEnte(1,"11111111111","Male"),true);
					
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

				//Test del metodo modificaTirocinio di TirocinioDAO 
				@Test
				void modificaTirocinio() 
				{
					boolean inserito = false;
					
				
				//	Tirocinio nuovo= new Tirocinio(1,modifiedDate,11,"java","Javascript","css","Bene","Annullato","Progettoformartivo.pdf","Ragazzo valido",4859,"11111111111");
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
						String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
				    	stmtSelect.executeUpdate(sql4);
						String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
				    	stmtSelect.executeUpdate(sql2);
				    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
				    	stmtSelect.executeUpdate(sql5);
				    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
				    	stmtSelect.executeUpdate(sql3);
				    	String sql1 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','Accettato','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
				    	stmtSelect.executeUpdate(sql1);
				    	conn.commit();
				    	
				    }
				    catch (Exception e) {
				    	e.printStackTrace();
				    }
					
					assertEquals(tirocinioDao.modificaTirocinio(nuovo),true);
					
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
				
				//Test del metodo uploadProgettoFormativo di TirocinioDAO 
				@Test
				void uploadProgettoFormativo() 
				{
					boolean inserito = false;				
					
					try 
					{
						
						Statement stmtSelect = conn.createStatement();
						String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
				    	stmtSelect.executeUpdate(sql4);
						String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
				    	stmtSelect.executeUpdate(sql2);
				    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
				    	stmtSelect.executeUpdate(sql5);
				    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
				    	stmtSelect.executeUpdate(sql3);
				    	String sql1 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','Accettato','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
				    	stmtSelect.executeUpdate(sql1);
				    	conn.commit();
				    	
				    }
				    catch (Exception e) {
				    	e.printStackTrace();
				    }
					
					assertEquals(tirocinioDao.uploadProgettoFormativo(1,"ProgettoFormativo2.pdf"),true);
					
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
				
				
				//Test del metodo downloadProgettoFormativo di TirocinioDAO 
				@Test
				void downloadProgettoFormativo() 
				{
					boolean inserito = false;				
					
					try 
					{
						
						Statement stmtSelect = conn.createStatement();
						String sql4 = ("INSERT INTO User VALUES('p.aurilia@studenti.unisa.it','Pellegrino','Aurilia','M','pelle','0');");
				    	stmtSelect.executeUpdate(sql4);
						String sql2 = ("INSERT INTO tirocinante VALUES('4859','"+modifiedDate+"','Salerno','italiana','Salerno','rlaplg98a08i805e','3294475051','p.aurilia@studenti.unisa.it');");
				    	stmtSelect.executeUpdate(sql2);
				    	String sql5 = ("INSERT INTO User VALUES('green@gmail.com','Salvatore','Totti','M','pass98','3');");
				    	stmtSelect.executeUpdate(sql5);
				    	String sql3 = ("INSERT INTO enteconvenzionato VALUES('11111111111','Avellino','Salvatore Totti','0825519149','100','Michele Persico','Michele Porto','08/01/1977','esperti in siti web','green@gmail.com');");
						stmtSelect.executeUpdate(sql3);
				    	String sql1 = ("INSERT INTO tirocinio VALUES('1','"+modifiedDate+"','11','informatica','javascript','Java','Bene','Accettato','progettoformativa.pdf','ragazzo valido','4859','11111111111');");
				    	stmtSelect.executeUpdate(sql1);
				    	conn.commit();
				    	
				    }
				    catch (Exception e) {
				    	e.printStackTrace();
				    }
					
					
					assertEquals(tirocinioDao.downloadProgettoFormativo(1),"progettoformativa.pdf");
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
}
