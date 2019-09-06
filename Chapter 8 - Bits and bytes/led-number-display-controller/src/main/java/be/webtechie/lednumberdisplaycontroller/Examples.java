package be.webtechie.lednumberdisplaycontroller;

import java.time.LocalTime;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Examples extends VBox {

    // Single display with inputs
    private LedNumberDisplay ledNumberDisplay;
    private ComboBox<HighlightType> selectHighLightType;
    private CheckBox enableDot;

    // Clock
    private LedNumberDisplay hoursMajor;
    private LedNumberDisplay hoursMinor;
    private LedNumberDisplay minutesMajor;
    private LedNumberDisplay minutesMinor;
    private LedNumberDisplay secondsMajor;
    private LedNumberDisplay secondsMinor;

    public Examples() {
        this.setPadding(new Insets(10));
        this.setSpacing(25);

        this.addSingleWithInputs();
        this.addClock();
    }

    private void addSingleWithInputs() {
        HBox holder = new HBox();
        holder.setSpacing(10);
        this.getChildren().add(holder);

        this.ledNumberDisplay = new LedNumberDisplay(DisplaySkin.CLASSIC, Color.BLACK, Color.DARKGRAY, Color.RED);
        holder.getChildren().add(this.ledNumberDisplay);

        VBox inputs = new VBox();
        inputs.setSpacing(10);
        holder.getChildren().add(inputs);

        this.selectHighLightType = new ComboBox<>();
        this.selectHighLightType.getItems().setAll(HighlightType.values());
        this.selectHighLightType.setOnAction(this::updateHighlights);
        inputs.getChildren().add(this.selectHighLightType);

        this.enableDot = new CheckBox("Dot");
        this.enableDot.setOnAction(this::updateHighlights);
        inputs.getChildren().add(this.enableDot);
    }

    private void updateHighlights(ActionEvent actionEvent) {
        this.ledNumberDisplay.highlight(this.selectHighLightType.getValue(), this.enableDot.isSelected());
    }

    private void addClock() {
        HBox holder = new HBox();
        holder.setSpacing(5);
        this.getChildren().add(holder);

        this.hoursMajor = new LedNumberDisplay(DisplaySkin.CLASSIC, Color.ORANGE, Color.PINK, Color.BLUE, false);
        this.hoursMinor = new LedNumberDisplay(DisplaySkin.CLASSIC, Color.ORANGE, Color.PINK, Color.BLUE, false);
        this.minutesMajor = new LedNumberDisplay(DisplaySkin.CLASSIC, Color.ORANGE, Color.PINK, Color.BLUE, false);
        this.minutesMinor = new LedNumberDisplay(DisplaySkin.CLASSIC, Color.ORANGE, Color.PINK, Color.BLUE, false);
        this.secondsMajor = new LedNumberDisplay(DisplaySkin.CLASSIC, Color.ORANGE, Color.PINK, Color.BLUE, false);
        this.secondsMinor = new LedNumberDisplay(DisplaySkin.CLASSIC, Color.ORANGE, Color.PINK, Color.BLUE, false);

        holder.getChildren()
                .addAll(this.hoursMajor, this.hoursMinor, this.minutesMajor, this.minutesMinor, this.secondsMajor,
                        this.secondsMinor);

        (new Thread(() -> {
            while (true) {
                LocalTime now = LocalTime.now();

                hoursMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getHour())));
                hoursMinor.highlight(HighlightType.getByNumber(now.getHour() % 10));

                // TODO add separation segments

                minutesMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getMinute())));
                minutesMinor.highlight(HighlightType.getByNumber(now.getMinute() % 10));

                // TODO add separation segments

                secondsMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getSecond())));
                secondsMinor.highlight(HighlightType.getByNumber(now.getSecond() % 10));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.err.println("The clock thread was interrupted");
                }
            }
        })).start();
    }

    private int getMajorDigit(int value) {
        return (value - (value % 10)) / 10;
    }
}
