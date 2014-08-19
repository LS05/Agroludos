package agroludos.presentation.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

import agroludos.presentation.views.main.MainController;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class Navigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAIN    = "/agroludos/presentation/views/main.fxml";
    public static final String VISTA_LOGIN = "/agroludos/presentation/views/login.fxml";
    public static final String VISTA_CONF = "/agroludos/presentation/views/amministratore/conf_sistema.fxml";

    /** The main application layout controller. */
    private MainController mainController;

    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param mainController the main application layout controller.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public void loadVista(String fxml) {
        try {
            mainController.setView(
                (Node)FXMLLoader.load(
                    Navigator.class.getResource(
                        fxml
                    )
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}