package model.DAO;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DbConnection;
import model.EnteConvenzionato;

public class EnteConvenzionatoDAO {
	public synchronized ArrayList allEnte()
	{
		Connection con = null;
		PreparedStatement ps = null;
		ArrayList<EnteConvenzionato> listaEnti = new ArrayList<EnteConvenzionato>();
		try 
		{
			con= new DbConnection().getInstance().getConn();
			
			ps= con.prepareStatement("SELECT * "
									+ "FROM ENTECONVENZIONATO, USER"
									+ "WHERE ENTECONVENZIONATO.EMAIL=USER.EMAIL");
			ResultSet res = ps.executeQuery();
			
			while(res.next())
			{
				EnteConvenzionato purchase= new EnteConvenzionato();
				purchase.setEmail(res.getString("EMAIL"));
				purchase.setName(res.getString("NAME"));
				purchase.setPartitaIva(res.getString("PARTITAIVA"));
				purchase.setSede(res.getString("SEDE"));
				purchase.setRappresentante(res.getString("RAPPRESENTANTE"));
				purchase.setTelefono(res.getString("TELEFONO"));
				purchase.setDipendenti(res.getShort("DIPENDENTI"));
				purchase.setDotRiferimento(res.getString("DOTRIFERIMENTO"));
				purchase.setDescrizioneAttivita(res.getString("DESCRIZIONEATTIVITA"));
				
				listaEnti.add(purchase);
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
				ps.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return listaEnti;	
	}
	
	public synchronized boolean inserisciEnte(EnteConvenzionato enteConvenzionato)
	{
		
		Connection con = null;
		PreparedStatement psEnteConvenzionato = null;
		PreparedStatement psUser = null;

		try 
		{
			con= new DbConnection().getInstance().getConn();
			
			psEnteConvenzionato= con.prepareStatement("INSERT INTO ENTECONVENZIONATO(PARTITAIVA, SEDE, RAPPRESENTANTE,"
														+ " TELEFONO, DIPENDENTI, DOTRIFERIMENTO"
														+ "DESCRIZIONEATTIVITA, EMAIL) "
														+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			psEnteConvenzionato.setString(1, enteConvenzionato.getPartitaIva());
			psEnteConvenzionato.setString(2, enteConvenzionato.getSede());
			psEnteConvenzionato.setString(3, enteConvenzionato.getRappresentante());
			psEnteConvenzionato.setString(4, enteConvenzionato.getTelefono());	
			psEnteConvenzionato.setInt(5, enteConvenzionato.getDipendenti());
			psEnteConvenzionato.setString(6, enteConvenzionato.getDotRiferimento());
			psEnteConvenzionato.setString(7, enteConvenzionato.getDescrizioneAttivita());
			psEnteConvenzionato.setString(8, enteConvenzionato.getEmail());	
			
			psUser= con.prepareStatement("INSERT INTO USER(EMAIL, NAME, USER_TYPE) "
													+ "VALUES (?, ?, 3)");
			psUser.setString(1, enteConvenzionato.getEmail());
			psUser.setString(2, enteConvenzionato.getName());

			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				psEnteConvenzionato.close();
				psUser.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return false;	
	}
	
	public synchronized boolean modificaEnte(EnteConvenzionato enteConvenzionato)
	{
		
		Connection con = null;
		PreparedStatement psEnteConvenzionato = null;
		PreparedStatement psUser = null;
		
		
		try 
		{
			con= new DbConnection().getInstance().getConn();
			
			boolean update = inserisciEnte(enteConvenzionato);
			
			if(update==false)	
			{
			
				psEnteConvenzionato= con.prepareStatement("UPDATE ENTECONVENZIONATO"
														+ "SET SEDE = ?, RAPPRESENTANTE = ?, TELEFONO = ?,"
															+ "DIPENDENTI = ?, DOTRIFERIMENTO = ?"
															+ "DESCRIZIONEATTIVITA = ?, EMAIL = ? "
															+ "WHERE PARTITAIVA=?;");
				
				psEnteConvenzionato.setString(1, enteConvenzionato.getSede());
				psEnteConvenzionato.setString(2, enteConvenzionato.getRappresentante());
				psEnteConvenzionato.setString(3, enteConvenzionato.getTelefono());	
				psEnteConvenzionato.setInt(4, enteConvenzionato.getDipendenti());
				psEnteConvenzionato.setString(5, enteConvenzionato.getDotRiferimento());
				psEnteConvenzionato.setString(6, enteConvenzionato.getDescrizioneAttivita());
				psEnteConvenzionato.setString(7, enteConvenzionato.getEmail());	
				psEnteConvenzionato.setString(8, enteConvenzionato.getPartitaIva());
				
				psUser= con.prepareStatement("UPDATE USER"
											+ "SET NAME = ?"
											+ "WHERE EMAIL = ?;");
				psUser.setString(1, enteConvenzionato.getName());
				psUser.setString(2, enteConvenzionato.getEmail());

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
				psEnteConvenzionato.close();
				psUser.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return false;	
	}
	
	public synchronized boolean updatePassword(String email, String password)
	{
		
		Connection con = null;
		PreparedStatement psUser = null;
			
		try 
		{
			con= new DbConnection().getInstance().getConn();
			
			psUser= con.prepareStatement("UPDATE USER"
										+ "SET PASSWORD ="+password+""
										+ "WHERE EMAIL = "+email+";");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				psUser.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return false;	
	}
}
