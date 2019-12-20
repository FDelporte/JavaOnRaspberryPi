# JavaFX LCD display controller

This application controls a LCD display.

## Sources

## Building the application

* This is a Maven project
* Open the terminal and run "mvn clean package"

## Running on Raspberry Pi

* To run on Raspberry Pi, use Liberica JDK (with included JavaFX) 
* Copy the generated jar from the target directory "lcd-display-controller-0.0.1-jar-with-dependencies.jar" to your Pi
* Run with "java -jar lcd-display-controller-0.0.1-jar-with-dependencies.jar"

## Wiring


## Inspired by

* [Adafruit - Drive a 16x2 LCD with the Raspberry Pi](https://learn.adafruit.com/drive-a-16x2-lcd-directly-with-a-raspberry-pi/overview)
* [Pi4J LcdExample.java](https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/LcdExample.java)