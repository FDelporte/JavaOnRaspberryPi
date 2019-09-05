package be.webtechie.lednumberdisplaycontroller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        LedType ledtype = new LedType(75, 100, 10);

        ledtype.addSegmentDefinition(new LedSegmentDefinition("A",
                new double[] {0, 10, 50, 60, 50, 10, 0},
                new double[] {5, 0, 0, 5, 10, 10, 5}
        ));
        ledtype.addSegmentDefinition(new LedSegmentDefinition("B",
                new double[] {60, 60, 50, 50, 60},
                new double[] {7, 48, 43, 12, 7}
        ));
        ledtype.addSegmentDefinition(new LedSegmentDefinition("C",
                new double[] {60, 60, 50, 50, 60},
                new double[] {52, 93, 88, 57, 52}
        ));
        ledtype.addSegmentDefinition(new LedSegmentDefinition("D",
                new double[] {0, 10, 50, 60, 50, 10, 0},
                new double[] {95, 90, 90, 95, 100, 100, 95}
        ));
        ledtype.addSegmentDefinition(new LedSegmentDefinition("E",
                new double[] {0, 10, 10, 0, 0},
                new double[] {52, 57, 88, 93, 52}
        ));
        ledtype.addSegmentDefinition(new LedSegmentDefinition("F",
                new double[] {0, 10, 10, 0, 0},
                new double[] {7, 12, 43, 48, 7}
        ));
        ledtype.addSegmentDefinition(new LedSegmentDefinition("G",
                new double[] {0, 10, 50, 60, 50, 10, 0},
                new double[] {50, 45, 45, 50, 55, 55, 50}
        ));
        
        
        var scene = new Scene(new LedNumberDisplay(ledtype, Color.DARKGRAY, Color.RED), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}