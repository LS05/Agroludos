package agroludos.system;

public interface RulesErrorsConf {
	String getRule(String ruleName);
	String getErrorKey(String errorKey);
	String getErrorMessage(String errorMessage);
}