<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession,model.Student, model.Tirocinante, model.Tirocinio"%>
<%@ page import="java.util.*,model.Request"%>
<%
	String pageName = "HomeStudente.jsp";
	String pageFolder = "_areaStudent";

	//Per prelevare l'utente dalla sessione.
	Student user = (Student) request.getSession().getAttribute("user");
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
		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->
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
								<div class="bordiET">
									<h2 class="centro">
										BENVENUTO NELLA TUA AREA UTENTE
										<%=user.getName().toUpperCase()%>!
									</h2>
									<div class="pf">
										<img alt="Omino" class="imgET" src="../imagesEV/omino.png">
									</div>
									<div class="pf centro mgR">
										<h4>Dove vuoi navigare?</h4>
										<br> <a href="../_areaStudent/viewRequest.jsp"
											class="linkET"><button class="buttonET">Richieste
												effettuate per la convalida dei CFU</button></a><br>
										<br> <a href="" class="linkET"><button
												class="buttonET" disabled>Richieste effettuate per
												il Tirocinio Esterno</button></a><br>
										<br> <a href="../ServletStatoTirocinanteET"
											class="linkET"><button class="buttonET">Controlla
												lo stato del TUO Tirocinio!</button></a><br>
										<br>
									</div>
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
</body>
</html>
