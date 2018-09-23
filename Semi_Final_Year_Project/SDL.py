
from __future__ import division
import cv2
import numpy as np
import os
from random import shuffle
from tqdm import tqdm

my_test_dir='/media/salil007/Titanium/Study_Booster/Root/uploads'    
img_size = 50
learning_rate = 0.001

model_name = 'catsvsdogs-{}-{}.model'.format(learning_rate,'6conv-new-final')

def process_my_testing_data():
    my_testing_data = []
    for img in tqdm(os.listdir(my_test_dir)):
        path = os.path.join(my_test_dir,img)
        img_num = img.split('.')[0]
        img = cv2.resize(cv2.imread(path,cv2.IMREAD_GRAYSCALE),(img_size,img_size))
        my_testing_data.append([np.array(img), img_num])
    np.save('my_test_data.npy',my_testing_data)
    return my_testing_data   

import tflearn
from tflearn.layers.conv import conv_2d,max_pool_2d
from tflearn.layers.core import input_data,dropout,fully_connected
from tflearn.layers.estimator import regression

import tensorflow as tf
tf.reset_default_graph()

convnet = input_data(shape=[None,img_size,img_size,1], name='input')

convnet = conv_2d(convnet,32,2,activation='relu')
convnet = max_pool_2d(convnet,2)

convnet = conv_2d(convnet,64,2,activation='relu')
convnet = max_pool_2d(convnet,2)

convnet = conv_2d(convnet,32,2,activation='relu')
convnet = max_pool_2d(convnet,2)

convnet = conv_2d(convnet,64,2,activation='relu')
convnet = max_pool_2d(convnet,2)

convnet = conv_2d(convnet,32,2,activation='relu')
convnet = max_pool_2d(convnet,2)

convnet = conv_2d(convnet,64,2,activation='relu')
convnet = max_pool_2d(convnet,2)

convnet = fully_connected(convnet,1024,activation='relu')
convnet = dropout(convnet,0.8)

convnet = fully_connected(convnet,2,activation='softmax')
convnet = regression(convnet,optimizer='adam',learning_rate=0.001,loss='categorical_crossentropy',name='targets')

model = tflearn.DNN(convnet,tensorboard_dir='log')

if os.path.exists('{}.meta'.format(model_name)):
    model.load(model_name)
    print("Model Loaded!!")

'''while(1):
   if(os.path.exists("condition.txt")):
      break'''

my_test_data = process_my_testing_data()
my_test_data = np.load('my_test_data.npy')

for data in tqdm(my_test_data):     
     img_data = data[0]                                          #for personalized testing
     x = img_data.reshape(img_size,img_size,1)
     model_out = model.predict([x])
     if(model_out[0][0]>model_out[0][1]):
          print("Cat")
          print(model_out[0][0])
     else: 
          print("Dog")
          print(model_out[0][1])
          
          
          
          
          
          
