package agroludos.to;

public class SuccessImpl implements SuccessTO{
	private static final long serialVersionUID = 1L;
	private String message;

	@Override
	public void setMessagge(String message) {
		this.message = message;
		
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
