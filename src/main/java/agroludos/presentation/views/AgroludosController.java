package agroludos.presentation.views;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.to.AgroludosTO;

/**
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public abstract class AgroludosController extends Controller{	
	public abstract void forward(AgroRequest request, AgroResponse response);
	public abstract void initializeView(AgroludosTO mainTO);
	public abstract void initializeView();
}