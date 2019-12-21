package model;
/**
 * Class.
 *
 * Questa classe va a rappresentare l'oggetto Tirocinio che nel seguente dominio applicativo raffigura il "Progetto Formativo" del tirocinante.
 */
public class Tirocinio {
    private int codTirocinio;
	private String dataInizioTirocinio;
    private short cfuPrevisti;
    private String competenze;
    private String attivitaPreviste;
    private String svolgimentoTirocinio;
    private String statoTirocinio;
    private String progettoFormativo;
	private String competenzeAcquisire;
	private String descrizioneEnte;
	private int matricola;
	private String partitaIva;
	
	/** Costruttore vuoto. **/
	public Tirocinio() 
	{
	}
	
    /**
     * Contructor.
     *
     * @param codTirocinio is the id of Tirocinio
     * @param dataInizioTirocinio is the start date of the Tirocinio.
     * @param cfuPrevisti are cfu assigned for the Tirocinio.
     * @param competenze are the skills of the Tirocinio.
     * @param competenzeAcquisire are earned skills of the Tirocinio.
     * @param attivitaPreviste are planned activities of the Tirocinio.
     * @param svolgimentoTirocinio is conduct of the Tirocinio.
     * @param statoTirocinio is the state of the Tirocinio.
     * @param progettoFormativo is the training project.
     * @param competenzeAcquisire descrizione delle competenze da Acquisire
     * @param descrizioneEnte descrizione del Tirocinio dello 'Studente' all 'EnteConvenzionato'
     * @param matricola  dello 'Studente' associato
     * @param partitaIva dell' 'EnteConvenzionato' associato
     * 
     */
    public Tirocinio(int codTirocinio, String dataInizioTirocinio, short cfuPrevisti, String competenze, String competenzeAcquisire, String attivitaPreviste, String svolgimentoTirocinio, String statoTirocinio, String progettoFormativo, String descrizioneEnte, int matricola, String partitaIva) {
        this.codTirocinio = codTirocinio;
        this.dataInizioTirocinio = dataInizioTirocinio;
        this.cfuPrevisti = cfuPrevisti;
        this.competenze = competenze;
        this.competenzeAcquisire = competenzeAcquisire;
        this.attivitaPreviste = attivitaPreviste;
        this.svolgimentoTirocinio = svolgimentoTirocinio;
        this.statoTirocinio = statoTirocinio;
        this.progettoFormativo = progettoFormativo;
        this.descrizioneEnte = descrizioneEnte;
        this.matricola=matricola;
        this.partitaIva=partitaIva;
    }

    public int getCodTirocinio() {
        return codTirocinio;
    }

    public void setCodTirocinio(int codTirocinio) {
        this.codTirocinio = codTirocinio;
    }

    public String getDataInizioTirocinio() {
        return dataInizioTirocinio;
    }

    public void setDataInizioTirocinio(String dataInizioTirocinio) {
        this.dataInizioTirocinio = dataInizioTirocinio;
    }

    public short getCfuPrevisti() {
        return cfuPrevisti;
    }

    public void setCfuPrevisti(short cfuPrevisti) {
        this.cfuPrevisti = cfuPrevisti;
    }

    public String getCompetenze() {
        return competenze;
    }

    public void setCompetenze(String competenze) {
        this.competenze = competenze;
    }

    public String getCompetenzeAcquisire() {
        return competenzeAcquisire;
    }

    public void setCompetenzeAcquisire(String competenzeAcquisire) {
        this.competenzeAcquisire = competenzeAcquisire;
    }

    public String getAttivitaPreviste() {
        return attivitaPreviste;
    }

    public void setAttivitaPreviste(String attivitaPreviste) {
        this.attivitaPreviste = attivitaPreviste;
    }

    public String getSvolgimentoTirocinio() {
        return svolgimentoTirocinio;
    }

    public void setSvolgimentoTirocinio(String svolgimentoTirocinio) {
        this.svolgimentoTirocinio = svolgimentoTirocinio;
    }

    public String getStatoTirocinio() {
        return statoTirocinio;
    }

    public void setStatoTirocinio(String statoTirocinio) {
        this.statoTirocinio = statoTirocinio;
    }

    public String getProgettoFormativo() {
        return progettoFormativo;
    }

    public void setProgettoFormativo(String progettoFormativo) {
        this.progettoFormativo = progettoFormativo;
    }
    
    public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
}
