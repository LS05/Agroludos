package agroludos.presentation.controller;

import adisys.server.business.Infermiere;
import adisys.server.business.Intervento;
import adisys.server.business.Paziente;
import adisys.server.business.TipoIntervento;
import adisys.server.integration.Database;
import adisys.server.integration.Infermieri;
import adisys.server.integration.Interventi;
import adisys.server.integration.Pazienti;
import adisys.server.integration.Pianificazione;
import adisys.server.presentation.req.DataFieldException;
import adisys.server.presentation.views.SplashScreen;
import adisys.server.to.InfermiereTO;
import adisys.server.to.PazienteTO;
import adisys.server.to.ServerTOFactory; 
import agroludos.business.bd.ApplicationException;
import agroludos.business.bd.BDFactory;
import agroludos.business.bd.BusinessDelegate;
import agroludos.presentation.reqresh.AdiRequestContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ADISysController extends ApplicationController{

	private static final int PRIMA_RIGA_DTM = 0; //Prma riga del defaulttablemodel
	private static final int COLONNA_NOME_TIPO_DTM = 0; //Prma riga del defaulttablemodel
	private static final int COLONNA_NOTE_TIPO_DTM = 1; //Prma riga del defaulttablemodel


	private static JFrame FMain;
	private static JFrame FPazienti;
	private static JFrame FInfermieri;
	private static JFrame FInterventi;
	private static JFrame FVerifica;
	private static JFrame FEsportazione;
	private static BusinessDelegate adisysBD;
	
	private static ServerTOFactory toFact = ServerTOFactory.getTOFactory();

	public static void avvia(){
		adisysBD = BDFactory.getBD();
		int res = adisysBD.checkConfigurazione();
	}
	
	//VISUALIZZAZIONE FINESTRA
	
	/**
	 * Esce da adisys server
	 */
	public static void chiudiAdisys()
	{
		System.exit(0);
	}
	/**Visualizza la finestra di dialogo dell'editor dei pazienti
	 * @category Visualizzazione
	 */
	public static void visualizzaEditorPazienti()
	{
//		try {
//			FPazienti = factView.createBoundary("main");
//		} catch (BoundaryNotFoundException e) {
//			System.err.println(e.getMessage());
//		}
	}

	/**Visualizza la finestra di dialogo dell'editor degli infemieri
	 * @category Visualizzazione
	 */
	public static void visualizzaEditorInfermieri()
	{
//		try {
//			FInfermieri = factView.createBoundary("editorinfermieri");
//		} catch (BoundaryNotFoundException e) {
//			System.err.println(e.getMessage());
//		}
	}

	/**Visualizza la finestra di dialogo dell'editor degli interventi
	 * @category Visualizzazione
	 */
	public static void visualizzaEditorInterventi()
	{
//		try {
//			FInterventi = factView.createBoundary("editorinterventi");
//		} catch (BoundaryNotFoundException e) {
//			System.err.println(e.getMessage());
//		}
//		System.out.println("metodo: visualizzaEditorInterventi()");
	}

	/**
	 * @category Visualizza la finestra di dialogo per la verifica dei file di journaling
	 */
	public static void visualizzaDialogoVerifica()
	{
//		try {
//			FVerifica = factView.createBoundary("dialogoverifica");
//		} catch (BoundaryNotFoundException e) {
//			System.err.println(e.getMessage());
//		}
	}

	/**
	 * @category Visualizza la finestra di dialogo per l'esportazione della pianificazione di un infermiere
	 */
	public static void visualizzaDialogoEsportazione() {
//		try {
//			FEsportazione = factView.createBoundary("dialogoesportazione");
//		} catch (BoundaryNotFoundException e) {
//			System.err.println(e.getMessage());
//		}
	}

	//RECUPERO MODELLI TABELLE
	/*
	 * I metodi getTabella sono da eliminare in quanto dipendono molto dall'interfaccia.
	 * La scelta migliore è quella di ritornare una lista dei dati che servono per 
	 * utilizzarli all'interno dell'interfaccia stessa. 
	 */

	/**Restituisce Un tableModel per JTable contenente i dati dei pazienti
	 * @category Tabelle
	 */
	public static AbstractTableModel getTabellaPazienti()
	{
		return Pazienti.getTabellaPazienti();
	}

	/**Restituisce Un tableModel per JTable contenente i dati degli infermieri con il numero di interventi
	 * @category Tabelle
	 */
	public static AbstractTableModel getTabellaInfoInfermieri()
	{
		return Infermieri.getTabellaInfoInfermieriConInterventi();
	}

	/**Restituisce Un tableModel per JTable contenente i dati dettagliati degli interventi
	 * @category Tabelle
	 */
	public static AbstractTableModel getTabellaVisualizzazioneInterventi()
	{
		return Interventi.getTabellaVisualizzazioneInterventi();
	}

	/**Restituisce Un tableModel per JTable contenente i dati base degli interventi
	 * @category Tabelle
	 */
	public static AbstractTableModel getTabellaInterventi()
	{
		return Interventi.getTabellaInterventi();
	}

	/**Restituisce Un tableModel per JTable contenente i dati degli infermieri
	 * @category Tabelle
	 */
	public static AbstractTableModel getTabellaInfermieri()
	{
		return Infermieri.getTabellaInfermieri();
	}
	
	public static List<InfermiereTO> getAllInfermieri(){
		return adisysBD.getAllInfermieri();
	}
	
	public static List<PazienteTO> getAllPazienti(){
		return adisysBD.getAllPazienti();
	}
	
	public static PazienteTO getPaziente(AdiRequestContext req){
		PazienteTO paz = toFact.getPazienteTO();
		try {
			paz.setID(req.getStringData("id"));
		} catch (DataFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adisysBD.getPaziente(paz);
	}
	
	public static PazienteTO confermaModificaPaziente(AdiRequestContext req){
		PazienteTO paz = toFact.getPazienteTO();
		
		try {
			paz.setID(req.getStringData("id"));
		} catch (DataFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			paz = adisysBD.modificaPaziente(paz);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		return paz;
	}

	/**Vers 2.0 - Metodo per la lettura dei numeri di cellulare.
	 * 
	 * @param idPaziente
	 * @return 
	 */
	public static DefaultListModel<String> getCellulariPaziente(int idPaziente)
	{
		//Caricamento paziente
		Paziente p = Pazienti.getPaziente(idPaziente);

		//Recupero array cellulari
		Object c[] = p.getCellulari();

		//Creazione modello lista
		DefaultListModel<String> modelloListaCellulari = new DefaultListModel<>();

		//Riempimento modello
		for(int cont=0; cont < c.length; cont++)
		{
			modelloListaCellulari.addElement(String.valueOf(c[cont]));
		}

		//Restituzione Modello
		return modelloListaCellulari;
	}

	public static DefaultTableModel getModelloTipiVuoto()
	{
		//Intestazioni
		String[] colonne={"Tipo","Note"};

		//Creazione modello
		return new DefaultTableModel(colonne,0);
	}

	public static DefaultTableModel getTipiIntervento(int IDIntervento)
	{

		DefaultTableModel modelloTipi = getModelloTipiVuoto();
		//Recupero dati sui tipi di intervento
		ArrayList<TipoIntervento> tipi = Interventi.leggiTipiIntervento(IDIntervento);

		for(TipoIntervento t: tipi)
		{
			String[] valori = {t.getNome(),t.getNote()};
			modelloTipi.addRow(valori);
		}
		return modelloTipi;        
	}

	//OPERAZIONI SU PAZIENTI       

	public static void creaPaziente(){
		//PazienteTO paz = new PazienteTO();
	}

	/**Aggiunge al database un nuovo paziente con i dati forniti
	 * @category Paziente Creazione
	 * @param nome
	 * @param cognome
	 * @param dataNascita (es. "30/08/1945")
	 * @param formatoDataNascita (es. "dd/MM/yyyy")
	 * @param cellulare
	 */
	public static void creaPaziente(String nome, String cognome, String dataNascita, String formatoDataNascita, Object[] cellulari)
	{
		//VERIFICA DEI DATI
		String errLog="";

		errLog += Paziente.verificaNome(nome);
		errLog += Paziente.verificaCognome(cognome);
		errLog += Paziente.verificaDataNascita(dataNascita, formatoDataNascita);

		//Modifica versione 2 - ciclo che controlla tutti i numneri di cellulare
		for(Object cellulare:cellulari)
			errLog += Paziente.verificaCellulare(String.valueOf(cellulare));

		//Verifica errori rilevati
		if (errLog.isEmpty())
		{
			//NESSUN ERRORE - RICHIESTA CONFERMA
			String messaggioConferma = "Creare il paziente " + nome +" " + cognome + "?";
			if (JOptionPane.showConfirmDialog(null, messaggioConferma, "Conferma Aggiunta Paziente", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
			{
				//Conferma accordata - Creazione del paziente
				Paziente p = new Paziente();

				p.setNome(nome);
				p.setCognome(cognome);
				p.setDataNascita(dataNascita, formatoDataNascita);

				//Modifica versione 2: aggiunta dei numeri di cellulare
				for(Object cellulare:cellulari) p.addCellulare(String.valueOf(cellulare));


				//Tentativo di aggiunta al DB
				if (Pazienti.creaPaziente(p))
				{
					//Ok
					String messaggioOK= "Paziente creato con successo!";
					JOptionPane.showMessageDialog(null, messaggioOK);
				}
				else
				{
					//Aggiunta non effettuata
					String messaggioErrore= "Impossibile aggiungere il paziente al DB. \nContattare il supporto tecnico.";
					JOptionPane.showMessageDialog(null, messaggioErrore);
				}
			}
			else
			{
				//Conferma non accordata - avviso
				JOptionPane.showMessageDialog(null, "Operazione annullata", "Aggiunta Annullata", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			//Errori rilevati, visualizza messaggio di errore
			String errMsg= "ERRORI RILEVATI:" + errLog +"\nCorreggere e ritentare l'inserimento";
			JOptionPane.showMessageDialog(null, errMsg, "Impossibile Aggiungere Paziente", JOptionPane.ERROR_MESSAGE);
		}
	}


	//OPERAZIONI SU PAZIENTI       

	/**Aggiunge al database un nuovo paziente con i dati forniti
	 * @category Paziente Creazione
	 * @param nome
	 * @param cognome
	 * @param dataNascita (es. "30/08/1945")
	 * @param formatoDataNascita (es. "dd/MM/yyyy")
	 * @param cellulare
	 */
	public static void modificaPazienteOLD(String ID, String nome, String cognome, String dataNascita, String formatoDataNascita, Object[] cellulari)
	{
		//VERIFICA DEI DATI
		String errLog="";

		errLog += Paziente.verificaCoerenzaID(ID);
		errLog += Paziente.verificaNome(nome);
		errLog += Paziente.verificaCognome(cognome);
		errLog += Paziente.verificaDataNascita(dataNascita, formatoDataNascita);

		//Modifica versione 2 - ciclo che controlla tutti i numneri di cellulare
		for(Object cellulare:cellulari)
			errLog += Paziente.verificaCellulare(String.valueOf(cellulare));

		//verifica esisteza id
		if (errLog.isEmpty())
			if (!Pazienti.idEsistente(Integer.valueOf(ID)))
				errLog = "ERRORE: ID PAZIENTE NON ESISTENTE";

		//Verifica errori rilevati
		if (errLog.isEmpty())
		{
			//NESSUN ERRORE - RICHIESTA CONFERMA
			String messaggioConferma = "Inserire i nuovi dati per " + nome +" " + cognome + "?";
			if (JOptionPane.showConfirmDialog(null, messaggioConferma, "Conferma", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
			{
				//Conferma accordata - Modifica del paziente
				Paziente p = new Paziente();

				p.setID(Integer.valueOf(ID));
				p.setNome(nome);
				p.setCognome(cognome);
				p.setDataNascita(dataNascita, formatoDataNascita);

				//Modifica versione 2 ciclo che aggiunge i numeri di cellulare
				for(Object cellulare:cellulari)
					p.addCellulare(String.valueOf(cellulare));

				//Tentativo di aggiunta al DB
				if (Pazienti.modificaPaziente(p))
				{
					//Ok
					String messaggioOK= "Paziente modificato con successo!";
					JOptionPane.showMessageDialog(null, messaggioOK);
				}
				else
				{
					//Aggiunta non effettuata
					String messaggioErrore= "Impossibile modificare il paziente nel DB. \nContattare il supporto tecnico.";
					JOptionPane.showMessageDialog(null, messaggioErrore);
				}
			}
			else
			{
				//Conferma non accordata - avviso
				String messaggio= "Operazione annullata.";
				JOptionPane.showMessageDialog(null, messaggio);
			}
		}
		else
		{
			//Errori rilevati, visualizza messaggio di errore
			String errMsg= "ERRORI RILEVATI:" + errLog +"\nCorreggere e ritentare la modifica";
			JOptionPane.showMessageDialog(null, errMsg);
		}
	}


	/**
	 * @category Paziente Cancella
	 */
	public static void cancellaPaziente(int selectedID) 
	{
		//RICHIESTA CONFERMA
		String msgConferma= "Cancellare il paziente selezionato?\nSaranno persi anche gli interventi che lo riguardano";
		if (JOptionPane.showConfirmDialog(null, msgConferma, "Conferma cancellazione", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			//CANCELLAZIONE
			Pazienti.cancellaPaziente(selectedID) ;	
		}
		else
			JOptionPane.showMessageDialog(null, "Operazione annullata");

	}

	/**
	 * @category Paziente Cancella
	 */
	public static void cancellaTuttiPazienti() {

		//Preparazione del messaggio di conferma
		String messaggio="Cancellare tutti i pazienti?";
		messaggio += "\nSaranno persi anche tutti i dati relativi agli interventi";

		//Visualizzazione del messaggio
		if(JOptionPane.showConfirmDialog(null, messaggio, "Attenzione!", JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION)
		{
			//Conferma ricevuta
			//Cancellazione
			if(Pazienti.cancellaTutti()) 
				//Conferma cancellazione
				JOptionPane.showMessageDialog(null, "Pazienti cancellati");
			else
				//Avviso cancellazione fallita
				JOptionPane.showMessageDialog(null, "Cancellazione non andata a buon fine.\nControllare i dati");
		}
	}

	//OPERAZIONI SU INFERMIERI    
	/**Metodo per la creazione di un Infermiere
	 * @param newNome - Nome dell'infermiere (stringa)
	 * @param newCognome - Cognome dell'infermiere (stringa)
	 * @category Infermiere Creazione
	 */
	public static void creaInfermiere(String newNome, String newCognome)
	{
		//Verifica dei dati
		String stringaErrore= new String();
		stringaErrore += Infermiere.verificaNome(newNome);
		stringaErrore += Infermiere.verificaCognome(newCognome);


		if (stringaErrore.isEmpty())
		{
			//Creazione nuovo infermiere
			Infermiere nuovoInfermiere = new Infermiere();
			nuovoInfermiere.setNome(newNome);
			nuovoInfermiere.setCognome(newCognome);


			//Aggiunta al database
			if(Infermieri.creaInfermiere(nuovoInfermiere))
			{
				//Caso infermiere aggiunto
				String messaggio= "Infermiere creato con successo";
				JOptionPane.showMessageDialog(null, messaggio);
			}
			else
			{
				//Caso infermiere non aggiunto
				String messaggio= "Si è verificato un errore, impossibile creare l'infermiere";
				JOptionPane.showMessageDialog(null, messaggio);
			}

		}
		else 
		{
			//Caso dati non validi
			JOptionPane.showMessageDialog(null, "Errori rilevati:" + stringaErrore);
		}
	}
	
	
	public static void modificaInfermiere(AdiRequestContext req){
		InfermiereTO inf = toFact.getInfermiereTO();
		
		try {
			inf.setNome(req.getStringData("nome"));
		} catch (DataFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			inf.setCognome(req.getStringData("cognome"));
		} catch (DataFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inf.setID(req.getStringData("id"));
		} catch (DataFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			adisysBD.modificaInfermiere(inf);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param ID
	 * @param newNome
	 * @param newCognome
	 * @category Infermiere Modifica
	 */
	public static void modificaInfermiere(int ID, String newNome, String newCognome)
	{
		//Verifica dei dati
		String stringaErrore= new String();
		if(!Infermieri.idEsistente(ID))
			stringaErrore += "\n- Tentativo di modificare un infermiere con id non esistente (" + ID  +").";
		stringaErrore += Infermiere.verificaNome(newNome);
		stringaErrore += Infermiere.verificaCognome(newCognome);


		if (stringaErrore.isEmpty())
		{
			//Messaggio di conferma
			String domanda= "Modificare i dati dell'infermiere selezionato?";
			if(JOptionPane.showConfirmDialog(null, domanda)==JOptionPane.YES_OPTION)
			{
				//APPLICAZIONE MODIFICHE
				//Creazione oggetto
				Infermiere infermiere = new Infermiere();
				infermiere.setID(ID);
				infermiere.setNome(newNome);
				infermiere.setCognome(newCognome);

				//Applicazione modifiche al database
				if(Infermieri.modificaInfermiere( infermiere))
				{
					//Caso infermiere aggiunto
					String messaggio= "Infermiere modificato con successo";
					JOptionPane.showMessageDialog(null, messaggio);
				}
				else
				{
					//Caso infermiere non aggiunto
					String messaggio= "Si è verificato un errore, impossibile modificare l'infermiere";
					JOptionPane.showMessageDialog(null, messaggio);
				}
			}
		}
		else 
		{
			//Caso dati non validi
			JOptionPane.showMessageDialog(null, "Errori rilevati:" + stringaErrore);
		}
	}

	/**
	 * 
	 * @param idInt
	 * @category Infermiere Cancella
	 */
	public static void cancellaInfermiere(int idInt) {

		//PREPARAZIONE MESSAGGIO DI CONFERMA
		String messaggio="Cancellare l'infermiere selezionato?";
		messaggio += "\nSaranno persi anche tutti i dati relativi agli interventi";
		messaggio += "\nSe ci sono interventi assegnati all'infermiere e' opportuno modificare l'assegnazione.";

		//VISUALIZZAZIONE MESSAGGIO
		if(JOptionPane.showConfirmDialog(null, messaggio, "ATTENZIONE", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			if(Infermieri.cancellaInfermiere(idInt)) 
			{
				//NOTIFICA OPERAZIONE COMPLETATA
				JOptionPane.showMessageDialog(null, "Infermiere cancellato");
			}
			else
			{
				//NOTIFICA OPERAZIONE FALLITA
				JOptionPane.showMessageDialog(null, "Cancellazione non andata a buon fine.\nControllare i dati");
			}
	}

	public static void cancellaTuttiInfermieri() {
		String messaggioConferma= "Confermare la cancellazione di tutti gli infermieri?\nL'operazione non potra' essere annullata e\nsaranno cancellati tutti gli interventi dal database";
		String messaggioCancellazioneOK ="Cancellazione avvenuta con successo.";
		String messaggioCancellazioneErrore = "La cancellazione non e' stata portata a termine \na causa di un errore nel database.";

		if (JOptionPane.showConfirmDialog(null, messaggioConferma, null, JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		{
			//Conferma avvenuta - cancellazione dati
			if (Infermieri.cancellaTutti())
				JOptionPane.showMessageDialog(null, messaggioCancellazioneOK);
			else
				JOptionPane.showMessageDialog(null,messaggioCancellazioneErrore );
		}


	}

	//OPERAZIONI SU INTERVENTI
	/**Riceve i dati relativi ad un nuovo intervento, ne controlla la validita' e 
	 * li invia al database
	 * 
	 * @param idPaziente intero, indice del paziente
	 * @param idInfermiere intero, indice dell'infermiere
	 * @param citta stringa
	 * @param civico stringa
	 * @param cap stringa
	 * @param data stringa nel formato gg/mm/aaaa
	 * @param ora nel formato hh.mm
	 * @param tipiIntervento modello di tabella con tipi di intervento e rispettive note
	 * @category Intervento Creazione
	 */
	public static void creaIntervento(String idPaziente, String idInfermiere, String citta, String civico, String cap, String data, String ora, DefaultTableModel tipiIntervento)
	{
		//VERIFICA VALIDITA' E CORREZIONE DEI DATI 
		String errLog = "";
		errLog += Paziente.verificaCoerenzaID(idPaziente);
		errLog += Infermiere.verificaCoerenzaID(idInfermiere);

		errLog += Intervento.verificaValiditaCitta(citta);
		errLog += Intervento.verificaValiditaCivico(civico);
		errLog += Intervento.verificaValiditaCap(cap); //TODO verificaCAP

		errLog += Intervento.verificaValiditaData(data); //TODO verifica data
		errLog += Intervento.verificaValiditaOra(ora); //TODO verifica ora inizio

		//Set costante per esplorazione del modello dei tipi di intervento
		final int postUltimaRiga=tipiIntervento.getRowCount();

		//Array per il controllo di coerenza e l'inserimento della lista interventi
		ArrayList<TipoIntervento> listaTipi = new ArrayList<>();

		//Inserimento dati nell'array
		for(int i=PRIMA_RIGA_DTM;i<postUltimaRiga;i++) 
			listaTipi.add(new TipoIntervento( String.valueOf(tipiIntervento.getValueAt(i, COLONNA_NOME_TIPO_DTM)),  String.valueOf(tipiIntervento.getValueAt(i, COLONNA_NOTE_TIPO_DTM))));

		//verifica tipi intervento
		errLog += Intervento.verificaCoerenzaTipi(listaTipi);        

		//VERIFICA ESISTENZA PAZIENTE ED INFERMIERE
		if(errLog.isEmpty())
		{
			if(!Pazienti.idEsistente(Integer.valueOf(idPaziente)))
				errLog += "\nATTENZIONE: PAZIENTE N."+idPaziente+" NON ESISTENTE";
			if(!Infermieri.idEsistente(Integer.valueOf(idInfermiere)))
				errLog += "\nATTENZIONE: INFERMIERE M."+idInfermiere+" NON ESISTENTE";
		}

		//Controllo errori
		if(errLog.isEmpty())
		{
			// RICHIESTA CONFERMA
			String messaggio="Creare il nuovo intervento?";
			if(JOptionPane.showConfirmDialog(null,messaggio,"Conferma",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			{
				// INSERIMENTO DATI
				//Creazione nuovo oggetto intervento
				Intervento nuovoIntervento = new Intervento();

				nuovoIntervento.setIDPaziente(idPaziente);
				nuovoIntervento.setIDInfermiere(idInfermiere);

				nuovoIntervento.setCitta(citta);
				nuovoIntervento.setCivico(civico);
				nuovoIntervento.setCap(cap);

				nuovoIntervento.setData(data);
				nuovoIntervento.setOraInizio(ora);

				//TODO Aggiunta tipi intervento
				for(TipoIntervento t:listaTipi) nuovoIntervento.addTipoIntervento(t);

				if (Interventi.creaIntervento(nuovoIntervento))
					JOptionPane.showMessageDialog(null, "Intervento creato con successo.", "OK!!", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "Errore interfaccia database, intervento non creato", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Operazione annullata.");
			}

		}
		else
		{
			// NOTIFICA ERRORE
			JOptionPane.showMessageDialog(null, errLog, "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
		}
	}


	/**Riceve i dati relativi ad un nuovo intervento, ne controlla la validita' e 
	 * li invia al database
	 * @param id intero, indice dell'intervento da modificare
	 * @param idPaziente intero, indice del paziente
	 * @param idInfermiere intero, indice dell'infermiere
	 * @param citta stringa
	 * @param civico stringa
	 * @param cap stringa
	 * @param data stringa nel formato gg/mm/aaaa
	 * @param ora nel formato hh.mm
	 * @param tipiIntervento modello di tabella con tipi di intervento e rispettive note
	 * 
	 * @category Intervento Modifica
	 */
	public static void modificaIntervento(String id, String idPaziente, String idInfermiere, String citta, String civico, String cap, String data, String ora, DefaultTableModel tipiIntervento) 
	{
		//VERIFICA VALIDITA' E CORREZIONE DEI DATI 
		String errLog = "";
		errLog += Paziente.verificaCoerenzaID(idPaziente);
		errLog += Infermiere.verificaCoerenzaID(idInfermiere);

		errLog += Intervento.verificaValiditaCitta(citta);
		errLog += Intervento.verificaValiditaCivico(civico);
		errLog += Intervento.verificaValiditaCap(cap); //TODO verificaCAP

		errLog += Intervento.verificaValiditaData(data); //TODO verifica data
		errLog += Intervento.verificaValiditaOra(ora); //TODO verifica ora inizio

		//Set costante per esplorazione del modello dei tipi di intervento
		final int postUltimaRiga=tipiIntervento.getRowCount();

		//Array per il controllo di coerenza e l'inserimento della lista interventi
		ArrayList<TipoIntervento> listaTipi = new ArrayList<>();

		//Inserimento dati nell'array
		for(int i=PRIMA_RIGA_DTM;i<postUltimaRiga;i++) 
			listaTipi.add(new TipoIntervento( String.valueOf(tipiIntervento.getValueAt(i, COLONNA_NOME_TIPO_DTM)),  String.valueOf(tipiIntervento.getValueAt(i, COLONNA_NOTE_TIPO_DTM))));

		//verifica tipi intervento
		errLog += Intervento.verificaCoerenzaTipi(listaTipi);        


		//VERIFICA ESISTENZA INTERVENTO, PAZIENTE ED INFERMIERE
		if(errLog.isEmpty())
		{
			if(!Interventi.idEsistente(Integer.valueOf(id)))
				errLog += "\nATTENZIONE: INTERVENTO N."+idPaziente+" NON ESISTENTE";
			if(!Pazienti.idEsistente(Integer.valueOf(idPaziente)))
				errLog += "\nATTENZIONE: PAZIENTE N."+idPaziente+" NON ESISTENTE";
			if(!Infermieri.idEsistente(Integer.valueOf(idInfermiere)))
				errLog += "\nATTENZIONE: INFERMIERE M."+idInfermiere+" NON ESISTENTE";
		}

		//Controllo errori
		if(errLog.isEmpty())
		{
			//Richiesta conferma
			String messaggio="Modificare l'intervento?";
			if(JOptionPane.showConfirmDialog(null, messaggio,"Conferma",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			{
				//Creazione nuovo oggetto intervento
				Intervento intervento = new Intervento();

				//INSERIMENTO DATI
				intervento.setID(id);
				intervento.setIDPaziente(idPaziente);
				intervento.setIDInfermiere(idInfermiere);

				intervento.setCitta(citta);
				intervento.setCivico(civico);
				intervento.setCap(cap);

				intervento.setDataFmt(data,"dd/MM/yyyy");
				intervento.setOraInizioFmt(ora,"HH.mm");

				//TODO Aggiunta tipi intervento
				for(TipoIntervento t:listaTipi) intervento.addTipoIntervento(t);

				//APPLICAZIONE MODIFICHE
				if(Interventi.modificaIntervento(intervento))
					JOptionPane.showMessageDialog(null, "Intervento modificato con successo");
				else
					JOptionPane.showMessageDialog(null, "Errore interfaccia database, intervento non modificato");

			}

		}
		else
		{
			//Notifica errore
			JOptionPane.showMessageDialog(null, errLog, "ERRORE", JOptionPane.ERROR_MESSAGE);

		}

	}


	/**
	 * 
	 * @param id
	 * @category Intervento Cancella
	 */
	public static void cancellaIntervento(int id)
	{

		//VERIFICA ESISTENZA INTERVENTO
		if(Interventi.idEsistente(id))
		{			
			//Intervento esistente preparazione messaggio di conferma
			String messaggio="Cancellare l'intervento selezionato?";

			//Visualizzazione conferma
			if(JOptionPane.showConfirmDialog(null, messaggio, "ATTENZIONE", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			{
				//Cancellazione confermata
				if(Interventi.cancellaIntervento(id))
					//Avviso cancellazione andata a buon fine
					JOptionPane.showMessageDialog(null, "Intervento cancellato");
				else
					//Avviso cancellazione fallita
					JOptionPane.showMessageDialog(null, "Cancellazione non andata a buon fine.\nControllare i dati");
			}
		}
		else
			JOptionPane.showMessageDialog(null,"Intevrnnto non presente nel DB - Impossibile cancellare");
	}

	/**
	 * @category Intervento Cancella
	 */
	public static void cancellaTuttiInterventi() {
		//Preparazione messaggio di conferma
		String messaggio="Cancellare tutti gli interventi pianificati?";
		messaggio += "\nI dati non saranno piu' recuperabili";

		//Visualizzazione conferma
		if(JOptionPane.showConfirmDialog(null, messaggio, "ATTENZIONE", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
		{
			//Cancellazione confermata
			if(Interventi.cancellaTutti()) 
				//Avviso cancellazione andata a buon fine
				JOptionPane.showMessageDialog(null, "Interventi cancellati");
			else
				//Avviso cancellazione fallita
				JOptionPane.showMessageDialog(null, "Cancellazione non andata a buon fine.\nControllare i dati");
		}
	}
	public static void showInfoIntervento(int id){
		//Recupero oggetto
		JOptionPane.showMessageDialog(null,Interventi.getIntervento(id).toString());

	}

	public static void showInfoInfermiere(int id){
		JOptionPane.showMessageDialog(null, Infermieri.getInfermiere(id).toString());

	}

	public static void showInfoPaziente(int id){
		JOptionPane.showMessageDialog(null, Pazienti.getPaziente(id).toString());
	}

	public static void esportaPianificazione(int idInfermiere) 
	{
		//Verifica id esistente
		if (Infermieri.idEsistente(idInfermiere))
			JOptionPane.showMessageDialog(null, Pianificazione.esportaPianificazione(idInfermiere));
	}

	@Override
	public Class getResourceClass() {
		return this.getClass();
	}
}
