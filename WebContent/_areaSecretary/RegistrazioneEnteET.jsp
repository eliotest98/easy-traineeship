<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	 //Controllo autenticazione tramite parametro in sessione (1 = Segreteria).
	String userET = (String) request.getSession().getAttribute("userET");
	if ((userET == null) || (!userET.equals("1"))) {
		response.sendRedirect("login.jsp");
		return;
	}
	//Page Folder
	String pageName = "RegistrazioneEnteET.jsp";
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
	<div class="page-wrapper" id="registrazioneEnte">

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
										<h2 class="text-center">Registrazione Ente Convenzionato</h2>
										<p class="text-center">Compila tutti i campi per
											registrare un nuovo ente.</p>
									</div>
									<form id="signUp" action="../ServletRegistrazioneEnteET"
										method="post">
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="name">Nome Ente</label> <input type="text"
												class="form-control" id="name" name="name"
												placeholder="Nome Ente"
												>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="partitaIva">Partita IVA</label> <input type="tel"
												oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
												class="form-control" placeholder="Partita IVA"
												name="partitaIva" id="partitaIva">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="email">Email</label> <input type="email"
												class="form-control" id="email" name="email"
												placeholder="Email">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="sede">Sede</label> <input type="text"
												class="form-control" id="sede" name="sede"
												placeholder="Sede" >
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="telefono">Numero di telefono</label> <input
												type="tel"
												oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
												class="form-control" placeholder="Numero di Telefono"
												name="telefono" id="telefono">
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dipendenti">Numero Dipendenti</label> <input
												type="text" class="form-control"
												placeholder="Numero di Dipendenti" name="dipendenti"
												id="dipendenti" >
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="rappresentante">Nome Rappresentante</label> <input
												type="text" class="form-control" id="rappresentante"
												name="rappresentante" placeholder="Nome Rappresentante"
												>
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dataDiNascita">Data di Nascita del
												Rappresentante</label> <input type="text" class="form-control"
												placeholder="Data di Nascita" name="dataDiNascita"
												id="dataDiNascita" >
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="dotRiferimento">Professore di Riferimento</label>
											<input type="text" class="form-control" id="dotRiferimento"
												name="dotRiferimento"
												placeholder="Professore di Riferimento" >
										</div>
										<div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
											<label for="referente">Referente Tirocini</label> <input
												type="text" class="form-control"
												placeholder="Referente Tirocini" 
												name="referente" id="referente" >
										</div>
										<div class="form-group col-lg-12 col-md-12 col-md-12 ">
											<label for="descrizioneAttivita">Descrizione Attivit&agrave;</label>
											<textarea class="form-control"  rows="3"
												placeholder="Descrizione delle Attivit&agrave;" name="descrizioneAttivita"
												id="descrizioneAttivita"></textarea>
										</div>
										<div
											class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<button onclick="return validate()" class="btn btn-primary btn-submit">Registra
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
	<!--<jsp:include page="/partials/includes.jsp" />-->
	<script>
	function successMessage(){
		toastr.success("Registrazione effettuata con successo");
	}
	</script>
	<script>
		/*This function validate form inputs*/
		function validate() {
	
			/*Form Input Values*/
			var form = document.forms["registrazioneEnte"]
			var name = document.getElementById("name").value;
			var partitaIva = document.getElementById("partitaIva").value;
			var email = document.getElementById("email").value;
			var sede =document.getElementById("sede").value;
			var telefono = document.getElementById("telefono").value;
			var dipendenti = document.getElementById("dipendenti").value;
			var rappresentante = document.getElementById("rappresentante").value;
			var dataDiNascita = document.getElementById("dataDiNascita").value;
			var dotRiferimento = document.getElementById("dotRiferimento").value;
			var referente = document.getElementById("referente").value;
			var descrizioneAttivita = document.getElementById("descrizioneAttivita").value;
			console.log(name)
			/*REGEX Values*/
			const alphaNumRGX = /^[a-z A-Z 0-9]$/
			const alphRGX = /^[a-z A-Z]$/
			const dateRGX = /^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$/
			const enumRGX = /^[0-9]{1,3}$/
			const mailRGX = /^[A-z0-9\.\+_-] +@[A-z0-9\._-]+\.[A-z]{2,6}$/
			const numRGX = /^[0-9]{10}$/
			/*LENGTH VALIDATION*/
			if (name.length <= 0 || name.length > 64) // Check Length
			{
				toastr.error("Lunghezza non rispettata")
				toastr.error("Registrazione non effettuata")
				return false;
			}
			if (partitaIva.length <= 0 || partitaIva.length > 11) // Check Length
			{
				toastr.error("Lunghezza non rispettata")
				toastr.error("Registrazione non effettuata")
				return false;
			}
			if (email.length <= 0 || email.length > 64) // Check Length
			{
				toastr.error("Lunghezza non rispettata")
				toastr.error("Registrazione non effettuata")
				return false;
			}
			if (sede.length <= 0 || sede.length > 64) // Check Length
			{
				toastr.error("Lunghezza non rispettata")
			    toastr.error("Registrazione non effettuata")
				return false;
			}
			if (telefono.length <= 0 || telefono.length > 64) // Check Length
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Lunghezza non rispettata")
				return false;
			}
			if (dipendenti.length <= 0 || dipendenti.length > 64) // Check Length
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Lunghezza non rispettata")
				return false;
			}
			if (rappresentante.length <= 0 || rappresentante.length > 64) // Check Length
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Lunghezza non rispettata")
				return false;
			}
			if (dataDiNascita.length <= 0 || dataDiNascita.length > 64) // Check Length
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Lunghezza non rispettata")
				return false;
			}
			if (dotRiferimento.length <= 0 || dotRiferimento.length > 64) // Check Length
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Lunghezza non rispettata")
				return false;
			}
			if (referente.length <= 0 || referente.length > 64) // Check Length
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Lunghezza non rispettata")
				return false;
			}
			if (descrizioneAttivita.length <= 0 || descrizioneAttivita.length > 64) // Check Length
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Lunghezza non rispettata")
				return false;
			}
			/*FORMAT VALIDATION*/
			if (alphRGX.test(name)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (numRGX.test(partitaIva)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (mailRGX.test(email)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (alphRGX.test(sede)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (!numRGX.test(telefono)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (!enumRGX.test(dipendenti)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (alphRGX.test(rappresentante)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (!dateRGX.test(dataDiNascita)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (alphRGX.test(dotRiferimento)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (alphRGX.test(referente)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			if (alphaNumRGX.test(descrizioneAttivita)) // Check Format
			{
				toastr.error("Registrazione non effettuata")
				toastr.error("Formato non rispettato")
				return false;
			}
			toastr.success("Registrazione  effettuata")
			return true
		}
	</script>
</body>
</html>
