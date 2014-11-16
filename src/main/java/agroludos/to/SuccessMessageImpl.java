package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link SuccessMessageTO}.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class SuccessMessageImpl implements SuccessMessageTO{
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