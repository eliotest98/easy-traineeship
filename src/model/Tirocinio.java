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
    private String competenzeAcquisite;
    private String attivitaPreviste;
    private String svolgimentoTirocinio;
    private String statoTirocinio;
    private String progettoFormativo;
    /**
     * Contructor.
     *
     * @param codTirocinio is the id of Tirocinio
     * @param dataInizioTirocinio is the start date of the Tirocinio.
     * @param cfuPrevisti are cfu assigned for the Tirocinio.
     * @param competenze are the skills of the Tirocinio.
     * @param competenzeAcquisite are earned skills of the Tirocinio.
     * @param attivitaPreviste are planned activities of the Tirocinio.
     * @param svolgimentoTirocinio is conduct of the Tirocinio.
     * @param statoTirocinio is the state of the Tirocinio.
     * @param progettoFormativo is the training project.
     */
    public Tirocinio(int codTirocinio, String dataInizioTirocinio, short cfuPrevisti, String competenze, String competenzeAcquisite, String attivitaPreviste, String svolgimentoTirocinio, String statoTirocinio, String progettoFormativo) {
        this.codTirocinio = codTirocinio;
        this.dataInizioTirocinio = dataInizioTirocinio;
        this.cfuPrevisti = cfuPrevisti;
        this.competenze = competenze;
        this.competenzeAcquisite = competenzeAcquisite;
        this.attivitaPreviste = attivitaPreviste;
        this.svolgimentoTirocinio = svolgimentoTirocinio;
        this.statoTirocinio = statoTirocinio;
        this.progettoFormativo = progettoFormativo;
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

    public String getCompetenzeAcquisite() {
        return competenzeAcquisite;
    }

    public void setCompetenzeAcquisite(String competenzeAcquisite) {
        this.competenzeAcquisite = competenzeAcquisite;
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
}
