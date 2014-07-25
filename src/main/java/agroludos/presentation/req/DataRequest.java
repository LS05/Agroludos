package agroludos.presentation.req;

public class DataRequest extends AgroRequest{

	public DataRequest(String command) {
		super(command);
	}

	public boolean isParameter() {
		return false;
	}

	@Override
	public Object getData(Object key) throws DataFieldException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return null;
	}	
}
