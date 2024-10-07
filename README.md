# PodcastIndex-SDK

[![Maven Central](https://img.shields.io/maven-central/v/io.github.mr3y-the-programmer/podcastindex-sdk?label=Maven%20Central)](https://search.maven.org/artifact/io.github.mr3y-the-programmer/podcastindex-sdk)
![Github Actions](https://github.com/mr3y-the-programmer/PodcastIndex-SDK/actions/workflows/build.yml/badge.svg)
![badge-android](https://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](https://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat)
![badge-desktop](https://img.shields.io/badge/platform-desktop-DB413D.svg?style=flat)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
<a href="https://mailchi.mp/kotlinweekly/kotlin-weekly-426"><img alt="Kotlin Weekly" src="https://img.shields.io/badge/Kotlin_Weekly-%23426-purple"/></a>

<p align="center">
  Jump to:
  <a href="#about">About</a> |
  <a href="#download">Download</a> |
  <a href="#how-to-use">How to use</a> |
  <a href="#advanced">Advanced</a> |
  <a href="#support-table">Roadmap</a> |
  <a href="#license">License</a>
</p>

<hr>

## About
Unofficial Type-safe PodcastIndex SDK for Kotlin Multiplatform Supporting Android/Desktop/iOS platforms.

[Podcast Index](https://podcastindex.org/) is a large, categorized, and free index of podcasts. It offers a REST API to access the index. This SDK is a wrapper around the API that offers idiomatic Kotlin API plus handling things like Authentication, retrying failed authenticated requests in odd scenarios, Logging, Serialization, and more for developers looking to build podcast experiences.

## Download
Add the Maven Central repository if it is not already there:
```kotlin
repositories {
   mavenCentral()
}
```
In Kotlin Multiplatform projects, add the dependency:
```kotlin
commonMain {
    dependencies {
        implementation("io.github.mr3y-the-programmer:podcastindex-sdk:<latest-version>")
    }
}
```
Or in a single-platform project:
```kotlin
dependencies {
   implementation("io.github.mr3y-the-programmer:podcastindex-sdk:<latest-version>")
}
```
**Compatibility**:
   - Java 8+.
   - Kotlin 1.9+.
   - Ktor 2.

## How to use
1. Initialize client instance by calling `PodcastIndexClient` with your credentials:
```kotlin
val client = PodcastIndexClient(BuildConfig.API_KEY, BuildConfig.API_SECRET, userAgent = "MyPodcastApp/1.2")
```
That's it, now you're ready to start interacting with different SDK services, `PodcastIndexClient` function also takes an optional config lambda parameter which allows you to enable/disable some properties, See <a href="#advanced">Advanced</a> section for more details 

### Search Service
<details>
  <summary><b>Search Podcasts by term: Click Here to See</b></summary>

````kotlin
// This method takes other optional parameters like limit, includeSimilar...etc that allow you to fine-tune the search result.
val result: MultiplePodcastsResult = client.search.forPodcastsByTerm(term = "android")
````
</details>

<details>
  <summary><b>Search Podcasts by title: Click Here to See</b></summary>

````kotlin
// This method takes other optional parameters like limit, includeSimilar...etc that allow you to fine-tune the search result.
val result: MultiplePodcastsResult = client.search.forPodcastsByTitle(title = "Talking Kotlin")
````
</details>

<details>
  <summary><b>Search Episodes by person: Click Here to See</b></summary>

````kotlin
val result: MultipleEpisodesResult = client.search.forEpisodesByPerson(name = "john doe")
````
</details>

<details>
  <summary><b>Search Music Podcasts by term: Click Here to See</b></summary>

````kotlin
val result: MultiplePodcastsResult = client.search.forMusicPodcastsByTerm(term = "music")
````
</details>

Similarly, accessing **Podcasts**, **Episodes** services/endpoints follows the same approach.

## Advanced
- Enable logging to get better insights about outgoing requests which might be helpful for debugging (Default is false):
```kotlin
val client = PodcastIndexClient(...) {
    enableLogging = if (BuildConfig.DEBUG) true else false
}
```
Sensitive information like `API_KEY` & `API_SECRET` are masked and won't be printed out in logs so, you can enable this safely.

- Change the default tag used for logging (Default is "PodcastIndexSDK"):
```kotlin
val client = PodcastIndexClient(...) {
    loggingTag = "MyCustomTag"
}
```
- Specify the max number of retries when a request fails (Default is 3):
```kotlin
val client = PodcastIndexClient(...) {
    maxRetries = 5
}
```
- Enabling/Disabling request timeout (Default is true):
```kotlin
val client = PodcastIndexClient(...) {
    enableTimeout = false
}
```
- Specify the default timeout in milliseconds (Default is 10 seconds):
```kotlin
val client = PodcastIndexClient(...) {
    defaultTimeout = 5_000L // 5 seconds
}
```

## Support table
Some endpoints are still work-in-progress and not implemented yet (⌛)
|                            | 🔰 Status |                         | 🔰 Status |
|----------------------------|:---------:| ----------------------- | --------- |
| **Search**                 |           | **Recent**              |           |
| Search Podcasts            | ✔️        | Get Recent Episodes     | ⌛        |
| Search Podcasts by Title   | ✔️        | Get Recent Feeds        | ⌛        |
| Search Episodes by Person  | ✔️         | Get New Feeds           | ⌛        |
| Search Music Podcasts      | ✔️         | Get Soundbites          | ⌛        |
|                            |           |                         |           |
| **Podcast**                |           | **Episodes**            |           |
| Get Podcast By Feed ID     | ✔️        | Get Episodes By Feed ID | ✔️        |
| Get Podcast By Feed URL    | ✔️        | Get Episodes By Feed URL | ✔️        |
| Get Podcast By GUID        | ✔️        | Get Episode By GUID     | ✔️        |
| Get Podcasts By TAG        | ⌛        | Get Episode By ID       | ✔️        |
| Get Podcast By iTunes ID   | ✔️        | Get Episodes By iTunes ID | ✔️        |
| Get Podcasts By Medium     | ✔️        | Get Live Episodes       | ✔️        |
| Get Trending Podcasts      | ✔️        | Get Random Episodes     | ✔️        |
| Get Dead Podcasts          | ⌛        | Get Episodes by Feed GUID | ✔️         |
|                            |           |                         |           |
| **Apple Replacement**      |           | **Value**               |           |
| Search on Apple iTunes     | ⌛        | Get Value By Feed ID    | ⌛        |
| Lookup on Apple iTunes     | ⌛        | Get Value By Feed URL   | ⌛        |
|                            |           |                         |           |
| **Stats**                  |           | **Category**            |           |
| Get Current Stats          | ⌛        | Get Categories          | ⌛        |
|                            |           |                         |           |
| **Hub**                    |           | **Add Service**         |          |
| Notify Changes By Feed Id  | ⌛        | ...                     | ⌛         |
| Notify Changes By Feed URL | ⌛        | ...                     | ⌛         |

## License
```
Copyright 2024 MR3Y

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
