module be.webtechie.pi4j.serial {
    requires javafx.controls;
    requires pi4j.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    exports be.webtechie.pi4j.serial;
    exports be.webtechie.pi4j.serial.data;
}