# Recruitment-Task

The purpose of this application is to record time which a user spends on tasks defined by himself. The user can add new tasks. New task name is validated – it can’t be empty or repeated. Tasks names are stored in shared preferences so they are available after application restart.   

The application is written in MVVM pattern and uses Jetpack Compose to display UI. Hilt is used as a dependency framework and Kotlin coroutines is the desired mechanism for asynchronous operations.   

Below you can find the list of tasks to implement in the application:  

1. Try to understand the code and the application. We would like to verify if you are able to work with already written code and understand it.  

2. Your task is to add a mechanism to record time which users spend on each task. The timer should be displayed next to play button. Users can press the play button next to the task name which should result in displaying a timer. After finishing his task the user presses the button again and the recorded value should be stored in a persistent way which means it should survive screen rotation and restart of the application. You may find some ready components for measuring time in Kotlin Coroutines.  

3. After clicking the task name the user should be redirected to the new screen where all recorded times should be available as a list. There should be an additional component which displays summary value.   

4. After the application goes into background the time measurement can be stopped by the Android system to free resources. When at least one timer is running then run foreground service to stop this behaviour. Service should be stopped when no task is in progress. 

5. Application should support Android devices from API level 21 to 31. 
