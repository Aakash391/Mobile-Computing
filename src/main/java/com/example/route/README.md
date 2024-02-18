# Route Tracking App

This Android app provides a route tracking functionality, allowing users to visualize their journey through a list of stops. The app uses Jetpack Compose for the UI and offers features such as distance tracking, progress indicators, and the ability to switch between different set of units like km(kilometre) or mi(miles).

## Features
### 1. Stop List
- The app displays all the list of stops with a functionality that those stops that the user have crossed will be shown in red.
- Those stops that will coming up next are shown in while and the current next stops will be shown in green.

### 2. Progress Tracking
- The app displays a linear progress indicator showing the journey from the starting point ("Badarpur") to the destination point ("Moolchand" or "Kashmere Gate").
- The progress bar is updated as the user reaches each stop.

### 3. Distance Information
- The app shows the distance covered and the distance left to the next stop.
- Distance information is displayed in both kilometers and miles, with the ability to switch between units.

### 4. Next Station Information
- The app displays information about just next station

### 5. User Controls
- Users can manually progress through the journey by clicking the "Reached" button.
- The app dynamically updates the progress bar and distance information accordingly.

## Files
### 1. Stop.kt
- This is the data file
- It contains the data class of Stop
- A Stop have three components
    - Stop_ID : Int
    - Stop_name : String
    - distanceFromPreviousStop : Float

### 2. AllStops.kt
- This file contains the list of All the stops
- One list is used for normal list(unscrollable)
- Other list is used for lazyColumn(scrollable)

### 3. ProgressBar.kt
- This file contains the function of showling the progressBar, starting stop and destination stop
- The ProgressBar used is the LinearProgressindicator
- The progressBar is updated based on the number of stops in the stop list

### 4. NextStation.kt
- This file contains the functionality of displaying the nextStation, totalDistanceCovered and totalDistanceLeft
- totalDistanceCovered and totalDistanceLeft is shown both in km and mi.
- Whenever the user reaches the next station the next to next station starts displaying and the value of totalDistanceCovered and totalDistanceLeft is updated accordingly

### 5. StopList.kt and AllStopList.kt
- Both the file is similar except one is showing the normal list(unscrollable) with normal Column and the other is showing the scrollable list using LazyColumn
- These files shows the list of all stops with their distanceFromPreviousStops
- distanceFromPreviousStops are shown in both km and mi.
- All the stops that have been covered are shown in Red.
- The next Stop is shown in Green.
- All other stops after the next stops are shown in white
- whenever the user reaches the next stop these colors are updated accordingly to give a good UI experience to the user.

### 6. MainActivity.kt
- This is the main file from where the program starts
- It displays all the above components that is discussed above
- It contains two Buttons
    - Reached 
        - This button allow user to update the app that the next station is reached.
        - When this button is pressed the stopList/AllStopList,totaldistanceCovered, nextStation, totalDistanceLeft is updated accordingly
    - Switch
        - This button allows to switch between km to mi or vice versa
        - When this button is clicked the totalDistanceLeft, totalDistanceCovered and the distanceFromPreviousStop in the stopList is switached to mi or vice-versa
- When the last stop is also reached it shows the meassage "Reached laststation_name" and remove the stoplist/allstopList and the Reached button and keeps only the progressBar, intiialstop, finalStop and the Switch Button.

## Dependencies

- Kotlin
- Jetpack Compose
