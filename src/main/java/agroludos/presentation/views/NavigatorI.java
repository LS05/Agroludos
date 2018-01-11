package agroludos.presentation.views;

import java.io.IOException;

import javafx.stage.Stage;
import agroludos.exceptions.system.ViewNotFoundException;
import agroludos.to.AgroludosTO;

public interface NavigatorI {

	/**
	 * Inserisce il main stage nella viewsCache
	 * @param mainStage
	 */
	void setMainStage(Stage mainStage);

	/**
	 * Restituisce il controller del nome della view in input
	 * se non è stata creata la crea invocando il metodo {@link #setVista(String)}
	 * @param viewName
	 * @return
	 * @throws ViewNotFoundException
	 * @throws IOException
	 */
	AgroludosController getRequestDispatcher(String viewName)
			throws ViewNotFoundException, IOException;

	/**
	 * Restituisce lo stage di una view tramite il suo nome interrogando {@link ViewsCache}
	 * @param viewName
	 * @return
	 */
	Stage getStage(String viewName);

	/**
	 * invoca il metodo {@link #setVista(String)} per mostrare la view dopodichè chiama il 
	 * metodo initializeView passando un TO che inizializzerà i dati della view
	 * @param viewName
	 * @param mainTO
	 */
	void setVista(String viewName, AgroludosTO mainTO);

	/**
	 * Crea o legge se già esistente una view nella {@link ViewsCache} e la mostra a video
	 * Se si tratta di una view di controllo non effettua lo show()
	 * @param viewName
	 */
	void setVista(String viewName);

	/**
	 * chiude una view
	 * @param viewName
	 */
	void closeVista(String viewName);

}