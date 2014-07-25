
package agroludos.presentation.controller;

import adisys.server.business.InterventoCompleto;
import adisys.server.business.Rilevazione;
import adisys.server.integration.Infermieri;
import adisys.server.integration.Journaling;
import adisys.server.strumenti.ADISysTableRendererVerifica;
import adisys.server.strumenti.DateFormatConverter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Controller per la verifica dei dati importati da ADISys Mobile
 * Fornisce la visualizzazione direttamente nei componenti di un form
 * e preleva i dati dalla classe Verifica
 *  
 * @author Francesco
 */
public class ADISysControllerVerifica {


	public static void caricaFile(String nomeFile)
	{
		JOptionPane.showMessageDialog(null, Journaling.caricaFile(nomeFile));
	}

	public static void getDatiInfermiere (JLabel labelInfermiere)
	{
		String stringaInfermiere ="Infermiere: ";
		stringaInfermiere += Infermieri.getInfermiere(Journaling.getListaInterventi().get(0).getIDInfermiere()).getNome();
		stringaInfermiere += " " + Infermieri.getInfermiere(Journaling.getListaInterventi().get(0).getIDInfermiere()).getCognome();
		labelInfermiere.setText(stringaInfermiere);
	}

	public static void popolaTabellaAttivita(JTable tabella)
	{
		final String FORMATO_DATA_GUI ="dd/MM/yyyy";
		final String FORMATO_ORA_GUI ="HH:mm:ss";

		//Costruzione della matrice dei dati
		ArrayList<InterventoCompleto> listaInterventi = Journaling.getListaInterventi();

		//TableModel per popolamento tabella
		DefaultTableModel modello = new DefaultTableModel()
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{return false;}
		};

		//Settaggio colonne
		String titoli[] = {"IL", "DALLE","ALLE", "LUOGO","PAZIENTE","GPS","ACCEL."};

		modello.setColumnCount(titoli.length);
		modello.setColumnIdentifiers(titoli);

		if(!listaInterventi.isEmpty())
		{
			for (InterventoCompleto i:listaInterventi)
			{
				String riga[]={
						i.getDataDaFormato(FORMATO_DATA_GUI),
						i.getOraInizioDaFormato(FORMATO_ORA_GUI),
						i.getOraFineDaFormato(FORMATO_ORA_GUI),
						i.getCivico() + "- " +i.getCitta(),
						i.getPaziente().getNome() + " " + i.getPaziente().getCognome(),
						i.getStatoVerificaGPS().toString(),
						i.getStatoVerificaAccelerometro().toString()
				};

				modello.addRow(riga);

			}
		}

		tabella.setModel(modello);

		int colonnaGraficaGPS =5;
		int colonnaGraficaACC =6;


		ADISysTableRendererVerifica renderer = new ADISysTableRendererVerifica(colonnaGraficaGPS,colonnaGraficaACC);
		tabella.setDefaultRenderer(tabella.getColumnClass(0), renderer);

	}


	public static void popolaTabellaLog(JTable tabella, int indiceIntervento)
	{
		ArrayList<InterventoCompleto> listaInterventi = Journaling.getListaInterventi();
		DefaultTableModel modelloLog = new DefaultTableModel()
		{
			@Override
			public boolean isCellEditable(int r, int c)
			{
				return false;
			}
		};

		modelloLog.addColumn("Timestamp");
		modelloLog.addColumn("Latitude");
		modelloLog.addColumn("Longitude");
		modelloLog.addColumn("Altitude");
		modelloLog.addColumn("Accuracy");
		modelloLog.addColumn("Accel X");
		modelloLog.addColumn("Accel Y");
		modelloLog.addColumn("Accel Z");

		int i=0;
		while(listaInterventi.get(indiceIntervento).getLog(i)!=null)
		{
			Rilevazione u =  listaInterventi.get(indiceIntervento).getLog(i);
			Object[] nuovaRiga=
				{
					DateFormatConverter.long2dateString(u.getTimestamp().getTime(),"dd/MM/yyyy-HH:mm:ss"),
					u.getGpsLatitude(),
					u.getGpsLongitude(),
					u.getGpsAltitude(),
					u.getGpsAccuracy(),
					u.getAccX(),
					u.getAccY(),
					u.getAccZ() 
				};
			modelloLog.addRow(nuovaRiga);
			i++;
		}    
		tabella.setModel(modelloLog);


	}

	public static void getListaJournaling(JComboBox<String> cbLista)
	{
		String[] listaFile = Journaling.listaFileJournaling();

		if (listaFile == null) 
			JOptionPane.showMessageDialog(null, "Errore nel caricamento dei file di journaling. (Lista nulla)");
		else if(listaFile.length == 0){
			JOptionPane.showMessageDialog(null, "Nessun file presente!");
		} 
		else
		{
			DefaultComboBoxModel<String> modello= new DefaultComboBoxModel<String>();
			for(String s:listaFile) modello.addElement(s);
			cbLista.setModel(modello);
		}

	}

	public static void showInfoInterventoCompleto(int indiceTabella)
	{
		int indiceLista=indiceTabella;
		//Trace
		System.out.println("ControllerVerifica - Prelievo elemento " + indiceTabella + " -> "+ indiceLista);
		JOptionPane.showMessageDialog(null, Journaling.getIntervento(indiceLista).toString());
	}

}
