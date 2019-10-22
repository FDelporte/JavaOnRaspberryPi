package be.webtechie.javafxspringledcontroller.server;

import be.webtechie.javafxspringledcontroller.client.QueueClient;
import be.webtechie.javafxspringledcontroller.led.LedCommand;
import be.webtechie.javafxspringledcontroller.led.LedEffect;
import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;
import javafx.scene.paint.Color;

/**
 * Handler for the internal Undertow webserver.
 */
public class WebHandler implements HttpHandler {

    final QueueClient queueClient;

    public WebHandler(QueueClient queueClient) {
        this.queueClient = queueClient;
    }

    /**
     * Whenever a request is received by Undertow webserver, this handler will be called.
     *
     * @param exchange {@link HttpServerExchange}
     */
    @Override
    public void handleRequest(HttpServerExchange exchange) {
        String path = exchange.getRequestPath();

        if (this.queueClient == null) {
            this.returnError(exchange, StatusCodes.INTERNAL_SERVER_ERROR,
                    "Sorry, Message Queue Client is not available! Can not send your request...");
        } else if (path.equals("/red-alert")) {
            this.queueClient.sendMessage(new LedCommand(LedEffect.BLINKING, 1000, Color.RED, Color.WHITE));
            this.returnSuccess(exchange, "RED ALERT message has been sent");
        } else if (path.equals("/all-white")) {
            this.queueClient.sendMessage(new LedCommand(LedEffect.ALL_WHITE, 1000, Color.WHITE, Color.BLACK));
            this.returnSuccess(exchange, "ALL WHITE message has been sent");
        } else if (path.equals("/all-out")) {
            this.queueClient.sendMessage(new LedCommand(LedEffect.ALL_OUT, 1000, Color.BLACK, Color.BLACK));
            this.returnSuccess(exchange, "ALL OUT message has been sent");
        } else {
            this.returnError(exchange, StatusCodes.NOT_FOUND, "The requested path is not available");
        }
    }

    /**
     * Return a success page.
     *
     * @param exchange {@link HttpServerExchange}
     * @param message The message to be shown on the page
     */
    private void returnSuccess(HttpServerExchange exchange, String message) {
        this.returnPage(exchange, StatusCodes.OK, "LED command handled", message);
    }

    /**
     * Return an error page.
     *
     * @param exchange {@link HttpServerExchange}
     * @param statusCode HTTP status code
     * @param message The message to be shown on the page
     */
    private void returnError(HttpServerExchange exchange, int statusCode, String message) {
        this.returnPage(exchange, statusCode, "Error", message);
    }

    /**
     * Create and return the page.
     *
     * @param exchange {@link HttpServerExchange}
     * @param statusCode HTTP status code
     * @param title Title of the page
     * @param content Content of the page
     */
    private void returnPage(HttpServerExchange exchange, int statusCode, String title, String content) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("   <head>\n")
                .append("       <title>").append(title).append("</title>\n")
                .append("       <style>\n")
                .append("           body {\n")
                .append("               font-family: sans-serif; font-size: 14px; background: #133353; color: #ffffff;\n")
                .append("           }\n")
                .append("           a {\n")
                .append("               color: #ffffff; font-size: 18px; font-weight: bold;\n")
                .append("           }\n")
                .append("           ul {\n")
                .append("               list-style: none;  margin 0 0 20px  0; padding: 0;\n")
                .append("           }\n")
                .append("           li {\n")
                .append("               margin: 0 20px 0 0; display: inline;\n")
                .append("           }\n")
                .append("       </style>\n")
                .append("   </head>\n")
                .append("   <body>\n")
                .append("       <ul>\n")
                .append("           <li><a href='/red-alert'>RED ALERT</a></li>\n")
                .append("           <li><a href='/all-white'>ALL WHITE</a></li>\n")
                .append("           <li><a href='/all-out'>ALL OUT</a></li>\n")
                .append("       </ul>\n")
                .append("       ").append(content).append("\n")
                .append("   </body>\n ")
                .append("</html>");
        exchange.getResponseHeaders().put(Headers.CONTENT_LENGTH, sb.toString().length());
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        Sender sender = exchange.getResponseSender();
        exchange.setStatusCode(statusCode);
        sender.send(sb.toString());
    }
}
