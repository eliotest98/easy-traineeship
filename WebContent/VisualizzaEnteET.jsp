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
														email = listaEnti.get(i).getEmail();
														%>
														
														<td class="text-center" align="center">
															<a href='_areaSecretary/ModificaEnteET.jsp?ente=<%=i%>' class="btn btn-primary btn-action modificaEnte" title="Modifica Ente" data-idrequest="35"><i class="fa fa-edit"></i></a>
															<button id="email" name="enteEmail"  type="submit" class="btn btn-primary btn-action eliminaEnte refuse" style="background:#e73f43; border:#e73f43" data-type="2" data-idrequest="35" title="Elimina Ente"><i class="fa fa-times"></i></button>												
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
								    <span class="close">&times;</span>
								    <p>Sei sicuro di voler eliminare l'Ente?</p>
								    <form id="signUp" action="./ServletEliminaEnteET" method="post">
											<button onclick="elimina()"id="email" name="enteEmail"  value=<%=email %> type="submit" class="btn btn-primary btn-action eliminaEnte refuse" style="background:#e73f43; border:#e73f43" data-type="2" data-idrequest="35" title="Elimina Ente">Si</button>
											<button onclick="notelimina()"id="close" name="enteEmail"  type="submit" class="btn btn-primary btn-action eliminaEnte refuse" style="background:#e73f43; border:#e73f43" data-type="2" data-idrequest="35" title="Elimina Ente">No</button>
									</form>
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
			var modal = document.getElementById("myModal");

			// Get the button that opens the modal
			var btn = document.getElementById("email");

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
			  toastr.error("Eliminazione non effettuata");
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
			  if (event.target == modal) {
			    modal.style.display = "none";
			    toastr.error("Eliminazione non effettuata");
			  }
			}
		</script>
		<script>
		function elimina()
		{
			toastr.success("Eliminazione effettuata con successo");
		}
		</script>
		<script>
		function notelimina()
		{
			toastr.error("Eliminazione non effettuata");
		}
		</script>
</body>
</html>