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
	if((Tirocinio)request.getAttribute("tirocinio")==null)
	{
		int codTirocinio = Integer.parseInt((String) request.getParameter("codTirocinio"));
	}
	else
	{
		tirocinio=(Tirocinio)request.getAttribute("tirocinio");
	}
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
								
								<h2>Carica il Progetto Formativo (PDF):</h2>
									<h2>
										Trascina o premi sull'apposito riquadro per caricare un file.
										</h2>
										<div class='dropzoneUploader'>
											<form action="ServletUploadET" id="myform" name="myform">
												<input type="file" 
												style="text-align:center;height:180px;width: 1280px;cursor:pointer;outline:none;" 
												name="file" required>
												
											</form>
										</div>
											<div class="form-group">
													<input type="submit" form="myform" class="btn btn-primary btn-submit" value="Concludi">
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