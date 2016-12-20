from keras.models import load_model
import numpy as np
from scipy import misc


model = load_model('first_100epoch.h5')
print(model.summary())
#misc.imread('/home/ubuntu/train/test10/cat0.jpg')
cat0 = misc.imread('/home/ubuntu/train/test10/cat0.jpg')
print('type is :' + str(type(cat0)))
cat0 = cat0.transpose(2,0,1).reshape(3,150,150)

cat1 = misc.imread('/home/ubuntu/train/test10/cat1.jpg')
cat1 = cat1.transpose(2,0,1).reshape(3,150,150)

cat2 = misc.imread('/home/ubuntu/train/test10/cat2.jpg')
cat2 = cat2.transpose(2,0,1).reshape(3,150,150)

cat3 = misc.imread('/home/ubuntu/train/test10/cat3.jpg')
cat3 = cat3.transpose(2,0,1).reshape(3,150,150)

cat4 = misc.imread('/home/ubuntu/train/test10/cat4.jpg')
cat4 = cat4.transpose(2,0,1).reshape(3,150,150)

dog0 = misc.imread('/home/ubuntu/train/test10/dog0.jpg')
dog0 = dog0.transpose(2,0,1).reshape(3,150,150)

dog1 = misc.imread('/home/ubuntu/train/test10/dog1.jpg')
dog1 = dog1.transpose(2,0,1).reshape(3,150,150)

dog2 = misc.imread('/home/ubuntu/train/test10/dog2.jpg')
dog2 = dog2.transpose(2,0,1).reshape(3,150,150)

dog3 = misc.imread('/home/ubuntu/train/test10/dog3.jpg')
dog3 = dog3.transpose(2,0,1).reshape(3,150,150)

dog4 = misc.imread('/home/ubuntu/train/test10/dog4.jpg')
dog4 = dog4.transpose(2,0,1).reshape(3,150,150)


arrs = [cat0]

arrs = np.append(arrs, [cat1], axis = 0)
arrs = np.append(arrs, [cat2], axis = 0)
arrs = np.append(arrs, [cat3], axis = 0)
arrs = np.append(arrs, [cat4], axis = 0)

arrs = np.append(arrs, [dog0], axis = 0)
arrs = np.append(arrs, [dog1], axis = 0)
arrs = np.append(arrs, [dog2], axis = 0)
arrs = np.append(arrs, [dog3], axis = 0)
arrs = np.append(arrs, [dog4], axis = 0)

res = np.concatenate([arr[np.newaxis] for arr in arrs])
r = model.predict(res)
#r = r.tolist()
print(r)
print('propobilities is: ')
print(model.predict_proba(res))


