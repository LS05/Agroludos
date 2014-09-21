package agroludos.presentation.views.xml;

import javafx.fxml.FXMLLoader;

public class AgroludosMainView extends AgroludosWindow{

	private View mainView;

	AgroludosMainView(View mainView) {
		super(mainView);
		this.mainView = mainView;
	}

	@Override
	public String getUrl() {
		return this.mainView.getUrl();
	}

	@Override
	public String getName() {
		return this.mainView.getName();
	}

	@Override
	public int getWidth() {
		return this.mainView.getWidth();
	}

	@Override
	public int getHeight() {
		return this.mainView.getHeight();
	}

	@Override
	public String getTitle() {
		return this.mainView.getTitle();
	}

	@Override
	public FXMLLoader getLoader() {
		return this.loader;
	}

	@Override
	public boolean isDialog() {
		return false;
	}

	@Override
	public boolean isMainView() {
		return true;
	}

}
