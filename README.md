# Getting started with Java on Raspberry Pi
In this project you can find all the sources and scripts described in the book ["Getting started with Java on the Raspberry Pi" which is available on Leanpub](https://leanpub.com/gettingstartedwithjavaontheraspberrypi/).

## Content
Everything is organized according to the chapters in the book.

Within each chapter you will find (but not always all of them):

* A README file like this on, with more info about the content of the chapter itself, links to related articles and more.
* One "scripts" directory with scripts e.g. to install Java on the Pi.
* One "schemes" directory with Fritzing files with the wiring of the projects in the chapter.
* "java-..." directories, depending on the content:
	* Only .java-files: code you can run without the need to compile (with Java 11+).
	* A new directory structure with a "pom.xml" in the root: a full Maven Java project.
* "javafx-..." directories: a full Mavan JavaFX project.
* "arduino-..." directories: an Arduino project.

## Chapters
* 1: Introduction
* 2: Tools used in the book and example projects
* 3: Selecting an IDE for Java development
* 4: Info and history of Java and how to install on the Raspberry Pi
* 5: Information about the pins on a Raspberry Pi
* 6: What is Maven and how we are going to use it
* 7: What is JavaFX and some sample applications
* 8: Bits and bytes and how to use them
* 9: Pi4J the perfect connector between Java and hardware
* 10: Spring to control the Pi via web services
* 11: Queues allow you to control multiple Pi's and Arduinos