# Bonfire sample app

This application is made as a sample app for [bonfire](https://www.bonfireit.com). 

Prerequisites; 
- minimum API 21
- PlayServices - assumed that are installed, did't handle situation when they are not. 
- canary leak is turned on in debug build
- app will ask about location permissions on start, if revoked app will finish. 
 
As well there is pagination for loading recommended places. Note as well that some places does not have image added 
(for my hometown none of them), so it will use some placeholder.

## Build instructions 
Only run `./gradlew assembleRelease` in main dir of project. You will find apk file in `app/build/outputs/apk`.

### Running tests
Sorry, I did't have time for tests. I wanted to attach some, and generate code report with `Jacoco`, but in the end I didn't . 

## Architecture wireframe

Architecture of the app is based on [MVI blog series](http://hannesdorfmann.com/android/mosby3-mvi-1). 
To be honest this is my first time using MVI - I've read that blog post some time ago and I've never had time to try it out.
This app was perfect opportunity to do so. Normally I use _classic_ MVP, as well based on Mosby. 

App is far from perfect, especially `NearbyPresenter`, but I didn't have much time to play with that architecture, 
neither I was feeling like rewriting app to MVP which I normally use. 

MVI may seem like over-engineering, and it it is little bit. Though it requires a lot of code to describe 
state, and its changes, as well it solves a lot of issues with View state (like orientation change problems etc). 
Anyway, I will not write a lot about this architecture, you can read blog post mentioned above. 


Credentials for Foursquare APU are placed in: `app/build.gradle`
