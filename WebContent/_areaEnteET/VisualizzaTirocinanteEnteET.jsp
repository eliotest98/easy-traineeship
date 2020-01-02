<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,controller.ServletVisualizzaTirocinanteEnteET, model.Tirocinante, model.Tirocinio, controller.CheckSession" %>
<%
	String pageName = "VisualizzaTirocinanteEnteET.jsp";
	String pageFolder = "_areaEnteET";

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	//Prelevo dalla sessione l' EnteConvenzionato
	String EnteConvenzionato=" ";
	EnteConvenzionato = (String) session.getAttribute("EnteConvenzionato");
	
	//Prelevo la matricola e istanzio un tirocinante
	long matricola = Long.valueOf(request.getParameter("matricola"));
	Tirocinante tirocinante=new Tirocinante();
	tirocinante=(Tirocinante)request.getAttribute("tirocinante");
	Tirocinio tirocinio=new Tirocinio();
	tirocinio = (Tirocinio)request.getAttribute("tirocinio");
	
	if(tirocinante==null)
	{
		request.setAttribute("matricola", matricola);
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("../ServletVisualizzaTirocinanteEnteET");
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
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 25%;
}

/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
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
								if(tirocinante!=null)
								{
									Date giorno = tirocinante.getDataNascita();
									String data = giorno.toString();
									String anno = data.substring(0,4);
									String mese = data.substring(4,8);
									String day = data.substring(8,10);
									String datanascita = day+mese+anno;
								%>
									<div class="panel">
										<h2 class="text-center">Informazioni del Tirocinante</h2>
									</div>
									<form id="signUp"  name="modificaEnte"  action="../ServletGestioneRichiesteEnteET"
										method="post">
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="matricola">Matricola</label> <input type="text"
												class="form-control" id="matricola" name="matricola"
												placeholder="Matricola" value="0<%=tirocinante.getMatricola()%>" minlength="1" maxlength="64"
												required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="name">Nome</label> <input type="text"
												class="form-control" placeholder="Name" value="<%=tirocinante.getName()%>"
												name="name" id="name" size="11" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="surname">Cognome</label> <input type="text"
												class="form-control" id="surname" name="surname"
												placeholder="Email" value="<%=tirocinante.getSurname()%>" minlength="3" maxlength="64" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dataDiNascita">Data di Nascita</label> <input type="text"
												class="form-control" id="dataDiNascita" name="dataDiNascita"
												placeholder="Data di Nascita" value="<%=datanascita%>" minlength="1" maxlength="64" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="luogoDiNascita">Luogo di Nascita</label> 
											<input	type="text" class="form-control" placeholder="Luogo di Nascita" value="<%=tirocinante.getLuogoNascita()%>"
												name="luogoDiNascita" id="luogoDiNascita" size="10" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="cittadinanza">Cittadinanza</label> <input
												type="text" class="form-control"
												placeholder="Cittadinanza" value="<%=tirocinante.getCittadinanza()%>" name="cittadinanza"
												id="cittadinanza" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="facolta">Facolt&agrave;</label> <input
												type="text" class="form-control" id="facolta"
												name="facolta" placeholder="Facoltà" value="Informatica"
												minlength="1" maxlength="64" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="sesso">Sesso</label> <input type="text" class="form-control"
												placeholder="Sesso" value="<%=tirocinante.getSex()%>" name="sesso"
												id="sesso" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="email">E-Mail</label>
											<input type="email" class="form-control" id="email"
												name="email" placeholder="email" value="<%=tirocinante.getEmail()%>" minlength="1"
												maxlength="64" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="residenza">Residenza</label> <input
												type="text" class="form-control"
												placeholder="residenza" value="<%=tirocinante.getResidenza()%>" minlength="1"
												maxlength="64" name="residenza" id="residenza" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="codiceFiscale">Codice Fiscale</label>
											<input type = "text" class="form-control"
												placeholder=" Codice Fiscale" value="<%=tirocinante.getCodiceFiscale()%>" minlength="1"
												maxlength="256" name="codiceFiscale"
												id="codiceFiscale" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="telefono">Numero di Telefono</label>
											<input type = "tel" class="form-control"
												placeholder="Numero di Telefono" value="<%=tirocinante.getTelefono()%>" minlength="1"
												maxlength="256" name="telefono"
												id="telefono" required disabled>
										</div>
										<!--  Campo CFU conseguiti prelevato da Tirocinio -->
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="cfu">CFU Conseguiti</label>
											<input type = "text" class="form-control"
												placeholder="CFU Conseguiti" value="<%=tirocinio.getCfuPrevisti()%>" minlength="1"
												maxlength="3" name="cfuConseguiti"
												id="cfu" required disabled>
										</div>
										<!-- Campo Competenze Possedute Prelevato da Tirocinio -->
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        	<label for="competenzePossedute">Competenze Possedute</label>
                                        	<input type="text" class="form-control" name="competenzePossedute" id="competenzePossedute"
                                               placeholder="Competenze Possedute" value="<%=tirocinio.getCompetenze()%>" maxlength="256" required disabled>
                                   		</div>
                                   		<!-- Campo competenze da acquisire Prelevato da Tirocinio-->
                                    	<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        	<label for="competenzeDaAcquisire">Competenze da Acquisire</label>
                                        	<input type="text" class="form-control" name="competenzeDaAcquisire" id="competenzeDaAcquisire"
                                               placeholder="Competenze da Acquisire" value="<%=tirocinio.getCompetenzeAcquisire()%>" maxlength="256" required disabled>
                                    	</div>
                                    	<!-- Campo Modalita svolgimento tirocinio, prelevato da Tirocinio-->
                                    	<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        	<label for="modalitaTirocinio">Modalit&agrave; svolgimento Tirocinio</label>
                                        	<input type="text" class="form-control" name="modalitaTirocinio" id="modalitaTirocinio" 
                                        	   placeholder="Svolgimento" value="<%=tirocinio.getSvolgimentoTirocinio()%>" maxlength="256" required disabled>
                                    	</div>
                                    	<!-- Campo attivita previste, prelevato da Tirocinio -->
	                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
	                                        <label for="attivitaPreviste">Attivit&agrave; previste</label>
	                                        <input type="text" class="form-control" name="attivitaPreviste" id="attivitaPreviste"
	                                         	placeholder=".." value="<%=tirocinio.getAttivitaPreviste() %>" maxlength="256" required disabled>
	                                    </div>
	                                    <!-- Campo Descrizione, prelevato da Tirocinio -->
	                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="descrizione">Descrizione</label>
											 <input	type="text" class="form-control" name="descrizione" id="descrizione"  minlength="1"
											 	placeholder="Descrizione" value="<%=tirocinio.getDescrizioneEnte()%>" maxlength="256" required disabled>
											</div>
										<!-- Tasti Accetta / Rifuta -->
										<div
											class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<button id="Accetta" type="submit" class="btn btn-primary btn-submit" title="Accetta" data-idrequest="35">Accetta</button>
											<button id="Rifiuta" type="submit" class="btn btn-primary btn-submit" title="Rifiuta" data-idrequest="35">Rifiuta</button>														
										</div>
										<div class="clearfix"></div>
									</form>
								<%}%>
								
								<!-- The Modal -->
								<div id="myModal" class="modal">
								
								  <!-- Modal content -->
								  <div class="modal-content">
								    <span class="close">&times;</span>
								    <p>Sei sicuro di voler accettare la richiesta di Tirocinio?</p>
								    <form id="signUp" action="../ServletGestioneRichiesteSegreteriaET" method="post">
											<button onclick="accetta()"id="Accetta" name="enteEmail"  value="niente per il momento" type="submit" class="btn btn-primary btn-action eliminaEnte refuse" style="background:#e73f43; border:#e73f43" data-type="2" data-idrequest="35" title="Accetta Richiesta">Si</button>
											<button onclick="notaccetta()"id="close" name="enteEmail"  type="submit" class="btn btn-primary btn-action eliminaEnte refuse" style="background:#e73f43; border:#e73f43" data-type="2" data-idrequest="35" title="Annulla">No</button>
									</form>
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
			jQuery(document).ready(function($){
				$('#TabellaEnteTable').DataTable( {
			        "order": [[ 0, "desc" ]],
			        "lengthMenu": [[10, -1], [10, "Tutti"]],
			        "autoWidth": false,
			        "bAutoWidth": false,
			        "language": {
						    "sEmptyTable":     "Nessuna Richiesta Presente",
						    "sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
						    "sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
						    "sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
						    "sInfoPostFix":    "",
						    "sInfoThousands":  ".",
						    "sLengthMenu":     "Visualizza _MENU_ elementi",
						    "sLoadingRecords": "Caricamento...",
						    "sProcessing":     "Elaborazione...",
						    "sSearch":         "Cerca:",
						    "sZeroRecords":    "La ricerca non ha portato alcun risultato.",
						    "oPaginate": {
						        "sFirst":      "Inizio",
						        "sPrevious":   '<i class="fa fa-caret-left"></i>',
						        "sNext":       '<i class="fa fa-caret-right"></i>',
						        "sLast":       "Fine"
						    },
						    "oAria": {
						        "sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
						        "sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
						    }

			        }        
			    } );
			});// Get the modal
			var modal = document.getElementById("myModal");

			// Get the button that opens the modal
			var btn = document.getElementById("Accetta");

			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];

			// When the user clicks the button, open the modal 
			btn.onclick = function() {
			  modal.style.display = "block";
			  if(btn == email)
				{	
					showAlert();
					return true;
				}
				else
				{
					showAlert();
					return false;
				}
			}

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
			  modal.style.display = "none";
			  toastr.error("Accettazione non effettuata");
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
			  if (event.target == modal) {
			    modal.style.display = "none";
			    toastr.error("Accettazione non effettuata");
			  }
			}
		</script>
		<script>
		function accetta()
		{
			toastr.success("Accettazione effettuata con successo");
		}
		</script>
		<script>
		function notaccetta()
		{
			toastr.error("Accettazione non effettuata");
		}
		</script>
</body>
</html>