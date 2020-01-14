package model;

import java.sql.Date;

/*
 * Class.
 *
 * Questa classe va a rappresentare l'oggetto Tirocinante che nel seguente dominio applicativo
 * raffigura il tirocinante che si presta al Tirocinio.
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
   * @param matricola ï¿½ la chiave primaria del Tirocinante.
   * @param dataNascita è la data di nascita del Tirocinante.
   * @param luogoNascita is the place of birth of the Tirocinante.
   * @param cittadinanza è la cittadinanza del Tirocinante.
   * @param residenza è la residenza del Tirocinante.
   * @param codiceFiscale è il codice fiscale del Tirocinante.
   * @param telefono + il numero di telefono del Tirocinante.
   */
  public Tirocinante(String email, String name, String surname, char sex, String password,
      int userType, long matricola, Date dataNascita, String luogoNascita, String cittadinanza,
      String residenza, String codiceFiscale, long telefono) {
    super.setName(name);
    super.setSurname(surname);
    super.setPassword(password);
    super.setSex(sex);
    super.setUserType(userType);
    super.setEmail(email);
    this.matricola = matricola;
    this.facolta = "Informatica";
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
    return super.getName();
  }

  public void setName(String name) {
    super.setName(name);
  }

  public String getSurname() {
    return super.getSurname();
  }

  public void setSurname(String surname) {
    super.setSurname(surname);;
  }

  public char getSex() {
    return super.getSex();
  }

  public void setSex(char sex) {
    super.setSex(sex);;
  }

  public String getPassword() {
    return super.getPassword();
  }

  public void setPassword(String password) {
    super.setPassword(password);
  }

  public int getUserType() {
    return super.getUserType();
  }

  public void setUserType(int userType) {
    super.setUserType(userType);;
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
