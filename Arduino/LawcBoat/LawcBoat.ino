//#include<Servo.h>   
#include <SoftwareSerial.h>
#include<Servo.h>

//paddlers pins
const int m1 = 2;
const int m2 = 3;
const int m3 = 4;
const int m4 = 5;
//conveyor belt pin
const int conveyorBelt = 6;
//vacuum pin
const int vacuum = 7;

const int trigPin = 8;
const int echoPin = 9;

const int engine = 10;

//servo pins for gates
const int servoPinForGate = A0;
const int servoPinForGate1 = A1;
Servo servoForGate,servoForGate1;


SoftwareSerial bt(12, 13); //Rx | Tx
void setup() {
  //paddlers pins set to output mode
  pinMode(m1,OUTPUT);
  pinMode(m2,OUTPUT);
  pinMode(m3,OUTPUT);
  pinMode(m4,OUTPUT);
  //conveyor belt and vacuum to output mode
  pinMode(conveyorBelt,OUTPUT);
  pinMode(vacuum,OUTPUT);

  //ultrasonic sensor
  pinMode(trigPin,INPUT);
  pinMode(echoPin,INPUT);
  
  pinMode(servoPinForGate,OUTPUT);
  pinMode(servoPinForGate1,OUTPUT);

  servoForGate.attach(servoPinForGate);
  servoForGate1.attach(servoPinForGate1);
  
  Serial.begin(9600);
  bt.begin(9600);
}
//these two booleans are used to conditionally turn on or off conveyor belt and vacuum respectively.
bool isConveyorBeltOn  = false;
bool isVacuumOn  = false;
bool isGatesOn = false;
bool isEngineOn= false;
bool allowConveyorBelt = true;

int timee;
int distance;
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

          if(allowConveyorBelt){
             if(!isConveyorBeltOn){
              Serial.println("Turning ON Conveyor Belt");
              bt.println("Turning ON Conveyor Belt");
              digitalWrite(conveyorBelt,HIGH);
              isConveyorBeltOn = true;
            }else{
              Serial.println("Turning OFF Conveyor Belt");
              bt.println("Turning OFF Conveyor Belt");
              digitalWrite(conveyorBelt,LOW);
              isConveyorBeltOn = false;
            } 
          }
          
          break;
      
      case 'v':
          if(!isVacuumOn){
          Serial.println("Turning ON Vacuum");
          bt.println("Turning ON Vacuum");
            digitalWrite(vacuum,HIGH);
            isVacuumOn = true;
          }else{
          Serial.println("Turning OFF Vacuum");
          bt.println("Turning OFF Vacuum");
            digitalWrite(vacuum,LOW);
            isVacuumOn = false;
          }
          break;
      case 'g':
          if(isGatesOn){
            servoForGate.write(30); 
            servoForGate1.write(108);
          }else{
            servoForGate.write(108); 
            servoForGate1.write(30);
          }
          break;

      case 'e':
          if(!isEngineOn){
            Serial.println("Turning on the engine");
            bt.println("Turning on the engine");
            digitalWrite(engine,HIGH);
          }else{
            Serial.println("Turning on the engine");
            bt.println("Turning on the engine");
            digitalWrite(engine,LOW);
          }
      default:
          Serial.println("Not The Choice");
          bt.println("Not The Choice");
    }
  }

  
  timee = pulseIn(echoPin,HIGH);
  distance = (timee * 0.034)/2;

  if(distance < 10){
    allowConveyorBelt = false;
  }else{
    allowConveyorBelt = true;
  }
}
