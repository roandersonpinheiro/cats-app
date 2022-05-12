# Igmur API

![igmr](https://user-images.githubusercontent.com/81362718/168088513-bf2f076a-e0ac-4cc8-8562-4febc55665f0.jpeg)

<p>The goal of the application is to fetch cats images from https://imgur.com, and show the
images on gallery.</>
  
## Fetching the images
To fetch the images url, imgur requires two keys, you can use the following, or create a new
pair if necessary.

| Client ID  | Client Secret |
| ------------- | ------------- |
| XXXXXXXXXXX |xxxxxxxxxxxxxxxxx|
  
  
You can send a GET to the following url to fetch the cat images, don't forget to include
Client-ID on header authorization key, you can check if the API is working using the
command line, For more information about the imgur use visit https://imgur.com
  
`curl --header "Authorization: Client-ID XXXXXXXXXXXX" https://api.imgur.com/3/gallery/search/?q=cats` 
  
Showing the images
Create an Android app in the Kotlin programming language with just one screen to show the
fetched images in a gallery.
  
### Tech stack & Open-source libraries  
 - Minimum SDK level 16
 - [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
 - [Hilt](https://dagger.dev/hilt/) for dependency injection.
 - Architecture
    - MVVM Architecture (View - DataBinding - ViewModel - Model)
    - Repository Pattern
 - [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
 - [GSON](https://github.com/google/gson) - JSON library for Kotlin and Java.
  
  
### Architecture  
CatApp is based on the MVVM architecture and the Repository pattern.
  ![mvvm](https://user-images.githubusercontent.com/81362718/168135484-330637d7-8050-4d6f-aabc-612aa38e50d9.png)


## System requirements
Before starting, you will need to have the following tools installed on your machine
[Git](https://git-scm.com), [Java JDK](https://www.oracle.com/java/technologies/ac/)
Also it's good to have an editor to work with code like [Android Studio](https://developer.android.com/studio?gclid=Cj0KCQjw4PKTBhD8ARIsAHChzRKi30aGDER9Z8oYJSv3OYapHZheJhRcvbol7kMkXaJWDm7gbgAKklgaAmmjEALw_wcB&gclsrc=aw.ds) 
  

  ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
  ![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white)
  ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
  ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
  ![Kotlin](https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white)
