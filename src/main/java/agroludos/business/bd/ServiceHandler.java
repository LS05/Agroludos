package agroludos.business.bd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.MethodNotFoundException;
import agroludos.exceptions.system.ServiceHandlerException;
import agroludos.exceptions.system.ServiceNotFoundException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.controller.mapper.CommandMap;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

/**
 * La classe modella la strategia di esecuzione del servizio richiesto. 
 * Il servizio è richiamato sfruttando l'AgroludosService settato con il metodo setService(). Il metodo da eseguire
 * è ottenuto utilizzando la classe CommandMap e il risultato dell'esecuizione del metodo sarà settato all'interno della
 * classe AgroResponseContext.
 * CommandMap e AgroResponseContext rappresentano le dipende principali per la classe infatti sono i parametri dell'unico
 * costruttore.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see agroludos.presentation.controller.mapper.CommandMap
 * @see agroludos.presentation.resph.AgroResponseContext
 */
class ServiceHandler {

	/**
	 * Classe di supporto che permette di ottenere un'istanza di classe Method che rappresenta
	 * il servizio da eseguire.
	 * 
	 * @see java.lang.reflect.Method
	 */
	//TODO Cambiare package
	private CommandMap cmdMap;

	/**
	 * Rappresenta una componente del livello di business che fornisce il servizio di business
	 */
	private AgroludosService service;

	/**
	 * Classe che modella la risposta, ovvero il risultato dell'esecuzione del servizio
	 */
	private AgroResponseContext response;

	/**
	 * Il costruttore inizializza le dipendeze della classe
	 * 
	 * @param response Risultato dell'esecuzione del servizio
	 * @param commandMap Permette di ottenere un'istanza di classe Method che rappresenta
	 * il servizio da eseguire.
	 */
	ServiceHandler(AgroResponseContext response, CommandMap commandMap){
		this.response = response;
		this.cmdMap = commandMap;
		this.service = null;
	}

	/**
	 * Metodo che imposta la componente di business su cui eseguire il servizio
	 * 
	 * @param service Componente di business
	 */
	void setService(AgroludosService service){
		this.service = service;
	}

	/**
	 * Metodo che esegue il servizio richiesto. Il nome del servizio è ottenuto tramite il metodo getCommandName().
	 * Dato il nome del servizio e la componente di business che lo eseguirà (rappresentata dall'attributo service),
	 * viene poi utilizzato il metodo getMethod della classe CommandMap che permette di ottenere un'oggetto instanza 
	 * di classe {@link java.lang.reflect.Method}. Tale oggetto rappresenta il metodo da eseguire sulla classe
	 * rappresentata dall'attributo service. 
	 * Ottenuta l'istanza di Method viene utilizzato il suo metodo invoke per la sua esecuzione.
	 * L'esecuzione del metodo avviene in due modi in base al fatto che la richiesta ha dei parametri o meno.
	 * Se il metodo isParam() ritorna true, allora il metodo invoke è chiamato passando i parametri. I parametri 
	 * della richiesta sono ottenuti tramite il metodo getData().
	 * Altrimenti se isParam() ritorna false il metodo invoke è chiamato specificando solo l'istanza della componente 
	 * di business che eseguirà il metodo.
	 * Eseguito il metodo il risultato è salvato tramite setData() della classe AgroResponseContext. Se non è stata
	 * sollevata alcuna eccezione, viene impostata la view su cui effettuare il dispatching utilizzando il metodo
	 * setLogicalViewName(), a cui viene fornito il nome della view per il caso di successo con il metodo getSuccView
	 * della classe Command.
	 * 
	 * TODO Eccezioni non documentate
	 * 
	 * @param command Contiene le informazioni come il nome della classe che deve eseguire il servizio di business,
	 * nome per la view nel caso di successo e nome della view nel caso di fallimento
	 * @param request Contiene i dati della richiesta ovvero: nome del servizio da eseguire o eventuali parametri.
	 * @return Il risultato dell'esecuzione del metodo
	 * @throws ServiceHandlerException
	 * @throws ServiceNotFoundException
	 * 
	 * @see agroludos.presentation.reqh.AgroRequestContext#getCommandName()
	 * @see agroludos.presentation.controller.mapper.CommandMap#getMethod(Class, String)
	 * @see java.lang.reflect.Method#invoke(Object, Object...)
	 * @see agroludos.presentation.reqh.AgroRequestContext#isParam()
	 * @see agroludos.presentation.reqh.AgroRequestContext#getData()
	 * @see agroludos.presentation.resph.AgroResponseContext#setData(Object)
	 * @see agroludos.presentation.resph.AgroResponseContext#setLogicalViewName(String)
	 * @see agroludos.presentation.controller.mapper.Command#getSuccView()
	 * 
	 */
	AgroResponseContext handleService(Command command, AgroRequestContext request) throws ServiceHandlerException, ServiceNotFoundException{

		if(this.service != null){
			Object res = null;
			try {
				Method service = this.cmdMap.getMethod(this.service.getClass(), request.getCommandName());
				service.setAccessible(true);
				if(request.isParam()){	
					res = service.invoke(this.service, request.getData());
				} else {
					res = service.invoke(this.service);
				}
				this.response.setLogicalViewName(command.getSuccView());
				this.response.setData(res);
			} catch (IllegalAccessException e) { 
				throw new ServiceHandlerException(e.getMessage(), e.getCause());
			} catch (IllegalArgumentException e){
				throw new ServiceHandlerException(e.getMessage(), e.getCause());
			}  catch (MethodNotFoundException e) {
				throw new ServiceNotFoundException(request.getCommandName(), e);
			} catch (InvocationTargetException e) {
				this.response.setLogicalViewName(command.getFailView());

				Throwable cause = e.getCause();
				if(cause == null) {
					throw new IllegalStateException(e);
				} else if(cause instanceof ValidationException) {
					ValidationException exception = (ValidationException) cause;
					if( exception.getErrors() != null ) {
						this.response.setData(exception.getErrors());
					} else {
						this.response.setData(exception.getMessage());
					}

				} else{
					this.response.setData(e.getTargetException().getMessage());
				}
			}
		}
		return this.response;
	}
}