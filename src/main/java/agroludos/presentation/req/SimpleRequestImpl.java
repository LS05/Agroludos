package agroludos.presentation.req;

class SimpleRequestImpl implements SimpleRequest{
	private String commandName;
	private String viewName;
	private AgroSession session;
	
	SimpleRequestImpl(String commandName, String viewName, AgroSession session) {
		this.commandName = commandName;
		this.viewName = viewName;	
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
	public String getviewName() {
		return this.viewName;
	}
}
