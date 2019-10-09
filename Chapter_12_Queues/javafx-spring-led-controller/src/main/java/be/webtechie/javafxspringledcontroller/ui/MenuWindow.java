package be.webtechie.javafxspringledcontroller.ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuWindow extends HBox {

    private final Pane pane;
    private final Group home;
    private final Group led;

    /**
     * Builds the UI.
     */
    public MenuWindow() {
        this.setSpacing(25);
        this.getStylesheets().add("styles/style.css");
        this.getStyleClass().add("bg");

        this.getChildren().add(this.getMenu());

        this.pane = new StackPane();
        this.getChildren().add(this.pane);

        this.home = new Group();
        this.led = new Group(new LedControlPanel());

        this.showLedController(null);
    }

    /**
     * Builds the button bar.
     *
     * @return
     */
    private VBox getMenu() {
        final VBox buttons = new VBox();
        buttons.setPadding(new Insets(5, 5, 5, 5));
        buttons.setSpacing(5);

        final Button btHome = new Button("Home");
        btHome.getStyleClass().add("menuButton");
        btHome.setOnAction(this::showHome);
        buttons.getChildren().add(btHome);

        final Button btColors = new Button("LED");
        btColors.getStyleClass().add("menuButton");
        btColors.setOnAction(this::showLedController);
        buttons.getChildren().add(btColors);

        return buttons;
    }

    /**
     * Switch to the home screen.
     *
     * @param e
     */
    private void showHome(ActionEvent e) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(this.home);
    }

    /**
     * Switch to the color picker screen.
     *
     * @param e
     */
    private void showLedController(ActionEvent e) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(this.led);
    }
}
