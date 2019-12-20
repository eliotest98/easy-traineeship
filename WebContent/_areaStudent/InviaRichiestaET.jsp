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

    <!-- Preloader -->
    <!-- <div class="preloader"></div>  -->


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
                                <form id="signUp" action="/ServletRichiestaInizioTirocinioET" method="post">
                                	<!-- Campo nome tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="nomeTirocinante">Nome</label>
                                        <input type="text" class="form-control" id="nomeTirocinante" name="nomeTirocinante"
                                               placeholder="Nome Tirocinante" minlength="1" maxlength="50" required>
                                    </div>
                                    <!-- Campo cognome tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cognomeTirocinante">Cognome</label>
                                        <input type="text" class="form-control" id="cognomeTirocinante" name="cognomeTirocinante"
                                               placeholder="Cognome Tirocinante" minlength="1" maxlength="50" required>
                                    </div>
                                    <!-- Campo matricola tirocinante, lunghezza 10 cifre, formato solo numeri. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="matricolaTirocinante">Matricola</label>
                                        <input type="number" class="form-control" id="matricolaTirocinante" name="matricolaTirocinante"
                                               placeholder="Matricola Tirocinante" size="10" required>
                                    </div>
                                    <!-- Campo facolta tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="facoltaTirocinante">Facolt&agrave;</label>
                                        <input type="text" class="form-control" id="facoltaTirocinante" name="facoltaTirocinante"
                                               placeholder="Facolta Tirocinante" minlength="1" maxlength="50" required>
                                    </div>
                                     <!-- Campo data di nascita, lunghezza fra 1 e 10, formato cifre + /. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="dataDiNascita">Data di Nascita</label>
                                        <input type="date" class="form-control"
                                               placeholder="Data di Nascita Tirocinante" name="dataDiNascita" id="dataDiNascita" required>
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
<!--End pagewrapper-->

<!--<jsp:include page="/partials/includes.jsp" />-->

</body>
</html>
