package be.webtechie.javafxspringledcontroller;

import be.webtechie.javafxspringledcontroller.client.QueueClient;
import be.webtechie.javafxspringledcontroller.event.EventManager;
import be.webtechie.javafxspringledcontroller.server.WebHandler;
import be.webtechie.javafxspringledcontroller.ui.MenuWindow;
import io.undertow.Undertow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LedControllerApplication extends Application {

	/**
	 * Initialize the {@link EventManager} and {@link QueueClient}, start the webserver and show the UI.
	 */
	@Override
	public void start(Stage stage) {
		EventManager eventManager = new EventManager();
		QueueClient queueClient = null; // new QueueClient(eventManager, "192.168.0.141", "ledCommand");

		Undertow server = Undertow.builder()
				.addHttpListener(8080, "localhost")
				.setHandler(new WebHandler(queueClient))
				.build();
		server.start();

		var scene = new Scene(new MenuWindow(eventManager, queueClient), 1024, 600);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}