package agroludos.system;

/**
 * Permette la lettura dei valori presenti nel file di proprietà per la gestione delle 
 * regole di validazione, i messaggi di errore di validazione e la lettura delle chiavi
 * dei corrispondenti messaggi di errore.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface RulesErrorsConf extends Conf{
	String getRule(String ruleName);
	String getErrorKey(String errorKey);
	String getErrorMessage(String errorMessage);
}