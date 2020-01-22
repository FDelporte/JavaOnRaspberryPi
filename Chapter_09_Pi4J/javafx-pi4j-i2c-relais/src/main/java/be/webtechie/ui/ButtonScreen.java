package be.webtechie.ui;

import java.io.BufferedReader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ButtonScreen extends VBox {

    private final Button bt1;
    private final Button bt2;
    private final Button bt3;
    private final Button bt4;
    private final Button bt5;
    private final Button bt6;
    private final Button bt7;
    private final Button bt8;

    public ButtonScreen() {
        HBox row1 = new HBox();
        this.getChildren().addAll(row1);

        this.bt1 = new Button("Relais 1");
        this.bt1.setOnAction(this::clicked);
        row1.getChildren().add(this.bt1);
        this.bt2 = new Button("Relais 2");
        this.bt2.setOnAction(this::clicked);
        row1.getChildren().add(this.bt2);
        this.bt3 = new Button("Relais 3");
        this.bt3.setOnAction(this::clicked);
        row1.getChildren().add(this.bt3);
        this.bt4 = new Button("Relais 4");
        this.bt4.setOnAction(this::clicked);
        row1.getChildren().add(this.bt4);

        HBox row2 = new HBox();
        this.getChildren().addAll(row2);

        this.bt5 = new Button("Relais 5");
        this.bt5.setOnAction(this::clicked);
        row2.getChildren().add(this.bt5);
        this.bt6 = new Button("Relais 6");
        this.bt6.setOnAction(this::clicked);
        row2.getChildren().add(this.bt6);
        this.bt7 = new Button("Relais 7");
        this.bt7.setOnAction(this::clicked);
        row2.getChildren().add(this.bt7);
        this.bt8 = new Button("Relais 8");
        this.bt8.setOnAction(this::clicked);
        row2.getChildren().add(this.bt8);
    }

    private void clicked(ActionEvent actionEvent) {
        System.out.println("Clicked: " + actionEvent.getSource());
    }
}
