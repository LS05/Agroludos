package agroludos.presentation.fc;

public class APIFactory {
	
	private static APIFactory apiFactoryInstance;

	private APIFactory(){ }

	public static APIFactory getInstance(){
		if(apiFactoryInstance == null)
			apiFactoryInstance = new APIFactory();
		return apiFactoryInstance;
	}
	
	public AdisysAPI getAPI(){
		return new API();
	}
}
