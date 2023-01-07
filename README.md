## Movies app (wip 🚧)

Android app showing popular movies from TDMB API.

### Running the project
*Required*:  At least Android Studio Dolphin | 2021.3.1 Patch 1. This will be less messy. 

#### TMDB API Key
- Get a API key from [developers.themoviedb.org](https://developers.themoviedb.org/3/getting-started/authentication)
- Put it in `local.properties` file as follows
```
TMDB_API_KEY="<api key>"
```

Build and run the project


### Dependencies

1. Jetpack
   - Jetpack compose
   - Navigation-compose - For Navigation
   - Lifecycle components - ViewModel, ViewModel module to persist data in case of process death
   - Hilt - For Dependency Injection
   - Room - For persisting data locally
   - WorkManager - For scheduling tasks
2. Coroutines - For Concurrency and Asynchronous tasks
3. Coil - For Image Loading and Caching
4. Retrofit, Moshi, OkHttp3 - for network calls
5. logcat 
6. Testing dependencies
   - Junit
   - Androidx Test


### Architecture
🚧

#### Data
🚧

#### Domain
🚧

#### Presentation
- MVI pattern 🚧

### Features
🚧

### Resources