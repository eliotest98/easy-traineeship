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
								
								<input type="hidden" id="sede" value="<%= tirocinio.getEnteConvenzionato().getSede() %>" />
								
								<input type="hidden" id="dataInizioTirocinio" value="<%= tirocinio.getDataInizioTirocinio() %>" />
								
								<input type="hidden" id="competenze" value="<%= tirocinio.getCompetenzeAcquisire() %>" />
								<input type="hidden" id="attivitaPreviste" value="<%= tirocinio.getAttivitaPreviste() %>" />
								<input type="hidden" id="svolgimentoTirocinio" value="<%= tirocinio.getSvolgimentoTirocinio() %>" />
								
							
		</div>
		<jsp:include page="/partials/footer.jsp" />
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />

		<script src="<%= request.getContextPath() %>/js/progettoFormativo.js"></script>


	
	
</body>
</html>
