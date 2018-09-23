import RPi.GPIO as GPIO
import time
import urllib2
import json

LED = 21
BUZZ = 24
TEMP_MIN = 17
WIND_MIN = 20
HUMIDITY_MIN = 70

def get_temp():
    url = "http://api.wunderground.com/api/d640a42b754509c1/conditions/q/CA/lucknow.json"
    api = urllib2.urlopen(url)
    json_data = json.loads(api.read().decode())
    return json_data ["current_observation"] ["temp_c"]

def get_wind():
    url = "http://api.wunderground.com/api/d640a42b754509c1/conditions/q/CA/lucknow.json"
    api = urllib2.urlopen(url)
    json_data = json.loads(api.read().decode())
    return json_data ["current_observation"] ["wind_mph"]

def get_humidity():
    url = "http://api.wunderground.com/api/d640a42b754509c1/conditions/q/CA/lucknow.json"
    api = urllib2.urlopen(url)
    json_data = json.loads(api.read().decode())
    return json_data ["current_observation"] ["relative_humidity"]

def weather():
    temp = get_temp()
    wind = get_wind()
    humidity = get_humidity()
    return temp, wind, humidity

#ab64a1b2df6da8ab 
def buzzer():
    GPIO.output(BUZZ, True)
    time.sleep(1)
    print ("Buzz")
    
def blink_led():
    i = 10
    while i > 0:
        GPIO.output(LED, True)
        time.sleep(0.1)
        GPIO.output(LED, False)
        time.sleep(0.1)
        print ("Blink")
        i = i - 1
    
    
def notify_on():
    buzzer()
    blink_led()
    
def notify_off():
    GPIO.output(BUZZ, False)
    GPIO.output(LED, False)

def main():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(LED, GPIO.OUT)
    GPIO.setup(BUZZ, GPIO.OUT)
    try:
        while 1:
            temp, wind, humidity = weather()
            print ("Temp: "+ str(temp) +" Humidity: " + str(humidity) + " Wind Speed: "+ str(wind) )
            if temp > TEMP_MIN:
                notify_on()
            elif humidity > HUMIDITY_MIN :
                notify_on()
            elif wind > WIND_MIN :
                notify_on()
            notify_off()
            
            
    except KeyboardInterrupt:
	GPIO.cleanup();  

if __name__== "__main__":
  main()

      
