<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,model.Tirocinio,controller.CheckSession"%>
<%
	String pageName = "DocumentiET.jsp";
	String pageFolder = "";

	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());

	ArrayList<Tirocinio> listaTirocinio = new ArrayList<Tirocinio>();
	listaTirocinio = (ArrayList<Tirocinio>) request.getAttribute("listaTirocinio");
	
	//controllo che la lista non sia null
	if (listaTirocinio == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("ServletDocumentiTirocinioET");
		dispatcher.forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>
<body>
	<div class="page-wrapper">

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
								<%
									//Se la listaTirocinio non � null mostro la tabella
									if (listaTirocinio != null) {
								%>
								<table id="DocumentiTable"
									class="display data-results table table-striped table-hover table-bordered">

									<thead>
										<tr align="center">
											<th class="text-center" align="center">Matricola</th>
											<th class="text-center" align="center">Nome</th>
											<th class="text-center" align="center">Cognome</th>
											<th class="text-center" align="center">Competenze</th>
											<th class="text-center" align="center">Competenze
												Acquisite</th>
											<th class="text-center" align="center">Attivit&agrave;
												Previste</th>
											<th class="text-center" align="center">Svolgimento
												Tirocinio</th>
											<th class="text-center" align="center">Partita IVA Ente</th>
											<th class="text-center" align="center">Azioni</th>
										</tr>
									</thead>
									<tbody>
										<%
											//Scorro tutta la listaTirocinio
												for (int i = 0; i < listaTirocinio.size(); i++) {
										%>
										<tr role='row'>
											<td class='text-center'>0<%=listaTirocinio.get(i).getMatricola()%></td>
											<td class='text-center'><%=listaTirocinio.get(i).getTirocinante().getName()%></td>
											<td class='text-center'><%=listaTirocinio.get(i).getTirocinante().getSurname()%></td>
											<td class='text-center'><%=listaTirocinio.get(i).getCompetenze()%></td>
											<td class='text-center'><%=listaTirocinio.get(i).getCompetenzeAcquisire()%></td>
											<td class='text-center'><%=listaTirocinio.get(i).getAttivitaPreviste()%></td>
											<td class='text-center'><%=listaTirocinio.get(i).getSvolgimentoTirocinio()%></td>
											<%
												//se l'ente convenzionato � null vuol dire che l'ente non � stato ancora scelto
												if (listaTirocinio.get(i).getPartitaIva() == null) {
											%>
											<td class='text-center'>Non &egrave; stato scelto ancora
												nessun Ente.</td>
											<%
												} else {
											%>
											<td class='text-center'><%=listaTirocinio.get(i).getPartitaIva()%></td>
											<%
												}
											%>
											<td class="text-center" align="center">
												<a href="ServletDownloadET?cod=<%=listaTirocinio.get(i).getCodTirocinio()%>">
													<button id="download" name="download" type="submit"
													style="width: 30px; background-color: #FF9900; outline: none; border: 4px solid #FF9900; border-radius: 5px; color: white; margin: 2%;"
													style="background:#e73f43; border:#e73f43" data-type="2"
													data-idrequest="35" title="Download">
														<img src="./images/icons/download.png" title="Download">
													</button>
												</a>
												<a href="UploadET.jsp?cod=<%=listaTirocinio.get(i).getCodTirocinio()%>">
													<button id="upload" name="upload" type="submit"
													style="width: 30px; background-color: #FF9900; outline: none; border: 4px solid #FF9900; border-radius: 5px; color: white; margin: 2%;"
													data-type="2" data-idrequest="35" title="Upload">
														<img src="./images/icons/upload.png" title="Upload">
													</button>
												</a>
											</td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
								<%
									}
								%>
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
							$('#DocumentiTable')
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
	</script>
</body>
</html>