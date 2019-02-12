#include <Wire.h>
#include <Adafruit_NeoPixel.h>

#define PIN 6

Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, PIN, NEO_GRB + NEO_KHZ800);

void setup() {
  Wire.begin(8);                // join i2c bus with address #8
  Wire.onReceive(receiveEvent); // register event
  Serial.begin(9600);           // start serial for output

  strip.begin();
  strip.show();
}

void loop() {
  delay(100);
}

// function that executes whenever data is received from master
// this function is registered as an event, see setup()
void receiveEvent(int howMany) {
  char dat[howMany + 1];
  dat[howMany] = 0;

  int i = 0;

  while (1 < Wire.available()) { // loop through all but the last
    dat[i] = Wire.read(); // receive byte as a character
    i++;
  }

  char* command = strtok(dat, ",");
  while (command != 0)
  {
    // Split the command in two values
    char* separator = strchr(command, ':');
    if (separator != 0)
    {
      // Actually split the string in 2: replace ':' with 0
      *separator = 0;
      int servoId = atoi(command);
      ++separator;
      int position = atoi(separator);

      // Do something with servoId and position
    }
    // Find the next command in input string
    command = strtok(0, "&");
  }
}
