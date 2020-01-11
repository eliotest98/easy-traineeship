<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,controller.ServletVisualizzaTirocinanteET, model.Tirocinante, model.Tirocinio, controller.CheckSession" %>
<%
	String pageName = "VisualizzaTirocinanteET.jsp";
	String pageFolder = "_areaSecretary";

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	//Prelevo dalla sessione l' EnteConvenzionato
	String EnteConvenzionato=" ";
	EnteConvenzionato = (String) session.getAttribute("EnteConvenzionato");
	
	Tirocinante tirocinante=new Tirocinante();
	tirocinante=(Tirocinante)request.getSession().getAttribute("tirocinante");
	Tirocinio tirocinio=new Tirocinio();
	tirocinio = (Tirocinio)request.getSession().getAttribute("tirocinio");
	
	//Prelevo la matricola e istanzio un tirocinante
	long matricola = 0;
	if (request.getParameter("matricola")==null) {
		matricola = tirocinante.getMatricola();
	}
	else {
		matricola = Long.valueOf(request.getParameter("matricola"));
	}
	
	

	
	
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
									//String format data di nascita
									Date giorno = tirocinante.getDataNascita();
									String data = giorno.toString();
									String anno = data.substring(0,4);
									String mese = data.substring(4,8);
									String day = data.substring(8,10);
									String datanascita = day+mese+anno;
									//String forma data di inizio tirocinio
									String giorno2 = tirocinio.getDataInizioTirocinio();
									String anno2 = giorno2.substring(0,4);
									String mese2 = giorno2.substring(4,8);
									String day2 = giorno2.substring(8,10);
									String datainiziotirocinio = day2+mese2+anno2;
								%>
									<div class="panel">
										<h2 class="text-center">Informazioni del Tirocinante</h2>
									</div>
									
								<!-- 	<form id="signUp"  name="modificaEnte"  action="../ServletGestioneRichiesteEnteET"
										method="post"> -->
										<!-- Campo Matricola -->
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Matricola </strong>  <% out.println( "<br>" + "0" +tirocinante.getMatricola()); %>
									    </div>
									    <!-- Campo Facolta -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Facolt&agrave; </strong>  <% out.println( "<br>"+ "Informatica"); %>
									    </div>
									    <!-- Campo Nome -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Nome </strong>  <% out.println( "<br>"+ tirocinante.getName()); %>
									    </div>
									    <!--  Campo Cognome -->
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Cognome </strong>  <% out.println( "<br>"+ tirocinante.getSurname()); %>
									    </div>
									    <!-- Campo data di nascita -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Data di nascita </strong>  <% out.println( "<br>"+ datanascita); %>
									    </div>
									    <!--  Campo luogo di nascita -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Luogo di Nascita </strong>  <% out.println( "<br>"+ tirocinante.getLuogoNascita()); %>
									    </div>
									    <!--  Campo Cittadinanza -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Cittadinanza </strong>  <% out.println( "<br>"+ tirocinante.getCittadinanza()); %>
									    </div>
									    <!--  Campo Sesso -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Sesso </strong>  <% out.println( "<br>"+ tirocinante.getSex()); %>
									    </div>
									    <!-- Campo E-mail -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>E-Mail </strong>  <% out.println( "<br>"+ tirocinante.getEmail()); %>
									    </div>
									    <!--  Campo Residenza -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Residenza </strong>  <% out.println( "<br>"+ tirocinante.getResidenza()); %>
									    </div>
									    <!--  Campo Codice Fiscale -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Codice Fiscale </strong>  <% out.println( "<br>"+ tirocinante.getCodiceFiscale()); %>
									    </div>
									    <!--  Campo Numero di Telefono -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Numero di Telefono </strong>  <% out.println( "<br>"+ tirocinante.getTelefono()); %>
									    </div>
									    <!--  Campo CFU -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>CFU Conseguiti </strong>  <% out.println( "<br>"+ tirocinio.getCfuPrevisti()); %>
									    </div>
									    <!--  Campo Data Inizio Tirocinio -->
									    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
									    	<strong>Data Inizio Tirocinio </strong>  <% out.println( "<br>"+ datainiziotirocinio); %>
									    </div>
										<!-- Tasti Accetta / Rifuta -->
										<div
											class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<button id="Accetta" type="submit" class="btn btn-primary btn-submit" title="Accetta" data-idrequest="35">Accetta</button>
											<button id="Rifiuta" type="submit" class="btn btn-primary btn-submit" title="Rifiuta" data-idrequest="35">Rifiuta</button>														
										</div>
										<div class="clearfix"></div>
								<!-- 	</form> -->
								<%}%>
								
								<!-- Modal Accettazione -->
								<div id="myModalAccettazione" class="modal">
								
								  <!-- Modal content -->
								  <div class="modal-content">
								    <span class="close"></span>
								    <p>Sei sicuro di voler accettare la richiesta di Tirocinio?</p>
								  		<table>
								  		<tr><td>
								  		  <%request.setAttribute("matricola", matricola);%>
								  		  <button onclick="return accetta()" id="accetta"  value="<%=matricola %>" class="btn btn-primary btn-action eliminaEnte refuse" data-type="2" data-idrequest="35" title="Accetta Richiesta">Si</button> </td>
										 <td><button onclick="notaccetta()"id="close" name="nonAccetta" class="btn btn-primary btn-action eliminaEnte refuse" data-type="2" data-idrequest="35" title="Annulla">No</button></td></tr>
										</table>
										
									</div>
								</div>
								
								<!-- The Modal Rifiuto -->
								<div id="myModalRifiuto" class="modal">
								
								 <!-- Modal content -->
								 <div class="modal-content">
								    <span class="close"></span>
								    <p>Sei sicuro di voler rifiutare la richiesta di Tirocinio?</p>
											<label for="nome">Inserisci Motivazione</label> 
											<input type="text" class="form-control" id="motivazione" name="motivazione" placeholder="Motivazione del Rifiuto" minlength="1" maxlength="256"  required pattern="[ 0-9a-zA-Z/./,/?]{1,256}">
											<button onclick="return rifiuta()"id="rifiuta"  value="<%=matricola %>" class="btn btn-primary btn-action eliminaEnte refuse"  data-type="2" data-idrequest="35" title="Rifiuta Richiesta">Si</button>
											<button onclick="notrifiuta()"id="close" name="nonRifiuta"  class="btn btn-primary btn-action eliminaEnte refuse"  data-type="2" data-idrequest="35" title="Annulla">No</button>
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
			}); 
			// Get the modal
			var modalAccettazione = document.getElementById("myModalAccettazione");
			var modalRifiuto = document.getElementById("myModalRifiuto");
			// Get the button that opens the modal
			var btnAccettazione = document.getElementById("Accetta");
			var btnRifiuto = document.getElementById("Rifiuta");
			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];
		
			// When the user clicks the button, open the modal 
			btnAccettazione.onclick = function() {
			  modalAccettazione.style.display = "block";
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
			//Quando l'utente clicca sul tasto2 apri il modal
			btnRifiuto.onclick = function() {
			  modalRifiuto.style.display = "block";
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
			  modalAccettazione.style.display = "none";
			  toastr.error("Operazione non effettuata");
			}
			
			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
			  if (event.target == modalAccettazione) {
			    modalAccettazione.style.display = "none";
			    toastr.error("Accettazione non effettuata");
			  }
			  if (event.target == modalRifiuto) {
			    modalRifiuto.style.display = "none";
			    toastr.error("Rifiuto non effettuato");
			}

		}
		</script>
		<script>
		function accetta()
		{
		var matricola = document.getElementById("accetta").value;
	
			$.ajax({
				  type: "POST",
				  url: absolutePath+ "/ServletGestioneRichiesteSegreteriaET",
				  async:true,
				  data: {"matricola": matricola, "flag": 2},
				  success: function(resp){
					  console.log(resp)
					  if(resp){
							showAlert();
							toastr.success("Accettazione effettuata con successo");
						    modalAccettazione.style.display = "none";
						      setTimeout(function(){// wait for 5 secs(2)
						           window.location.replace(absolutePath+"/_areaSecretary/VisualizzaRichiestaET.jsp"); // then reload the page.(3)
						      }, 1000); 
				  }
					  else{
							showAlert();
							toastr.success("Accettazione non effettuata");
						    modalAccettazione.style.display = "none";
						     setTimeout(function(){// wait for 5 secs(2)
						           location.replace="VisualizzaTirocinanteET.jsp"; // then reload the page.(3)
						      }, 3000); 
						     }
				  }
				});
		}
		</script>
		<script>
		function notaccetta()
		{
			modalAccettazione.style.display = "none";
			toastr.error("Rifiuto non effettuato");
			
		}
		</script>
		<script>
		function rifiuta()
		{
			var matricola = document.getElementById("rifiuta").value;
			var motivazione = document.getElementById("motivazione").value;
			console.log(rifiuta)
			if(motivazione!=null && motivazione!="") {
				$.ajax({
					  type: "POST",
					  url: absolutePath+ "/ServletGestioneRichiesteSegreteriaET",
					  async:true,
					  data: {"matricola": matricola, "motivazione": motivazione, "flag": 3},
					  success: function(resp){
						  console.log(resp)
						  if(resp){
						showAlert();
						toastr.success("Rifiuto effettuato con successo");
					    modalRifiuto.style.display = "none";
					     setTimeout(function(){// wait for 5 secs(2)
					    	 window.location.replace(absolutePath+"/_areaSecretary/VisualizzaRichiestaET.jsp"); // then reload the page.(3)
					      }, 3000); 
					     }
						  else{
								showAlert();
								toastr.success("Rifiuto non effettuato");
							    modalRifiuto.style.display = "none";
							     setTimeout(function(){// wait for 5 secs(2)
							           location.reload(); // then reload the page.(3)
							      }, 3000); 
							     }
					  }
					});
			} else {
				toastr.error("Inserisci la motivazione del rifiuto");
			}
		}		
		</script>
		<script>
		function notrifiuta()
		{
			modalRifiuto.style.display = "none";
			toastr.error("Rifiuto non effettuato");
			
		}
		</script>
</body>
</html>