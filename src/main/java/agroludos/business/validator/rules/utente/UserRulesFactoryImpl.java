package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

class UserRulesFactoryImpl implements UserRulesFactory {
	private static UserEmailRule emailRule;
	private static UserNomeRule nameRule;
	private static UserCognomeRule cognomeRule;
	private static UserUsernameRule usernameRule;
	private static UserPasswordRule passwordRule;

	@Override
	public AgroludosRule getEmailRule() throws IOException{
		if(emailRule == null)
			emailRule = new UserEmailRule();
		return emailRule;	
	}

	@Override
	public AgroludosRule getNameRule() throws IOException{
		if(nameRule == null)
			nameRule = new UserNomeRule();
		return nameRule;	
	}
	
	@Override
	public AgroludosRule getCognomeRule() throws IOException {
		if(cognomeRule == null)
			cognomeRule = new UserCognomeRule();
		return cognomeRule;
	}
	
	@Override
	public AgroludosRule getUsernameRule() throws IOException {
		if(usernameRule == null)
			usernameRule = new UserUsernameRule();
		return usernameRule;
	}

	@Override
	public AgroludosRule getPasswordRule() throws IOException {
		if(passwordRule == null)
			passwordRule = new UserPasswordRule();
		return passwordRule;
	}
}