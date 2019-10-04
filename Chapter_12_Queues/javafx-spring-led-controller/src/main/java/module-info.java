module be.webtechie.javafxspringledcontroller {
    requires javafx.controls;
    requires org.slf4j;
    requires spring.beans;
    requires spring.core;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    exports be.webtechie.javafxspringledcontroller;
    exports eu.hansolo.fx.colorselector;
    opens be.webtechie.javafxspringledcontroller;
    opens eu.hansolo.fx.colorselector;
}