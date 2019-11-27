//#include<Servo.h>   
#include <SoftwareSerial.h>
//paddlers pins
const int m1 = 2;
const int m2 = 3;
const int m3 = 4;
const int m4 = 5;
//conveyor belt pin
const int conveyorBelt = 6;
const int conveyorBelt1 = 7;
//vacuum pin
const int vacuum = 8;
const int vacuum1 = 9 ;


SoftwareSerial bt(11, 12); //Rx | Tx
void setup() {
  //paddlers pins set to output mode
  pinMode(m1,OUTPUT);
  pinMode(m2,OUTPUT);
  pinMode(m3,OUTPUT);
  pinMode(m4,OUTPUT);
  //conveyor belt and vacuum to output mode
  pinMode(conveyorBelt,OUTPUT);
  pinMode(conveyorBelt1,OUTPUT);
  pinMode(vacuum,OUTPUT);
  pinMode(vacuum1,OUTPUT);
  
  Serial.begin(9600);
  bt.begin(9600);
}
//these two booleans are used to conditionally turn on or off conveyor belt and vacuum respectively.
bool isConveyorBeltOn  = false;
bool isVacuumOn  = false;

void loop() {
  if(bt.available()>0){
    char c = bt.read();
    /*
    the char c is coming from bluetooth data
    and the chars and the purpose is
    w - Going forward
    s - stop
    a - forward left
    d - forward right
    j - backward left
    k - backward
    l - backward right
    e - engine
    c - conveyor belt
    v - vacuum
    */
    switch(c){
      case 'w':
          Serial.println("GOING FORWARD");
          bt.println("GOING FORWARD");
          digitalWrite(m1,HIGH);
          digitalWrite(m2,LOW);
          digitalWrite(m3,HIGH);
          digitalWrite(m4,LOW);
          break;
      case 's':
          Serial.println("Stoping");
          bt.println("Stoping");
          digitalWrite(m1,LOW);
          digitalWrite(m2,LOW);
          digitalWrite(m3,LOW);
          digitalWrite(m4,LOW);
          break;
      case 'a':
          Serial.println("GOING FORWARD LEFT");
          bt.println("GOING FORWARD LEFT");
          digitalWrite(m1,LOW);
          digitalWrite(m2,LOW);
          digitalWrite(m3,HIGH);
          digitalWrite(m4,LOW);
          break;
      case 'd':
          Serial.println("GOING FORWARD RIGHT");
          bt.println("GOING FORWARD RIGHT");
          digitalWrite(m1,HIGH);
          digitalWrite(m2,LOW);
          digitalWrite(m3,LOW);
          digitalWrite(m4,LOW);
          break;
      case 'j':
          Serial.println("GOING BACKWARD LEFT");
          bt.println("GOING BACKWARD LEFT");
          digitalWrite(m1,LOW);
          digitalWrite(m2,LOW);
          digitalWrite(m3,LOW);
          digitalWrite(m4,HIGH);
          break;
      case 'l':
          Serial.println("GOING BACKWARD RIGHT");
          bt.println("GOING BACKWARD RIGHT");
          digitalWrite(m1,LOW);
          digitalWrite(m2,HIGH);
          digitalWrite(m3,LOW);
          digitalWrite(m4,LOW);
          break;
      case 'k':
          Serial.println("GOING BACKWARD");
          bt.println("GOING BACKWARD");
          digitalWrite(m1,LOW);
          digitalWrite(m2,HIGH);
          digitalWrite(m3,LOW);
          digitalWrite(m4,HIGH);
          break;
          
      case 'c':
          if(!isConveyorBeltOn){
          Serial.println("Turning ON Conveyor Belt");
          bt.println("Turning ON Conveyor Belt");
            digitalWrite(conveyorBelt,HIGH);
            digitalWrite(conveyorBelt1,LOW);
            isConveyorBeltOn = true;
          }else{
          Serial.println("Turning OFF Conveyor Belt");
          bt.println("Turning OFF Conveyor Belt");
            digitalWrite(conveyorBelt,LOW);
            digitalWrite(conveyorBelt1,LOW);
            isConveyorBeltOn = false;
          }
          break;
      
      case 'v':
          if(!isVacuumOn){
          Serial.println("Turning ON Vacuum");
          bt.println("Turning ON Vacuum");
            digitalWrite(vacuum,HIGH);
            digitalWrite(vacuum1,LOW);
            isVacuumOn = true;
          }else{
          Serial.println("Turning OFF Vacuum");
          bt.println("Turning OFF Vacuum");
            digitalWrite(vacuum,LOW);
            digitalWrite(vacuum1,LOW);
            isVacuumOn = false;
          }
          break;
      default:
          Serial.println("Not The Choice");
          bt.println("Not The Choice");
    }
  }
}
