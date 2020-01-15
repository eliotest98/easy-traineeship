<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,controller.ServletStatoTirocinioET, model.Tirocinante, model.Tirocinio, model.EnteConvenzionato, controller.CheckSession"%>
<%
	String pageName = "VisualizzaStatoTirocinioET.jsp";
	String pageFolder = "_areaSecretary";

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	//Controllo autenticazione tramite parametro in sessione (1 = Segreteria).
	String userET = (String) request.getSession().getAttribute("userET");
	if ((userET == null) || (!userET.equals("1"))) {
		response.sendRedirect("login.jsp");
		return;
	}
	//Prelevo la matricola e istanzio un tirocinante
	long matricola = Long.valueOf(request.getParameter("matricola"));
	Tirocinante tirocinante = new Tirocinante();
	tirocinante = (Tirocinante) request.getAttribute("tirocinante");
	Tirocinio tirocinio = new Tirocinio();
	tirocinio = (Tirocinio) request.getAttribute("tirocinio");
	EnteConvenzionato enteConvenzionato = new EnteConvenzionato();
	enteConvenzionato = (EnteConvenzionato) request.getAttribute("ente");
	if (tirocinante == null) {
		request.setAttribute("matricola", matricola);
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("../ServletStatoTirocinioET");
		dispatcher.forward(request, response);
	}
%>

<!DOCTYPE html>
<html>
<head>
<style>
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 25%;
  text-align:center;
}

