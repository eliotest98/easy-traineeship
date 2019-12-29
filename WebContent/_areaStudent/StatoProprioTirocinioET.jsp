<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,controller.ServletListaEnteET, model.Student, model.Tirocinio, model.Tirocinante, controller.CheckSession"%>
<%
	String pageName = "StatoProprioTirocinioET.jsp";
	String pageFolder = "_areaStudent";
/*
    //Per prelevare l'utente dalla sessione e precompilare i campi.
    Student user = (Student)request.getSession().getAttribute("user");
    Tirocinante tirocinante = (Tirocinante)request.getSession().getAttribute("Tirocinante");
    //Per completare i campi di tirocinio
    Tirocinio t = (Tirocinio)request.getSession().getAttribute("ProgettoFormativo");
    //Per vedere chi è in sessione.
    int resp = Integer.parseInt((String)request.getSession().getAttribute("userET"));*/
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>
<body onLoad="showData()">
	<div class="page-wrapper">

		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>

		<div class="sidebar-page-container basePage viewRequestStudent">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<!--controllo sessione-->
								<div style="border: 2px solid orange; font-size: 18px; margin: 3%; border-radius: 10px;">
									<h2 style="text-align: center;">Progetto Formativo:</h2>
									<div style="display: inline-block; margin: 5%;">
										<span>Nome: SIMONA</span><br> 
										<span>Matricola: 0512105239</span><br>
										<span>Crediti previsti: 23</span><br>
										<span>Indicazione delle attivit&agrave; formative previste: **blablabla**</span><br>
									</div>
									<div style="display: inline-block; margin: 5%;">
										<span>Cognome: GRILLETTO</span><br>
										<span>Facolt&agrave;: Informatica</span><br>
										<span>E-mail: s.grilletto@studenti.unisa.it</span><br>
										<span>Indicazione delle modalit&agrave; di svolgimento del Tirocinio:</span>
									</div>
									<button onclick="mostraStato()">Mostra STATO TIROCINIO</button>
									<p id="qui"></p>
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
	<script type="text/javascript">
	
	</script>
</body>
</html>