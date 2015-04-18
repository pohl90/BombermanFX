package edu.hm.pohl.BombermanFX.ui.widgets;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class BToolBar extends HBox {
    
    private static final String STYLE_CLASS = "b-tool-bar";
    
    private final ToolBar toolBarLeft = new ToolBar();
    private final ToolBar toolBarRight = new ToolBar();
    private final StackPane placeHolder = new StackPane();

    public BToolBar() {
        toolBarRight.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(placeHolder, Priority.ALWAYS);
        getChildren().addAll(toolBarLeft, placeHolder, toolBarRight);
        toolBarLeft.getStyleClass().add(STYLE_CLASS);
        toolBarRight.getStyleClass().add(STYLE_CLASS);
    }

    public void addLeft(final Node node) {
        toolBarLeft.getItems().add(0, node);
    }
    
    public void addRight(final Node node) {
        toolBarRight.getItems().add(node);
    }
    
    public void addButtonLeft(final EventHandler<ActionEvent> actionHandler, final Label icon) {
        addLeft(createButton(actionHandler, icon));
    }

    public void addButtonRight(final EventHandler<ActionEvent> actionHandler, final Label icon) {
        addRight(createButton(actionHandler, icon));
    }
    
    private Button createButton(final EventHandler<ActionEvent> actionHandler, final Label icon) {
        final Button button = new Button();
        button.setGraphic(icon);
        button.setOnAction(actionHandler);
        button.setFocusTraversable(false);
        return button;
    }
}
