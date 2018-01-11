package agroludos.business.validator.iscrizione;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.iscrizione.IscrRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ErrorTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.TOFactory;

/**
 * Rappresenta il validator per oggetti istanze di classi che implemento l'interfaccia {@link IscrizioneTO}
 * Effettua la validazione in base alle regole specificate in fase di inizializzazione e ottenute con il factory
 * {@link IscrRulesFactory}.
 * Nella validazione è utilizzata un'istanza di una classe che implementa l'interfaccia {@link ErrorTO} ottenuta
 * tramite il factory {@link TOFactory}, che contiene gli errori dovuti alla valutazione di ciascuna regola.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class IscrValidator implements AgroludosValidator{
	/**
	 * Factory dei TransferObject
	 */
	private TOFactory toFact;
	
	/**
	 * Regola per la validazione del nome
	 */
	private AgroludosRule dataRule;

	/**
	 * Il costruttore inizializza l'attributo toFact utilizzando la dipendenza toFactory e
	 * inizializza le regole presenti con il rulesFactory.<br>
	 * Viene creata la catena di regole per effettuare la validazione sfruttando il pattern
	 * Chain-of-responsibility che permette di modularizzare e riutilizzare le singole regole.
	 * In particolare la catena per il validator dell'iscrizione riguarda: costo e data.
	 *  
	 * @param rulesFactory Factory delle regole per validare un {@link ManagerDiSistemaTO}
	 * @param toFactory Factory dei transfer object, usato per ottenere un'istanza di {@link ErrorTO}
	 */
	IscrValidator(IscrRulesFactory rulesFactory, TOFactory toFactory) {
		this.toFact = toFactory;

		this.dataRule = rulesFactory.getDataRule();
		AgroludosRule costoRule = rulesFactory.getCostoRule();

		this.dataRule.setSuccessor(costoRule);
	}

	/**
	 * Esegue il processo di validazione. La validazione è eseguita sul Transfer Object specificato
	 * come paramentro. In questo caso l'oggetto deve essere istanza di una classe che implementa l'interfaccia
	 * {@link IscrizioneTO} e la validazione è effettuata tramite il metodo validate() a partire dalla prima 
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
		if(to instanceof IscrizioneTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			IscrizioneTO iscrizione = (IscrizioneTO)to;

			this.dataRule.validate(iscrizione, errorTO);

			if(errorTO.hasErrors()){
				throw new ValidationException(errorTO);
			}
		}
	}
}