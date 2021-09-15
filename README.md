# Android Clean Architecture Components Boilerplate

This boilerplate is not only helpful to other developers, but also that it helps to educate in the area of architecture.

It is written 100% in Kotlin with both UI and Unit tests - we will also be keeping this up-to-date as libraries change!

### Disclaimer

Note: The use of clean architecture may seem over-complicated for this sample project. However, this allows us to keep the amount of boilerplate code to a minimum and also demonstrate the approach in a simpler form.

Clean Architecture will not be appropriate for every project, so it is down to you to decide whether or not it fits your needs 🙂

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Mockito](http://site.mockito.org/)
* [Espresso](https://developer.android.com/training/testing/espresso/index.html)
* [Robolectric](http://robolectric.org/)

## Requirements

* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android 11 ([API 30](https://developer.android.com/studio/releases/platforms))
* Latest Android SDK Tools and build tools.

## Architecture

The architecture of the project follows the principles of Clean Architecture. Let's look at each of the architecture layers and the role each one plays :)

### Presentation

The presentation Module contains everything that is related to the user facing parts of our application. This layer should use MVVM to handle the orchestration of data from the data source to the UI - this will be made up of an Android Architecture Components View Model class implementation which will use the Use Case classes to retrieve data. The View Model class will then create an instance of an immutable UI state class which will then be passed to the View which has subscribed to the data stream.

This View will then use the model to render it's state. The UI state should be the only state of the View, this way there is only one source of truth for how the display of content is constructed. The UI state should be a sealed class and make use of a Resource State instance (LOADING, SUCCESS, ERROR) so that UI states can easily be composed.

### Domain

The Domain Module contains the bussiness logic. The software in this layer encapsulates and stores all the use cases of the system, these handle the flow of data to and from the repository.

### Data

The Data Module is our access point to external data layers and is used to fetch data from multiple sources (the cache and network), it also contains the Use Case classes (domain logic) which are uses to carry out operations on the data (such as creating an update, closing a conversation). The data layer contains the Data Models which are used in this module and modules higher than it (such as the UI module). The Remote and Local module models will map to this model, past here the model will never change so it makes no sense to perform any more mappings.

The Use Case classes will use a repository interface which defines the set of operations which can be performed within the data layer. This interface is the implemented by a Data Repository class which will use a Data Store Factory to retrieve an instance of a Data Store Interface to retrieve data from. This Data Store Interface will be implemented by an external layer and allows us to abstract the source of data from our data layer - allowing us to create a more flexible and decoupled data source

### Remote

The Remote module handles all communications with remote sources, in our case it makes simple API calls using a Retrofit interface. This service will be used within a Remote Data Source implementation class to retrieve instance of the Remote Model representations - this data store class implements the data store interface from the data layer. The data store class uses a model mapper that will map these Remote models to the model representation found within the Data module.

### Local

The Local module handles all communication with the local database which is used to cache data. For this our Database should use the Room architecture component library for the data base, whos data is accessed using DAO classes from said library.

The cache layer will have it's on Local Model representations which will be retrieved using the DAOs through the Local Data Source implementation class, which implements the data store interface from the data layer. This data store class will use a model mapper that will map these Cache models to the model representation found within the Local module.

## Conclusion

We will be happy to answer any questions that you may have on this approach, and if you want to lend a hand with the boilerplate then please feel free to submit an issue and/or pull request 🙂

Again to note, use Clean Architecture where appropriate. This is example can appear as over-architectured for what it is - but it is an example only. The same can be said for individual models for each layer, this decision is down to you.
