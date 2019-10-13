package be.webtechie.javafxspringledcontroller.ui.tablecell;

import be.webtechie.javafxspringledcontroller.led.LedCommand;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class ColorView extends TableCell<LedCommand, LedCommand> {

    public ColorView(TableColumn<LedCommand, LedCommand> data) {
        System.out.println(data.toString());
    }
}
