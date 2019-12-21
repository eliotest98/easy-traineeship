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
    public Tirocinante ricercaTirocinante(int matricola) {
        Tirocinante tirocinante = null;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DbConnection().getInstance().getConn();
            String sql = "SELECT * FROM TIROCINANTE WHERE matricola = ?";
            ps = conn.prepareStatement(sql);
            ps.executeQuery(sql);
            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                tirocinante = new Tirocinante();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                tirocinante.setMatricola(rs.getInt("MATRICOLA"));
                tirocinante.setDataNascita(df.format(rs.getDate("DATANASCITA")));
                tirocinante.setLuogoNascita(rs.getString("LUOGONASCITA"));
                tirocinante.setCittadinanza(rs.getString("CITTADINANZA"));
                tirocinante.setResidenza(rs.getString("RESIDENZA"));
                tirocinante.setCodiceFiscale(rs.getString("CODICEFISCALE"));
                tirocinante.setTelefono(rs.getInt("TELEFONO"));
                tirocinante.setEmail("EMAIL");
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
    public List<Tirocinante> allTirocinante() {

        Connection con = null;
        PreparedStatement ps = null;
        List<Tirocinante> tirocinanti = null;

        try {
            con = new DbConnection().getInstance().getConn();
            ps = con.prepareStatement("SELECT * " + "FROM TIROCINANTE ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tirocinanti = new ArrayList<Tirocinante>();
                Tirocinante tirocinante = new Tirocinante();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                tirocinante.setMatricola(rs.getInt("MATRICOLA"));
                tirocinante.setDataNascita(df.format(rs.getDate("DATANASCITA")));
                tirocinante.setLuogoNascita(rs.getString("LUOGONASCITA"));
                tirocinante.setCittadinanza(rs.getString("CITTADINANZA"));
                tirocinante.setResidenza(rs.getString("RESIDENZA"));
                tirocinante.setCodiceFiscale(rs.getString("CODICEFISCALE"));
                tirocinante.setTelefono(rs.getInt("TELEFONO"));
                tirocinante.setEmail("EMAIL");
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
        PreparedStatement psUser = null;

        try {
            con = new DbConnection().getInstance().getConn();

            psUser = con.prepareStatement(
                "INSERT INTO USER(EMAIL, NAME, SURNAME, SEX, PASSWORD, USER_TYPE) "
                    + "VALUES (?, ?, ?, ?, ?, 0)");
            psUser.setString(1, tirocinante.getEmail());
            psUser.setString(2, tirocinante.getName());
            psUser.setString(3, tirocinante.getSurname());
            psUser.setString(4, " ");
            psUser.setString(5, tirocinante.getPassword());

            psTirocinante = con.prepareStatement(
                "INSERT INTO TIROCINANTE(MATRICOLA, DATANASCITA, LUOGONASCITA,"
                    + " CITTADINANZA, RESIDENZA, CODICEFISCALE," + "TELEFONO, EMAIL) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            psTirocinante.setInt(1, tirocinante.getMatricola());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALIAN);
            Date date = format.parse(tirocinante.getDataNascita());
            psTirocinante.setDate(2, (java.sql.Date) date);
            psTirocinante.setString(3, tirocinante.getLuogoNascita());
            psTirocinante.setString(4, tirocinante.getCittadinanza());
            psTirocinante.setString(5, tirocinante.getResidenza());
            psTirocinante.setString(6, tirocinante.getCodiceFiscale());
            psTirocinante.setLong(7, tirocinante.getTelefono());
            psTirocinante.setString(8, tirocinante.getEmail());


            if ((psUser.executeUpdate() == 1) && (psTirocinante.executeUpdate() == 1))
                return true;

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                psTirocinante.close();
                psUser.close();
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
                if (psUser.executeUpdate() == 1)
                    return true;
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
