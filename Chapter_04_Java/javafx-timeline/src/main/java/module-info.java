module be.webtechie.timeline {
    requires javafx.controls;
    requires javafx.swing;
    requires com.pi4j;
    exports be.webtechie.timeline;
    opens be.webtechie.timeline to javafx.graphics;
}