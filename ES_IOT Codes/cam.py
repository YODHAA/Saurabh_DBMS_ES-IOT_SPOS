import urllib
import cv2
import numpy as np

url = "http://192.168.69.10:8080/shot.jpg"

while True:
	imgResp = urllib.request.urlopen(url)
	imgNp = np.array(bytearray(imgResp.read()),dtype=np.uint8)
	img = cv2.imdecode(imgNp,-1)
	
	cv2.imshow('Video', img)
	if cv2.waitKey(1) & 0xFF == ord('q'):
		break

cv2.destroyAllWindows()
