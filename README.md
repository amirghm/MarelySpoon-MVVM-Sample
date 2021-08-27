# MarelySpoon-Sample-App
![MarelySpoon Sample App Icon](https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/banner.jpg)

This application aims to show a bunch of recipes with details. It was written by **Kotlin** in Android Studio and created by **MVVM Architecture** and **Repository Pattern**.
It gets data from [Items List](https://cdn.contentful.com/spaces/kk2bw5ojx476/environments/master/entries?access_token=7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c) and shows them inside a list and by clicking on it you'll able to see the details.


## Table of Contents

- [Structure](#structure)
- [Testing](#testing)
- [Libraries](#libraries)
- [Screenshots](#screenshots)

## Structure
The application has five main parts which responsible for managing applications.  
```annotations``` ```data``` ```di``` ```ui``` ```utils``` 

<img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/structure.png"/>

### Annotations
In this part we store any enum shape classes or types

<img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/annotations.png"/>

### Data
In this part we have our ```Models```, which distinct into two parts (at this code), **Simple Models** and **Response Models**. Another part is ```Repository``` which contains one interface and one implementation Repository class. The last part is about the ```DataSource```  which in this test we only have ```Remote Data Source```.

<img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/data.png"/>

### DI
This package handles all boilerplate for the dependency injection feature. ```NetworkModule``` is responsible for creating di for the network operations and ```RepositoryModule``` is responsible for the repository di.

<img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/di.png"/>

### UI
The application is hosted by just **one activity** to better presentation to users. it has two fragments which ```RecipesFragment``` is responsible for the recipe list and ```RecipeDetailsFragment``` is responsible for the details of recipes in the list. The ```RecipesFragment``` contains one Adapter, one ViewHolder, and one diffutil. There is one function collection that exists which is to convert all item response data to the UI model data.

<img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/ui.png"/>

### Utils

In this part we have two main sub-packages, one of the is ```Extentions``` which represents necessary extension for the other parts, and also some data binding extensions is defined into extension. Another part is a helper which contains all helper classes which we use in our code to handle some tasks, like loading images, handling network responses.

<img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/utils.png"/>

## Testing

Each module has its tests and all server responses in the tests are served by the mock web server by appending relative URLs to the localhost and the connected port as the base URL.

In the ``data-remote`` module the responses are mocked using the mockwebserver and verified that they
are what we expect.

In the ```app``` module there are unit tests for the viewmodels and util classes 
and connected tests for the UI Screens.

The test instrumentation app uses modules that have been swapped with fakes for
the network module to run requests on localhost with mockwebserver, removes flakiness compared to relying on actual data from the real server aspects such as internet connection or network service might bring up issues.

## Libraries

Libraries used in the whole application are:

- [Jetpack](https://developer.android.com/jetpack)🚀
  - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI related data in a lifecycle conscious way and act as a channel between use cases and ui
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - support library that allows binding of UI components in layouts to data sources, binds images results to UI
  - [Navigation Component](https://developer.android.com/guide/navigation) - Handle application navigation.
- [Retrofit](https://square.github.io/retrofit/) - type-safe HTTP client and supports coroutines out of the box.  
- [GSON](https://github.com/google/gson) - JSON Parser,used to parse requests on the data layer for Entities.
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logs HTTP request and response data.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines,provides `runBlocking` coroutine builder used in tests
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - a web server for testing HTTP clients, verify requests and responses on the star wars API with the retrofit client.
- [Material Design](https://material.io/develop/android/docs/getting-started/) - Represents Material Component for the Views
- [Dagger Hilt](https://dagger.dev/hilt/) - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
- [Hamcrest](http://hamcrest.org/) - Hamcrest is a framework for writing matcher objects allowing ‘match’ rules to be defined declaratively. we use this to create human-readable test function names
- [Robolectric](http://robolectric.org/) - Unit test on android framework.
- [Glide](https://github.com/bumptech/glide/) - An image loading framework to load and cache images from source simply.

## Screenshots

<img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/main.jpg" width=200><img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/details-1.jpg" width=200><img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/details-2.jpg" width=200><img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/no-internet.jpg" width=200> 
<img src="https://raw.githubusercontent.com/amirghm/MarelySpoon-MVVM-Sample/main/screenshot/landscape.jpg" height=350>
