package model.DAO;

import controller.DbConnection;
import model.Tirocinante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Tirocinante DAO
 *
 * La seguente classe si occupa di gestire i metodi CRUD dell'entità Tirocinante.
 */
public class TirocinanteDAO {
    /**
     * Questa funzione permette la ricerca del Tirocinante all'interno della base di dati attraverso
     * il numero della matricola.
     * @param matricola
     * @return Tirocinante tirocinante
     */
    public Tirocinante ricercaTirocinanteByMatricola(long matricola) {
        Tirocinante tirocinante = null;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DbConnection().getInstance().getConn();
            ps = conn.prepareStatement("SELECT * "
					+ "FROM TIROCINANTE, USER "
					+ "WHERE TIROCINANTE.EMAIL=USER.EMAIL AND TIROCINANTE.matricola = ?");
            ps.setLong(1, matricola);		
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                tirocinante = new Tirocinante();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                tirocinante.setEmail(rs.getString("EMAIL"));
                tirocinante.setName(rs.getString("NAME"));
                tirocinante.setSurname(rs.getString("SURNAME"));
                tirocinante.setSex(rs.getString("SEX").charAt(0));
                tirocinante.setUserType(rs.getInt("USER_TYPE"));
                tirocinante.setMatricola(rs.getInt("MATRICOLA"));
                tirocinante.setDataNascita(rs.getDate("DATANASCITA"));
                tirocinante.setLuogoNascita(rs.getString("LUOGONASCITA"));
                tirocinante.setCittadinanza(rs.getString("CITTADINANZA"));
                tirocinante.setResidenza(rs.getString("RESIDENZA"));
                tirocinante.setCodiceFiscale(rs.getString("CODICEFISCALE"));
                tirocinante.setTelefono(rs.getLong("TELEFONO"));
                
            }
        } catch (SQLException e) {
            // Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                // Auto-generated catch block
                e.printStackTrace();
            }
        }
        return tirocinante;
    }
    
    public synchronized Tirocinante ricercaTirocinanteByEmail(String email) {
        Tirocinante tirocinante = new Tirocinante();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DbConnection().getInstance().getConn();
            ps = conn.prepareStatement("SELECT * "
					+ "FROM TIROCINANTE, USER "
					+ "WHERE TIROCINANTE.EMAIL=USER.EMAIL AND USER.EMAIL='" + email +"';");		
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                tirocinante.setEmail(rs.getString("EMAIL"));
                tirocinante.setName(rs.getString("NAME"));
                tirocinante.setSurname(rs.getString("SURNAME"));
                tirocinante.setSex(rs.getString("SEX").charAt(0));
                tirocinante.setUserType(rs.getInt("USER_TYPE"));
                tirocinante.setMatricola(rs.getInt("MATRICOLA"));
                tirocinante.setDataNascita(rs.getDate("DATANASCITA"));
                tirocinante.setLuogoNascita(rs.getString("LUOGONASCITA"));
                tirocinante.setCittadinanza(rs.getString("CITTADINANZA"));
                tirocinante.setResidenza(rs.getString("RESIDENZA"));
                tirocinante.setCodiceFiscale(rs.getString("CODICEFISCALE"));
                tirocinante.setTelefono(rs.getLong("TELEFONO"));
                
            }
        } catch (SQLException e) {
            // Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                // Auto-generated catch block
                e.printStackTrace();
            }
        }
        return tirocinante;
    }

    /**
     * Questa funzione permette di ricercare tutti i tirocinanti all'interno della base di dati.
     * @return List<Tirocinante> tirocinanti
     */
    public ArrayList<Tirocinante> allTirocinante() {

        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<Tirocinante> tirocinanti = null;

        try {
            con = new DbConnection().getInstance().getConn();
            ps= con.prepareStatement("SELECT * "
					+ "FROM TIROCINANTE, USER "
					+ "WHERE TIROCINANTE.EMAIL=USER.EMAIL");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tirocinanti = new ArrayList<Tirocinante>();
                Tirocinante tirocinante = new Tirocinante();
                //DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                tirocinante.setEmail(rs.getString("EMAIL"));
                tirocinante.setName(rs.getString("NAME"));
                tirocinante.setSurname(rs.getString("SURNAME"));
                tirocinante.setSex(rs.getString("SEX").charAt(0));
                tirocinante.setUserType(rs.getInt("USER_TYPE"));
                tirocinante.setMatricola(rs.getInt("MATRICOLA"));
                tirocinante.setDataNascita(rs.getDate("DATANASCITA"));
                tirocinante.setLuogoNascita(rs.getString("LUOGONASCITA"));
                tirocinante.setCittadinanza(rs.getString("CITTADINANZA"));
                tirocinante.setResidenza(rs.getString("RESIDENZA"));
                tirocinante.setCodiceFiscale(rs.getString("CODICEFISCALE"));
                tirocinante.setTelefono(rs.getLong("TELEFONO"));
                
                tirocinanti.add(tirocinante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tirocinanti;
    }

    /**
     * Questa funzione permette di inserire un nuovo tirocinante all'interno della base di dati.
     * @param tirocinante
     * @return boolean result
     */
    public boolean inserisciTirocinante(Tirocinante tirocinante) {

        Connection con = null;
        PreparedStatement psTirocinante = null;

        try {
            con = new DbConnection().getInstance().getConn();

            psTirocinante = con.prepareStatement(
                "INSERT INTO TIROCINANTE(MATRICOLA, DATANASCITA, LUOGONASCITA, "
                    + "CITTADINANZA, RESIDENZA, CODICEFISCALE, TELEFONO, EMAIL) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            psTirocinante.setLong(1, tirocinante.getMatricola());
            //DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);
            Date date = tirocinante.getDataNascita();
            psTirocinante.setDate(2, (java.sql.Date) date);
            psTirocinante.setString(3, tirocinante.getLuogoNascita());
            psTirocinante.setString(4, tirocinante.getCittadinanza());
            psTirocinante.setString(5, tirocinante.getResidenza());
            psTirocinante.setString(6, tirocinante.getCodiceFiscale());
            psTirocinante.setLong(7, tirocinante.getTelefono());
            psTirocinante.setString(8, tirocinante.getEmail());


            if ( (psTirocinante.executeUpdate() == 1)) {
            	con.commit();
            	return true;
            }
                

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                psTirocinante.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    /**
     * Questa funzione permette di aggiornare la password del tirocinante.
     * @param email
     * @param password
     * @return boolean result
     */
    public boolean updatePassword(String email, String password) {
        {

            Connection con = null;
            PreparedStatement psUser = null;

            try {
                con = new DbConnection().getInstance().getConn();

                psUser = con.prepareStatement(
                    "UPDATE USER " + "SET PASSWORD =" + password + " WHERE EMAIL = '" + email
                        + "';");
                if (psUser.executeUpdate() == 1) {
                	con.commit();
                    return true;
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    psUser.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }
}
