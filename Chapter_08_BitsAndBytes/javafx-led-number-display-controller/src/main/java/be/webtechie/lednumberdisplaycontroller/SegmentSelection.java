package be.webtechie.lednumberdisplaycontroller;

import be.webtechie.javafxlednumberdisplay.component.LedNumber;
import be.webtechie.javafxlednumberdisplay.definition.DisplaySkin;
import be.webtechie.javafxlednumberdisplay.definition.HighlightType;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    private Label lblValue;

    public SegmentSelection() {
        this.setPadding(new Insets(10));
        this.setSpacing(25);
        this.setAlignment(Pos.CENTER);

        this.addLedNumberWithHighlightSelection();
        this.addBitSelection();

        this.selectHighLightType = new ComboBox<>();
        this.selectHighLightType.getItems().setAll(HighlightType.values());
        this.selectHighLightType.setOnAction(this::updateHighlights);
        this.getChildren().add(this.selectHighLightType);

        this.lblValue = new Label();
        this.lblValue.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        this.getChildren().add(this.lblValue);
    }

    private void addLedNumberWithHighlightSelection() {
        HBox holder = new HBox();
        holder.setSpacing(100);
        holder.setStyle("-fx-background-color: black;");
        holder.setAlignment(Pos.CENTER);
        this.getChildren().add(holder);

        Image img = new Image("led-number-display.jpg");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        holder.getChildren().add(imgView);

        this.ledNumberDisplay = new LedNumber(DisplaySkin.CLASSIC, Color.BLACK, Color.DARKGRAY, Color.RED);
        this.ledNumberDisplay.setMaxHeight(105);
        holder.getChildren().add(this.ledNumberDisplay);
    }

    private void addBitSelection() {
        HBox bitsSelection = new HBox();
        bitsSelection.setSpacing(10);
        bitsSelection.setAlignment(Pos.CENTER);
        this.getChildren().add(bitsSelection);

        this.cbA = new CheckBox("A (0x01)");
        this.cbA.setOnAction(this::updateFromBits);
        this.cbB = new CheckBox("B (0x02)");
        this.cbB.setOnAction(this::updateFromBits);
        this.cbC = new CheckBox("C (0x04)");
        this.cbC.setOnAction(this::updateFromBits);
        this.cbD = new CheckBox("D (0x08)");
        this.cbD.setOnAction(this::updateFromBits);
        this.cbE = new CheckBox("E (0x10)");
        this.cbE.setOnAction(this::updateFromBits);
        this.cbF = new CheckBox("F (0x20)");
        this.cbF.setOnAction(this::updateFromBits);
        this.cbG = new CheckBox("G (0x40)");
        this.cbG.setOnAction(this::updateFromBits);
        this.cbH = new CheckBox("H (0x80)");
        this.cbH.setOnAction(this::updateFromBits);

        bitsSelection.getChildren()
                .addAll(this.cbH, this.cbG, this.cbF, this.cbE, this.cbD, this.cbC, this.cbB, this.cbA);
    }

    private void updateHighlights(ActionEvent actionEvent) {
        HighlightType highlightType = this.selectHighLightType.getValue();

        if (highlightType == null) {
            return;
        }

        this.ledNumberDisplay.highlight(highlightType, this.cbH.isSelected());

        this.cbA.setSelected(highlightType.isA());
        this.cbB.setSelected(highlightType.isB());
        this.cbC.setSelected(highlightType.isC());
        this.cbD.setSelected(highlightType.isD());
        this.cbE.setSelected(highlightType.isE());
        this.cbF.setSelected(highlightType.isF());
        this.cbG.setSelected(highlightType.isG());

        this.setValue();
    }

    private void updateFromBits(ActionEvent actionEvent) {
        this.selectHighLightType.valueProperty().set(null);

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

        this.setValue();
    }

    private void setValue() {
        int value = (this.cbA.isSelected() ? 0x01 : 0x00)
                + (this.cbB.isSelected() ? 0x02 : 0x00)
                + (this.cbC.isSelected() ? 0x04 : 0x00)
                + (this.cbD.isSelected() ? 0x08 : 0x00)
                + (this.cbE.isSelected() ? 0x10 : 0x00)
                + (this.cbF.isSelected() ? 0x20 : 0x00)
                + (this.cbG.isSelected() ? 0x40 : 0x00)
                + (this.cbH.isSelected() ? 0x80 : 0x00);

        this.lblValue.setText("Value: " + value
                + " = 0x" + padLeftZero(Integer.toHexString(value).toUpperCase(), 2)
                + " = " + padLeftZero(Integer.toBinaryString(value), 8));

        Executor.execute("python shift.py " + value);
    }

    private static String padLeftZero(String txt, int length) {
        StringBuilder rt = new StringBuilder();
        for (int i = 0; i < (length - txt.length()); i++) {
            rt.append("0");
        }
        return rt.append(txt).toString();
    }
}
