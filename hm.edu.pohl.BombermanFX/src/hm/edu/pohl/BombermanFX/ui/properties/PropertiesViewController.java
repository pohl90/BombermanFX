package hm.edu.pohl.BombermanFX.ui.properties;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;

public class PropertiesViewController {

	@FXML
	private ColorPicker colorPicker;
	private PropertiesView presenter;

	public void initialize() {

	}

	public void setPresenter(final PropertiesView presenter) {
		this.presenter = presenter;
	}

}
