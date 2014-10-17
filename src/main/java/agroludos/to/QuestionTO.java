package agroludos.to;

public interface QuestionTO extends AgroludosTO, Comparable<OptionalTO>{

	public abstract String getViewName();

	public abstract void setViewName(String viewName);

	public abstract String getRequest();

	public abstract void setRequest(String request);

	public abstract AgroludosTO getDataTO();

	public abstract void setDataTO(AgroludosTO dataTO);
	
	public abstract void setQuestion(String question);

	public abstract String getQuestion();

}