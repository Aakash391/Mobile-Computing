# Image Classification 

This android app classifiy the image uploaded by the user and shows the class the image belongs to using tensorflow lite model.

## Features
### 1. Uploading the image
- The app allows the user to pick any image from the gallery.
- The image is resize and then displays on the app which will be classiified by the app.

### 2. Classification of Image
- The app uses tensorflow model to classify the image
- The model is exported in the android studio and the image uploaded by the user is preprocessed and this preprocessed image is classified using this model

### 3. Native File
- The app shows the data uses by the native file that is in cpp.

## Files

### 3. MainActivity.kt
- This is the main file from where the program starts
- It displays and perfoms all the above components that is discussed above
- It contains two Buttons
    - Upload Image
        - This button allows user to pick an image from the gallery of their phone
        
    - Predict
        - This buttons classifies the uploaded image using tensorflow lite model
        - This buttons shows the class which is associated to the image.
        
### 4. classification.cpp
- This is the file where native code is written, which is also shown by the app.

## Dependencies

- Kotlin
- Jetpack Compose
- TEnsorflow

