package agroludos.presentation.views.mds;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;

public class ControllerMdsModificaOptional extends AgroludosController{

	private String viewName;

	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected String getNameView() {
		return this.viewName;
	}

	@Override
	protected void setNameView(String nameView) {
		// TODO Auto-generated method stub
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub

	}

}