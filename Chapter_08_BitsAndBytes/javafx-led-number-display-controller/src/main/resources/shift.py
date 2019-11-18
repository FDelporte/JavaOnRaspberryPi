# coding=utf-8

import RPi.GPIO as GPIO
import time
import sys

# Define BCM pin numbers
PIN_DATA = 4
PIN_CLOCK = 5
PIN_RELEASE = 6
WAIT_TIME = 0.1

# Initialize pins
GPIO.setmode(GPIO.BCM)
GPIO.setup(PIN_DATA, GPIO.OUT)
GPIO.setup(PIN_CLOCK, GPIO.OUT)
GPIO.setup(PIN_RELEASE, GPIO.OUT)

# Get value from startup argument
inputValue = 0xff

print "Number of arguments: " + str(len(sys.argv))

if len(sys.argv) > 1:
  inputValue = int(sys.argv[1])

print "Sending value: " + str(inputValue)

for y in range(8):
  # Set the DATA pin to the bit y of the given value
  bit = inputValue >> y & 1
  print "Sending bit value " + str(bit) + " to slot " + str(y)

  GPIO.output(PIN_DATA, bit)
  time.sleep(WAIT_TIME)

  # Set the CLOCK pin HIGH to store the DATA value into memory
  GPIO.output(PIN_CLOCK, 1)
  time.sleep(WAIT_TIME)

  # Prepare for the next value by setting the CLOCK pin LOW
  GPIO.output(PIN_CLOCK, 0)
  time.sleep(WAIT_TIME)

# Loop is finished, so all 8 values are sent
# Set the RELEASE pin high so the values from memory are sent to the outputs
GPIO.output(PIN_RELEASE, 1)
time.sleep(WAIT_TIME)

print "Released"

# Set the RELEASE pin LOW so the system is ready for a next value
GPIO.output(PIN_RELEASE, 0)
time.sleep(WAIT_TIME)

# Clean up the GPIO's
GPIO.cleanup()