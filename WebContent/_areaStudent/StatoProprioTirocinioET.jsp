<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,controller.ServletListaEnteET, model.Student, model.Tirocinio, model.Tirocinante, controller.CheckSession"%>
<%
	String pageName = "StatoProprioTirocinioET.jsp";
	String pageFolder = "_areaStudent";

	//Per prelevare l'utente dalla sessione e precompilare i campi.
	Student user = (Student) request.getSession().getAttribute("user");
	Tirocinante tirocinante = (Tirocinante) request.getSession().getAttribute("Tirocinante");
	//Per completare i campi di tirocinio
	ArrayList<Tirocinio> lista = (ArrayList<Tirocinio>) request.getSession().getAttribute("Tirocinio");
	//Per vedere chi è in sessione.
	int resp = Integer.parseInt((String) request.getSession().getAttribute("userET"));
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
<link href="<%=request.getContextPath()%>/css/styleET.css"
	rel="stylesheet">
</head>
<body onLoad="showData()">
	<div class="page-wrapper">

		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>

		<div class="sidebar-page-container basePage viewRequestStudent">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<!--controllo sessione-->
								<%
									if (resp == 0) {
								%>
								<div class="bordiET">
									<%
										if (lista!= null && tirocinante != null) {
									%>
									<h2 class="centro">PROGETTO FORMATIVO:</h2>
									<div class="pf">
										<span>Nome: <%=tirocinante.getName()%></span><br> <span>Matricola:
											<%=tirocinante.getMatricola()%></span><br> <span>Crediti
											previsti: <%=lista.get(0).getCfuPrevisti()%></span><br> <span>Indicazione
											delle attivit&agrave; formative previste:<br> <%=lista.get(0).getAttivitaPreviste()%></span>
									</div>
									<div class="pf">
										<span>Cognome: <%=tirocinante.getSurname()%></span><br>
										<span>Facolt&agrave;: <%=tirocinante.getFacolta()%></span><br>
										<span>E-mail: <%=tirocinante.getEmail()%></span><br> <span>Indicazione
											delle modalit&agrave; di svolgimento del Tirocinio:<br>
											<%=lista.get(0).getSvolgimentoTirocinio()%></span><br>
									</div>
									<div class="centro">
										<button onclick="mostraStato()" class="buttonET">
											<p id="cambia">MOSTRA STATO TIROCINIO</p>
										</button>
										<p id="qui" style="display: none;"><%=lista.get(0).getStatoTirocinio()%></p>
									</div>
									<br>
									<%
										}
											if (lista == null || tirocinante == null) {
									%>
									<h2 class="centro">
										OPS..
										<%=user.getName()%>
										<%=user.getSurname()%>, Non hai ancora compilato la
										richiesta di inizio Tirocinio?
									</h2>
									<h3 class="centro">
										Clicca <a href="easy-traineeship/WebContent/_areaStudent/InviaRichiestaET.jsp">qui!</a>
									</h3>
									<%
										}
									%>
								</div>
								<%
									}
								%>
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
		var mostrato = false;

		function mostraStato() {
			if (mostrato == false) {
				document.getElementById("qui").style.display = "block";
				document.getElementById("cambia").innerHTML = "NASCONDI STATO TIROCINIO";
				mostrato = true;
			} else {
				document.getElementById("qui").style.display = "none";
				document.getElementById("cambia").innerHTML = "MOSTRA STATO TIROCINIO";
				mostrato = false;
			}
		}
	</script>
</body>
</html>