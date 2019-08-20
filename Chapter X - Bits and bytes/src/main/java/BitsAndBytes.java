import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class BitsAndBytes extends VBox {

    private List<CheckBox> checkBoxes = new ArrayList<>();

    private Label resultBits = new Label();
    private Label resultHex = new Label();
    private Label resultSigned = new Label();
    private Label resultUnsigned = new Label();

    private GridPane gridPane = new GridPane();
    
    private int numberOfBits = 8;

    BitsAndBytes() {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(10);

        String fontCourier = "-fx-font-family: 'Courier New';";
        this.resultBits.setStyle(fontCourier);
        this.resultHex.setStyle(fontCourier);
        this.resultSigned.setStyle(fontCourier);
        this.resultUnsigned.setStyle(fontCourier);

        this.gridPane.setHgap(5);
        this.gridPane.setVgap(10);

        HBox bitSelection = new HBox();
        bitSelection.setSpacing(10);
        bitSelection.setPadding(new Insets(5));

        bitSelection.getChildren().add(new Label("Select type of value: "));

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton rbInteger = new RadioButton("Integer");
        rbInteger.setToggleGroup(toggleGroup);
        rbInteger.setOnAction(e -> this.buildGrid(32));
        bitSelection.getChildren().add(rbInteger);

        RadioButton rbShort = new RadioButton("Short");
        rbShort.setToggleGroup(toggleGroup);
        rbShort.setOnAction(e -> this.buildGrid(16));
        bitSelection.getChildren().add(rbShort);

        RadioButton rbByte = new RadioButton("Byte");
        rbByte.setToggleGroup(toggleGroup);
        rbByte.setSelected(true);
        rbByte.setOnAction(e -> this.buildGrid(8));
        bitSelection.getChildren().add(rbByte);

        this.getChildren().add(bitSelection);
        this.getChildren().add(this.gridPane);

        this.buildGrid(8);
    }

    private void buildGrid(int numberOfBits) {
        this.numberOfBits = numberOfBits;

        this.gridPane.getChildren().clear();
        this.checkBoxes.clear();
        
        this.gridPane.add(new Label("Power of 2"), 1, 0);
        this.gridPane.add(new Label("Value"), 1, 1);
        this.gridPane.add(new Label("Select"), 1, 2);

        this.gridPane.add(new Label("Bits"), 1, 3);
        this.gridPane.add(this.resultBits, 2, 3, this.numberOfBits, 1);

        this.gridPane.add(new Label("Hex value"), 1, 4);
        this.gridPane.add(this.resultHex, 2, 4, this.numberOfBits, 1);

        this.gridPane.add(new Label("Unsigned value"), 1, 5);
        this.gridPane.add(this.resultUnsigned, 2, 5, this.numberOfBits, 1);

        this.gridPane.add(new Label("Signed value"), 1, 6);
        this.gridPane.add(this.resultSigned, 2, 6, this.numberOfBits, 1);

        for (int i = 0; i < this.numberOfBits; i++) {
            int powerValue = (int) Math.pow(2, i);
            int column = this.numberOfBits + 1 - i;

            this.gridPane.add(new Label("2^" + i), column, 0);
            this.gridPane.add(new Label(this.formatNumber(powerValue)), column, 1);

            CheckBox checkBox = new CheckBox();
            this.checkBoxes.add(checkBox);
            checkBox.setOnAction(this::calculate);
            this.gridPane.add(checkBox, column, 2);
        }

        this.calculate(null);
    }

    private void calculate(ActionEvent actionEvent) {
        StringBuilder bits = new StringBuilder();

        for (int i = this.checkBoxes.size() - 1; i >= 0; i--) {
           bits.append(this.checkBoxes.get(i).isSelected() ? "1" : "0");
        }

        this.resultBits.setText(bits.toString());

        long result = Long.parseLong(bits.toString(), 2);
        this.resultHex.setText(String.format("0x%02x", result));

        /*
                  width                     minimum                         maximum
        SIGNED
        byte:     8 bit                        -127                            +127
        short:   16 bit                     -32 767                         +32 767
        int:     32 bit              -2 147 483 647                  +2 147 483 647
         */

        if (this.numberOfBits == 8) {
            this.resultUnsigned.setText(result + " - Min: 0 - Max: 255");
            this.resultSigned.setText((result >= 0x80 ? 0x80 - result : result) + " - Min: -127 - Max: 127");
        } else if (this.numberOfBits == 16) {
            this.resultUnsigned.setText(this.formatNumber(result) + " - Min: 0 - Max: 65.535");
            this.resultSigned.setText(this.formatNumber(result >= 0x8000 ? 0x8000 - result : result) + " - Min: -32.768 - Max: 32.767");
        } else {
            this.resultUnsigned.setText(this.formatNumber(result) + " - Min: 0 - Max: " + this.formatNumber(4294967295L));
            this.resultSigned.setText(this.formatNumber(result >= 0x80000000L ? 0x80000000L - result : result) + " - Min: " + this.formatNumber(-2147483647L) + " - Max: " + this.formatNumber(2147483647L));
        }
    }

    private String formatNumber(long value) {
        DecimalFormat df = new DecimalFormat("#,##0");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.getDefault()));
        return df.format(value);
    }
}
