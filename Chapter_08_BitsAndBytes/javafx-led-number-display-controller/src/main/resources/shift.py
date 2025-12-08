# coding=utf-8

import RPi.GPIO as GPIO
import time
import sys

# Define BCM pin numbers
PIN_DATA = 4
PIN_SRCLK = 5
PIN_RCLK_LATCH = 6
WAIT_TIME = 0.01

# Ignore GPIO warnings as we are re-using pins
GPIO.setwarnings(False)

# Initialize pins
GPIO.setmode(GPIO.BCM)
GPIO.setup(PIN_DATA, GPIO.OUT)
GPIO.setup(PIN_SRCLK, GPIO.OUT)
GPIO.setup(PIN_RCLK_LATCH, GPIO.OUT)

# Get value from startup argument
inputValue = 0xff

print("Number of arguments: " + str(len(sys.argv)))

if len(sys.argv) > 1:
  if sys.argv[1].startswith('0x'):
    # This is used to parse a hex value in format "0x00" into an integer
    inputValue = int(sys.argv[1], 16)
  else:
    # Parsing other values to integer
    inputValue = int(sys.argv[1])

print("Sending value: " + str(inputValue))

# Set the RELEASE pin low so the values are stored in memory
GPIO.output(PIN_RCLK_LATCH, 0)
time.sleep(WAIT_TIME)

for y in range(8):
  # Set the DATA pin to the bit y of the given value
  bit = inputValue >> (7 - y) & 1
  print("Sending bit value " + str(bit) + " to slot " + str(y))

  # Prepare for the next value by setting the CLOCK pin LOW
  GPIO.output(PIN_SRCLK, 0)
  time.sleep(WAIT_TIME)

  # Set the data (high or low state) for the pin y
  GPIO.output(PIN_DATA, bit)
  time.sleep(WAIT_TIME)

  # Set the CLOCK pin HIGH to store the DATA value into memory
  GPIO.output(PIN_SRCLK, 1)
  time.sleep(WAIT_TIME)

# Loop is finished, so all 8 values are sent
# Set the RELEASE pin high so the values from memory are sent to the outputs
GPIO.output(PIN_RCLK_LATCH, 1)
time.sleep(WAIT_TIME)

print("Done")