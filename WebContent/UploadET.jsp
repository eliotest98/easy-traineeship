<%@page import="model.DAO.TirocinioDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import=" model.Student, model.Tirocinio, controller.CheckSession, controller.ServletProgettoFormativoET "%>

<%
	String pageName = "UploadET.jsp";
	String pageFolder = "";
	
    //Per vedere chi e' in sessione.
    int resp = Integer.parseInt((String)request.getSession().getAttribute("userET"));
    //Non volevo creare un'altra servlet solo per trovare il tirocinio
    TirocinioDAO t = new TirocinioDAO();
    Tirocinio tirocinio = t.TirocinioByCodTirocinio(Integer.parseInt(((String)request.getParameter("cod"))));
%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
<link href="<%=request.getContextPath()%>/css/styleET.css"
	rel="stylesheet">
<style type="text/css">
/*Non togliete, esterno non funziona*/
.toast {
	opacity: 0, 8 !important;
}
</style>
</head>

<body>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js"></script>

	<div class="page-wrapper">

		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage uploadAttachedPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">

								<%
								//Se il tirocinio da caricare è presente
								if(tirocinio!=null)
								{
								%>
								<h2>
									CARICA IL PROGETTO FORMATIVO N.<%=tirocinio.getCodTirocinio()%>
								</h2>
								<h2>Trascina o premi sull'apposito riquadro per caricare un
									file</h2>
								<div
									action="ServletUploadET?cod=<%=tirocinio.getCodTirocinio()%>"
									class='dropzoneUploader'></div>
								<%
								} 
								if (tirocinio==null) 
								{
								%>
								<div class="bordiET">
									<h2 class="centro">Non ci sono documenti da caricare</h2>
									<h3 class="centro">
										Clicca qui per tornare <a href=javascript:history.go(-1);>indietro</a>
									</h3>
								</div>
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
	<!-- Script EV -->
	<script src="<%= request.getContextPath() %>/js/filesystem_dropzone.js"></script>



	<script>
			$( document ).ready(function() {	
				$(".dropzoneUploader").dropzone({
					  maxFiles: 1,
					  acceptedFiles: ".pdf",
					  accept: function(file, done){
					    done();
					  },
					  init: function() {		
					      this.on("maxfilesexceeded", function(file, errorMessage){
					    	  this.removeFile(file);
					    	  showAlert(1, errorMessage);		    	  
					      });
	                      
					      this.on("error", function(file, errorMessage) {
					    	  this.removeFile(file);
					    	  showAlert(1, errorMessage);
	                      });
	                    
						  this.on("success", function(file, response) {
							  var msg = jQuery.parseJSON(response);
						  	  if(!msg.result){
						  		showAlert(1, msg.error);
						  	  }	            		    
						  	  else{
						  		file.previewElement.querySelector("[data-dz-name]").innerHTML = msg.content;
						  	  }
						  });
					  }		  						
				});					
			});
		</script>


</body>
</html>
