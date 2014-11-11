package agroludos.to;

class InfoMessageImpl implements InfoMessageTO{
	private static final long serialVersionUID = 1L;
	private String message;

	@Override
	public void setMessage(String message) {
		this.message = message;

	}

	@Override
	public String getMessage() {
		return this.message;
	}

}