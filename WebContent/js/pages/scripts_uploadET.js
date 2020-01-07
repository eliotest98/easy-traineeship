/*Funzione ch erestituisce il successo della pagina.*/
	function concludi()
	{
		var con = document.getElementById("concludi");
		if(true)
		{	
			showAlert();
			toastr.success("PDF caricato con successo.");
			window.setTimeout('redirect()',3000);
			return true;
		}
		return false
	}
	
	function redirect()
	{
		location.href = '../_areaStudent/StatoProprioTirocinioET.jsp';
	}