<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" import="controller.CheckSession,model.Student, model.Tirocinio"%>

<%
    String pageName = "InviaRichiestaET.jsp";
    String pageFolder = "_areaStudent";
    
    //Per prelevare l'utente dalla sessione e precompilare i campi del form.
    Student user = (Student)request.getSession().getAttribute("user");
    //Per vedere chi è in sessione.
    int resp = Integer.parseInt((String)request.getSession().getAttribute("userET"));
    
%>
<!-- Pagina per l'invio della richiesta di inizio Tirocinio, dallo studente alla segreteria. -->

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/partials/head.jsp" />
    <style type="text/css">
    	/*Non togliete, esterno non funziona*/
	    .toast 
	    {
	    	opacity: 0,8 !important;
		}
    </style>
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
                                	<%
                                		Tirocinio tirocinio = (Tirocinio)request.getSession().getAttribute("Tirocinio");
                                		if(resp==0 && tirocinio==null){
                                	%>
                                    <h2 class="text-center">Invio Richiesta Tirocinio:</h2>
                                    <p class="text-center">Compilare i campi per l'invio della richiesta di inizio Tirocinio.</p>
                                </div>
                                <!-- Form per l'invio della richiesta. 
                                	 NB: javascrip è relativo, essendo state inserite le espressioni regolari
                                	 all'interno dell'input type, nell'attributo PATTERN. -->
                                <form action="../ServletRichiestaInizioTirocinioET" method="post" 
                                	  id="invioRichiestaTirocinio" onsubmit="return check()">
                                	<!-- Campo nome tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="nomeTirocinante">Nome</label>
                                        <input type="text" class="form-control" id="nomeTirocinante" name="nomeTirocinante" 
                                        	   value="<%=user.getName()%>" 
                                        	    title="Il Sistema ha rilevato il nome del Tirocinante."
                                        	    required disabled>
                                    </div>
                                    <!-- Campo cognome tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cognomeTirocinante">Cognome</label>
                                        <input type="text" class="form-control" id="cognomeTirocinante" name="cognomeTirocinante" 
                                        	   value="<%=user.getSurname()%>" 
                                        	   title="Il Sistema ha rilevato il cognome del Tirocinante."
                                        	   required disabled>
                                    </div>
                                    <!-- Campo matricola tirocinante, lunghezza 10 cifre, formato solo numeri. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="matricolaTirocinante">Matricola</label>
                                        <input type="text" class="form-control" id="matricolaTirocinante" name="matricolaTirocinante" 
                                        	   value="051210" placeholder="0512105239" size="10" maxlength="10"
                                        	   title="La lunghezza della matricola deve essere necessariamente 10 cifre. Essendo il Sistema esteso al Dipartimento di Informatica, si accettano Matricole con inizio: 051210." 
                                        	   required pattern="[0-9]{10}">
                                    </div>
                                    <!-- Campo facolta tirocinante, lunghezza fra 1 e 50, formato solo lettere. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="facoltaTirocinante">Facolt&agrave;</label>
                                        <input type="text" class="form-control" id="facoltaTirocinante" name="facoltaTirocinante" 
                                        	   title="Il Sistema &agrave; esteso solamente alla Facolt&agrave; di Informatica."
                                        	   value="Informatica" required disabled>
                                    </div>
                                     <!-- Campo data di nascita, lunghezza ==10, formato cifre + /.
                                     	  Ho usato date, quindi non saprei come fare i controlli.
                                     	  Min e max, servono per far sì che non si sfori quelle date. -->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="dataDiNascita">Data di Nascita</label>
                                        <input type="date" class="form-control" name="dataDiNascita" id="dataDiNascita"
                                        	   title="La Data di nascita del Tirocinante deve essere valida."
                                               min="1919-12-31" max="2000-12-31" required>
                                    </div>
                                    <!-- Campo luogo di nascita, lunghezza fra 1 e 64, formato caratteri alfabetici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="luogoDiNascita">Luogo di Nascita</label>
                                        <input type="text" class="form-control" name="luogoDiNascita" id="luogoDiNascita" 
                                               title="Il Luogo di nascita deve contenere almeno 1 carattere e massimo 64."
                                               placeholder="Napoli" size="64" maxlength="64" 
                                               required pattern="[A-Z a-z]{1,64}">
                                    </div>
                                    <!-- Campo cittadinanza, lunghezza fra 1 e 64, formato caratteri alfabetici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cittadinanza">Cittadinanza</label>
                                        <input type="text" class="form-control" name="cittadinanza" id="cittadinanza"
                                               title="La Cittadinanza deve contenere almeno 1 carattere e massimo 64."
                                               placeholder="Italiana" size="64" maxlength="64" 
                                               required pattern="[A-Z a-z]{1,64}">
                                    </div>
                                     <!-- Campo residenza, lunghezza fra 1 e 64, formato caratteri alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="residenza">Residenza</label>
                                        <input type="text" class="form-control" name="residenza" id="residenza"
                                               title="La Residenza deve contenere almeno 1 carattere e massimo 64."
                                               placeholder="Via Per Nola 49" size="64" maxlength="64" 
                                               required pattern="[A-Z a-z 0-9]{1,64}">
                                    </div>
                                     <!-- Campo Codice fiscale, lunghezza == 16, formato caratteri alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="codiceFiscale">Codice Fiscale</label>
                                        <input type="text" class="form-control" name="codiceFiscale" id="codiceFiscale"
                                               title="Il Codice Fiscale deve essere esattamente di 16 caratteri, fra maiuscole e numeri."
                                               placeholder="GRLSMN98R64F924S" size="16" maxlength="16" 
                                               required pattern="[A-Z0-9]{16}">
                                    </div>
                                     <!-- Campo Telefono, lunghezza == 10, formato caratteri numerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="telefono">Telefono</label>
                                        <input type="text" class="form-control" name="telefono" id="telefono"
                                               title="Il numero di Telefono deve essere esattamente 10 cifre."
                                               placeholder="3469486823" size="10" maxlength="10" 
                                               required pattern="[0-9]{10}">
                                    </div>
                                     <!-- Campo email, lunghezza fra 1 e 50 con @studenti.unisa.it, formato alfanumerici-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="email">E-mail</label>
                                        <input type="email" class="form-control" name="email" id="email"
                                        	   title="Il Sistema ha rilevato l'E-mail del Tirocinante."
                                       		   value="<%=user.getEmail()%>" required disabled>
                                    </div>
                                     <!-- Campo CFU conseguiti, lunghezza fra 1 e 180, formato numerico-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cfu">CFU Conseguiti</label>
                                        <input type="number" class="form-control" name="cfu" id="cfu"
                                               title="Il numero di CFU conseguiti deve essere compreso fra 1 e 180."
                                               placeholder="175" min="1" max="180" maxlength="3" 
                                               required pattern="[0-9]{1,3}">
                                    </div>
                                    <!-- Campo competenze possedute, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="competenzePossedute">Competenze Possedute</label>
                                        <input type="text" class="form-control" name="competenzePossedute" id="competenzePossedute"
                                               title="Il campo deve contenere almeno un carattere e massimo 256."
                                               placeholder="Java,C++.." maxlength="256" required pattern="{1,256}">
                                    </div>
                                    <!-- Campo competenze da acquisire, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="competenzeDaAcquisire">Competenze da Acquisire</label>
                                        <input type="text" class="form-control" name="competenzeDaAcquisire" id="competenzeDaAcquisire"
                                               title="Il campo deve contenere almeno un carattere e massimo 256."
                                               placeholder="Python" maxlength="256" required pattern="{1,256}">
                                    </div>
                                    <!-- Campo Modalita svolgimento tirocinio, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="modalitaTirocinio">Modalit&agrave; svolgimento Tirocinio</label>
                                        <input type="text" class="form-control" name="modalitaTirocinio" id="modalitaTirocinio" 
                                        	   title="Il campo deve contenere almeno un carattere e massimo 256."
                                        	   placeholder="Svolgimento, in sede..." maxlength="256" required pattern="{1,256}">
                                    </div>
                                      <!-- Campo attivita previste, lunghezza fra 1 e 256-->
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="attivitaPreviste">Attivit&agrave; previste</label>
                                        <input type="text" class="form-control" name="attivitaPreviste" id="attivitaPreviste"
                                               title="Il campo deve contenere almeno un carattere e massimo 256."
                                               placeholder="Svolgimento, in sede..." maxlength="256" required pattern="{1,256}">
                                    </div>
                                    <div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    	<!-- Bottone per resettare -->
                                    	<button type="reset" class="btn btn-primary btn-submit">
                                    		Reset
                                    	</button>
                                    	<!-- Bottone per inviare la richiesta -->
                                        <button type="submit" class="btn btn-primary btn-submit">
                                        	Invio Richiesta
                                        </button>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                                  <%
                                  	}
                                	//	
                                	else if (resp!=0) 
                                	{
                                	%>
									<h3 style="text-align: center;">
										L'Utente in Sessione non &egrave; abilitato a svolgere questa funzionalit&agrave;.<br>
										Si prega di uscire dalla pagina. <br>
										Clicca <a href="../index.jsp"> qui </a> per tornare alla Home. <br>
									</h3>
								 <%
								 }
                                	else if (tirocinio!=null)
                                	{
                                 %>		
                                		<h3 style="text-align: center;">
										Impossibile accedere alla pagina di richiesta tirocinio: <br>
										&egrave; possibile richiedere un solo tirocinio per volta.<br>
										Si prega di uscire dalla pagina. <br>
										Clicca <a href="../_areaStudent/HomeStudente.jsp"> qui </a> per tornare alla Home. <br>
									</h3>
								 <%		
                                	}
								 %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/partials/footer.jsp" />
</div>
<script>
	/*Controllo se la matricola inizia con 051210, informatica*/
	function checkMatricola()
	{
		 var matricola = document.getElementById("matricolaTirocinante").value;
		 var mat = matricola.substring(0, 6);
		 /*vede se matcha*/
		 if(mat == "051210")
		 {
			 console.log("si");
			 showAlert();
			 toastr.success("Matricola corretta.");
			 return true;
		 }
		 else
		 {
			 showAlert();
			 toastr.warning("Matricola non rilevata.");
			 return false;
		 }
	}
	/*Funzione che consente la sottomissione dei campi alla richiesta.*/
	function check()
	{
		if(checkMatricola())
		{	
			showAlert();
			toastr.success("Invio richiesta Tirocinio completato con successo.");
			return true;
		}
		else
		{
			showAlert();
			toastr.error("Invio non riuscito.");
			return false;
		}
	}
</script>
<!--End pagewrapper
<jsp:include page="/partials/includes.jsp" />-->
</body>
</html>
