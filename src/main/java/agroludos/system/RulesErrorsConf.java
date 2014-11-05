package agroludos.system;

public interface RulesErrorsConf extends Conf{
	String getRule(String ruleName);
	String getErrorKey(String errorKey);
	String getErrorMessage(String errorMessage);
}