DVT Weather App Assessment 

App Overview : 

Architecture - CLEAN Architecure was used to separate the code into their respective purposes. The main ones being the data layer which contains api logic and models and the presentation layer which has the ui components and the viewModels.

Libraries found in the gradle.app - \
implementation(libs.androidx.navigation.compose) Navigation in Jetpack Compose\
implementation(libs.retrofit) The go-to when working with REST apis\
implementation(libs.converter.gson) Converts json to java/kotlin and vice versa\
implementation(libs.androidx.lifecycle.livedata.ktx) Live data had to be used with some of the location services but State variables were used elsewhere\
implementation(libs.play.services.location) Google Location services\
implementation(libs.maps.compose) For the Map View

Building the project - Clone and Run. The openweathermap api key is in the strings.xml and the google map api key is in the AndroidManifest.xml but the repo should work as is. The apk was added to the repo.

Thats all Folks !!! 🎃

![app_one](https://github.com/user-attachments/assets/4efeff96-c358-45f1-9fea-175fbbe40951)
