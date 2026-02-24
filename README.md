# Getting Started with Java on the Raspberry Pi

In this project you can find all the sources and scripts described in the book ["Getting started with Java on the Raspberry Pi" which is available on Leanpub](https://leanpub.com/gettingstartedwithjavaontheraspberrypi/). For more info, check [webtechie.be/books/](https://webtechie.be/books/).

## Content

Everything is organized according to the chapters in the book. Within each chapter you will find (but not always all of them):

* A README file like this on, with more info about the content of the chapter itself, links to related articles and more.
* One "scripts" directory with scripts e.g. to install Java on the Pi.
* One "schemes" directory with Fritzing files with the wiring of the projects in the chapter.
* "java-..." directories, depending on the content:
	* Only .java-files: code you can run without the need to compile (with Java 11+).
	* A new directory structure with a "pom.xml" in the root: a full Maven Java project.
* "javafx-..." directories: a full Mavan JavaFX project.
* "arduino-..." directories: an Arduino project.

![](cover/cover_2025_java25.jpg)

## Chapters

* 1: Introduction
* 2: Tools used in the book and example projects
* 3: Selecting an IDE for Java development
* 4: Info and history of Java and how to install on the Raspberry Pi
* 5: Information about the Raspberry Pi models
* 6: What is Maven and how we are going to use it
* 7: What is JavaFX and some sample applications
* 8: Bits and bytes and how to use them
* 9: Pi4J the perfect connector between Java and hardware
* 10: Serial communication
* 11: Spring to control the Pi via web services
* 12: Queues allow you to control multiple Pi's and Arduinos
  This document describes all the changes in this sources-project since the book "Getting Started with Java on Rasperry Pi" was published.

## Changes

As Java, Raspberry Pi, and all related topics keep evolving, the book needs continuous updates. Below you can find the list of changes in this repository to keep the sources you find here, and the content of the ebook "in sync."

### 2026

* Chapter 10: Serial communication
  * Added chapter about serial communication as this is no longer included in Pi4J V4
* Chapter 10 became Chapter 11 (Spring)
* Chapter 11 became Chapter 12 (Queues)

### 2025

* Bumping all examples to Java 25 and latest dependencies (WORK-IN-PROGRESS!!!)
* 20250122: Update Chapter 4 (javafx-timeline) and Chapter 5 (javafx-pinning-info) to use the Pi4J library to get the Raspberry Pi BoardModel and GPIO info.

### 2024

* 20240105: Added FXGL example to Chapter 09

### 2021

* 20211214: Bumped pi4j-version to 1.3 to add support for Raspberry Pi 4/400/CM4
* 20211214: Bumped log4j-version to 2.16.0 because of security fix

### 2020

* 20200615: Added [FOOTNOTE_LINKS.md](FOOTNOTE_LINKS.md) with all the links which can be found in the footnotes of the book.
* 20200422: Scheme image updated in Chapter 9 > distance sensor. Resistor needs to be connected to ground instead of power on the breadboard. Was correct on the picture, not the scheme.

