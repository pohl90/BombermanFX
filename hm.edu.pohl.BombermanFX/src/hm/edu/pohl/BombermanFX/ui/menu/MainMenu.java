package hm.edu.pohl.BombermanFX.ui.menu;

import hm.edu.pohl.BombermanFX.ui.BBaseScreen;
import hm.edu.pohl.BombermanFX.utils.BFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class MainMenu {

    private final Node mainMenuNode;
    private final MainMenuController controller;

    private final BBaseScreen base;

    public MainMenu(final BBaseScreen baseScreen) {
        this.base = baseScreen;
        final FXMLLoader fxmlLoader = BFXMLLoader.loadFXML(this.getClass(), "mainmenu.fxml");
        mainMenuNode = fxmlLoader.getRoot();
        controller = fxmlLoader.getController();
        controller.setPresenter(this);
    }

    public Node getNode() {
        return mainMenuNode;
    }

    public void startGame() {
        base.startGame();
    }

    public void showOptions() {
        base.showOptions();
    }

    public void exit() {
        base.exit();
    }

}
