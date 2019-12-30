package model.DAO;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DbConnection;
import model.Tirocinio;
import model.EnteConvenzionato;
import model.Tirocinante;

public class TirocinioDAO {
	
	/*
	 * Metodo che interroga il DB e restituisce tutti i 'Tirocini'
	 * 
	 * 
	 * @return listaTirocini
	 * */
	public synchronized ArrayList allTirocinio()
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
									+ "FROM TIROCINIO, TIROCINANTE, USER, ENTECONVENZIONATO "
									+ "WHERE TIROCINIO.MATRICOLA=TIROCINANTE.MATRICOLA && "
									+ "TIROCINANTE.EMAIL=USER.EMAIL && "
									+ "TIROCINIO.PARTITAIVA=ENTECONVENZIONATO.PARTITAIVA");
			ResultSet res = ps.executeQuery();
			//Ciclo che inserisce all' interno della lista i 'Tirocini'
			//restituiti dalla query
			while(res.next())
			{
				
				Tirocinio tirocinio= new Tirocinio();
				Tirocinante tirocinante= new Tirocinante();
				EnteConvenzionato enteConvenzionato= new EnteConvenzionato();
				//Dati del Tirocinio
				tirocinio.setCodTirocinio(res.getInt("CODTIROCINIO"));
				tirocinio.setDataInizioTirocinio(res.getString("DATAINIZIOTIROCINO"));
				tirocinio.setCfuPrevisti(res.getShort("CFUPREVISTI"));
				tirocinio.setCompetenze(res.getString("COMPETENZE"));
				tirocinio.setCompetenzeAcquisire(res.getString("COMPETENZEACQUISIRE"));
				tirocinio.setAttivitaPreviste(res.getString("ATTIVITAPREVISTE"));
				tirocinio.setSvolgimentoTirocinio(res.getString("SVOLGIMENTOTIROCINIO"));
				tirocinio.setStatoTirocinio(res.getString("STATOTIROCINIO"));
				tirocinio.setProgettoFormativo(res.getString("PROGETTOFORMATIVO"));
				tirocinio.setDescrizioneEnte(res.getString("DESCRIZIONEENTE"));
				tirocinio.setMatricola(res.getInt("MATRICOLA"));
				tirocinio.setPartitaIva(res.getString("PARTITAIVA"));
				//Dati del Tirocinante
				tirocinante.setEmail(res.getString("EMAIL"));
                tirocinante.setName(res.getString("NAME"));
                tirocinante.setSurname(res.getString("SURNAME"));
                tirocinante.setSex(res.getString("SEX").charAt(0));
                tirocinante.setUserType(res.getInt("USER_TYPE"));
                tirocinante.setMatricola(res.getInt("MATRICOLA"));
                tirocinante.setDataNascita(res.getDate("DATANASCITA"));
                tirocinante.setLuogoNascita(res.getString("LUOGONASCITA"));
                tirocinante.setCittadinanza(res.getString("CITTADINANZA"));
                tirocinante.setResidenza(res.getString("RESIDENZA"));
                tirocinante.setCodiceFiscale(res.getString("CODICEFISCALE"));
                tirocinante.setTelefono(res.getLong("TELEFONO"));
                tirocinio.setTirocinante(tirocinante);
                //Dati ente convenzionato
                enteConvenzionato.setEmail(res.getString("EMAIL"));
				enteConvenzionato.setName(res.getString("NAME"));
				enteConvenzionato.setPartitaIva(res.getString("PARTITAIVA"));
				enteConvenzionato.setSede(res.getString("SEDE"));
				enteConvenzionato.setRappresentante(res.getString("RAPPRESENTANTE"));
				enteConvenzionato.setReferente(res.getString("REFERENTE"));
				enteConvenzionato.setTelefono(res.getString("TELEFONO"));
				enteConvenzionato.setDipendenti(res.getShort("DIPENDENTI"));
				enteConvenzionato.setDotRiferimento(res.getString("DOTRIFERIMENTO"));
				enteConvenzionato.setDescrizioneAttivita(res.getString("DESCRIZIONEATTIVITA"));
				enteConvenzionato.setDataDiNascita(res.getString("DATANASCITA"));
				tirocinio.setTirocinante(tirocinante);
				tirocinio.setEnteConvenzionato(enteConvenzionato);
				
				listaTirocini.add(tirocinio);//listaTirocini
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
					+ "FROM TIROCINIO, TIROCINANTE, USER "
					+ "WHERE TIROCINIO.MATRICOLA=TIROCINANTE.MATRICOLA && "
					+ "TIROCINANTE.EMAIL=USER.EMAIL && "
					+ "TIROCINIO.STATOTIROCINIO='"+statoTirocinio+"';");
			ResultSet res = ps.executeQuery();
			//Ciclo che inserisce all' interno della lista i 'Tirocini'
			//restituiti dalla query
			while(res.next())
			{
				
				Tirocinio tirocinio= new Tirocinio();
				Tirocinante tirocinante= new Tirocinante();
				EnteConvenzionato enteConvenzionato= new EnteConvenzionato();
				//Dati del Tirocinio
				tirocinio.setCodTirocinio(res.getInt("CODTIROCINIO"));
				tirocinio.setDataInizioTirocinio(res.getString("DATAINIZIOTIROCINO"));
				tirocinio.setCfuPrevisti(res.getShort("CFUPREVISTI"));
				tirocinio.setCompetenze(res.getString("COMPETENZE"));
				tirocinio.setCompetenzeAcquisire(res.getString("COMPETENZEACQUISIRE"));
				tirocinio.setAttivitaPreviste(res.getString("ATTIVITAPREVISTE"));
				tirocinio.setSvolgimentoTirocinio(res.getString("SVOLGIMENTOTIROCINIO"));
				tirocinio.setStatoTirocinio(res.getString("STATOTIROCINIO"));
				tirocinio.setProgettoFormativo(res.getString("PROGETTOFORMATIVO"));
				tirocinio.setDescrizioneEnte(res.getString("DESCRIZIONEENTE"));
				tirocinio.setMatricola(res.getInt("MATRICOLA"));
				tirocinio.setPartitaIva(res.getString("PARTITAIVA"));
				//Dati del Tirocinante
				tirocinante.setEmail(res.getString("EMAIL"));
                tirocinante.setName(res.getString("NAME"));
                tirocinante.setSurname(res.getString("SURNAME"));
                tirocinante.setSex(res.getString("SEX").charAt(0));
                tirocinante.setUserType(res.getInt("USER_TYPE"));
                tirocinante.setMatricola(res.getInt("MATRICOLA"));
                tirocinante.setDataNascita(res.getDate("DATANASCITA"));
                tirocinante.setLuogoNascita(res.getString("LUOGONASCITA"));
                tirocinante.setCittadinanza(res.getString("CITTADINANZA"));
                tirocinante.setResidenza(res.getString("RESIDENZA"));
                tirocinante.setCodiceFiscale(res.getString("CODICEFISCALE"));
                tirocinante.setTelefono(res.getLong("TELEFONO"));
 
              
				tirocinio.setTirocinante(tirocinante);
				
				listaTirocini.add(tirocinio);//listaTirocini
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
	 * persenti all' interno di un enteConvenzionato
	 * 
	 * @param email
	 * @return listaTirocini
	 * */
	public synchronized ArrayList allTirocinioByEnte(String email)
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
					+ "FROM TIROCINIO, TIROCINANTE, USER, ENTECONVENZIONATO "
					+ "WHERE TIROCINIO.MATRICOLA=TIROCINANTE.MATRICOLA && "
					+ "TIROCINANTE.EMAIL=USER.EMAIL && "
					+ "TIROCINIO.PARTITAIVA=ENTECONVENZIONATO.PARTITAIVA && "
					+ "ENTECONVENZIONATO.EMAIL='"+email+"';");
			ResultSet res = ps.executeQuery();
			//Ciclo che inserisce all' interno della lista i 'Tirocini'
			//restituiti dalla query
			while(res.next())
			{
				
				Tirocinio tirocinio= new Tirocinio();
				Tirocinante tirocinante= new Tirocinante();
				//Dati del Tirocinio
				tirocinio.setCodTirocinio(res.getInt("CODTIROCINIO"));
				tirocinio.setDataInizioTirocinio(res.getString("DATAINIZIOTIROCINO"));
				tirocinio.setCfuPrevisti(res.getShort("CFUPREVISTI"));
				tirocinio.setCompetenze(res.getString("COMPETENZE"));
				tirocinio.setCompetenzeAcquisire(res.getString("COMPETENZEACQUISIRE"));
				tirocinio.setAttivitaPreviste(res.getString("ATTIVITAPREVISTE"));
				tirocinio.setSvolgimentoTirocinio(res.getString("SVOLGIMENTOTIROCINIO"));
				tirocinio.setStatoTirocinio(res.getString("STATOTIROCINIO"));
				tirocinio.setProgettoFormativo(res.getString("PROGETTOFORMATIVO"));
				tirocinio.setDescrizioneEnte(res.getString("DESCRIZIONEENTE"));
				tirocinio.setMatricola(res.getInt("MATRICOLA"));
				tirocinio.setPartitaIva(res.getString("PARTITAIVA"));
				//Dati del Tirocinante
				tirocinante.setEmail(res.getString("EMAIL"));
                tirocinante.setName(res.getString("NAME"));
                tirocinante.setSurname(res.getString("SURNAME"));
                tirocinante.setSex(res.getString("SEX").charAt(0));
                tirocinante.setUserType(res.getInt("USER_TYPE"));
                tirocinante.setMatricola(res.getInt("MATRICOLA"));
                tirocinante.setDataNascita(res.getDate("DATANASCITA"));
                tirocinante.setLuogoNascita(res.getString("LUOGONASCITA"));
                tirocinante.setCittadinanza(res.getString("CITTADINANZA"));
                tirocinante.setResidenza(res.getString("RESIDENZA"));
                tirocinante.setCodiceFiscale(res.getString("CODICEFISCALE"));
                tirocinante.setTelefono(res.getLong("TELEFONO"));
                tirocinio.setTirocinante(tirocinante);
              
				tirocinio.setTirocinante(tirocinante);
				
				listaTirocini.add(tirocinio);//listaTirocini
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
	public synchronized ArrayList allTirocinioTirocinante(long matricola)
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
				
				Tirocinio tirocinio= new Tirocinio();
				tirocinio.setCodTirocinio(res.getInt("CODTIROCINIO"));
				tirocinio.setDataInizioTirocinio(res.getString("DATAINIZIOTIROCINO"));
				tirocinio.setCfuPrevisti(res.getShort("CFUPREVISTI"));
				tirocinio.setCompetenze(res.getString("COMPETENZE"));
				tirocinio.setCompetenzeAcquisire(res.getString("COMPETENZEACQUISIRE"));
				tirocinio.setAttivitaPreviste(res.getString("ATTIVITAPREVISTE"));
				tirocinio.setSvolgimentoTirocinio(res.getString("SVOLGIMENTOTIROCINIO"));
				tirocinio.setStatoTirocinio(res.getString("STATOTIROCINIO"));
				tirocinio.setProgettoFormativo(res.getString("PROGETTOFORMATIVO"));
				tirocinio.setDescrizioneEnte(res.getString("DESCRIZIONEENTE"));
				tirocinio.setMatricola(res.getInt("MATRICOLA"));
				tirocinio.setPartitaIva(res.getString("PARTITAIVA"));
				listaTirocini.add(tirocinio);//listaTirocini
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
			psTirocinio= con.prepareStatement("INSERT INTO TIROCINIO(CODTIROCINIO,DATAINIZIOTIROCINO, CFUPREVISTI, "
																+ "COMPETENZE, COMPETENZEACQUISIRE, "
																+ "ATTIVITAPREVISTE, SVOLGIMENTOTIROCINIO, "
																+ "STATOTIROCINIO, PROGETTOFORMATIVO, DESCRIZIONEENTE, MATRICOLA, PARTITAIVA) "
										+ "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			psTirocinio.setInt(1, tirocinio.getCodTirocinio());
            psTirocinio.setDate(2, new Date(0));
			psTirocinio.setShort(3, tirocinio.getCfuPrevisti());
			psTirocinio.setString(4, tirocinio.getCompetenze());
			psTirocinio.setString(5, tirocinio.getCompetenzeAcquisire());
			psTirocinio.setString(6, tirocinio.getAttivitaPreviste());
			psTirocinio.setString(7, tirocinio.getSvolgimentoTirocinio());
			psTirocinio.setString(8, "In attesa della Segreteria");
			psTirocinio.setString(9, " ");
			psTirocinio.setString(10, " ");
			psTirocinio.setLong(11, tirocinio.getMatricola());
			psTirocinio.setString(12, tirocinio.getPartitaIva());
			
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
	 * Metodo che interagisce con il DB e invia la richiesta all' 'EnteConvenzionato'
	 * @param codTirocinio
	 * @param partitaIva
	 * @param descrizioneEnte
	 * @return boolean
	 */
	public synchronized boolean richiestaEnte(int codTirocinio, String partitaIva, String descrizioneEnte) {

		Connection con = null; // variabile per la connessione al DB
		PreparedStatement psTirocinio = null;// Creazione oggetto Statement per il 'Tirocinio
		try {
			// Connessione con il DB
			con = new DbConnection().getInstance().getConn();
			
			
			// Update per la richiesta all' ente
			psTirocinio = con.prepareStatement("UPDATE TIROCINIO SET DESCRIZIONEENTE='" + descrizioneEnte + "', "
						+ "PARTITAIVA ='" + partitaIva + "' "
						+ "WHERE CODTIROCINIO ='" + codTirocinio + "'; ");
						
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
		// Ritorna false se la richiesta all' ente non e' andata a buon fine
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
			psTirocinio=con.prepareStatement("UPDATE TIROCINIO SET STATOTIROCINIO='"+statoTirocinio+"' WHERE CODTIROCINIO='"+codTirocinio+"';");
			
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
													+ "DESCRIZIONEENTE = ?, MATRICOLA = ?,"
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
				psTirocinio.setLong(10, tirocinio.getMatricola());
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
	
	public synchronized boolean uploadProgettoFormativo(int codTirocinio, String progettoFormativo)
	{
		Connection con = null; //variabile per la connessione al DB
		PreparedStatement psTirocinio = null;// Creazione oggetto Statement per il 'Tirocinio
		try 
		{
			//Connessione con il DB
			con= new DbConnection().getInstance().getConn();
			//Inserimento in 'Tirocinio' del path del 'progetto formativo'
			psTirocinio= con.prepareStatement("UPDATE TIROCINIO " 
											+ "SET PROGETTOFORMATIVO ='"+progettoFormativo+"' " 
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
	 * Metodo che interagisce con il DB e restituisce il path del 
	 * 'PROGETTOFORMATIVO' 
	 * 
	 * @param codTirocinio
	 * @return progettoFormativo
	 * */
	public synchronized String downloadProgettoFormativo(int codTirocinio)
	{
		Connection con = null; //variabile per la connesione del DB
		PreparedStatement ps = null;// Creazione oggetto Statement
		String progettoFormativo=null;
		try 
		{
			//Connessione con il DB
			con= new DbConnection().getInstance().getConn();
			//Query Sql per prelevare il path del progetto formativo
			ps= con.prepareStatement("SELECT PROGETTOFORMATIVO "
									+ "FROM TIROCINIO "
									+ "WHERE CODTIROCINIO="+codTirocinio+";");
			ResultSet res = ps.executeQuery();
			//Ciclo che inserisce all' interno della lista i 'Tirocini'
			//restituiti dalla query
			if(res.next())
			{
			progettoFormativo=res.getString("PROGETTOFORMATIVO");
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
		return progettoFormativo;	
	}
}	
	
