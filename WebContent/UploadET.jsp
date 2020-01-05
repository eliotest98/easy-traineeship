<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import=" model.Student, model.Tirocinio, controller.CheckSession"%>

<%
	String pageName = "UploadET.jsp";
	String pageFolder = "";
	
	//Per prelevare l'utente dalla sessione e precompilare i campi del form.
    Student user = (Student)request.getSession().getAttribute("user");
    //Per vedere chi e' in sessione.
    //int resp = Integer.parseInt((String)request.getSession().getAttribute("userET"));
    
	
    Tirocinio tirocinio=new Tirocinio();
	tirocinio=(Tirocinio)request.getAttribute("tirocinio");
%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/partials/head.jsp" />
	<link href="<%=request.getContextPath()%>/css/styleET.css" rel="stylesheet">
</head>
<body>
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
								
								<h2>Richiesta:</h2>
									<h2>
										Trascina o premi sull'apposito riquadro per caricare un file
										</h2>
										<div action='<%= request.getContextPath() + "/Uploader" %>' class='dropzoneUploader'></div>
										<div class="form-group">
											<button type="submit" class="btn btn-primary btn-submit" id='aggiungiAllegati'>
												Concludi
											</button>
										</div>
							<!-- <% 
								if ((tirocinio==null) ) 
								{
								%>
									<div class="bordiET">
										<h2 class="centro">
											
											Non ci sono documenti da caricare
										</h2>
										<h3 class="centro">
											Clicca qui per tornare <a href=javascript:history.go(-1);>indietro</a>
										</h3>
									</div>
								<%
								}
								%>-->	
								
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