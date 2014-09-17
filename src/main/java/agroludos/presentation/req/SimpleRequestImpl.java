package agroludos.presentation.req;

class SimpleRequestImpl implements SimpleRequest{
	String commandName;
	private AgroSession session;
	
	SimpleRequestImpl(String commandName, AgroSession session) {
		this.commandName = commandName;
		this.session = session;
	}

	@Override
	public String getCommandName() {
		return this.commandName;
	}

	@Override
	public boolean isParam() {
		return false;
	}	
	
	@Override
	public AgroSession getSession() {
		return this.session;
	}
}
