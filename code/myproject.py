from __future__ import print_function

from keras.models import load_model
from keras.models import Sequential


import numpy as np
import urllib2


import urllib

from flask import Flask
from flask import jsonify
from flask import request




url="https://github.com/reasonwow/Cat-Image-Classification/raw/master/model_1205_20epoch.h5"
req=urllib. urlretrieve(url)

print(req[0])

#Load the model from server
model = load_model('/home/ubuntu/4layers_20k_20epoch.h5')

print(model.summary())

#Flask backend source code
from flask import Flask
application = Flask(__name__)

@application.route("/")
def hello():
    return "<h1 style='color:blue'>WTF?!IT IS DONE!Really?</h1>"

@application.route('/test_image', methods = ['POST'])
def test_post():

    test_json = request.get_json()

    data = request.get_data()
    print('request raw data is : ')
    print(data)
    print(type(data))

    #Processing image
    f=data[:-1].split('\n')
    img=[]
    for color in f:
	    color=color[:-1]
	    colist=map(np.uint8,color.split(','))
	    coarray=np.asarray(colist).reshape([150,150])
	    img.append(coarray)
    img=np.asarray(img)	
    print(type(img))

    #Transfer image
    arrs = [img]
    res = np.concatenate([arr[np.newaxis] for arr in arrs])
    r = model.predict(res)
    print('result is:' + str(r))
    return jsonify({'message' : str(r)})

if __name__ == "__main__":
#    application.run(debug = True)
    application.run(host='0.0.0.0')
