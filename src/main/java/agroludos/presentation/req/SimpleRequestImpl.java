package agroludos.presentation.req;

class SimpleRequestImpl implements SimpleRequest{
	private String commandName;
	private String fromName;
	private AgroSession session;
	
	SimpleRequestImpl(String commandName, String fromName, AgroSession session) {
		this.commandName = commandName;
		this.fromName = fromName;	
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

	@Override
	public String getFromName() {
		return this.fromName;
	}
}
