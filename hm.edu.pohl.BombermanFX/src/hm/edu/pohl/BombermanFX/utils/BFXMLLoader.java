package hm.edu.pohl.BombermanFX.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class BFXMLLoader {
    
    public static FXMLLoader loadFXML(final Class<?> sourceClass, final String source) {
        final FXMLLoader loader = new FXMLLoader(sourceClass.getResource(source));
        try {
            loader.load();
            return loader;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return loader;
    }
}
