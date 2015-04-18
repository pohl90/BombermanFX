package edu.hm.pohl.BombermanFX.launch;

import edu.hm.pohl.BombermanFX.api.map.BImageProvider;
import edu.hm.pohl.BombermanFX.ui.BBaseScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameStart extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final BorderPane root = new BorderPane();
        final BBaseScreen baseScreen = new BBaseScreen();
        root.setCenter(baseScreen);
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(evet -> baseScreen.exit());
        stage.setTitle("Bomberman FX");
        stage.getIcons().add(BImageProvider.getGameIcon());
        loadCssAndFont(scene);
        stage.show();
    }

    private void loadCssAndFont(final Scene scene) {
        scene.getStylesheets().add(getClass().getResource("default.css").toExternalForm());
        Font.loadFont(getClass().getResourceAsStream("fontawesome-webfont.ttf"), 12);
    }
}
