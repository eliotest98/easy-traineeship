<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" import="controller.CheckSession"%>

<%
    String pageName = "InviaRichiestaET.jsp";
    String pageFolder = "_areaStudent";
%>
<!-- Pagina per l'invio della richiesta di inizio Tirocinio, dallo studente alla segreteria. -->

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/partials/head.jsp" />
</head>

<body onLoad="">
<div class="page-wrapper" id="InvioRichiestaET">

    <!-- Preloader 
    <div class="preloader"></div>-->


    <jsp:include page="/partials/header.jsp">
        <jsp:param name="pageName" value="<%= pageName %>" />
        <jsp:param name="pageFolder" value="<%= pageFolder %>" />
    </jsp:include>


    <div class="sidebar-page-container basePage signUpPage">
        <div class="auto-container">
            <div class="row clearfix">
                <div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="content">
                        <div class="news-block-seven">
                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container">
                                <div class="panel">
                                    <h2 class="text-center">Invio della richiesta di inizio Tirocinio:</h2>
                                    <p class="text-center">Compilare i campi per l'invio della richiesta di inizio Tirocinio.</p>
                                </div>
                                <!-- Form per l'invio della richiesta. -->
                                <form id="invioRichiestaTirocinio" action="/ServletRichiestaInizioTirocinioET" method="post">
                                	<!-- Campo nome tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="nomeTirocinante">Nome</label>
                                        <input type="text" class="form-control" id="nomeTirocinante" name="nomeTirocinante"
                                               placeholder="Nome Tirocinante" size="50" minlength="1" maxlength="50" required>
                                    </div>
                                    <!-- Campo cognome tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cognomeTirocinante">Cognome</label>
                                        <input type="text" class="form-control" id="cognomeTirocinante" name="cognomeTirocinante"
                                               placeholder="Cognome Tirocinante" size="50" minlength="1" maxlength="50" required>
                                    </div>
                                    <!-- Campo matricola tirocinante, lunghezza 10 cifre, formato solo numeri. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="matricolaTirocinante">Matricola</label>
                                        <input type="tel" class="form-control" id="matricolaTirocinante" name="matricolaTirocinante"
                                               placeholder="Matricola Tirocinante" size="10" minlength="10" maxlength="10" required>
                                    </div>
                                    <!-- Campo facolta tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="facoltaTirocinante">Facolt&agrave;</label>
                                        <input type="text" class="form-control" id="facoltaTirocinante" name="facoltaTirocinante"
                                               placeholder="Facolta Tirocinante" size="50" minlength="1" maxlength="50" required>
                                    </div>
                                     <!-- Campo data di nascita, lunghezza ==10, formato cifre + /. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="dataDiNascita">Data di Nascita</label>
                                        <input type="tel" class="form-control" name="dataDiNascita" id="dataDiNascita"
                                               placeholder="Data di Nascita Tirocinante" size="10" minlength="10" maxlength="10" required>
                                    </div>
                                    <!-- Campo luogo di nascita, lunghezza fra 1 e 64, formato caratteri alfabetici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="luogoDiNascita">Luogo di Nascita</label>
                                        <input type="text" class="form-control" name="luogoDiNascita" id="luogoDiNascita" 
                                               placeholder="Luogo di Nascita Tirocinante" size="64" minlength="1" maxlength="64" required>
                                    </div>
                                    <!-- Campo cittadinanza, lunghezza fra 1 e 64, formato caratteri alfabetici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cittadinanza">Cittadinanza</label>
                                        <input type="text" class="form-control" name="cittadinanza" id="cittadinanza"
                                               placeholder="Cittadinanza Tirocinante" size="64" minlength="1" maxlength="64" required>
                                    </div>
                                     <!-- Campo residenza, lunghezza fra 1 e 64, formato caratteri alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="residenza">Residenza</label>
                                        <input type="text" class="form-control" name="residenza" id="residenza"
                                               placeholder="Residenza Tirocinante" size="64" minlength="1" maxlength="64" required>
                                    </div>
                                     <!-- Campo Codice fiscale, lunghezza == 16, formato caratteri alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="codiceFiscale">Codice Fiscale</label>
                                        <input type="text" class="form-control" name="codiceFiscale" id="codiceFiscale"
                                               placeholder="Codice Fiscale Tirocinante" size="16" minlength="16" maxlength="16" required>
                                    </div>
                                     <!-- Campo Telefono, lunghezza == 10, formato caratteri numerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="telefono">Telefono</label>
                                        <input type="tel" class="form-control" name="telefono" id="telefono"
                                               placeholder="Telefono Tirocinante" size="10" minlength="10" maxlength="10" required>
                                    </div>
                                     <!-- Campo email, lunghezza fra 1 e 50 con @studenti.unisa.it, formato alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="email">E-mail</label>
                                        <input type="email" class="form-control" name="email" id="email"
                                               placeholder="E-mail Tirocinante" size="50" minlength="1" maxlength="50" required>
                                    </div>
                                     <!-- Campo CFU conseguiti, lunghezza fra 1 e 180, formato numerico-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cfu">CFU Conseguiti</label>
                                        <input type="tel" class="form-control" name="cfu" id="cfu"
                                               placeholder="CFU Conseguiti" size="3" minlength="1" maxlength="3" required>
                                    </div>
                                    <!-- Campo competenze possedute, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="competenzePossedute">Competenze Possedute</label>
                                        <input type="text" class="form-control" name="competenzePossedute" id="competenzePossedute"
                                               placeholder="Competenze Possedute" minlength="1" maxlength="256" required>
                                    </div>
                                    <!-- Campo competenze da acquisire, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="competenzeDaAcquisire">Competenze da Acquisire</label>
                                        <input type="text" class="form-control" name="competenzeDaAcquisire" id="competenzeDaAcquisire"
                                               placeholder="Competenze da Acquisire" minlength="1" maxlength="256" required>
                                    </div>
                                    <!-- Campo Modalita svolgimento tirocinio, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="modalitaTirocinio">Modalit&agrave; svolgimento Tirocinio</label>
                                        <input type="text" class="form-control" name="modalitaTirocinio" id="modalitaTirocinio"
                                               placeholder="Modalit&agrave; Tirocinio" minlength="1" maxlength="256" required>
                                    </div>
                                      <!-- Campo attivita previste, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="attivitaPreviste">Attivit&agrave; previste</label>
                                        <input type="text" class="form-control" name="attivitaPreviste" id="attivitaPreviste"
                                               placeholder="Attivit&agrave; previste" minlength="1" maxlength="256" required>
                                    </div>
                                    <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    	<!-- Bottone per resettare -->
                                    	<button type="reset" class="btn btn-primary btn-submit">Reset</button>
                                    	<!-- Bottone per inviare la richiesta -->
                                        <button type="submit" class="btn btn-primary btn-submit">Invio Richiesta</button>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/partials/footer.jsp" />
</div>

<!--End pagewrapper
<jsp:include page="/partials/includes.jsp" />-->

</body>
</html>
