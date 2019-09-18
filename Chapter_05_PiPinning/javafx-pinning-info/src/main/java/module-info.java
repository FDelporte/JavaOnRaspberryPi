module be.webtechie {
    requires javafx.controls;
    exports be.webtechie;
    opens be.webtechie.pinninginfo.data to javafx.base;
}