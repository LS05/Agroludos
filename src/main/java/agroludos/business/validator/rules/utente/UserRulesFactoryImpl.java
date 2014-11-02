package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;

class UserRulesFactoryImpl implements UserRulesFactory {

	private StringValidator strValidator;

	UserRulesFactoryImpl(StringValidator strValidator){
		this.strValidator = strValidator;
	}
	
	@Override
	public AgroludosRule getEmailRule() throws IOException{
		return new UserEmailRule(this.strValidator);	
	}

	@Override
	public AgroludosRule getNomeRule() throws IOException{
		return new UserNomeRule(this.strValidator);	
	}
	
	@Override
	public AgroludosRule getCognomeRule() throws IOException { 
		return new UserCognomeRule(this.strValidator);
	}
	
	@Override
	public AgroludosRule getUsernameRule() throws IOException { 
		return new UserUsernameRule();
	}

	@Override
	public AgroludosRule getPasswordRule() throws IOException { 
		return new UserPasswordRule();
	}
}