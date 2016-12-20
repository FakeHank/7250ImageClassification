from scipy import misc
import os
# Set root directory for data
path='/home/ubuntu/train/data/'

## split dataset into different folder based on name

#images=os.listdir(path)

#imgs=[]
#for img in images:
#    img = misc.imread(path+img)
#    imgs.append(img)

# cats=[]
# dogs=[]
#for i in range(0,10000):
 #   cat=misc.imread(path+'cat.'+str(i)+'.jpg')
  #  misc.imsave(path+'train/cats/cat.'+str(i)+'.jpg',cat)
   # dog=misc.imread(path+'dog.'+str(i)+'.jpg')
   # misc.imsave(path+'train/dogs/dog.'+str(i)+'.jpg',dog)

#for i in range(10000,12500):
 #   cat=misc.imread(path+'cat.'+str(i)+'.jpg')
  #  misc.imsave(path+'test/cats/cat.'+str(i)+'.jpg',cat)
   # dog=misc.imread(path+'dog.'+str(i)+'.jpg')
   # misc.imsave(path+'test/dogs/dog.'+str(i)+'.jpg',dog)

# Plotting a image 

#img = misc.imread(path+'test/dogs/dog.1000.jpg')

#import matplotlib.pyplot as plt
#plt.imshow(img)
#plt.show()

# Start building model

from keras.preprocessing.image import ImageDataGenerator
from keras.models import Sequential
from keras.layers import Convolution2D, MaxPooling2D
from keras.layers import Activation, Dropout, Flatten, Dense

# input size
img_width, img_height = 150, 150

train_data_dir = '/home/ubuntu/train/data/train/'
validation_data_dir = '/home/ubuntu/train/data/test/'
nb_train_samples = 20000
nb_validation_samples = 5000
nb_epoch = 30

# model

model = Sequential()
model.add(Convolution2D(32, 3, 3, input_shape=(3, img_width, img_height)))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Convolution2D(32, 3, 3))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Convolution2D(64, 3, 3))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Convolution2D(128, 3, 3))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Convolution2D(64, 3, 3))
model.add(Activation('relu'))
model.add(MaxPooling2D(pool_size=(2, 2)))

model.add(Flatten())
model.add(Dense(64))
model.add(Activation('relu'))
model.add(Dropout(0.5))
model.add(Dense(1))
model.add(Activation('sigmoid'))

model.compile(loss='binary_crossentropy',
              optimizer='rmsprop',
              metrics=['accuracy'])


# this is the augmentation configuration we will use for training
train_datagen = ImageDataGenerator(
        rescale=1./255,
        shear_range=0.2,
        zoom_range=0.2,
        horizontal_flip=True)


test_datagen = ImageDataGenerator(rescale=1./255)

train_generator = train_datagen.flow_from_directory(
        train_data_dir,
        target_size=(img_width, img_height),
        batch_size=32,
        class_mode='binary')

validation_generator = test_datagen.flow_from_directory(
        validation_data_dir,
        target_size=(img_width, img_height),
        batch_size=32,
        class_mode='binary')

model.fit_generator(
        train_generator,
        samples_per_epoch=nb_train_samples,
        nb_epoch=nb_epoch,
        validation_data=validation_generator,
        nb_val_samples=nb_validation_samples)

model.save('4layers_20k_30epoch.h5')



