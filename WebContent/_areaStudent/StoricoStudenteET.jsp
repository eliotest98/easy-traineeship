<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,controller.ServletListaEnteET, model.*, controller.CheckSession"%>
<%
	String email="";
	String pageName = "StoricoStudenteET.jsp";
	String pageFolder = "_areaStudent";

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	//Prelevo dalla sessione lo studente ed il tirocinante
	Student user = (Student) request.getSession().getAttribute("user");
	Tirocinante tirocinante = (Tirocinante) request.getSession().getAttribute("Tirocinante");
	//Per vedere chi è in sessione.
	int resp = Integer.parseInt((String) request.getSession().getAttribute("userET"));
	
	ArrayList<Tirocinio> listaTirocini = (ArrayList<Tirocinio>) request.getSession().getAttribute("listaTirocini");
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
	text-align: center;
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
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>

		<div class="sidebar-page-container basePage viewRequestStudent">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<%
								//Se non è lo studente in sessione
								if(resp == 0)
								{
								%>
								<table id="TabellaEnteTable"
									class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr align="center">
											<th class="text-center" align="center">Codice Tirocinio:</th>
											<th class="text-center" align="center">Stato:</th>
											<th class="text-center" align="center">Motivazione:</th>
											<th class="text-center" align="center">Competenze
												Possedute:</th>
											<th class="text-center" align="center">Competenze da
												acquisire:</th>
											<th class="text-center" align="center">Attivit&agrave;
												previste:</th>
											<th class="text-center" align="center">Modalit&agrave;
												di svolgimento:</th>
										</tr>
									</thead>
									<tbody>
										<%
							                //Se la listaEnti non è null mostro la tabella
							                if(listaTirocini!=null)
							                {
												//Scorro tutta la listaEnti
												for( int i = 0; i < listaTirocini.size(); i++)
												{ %>
										<tr role='row'>
											<td class='text-center'><%=listaTirocini.get(i).getCodTirocinio()%></td>
											<td class='text-center'><%=listaTirocini.get(i).getStatoTirocinio()%>
												<%if(listaTirocini.get(i).getStatoTirocinio().equalsIgnoreCase("In attesa Ente"))
														{%>
												<button id="<%=listaTirocini.get(i).getCodTirocinio()%>"
													name="enteEmail"
													style="background-color: #FF9900; outline: none; border: 4px solid #FF9900; border-radius: 5px; color: white; margin: 2%;"
													data-type="2" data-idrequest="35" title="Annulla Richiesta">Annulla
													Richiesta</button> <%}%></td>
											<%if(listaTirocini.get(i).getStatoTirocinio().equalsIgnoreCase("Rifiutato")||listaTirocini.get(i).getStatoTirocinio().equalsIgnoreCase("Annullato"))
														{%>
											<td class='text-center'><%=listaTirocini.get(i).getDescrizioneEnte()%></td>
											<%} else {%>
											<td class='text-center'>Non presente.</td>
											<%}%>
											<td class='text-center'><%=listaTirocini.get(i).getCompetenze()%></td>
											<td class='text-center'><%=listaTirocini.get(i).getCompetenzeAcquisire()%></td>
											<td class='text-center'><%=listaTirocini.get(i).getAttivitaPreviste()%></td>
											<td class='text-center'><%=listaTirocini.get(i).getSvolgimentoTirocinio()%></td>
										</tr>
										<%} %>
									</tbody>
								</table>
								<%} }%>
							</div>
							<!-- The Modal -->
							<div id="myModal" class="modal">

								<!-- Modal content -->
								<div class="modal-content">
									<span class="close"></span>
									<p>Sei sicuro di voler annulla la richiesta?</p>
									<button onclick="return elimina()" id="email"
										style="width: 30px; background-color: #FF9900; outline: none; border: 4px solid #FF9900; border-radius: 5px; color: white; margin: 2%;"
										data-type="2" data-idrequest="35" title="Elimina Ente">Si</button>
									<button onclick="notelimina()" id="close" type="submit"
										style="width: 30px; background-color: #FF9900; outline: none; border: 4px solid #FF9900; border-radius: 5px; color: white; margin: 2%;"
										data-type="2" data-idrequest="35" title="Annulla">No</button>
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
		var modal = document.getElementById("myModal");

		// Get the button that opens the modal

		var email = "";

		$('button[name ="enteEmail"]').click(function() {
			modal.style.display = "block";
			var btn = $(this).attr("id");
			email = $(this).attr("id");
			console.log(email)
			if (btn == email) {
				showAlert();
				return true;
			} else {
				showAlert();
				return false;
			}
		});

		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close");

		// When the user clicks the button, open the modal 
		//btn[i].onclick = function() {

		//}

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
			showAlert();
			toastr.error("Annullamento non effettuato");
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
				showAlert();
				toastr.error("Annullamento non effettuato");
			}
		}

		function elimina() {
			console.log("Sto eliminando " + email);
			$.ajax({
				type : "POST",
				url : absolutePath + "/ServletAnnullaEnteDaStudenteET",
				async : true,
				data : {
					"enteEmail" : email
				},
				success : function(resp) {
					console.log(resp)
					if (resp) {
						showAlert();
						toastr.success("Annullamento effettuato con successo");
						modal.style.display = "none";
						setTimeout(function() {// wait for 5 secs(2)
							location.reload(); // then reload the page.(3)
						}, 1000);
						return true;
					} else {
						showAlert();
						toastr.error("Annullamento non effettuato");
						modal.style.display = "none";
						return false;
					}

				}
			});
		}
	</script>
	<script>
		function notelimina() {
			modal.style.display = "none";
			showAlert();
			toastr.error("Annullamento non effettuato");
		}
	</script>
</body>
</html>