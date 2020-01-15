<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%
	/*
	Progetto coordinato GPS/IS anno accademico 2019/2020.
	
	Il progetto prevede l'implementazione di un gestore di tirocini esterno 
	all'interno del sistema esistente "English Validation".
	Il sistema risultante avra', quindi, come obiettivi:
		-Fornire uno strumento per supportare e promuovere l'attivia' di tirocinio,
		assicurando che tutti gli stakeholders coinvolti interagiscono in modo semplice 
		ed efficiente;
		-Creare un sistema accessibile agli studenti del Dipartimento di Informatica, 
		alla segreteria studenti ed al Presidente del Consiglio Didattico;
		Permettere allo studente di reperire informazioni sulle aziende, 
		e far successivamente richiesta di disponibilita', in modo veloce ed immediato;
		Ottimizzare l'attuale iter burocratico di firma, da parte degli stakeholders,
		dei documenti necessari al tirocinio ("Progetto Formativo") dando la possibilità
		di caricare i suddetti documenti direttamente sulla piattaforma;
		La verifica da parte della Commissione Riconoscimenti degli estremi identificativi 
		delle assicurazioni per la responsabilita' civile, il numero di CFU che dovrebbero 
		essere riconosciuti, e la completezza delle firme necessarie per l'approvazione.
	
	Teams:
		Alessandro Serritiello
		Elio Testa
		Simona Grilletto
		Giuseppe Barisano
		Luigi Barbato
		Pellegrino Aurilia
		Salvatore Amendola
		Felice Montella
	
	Project managers:
		Vincenzo Belmonte
		Vincenzo De Rosa.
	
	Customer/Sponsor:
		Dipartimento di Informatica dell'Università degli studi di Salerno.
		Presidente del Consiglio Didattico di Informatica Prof.ssa Filomena Ferrucci.
		
	Documentazione:
		- C2_RAD_Vers.1.6.pdf
		- C2_Checklist_RAD_Vers.0.2.xlsx
		- C2_AD_SistemaProposto.pdf
		- C2_AD_SistemaCorrente.pdf
		- C2_SDD_Vers.1.0.pdf
		- C2_Checklis_SDD_Vers.0.3.xlsx
		- C2_ODD_Vers.1.0.pdf
		- C2_ClassDiagram_ODD_Vers.1.0.png
	
	Documentazione di Testing:
		- C2_ITP_Vers_1.0.pdf
		- Diagramma dipendenze CON NUMERI v2.png
		- C2_TCS_Vers.1.4.pdf
		- C2_TER_Vers.1.0.pdf
		- C2_TIR_Vers.1.0.pdf
		- ET_TP_Vers.1.0.pdf
		- C2_UTR_Vers.1.0.pdf
		
	Distro 1.0
		Prima release della piattaforma "Easy-Traineeship". 
		Per maggiorni informazioni sull' istallazione consultare il "manuale di installazione"
		
		Prima di installare: 
			-Scaricare e installare JDK 8
			-Scaricare il server "apache Tomcat" v9.0
			-Clone/Download della repository
			-Inserire il file "Easy-Traineeship.war" in C:\apache-tomcat-9.0\webapps
			
		Preparazione ed installazione della base di dati 
			-Scaricare e installare MySQL Server/Workbench
			-Durante l' istallazione inserire le credenziali: 
				a. Nome Utente: root 
				b. Password: password 
				c. hostPort: 3306 
				d. hostName: localhost
			-Importare/Lanciare "C2_IstanziaDB_ET_v1.0.sql" in MySQL Server/Workbench.
			
		Installazione 
		
			-Avviare il Server "apache Tomcat".
			-Aprire il Web Browser e recarsi al link del progetto.
			-Dopo aver concluso le operazioni desiderate sulla 7
			piattaforma stoppare il "apachetomcat".
			

	*/
%>
<%
	String pageName = "index.jsp";
	String pageFolder = "";
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body onLoad="">
	<div class="page-wrapper">

		<!-- Preloader -->
		<!-- <div class="preloader"></div>  -->

		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>


		<div class="sidebar-page-container basePage indexPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 index-container">
									<div class="panel">
										<h2 class="text-center">Benvenuto</h2>
										<p></p>
									</div>
									<a href=".\login.jsp" class="btn btn-primary btn-lg btn-block"
										role="button" aria-pressed="true">Accedi</a>
									<p></p>
									<a href="_areaStudent\signUp.jsp"
										class="btn btn-primary btn-lg btn-block" role="button"
										aria-pressed="true">Registrati</a>
									<p></p>
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

	<jsp:include page="/partials/includes.jsp" />
	<script src="<%=request.getContextPath()%>/js/pages/scripts_login.js"></script>

</body>
</html>