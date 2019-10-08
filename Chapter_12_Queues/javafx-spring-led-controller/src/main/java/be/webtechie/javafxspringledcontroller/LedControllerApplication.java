package be.webtechie.javafxspringledcontroller;

import be.webtechie.javafxspringledcontroller.ui.MenuWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LedControllerApplication extends Application {

	@Override
	public void init() throws Exception {
		// NOP
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new MenuWindow(), 400, 400));
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		// context.close();
	}
}
