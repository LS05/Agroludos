package agroludos.presentation.req;

class SimpleRequest implements AgroRequest{
	String commandName;
	
	SimpleRequest(String commandName) {
		this.commandName = commandName;
	}

	@Override
	public String getCommandName() {
		return this.commandName;
	}

	@Override
	public boolean isParam() {
		return false;
	}	
}
