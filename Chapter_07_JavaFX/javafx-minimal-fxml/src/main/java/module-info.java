module be.webtechie {
    requires javafx.controls;
    requires javafx.fxml;

    opens be.webtechie to javafx.fxml;
    exports be.webtechie;
}
