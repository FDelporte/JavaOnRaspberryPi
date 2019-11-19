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

    private final String scriptFilePath;

    /**
     * Generates the SegmentSelection UI.
     *
     * @param scriptFilePath The full path to the python script copied to the temp directory to execute the segment selection.
     */
    public SegmentSelection(String scriptFilePath) {
        this.scriptFilePath = scriptFilePath;
        System.out.println("Initializing SegmentSelection with script file located copied to " + scriptFilePath);

        this.setPadding(new Insets(10));
        this.setSpacing(25);
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(this.generateLedNumberWithHighlightSelection());
        this.getChildren().add(this.generateBitSelection());
        this.getChildren().add(this.initializeHighLightTypeSelection());

        this.lblValue = new Label();
        this.lblValue.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        this.getChildren().add(this.lblValue);
    }

    /**
     * Generate a black holder with an image to show the A, B, C... segment location and
     * the LED number display component from the Maven dependency be.webtechie.javafx-led-number-display
     *
     * @return {@link HBox} with the image and led number display
     */
    private HBox generateLedNumberWithHighlightSelection() {
        HBox holder = new HBox();
        holder.setSpacing(100);
        holder.setStyle("-fx-background-color: black;");
        holder.setAlignment(Pos.CENTER);

        Image img = new Image("led-number-display.jpg");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        holder.getChildren().add(imgView);

        this.ledNumberDisplay = new LedNumber(DisplaySkin.CLASSIC, Color.BLACK, Color.DARKGRAY, Color.RED);
        this.ledNumberDisplay.setMaxHeight(105);
        holder.getChildren().add(this.ledNumberDisplay);

        return holder;
    }

    /**
     * Generate the holder with 8 checkboxes for the segment selection.
     *
     * return {@link HBox} with the checkboxes
     */
    private HBox generateBitSelection() {
        HBox checkBoxes = new HBox();
        checkBoxes.setSpacing(10);
        checkBoxes.setAlignment(Pos.CENTER);

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

        checkBoxes.getChildren().addAll(this.cbH, this.cbG, this.cbF, this.cbE, this.cbD, this.cbC, this.cbB, this.cbA);

        return checkBoxes;
    }

    /**
     * Initializes the {@link ComboBox} with all the available {@link HighlightType}
     */
    private void initializeHighLightTypeSelection() {
        this.selectHighLightType = new ComboBox<>();
        this.selectHighLightType.getItems().setAll(HighlightType.values());
        this.selectHighLightType.setOnAction(this::updateHighlights);
    }

    /**
     * Change the states of all the {@link ComboBox} to match the selected {@link HighlightType}
     */
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

    /**
     * Change the LED segment component to match the selected {@link ComboBox}
     *
     * @param actionEvent {@link ActionEvent} called from a changed {@link ComboBox}
     */
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

    /**
     * Calculate the value base on the selected {@link ComboBox}, display in the label of the UI and send to the
     * hardware by calling the Python script.
     */
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

        Executor.execute("python " + this.scriptFilePath + " " + value);
    }

    /**
     * Helper function to pad "0" to the left of the given string till the requested string length is reached.
     *
     * @param txt Text to which "0" must be added
     * @param length Requested length
     * @return String with the requested length
     */
    private static String padLeftZero(String txt, int length) {
        StringBuilder rt = new StringBuilder();
        for (int i = 0; i < (length - txt.length()); i++) {
            rt.append("0");
        }
        return rt.append(txt).toString();
    }
}
