<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession , controller.Utils "%>

<%
	String pageName = request.getParameter("pageName");
	String pageFolder = request.getParameter("pageFolder");
	String menu = "";
	String hiddenMenu = "";
	String logoRedirect = ""; //tiene traccia del path a cui reindirizzare il sito quando si preme sul logo
	String Segreteria = " ";
	String UserET= " ";
	
	CheckSession ck = new CheckSession(pageName,pageFolder,request.getSession());
	
	Segreteria = (String) session.getAttribute("Segreteria");
	UserET = (String) session.getAttribute("userET");

  if (pageFolder.equals("_areaAdmin")) { //se stiamo in una pagina dell'area admin
	  logoRedirect = request.getContextPath()+"/_areaAdmin/viewRequest.jsp";
  
    if (pageName.equals("viewRequest.jsp")) {
      menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
          + "/viewRequest.jsp\">Richieste</a></li>";
      menu += "<li><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
      menu +=
          "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
    }
  } else if (pageFolder.equals("_areaSecretary")) { //se stiamo in una pagina dell'area segreteria
	  logoRedirect = request.getContextPath()+"/_areaSecretary/viewRequest.jsp";
  
    if (pageName.equals("viewRequest.jsp")) {
      menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
          + "/viewRequest.jsp\">Richieste</a></li>";
      menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
          + "/VisualizzaRichiestaET.jsp\">Richieste Tirocinio</a></li>";
      menu += "<li><a href=\"" + request.getContextPath() + "/VisualizzaEnteET.jsp\">Lista Ente</a></li>";
      menu += "<li><a href=\"" + request.getContextPath() + "/_areaSecretary/RegistrazioneEnteET.jsp\">Registra Ente</a></li> ";
      menu += "<li><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
      menu +="<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
    }if (pageName.equals("VisualizzaRichiestaET.jsp")) {
        menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
                + "/viewRequest.jsp\">Richieste</a></li>";
            menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
                + "/VisualizzaRichiestaET.jsp\">Richieste Tirocinio</a></li>";
            menu += "<li><a href=\"" + request.getContextPath() + "/VisualizzaEnteET.jsp\">Lista Ente</a></li>";
            menu += "<li><a href=\"" + request.getContextPath() + "/_areaSecretary/RegistrazioneEnteET.jsp\">Registra Ente</a></li> ";
            menu += "<li><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
            menu +="<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
    }else if (pageName.equals("ModificaEnteET.jsp")) { //se ci troviamo in ModificaEnteET.jsp
        menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
        + "/VisualizzaEnteET.jsp\">Modifica Ente</a></li> <li ><a href=javascript:history.go(-1);>Indietro</a></li> ";
    }else if (pageName.equals("RegistrazioneEnteET.jsp")) { //se ci troviamo in RegistrazioneEnteET.jsp
    	menu += "<li ><a href=\"" + request.getContextPath() + "/" + pageFolder
    	          + "/viewRequest.jsp\">Richieste</a></li>";
    	      menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
    	          + "/VisualizzaRichiestaET.jsp\">Richieste Tirocinio</a></li>";
    	      menu += "<li><a href=\"" + request.getContextPath() + "/VisualizzaEnteET.jsp\">Lista Ente</a></li>";
    	      menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/_areaSecretary/RegistrazioneEnteET.jsp\">Registra Ente</a></li> ";
    	      menu += "<li><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
    	      menu +="<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
      }else if (pageName.equals("VisualizzaTirocinanteET.jsp"))//se ci troviamo in VisualizzaTirocinanteET.jsp
      {
    	  menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
          + "/VisualizzaTirocinanteET.jsp\">Informazioni Tirocinante</a></li> <li ><a href=javascript:history.go(-1);>Indietro</a></li> ";
      }	
	} else if (pageFolder.equals("_areaStudent")) { //se stiamo in una pagina dell'area studente
		logoRedirect = request.getContextPath() + "/_areaStudent/HomeStudente.jsp";

		if (pageName.equals("viewRequest.jsp")) { //se stiamo in viewRequest
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/viewRequest.jsp\">Richieste</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/_areaStudent/InviaRichiestaET.jsp\">Richiesta Tirocinio</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/"
					+ "ServletListaEnteET?richiestaEnte=ok\">Richiesta Ente</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("HomeStudente.jsp")) { //se stiamo in home dello studente, fra et ed ev
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/_areaStudent/InviaRichiestaET.jsp\">Richiesta Tirocinio</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/"
					+ "ServletListaEnteET?richiestaEnte=ok\">Richiesta Ente</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("StatoProprioTirocinioET.jsp")) { //se stiamo in controlla stato
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/_areaStudent/InviaRichiestaET.jsp\">Richiesta Tirocinio</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/"
					+ "ServletListaEnteET?richiestaEnte=ok\">Richiesta Ente</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("StoricoStudenteET.jsp")) { //se stiamo nello storico delle richieste
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/_areaStudent/InviaRichiestaET.jsp\">Richiesta Tirocinio</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/"
					+ "ServletListaEnteET?richiestaEnte=ok\">Richiesta Ente</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("InviaRichiestaET.jsp")) { //se stiamo in invio richiesta
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> ";
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
					+ "/_areaStudent/InviaRichiestaET.jsp\">Richiesta Tirocinio</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/"
					+ "ServletListaEnteET?richiestaEnte=ok\">Richiesta Ente</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("InviaRichiestaEnteET.jsp")) { //se stiamo in home dello studente, fra et ed ev
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/_areaStudent/InviaRichiestaET.jsp\">Richiesta Tirocinio</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/"
					+ "ServletListaEnteET?richiestaEnte=ok\">Richiesta Ente</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("firstForm.jsp")) { //pagina cfu inglese
			menu += "<li ><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/viewRequest.jsp\">Richieste</a></li>";
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> ";
			menu += "<li ><a href=\"" + request.getContextPath()
					+ "/_areaStudent/InviaRichiestaET.jsp\">Richiesta Tirocinio</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/"
					+ "ServletListaEnteET?richiestaEnte=ok\">Richiesta Ente</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("uploadAttached.jsp")) { //bho
			menu += "<li ><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/viewRequest.jsp\">Richieste</a></li>";
			menu += "<li ><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">Compila Richiesta</a></li>";
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Carica Allegato</a></li>";
			menu += "<li><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> ";
			menu += "<li ><a href=\"" + request.getContextPath()
					+ "/_areaStudent/InviaRichiestaET.jsp\">Richiesta Tirocinio</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/"
					+ "ServletListaEnteET?richiestaEnte=ok\">Richiesta Ente</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}
		if (pageName.equals("signUp.jsp")) {
			logoRedirect = request.getContextPath() + ck.getUrlRedirect(); //siccome signUp � raggiungibile solo quando non sono loggato
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/signUp.jsp\">Registrati</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/index.jsp\">Benvenuto</a></li>";
		}
	} else if (pageFolder.equals("_areaEnteET")) { //se stiamo in una pagina dell'area utente
		logoRedirect = request.getContextPath() + "/_areaEnteET/VisualizzaRichiestaEnteET.jsp";
		if (pageName.equals("VisualizzaRichiestaEnteET.jsp")) { //se stiamo in viewRequest
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/VisualizzaRichiestaEnteET.jsp\">Richieste</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
		}else if(pageName.equals("VisualizzaTirocinanteEnteET.jsp")){
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
			          + "/VisualizzaTirocinanteEnteET.jsp\">Informazioni Tirocinante</a></li> <li ><a href=javascript:history.go(-1);>Indietro</a></li> ";
		}
	} else if (pageFolder.equals("")) { //se non siamo (o siamo) loggati
		logoRedirect = request.getContextPath() + ck.getUrlRedirect();
		if (pageName.equals("login.jsp")) { //se ci troviamo in login.jsp
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
					+ "/login.jsp\">Login</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/index.jsp\">Benvenuto</a></li>";
		}else if(pageName.equals("DocumentiET.jsp")){
			if (Segreteria != null) {//Segreteria
				logoRedirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";
				 menu += "<li><a href=\"" + request.getContextPath() + "/_areaSecretary"
				          + "/viewRequest.jsp\">Richieste</a></li>";
				 menu += "<li><a href=\"" + request.getContextPath() + "/_areaSecretary"
				          + "/VisualizzaRichiestaET.jsp\">Richieste Tirocinio</a></li>";
				 menu += "<li><a href=\"" + request.getContextPath() + "/VisualizzaEnteET.jsp\">Lista Ente</a></li>";
				 menu += "<li><a href=\"" + request.getContextPath() + "/_areaSecretary/RegistrazioneEnteET.jsp\">Registra Ente</a></li> ";
				 menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
				 menu +="<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			}else if(UserET.equals("3")){//Ente
				logoRedirect = request.getContextPath() + "/_areaEnteET/VisualizzaRichiestaEnteET.jsp";
				menu += "<li><a href=\"" + request.getContextPath() + "/_areaEnteET"
						+ "/VisualizzaRichiestaEnteET.jsp\">Richieste</a></li>";
				menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			}else if(UserET.equals("2")){//Admin
				logoRedirect = request.getContextPath() + "/_areaAdmin/viewRequest.jsp";
				menu += "<li><a href=\"" + request.getContextPath() + "/_areaAdmin/" + pageFolder
				          + "/viewRequest.jsp\">Richieste</a></li>";
				menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
			}
		}else if (pageName.equals("index.jsp")) { //se ci troviamo in index.jsp
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
					+ "/index.jsp\">Benvenuto</a></li> <li ><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li>";
		} else { //se ci troviamo in logout.jsp
			if (pageName.equals("logout.jsp")) {
				menu += "<li><a href=\"" + request.getContextPath() + "/index.jsp\">Benvenuto</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/login.jsp\">Accedi</a></li>";
			} else if (pageName.equals("VisualizzaEnteET.jsp")) //se ci troviamo in visualizzaEnteET.jsp
			{
				if (Segreteria != null) {
					logoRedirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";
					menu += "<li><a href=\"" + request.getContextPath() + "" + pageFolder
							+ "/_areaSecretary/viewRequest.jsp\">Richieste</a></li>";
					menu += "<li><a href=\"" + request.getContextPath() + ""
							+ "/_areaSecretary/VisualizzaRichiestaET.jsp\">Richieste Tirocinio</a></li>";
					menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
							+ "/VisualizzaEnteET.jsp\">Lista Ente</a></li>";
					menu += "<li><a href=\"" + request.getContextPath()
							+ "/_areaSecretary/RegistrazioneEnteET.jsp\">Registra Ente</a></li> ";
					menu += "<li><a href=\"" + request.getContextPath() + "/DocumentiET.jsp\">Documenti da Firmare</a></li> ";
					menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Disconnetti</a></li>";
				}else if(UserET !=null){
					logoRedirect = request.getContextPath() + "/_areaStudent/HomeStudente.jsp";
					menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
					+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> <li ><a href=javascript:history.go(-1);>Indietro</a></li> ";
				}else {
					menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
							+ "/VisualizzaEnteET.jsp\">Lista Enti</a></li> <li ><a href=javascript:history.go(-1);>Indietro</a></li> ";
				}
			}
		}
	}

	hiddenMenu = menu;
%>
<!-- Modal -->
<div id="defaultModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<form action="#">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</form>

	</div>
</div>
<!-- Main Header -->
<header class="main-header">
	<!--Header-Upper-->
	<div class="header-upper">
		<div class="auto-container">
			<div class="clearfix">

				<div class="logo-outer">
					<div class="logo">
						<a href="<%=logoRedirect%>"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--End Header Upper-->

	<!--Header Lower-->
	<div class="header-lower">
		<div class="auto-container">
			<div class="nav-outer clearfix">

				<!-- Main Menu -->
				<nav class="main-menu">
					<div class="navbar-collapse collapse clearfix"
						id="bs-example-navbar-collapse-1">
						<ul class="navigation clearfix">
							<%=menu%>
						</ul>
					</div>
				</nav>


				<!-- Hidden Nav Toggler -->
				<div class="nav-toggler">
					<button class="hidden-bar-opener">
						<span class="icon qb-menu1"></span>
					</button>
				</div>

			</div>
		</div>
	</div>
	<!--End Header Lower-->

</header>
<!--End Header Style Two -->

<!-- Hidden Navigation Bar -->
<section class="hidden-bar left-align">

	<div class="hidden-bar-closer">
		<button>
			<span class="qb-close-button"></span>
		</button>
	</div>

	<!-- Hidden Bar Wrapper -->
	<div class="hidden-bar-wrapper">
		<div class="logo">
			<a href="#"></a>
		</div>
		<!-- .Side-menu -->
		<div class="side-menu">
			<!--navigation-->
			<ul class="navigation clearfix">
				<%=hiddenMenu%>
			</ul>
		</div>
		<!-- /.Side-menu -->

	</div>
	<!-- / Hidden Bar Wrapper -->


</section>
<!-- End / Hidden Bar -->
