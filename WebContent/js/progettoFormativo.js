
		function createPdf(){
			var data = new Date();
			var gg, mm, aaaa;
			gg = data.getDate() + "/";
			mm = data.getMonth() + 1 + "/";
			aaaa = data.getFullYear();
			
			var doc = new jsPDF();


			doc.setTextColor(50,60,181);
			doc.setFontStyle("bold");
			doc.setFontSize(14);
			doc.text("UNIVERSITA' DEGLI STUDI DI SALERNO ",105, 20, null, null,  'center');			
			doc.setFontSize(12);
			doc.text("DIPARTIMENTO DI INFORMATICA", 105, 30, null, null, 'center');			
			doc.setTextColor('black');
			doc.setFontSize(12);
			doc.text("PROGETTO FORMATIVO E DI ORIENTAMENTO", 105, 40, null, null, 'center' );
			doc.text('LAUREA TRIENNALE / LAUREA MAGISTRALE ', 105, 50, null, null, 'center' );
			doc.setFontSize(12);
			doc.text('Relativo alla Convenzione per tirocinio di formazione ed orientamento (curriculare) stipulata', 10, 60);
			doc.fromHTML('<b>con <em>' + $("#name").val()  + ' ' + $("#surname").val()  + '</em> in data  <em>' +  gg + mm + aaaa + '</em>, Repertorio N. <em>' + $("#cod").val() + '</em></b>', 9, 60);
			doc.text('SOGGETTO PROMOTORE', 10, 80);
	       	doc.setFontStyle('justify');
	       	doc.text("Dipartimento di Informatica dell' Universita' degli Studi di Salerno; ", 10, 87);
	        doc.text('Sede in Via Giovanni Paolo II, 132, 84084 Fisciano (Salerno)', 10, 94);
		    doc.text('Indirizzo PEC ammicent@pec.unisa.it ', 10, 101);
		    doc.text('Codice Fiscale 80018670655 ', 10, 108);
	        doc.text('Rappresentante legale: prof. Alfredo De Santis, in qualità di Direttore pro tempore, nato a nato a Nocera ', 10, 115);
		    doc.text('Inferiore (SA) il 07/12/1960. ', 10, 122);	
	        
	        doc.setFontStyle("bold");
		    doc.text('SOGGETTO OSPITANTE ', 10, 135);
	        doc.setFontStyle("justify");
	        
	        doc.fromHTML('Denominazione  <em><b> ' + $("#denominazione").val() + ' </b></em> (specificare la natura giuridica) ', 9, 135);
	        doc.fromHTML('Sede legale in <em><b>' + $("#sede").val()  + '</b></em> ', 9, 142);
	        doc.fromHTML('Indirizzo PEC <em><b>' + $("#pec").val()  + '</b></em> ', 9, 149);
	        doc.fromHTML('Codice Fiscale e Partita IVA <em><b>' + $("#partitaIva").val()  + '</b></em> ', 9, 156);
			
	        doc.fromHTML("Rappresentante legale: <em><b> " + $("#rappresentante").val() + " </em></b>, in qualità di <em><b>Amministratore</em></b>, nato a ______________ ", 9, 163);
	        doc.fromHTML("il: <em><b> " + $("#dataNascita").val() + " </em></b> ", 9, 170);
	        doc.fromHTML("Attività economica esercitata _________________", 9, 177);
	        doc.fromHTML("Codice ATECO _________________ ", 9, 184);
	        doc.fromHTML("Numero Dipendenti a tempo indeterminato <em><b> " + $("#dipendenti").val() + " </em></b>", 9, 191);

		    doc.fromHTML('<b>TIROCIANTE</b> ', 9, 204);

	        doc.fromHTML('Cognome e nome del tirocinante;', 9, 211);
	        doc.fromHTML('Data e luogo di nascita <em><b>' + $("#dataNascitaTirocinante").val()  + ' ' + $("#luogoNascitaTirocinante").val()  + '</b></em> ', 9, 218);
	        doc.fromHTML('Cittadinanza <em><b>' + $("#cittadinanza").val()  + '</b></em> ', 9, 225);
	        doc.fromHTML('Residenza <em><b>' + $("#residenza").val()  + '</b></em> ', 9, 232);
	        
	        doc.addPage();
	        
	        doc.fromHTML('Codice Fiscale <em><b>' + $("#codiceFiscaleTirocinante").val()  + '</b></em>', 9, 20);
	        doc.fromHTML('Telefono n. <em><b>' + $("#telefonoTirocinante").val()  + ' </b></em> ', 9, 27);
	        doc.fromHTML('Indirizzo e-mail <em><b>' + $("#emailTirocinante").val()  + '</b></em> ', 9, 34);
	        doc.fromHTML('Iscritto al Corso di Laurea Triennale in Informatica  ', 9, 41);
	        
		    doc.fromHTML('<b>TUTOR DESIGNATO DAL DIPARTIMENTO:<em>'+ $("#dotRiferimento").val()+ '</em></b>', 9, 55);
		    
		    doc.fromHTML('<b>TUTOR DESIGNATO DAL SOGGETTO OSPITANTE: <em>'+ $("#referente").val() +'</em></b>' , 9, 69);
		    doc.fromHTML("Tel. __________________", 9, 76);
		    doc.fromHTML("Email _________________ ", 9, 83);
		    
		    doc.fromHTML("<b>N. TOTALE DI CREDITI FORMATIVI PREVISTI PER L' ATTIVITÀ DI TIROCINIO: <em>"+ $("#cfu").val() + "</em> di cui:</b>", 9, 97);
		    doc.rect(13, 107, 3, 3)
		    doc.fromHTML("___ CFU per tirocinio curricular", 17, 104);
		    doc.rect(13, 114, 3, 3)
		    doc.fromHTML("___ CFU provenienti da tirocinio esterno (1) a scelta", 17, 111);
		    doc.rect(13, 121, 3, 3)
		    doc.fromHTML("___ CFU provenienti da tirocinio esterno (2) a scelta", 17, 118);
		    
		    doc.fromHTML('<b>SEDE DI SVOLGIMENTO DEL TIROCINIO: <em>'+ $("#sede").val()+ '</em></b>', 9, 132);
		    
			doc.setFontStyle("bold");
		    doc.fromHTML('<b>INDICAZIONE LE COMPETENZE DA ACQUISIRE    </b>', 9, 146);
		    var obiettivi=$("#competenze").val();
		    var splitTesto = doc.splitTextToSize(obiettivi, 180);
		    doc.text(10, 158,splitTesto);
		    
		    doc.fromHTML('<b>INDICAZIONE DELLE ATTIVITÀ FORMATIVE PREVISTE   </b>', 9, 186);
		    var obiettivi=$("#attivitaPreviste").val();
		    var splitTesto = doc.splitTextToSize(obiettivi, 180);
		    doc.text(10, 198,splitTesto);
		    
		    doc.fromHTML('<b>INDICAZIONE DELLE MODALITÀ DI SVOLGIMENTO DEL TIROCINIO </b>', 9, 226);
		    var obiettivi=$("#svolgimentoTirocinio").val();
		    var splitTesto = doc.splitTextToSize(obiettivi, 180);
		    doc.text(10, 238,splitTesto);
		    
		    doc.addPage();
		    doc.fromHTML('<b>DURATA DEL TIROCINIO:</b> n. ______ mesi, a decorrere dal <b><em>'+ $("#dataInizioTirocinio").val()+ '</em></b> e fino al ___________', 9, 34);
		    
		    doc.fromHTML("<b>INDICAZIONE DELL' ORARIO DI SVOLGIMENTO DEL TIROCINIO </b>", 9, 48);
		    doc.fromHTML('________________________________________________________________________________', 9, 55);
		    doc.fromHTML('________________________________________________________________________________', 9, 62);
		    
		    doc.fromHTML("<b>INDICAZIONE DELL' ORARIO DI SVOLGIMENTO DEL TIROCINIO </b>", 9, 76);
		    doc.fromHTML("Posizione assicurativa INAIL: Gestione per conto dello Stato", 9, 83);
		    doc.fromHTML("Polizza assicurativa RC ________________________________________________________", 9, 90);
		    doc.fromHTML("Polizza assicurativa Infortuni  ________________________________________________", 9, 97);
		    var testo="Ai sensi dell’art.5 della convenzione Rep.n.___________, a cui fa riferimento il presente progetto formativo, il Soggetto ospitante, in caso di infortunio del tirocinante durante lo svolgimento del tirocinio,  si impegna a segnalare tempestivamente l’evento al Dipartimento di Informatica e al Responsabile dell’Ufficio Stato Giuridico e Formazione dell’Università, al fine di consentire a quest’ultimo di trasmettere la denuncia di infortunio all'INAIL in via telematica entro i tempi previsti dalla normativa vigente (48 ore). Il Responsabile pro tempore dell’Ufficio Stato Giuridico e Formazione dell’Ateneo è il dott. Pasquale Talarico, di cui si indicano di seguito il recapito telefonico e gli indirizzi e-mail a cui far pervenire la segnalazione dell’infortunio con copia della convenzione e del progetto formativo.  Inoltre all’Ufficio Stato Giuridico e Formazione vanno trasmessi, a cura del tirocinante, una copia del certificato medico di infortunio lavorativo e una relazione scritta sulle modalità in cui è avvenuto l’infortunio (orario dell’infortunio, data e ora di abbandono del posto del di lavoro, attività svolta in occasione dell’infortunio e cause dello stesso). Tale documentazione deve essere trasmessa con la massima tempestività per le vie brevi oppure tramite e-mail.";
		   
		    var splitTesto = doc.splitTextToSize(testo, 180);
		    doc.text(10, 115,splitTesto);
		    	
			doc.text("Ufficio Stato Giuridico e Formazione", 105, 185, null, null,  'center');	
			doc.text("Tel. 089 96 6204", 105, 192, null, null,  'center');	
			doc.text("e-mail p.talarico@unisa.it", 105, 199, null, null,  'center');	
			doc.text("e-mail ufgiufor@unisa.it", 105, 206, null, null,  'center');	
			
			doc.fromHTML("<b>EVENTUALI INDENNITÀ DI PARTECIPAZIONE ED EVENTUALI ALTRE AGEVOLAZIONI</b>", 9, 220);
			doc.fromHTML("<b> DI ALTRA NATURA (solo se previsti)</b>", 9, 227);
			
			doc.rect(13, 237, 3, 3)
			doc.fromHTML("Euro __________ (________________________________ in lettere) mensili; ", 17, 234);
			doc.rect(13, 244, 3, 3)
			doc.fromHTML("rimborso spese documentate (vitto, trasporto, altro);", 17, 241);
			doc.rect(13, 251, 3, 3)
			doc.fromHTML("ticket restaurant;  ", 17, 248);
			doc.rect(13, 258, 3, 3)
			doc.fromHTML("altro (specificare) ___________________________", 17, 255);
	        
			doc.addPage();
			
			doc.fromHTML('<b>OBBLIGHI DEL TIROCINANTE </b>', 9, 20);
			doc.text("- Svolgere le attività previste dal presente progetto formativo e di orientamento, rispettando l’ambiente di lavoro; ", 10, 34);
			doc.text("- seguire le indicazioni dei tutori e fare riferimento ad essi per qualsiasi esigenza di tipo organizzativo o altre",10, 41);
			doc.text("altre evenienze;",10,48 );
			
			doc.text("- rispettare gli orari e le regole di comportamento concordati nel presente progetto; ",10,55 );
			doc.text("- rispettare i regolamenti interni e le norme disciplinari in uso presso il soggetto ospitante; ",10,62 );
			doc.text("- rispettare le norme in materia di igiene, sicurezza e salute sui luoghi di lavoro; ",10, 69);
			doc.text("- rispettare gli obblighi di riservatezza, sia durante che dopo lo svolgimento del tirocinio per quanto attiene ai ",10, 76 );
			doc.text("dati, alle informazioni o a tutto quanto acquisito in termini di conoscenze in merito a processi produttivi e",10, 83 );
			doc.text("prodotti/servizi dell’azienda ospitante. ",10, 90 );
			doc.setFontStyle("bold");
			doc.text("AUTORIZZAZIONE AL TRATTAMENTO DEI DATI PERSONALI ED AZIENDALI ED",10, 104 );
			doc.text("ASSUNZIONE DI  RESPONSABILITÀ: ",10, 111 );
			doc.setFontStyle("normal");
			doc.text("Con la sottoscrizione del presente progetto si autorizza il trattamento dei dati personali e del soggetto ",10, 118 );			
			doc.text("ospitante ai sensi e per gli effetti del Decreto Legislativo 30 giugno 2003, n. 196. ",10, 125 );	
			doc.text("Agli effetti delle vigenti leggi e nella consapevolezza delle conseguenze penali connesse a dichiarazioni ",10, 132 );			
			doc.text("mendaci, si dichiara che tutti i dati sopra riportati sono veri.",10, 139 );	
			
			doc.fromHTML('Fisciano <b><em>'  +  gg + mm + aaaa + '</em></b>', 9, 146);
			
			doc.text("Firma e timbro del soggetto promotore ___________________________________________________",10, 166 );			
			doc.text("Firma del tutore designato dal soggetto promotore________________________________________  ",10, 176 );			
			doc.text("Firma e timbro del soggetto ospitante____________________________________________________ ",10, 186 );
			doc.text("Firma del tutore designato dal soggetto ospitante*_______________________________________",10, 196 );
			doc.text("Firma per presa visione ed accettazione del tirocinante__________________________________ ",10, 206 );
			
			doc.setFontSize(10);
			doc.text("*Si ritiene opportuno far firmare il progetto anche ai tutori designati dal soggetto promotore e dal soggetto ospitante, ",10, 280);			
			doc.text("anche se non è obbligatorio, in modo tale che anch’essi siano a conoscenza sin dall’inizio del contenuto del progetto ",10, 284 );			
			doc.text("stesso, evitando successive comunicazioni.",10, 288 );
			
			doc.save('a4.pdf')
		}
