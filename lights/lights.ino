#include <Wire.h>
#include <Adafruit_NeoPixel.h>

#define PIN 6

Adafruit_NeoPixel strip = Adafruit_NeoPixel(32, PIN, NEO_GRB + NEO_KHZ800);

void setup() {
  Wire.begin(4);                // join i2c bus with address #8
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
    char c = Wire.read(); // receive byte as a character
    //Serial.print(c);         // print the character
    dat[i] = c;
    i++;
  }
  int x = Wire.read();    // receive byte as an integer
  //Serial.println(s);         // print the integer

  String message[3];
  char *p = dat;
  char *str;
  int z = 0;
  
  while ((str = strtok_r(p, ",", &p)) != NULL){
    //Serial.println(z);
    message[z++] = str;
  }

  //Serial.println(message[1]);
  
  char cbuf[message[1].length()+1];
  cbuf[message[1].length()] = 0;

  message[1].toCharArray(cbuf, sizeof(cbuf));
  char *cq = cbuf;
  char *csub;
  int c = 0;

  String grb[3];

  
  while ((csub = strtok_r(cq, ".", &cq)) != NULL){
    //Serial.println(csub);
    //Serial.print(", ");
    grb[c] = csub;//atoi(csub);
    //Serial.println(grb[c]);
    Serial.println(c);
    c++;
  }
  /*
  while ((csub = strtok_r(cq, ".", &cq))) 
        Serial.println(csub);
        */

  

  char lbuf[sizeof(message[0])];
  message[0].toCharArray(lbuf, sizeof(lbuf));
  char *lq = lbuf;
  char *lsub;
  int l = 0;

  int range[2];
  while ((lsub = strtok_r(lq, ".", &lq)) != NULL){
    range[l] = atoi(lsub);
    l++;
  }

  //Serial.print(range[1]);
  //Serial.println(range[0]);

  
  //Serial.println(sizeof(message[1])+1);

  //Serial.println(message[1]);

  for(int i=range[0];i<range[1];i++){

    // pixels.Color takes RGB values, from 0,0,0 up to 255,255,255
    /*
    //Serial.print(range[1]);
    //Serial.print(range[0]);
    
    Serial.print(grb[0]);
    Serial.print(", ");
    Serial.print(grb[1]);
    Serial.print(", ");
    Serial.println(grb[2]);
    */
    
    
    strip.setPixelColor(i, strip.Color(grb[1].toInt(), grb[0].toInt(), grb[2].toInt()));
    strip.show();
    delay(2);
  }

}
