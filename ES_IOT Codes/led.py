import RPi.GPIO as GPIO;
import time;
GPIO.cleanup();
GPIO.setmode(GPIO.BOARD);
GPIO.setup(11,GPIO.OUT);
try:
    while 1:
            GPIO.output(11,True);
            time.sleep(1);
            GPIO.output(11,False);
            time.sleep(1);
except KeyboardInterrupt:
    GPIO.cleanup();
    
