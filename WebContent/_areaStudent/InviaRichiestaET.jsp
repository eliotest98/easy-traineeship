<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" import="controller.CheckSession,model.Tirocinante"%>

<%
    String pageName = "InviaRichiestaET.jsp";
    String pageFolder = "_areaStudent";
    
    //Per prelevare l'utente (Tirocinanate) dalla sessione e precompilare i campi del form.
    Tirocinante user = new Tirocinante();
    //user = (Tirocinante) request.getSession().getAttribute("user");
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
                                    <h2 class="text-center">Invio Richiesta Tirocinio:</h2>
                                    <p class="text-center">Compilare i campi per l'invio della richiesta di inizio Tirocinio.</p>
                                </div>
                                <!-- Form per l'invio della richiesta. -->
                                <form id="invioRichiestaTirocinio" action="/ServletRichiestaInizioTirocinioET" method="post">
                                	<!-- Campo nome tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="nomeTirocinante">Nome</label>
                                        <input type="text" class="form-control" id="nomeTirocinante" name="nomeTirocinante" value="<%=user.getName()%>"
                                               size="50" min="1" maxlength="50" required disabled>
                                    </div>
                                    <!-- Campo cognome tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cognomeTirocinante">Cognome</label>
                                        <input type="text" class="form-control" id="cognomeTirocinante" name="cognomeTirocinante" value="<%=user.getSurname()%>"
                                               size="50" min="1" maxlength="50" required disabled>
                                    </div>
                                    <!-- Campo matricola tirocinante, lunghezza 10 cifre, formato solo numeri. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="matricolaTirocinante">Matricola</label>
                                        <input type="tel" class="form-control" id="matricolaTirocinante" name="matricolaTirocinante" value="051210"
                                               placeholder="0512105239" size="10" min="10" maxlength="10" required>
                                    </div>
                                    <!-- Campo facolta tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="facoltaTirocinante">Facolt&agrave;</label>
                                        <input type="text" class="form-control" id="facoltaTirocinante" name="facoltaTirocinante" value="Informatica"
                                               size="50" min="1" maxlength="50" required disabled>
                                    </div>
                                     <!-- Campo data di nascita, lunghezza ==10, formato cifre + /. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="dataDiNascita">Data di Nascita</label>
                                        <input type="date" class="form-control" name="dataDiNascita" id="dataDiNascita"
                                               size="10" min="10" maxlength="10" required>
                                    </div>
                                    <!-- Campo luogo di nascita, lunghezza fra 1 e 64, formato caratteri alfabetici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="luogoDiNascita">Luogo di Nascita</label>
                                        <input type="text" class="form-control" name="luogoDiNascita" id="luogoDiNascita" 
                                               placeholder="Napoli" size="64" min="1" maxlength="64" required>
                                    </div>
                                    <!-- Campo cittadinanza, lunghezza fra 1 e 64, formato caratteri alfabetici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cittadinanza">Cittadinanza</label>
                                        <input type="text" class="form-control" name="cittadinanza" id="cittadinanza"
                                               placeholder="Italiana" size="64" min="1" maxlength="64" required>
                                    </div>
                                     <!-- Campo residenza, lunghezza fra 1 e 64, formato caratteri alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="residenza">Residenza</label>
                                        <input type="text" class="form-control" name="residenza" id="residenza"
                                               placeholder="Via Per Nola 49" size="64" min="1" maxlength="64" required>
                                    </div>
                                     <!-- Campo Codice fiscale, lunghezza == 16, formato caratteri alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="codiceFiscale">Codice Fiscale</label>
                                        <input type="text" class="form-control" name="codiceFiscale" id="codiceFiscale"
                                               placeholder="GRLSMN98R64F924S" size="16" min="16" maxlength="16" required>
                                    </div>
                                     <!-- Campo Telefono, lunghezza == 10, formato caratteri numerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="telefono">Telefono</label>
                                        <input type="tel" class="form-control" name="telefono" id="telefono"
                                               placeholder="3469486823" size="10" min="10" maxlength="10" required>
                                    </div>
                                     <!-- Campo email, lunghezza fra 1 e 50 con @studenti.unisa.it, formato alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="email">E-mail</label>
                                        <input type="email" class="form-control" name="email" id="email" value="<%=user.getEmail()%>"
                                               size="50" min="1" maxlength="50" required disabled>
                                    </div>
                                     <!-- Campo CFU conseguiti, lunghezza fra 1 e 180, formato numerico-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cfu">CFU Conseguiti</label>
                                        <input type="tel" class="form-control" name="cfu" id="cfu"
                                               placeholder="175" size="3" min="1" maxlength="3" required>
                                    </div>
                                    <!-- Campo competenze possedute, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="competenzePossedute">Competenze Possedute</label>
                                        <input type="text" class="form-control" name="competenzePossedute" id="competenzePossedute"
                                               placeholder="Java,C++.." min="1" maxlength="256" required>
                                    </div>
                                    <!-- Campo competenze da acquisire, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="competenzeDaAcquisire">Competenze da Acquisire</label>
                                        <input type="text" class="form-control" name="competenzeDaAcquisire" id="competenzeDaAcquisire"
                                               placeholder="Python" min="1" maxlength="256" required>
                                    </div>
                                    <!-- Campo Modalita svolgimento tirocinio, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="modalitaTirocinio">Modalit&agrave; svolgimento Tirocinio</label>
                                        <input type="text" class="form-control" name="modalitaTirocinio" id="modalitaTirocinio" value="Esterno"
                                               min="1" maxlength="256" required disabled>
                                    </div>
                                      <!-- Campo attivita previste, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="attivitaPreviste">Attivit&agrave; previste</label>
                                        <input type="text" class="form-control" name="attivitaPreviste" id="attivitaPreviste"
                                               placeholder="Svolgimento, in sede..." min="1" maxlength="256" required>
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
