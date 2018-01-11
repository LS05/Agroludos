package agroludos.business.validator.competizione;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.competizione.CompRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.TOFactory;

/**
 * Rappresenta il validator per oggetti istanze di classi che implemento l'interfaccia {@link CompetizioneTO}
 * Effettua la validazione in base alle regole specificate in fase di inizializzazione e ottenute con il factory
 * {@link CompRulesFactory}.
 * Nella validazione è utilizzata un'istanza di una classe che implementa l'interfaccia {@link ErrorTO} ottenuta
 * tramite il factory {@link TOFactory}, che contiene gli errori dovuti alla valutazione di ciascuna regola.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class CompValidator implements AgroludosValidator{
	
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
	 * In particolare la catena per il validator della competizione riguarda: nome, numero partecipanti,
	 * costo e data.
	 *  
	 * @param rulesFactory Factory delle regole per validare una {@link CompetizioneTO]
	 * @param toFactory Factory dei transfer object, usato per ottenere un'istanza di {@link ErrorTO}
	 */
	CompValidator(CompRulesFactory rulesFactory, TOFactory toFactory){
		this.toFact = toFactory;

		this.nomeRule = rulesFactory.getNomeRule();
		AgroludosRule nPartRule = rulesFactory.getNumPartRule();
		AgroludosRule costoRule = rulesFactory.getCostoRule();
		AgroludosRule dataRule = rulesFactory.getDataRule();

		this.nomeRule.setSuccessor(nPartRule);
		nPartRule.setSuccessor(costoRule);
		costoRule.setSuccessor(dataRule);
	}

	/**
	 * Esegue il processo di validazione. La validazione è eseguita sul Transfer Object specificato
	 * come paramentro. In questo caso l'oggetto deve essere istanza di una classe che implementa l'interfaccia
	 * {@link CompetizioneTO} e la validazione è effettuata tramite il metodo validate() a partire dalla prima 
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
		if(to instanceof CompetizioneTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			CompetizioneTO competizione = (CompetizioneTO)to;

			this.nomeRule.validate(competizione, errorTO);

			if(errorTO.hasErrors()){
				throw new ValidationException(errorTO);
			}
		}
	}
}