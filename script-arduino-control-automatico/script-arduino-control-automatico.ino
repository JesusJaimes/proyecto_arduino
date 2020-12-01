
int izqA = 5; 
int izqB = 6; 
int derA = 9; 
int derB = 10; 
int vel = 255; // Velocidad de los motores (0-255)

void setup()  { 
  pinMode(derA, OUTPUT);
  pinMode(derB, OUTPUT);
  pinMode(izqA, OUTPUT);
  pinMode(izqB, OUTPUT);
 } 
 
void loop()  { 
    analogWrite(derB, 0);  
    analogWrite(izqB, 0); 
    delay (3000);
    
    analogWrite(derA, vel);  
    analogWrite(izqA, vel); 
    delay (5000);
   
    analogWrite(derA, vel); 
    analogWrite(izqA, 0); 
    delay (1000);

    analogWrite(derB, vel);  
    analogWrite(izqB, vel); 
    delay (3000); 
    
    analogWrite(derA, 0);  
    analogWrite(izqA, vel); 
    delay (1000);

    analogWrite(derA, vel); 
    analogWrite(izqA, vel); 
    delay (5000);
     
    analogWrite(derA, 0); 
    analogWrite(izqA, 0);
    delay (3000);             
}
