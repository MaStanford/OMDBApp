Salesforce Exam App

I tested on a Pixel XL API 27 emulator.

Standard Android build and installation using Gradle
Ex:
gradlew assembleDebug
gradlew installDebug

Dependencies are:
-Retrofit with RxJava and GSON converter instead of Volley
-Support library / Constraint etc.
-Picasso

I didn't have time to write tests, but some example tests would be:

DB:
Assert that data put into the DB is there
Assert that DB can handle bad data

Network:
Instrumentation test to make sure we handle network state
Assert that we get the right data for a set request

UI:
Using RoboElectric assert that UI elements change as expected

Others:
Memeory tests, profile and check battery usage
Handle orientation changes.