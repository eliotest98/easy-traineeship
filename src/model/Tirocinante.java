package model;

import java.util.Date;

/**
 * Class.
 *
 * Questa classe va a rappresentare l'oggetto Tirocinante che nel seguente dominio applicativo raffigura il tirocinante che si presta al Tirocinio.
 */
public class Tirocinante extends Student {
	private String email;
	private String name;
	private String surname;
	private char sex;
	private String password;
	private int userType;
    private int matricola;
    private Date dataNascita;
    private String luogoNascita;
    private String cittadinanza;
    private String residenza;
    private String codiceFiscale;
    private long telefono;
    
	/**
     * Contructor.
     *
     * @param matricola is the id of Tirocinante
     * @param dataNascita is the birth date of the Tirocinante.
     * @param luogoNascita is the place of birth of the Tirocinante.
     * @param cittadinanza is the citizenship of the Tirocinante.
     * @param residenza is the residence of the Tirocinante.
     * @param codiceFiscale is the tax code of the Tirocinante.
     * @param telefono is the number phone of the Tirocinante.
     */
    public Tirocinante(String email, String name, String surname, char sex, String password,
    	      int userType, int matricola, Date dataNascita, String luogoNascita, String cittadinanza, String residenza, String codiceFiscale, long telefono) {
    	this.email = email;
    	this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.password = password;
        this.userType = userType;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}


    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
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
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
