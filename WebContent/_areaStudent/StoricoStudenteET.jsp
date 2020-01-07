<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,controller.ServletListaEnteET, model.*, controller.CheckSession" %>
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
									<table id="TabellaEnteTable" class="display data-results table table-striped table-hover table-bordered">
										<thead>
											<tr align="center">
												<th class="text-center" align="center">Codice Tirocinio:</th>
												<th class="text-center" align="center">Stato:</th>
												<th class="text-center" align="center">Competenze Possedute:</th>
												<th class="text-center" align="center">Competenze da acquisire:</th>
												<th class="text-center" align="center">Ente Convenzionato:</th>
												<th class="text-center" align="center">Attivit&agrave; previste:</th>
												<th class="text-center" align="center">Modalit&agrave; di svolgimento:</th>
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
													<tr role='row' >
														<td class='text-center'><%=listaTirocini.get(i).getCodTirocinio()%></td>
														<td class='text-center'><%=listaTirocini.get(i).getStatoTirocinio()%>
														<%if(listaTirocini.get(i).getStatoTirocinio().equalsIgnoreCase("In attesa Ente"))
														{%>
														<form id="AnnullaEnte" action="ServletAnnullaEnteDaStudenteET" method="post">
															<input id="codTirocinio"name="codTirocinio"type="hidden"value="<%=listaTirocini.get(i).getCodTirocinio()%>">
															<input id="matricola"name="matricola"type="hidden"value="<%=listaTirocini.get(i).getMatricola() %>">
															<button id="Accetta" type="submit" style="background-color:#FF9900;outline:none;border:4px solid #FF9900;border-radius:5px; color:white; margin:2%;" title="Accetta" data-idrequest="35">Annulla Richiesta</button>
														</form>
														<%}%>
														</td>
														<td class='text-center'><%=listaTirocini.get(i).getCompetenze()%></td>
														<td class='text-center'><%=listaTirocini.get(i).getCompetenzeAcquisire()%></td>
														<%if(listaTirocini.get(i).getEnteConvenzionato()==null)
														{%>
															<td class='text-center'>Non &egrave; stato scelto ancora nessun Ente.</td>
														<%} else{%>
															<td class='text-center'><%=listaTirocini.get(i).getEnteConvenzionato()%></td>
														<%} %>
														<td class='text-center'><%=listaTirocini.get(i).getAttivitaPreviste()%></td>
														<td class='text-center'><%=listaTirocini.get(i).getSvolgimentoTirocinio()%></td>
													</tr>
												<%
												} }%>
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
		</script>
</body>
</html>