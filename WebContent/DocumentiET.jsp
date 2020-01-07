<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.Tirocinio,controller.CheckSession" %>
<%
	String pageName = "DocumentiET.jsp";
	String pageFolder = "";

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());	
	
	ArrayList<Tirocinio> listaTirocinio=new ArrayList<Tirocinio>();
	listaTirocinio=(ArrayList<Tirocinio>)request.getAttribute("listaTirocinio");
	if(listaTirocinio==null)
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("ServletDocumentiTirocinioET");
        dispatcher.forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
<head>
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
								//Se la listaTirocinio non è null mostro la tabella
								if(listaTirocinio!=null)
								{
								%>
									<table id="DocumentiTable" class="display data-results table table-striped table-hover table-bordered">
	
										<thead>
											<tr align="center">
												<th class="text-center" align="center">Matricola</th>
												<th class="text-center" align="center">Nome</th>
												<th class="text-center" align="center">Cognome</th>
												<th class="text-center" align="center">Competenze</th>
												<th class="text-center" align="center">Competenze Acquisite</th>
												<th class="text-center" align="center">Attivit&agrave; Previste</th>
												<th class="text-center" align="center">Svolgimento Tirocinio</th>
												<th class="text-center" align="center">Nome Ente</th>
												<th class="text-center" align="center">Azioni</th>		
											</tr>
										</thead>
										<tbody>
											<%
											//Scorro tutta la listaTirocinio
											for( int i = 0; i < listaTirocinio.size(); i++)
											{ %>
												<tr role='row' >
													<td class='text-center'>0<%=listaTirocinio.get(i).getMatricola()%></td>
													<td class='text-center'><%=listaTirocinio.get(i).getTirocinante().getName()%></td>
													<td class='text-center'><%=listaTirocinio.get(i).getTirocinante().getSurname()%></td>
													<td class='text-center'><%=listaTirocinio.get(i).getCompetenze()%></td>
													<td class='text-center'><%=listaTirocinio.get(i).getCompetenzeAcquisire()%></td>
													<td class='text-center'><%=listaTirocinio.get(i).getAttivitaPreviste()%></td>
													<td class='text-center'><%=listaTirocinio.get(i).getSvolgimentoTirocinio()%></td>
													<% if(listaTirocinio.get(i).getEnteConvenzionato()==null)
														{%>
															<td class='text-center'>Non &egrave; stato scelto ancora nessun Ente.</td>
														<%} else{%>
															<td class='text-center'><%=listaTirocinio.get(i).getEnteConvenzionato()%></td>
														<%} %>
													<td class="text-center" align="center">
														<form action="./ServletProgettoFormativoET" method="post">
															<input type ="hidden" name ="codiceTirocinio" value ="<%=listaTirocinio.get(i).getCodTirocinio()%>">
															<button id="download" name="download" type="submit" class="btn btn-primary btn-action Download" style="background:#e73f43; border:#e73f43" data-type="2" data-idrequest="35" title="Download"><i class="fa fa-times"></i></button>
															<button id="upload" name="upload"  type="submit" class="btn btn-primary btn-action Upload" style="background:#e73f43; border:#e73f43" data-type="2" data-idrequest="35" title="Upload"><i class="fa fa-times"></i></button>
														</form>
													</td>
												</tr>
											<%
											} %>
										</tbody>
									</table>
								<%}%>
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
				$('#DocumentiTable').DataTable( {
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
		</script>
</body>
</html>