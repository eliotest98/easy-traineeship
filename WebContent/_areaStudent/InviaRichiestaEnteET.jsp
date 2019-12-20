<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" import="java.util.*,controller.CheckSession,controller.ServletListaEnteET, model.EnteConvenzionato"%>

<%
    String pageName = "inviaRichiestaEnteET.jsp";
    String pageFolder = "_areaStudent";
    
    ArrayList<EnteConvenzionato> listaEnti=new ArrayList<EnteConvenzionato>();
	listaEnti=(ArrayList<EnteConvenzionato>)request.getAttribute("listaEnti");
	
	
%>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/partials/head.jsp" />
</head>

<body onLoad="">
<div class="page-wrapper" id="inviaRichiestaEnte">

    <!-- Preloader -->
    <!-- <div class="preloader"></div>  -->


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
                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 signUp-container">
                                <div class="panel">
                                    <h2 class="text-center">Invio Richiesta di Tirocinio all'Ente Convenzionato</h2>
                                    <p class="text-center">Compila tutti i campi per inviare la richiesta.</p>
                                </div>
                                <div>
	                                <form id="signUp" action="../ServletSceltaEnteET" method="post">
	                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
	                                    <label for="ente">Ente</label>
	                                        <select>
	                              <% System.out.println("jsp="+listaEnti); %>
	                                    	<%
											//Se la listaEnti non è null mostro la tabella
											
											if(listaEnti!=null)
											{
												//Scorro tutta la listaEnti
												for( int i = 0; i < listaEnti.size(); i++)
												{ %>
	  												<option value="<%=listaEnti.get(i).getName()%>"><%=listaEnti.get(i).getName()%></option>					
												<%
												} 
											}
												%>
	                                        </select>
	                                  	</div>
	                                  </form>
                                    </div>       
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="nome">Nome</label>
                                        <input type="text" class="form-control" id="nome" name="nome"
                                               placeholder="Nome" minlength="1" maxlength="50" required>
                                    </div>
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="cognome">Cognome</label>
                                        <input type="text" class="form-control" id="cognome" name="cognome"
                                               placeholder="Cognome" minlength="1" maxlength="50" required>
                                    </div>
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="facolta">Facolt&agrave;</label>
                                        <input type="text" class="form-control" id="facolta" name="facolta"
                                               placeholder="Facolt&agrave;" minlength="1" maxlength="50" required>
                                    </div>
                                    <div class="form-group col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                        <label for="descrizione">Descrizione</label>
                                        <input type="text" class="form-control"
                                               placeholder="Descrizione" minlength="1" maxlength="256" name="descrizione" id="descrizione" required>
                                    </div>
                                    <div
                                            class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <input type="submit" class="btn btn-primary btn-submit" value="Invia Richiesta"></input>
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

<jsp:include page="/partials/includes.jsp" />

</body>
</html>