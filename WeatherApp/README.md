
This android app provides the maximum and minimum temperature of a specific location of specific date using the free weather API. If the date is in the future then average of last 10 year temperature is fetched and stored in the database

## Features
### 1. Date Picker
- The app uses Date picker to pick a specific date without any error in picking the date.

### 2. API call
- The app uses free weather API to fetch maximum and minimum temperature
- If the date is in the future then average of last 10 available temperature year is fetched
- The API call is made using retrofit

### 3. Database (SQLite)
- The app uses SQLite to store the maximum and minimum temperature in the database
- When the temperature is fetched using the API, the previous 10 year of that specific date is also fetched and stored in the database

### 4. Network Connectivity
- The app also monitors the network connection in a seperate thread
- When the user is connected to internet it displays "Online" otherwise "Offline"
- if the user is Offline then the temperature is fetched from the database, if there is no data in the database then it shows "No Avaialble Data"

### 5. Proper Error handling
- When no date is picked, then it shows "PLease select the date"
- When no data is in the database, then it shows "No available data"

## Files
### 1. Database.kt
- This is the database file
- It contains the function to add temperature to database and read temperature from database
- The table contains three column
    - Date
    - Maxtemp
    - Mintemp

### 2. FetchTemperature.kt
- This file uses the free weather API to fetch temperature
- All the values is parsed like location and date in this file

### 3. WeatherAPIService.kt
- This file uses the API to GET the JSON data from the API and store in the data class

### 4. WeatherData.kt
- This is the data class of weather
- Theh data class is according to the JSON data that is fetched from the API

### 5. PrintTemperature.kt
- This file is just used to print the maximum and minimum temperature in a better UI way

### 6. MainActivity.kt
- This is the main file from where the program starts
- It displays all the above components that is discussed above
- It contains two Buttons
    - PickDate 
        - This button allow user to pick the date using the DatePicker
        
    - FetchTemperature
        This button allows to fetch temperature from the API and store the temperature in the SQLite database


## Dependencies

- Kotlin
- Jetpack Compose

