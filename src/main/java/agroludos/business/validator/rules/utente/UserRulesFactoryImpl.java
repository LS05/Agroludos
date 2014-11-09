package agroludos.business.validator.rules.utente;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;

class UserRulesFactoryImpl implements UserRulesFactory {

	private StringValidator strValidator;

	UserRulesFactoryImpl(StringValidator strValidator){
		this.strValidator = strValidator;
	}

	@Override
	public AgroludosRule getEmailRule(){
		return new UserEmailRule(this.strValidator);	
	}

	@Override
	public AgroludosRule getNomeRule(){
		return new UserNomeRule();	
	}

	@Override
	public AgroludosRule getCognomeRule(){ 
		return new UserCognomeRule();
	}

	@Override
	public AgroludosRule getUsernameRule(){ 
		return new UserUsernameRule();
	}

	@Override
	public AgroludosRule getPasswordRule(){ 
		return new UserPasswordRule();
	}
}