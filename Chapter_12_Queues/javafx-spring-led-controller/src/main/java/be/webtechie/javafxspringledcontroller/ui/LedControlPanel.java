package be.webtechie.javafxspringledcontroller.ui;

import be.webtechie.javafxspringledcontroller.led.LedCommand;
import be.webtechie.javafxspringledcontroller.led.LedEffect;
import eu.hansolo.fx.colorselector.ColorSelector;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LedControlPanel extends HBox {
    private final ColorSelector colorSelector1;
    private final ColorSelector colorSelector2;

    private final Button btStatic;
    private final Button btStaticFade;
    private final Button btBlinking;
    private final Button btRunning;
    private final Button btStaticRainbow;
    private final Button btFadingRainbow;
    private final Button btClear;

    private final Slider slider;

    private LedEffect selectedLedEffect;

    public LedControlPanel() {
        this.setSpacing(25);

        VBox colorSelectors = new VBox();
        colorSelectors.setSpacing(25);
        this.getChildren().add(colorSelectors);

        this.colorSelector1 = new ColorSelector();
        this.colorSelector1.setPrefSize(250, 250);
        this.colorSelector1.selectedColorProperty().addListener(e -> this.sendMessage());
        this.colorSelector1.setSelectedColor(Color.BLUE);
        colorSelectors.getChildren().add(this.colorSelector1);

        this.colorSelector2 = new ColorSelector();
        this.colorSelector2.setPrefSize(250, 250);
        this.colorSelector2.selectedColorProperty().addListener(e -> this.sendMessage());
        this.colorSelector1.setSelectedColor(Color.RED);
        colorSelectors.getChildren().add(this.colorSelector2);

        GridPane effectButtons = new GridPane();
        effectButtons.setHgap(10);
        effectButtons.setVgap(10);
        this.getChildren().add(effectButtons);

        this.btStatic = new Button("Fixed");
        this.btStatic.getStyleClass().add("ledButton");
        this.btStatic.setOnAction(e -> this.setEffect(LedEffect.STATIC));
        effectButtons.add(this.btStatic, 0, 0);

        this.btStaticFade = new Button("Fade");
        this.btStaticFade.getStyleClass().add("ledButton");
        this.btStaticFade.setOnAction(e -> this.setEffect(LedEffect.STATIC_FADE));
        effectButtons.add(this.btStaticFade, 1, 0);

        this.btBlinking = new Button("Blink");
        this.btBlinking.getStyleClass().add("ledButton");
        this.btBlinking.setOnAction(e -> this.setEffect(LedEffect.BLINKING));
        effectButtons.add(this.btBlinking, 0, 1);

        this.btRunning = new Button("Run");
        this.btRunning.getStyleClass().add("ledButton");
        this.btRunning.setOnAction(e -> this.setEffect(LedEffect.RUNNING));
        effectButtons.add(this.btRunning, 1, 1);

        this.btStaticRainbow = new Button("Fixed rainbow");
        this.btStaticRainbow.getStyleClass().add("ledButton");
        this.btStaticRainbow.setOnAction(e -> this.setEffect(LedEffect.STATIC_RAINBOW));
        effectButtons.add(this.btStaticRainbow, 0, 2);

        this.btFadingRainbow = new Button("Fading rainbow");
        this.btFadingRainbow.getStyleClass().add("ledButton");
        this.btFadingRainbow.setOnAction(e -> this.setEffect(LedEffect.FADING_RAINBOW));
        effectButtons.add(this.btFadingRainbow, 1, 2);

        this.btClear = new Button("Clear");
        this.btClear.getStyleClass().add("ledButtonFullWidth");
        this.btClear.setOnAction(e -> this.setEffect(LedEffect.ALL_OUT));
        effectButtons.add(this.btClear, 0, 3, 2, 1);

        effectButtons.add(new Label(""), 0, 4, 2, 1);

        Label lblSpeed = new Label("Speed");
        lblSpeed.getStyleClass().add("ledSpeed");
        GridPane.setHalignment(lblSpeed, HPos.CENTER);
        effectButtons.add(lblSpeed, 0, 5, 2, 1);

        this.slider = new Slider();
        this.slider.setShowTickLabels(true);
        this.slider.setShowTickMarks(true);
        this.slider.valueProperty().addListener((observable, oldValue, newValue) -> this.sendMessage());
        effectButtons.add(this.slider, 0, 6, 2, 1);

        this.setEffect(LedEffect.ALL_OUT);
    }

    private void setEffect(LedEffect ledEffect) {
        this.selectedLedEffect = ledEffect;

        this.colorSelector1.setDisable(!ledEffect.useColor1());
        this.colorSelector2.setDisable(!ledEffect.useColor2());
        this.slider.setDisable(!ledEffect.useSpeed());
        this.slider.setMin(ledEffect.getMinimumSpeed());
        this.slider.setMax(ledEffect.getMaximumSpeed());

        this.sendMessage();
    }

    private void sendMessage() {
        LedCommand ledCommand = new LedCommand(
                this.selectedLedEffect,
                (int) this.slider.getValue(),
                this.colorSelector1.getSelectedColor(),
                this.colorSelector2.getSelectedColor()
        );

        System.out.println(ledCommand.toCommandString());
    }
}
