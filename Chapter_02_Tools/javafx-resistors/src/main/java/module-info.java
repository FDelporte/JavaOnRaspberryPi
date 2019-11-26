module be.webtechie.resistors {
    requires javafx.controls;
    requires be.webtechie.resistorcalculator;
    opens be.webtechie.resistors to javafx.graphics;
}