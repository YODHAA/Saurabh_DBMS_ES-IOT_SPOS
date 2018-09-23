import urllib2
import cookielib
from getpass import getpass
import sys
import os
from stat import *
#Version 60.0.3112.89 (Developer Build) Built on Ubuntu 14.04, running on Raspbian 9.1 (32-bit)
message = str(sys.argv[1:]).translate(None,'[],\'')

number = "8390347688"
message = raw_input("Enter text: ")

if __name__ == "__main__":    
    username = "8390743246"
    passwd = "mnrox"

    message = "+".join(message.split(' '))

 #logging into the sms site
    url ='http://site24.way2sms.com/Login1.action?'
    data = 'username='+username+'&password='+passwd+'&Submit=Sign+in'

 #For cookies

    cj= cookielib.CookieJar()
    opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
    
 #Adding header details
    #opener.addheaders=[('User-Agent','Chromium Version 60.0.3112.89')]
    try:
        usock =opener.open(url, data)
    except IOError:
        print "error"
        #return()

    jession_id =str(cj).split('~')[1].split(' ')[0]
    send_sms_url = 'http://site24.way2sms.com/smstoss.action?'
    send_sms_data = 'ssaction=ss&Token='+jession_id+'&mobile='+number+'&message='+message+'&msgLen=136'
    opener.addheaders=[('Referer', 'http://site25.way2sms.com/sendSMS?Token='+jession_id)]
    try:
        sms_sent_page = opener.open(send_sms_url,send_sms_data)
    except IOError:
        print "error"
        #return()

    print "success" 
    #return ()