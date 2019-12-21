package model;

/**
 * Class.
 *
 * Questa classe va a rappresentare l'oggetto Tirocinante che nel seguente
 * dominio applicativo raffigura il tirocinante che si presta al Tirocinio.
 */
public class Tirocinante extends Student {
	private int matricola;
	private String dataNascita;
	private String luogoNascita;
	private String cittadinanza;
	private String residenza;
	private String codiceFiscale;
	private long telefono;

	/**
	 * Contructor.
	 *
	 * @param matricola     is the id of Tirocinante
	 * @param dataNascita   is the birth date of the Tirocinante.
	 * @param luogoNascita  is the place of birth of the Tirocinante.
	 * @param cittadinanza  is the citizenship of the Tirocinante.
	 * @param residenza     is the residence of the Tirocinante.
	 * @param codiceFiscale is the tax code of the Tirocinante.
	 * @param telefono      is the number phone of the Tirocinante.
	 */
	public Tirocinante(int matricola, String dataNascita, String luogoNascita, String cittadinanza, String residenza,
			String codiceFiscale, long telefono) {
		this.matricola = matricola;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.cittadinanza = cittadinanza;
		this.residenza = residenza;
		this.codiceFiscale = codiceFiscale;
		this.telefono = telefono;
	}

	public Tirocinante() {
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
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
}
