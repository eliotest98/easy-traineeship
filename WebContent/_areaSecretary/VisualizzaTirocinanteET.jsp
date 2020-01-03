<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,controller.ServletListaEnteET, model.Tirocinante, model.Tirocinio, controller.CheckSession" %>
<%
	String pageName = "VisualizzaTirocinanteET.jsp";
	String pageFolder = "_areaSecretary";

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	//Prelevo dalla sessione la Segreteria
	String Segreteria=" ";
	Segreteria = (String) session.getAttribute("Segreteria");
	
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
        dispatcher = request.getRequestDispatcher("../ServletVisualizzaTirocinanteET");
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
									<form id="signUp"  name="modificaEnte"  action="../ServletModificaEnteET"
										method="post">
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="name">Matricola</label> <input type="text"
												class="form-control" id="name" name="name"
												placeholder="Nome Ente" value="0<%=tirocinante.getMatricola()%>" minlength="1" maxlength="64"
												required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="partitaIva">Nome</label> <input type="tel"
												oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
												class="form-control" placeholder="Partita IVA" value="<%=tirocinante.getName()%>"
												name="partitaIva" id="partitaIva" size="11" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="email">Cognome</label> <input type="email"
												class="form-control" id="email" name="email"
												placeholder="Email" value="<%=tirocinante.getSurname()%>" minlength="3" maxlength="64" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="sede">Data di Nascita</label> <input type="text"
												class="form-control" id="sede" name="sede"
												placeholder="Sede" value="<%=datanascita%>" minlength="1" maxlength="64" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="telefono">Luogo di Nascita</label> <input
												type="tel"
												oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
												class="form-control" placeholder="Numero di Telefono" value="<%=tirocinante.getLuogoNascita()%>"
												name="telefono" id="telefono" size="10" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dipendenti">Cittadinanza</label> <input
												type="text" class="form-control"
												placeholder="Numero di Dipendenti" value="<%=tirocinante.getCittadinanza()%>" name="dipendenti"
												id="dipendenti" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="rappresentante">Facoltà</label> <input
												type="text" class="form-control" id="rappresentante"
												name="rappresentante" placeholder="Nome Rappresentante" value="Informatica"
												minlength="1" maxlength="64" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dataDiNascita">Sesso</label> <input type="text" class="form-control"
												placeholder="Data di Nascita" value="<%=tirocinante.getSex()%>" name="dataDiNascita"
												id="dataDiNascita" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dotRiferimento">E-Mail</label>
											<input type="text" class="form-control" id="dotRiferimento"
												name="dotRiferimento"
												placeholder="Professore di Riferimento" value="<%=tirocinante.getEmail()%>" minlength="1"
												maxlength="64" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="referente">Residenza</label> <input
												type="text" class="form-control"
												placeholder="Referente Tirocini" value="<%=tirocinante.getResidenza()%>" minlength="1"
												maxlength="64" name="referente" id="referente" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="descrizioneAttivita">Codice Fiscale</label>
											<input type = "text" class="form-control"
												placeholder="Descrizione delle Attivit&agrave;" value="<%=tirocinante.getCodiceFiscale()%>" minlength="1"
												maxlength="256" name="descrizioneAttivita"
												id="descrizioneAttivita" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="descrizioneAttivita">Numero di Telefono</label>
											<input type = "text" class="form-control"
												placeholder="Descrizione delle Attivit&agrave;" value="<%=tirocinante.getTelefono()%>" minlength="1"
												maxlength="256" name="descrizioneAttivita"
												id="descrizioneAttivita" required disabled>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="cfu">CFU Conseguiti</label>
											<input type = "text" class="form-control"
												placeholder="CFU Conseguiti" value="<%=tirocinio.getCfuPrevisti()%>" minlength="1"
												maxlength="3" name="cfuConseguiti"
												id="cfu" required disabled>
										</div>
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
											<textarea class="form-control"  rows="3"
												placeholder="Motivazione del Rifiuto;" name="motivazioneRifiuto"
												id="motivazioneRifiuto" required></textarea>
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