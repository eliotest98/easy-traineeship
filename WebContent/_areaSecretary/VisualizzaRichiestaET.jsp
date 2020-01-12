<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,controller.ServletListaEnteET, model.Tirocinio, controller.CheckSession" %>
<%
	String pageName = "VisualizzaRichiestaET.jsp";
	String pageFolder = "_areaSecretary";	
	
	/**
     * Controllo autenticazione tramite parametro in sessione (1 = Segreteria).
    */ 
    String userET = (String) request.getSession().getAttribute("userET");
    if ((userET == null) || (!userET.equals("1"))) {
      response.sendRedirect("login.jsp");
      return;
    }
	
	ArrayList<Tirocinio> listaTirocini=new ArrayList<Tirocinio>();
	listaTirocini=(ArrayList<Tirocinio>)request.getAttribute("listaTirocini");
	
	if(listaTirocini==null)
	{
        RequestDispatcher dispatcher = request.getRequestDispatcher("../ServletGestioneRichiesteSegreteriaET");
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
								//Se la lista non è null mostro la tabella
								if(listaTirocini!=null)
								{
								%>
									<table id="TabellaRichiestaTable" class="display data-results table table-striped table-hover table-bordered">
										<thead>
											<tr align="center">
												<th class="text-center" align="center">Matricola</th>
												<th class="text-center" align="center">Nome</th>
												<th class="text-center" align="center">Cognome</th>
												<th class="text-center" align="center">Data Inizio</th>
												<th class="text-center" align="center">CFU</th>
												<th class="text-center" align="center">Competenze</th>
												<th class="text-center" align="center">Competenze Da Acquisire</th>
												
												<th class="text-center" align="center">Stato</th>
												<th class="text-center" align="center">Azioni</th>
											</tr>
										</thead>
										<tbody  >
											<%
											//Scorro tutta la listaEnti
											for( int i = 0; i < listaTirocini.size(); i++)
											{ 
												//String format per la Data Inizio Tirocinio
												String data = listaTirocini.get(i).getDataInizioTirocinio();
												String anno = data.substring(0,4);
												String mese = data.substring(4,8);
												String day = data.substring(8,10);
												String datainiziotirocinio = day+mese+anno;
											%>
												<tr role='row' >
													<td class='text-center'>0<%=listaTirocini.get(i).getMatricola()%></td>
													<td class='text-center'><%=listaTirocini.get(i).getTirocinante().getName()%></td>
													<td class='text-center'><%=listaTirocini.get(i).getTirocinante().getSurname()%></td>
													<td class='text-center'><%=datainiziotirocinio%></td>
													<td class='text-center'><%=listaTirocini.get(i).getCfuPrevisti()%></td>
													<td class='text-center'><%=listaTirocini.get(i).getCompetenze()%></td>
													<td class='text-center'><%=listaTirocini.get(i).getCompetenzeAcquisire()%></td>
													<td class='text-center'><%=listaTirocini.get(i).getStatoTirocinio()%></td>
													<td class="text-center" align="center">
														<a href='VisualizzaTirocinanteET.jsp?matricola=<%=listaTirocini.get(i).getMatricola()%>' class="btn btn-primary btn-action modificaEnte" title="Accetta/Rifiuta" data-idrequest="35"><i class="fa fa-eye"></i></a>
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
				$('#TabellaRichiestaTable').DataTable( {
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