package model;

import java.util.Date;

/**
 * Class.
 *
 * Questa classe va a rappresentare l'oggetto Tirocinante che nel seguente dominio applicativo raffigura il tirocinante che si presta al Tirocinio.
 */
public class Tirocinante extends Student {
    private long matricola;
    private String facolta;
    private Date dataNascita;
    private String luogoNascita;
    private String cittadinanza;
    private String residenza;
    private String codiceFiscale;
    private long telefono;
    /**
     * Contructor.
     *
     * @param matricola � la chiave primaria del Tirocinante.
     * @param facolta � sempre informatica, ma � stato messo in caso di ampliamento del sistema.
     * @param dataNascita � la data di nascita del Tirocinante.
     * @param luogoNascita is the place of birth of the Tirocinante.
     * @param cittadinanza is the citizenship of the Tirocinante.
     * @param residenza is the residence of the Tirocinante.
     * @param codiceFiscale is the tax code of the Tirocinante.
     * @param telefono is the number phone of the Tirocinante.
     */
    public Tirocinante(long matricola, String facolta, Date dataNascita, String luogoNascita, String cittadinanza, String residenza, String codiceFiscale, long telefono) {
        this.matricola = matricola;
        this.facolta = facolta;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.cittadinanza = cittadinanza;
        this.residenza = residenza;
        this.codiceFiscale = codiceFiscale;
        this.telefono = telefono;
    }

	public Tirocinante() {
    }

    public long getMatricola() {
        return matricola;
    }

    public void setMatricola(long matricola) {
        this.matricola = matricola;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getCittadinanza() {
        return cittadinanza;
    }

    public void setCittadinanza(String cittadinanza) {
        this.cittadinanza = cittadinanza;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getFacolta() {
      return facolta;
    }

    public void setFacolta(String facolta) {
      this.facolta = facolta;
    }
}
