package be.webtechie.javafxspringledcontroller.ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuWindow extends HBox {

    private final TabPane tabPane;
    private final Tab tabHome;
    private final Tab tabColorPicker;

    /**
     * Builds the UI.
     */
    public MenuWindow() {
        this.getStylesheets().add("styles/style.css");
        this.getStyleClass().add("bg");

        this.getChildren().add(this.getMenu());

        this.tabPane = new TabPane();

        this.tabHome = new Tab("", new Home());
        this.tabPane.getTabs().add(this.tabHome);

        this.tabColorPicker = new Tab("", new LedControlPanel());
        this.tabPane.getTabs().add(this.tabColorPicker);

        this.getChildren().add(this.tabPane);
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

        final Button btColors = new Button("Colors");
        btColors.getStyleClass().add("menuButton");
        btColors.setOnAction(this::showColorPicker);
        buttons.getChildren().add(btColors);

        return buttons;
    }

    /**
     * Switch to the home screen.
     *
     * @param e
     */
    private void showHome(ActionEvent e) {
        this.tabPane.getSelectionModel().select(this.tabHome);
    }

    /**
     * Switch to the color picker screen.
     *
     * @param e
     */
    private void showColorPicker(ActionEvent e) {
        this.tabPane.getSelectionModel().select(this.tabColorPicker);
    }
}
