import urllib2 as ul
import random

data = ul.urlopen("http://192.168.43.1:8080/shot.jpg")
imgName = "img"
imgLoc = "./" + imgName + ".jpg"
image = open(imgLoc, "wb")
image.write(data.read())
image.close()