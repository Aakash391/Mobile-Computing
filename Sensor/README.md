# Orientation Display

This android app shows the orientation of phone with respect to three different angles roll, pitch and yaw. It stores the values of these angles in the database and shows the graph of these three angles with respect to time.

## Features
### 1. Orientation using accelerometre sensor
- The app uses accelerometre sensor to calculate the values of roll, pitch and yaw.
- The sensing interval can be changed which means the data collection and display interval can be changed. 

### 2. Generating graphs
- The app also generate graphs of these three values with respect to time at which these value occurs or stored in database.
- The app uses third party library Ycharts to make graphs
- The graph is made by retreiving all the data from the database and then making the graph

### 3. Database (SQLite)
- The app uses SQLite to store the value of pitch, roll and yaw after every 1 second.

### 4. Seperate activity
- The app uses a second activity to make graphs.
- this second activity is called from the main activity

## Files
### 1. Database.kt
- This is the database file
- It contains the function to add values of pitch, roll and yaw to database and read these value from database
- The table contains three column
    - Roll
    - Pitch
    - Yaw

### 2. Graph.kt
- This files is used to make the graphs of roll, pitch and yaw
- This file uses the third party library Ycharts to make graph


### 3. MainActivity.kt
- This is the main file from where the program starts
- It displays and perfoms all the above components that is discussed above
- It contains two Buttons
    - Show graphs 
        - This button shows the graph to user by extracting data from database
        
    - back button
        - When all three graoh is shown then this button is displayed, it allows user to go back to the main page where the three values is shown


## Dependencies

- Kotlin
- Jetpack Compose
- SQLite
- YCharts


