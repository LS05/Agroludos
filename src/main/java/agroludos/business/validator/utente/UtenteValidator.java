package agroludos.business.validator.utente;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.TOFactory;
import agroludos.to.UtenteTO;

/**
 * Rappresenta il validator per oggetti istanze di classi che implemento l'interfaccia {@link UtenteTO}
 * Effettua la validazione in base alle regole specificate in fase di inizializzazione e ottenute con il factory
 * {@link UserRulesFactory}.
 * Nella validazione è utilizzata un'istanza di una classe che implementa l'interfaccia {@link ErrorTO} ottenuta
 * tramite il factory {@link TOFactory}, che contiene gli errori dovuti alla valutazione di ciascuna regola.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class UtenteValidator implements AgroludosValidator{
	
	/**
	 * Factory dei TransferObject
	 */
	private TOFactory toFact;
	
	/**
	 * Regola per la validazione del nome
	 */
	private AgroludosRule emailRule;

	/**
	 * Il costruttore inizializza l'attributo toFact utilizzando la dipendenza toFactory e
	 * inizializza le regole presenti con userRulesFactory.<br>
	 * Viene creata la catena di regole per effettuare la validazione sfruttando il pattern
	 * Chain-of-responsibility che permette di modularizzare e riutilizzare le singole regole.
	 * In particolare la catena per il validator dell'utente riguarda: nome e email.
	 *  
	 * @param userRulesFactory Factory delle regole per validare un {@link UtenteTO}
	 * @param toFactory Factory dei transfer object, usato per ottenere un'istanza di {@link ErrorTO}
	 */
	UtenteValidator(UserRulesFactory userRulesFactory, TOFactory toFactory){
		this.toFact = toFactory;

		this.emailRule = userRulesFactory.getEmailRule();
		AgroludosRule passwordRule = userRulesFactory.getPasswordRule();

		this.emailRule.setSuccessor(passwordRule);
	}

	/**
	 * Esegue il processo di validazione. La validazione è eseguita sul Transfer Object specificato
	 * come paramentro. In questo caso l'oggetto deve essere istanza di una classe che implementa l'interfaccia
	 * {@link UtenteTO} e la validazione è effettuata tramite il metodo validate() a partire dalla prima 
	 * regola della catena.
	 * Se durante il processo di validazione sono presenti degli errori, e quindi il metodo hasErrors ritorna true,
	 * allora viene sollevata una ValidatorException.
	 * 
	 * @throws ValidationException Sollevata se sono presenti degli errori
	 * @param to Oggetto istanza di una classe che implementa {@link UtenteTO}
	 * 
	 * @see agroludos.business.validator.rules.AgroludosRule#validate(AgroludosTO, ErrorTO)
	 * @see agroludos.to.ErrorTO#hasErrors()
	 */
	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof UtenteTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			UtenteTO utente = (UtenteTO)to;

			this.emailRule.validate(utente, errorTO);

			if(errorTO.hasErrors()){
				throw new ValidationException(errorTO);
			}
		}
	}
}