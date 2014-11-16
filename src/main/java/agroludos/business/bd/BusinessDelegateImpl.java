package agroludos.business.bd;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.BusinessComponentNotFoundException;
import agroludos.exceptions.system.ApplicationException;
import agroludos.exceptions.system.ServiceHandlerException;
import agroludos.exceptions.system.ServiceNotFoundException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

/**
 * La classe modella e implementa un <b>Business Delegate</b>.
 * Il Business Delegate (BD) rappresenta un'astrazione client-side dei servizi di business: 
 * ovvero astrae e nascone i dettagli implementativi dei servizi di business. 
 * Utilizzando il Business Delegate è possibile ridurre l'accoppiamento tra
 * i client e i servizi offerti dal sistema.
 * Il BD utilizza un ServiceLocator che si occupa di restituire un AgroludosService
 * e un ServiceHandler per eseguire il servizio restituito.
 * L'obiettivo della classe è quindi quello di incapsulare l'accesso a un servizio di business,
 * nascondendo i dettagli di implementazione della strategia di lookup (ritrovamento) e di accesso 
 * ad un servizio.
 * Grazie al BD possibili cambiamenti all'API sottostante dei servizi di business sono completamente
 * nascosti sia al BD stesso, ma in particolare sono nascosti ai client.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see agroludos.business.bd.ServiceLocator
 * @see agroludos.business.bd.ServiceHandler
 */

class BusinessDelegateImpl implements BusinessDelegate{
	/**
	 * Il ServiceLocator che si occuperà ti ritrovare il servizio richiesto.
	 */
	private ServiceLocator locator;
	
	/**
	 * Il ServiceHandler che si occuperà di eseguire il servizio richiesto.
	 */
	private ServiceHandler handler;

	/**
	 * Il costruttore che esprime le dipende del BD.
	 * 
	 * @param locator {@link agroludos.business.bd.ServiceLocator}
	 * @param handler {@link agroludos.business.bd.ServiceHandler}
	 */
	BusinessDelegateImpl(ServiceLocator locator, ServiceHandler handler){
		this.locator = locator;
		this.handler = handler;
	}

	/**
	 * Il metodo si occupa  di eseguire il servizio richiesto.
	 * Prima è eseguito il meccanismo di lookup utilizzando il ServiceLocator. Al metodo viene fornito il nome
	 * della classe contenente il servizio tramite getClassName() di Command, restituendo così un riferimento
	 * ad una classe che implementa l'interfaccia AgroludosService.
	 * AgroludosService rappresenta una componente del livello di business che fornisce il servizio di business.
	 * L'AgroludosService viene poi passato al ServiceHandler che grazie al metodo handleService si occuperà di
	 * eseguire il servizio richiesto.
	 * 
	 * @param command Contiene le informazioni come il nome della classe che deve eseguire il servizio di business
	 * @param request Contiene i dati della richiesta ovvero: nome del servizio da eseguire o eventuali parametri.
	 * @throws ApplicationException Sollevata nei seguenti casi
	 * <ul>
	 * 	<li>AgroludosService non è presente</li>
	 * 	<li>Il servizio richiesto non è presente nell'AgroludosService</li>
	 *  <li>L'esecuzione del metodo dell'AgroludosService ha sollevato un'eccezione</li>
	 * </ul>
	 * 
	 * @see agroludos.presentation.controller.mapper.Command#getClassName()
	 * @see agroludos.business.bd.ServiceLocator#lookup(String)
	 * @see agroludos.business.as.AgroludosService
	 */
	@Override
	public AgroResponseContext gestisciServizio(Command command, AgroRequestContext request) throws ApplicationException {
		AgroludosService applicationService = null;
		AgroResponseContext res = null;

		try {
			applicationService = this.locator.lookup(command.getClassName());
			this.handler.setService(applicationService);
			res = this.handler.handleService(command, request);
		} catch (BusinessComponentNotFoundException e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		} catch (ServiceNotFoundException e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		} catch(ServiceHandlerException e){
			// TODO Eccezione di programmazione
			// Sollevata solo in caso in cui ho una IllegalAccess o IllegalArgument
			// nell'handleService quindi da qui il programma deve chiudersi; 
			e.printStackTrace();
		} 

		return res;
	}
}
