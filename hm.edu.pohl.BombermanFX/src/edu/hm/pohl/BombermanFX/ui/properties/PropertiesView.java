package edu.hm.pohl.BombermanFX.ui.properties;

import edu.hm.pohl.BombermanFX.utils.BFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class PropertiesView {

	private final Node root;
	private final PropertiesViewController controller;

	public PropertiesView() {
		final FXMLLoader fxmlLoader = BFXMLLoader.loadFXML(getClass(), "propertiesview.fxml");
		root = fxmlLoader.getRoot();
		controller = fxmlLoader.getController();
		controller.setPresenter(this);
	}

	public Node getNode() {
		return root;
	}
}
