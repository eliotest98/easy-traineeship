<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="java.util.*,controller.CheckSession,controller.ServletListaEnteET, model.EnteConvenzionato,model.Student,model.Tirocinio"%>

<%
	String pageName = "InviaRichiestaEnteET.jsp";
	String pageFolder = "_areaStudent";
	
	//Accede alla pagina solo se la richiesta di inizio tirocinio � stata accettata
	//Fare in modo da avere il tirocinio qui..
	//Per vedere chi � in sessione.
    int resp = Integer.parseInt((String)request.getSession().getAttribute("userET"));
	Student s = (Student) request.getSession().getAttribute("user");
	//Compilare il menu a tendina per gli enti
	ArrayList<EnteConvenzionato> listaEnti = new ArrayList<EnteConvenzionato>();
	listaEnti = (ArrayList<EnteConvenzionato>) request.getAttribute("listaEnti");
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
<link href="<%=request.getContextPath()%>/css/styleET.css"
	rel="stylesheet">
</head>

<body onLoad="">
	<div class="page-wrapper" id="inviaRichiestaEnte">

		<!-- Preloader -->
		<!-- <div class="preloader"></div>  -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>


<div class="sidebar-page-container basePage signUpPage">
        <div class="auto-container">
            <div class="row clearfix">
                <div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="content">
                        <div class="news-block-seven">
                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container">
                                <div class="panel">
									<h2 class="text-center">Invio Richiesta di Tirocinio
											all'Ente Convenzionato</h2>
									<p class="text-center">Compila tutti i campi per inviare
											la richiesta.</p>
								</div>
								<form id="SceltaEnte" action="ServletSceltaEnteET" method="post">
									<!--Controlli: lunghezza  fra 1 e 50, alfabetico-->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<label for="nome">Nome</label> 
										<input type="text"
												class="form-control" value="<%=s.getName() %>"
												disabled required>
										<input type="hidden"  id="nome" name="nome" value="<%=s.getName() %>">
									</div>
									<!--Controlli: lunghezza  fra 1 e 50, alfabetico-->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<label for="cognome">Cognome</label> 
										<input type="text"
												class="form-control" value="<%=s.getSurname() %>"
												disabled required>
										<input type="hidden"  id="cognome" name="cognome" value="<%=s.getSurname() %>">
									</div>
									<!--Controlli: lunghezza  fra 1 e 50, alfabetico-->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<label for="facolta">Facolt&agrave;</label> 
										<input type="text"
												class="form-control" value="Informatica"
												required disabled>
										<input type="hidden"  id="facolta" name="facolta" value="Informatica">
									</div>
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<br>
										<select name="ente" required>
										<option value="">Seleziona Ente</option>
										<%
												if (listaEnti != null) {
													//Scorro tutta la listaEnti
													for (int i = 0; i < listaEnti.size(); i++) {
										%>
										<option value="<%=listaEnti.get(i).getPartitaIva()%>"><%=listaEnti.get(i).getName()%></option>
										<%
													}
												}
													%>
										</select>
									</div>
									<!--Controlli: TUTTO, da 1 a 256-->
									<div class="form-group">
										<label for="descrizione" class="desc">Descrizione:</label><br>
										<textarea class="form-control textArea" 
												placeholder="Inserire la descrizione da inviare all'Ente"
												maxlength="256" name="descrizione" id="descrizione" required></textarea>
									</div><br>
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