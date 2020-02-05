module be.webtechie.timeline {
    requires javafx.controls;
    requires be.webtechie.piheaders;
    exports be.webtechie.timeline;
    opens be.webtechie.timeline to javafx.graphics;
}