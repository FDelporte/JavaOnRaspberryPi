# coding=utf-8

from gpiozero import OutputDevice
import time
import sys

# Define BCM pin numbers
PIN_DATA = 4
PIN_SRCLK = 5
PIN_RCLK_LATCH = 6
WAIT_TIME = 0.01

# Initialize pins as output devices
data_pin = OutputDevice(PIN_DATA)
clock_pin = OutputDevice(PIN_SRCLK)
latch_pin = OutputDevice(PIN_RCLK_LATCH)

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
latch_pin.off()
time.sleep(WAIT_TIME)

for y in range(8):
  # Set the DATA pin to the bit y of the given value
  bit = inputValue >> (7 - y) & 1
  print("Sending bit value " + str(bit) + " to slot " + str(y))

  # Prepare for the next value by setting the CLOCK pin LOW
  clock_pin.off()
  sleep(WAIT_TIME)

  # Set the data (high or low state) for the pin y
  if bit:
      data_pin.on()
  else:
      data_pin.off()
  sleep(WAIT_TIME)

  # Set the CLOCK pin HIGH to store the DATA value into memory
  clock_pin.on()
  sleep(WAIT_TIME)

# Loop is finished, so all 8 values are sent
# Set the RELEASE pin high so the values from memory are sent to the outputs
latch_pin.on()
sleep(WAIT_TIME)

print("Done")