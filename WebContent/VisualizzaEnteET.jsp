<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,controller.ServletListaEnteET, model.EnteConvenzionato, controller.CheckSession" %>
<%
	String email="";
	String pageName = "VisualizzaEnteET.jsp";
	String pageFolder = "";

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	//Prelevo dalla sessione la Segreteria
	String Segreteria=" ";
	Segreteria = (String) session.getAttribute("Segreteria");
	
	
	ArrayList<EnteConvenzionato> listaEnti=new ArrayList<EnteConvenzionato>();
	listaEnti=(ArrayList<EnteConvenzionato>)request.getAttribute("listaEnti");
	
	//controllo se la lista è vuota
	if(listaEnti==null)
	{
        RequestDispatcher dispatcher = request.getRequestDispatcher("ServletListaEnteET");
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
  text-align: center;
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
		
		<div class="sidebar-page-container basePage viewRequestStudent">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<%
								//Se la listaEnti non è null mostro la tabella
								if(listaEnti!=null)
								{
								%>
									<table id="TabellaEnteTable" class="display data-results table table-striped table-hover table-bordered">
	
										<thead>
											<tr align="center">
												<th class="text-center" align="center">Nome Ente</th>
												<th class="text-center" align="center">Partita Iva</th>
												<th class="text-center" align="center">Sede</th>
												<th class="text-center" align="center">Dipendenti</th>
												<th class="text-center" align="center">Attivit&agrave;</th>
												<th class="text-center" align="center">Referente</th>
												<th class="text-center" align="center">E-Mail</th>
												<th class="text-center" align="center">Telefono</th>
												<%
												//Se è in sessione la segreteria mostro le azioni
												if(Segreteria!=null)
												{
													%>
													<th class="text-center" align="center">Azioni</th>
													<%
												}
												%>
												
											</tr>
										</thead>
										<tbody  >
											<%
											//Scorro tutta la listaEnti
											for( int i = 0; i < listaEnti.size(); i++)
											{ %>
												<tr role='row' >
													<td class='text-center'><%=listaEnti.get(i).getName()%></td>
													<td class='text-center'><%=listaEnti.get(i).getPartitaIva()%></td>
													<td class='text-center'><%=listaEnti.get(i).getSede()%></td>
													<td class='text-center'><%=listaEnti.get(i).getDipendenti()%></td>
													<td class='text-center'><%=listaEnti.get(i).getDescrizioneAttivita()%></td>
													<td class='text-center'><%=listaEnti.get(i).getReferente()%></td>
													<td class='text-center'><%=listaEnti.get(i).getEmail()%></td>
													<td class='text-center'><%=listaEnti.get(i).getTelefono()%></td>
													<%
													//Se è in sessione la segreteria mostro le azioni
													if(Segreteria!=null)
													{
														%>
														<td class="text-center" align="center">
														<a href='_areaSecretary/ModificaEnteET.jsp?ente=<%=i%>' class="btn btn-primary btn-action modificaEnte" title="Modifica Ente" data-idrequest="35"><i class="fa fa-edit"></i></a>
														<button id="<%=listaEnti.get(i).getEmail()%>"  name="enteEmail" class="btn btn-primary btn-action eliminaEnte refuse" style="background:#e73f43; border:#e73f43" data-type="2" data-idrequest="35" title="Elimina Ente"><i class="fa fa-times"></i></button>												
													</td>
										
														<%
													}
													%>
												</tr>
											<%
											} %>
										</tbody>
									</table>
								<%}%>
								
								<!-- The Modal -->
								<div id="myModal" class="modal">
								
								  <!-- Modal content -->
								  <div class="modal-content">
								    <span class="close"></span>
								    <p>Sei sicuro di voler eliminare l'Ente?</p>
											<button onclick="return elimina()" id="email"  style=" width:30px;background-color:#FF9900;outline:none;border:4px solid #FF9900;border-radius:5px; color:white; margin:2%;" data-type="2" data-idrequest="35" title="Elimina Ente">Si</button>
											<button onclick="notelimina()"id="close"   type="submit" style=" width:30px;background-color:#FF9900;outline:none;border:4px solid #FF9900;border-radius:5px; color:white; margin:2%;" data-type="2" data-idrequest="35" title="Annulla">No</button>
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
	<%
		//CODICE PER IL TOASTR DI SUCCESSO
		if (request.getParameter("cod")!=null) {
			if (request.getParameter("cod").equals("1")) {
		%>
			<script>
			showAlert();
			toastr.success("Email inviata all' Ente");
			showAlert();
			toastr.success("Ente registrato con successo.");
			</script>
		<%
			}
			else if (request.getParameter("cod").equals("2")) {
		%>
			<script>
			
			showAlert();
			toastr.error("Errore nella registrazione dell'Ente.");
			</script>
		<%
			} else if (request.getParameter("cod").equals("3")) {
				%>
				<script>
				showAlert();
				toastr.success("Modifica effettuata con successo.");
				</script>
			<%
				}
		}	
	%>
	
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
			var modal = document.getElementById("myModal");

			// Get the button that opens the modal
			
			var email = "";
			
			$('button[name ="enteEmail"]').click(function() {
				modal.style.display = "block";
				var btn =	$(this).attr("id");
				email = $(this).attr("id");
				console.log(email)
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
			  toastr.error("Eliminazione non effettuata");
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
			  if (event.target == modal) {
			    modal.style.display = "none";
			    showAlert();
			    toastr.error("Eliminazione non effettuata");
			  }
			}
			
			function elimina()
			{
				console.log("Sto eliminando " + email);
				$.ajax({
					  type: "POST",
					  url: absolutePath+ "/ServletEliminaEnteET",
					  async:true,
					  data: {"enteEmail": email},
					  success: function(resp){
						  console.log(resp)
						  if(resp){
						showAlert();
						toastr.success("Eliminazione effettuata con successo");
					    modal.style.display = "none";
					    setTimeout(function(){// wait for 5 secs(2)
					           location.reload(); // then reload the page.(3)
					      }, 1000);
						return true;
					  }
						  else{
								showAlert();
								toastr.success("Eliminazione non riuscita");
							    modal.style.display = "none";
								return false;
						  }
							  
					  }
					});
			}
			</script>
			<script>
			//funzione che visualizza l'eliminazione non effettuata
			function notelimina()
			{
				modal.style.display = "none";
				showAlert();
				toastr.error("Eliminazione non effettuata");
			}
		</script>
		<script>
		
		</script>
</body>
</html>