package agroludos.main;

import javafx.stage.Stage;

/**
 * L'interfaccia rappresenta il punto iniziale dell'intero sistema. 
 * Tramite il metodo {@link agroludos.main.App#initialize(Stage stage)} 
 * viene inizializzato lo stage dell'applicazione e viene impostata la schermata 
 * iniziale (o splash screen).
 * Il metodo effettua infine la richiesta al servizio checkConfigurazione. In questo 
 * modo &egrave; possibile stabilire se &egrave; necessario effettuare la configurazione iniziale 
 * oppure procedere con l'autenticazione.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface App {
	
	/**
	 * 
	 * @param stage
	 */
	void initialize(Stage stage);
}
