package model;

import interfacce.UserInterface;

public class EnteConvenzionato implements UserInterface {
  /**
   * Variabili.
   */
  private String email;
  private String name;
  private String surname;
  private char sex;
  private String password;
  private int userType;
  private String dataDiNascita;
  private String partitaIva;
  private String sede;
  private String rappresentante;
  private String referente;
  private String telefono;
  private int dipendenti;
  private String dotRiferimento;
  private String tipoTirocinio;
  private String descrizioneAttivita;

  /** Costruttore vuoto. **/
  public EnteConvenzionato() {
  }

  /**
   * Costruttore con tutti i parametri.
   */
  public EnteConvenzionato(String email, String name, String surname, char sex, String password,
      int userType, String dataDiNascita, String partitaIva, String sede, String rappresentante,
      String referente, String telefono, int dipendenti, String dotRiferimento,
      String tipoTirocinio, String descrizioneAttivita) {
    super();
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.sex = sex;
    this.password = password;
    this.userType = userType;
    this.dataDiNascita = dataDiNascita;
    this.partitaIva = partitaIva;
    this.sede = sede;
    this.rappresentante = rappresentante;
    this.referente = referente;
    this.telefono = telefono;
    this.dipendenti = dipendenti;
    this.dotRiferimento = dotRiferimento;
    this.tipoTirocinio = tipoTirocinio;
    this.descrizioneAttivita = descrizioneAttivita;
  }

  /**
   * Restituisce l'e-mail dell'Ente Convenzionato.
   * 
   * @return email
   */
  public String getEmail() {
    return email;
  }


  /**
   * Imposta l'e-mail dell'Ente Convenzionato.
   * 
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Restituisce il Nome dell'Ente Convenzionato.
   * 
   * @return name.
   */
  public String getName() {
    return name;
  }

  /**
   * Imposta il Nome dell'Ente Convenzionato.
   * 
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Metodo di interfaccia
   * 
   * @return surname
   */
  public String getSurname() {
    return surname;
  }

  /**
   * Metodo di interfaccia.
   * 
   * @param surname
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /**
   * Restituisce il Sesso del Rappresentante dell'Ente Convenzionato ('M' o 'F').
   * 
   * @return sex
   */
  public char getSex() {
    return sex;
  }

  /**
   * Imposta il Sesso del Rappresentante per l'Ente Convenzionato ('M' o 'F')
   * 
   * @param sex
   */
  public void setSex(char sex) {
    this.sex = sex;
  }

  /**
   * Restituisce la Password dell'Ente Convenzionato
   * 
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Imposta la Password dell'Ente Convenzionato
   * 
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Restiuisce l'Identificatore del Tipo di Utente (0, 1, 2, 3)
   * 
   * @return userType
   */
  public int getUserType() {
    return userType;
  }

  /**
   * Imposta l'Identificatore del Tipo di Utente (0, 1, 2, 3)
   * 
   * @param userType
   */
  public void setUserType(int userType) {
    this.userType = userType;
  }

  /**
   * Restituisce la Data di Nascita del rappresentante dell'Ente Convenzionato
   * 
   * @return the dataDiNascita
   */
  public String getDataDiNascita() {
    return dataDiNascita;
  }

  /**
   * Inserisce la Data di Nascita del rappresentante per l'Ente Convenzionato
   * 
   * @param dataDiNascita
   */
  public void setDataDiNascita(String dataDiNascita) {
    this.dataDiNascita = dataDiNascita;
  }

  /**
   * Restituisce la Partita IVA dell'Ente Convenzionato
   * 
   * @return partitaIva
   */
  public String getPartitaIva() {
    return partitaIva;
  }

  /**
   * Imposta la Partita IVA per l'Ente Convenzionato
   * 
   * @param partitaIva
   */
  public void setPartitaIva(String partitaIva) {
    this.partitaIva = partitaIva;
  }

  /**
   * Restituisce la sede dell'Ente Convenzionato
   * 
   * @return sede
   */
  public String getSede() {
    return sede;
  }

  /**
   * Imposta la Sede per l'Ente Convenzionato
   * 
   * @param sede
   */
  public void setSede(String sede) {
    this.sede = sede;
  }

  /**
   * Restituisce il Rappresentante dell'Ente Convenzionato
   * 
   * @return rappresentante
   */
  public String getRappresentante() {
    return rappresentante;
  }

  /**
   * Imposta il Rappresentante per l'Ente Convenzionato
   * 
   * @param rappresentante
   */
  public void setRappresentante(String rappresentante) {
    this.rappresentante = rappresentante;
  }

  /**
   * Restituisce il Referente dell'Ente Convenzionato
   * 
   * @return referente
   */
  public String getReferente() {
    return referente;
  }

  /**
   * Imposta il Referente per l'Ente Convenzionato
   * 
   * @param referente
   */
  public void setReferente(String referente) {
    this.referente = referente;
  }

  /**
   * Restituisce il Numero di Telefono dell' Ente Convenzionato
   * 
   * @return telefono
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Imposta il Numero di Telefono per l'Ente Convenzionato
   * 
   * @param telefono
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * Restituisce il Numero di Dipendenti dell'Ente Convenzionato
   * 
   * @return dipendenti
   */
  public int getDipendenti() {
    return dipendenti;
  }

  /**
   * Imposta il Numero di Dipendenti per l'Ente Convenzionato
   * 
   * @param dipendenti
   */
  public void setDipendenti(int dipendenti) {
    this.dipendenti = dipendenti;
  }

  /**
   * Restituisce il Dottore di Riferimento dell'Ente Convenzionato
   * 
   * @return dotRiferimento
   */
  public String getDotRiferimento() {
    return dotRiferimento;
  }

  /**
   * Imposta il Dottore di Riferimento per l'Ente Convenzionato
   * 
   * @param dotRiferimento
   */
  public void setDotRiferimento(String dotRiferimento) {
    this.dotRiferimento = dotRiferimento;
  }


  /**
   * Restituisce il Tipo Tirocinio dell'Ente Convenzionato
   * 
   * @return tipoTirocinio
   */
  public String getTipoTirocinio() {
    return tipoTirocinio;
  }

  /**
   * Imposta il Tipo Tirocinio per l'Ente Convenzionato (TE)
   * 
   * @param tipoTirocinio
   */
  public void setTipoTirocinio(String tipoTirocinio) {
    this.tipoTirocinio = tipoTirocinio;
  }

  /**
   * Restituisce la Descrizione delle Attività dell'Ente Convenzionato
   * 
   * @return descrizioneAttivita
   */
  public String getDescrizioneAttivita() {
    return descrizioneAttivita;
  }

  /**
   * Imposta la Descrizione delle Attività per l'Ente Convenzionato
   * 
   * @param descrizioneAttivita
   */
  public void setDescrizioneAttivita(String descrizioneAttivita) {
    this.descrizioneAttivita = descrizioneAttivita;
  }

  /**
   * Specifica se l'Utente è abilitato a visualizzare la pagina
   */
  public boolean validate() {
    return new Stub().database.containsKey(getEmail())
        && new Stub().database.containsValue(getPassword());
  }
}
