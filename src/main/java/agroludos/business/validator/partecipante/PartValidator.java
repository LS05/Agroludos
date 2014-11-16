package agroludos.business.validator.partecipante;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.partecipante.PRulesFactory;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TOFactory;

/**
 * Rappresenta il validator per oggetti istanze di classi che implemento l'interfaccia {@link PartecipanteTO}
 * Effettua la validazione in base alle regole specificate in fase di inizializzazione e ottenute con il factory
 * {@link UserRulesFactory}.
 * Nella validazione è utilizzata un'istanza di una classe che implementa l'interfaccia {@link ErrorTO} ottenuta
 * tramite il factory {@link TOFactory}, che contiene gli errori dovuti alla valutazione di ciascuna regola.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PartValidator implements AgroludosValidator{
	
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
	 * inizializza le regole presenti con userRulesFactory.<br>
	 * Viene creata la catena di regole per effettuare la validazione sfruttando il pattern
	 * Chain-of-responsibility che permette di modularizzare e riutilizzare le singole regole.
	 * In particolare la catena per il validator del Manager di Sistema riguarda: nome, email, 
	 * cognome, username, password e stipendio.
	 *  
	 * @param userRulesFactory Factory delle regole per validare un {@link PartecipanteTO}
	 * @param toFactory Factory dei transfer object, usato per ottenere un'istanza di {@link ErrorTO}
	 */
	PartValidator(UserRulesFactory userRulesFactory, PRulesFactory partRulesFactory, TOFactory toFactory){
		this.toFact = toFactory;
		
		this.nomeRule = userRulesFactory.getNomeRule();
		AgroludosRule cognomeRule = userRulesFactory.getCognomeRule();
		AgroludosRule usernameRule = userRulesFactory.getUsernameRule();
		AgroludosRule passwordRule = userRulesFactory.getPasswordRule();
		AgroludosRule emailRule = userRulesFactory.getEmailRule();
		AgroludosRule indirizzoRule = partRulesFactory.getIndirizzoRule();
		AgroludosRule dataSrcRule = partRulesFactory.getDataSrcRule();
		AgroludosRule cfRule = partRulesFactory.getCfRule();
		AgroludosRule srcRule = partRulesFactory.getSrcRule();
		AgroludosRule tesRule = partRulesFactory.getTesRule();
		AgroludosRule sessoRule = partRulesFactory.getSessoRule();
		AgroludosRule dataNascRule = partRulesFactory.getDataNascRule();


		this.nomeRule.setSuccessor(cognomeRule);
		cognomeRule.setSuccessor(cfRule);
		cfRule.setSuccessor(srcRule);
		srcRule.setSuccessor(tesRule);
		tesRule.setSuccessor(dataSrcRule);
		dataSrcRule.setSuccessor(indirizzoRule);
		indirizzoRule.setSuccessor(usernameRule);
		usernameRule.setSuccessor(passwordRule);
		passwordRule.setSuccessor(emailRule);
		emailRule.setSuccessor(sessoRule);
		sessoRule.setSuccessor(dataNascRule);
	}

	/**
	 * Esegue il processo di validazione. La validazione è eseguita sul Transfer Object specificato
	 * come paramentro. In questo caso l'oggetto deve essere istanza di una classe che implementa l'interfaccia
	 * {@link PartecipanteTO} e la validazione è effettuata tramite il metodo validate() a partire dalla prima 
	 * regola della catena.
	 * Se durante il processo di validazione sono presenti degli errori, e quindi il metodo hasErrors ritorna true,
	 * allora viene sollevata una ValidatorException.
	 * 
	 * @throws ValidationException Sollevata se sono presenti degli errori
	 * @param to Oggetto istanza di una classe che implementa {@link PartecipanteTO}
	 * 
	 * @see agroludos.business.validator.rules.AgroludosRule#validate(AgroludosTO, ErrorTO)
	 * @see agroludos.to.ErrorTO#hasErrors()
	 */
	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof PartecipanteTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			PartecipanteTO partecipante = (PartecipanteTO)to;

			this.nomeRule.validate(partecipante, errorTO);

			if(errorTO.hasErrors()){
				throw new ValidationException(errorTO);
			}
		}
	}
}