import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BitsAndBytes extends VBox {

    private List<CheckBox> checkBoxes = new ArrayList<>();

    private Label resultBits = new Label();
    private Label resultHex = new Label();
    private Label resultSigned = new Label();
    private Label resultUnsigned = new Label();

    private GridPane gridPane = new GridPane();
    
    private int numberOfBits = 8;

    public BitsAndBytes() {
        this.setPadding(new Insets(10, 10, 10, 10));

        this.resultBits.setStyle("-fx-font-family: 'Courier New';");
        this.resultHex.setStyle("-fx-font-family: 'Courier New';");
        this.resultSigned.setStyle("-fx-font-family: 'Courier New';");
        this.resultUnsigned.setStyle("-fx-font-family: 'Courier New';");

        this.gridPane.setHgap(5);
        this.gridPane.setVgap(10);

        HBox bitSelection = new HBox();
        bitSelection.setSpacing(10);

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

        this.gridPane.add(new Label("Signed value"), 1, 5);
        this.gridPane.add(this.resultSigned, 2, 5, this.numberOfBits, 1);

        this.gridPane.add(new Label("Unsigned value"), 1, 6);
        this.gridPane.add(this.resultUnsigned, 2, 6, this.numberOfBits, 1);

        for (int i = 0; i < this.numberOfBits; i++) {
            int powerValue = (int) Math.pow(2, i);
            int column = this.numberOfBits + 1 - i;

            this.gridPane.add(new Label("2^" + i), column, 0);
            this.gridPane.add(new Label(String.valueOf(powerValue)), column, 1);

            CheckBox checkBox = new CheckBox();
            this.checkBoxes.add(checkBox);
            checkBox.setOnAction(this::calculate);
            this.gridPane.add(checkBox, column, 2);
        }

        this.calculate(null);
    }

    private void calculate(ActionEvent actionEvent) {
        int result = 0;

        for (int i = 0; i < this.checkBoxes.size(); i++) {
            if (this.checkBoxes.get(i).isSelected()) {
                result += Math.pow(2, i);
            }
        }

        this.resultBits.setText(addLeadingZeros(Integer.toString(result, 2)));
        this.resultHex.setText(String.format("0x%02x", result));
        this.resultSigned.setText(Integer.toString(result));
        this.resultUnsigned.setText(Integer.toUnsignedString(result));
    }

    private String addLeadingZeros(String str) {
        StringBuilder sb = new StringBuilder(this.numberOfBits);
        while (sb.length() < (this.numberOfBits - str.length())) {
            sb.append("0");
        }

        //append original string
        sb.append(str);

        return sb.toString();
    }
}
