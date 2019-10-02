package be.webtechie.lednumberdisplaycontroller;

import be.webtechie.javafxlednumberdisplay.definition.DisplaySkin;
import be.webtechie.javafxlednumberdisplay.definition.HighlightType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import be.webtechie.javafxlednumberdisplay.component.LedNumber;

public class SegmentSelection extends VBox {

    // Single display with inputs
    private LedNumber ledNumberDisplay;
    private ComboBox<HighlightType> selectHighLightType;

    private CheckBox cbA;
    private CheckBox cbB;
    private CheckBox cbC;
    private CheckBox cbD;
    private CheckBox cbE;
    private CheckBox cbF;
    private CheckBox cbG;
    private CheckBox cbH;

    public SegmentSelection() {
        this.setPadding(new Insets(10));
        this.setSpacing(25);

        this.addLedNumberWithHighlightSelection();
        this.addBitSelection();
    }

    private void addLedNumberWithHighlightSelection() {
        HBox holder = new HBox();
        holder.setSpacing(10);
        this.getChildren().add(holder);

        Image img = new Image("led-number-display.jpg");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        holder.getChildren().add(imgView);

        this.ledNumberDisplay = new LedNumber(DisplaySkin.CLASSIC, Color.BLACK, Color.DARKGRAY, Color.RED);
        holder.getChildren().add(this.ledNumberDisplay);

        VBox inputs = new VBox();
        inputs.setSpacing(10);
        holder.getChildren().add(inputs);

        this.selectHighLightType = new ComboBox<>();
        this.selectHighLightType.getItems().setAll(HighlightType.values());
        this.selectHighLightType.setOnAction(this::updateHighlights);
        inputs.getChildren().add(this.selectHighLightType);
    }

    private void addBitSelection() {
        HBox bitsSelection = new HBox();
        bitsSelection.setSpacing(10);
        this.getChildren().add(bitsSelection);

        this.cbA = new CheckBox("A");
        this.cbA.setOnAction(this::updateFromBits);
        this.cbB = new CheckBox("B");
        this.cbB.setOnAction(this::updateFromBits);
        this.cbC = new CheckBox("C");
        this.cbC.setOnAction(this::updateFromBits);
        this.cbD = new CheckBox("D");
        this.cbD.setOnAction(this::updateFromBits);
        this.cbE = new CheckBox("E");
        this.cbE.setOnAction(this::updateFromBits);
        this.cbF = new CheckBox("F");
        this.cbF.setOnAction(this::updateFromBits);
        this.cbG = new CheckBox("G");
        this.cbG.setOnAction(this::updateFromBits);
        this.cbH = new CheckBox("H");
        this.cbH.setOnAction(this::updateFromBits);

        bitsSelection.getChildren().addAll(
                this.cbA, this.cbB, this.cbC, this.cbD,
                this.cbE, this.cbF, this.cbG, this.cbH
        );
    }

    private void updateHighlights(ActionEvent actionEvent) {
        this.ledNumberDisplay.highlight(this.selectHighLightType.getValue(), this.cbH.isSelected());

        this.cbA.setSelected(this.selectHighLightType.getValue().isA());
        this.cbB.setSelected(this.selectHighLightType.getValue().isB());
        this.cbC.setSelected(this.selectHighLightType.getValue().isC());
        this.cbD.setSelected(this.selectHighLightType.getValue().isD());
        this.cbE.setSelected(this.selectHighLightType.getValue().isE());
        this.cbF.setSelected(this.selectHighLightType.getValue().isF());
        this.cbG.setSelected(this.selectHighLightType.getValue().isG());
    }

    private void updateFromBits(ActionEvent actionEvent) {
        this.ledNumberDisplay.highlight(
                this.cbA.isSelected(),
                this.cbB.isSelected(),
                this.cbC.isSelected(),
                this.cbD.isSelected(),
                this.cbE.isSelected(),
                this.cbF.isSelected(),
                this.cbG.isSelected(),
                this.cbH.isSelected()
        );
    }

    private int getMajorDigit(int value) {
        return (value - (value % 10)) / 10;
    }
}
