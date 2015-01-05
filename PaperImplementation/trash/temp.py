import os
import multiprocessing
import re
import time
import random
from glob import glob
import itertools
import pickle

import numpy as np
np.set_printoptions(threshold='nan')

import skimage
from skimage import io

'''from sklearn import cross_validation
from sklearn import svm
from sklearn import preprocessing
from sklearn.linear_model.logistic import LogisticRegression
'''

def file_to_rgb(filename):
  """ return an image in rgb format: a gray scale image will be converted, a rgb image will be left untouched"""
  bild = io.imread(filename)
  if (bild.ndim==2):
    rgb_bild= skimage.color.gray2rgb(bild)
  else:
    rgb_bild = bild
  return rgb_bild

def hsv_to_feature(hsv,N,C_h,C_s,C_v):
  """ Takes an hsv picture and returns a feature vector for it.
  The vector is built as described in the paper 'Machine Learning Attacks Against the Asirra CAPTCHA' """  
  res = np.zeros((N,N,C_h,C_s,C_v))
  cell_size= 250/N
  h_range = np.arange(0.0,1.0,1.0/C_h)
  h_range = np.append(h_range,1.0)
  s_range = np.arange(0.0,1.0,1.0/C_s)
  s_range = np.append(s_range,1.0)
  v_range = np.arange(0.0,1.0,1.0/C_v)
  v_range = np.append(v_range,1.0)
 # print h_range,s_range,v_range
  count=0
  for i in range(N):
    for j in range(N):
      cell= hsv[i*cell_size:i*cell_size+cell_size,j*cell_size:j*cell_size+cell_size,:]
      # check for h
      print cell
      for h in range(C_h):
        h_cell = np.logical_and(cell[:,:,0]>=h_range[h],cell[:,:,0]<h_range[h+1])
        for s in range(C_s): 
          s_cell = np.logical_and(cell[:,:,1]>=s_range[s],cell[:,:,1]<s_range[s+1])
          for v in range(C_v):
            v_cell = np.logical_and(cell[:,:,2]>=v_range[v],cell[:,:,2]<v_range[v+1])
            gesamt = np.logical_and(np.logical_and(h_cell,s_cell),v_cell)
            res[i,j,h,s,v] = gesamt.any()
            #print count,res[i,j,h,s,v],hsv[i,j,0]
            #print i,j,h,s,v,hsv[i,j,0]
            count=count+1
  return np.asarray(res).reshape(-1)

def build_color_featurevector(filename,N,C_h,C_s,C_v):
  """ Takes a jpeg file and the parameters of the feature vector and builds such a vector"""
  #filename,N,C_h,C_s,C_v
  rgb_bild = file_to_rgb(filename)
  assert (rgb_bild.shape[2]==3)
  return hsv_to_feature(skimage.color.rgb2hsv(rgb_bild),N,C_h,C_s,C_v)
	    

x=build_color_featurevector("dog.55.jpg",5,10,6,6)
#print x