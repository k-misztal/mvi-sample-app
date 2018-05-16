# Bonfire sample app

This application is sample application written using MVI architecture. 

Prerequisites; 
- Minimum API 21
- PlayServices - assumed that are installed
- [Canary leak](https://github.com/square/leakcanary) is turned on in debug build
- The app will ask about location permissions on start. If revoked, the app will finish. 
 
As well there is pagination for loading recommended places.

The application can rotate, and state is not lost while rotating (and requests are not canceled).  

## Build instructions 
Only run `./gradlew assembleRelease` in the main dir of the project. You will find apk file in `app/build/outputs/apk`.

### Running tests
Sorry, no tests this time :(

## Architecture wireframe

The architecture of the app is based on [MVI blog series](http://hannesdorfmann.com/android/mosby3-mvi-1). I liked the blog post and wanted to check out MVI approach in practice. **It is not a production-ready code.** 

Credentials for Foursquare API are placed in: `app/build.gradle`
