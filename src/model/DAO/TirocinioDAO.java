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
	 * @return listaEnti
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
									+ "WHERE STATOTIROCINIO="+statoTirocinio+"");
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
										+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
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
		//Ritorna false se l'insert non è andato a buon fine 
		return false;	
	}
}	
