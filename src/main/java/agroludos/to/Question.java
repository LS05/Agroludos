package agroludos.to;

/**
 * Implementazione dell'interfaccia {@link QuestionTO}. 
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class Question implements QuestionTO{

	private static final long serialVersionUID = 1L;

	private String viewName;
	private String request;
	private String question;
	private AgroludosTO dataTO;


	@Override
	public String getViewName() {
		return viewName;
	}


	@Override
	public void setViewName(String nameView) {
		this.viewName = nameView;
	}


	@Override
	public String getRequest() {
		return request;
	}


	@Override
	public void setRequest(String request) {
		this.request = request;
	}


	@Override
	public AgroludosTO getDataTO() {
		return dataTO;
	}


	@Override
	public void setDataTO(AgroludosTO dataTO) {
		this.dataTO = dataTO;
	}

	@Override
	public void setQuestion(String question) {
		this.question = question;

	}

	@Override
	public String getQuestion() {
		return question;
	}	
}