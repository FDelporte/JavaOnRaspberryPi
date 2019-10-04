package be.webtechie.javafxspringledcontroller.ui;

import eu.hansolo.fx.colorselector.ColorSelector;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LedControlPanel extends HBox {
    private final ColorSelector colorSelector1;
    private final ColorSelector colorSelector2;

    public LedControlPanel() {
        this.colorSelector1 = new ColorSelector();
        this.colorSelector1.setPrefSize(400, 400);
        this.colorSelector1.selectedColorProperty().addListener(o -> System.out.println(colorSelector1.getSelectedColor()));

        this.getChildren().add(this.colorSelector1);

        VBox effectButtons = new VBox();
        effectButtons.getChildren().add(new Button("Static"));
        effectButtons.getChildren().add(new Button("Fading"));
        effectButtons.getChildren().add(new Button("Flashing"));
        effectButtons.getChildren().add(new Button("Running"));
        HBox sliderbox = new HBox();
        sliderbox.getChildren().add(new Label("Speed"));
        sliderbox.getChildren().add(new Slider());
        effectButtons.getChildren().add(sliderbox);
        this.getChildren().add(effectButtons);

        this.colorSelector2 = new ColorSelector();
        this.colorSelector2.setPrefSize(400, 400);
        this.colorSelector2.selectedColorProperty().addListener(o -> System.out.println(colorSelector2.getSelectedColor()));

        this.getChildren().add(this.colorSelector2);
    }
}
