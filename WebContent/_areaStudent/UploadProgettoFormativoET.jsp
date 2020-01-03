<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import=" model.Student, model.Tirocinio, controller.CheckSession"%>

<%
	String pageName = "IploadProgettoFomativoET.jsp";
	String pageFolder = "_areaStudent";
	
	//Per prelevare l'utente dalla sessione e precompilare i campi del form.
    Student user = (Student)request.getSession().getAttribute("user");
    //Per vedere chi e' in sessione.
    int resp = Integer.parseInt((String)request.getSession().getAttribute("userET"));
    
	
    Tirocinio tirocinio=new Tirocinio();
	tirocinio=(Tirocinio)request.getAttribute("tirocinio");

	if(tirocinio==null)
	{
        RequestDispatcher dispatcher = request.getRequestDispatcher("../ServletProgettoFormativoET");
        dispatcher.forward(request, response);
    }	 

%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js"></script>

	<div class="page-wrapper">

		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage uploadAttachedPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<div class="form-group">
									<button type="button"
										class="btn btn-primary btn-submit generatePDF"
										onclick="createPdf()">Genera PDF</button>
								</div>
								
								<!--  invio dei dati alla funzione "createPdf()" per -->
								<input type="hidden" id="name" value="<%=tirocinio.getTirocinante().getName() %>" />
								<input type="hidden" id="surname" value="<%= tirocinio.getTirocinante().getSurname() %>" />
								<input type="hidden" id="cod" value="<%= tirocinio.getCodTirocinio() %>" />
								<input type="hidden" id="denominazione" value="<%= tirocinio.getEnteConvenzionato().getName() %>" />
								<input type="hidden" id="sede" value="<%= tirocinio.getEnteConvenzionato().getSede() %>" />
								<input type="hidden" id="pec" value="<%= tirocinio.getEnteConvenzionato().getEmail() %>" />
								<input type="hidden" id="partitaIva" value="<%= tirocinio.getEnteConvenzionato().getPartitaIva() %>" />
								
								<input type="hidden" id="rappresentante" value="<%= tirocinio.getEnteConvenzionato().getRappresentante() %>" />
								<input type="hidden" id="dataNascita" value="<%= tirocinio.getEnteConvenzionato().getDataDiNascita() %>" />
								<input type="hidden" id="dipendenti" value="<%= tirocinio.getEnteConvenzionato().getDipendenti() %>" />
								
								<input type="hidden" id="dataNascitaTirocinante" value="<%= tirocinio.getTirocinante().getDataNascita() %>" />
								<input type="hidden" id="luogoNascitaTirocinante" value="<%= tirocinio.getTirocinante().getLuogoNascita() %>" />
								<input type="hidden" id="cittadinanza" value="<%= tirocinio.getTirocinante().getCittadinanza() %>" />
								<input type="hidden" id="residenza" value="<%= tirocinio.getTirocinante().getResidenza() %>" />
								
								<input type="hidden" id="codiceFiscaleTirocinante" value="<%= tirocinio.getTirocinante().getCodiceFiscale() %>" />
								<input type="hidden" id="telefonoTirocinante" value="<%= tirocinio.getTirocinante().getTelefono() %>" />
								<input type="hidden" id="emailTirocinante" value="<%= tirocinio.getTirocinante().getEmail() %>" />
								
								<input type="hidden" id="dotRiferimento" value="<%= tirocinio.getEnteConvenzionato().getDotRiferimento() %>" />
								<input type="hidden" id="referente" value="<%= tirocinio.getEnteConvenzionato().getReferente() %>" />
								
								<input type="hidden" id="cfu" value="<%= tirocinio.getCfuPrevisti() %>" />
							
		</div>
		<jsp:include page="/partials/footer.jsp" />
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />

	<script>
		function createPdf(){
			var data = new Date();
			var gg, mm, aaaa;
			gg = data.getDate() + "/";
			mm = data.getMonth() + 1 + "/";
			aaaa = data.getFullYear();
			
			var doc = new jsPDF();

			doc.setFont("Garamond");
			doc.setTextColor(50,60,181);
			doc.setFontStyle("bold");
			doc.setFontSize(14);
			doc.text("UNIVERSITA' DEGLI STUDI DI SALERNO ",105, 20, null, null,  'center');			
			doc.setFontSize(12);
			doc.text("DIPARTIMENTO DI INFORMATICA", 105, 30, null, null, 'center');			
			doc.setTextColor('black');
			doc.setFontSize(12);
			doc.text("PROGETTO FORMATIVO E DI ORIENTAMENTO", 105, 40, null, null, 'center' );
			doc.text('LAUREA TRIENNALE / LAUREA MAGISTRALE ', 105, 50, null, null, 'center' );
			doc.setFontSize(12);
			doc.text('Relativo alla Convenzione per tirocinio di formazione ed orientamento (curriculare) stipulata', 10, 60);
			doc.fromHTML('<b>con <em>' + $("#name").val()  + ' ' + $("#surname").val()  + '</em> in data  <em>' +  gg + mm + aaaa + '</em>, Repertorio N. <em>' + $("#cod").val() + '</em></b>', 9, 60);
			doc.text('SOGGETTO PROMOTORE', 10, 80);
	       	doc.setFontStyle('justify');
	       	doc.text("Dipartimento di Informatica dell' Universita' degli Studi di Salerno; ", 10, 87);
	        doc.text('Sede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno)', 10, 94);
		    doc.text('Indirizzo PEC ammicent@pec.unisa.it ', 10, 101);
		    doc.text('Codice Fiscale 80018670655 ', 10, 108);
	        doc.text('Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera ', 10, 115);
		    doc.text('Inferiore (SA) il 07/12/1960. ', 10, 122);	
	        
	        doc.setFontStyle("bold");
		    doc.text('SOGGETTO OSPITANTE ', 10, 135);
	        doc.setFontStyle("justify");
	        
	        doc.fromHTML('Denominazione  <em><b> ' + $("#denominazione").val() + ' </b></em> (specificare la natura giuridica) ', 9, 135);
	        doc.fromHTML('Sede legale in <em><b>' + $("#sede").val()  + '</b></em> ', 9, 142);
	        doc.fromHTML('Indirizzo PEC <em><b>' + $("#pec").val()  + '</b></em> ', 9, 149);
	        doc.fromHTML('Codice Fiscale e Partita IVA <em><b>' + $("#partitaIva").val()  + '</b></em> ', 9, 156);
			
	        doc.fromHTML("Rappresentante legale: <em><b> " + $("#rappresentante").val() + " </em></b>, in qualità di <em><b>Amministratore</em></b>, nato a ______________ ", 9, 163);
	        doc.fromHTML("il: <em><b> " + $("#dataNascita").val() + " </em></b> ", 9, 170);
	        doc.fromHTML("Attività economica esercitata _________________", 9, 177);
	        doc.fromHTML("Codice ATECO _________________ ", 9, 184);
	        doc.fromHTML("Numero Dipendenti a tempo indeterminato <em><b> " + $("#dipendenti").val() + " </em></b>", 9, 191);

		    doc.fromHTML('<b>TIROCIANTE</b> ', 9, 204);

	        doc.fromHTML('Cognome e nome del tirocinante;', 9, 211);
	        doc.fromHTML('Data e luogo di nascita <em><b>' + $("#dataNascitaTirocinante").val()  + ' ' + $("#luogoNascitaTirocinante").val()  + '</b></em> ', 9, 218);
	        doc.fromHTML('Cittadinanza <em><b>' + $("#cittadinanza").val()  + '</b></em> ', 9, 225);
	        doc.fromHTML('Residenza <em><b>' + $("#residenza").val()  + '</b></em> ', 9, 232);
	        
	        doc.addPage();
	        
	        doc.fromHTML('Codice Fiscale <em><b>' + $("#codiceFiscaleTirocinante").val()  + '</b></em>', 9, 20);
	        doc.fromHTML('Telefono n. <em><b>' + $("#telefonoTirocinante").val()  + ' </b></em> ', 9, 27);
	        doc.fromHTML('Indirizzo e-mail <em><b>' + $("#emailTirocinante").val()  + '</b></em> ', 9, 34);
	        doc.fromHTML('Iscritto al Corso di Laurea Triennale in Informatica  ', 9, 41);
	        
		    doc.fromHTML('<b>TUTOR DESIGNATO DAL DIPARTIMENTO:<em>'+ $("#dotRiferimento").val()+ '</em></b>', 9, 55);
		    
		    doc.fromHTML('<b>TUTOR DESIGNATO DAL SOGGETTO OSPITANTE: <em>'+ $("#referente").val() +'</em></b>' , 9, 69);
		    doc.fromHTML("Tel. __________________", 9, 76);
		    doc.fromHTML("Email _________________ ", 9, 83);
		    
		    doc.fromHTML("<b>N. TOTALE DI CREDITI FORMATIVI PREVISTI PER L' ATTIVITÀ DI TIROCINIO: <em>"+ $("#cfu").val() + "</em> di cui:</b>", 9, 97);
		    
		    doc.fromHTML("Email _________________ ", 9, 104);
		    
	        doc.save('a4.pdf')
		}
		</script>


	
	
</body>
</html>