/* The Close Button */
.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>
<jsp:include page="/partials/head.jsp" />
</head>
<body onLoad="showData()">
	<div class="page-wrapper">

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
								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container">
									<%
										//Se la listaEnti non è null mostro la tabella
										if (tirocinante != null && tirocinio != null) {
											//String format data di nascita
											Date giorno = tirocinante.getDataNascita();
											String data = giorno.toString();
											String anno = data.substring(0, 4);
											String mese = data.substring(4, 8);
											String day = data.substring(8, 10);
											String datanascita = day + mese + anno;
											//String forma data di inizio tirocinio
											String giorno2 = tirocinio.getDataInizioTirocinio();
											String anno2 = giorno2.substring(0, 4);
											String mese2 = giorno2.substring(4, 8);
											String day2 = giorno2.substring(8, 10);
											String datainiziotirocinio = day2 + mese2 + anno2;
									%>
									<div class="panel">
										<h2 class="text-center">Informazioni del Tirocinante</h2>
									</div>
									<!-- Campo Matricola -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Matricola </strong>
										<%
											out.println("<br>" + "0" + tirocinante.getMatricola());
										%>
									</div>
									<!-- Campo Facolta -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Facolt&agrave; </strong>
										<%
											out.println("<br>" + "Informatica");
										%>
									</div>
									<!-- Campo Nome -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Nome </strong>
										<%
											out.println("<br>" + tirocinante.getName());
										%>
									</div>
									<!--  Campo Cognome -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Cognome </strong>
										<%
											out.println("<br>" + tirocinante.getSurname());
										%>
									</div>
									<!-- Campo data di nascita -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Data di nascita </strong>
										<%
											out.println("<br>" + datanascita);
										%>
									</div>
									<!--  Campo luogo di nascita -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Luogo di Nascita </strong>
										<%
											out.println("<br>" + tirocinante.getLuogoNascita());
										%>
									</div>
									<!--  Campo Cittadinanza -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Cittadinanza </strong>
										<%
											out.println("<br>" + tirocinante.getCittadinanza());
										%>
									</div>
									<!--  Campo Sesso -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Sesso </strong>
										<%
											out.println("<br>" + tirocinante.getSex());
										%>
									</div>
									<!-- Campo E-mail -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>E-Mail </strong>
										<%
											out.println("<br>" + tirocinante.getEmail());
										%>
									</div>
									<!--  Campo Residenza -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Residenza </strong>
										<%
											out.println("<br>" + tirocinante.getResidenza());
										%>
									</div>
									<!--  Campo Codice Fiscale -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Codice Fiscale </strong>
										<%
											out.println("<br>" + tirocinante.getCodiceFiscale());
										%>
									</div>
									<!--  Campo Numero di Telefono -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Numero di Telefono </strong>
										<%
											out.println("<br>" + tirocinante.getTelefono());
										%>
									</div>
									<!--  Campo CFU -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>CFU Conseguiti </strong>
										<%
											out.println("<br>" + tirocinio.getCfuPrevisti());
										%>
									</div>
									<%
										if (tirocinio.getPartitaIva() != null) {
									%>
									<!-- Campo Nome Ente -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Ente Ospitante </strong>
										<%
											out.println("<br>" + enteConvenzionato.getName());
										%>
									</div>
									<%
										}
									%>

									<!-- Campo Competenze Possedute -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Competenze Possedute </strong>
										<%
											out.println("<br>" + tirocinio.getCompetenze());
										%>
									</div>
									<!-- Campo Competenze da Acquisire -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Competenze da Acquisire </strong>
										<%
											out.println("<br>" + tirocinio.getCompetenze());
										%>
									</div>
									<!--  Campo modalità svolgimento tirocinio -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Modalit&agrave; svolgimento Tirocinio </strong>
										<%
											out.println("<br>" + tirocinio.getSvolgimentoTirocinio());
										%>
									</div>
									<!--  Campo attività previste -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Attivit&agrave; previste </strong>
										<%
											out.println("<br>" + tirocinio.getAttivitaPreviste());
										%>
									</div>
									<!-- Campo Descrizione -->
									<% if (tirocinio.getPartitaIva() != null){ %>
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Descrizione </strong>
										<%
											out.println("<br>" + tirocinio.getDescrizioneEnte());
										%>
									</div>
									<%} %>
									<!-- Campo Stato del tirocinio -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Stato del Tirocinio </strong>
										<%
											out.println("<br>" + tirocinio.getStatoTirocinio());
										%>
									</div>
									<!--  Campo Data Inizio Tirocinio -->
									<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<strong>Data Inizio Tirocinio </strong>
										<%
											out.println("<br>" + datainiziotirocinio);
										%>
									</div>
									<!-- Campo Codice Tirocinio -->
									<div>
									<input type="hidden" class="form-control" id="codice" name="codice" value="<%=tirocinio.getCodTirocinio()%>" required>
									</div>
									<%if (!tirocinio.getStatoTirocinio().equals("Completo")){ %>
									<!-- Tasto Annulla -->
									<div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<button id="Annulla" type="submit"
											class="btn btn-primary btn-submit" title="Annulla"
											data-idrequest="35">Annulla Tirocinio</button>
									</div>
									<%} %>
									<div class="clearfix"></div>

									<%
										} else if (tirocinio == null) {
									%>
									<%
										tirocinante = (Tirocinante) request.getAttribute("tirocinante");
									%>
									<div>
										<h3 style="text-align: center;">
											Il Tirocinante
											<%
											out.println(tirocinante.getName() + " " + tirocinante.getSurname() + " con Matricola n. : 0"
														+ tirocinante.getMatricola());
										%>
											<br> non possiede alcuna richiesta di tirocinio attiva.
											<br> Clicca <a
												href="../_areaSecretary/VisualizzaListaTirocinantiET.jsp">
												qui </a> per tornare alla pagina precedente. <br>
										</h3>
									</div>
									<%
										}
									%>
									<!-- Modal Annullamento -->
									<div id="myModalAnnullamento" class="modal">

										<!-- Modal content -->
										<div class="modal-content">
											<span class="close"></span>
											<p>Sei sicuro di voler annullare il Tirocinio?</p>
											<center><table>
												<tr>
													<td><% request.setAttribute("matricola", matricola);%>
													<button onclick="return annulla()" id="modalAnnullaButton"
																name="flag" value="<%=matricola%>" type="submit"
																class="btn btn-primary btn-action eliminaEnte refuse"
																data-type="2" data-idrequest="35"
																title="Annulla Richiesta">Si</button>
														</td>
													<td><button onclick="notaccetta()" id="close"
															name="nonAccetta"
															class="btn btn-primary btn-action eliminaEnte refuse"
															data-type="2" data-idrequest="35" title="Annulla">No</button></td>
												</tr>
											</table></center>

										</div>
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
	<script>
		//script 'DataTable' di Bootstrap' per la gestione della 'Tabella'
		jQuery(document)
				.ready(
						function($) {
							$('#TabellaEnteTable')
									.DataTable(
											{
												"order" : [ [ 0, "desc" ] ],
												"lengthMenu" : [ [ 10, -1 ],
														[ 10, "Tutti" ] ],
												"autoWidth" : false,
												"bAutoWidth" : false,
												"language" : {
													"sEmptyTable" : "Nessuna Richiesta Presente",
													"sInfo" : "Vista da _START_ a _END_ di _TOTAL_ elementi",
													"sInfoEmpty" : "Vista da 0 a 0 di 0 elementi",
													"sInfoFiltered" : "(filtrati da _MAX_ elementi totali)",
													"sInfoPostFix" : "",
													"sInfoThousands" : ".",
													"sLengthMenu" : "Visualizza _MENU_ elementi",
													"sLoadingRecords" : "Caricamento...",
													"sProcessing" : "Elaborazione...",
													"sSearch" : "Cerca:",
													"sZeroRecords" : "La ricerca non ha portato alcun risultato.",
													"oPaginate" : {
														"sFirst" : "Inizio",
														"sPrevious" : '<i class="fa fa-caret-left"></i>',
														"sNext" : '<i class="fa fa-caret-right"></i>',
														"sLast" : "Fine"
													},
													"oAria" : {
														"sSortAscending" : ": attiva per ordinare la colonna in ordine crescente",
														"sSortDescending" : ": attiva per ordinare la colonna in ordine decrescente"
													}

												}
											});
						});
		// Get the modal
		var modalAnnullamento = document.getElementById("myModalAnnullamento");
		// Get the button that opens the modal
		var btnAnnullamento = document.getElementById("Annulla");
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];

		// When the user clicks the button, open the modal 
		btnAnnullamento.onclick = function() {
		modalAnnullamento.style.display = "block";
			/* if(btn == email)
			{	
				showAlert();
				return true;
			}
			else
			{
				showAlert();
				return false;
			}*/
		}

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modalAnnullamento.style.display = "none";
			showAlert();
			toastr.error("Operazione non effettuata");
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modalAnnullamento) {
				modalAnnullamento.style.display = "none";
				showAlert();
				toastr.error("Annullamento non effettuato");
			}
		}
	</script>
	<script>
		function annulla() {
			var matricola = document.getElementById("modalAnnullaButton").value;
			var cod = document.getElementById("codice").value;
			$.ajax({
					type : "POST",
					url : absolutePath + "/ServletAnnullaTirocinioET",
					async : true,
					data : { "matricola" : matricola, "cod" : cod
					},
					success : function(resp) {
						console.log(resp)
						if (resp) {
							showAlert();
							toastr
									.success("Annullamento effettuato con successo");
							modalAnnullamento.style.display = "none";
							setTimeout(
									function() {// wait for 5 secs(2)
										window.location
												.replace(absolutePath
														+ "/_areaSecretary/VisualizzaListaTirocinantiET.jsp"); // then reload the page.(3)
									}, 1000);
						} else {
							showAlert();
							toastr.success("Annullamento non effettuato");
							modalAnnullamento.style.display = "none";
							setTimeout(
									function() {// wait for 5 secs(2)
										location.replace = "VisualizzaStatoTirocinioET.jsp"; // then reload the page.(3)
									}, 3000);
							}
						}
					});
		}
	</script>
	<script>
		function notaccetta() {
			modalAnnullamento.style.display = "none";
			showAlert();
			toastr.error("Annullamento non effettuato");
		}
	</script>
</body>
</html>