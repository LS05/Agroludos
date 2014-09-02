package agroludos.presentation.req;

class SimpleRequest extends AgroRequest{
	
	public SimpleRequest(String command) {
		super(command, false);
	}

	public boolean isParameter() {
		return false;
	}	
}
