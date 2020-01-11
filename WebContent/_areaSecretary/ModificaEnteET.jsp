<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.DAO.EnteConvenzionatoDAO,model.EnteConvenzionato, controller.CheckSession, java.util.*"%>
	
<%
	 //Controllo autenticazione tramite parametro in sessione (1 = Segreteria).
	String userET = (String) request.getSession().getAttribute("userET");
	if ((userET == null) || (!userET.equals("1"))) {
		response.sendRedirect("login.jsp");
		return;
	}
	
	//Autoriempimento form
	int i = Integer.valueOf(request.getParameter("ente"));
	System.out.println(i);
	EnteConvenzionatoDAO entedao = new EnteConvenzionatoDAO();
	ArrayList<EnteConvenzionato> listaEnti = entedao.allEnte(); //(ArrayList<EnteConvenzionato>) request.getAttribute("listaEnti");
	//Page Folder
	String pageName = "ModificaEnteET.jsp";
	String pageFolder = "_areaSecretary";
	//CheckSession
	String Segreteria = " ";
	Segreteria = (String) session.getAttribute("Segreteria");
	//Stringa della modifica avvenuta
	String mess = (String) request.getAttribute("mess");
	
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body onLoad="">
	<div class="page-wrapper" id="modificaEnte">

		<!-- Preloader -->
		<!-- <div class="preloader"></div>  -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%=pageName%>" />
			<jsp:param name="pageFolder" value="<%=pageFolder%>" />
		</jsp:include>


		<div class="sidebar-page-container basePage signUpPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<div
									class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container">
									<div class="panel">
										<h2 class="text-center">Modifica Ente Convenzionato</h2>
										<p class="text-center">Compila tutti i campi per
											modificare un nuovo ente.</p>
									</div>
									<form id="signUp"  onsubmit="return sendRequest()">
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="name">Nome Ente</label> <input type="text"
												class="form-control" id="name" name="name"
												placeholder="Nome Ente" value="<%=listaEnti.get(i).getName() %>" min="1" max="64"
												required pattern="[0-9a-zA-Z]{1,64}" title="Formato del nome non corretto.">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="partitaIva">Partita IVA</label> <input type="tel"
												oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
												class="form-control" placeholder="Partita IVA" value="<%=listaEnti.get(i).getPartitaIva() %>"
												name="partitaIva" id="partitaIva" size="11"  min="1" max="64" required pattern="[0-9]{11}" title="Formato della partita iva non corretto.">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="email">Email</label> <input type="email"
												class="form-control" id="email" name="email"
												placeholder="Email" value="<%=listaEnti.get(i).getEmail() %>" min="1" max="64" required  title="Formato dell'Email non corretto.">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="sede">Sede</label> <input type="text"
												class="form-control" id="sede" name="sede"
												placeholder="Sede" value="<%=listaEnti.get(i).getSede() %>" min="1" max="64" required pattern="[A-Z a-z 0-9]{1,64}" title="Formato della Sede non corretto.">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="telefono">Numero di telefono</label> <input
												type="tel"
												oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
												class="form-control" placeholder="Numero di Telefono" value="<%=listaEnti.get(i).getTelefono() %>"
												name="telefono" id="telefono" size="10" required pattern="[0-9]{10}" title="Formato del telefono non corretto.">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dipendenti">Numero Dipendenti</label> <input
												type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" class="form-control"
												placeholder="Numero di Dipendenti" value="<%=listaEnti.get(i).getDipendenti() %>" name="dipendenti"
												id="dipendenti" required pattern="[0-9]{1,64}" title="Formato del numero di dipendenti non corretto." min="1" max="64">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="rappresentante">Nome Rappresentante</label> <input
												type="text" class="form-control" id="rappresentante"
												name="rappresentante" placeholder="Nome Rappresentante" value="<%=listaEnti.get(i).getRappresentante() %>"
												min="1" max="64" required pattern="[a-z A-Z]{1,64}" title="Formato del Rappresentante non corretto.">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dataDiNascita">Data di Nascita del
												Rappresentante</label> <input type="text" class="form-control"
												placeholder="Data di Nascita" value="<%=listaEnti.get(i).getDataDiNascita() %>" name="dataDiNascita"
												id="dataDiNascita" required pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dotRiferimento">Professore di Riferimento</label>
											<input type="text" class="form-control" id="dotRiferimento"
												name="dotRiferimento"
												placeholder="Professore di Riferimento" value="<%=listaEnti.get(i).getDotRiferimento() %>" min="1"
												max="64" required pattern="[a-z A-Z]{1,64}" title="Formato del Professore di Riferimento non corretto.">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="referente">Referente Tirocini</label> <input
												type="text" class="form-control"
												placeholder="Referente Tirocini" value="<%=listaEnti.get(i).getReferente() %>" minlength="1"
												maxlength="64" name="referente" id="referente" required pattern="[a-z A-Z]{1,64}" title="Formato del referente non corretto.">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="descrizioneAttivita">Descrizione Attivit&agrave;</label>
											<input type = "text" class="form-control"
												placeholder="Descrizione delle Attivit&agrave;" value="<%=listaEnti.get(i).getDescrizioneAttivita() %>" minlength="1"
												maxlength="256" name="descrizioneAttivita"
												id="descrizioneAttivita" required min="1" max="256" title="Formato della descrizione attività non corretto.">
										</div>
										<div
											class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<button onclick="sendRequest()"  class="btn btn-primary btn-submit">Modifica
												Ente</button>
										</div>
										<div class="clearfix"></div>
								</form>
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

	<!--!!!!!!!!CAUSA ERRORI!!!!!!!!!!!-->
<jsp:include page="/partials/includes.jsp" />
<script>
	function sendRequest(){
	/*Form Input Values*/
			var form = document.forms["modificaEnte"];
			var name = document.getElementById("name").value;
			var partitaIva = document.getElementById("partitaIva").value;
			var email = document.getElementById("email").value;
			var sede = document.getElementById("sede").value;
			var telefono = document.getElementById("telefono").value;
			var dipendenti = document.getElementById("dipendenti").value;
			var rappresentante = document.getElementById("rappresentante").value;
			var dataDiNascita = document.getElementById("dataDiNascita").value;
			var dotRiferimento = document.getElementById("dotRiferimento").value;
			var referente = document.getElementById("referente").value;
			var descrizioneAttivita = document.getElementById("descrizioneAttivita").value;
			$.ajax({
				  type: "POST",
				  url: absolutePath+ "/ServletModificaEnteET",
				  async:false,
				  data: {
					  "name": name, 
					  "partitaIva": partitaIva,
					  "email":email,
					  "sede":sede,
					  "telefono":telefono,
					  "dipendenti":dipendenti,
					  "rappresentante":rappresentante,
					  "dataDiNascita":dataDiNascita,
					  "dotRiferimento":dotRiferimento,
					  "referente":referente,
					  "descrizioneAttivita":descrizioneAttivita
					  },
				  success: function(msg){
					showAlert();
					toastr.success(msg);
				     setTimeout(function(){// wait for 5 secs(2)
				           location.reload(); // then reload the page.(3)
				      }, 3000); 
				     },
				   error: function(msg){
						showAlert();
						toastr.success(msg);
					     setTimeout(function(){// wait for 5 secs(2)
					           location.reload(); // then reload the page.(3)
					      }, 3000); 
				   }
				  })
			}
</script>
</body>
</html>
