package agroludos.business.validator.mdc;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.mdc.MdcRulesFactory;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TOFactory;

/**
 * Rappresenta il validator per oggetti istanze di classi che implemento l'interfaccia {@link ManagerDiCompetizioneTO}
 * Effettua la validazione in base alle regole specificate in fase di inizializzazione e ottenute con il factory
 * {@link UserRulesFactory}.
 * Nella validazione è utilizzata un'istanza di una classe che implementa l'interfaccia {@link ErrorTO} ottenuta
 * tramite il factory {@link TOFactory}, che contiene gli errori dovuti alla valutazione di ciascuna regola.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class MdcValidator implements AgroludosValidator{
	/**
	 * Factory dei TransferObject
	 */
	private TOFactory toFact;
	
	/**
	 * Regola per la validazione del nome
	 */
	private AgroludosRule nomeRule;
	
	/**
	 * Il costruttore inizializza l'attributo toFact utilizzando la dipendenza toFactory e
	 * inizializza le regole presenti con il rulesFactory.<br>
	 * Viene creata la catena di regole per effettuare la validazione sfruttando il pattern
	 * Chain-of-responsibility che permette di modularizzare e riutilizzare le singole regole.
	 * In particolare la catena per il validator del Manager di Competizione riguarda: nome, email, 
	 * cognome, username, password e stipendio.
	 *  
	 * @param userRulesFactory Factory delle regole per validare un {@link ManagerDiCompetizioneTO}
	 * @param toFactory Factory dei transfer object, usato per ottenere un'istanza di {@link ErrorTO}
	 */
	MdcValidator(UserRulesFactory userRulesFactory, MdcRulesFactory mdcRulesFactory, TOFactory toFactory) {
		this.toFact = toFactory;

		this.nomeRule = userRulesFactory.getNomeRule();
		AgroludosRule emailRule = userRulesFactory.getEmailRule();
		AgroludosRule cognomeRule = userRulesFactory.getCognomeRule();
		AgroludosRule usernameRule = userRulesFactory.getUsernameRule();
		AgroludosRule passwordRule = userRulesFactory.getPasswordRule();
		AgroludosRule stipendioRule = mdcRulesFactory.getStipendioRule();

		this.nomeRule.setSuccessor(cognomeRule);
		cognomeRule.setSuccessor(emailRule);
		emailRule.setSuccessor(usernameRule);
		usernameRule.setSuccessor(passwordRule);
		passwordRule.setSuccessor(stipendioRule);
	}

	/**
	 * Esegue il processo di validazione. La validazione è eseguita sul Transfer Object specificato
	 * come paramentro. In questo caso l'oggetto deve essere istanza di una classe che implementa l'interfaccia
	 * {@link ManagerDiCompetizioneTO} e la validazione è effettuata tramite il metodo validate() a partire dalla prima 
	 * regola della catena.
	 * Se durante il processo di validazione sono presenti degli errori, e quindi il metodo hasErrors ritorna true,
	 * allora viene sollevata una ValidatorException.
	 * 
	 * @throws ValidationException Sollevata se sono presenti degli errori
	 * @param to Oggetto istanza di una classe che implementa {@link CompetizioneTO}
	 * @see agroludos.business.validator.rules.AgroludosRule#validate(AgroludosTO, ErrorTO)
	 * @see agroludos.to.ErrorTO#hasErrors()
	 */
	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof ManagerDiCompetizioneTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			ManagerDiCompetizioneTO mdc = (ManagerDiCompetizioneTO)to;

			this.nomeRule.validate(mdc, errorTO);

			if(errorTO.hasErrors()){
				throw new ValidationException(errorTO);
			}
		}
	}
}