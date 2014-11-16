package agroludos.business.validator.agrotipo;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.agrotipo.TipoRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.TOFactory;
import agroludos.to.TipiAgroludosTO;

/**
 * Rappresenta il validator per oggetti istanze di classi che implemento l'interfaccia {@link TipiAgroludosTO}
 * Effettua la validazione in base alle regole specificate in fase di inizializzazione e ottenute con il factory
 * {@link TipoRulesFactory}.
 * Nella validazione è utilizzata un'istanza di una classe che implementa l'interfaccia {@link ErrorTO} ottenuta
 * tramite il factory {@link TOFactory}, che contiene gli errori dovuti alla valutazione di ciascuna regola.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class TipoValidator implements AgroludosValidator{
	
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
	 * inizializza le regole presenti con il rulesFactory.
	 * Viene creata la catena di regole per effettuare la validazione sfruttando il pattern
	 * Chain-of-responsibility che permette di modularizzare e riutilizzare le singole regole.
	 * 
	 * @see <a href="http://it.wikipedia.org/wiki/Chain-of-responsibility_pattern">http://it.wikipedia.org/wiki/Chain-of-responsibility_pattern</a>
	 * @see agroludos.business.validator.rules.agrotipo.TipoRulesFactory
	 * @see agroludos.to.TOFactory
	 * 
	 * @param rulesFactory Factory delle regole per validare una competizione
	 * @param toFactory Factory dei transfer object, usato per ottenere un'istanza di {@link ErrorTO}
	 */
	TipoValidator(TipoRulesFactory rulesFactory, TOFactory toFactory){
		this.toFact = toFactory;
		this.nomeRule = rulesFactory.getNomeRule();
	}

	/**
	 * Esegue il processo di validazione. La validazione è eseguita sul Transfer Object specificato
	 * come paramentro. In questo caso l'oggetto deve essere istanza di una classe che implementa l'interfaccia
	 * {@link TipiAgroludosTO} e la validazione è effettuata tramite il metodo validate a partire dalla prima 
	 * regola della catena.
	 * 
	 * Se durante il processo di validazione sono presenti degli errori, e quindi il metodo hasErrors ritorna true,
	 * allora viene sollevata una ValidatorException.
	 * 
	 * @throws ValidationException Sollevata se sono presenti degli errori
	 * 
	 * @see agroludos.business.validator.rules.AgroludosRule#validate(AgroludosTO, ErrorTO)
	 * @see agroludos.to.ErrorTO#hasErrors()
	 */
	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof TipiAgroludosTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			TipiAgroludosTO tipo = (TipiAgroludosTO)to;

			this.nomeRule.validate(tipo, errorTO);

			if(errorTO.hasErrors()){
				throw new ValidationException(errorTO);
			}
		}
	}
}