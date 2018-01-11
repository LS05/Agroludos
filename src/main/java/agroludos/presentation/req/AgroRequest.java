package agroludos.presentation.req;

//getviewName
public interface AgroRequest {
	public String getCommandName();
	
	public boolean isParam();
	
	public AgroSession getSession();
	
	public String getviewName();
}