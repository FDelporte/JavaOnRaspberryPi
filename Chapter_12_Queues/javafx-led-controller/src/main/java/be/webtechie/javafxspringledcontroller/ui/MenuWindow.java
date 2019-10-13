package be.webtechie.javafxspringledcontroller.ui;

import be.webtechie.javafxspringledcontroller.client.QueueClient;
import be.webtechie.javafxspringledcontroller.event.EventManager;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MenuWindow extends HBox {

    private final Pane pane;
    private final Group home;
    private final Group led;
    private final Group log;

    /**
     * Builds the UI.
     */
    public MenuWindow(EventManager eventManager, QueueClient queueClient) {
        this.setSpacing(25);
        this.getStylesheets().add("styles/style.css");
        this.getStyleClass().add("bg");

        this.getChildren().add(this.getMenu());

        this.pane = new StackPane();
        this.getChildren().add(this.pane);

        this.home = new Group();

        LedControlPanel ledControlPanel = new LedControlPanel(queueClient);
        eventManager.addListener(ledControlPanel);
        this.led = new Group(ledControlPanel);

        QueueMessagesList queueMessagesList = new QueueMessagesList();
        eventManager.addListener(queueMessagesList);
        this.log = new Group(queueMessagesList);

        this.show(this.led);
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
        btHome.setOnAction(e -> this.show(this.home));
        buttons.getChildren().add(btHome);

        final Button btColors = new Button("LED");
        btColors.getStyleClass().add("menuButton");
        btColors.setOnAction(e -> this.show(this.led));
        buttons.getChildren().add(btColors);

        final Button btLog = new Button("Queue");
        btLog.getStyleClass().add("menuButton");
        btLog.setOnAction(e -> this.show(this.log));
        buttons.getChildren().add(btLog);

        return buttons;
    }

    /**
     * Switch to the given screen.
     *
     * @param group The group node to be shown
     */
    private void show(Group group) {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(group);
    }
}
