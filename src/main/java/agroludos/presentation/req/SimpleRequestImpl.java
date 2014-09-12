package agroludos.presentation.req;

class SimpleRequestImpl implements SimpleRequest{
	String commandName;
	
	SimpleRequestImpl(String commandName) {
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
