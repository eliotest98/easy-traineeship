package model.DAO;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DbConnection;
import model.Tirocinio;

public class TirocinioDAO {
	
	/*
	 * Metodo che interroga il DB e restituisce tutti i 'Tirocini'
	 * persenti all' interno in base allo stato di 'STATOTIROCINIO'
	 * per Segreteria e Admin e EnteConvenzionato
	 * 
	 * @return listaTirocini
	 * */
	public synchronized ArrayList allTirocinioByStato(String statoTirocinio)
	{
		
		Connection con = null; //variabile per la connesione del DB
		PreparedStatement ps = null;// Creazione oggetto Statement
		//ArrayLista di tipo Tirocinio
		ArrayList<Tirocinio> listaTirocini = new ArrayList<Tirocinio>();
		try 
		{
			//Connessione con il DB
			con= new DbConnection().getInstance().getConn();
			//Query Sql per prelevare i Tirocini
			ps= con.prepareStatement("SELECT * "
									+ "FROM TIROCINIO "
									+ "WHERE STATOTIROCINIO="+statoTirocinio+";");
			ResultSet res = ps.executeQuery();
			//Ciclo che inserisce all' interno della lista i 'Tirocini'
			//restituiti dalla query
			while(res.next())
			{
				
				Tirocinio purchase= new Tirocinio();
				purchase.setCodTirocinio(res.getInt("CODTIROCINIO"));
				purchase.setDataInizioTirocinio(res.getString("DATAINIZIOTIROCINO"));
				purchase.setCfuPrevisti(res.getShort("CFUPREVISTI"));
				purchase.setCompetenze(res.getString("COMPETENZE"));
				purchase.setCompetenzeAcquisire(res.getString("COMPETENZEACQUISIRE"));
				purchase.setAttivitaPreviste(res.getString("ATTIVITAPREVISTE"));
				purchase.setSvolgimentoTirocinio(res.getString("SVOLGIMENTOTIROCINIO"));
				purchase.setStatoTirocinio(res.getString("STATOTIROCINIO"));
				purchase.setProgettoFormativo(res.getString("PROGETTOFORMATIVO"));
				purchase.setDescrizioneEnte(res.getString("DESCRIZIONEENTE"));
				purchase.setMatricola(res.getInt("MATRICOLA"));
				purchase.setPartitaIva(res.getString("PARTITAIVA"));
				listaTirocini.add(purchase);//listaTirocini
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				ps.close();// Chiusura oggetto Statement 
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return listaTirocini;	
	}
	
	/*
	 * Metodo che interroga il DB e restituisce tutti i 'Tirocini'
	 * persenti all' interno di un 'Tirocinante'
	 * 
	 * @return listaTirocini
	 * */
	public synchronized ArrayList allTirocinioTirocinante(int matricola)
	{
		
		Connection con = null; //variabile per la connesione del DB
		PreparedStatement ps = null;// Creazione oggetto Statement
		//ArrayLista di tipo Tirocinio
		ArrayList<Tirocinio> listaTirocini = new ArrayList<Tirocinio>();
		try 
		{
			//Connessione con il DB
			con= new DbConnection().getInstance().getConn();
			//Query Sql per prelevare i Tirocini
			ps= con.prepareStatement("SELECT * "
									+ "FROM TIROCINIO "
									+ "WHERE MATRICOLA="+matricola+";");
			ResultSet res = ps.executeQuery();
			//Ciclo che inserisce all' interno della lista i 'Tirocini'
			//restituiti dalla query
			while(res.next())
			{
				
				Tirocinio purchase= new Tirocinio();
				purchase.setCodTirocinio(res.getInt("CODTIROCINIO"));
				purchase.setDataInizioTirocinio(res.getString("DATAINIZIOTIROCINO"));
				purchase.setCfuPrevisti(res.getShort("CFUPREVISTI"));
				purchase.setCompetenze(res.getString("COMPETENZE"));
				purchase.setCompetenzeAcquisire(res.getString("COMPETENZEACQUISIRE"));
				purchase.setAttivitaPreviste(res.getString("ATTIVITAPREVISTE"));
				purchase.setSvolgimentoTirocinio(res.getString("SVOLGIMENTOTIROCINIO"));
				purchase.setStatoTirocinio(res.getString("STATOTIROCINIO"));
				purchase.setProgettoFormativo(res.getString("PROGETTOFORMATIVO"));
				purchase.setDescrizioneEnte(res.getString("DESCRIZIONEENTE"));
				purchase.setMatricola(res.getInt("MATRICOLA"));
				purchase.setPartitaIva(res.getString("PARTITAIVA"));
				listaTirocini.add(purchase);//listaTirocini
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				ps.close();// Chiusura oggetto Statement 
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return listaTirocini;	
	}
	
	/*
	 * Metodo che interagisce con il DB e inserisce in esso il 'Tirocinio'
	 * passato come parametro
	 * 
	 * dati inseriti sono:
	 * DATAINIZIOTIROCINO, CFUPREVISTI, COMPETENZE, COMPETENZEACQUISIRE,
	 * ATTIVITAPREVISTE, SVOLGIMENTOTIROCINIO, STATOTIROCINIO, MATRICOLA;
	 * 
	 * setta lo STATOTIROCINIO IN: inviataAllaSegreteria
	 * 
	 * @param tirocinio
	 * @return boolean
	 * */
	public synchronized boolean inserisciTirocinio(Tirocinio tirocinio)
	{
		
		Connection con = null; //variabile per la connessione al DB
		PreparedStatement psTirocinio = null;// Creazione oggetto Statement per il 'Tirocinio
		try 
		{
			//Connessione con il DB
			con= new DbConnection().getInstance().getConn();
			
			//Insert per l'inserimento in 'Tirocinio' dei dati parziali del 'Tirocinio'
			psTirocinio= con.prepareStatement("INSERT INTO TIROCINIO(DATAINIZIOTIROCINO, CFUPREVISTI,"
																+ "COMPETENZE, COMPETENZEACQUISIRE,"
																+ "ATTIVITAPREVISTE, SVOLGIMENTOTIROCINIO,"
																+ "STATOTIROCINIO, MATRICOLA) "
										+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			psTirocinio.setString(1, tirocinio.getDataInizioTirocinio());
			psTirocinio.setInt(2, tirocinio.getCfuPrevisti());
			psTirocinio.setString(3, tirocinio.getCompetenze());
			psTirocinio.setString(4, tirocinio.getCompetenzeAcquisire());
			psTirocinio.setString(5, tirocinio.getAttivitaPreviste());
			psTirocinio.setString(6, tirocinio.getSvolgimentoTirocinio());
			psTirocinio.setString(7, "inviataAllaSegreteria");
			psTirocinio.setInt(8, tirocinio.getMatricola());
			
			
			//Se l'inserimento va a buon fine restituisce true
			if(psTirocinio.executeUpdate()==1)
			{
				con.commit();
				return true;
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				psTirocinio.close();// Chiusura oggetto Statement dell' 'Tirocinio'
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		//Ritorna false se l'insert non � andato a buon fine 
		return false;	
	}
	/**
	 * Metodo che interagisce con il DB e modifica lo stato 'DESCRIZIONEENTE' del
	 * 'Tirocinio' dello Studente passando la 'matricola' come parametro
	 * @param matricola
	 * @param descrizione
	 * @return boolan
	 */
	public synchronized boolean aggiornaTirocinio(String matricola,String descrizione) {

		Connection con = null; // variabile per la connessione al DB
		PreparedStatement psTirocinio = null;// Creazione oggetto Statement per il 'Tirocinio
		try {
			// Connessione con il DB
			con = new DbConnection().getInstance().getConn();
			
			
			// Insert per l'inserimento in 'Tirocinio' dei dati parziali del 'Tirocinio'
			psTirocinio = con.prepareStatement("UPDATE TIROCINIO " + "SET DESCRIZIONEENTE ='" + descrizione + "' "
						+ "WHERE MATRICOLA =" + matricola + "; ");
						
			// Se la modifica va a buon fine restituisce true
			if (psTirocinio.executeUpdate() == 1) {
				con.commit();
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psTirocinio.close();// Chiusura oggetto Statement dell' 'Tirocinio'
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// Ritorna false se l'insert non � andato a buon fine
		return false;
	}
	
	/*
	 * Metodo che interagisce con il DB e modifica lo stato 'STATOTIROCNIO'
	 *  del 'Tirocinio' passato come parametro
	 * 
	 * @param codTirocinio
	 * @param statoTirocinio
	 * @return boolean
	 * */
	public synchronized boolean modificaStatoTirocinio(int codTirocinio, String statoTirocinio)
	{
		
		Connection con = null; //variabile per la connessione al DB
		PreparedStatement psTirocinio = null;// Creazione oggetto Statement per il 'Tirocinio
		try 
		{
			//Connessione con il DB
			con= new DbConnection().getInstance().getConn();
			
			//Insert per l'inserimento in 'Tirocinio' dei dati parziali del 'Tirocinio'
			psTirocinio= con.prepareStatement("UPDATE TIROCINIO " 
											+ "SET STATOTIROCINIO ='"+statoTirocinio+"' " 
											+ "WHERE CODTIROCINIO ="+codTirocinio+"; ");
			
			//Se la modifica va a buon fine restituisce true
			if(psTirocinio.executeUpdate()==1)
			{
				con.commit();
				return true;
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				psTirocinio.close();// Chiusura oggetto Statement dell' 'Tirocinio'
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		//Ritorna false se la modifica non � andata a buon fine 
		return false;	
	}
	
	/*
	 * Metodo che interagisce con il DB e modifica il Tirocinio
	 * 
	 * @param tirocinio
	 * @return boolean
	 * */
	public synchronized boolean modificaTirocinio(Tirocinio tirocinio)
	{
		
		Connection con = null;//variabile per la connessione al DB
		PreparedStatement psTirocinio = null;// Creazione oggetto Statement per il 'Tirocinio'
		try 
		{
			//Connessione con il DB
			con= new DbConnection().getInstance().getConn();
			//inserimento di un Tirocinio
			boolean update = inserisciTirocinio(tirocinio);
			//se l'operazione di inserimento non � andata a buon fine
			//faccio la modifica perch� il 'Tirocinio' esiste
			if(update==false)	
			{
				//Update di 'Tirocino'
				psTirocinio= con.prepareStatement("UPDATE TIROCINIO "
												+ "SET DATAINIZIOTIROCINO = ?, CFUPREVISTI = ?, "
													+ "COMPETENZE=?, COMPETENZEACQUISIRE = ?, "
													+ "ATTIVITAPREVISTE = ?, SVOLGIMENTOTIROCINIO = ?, "
													+ "STATOTIROCINIO = ?, PROGETTOFORMATIVO = ?, "
													+ "DESCRIZIONEENTE = ?, MATRICOLA = ? "
													+ "PARTITAIVA = ? "
													+ "WHERE CODTIROCINIO=?;");
				
				psTirocinio.setString(1, tirocinio.getDataInizioTirocinio());
				psTirocinio.setInt(2, tirocinio.getCfuPrevisti());
				psTirocinio.setString(3, tirocinio.getCompetenze());
				psTirocinio.setString(4, tirocinio.getCompetenzeAcquisire());
				psTirocinio.setString(5, tirocinio.getAttivitaPreviste());
				psTirocinio.setString(6, tirocinio.getSvolgimentoTirocinio());
				psTirocinio.setString(7, tirocinio.getStatoTirocinio());
				psTirocinio.setString(8, tirocinio.getProgettoFormativo());
				psTirocinio.setString(9, tirocinio.getDescrizioneEnte());
				psTirocinio.setInt(10, tirocinio.getMatricola());
				psTirocinio.setString(11, tirocinio.getPartitaIva());
				psTirocinio.setInt(12, tirocinio.getCodTirocinio());
				
				//Se la modifica va a buon fine restituisce true
				if(psTirocinio.executeUpdate()==1)
				{
					con.commit();
					return true;
				}
				
				try 
				{
					psTirocinio.close();// Chiusura oggetto Statement dell' 'Tirocinio'
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				return false;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		//Ritorna false se la modifica non � andata a buon fine 
		return false;	
	}
}	
	
