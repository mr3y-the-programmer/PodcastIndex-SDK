# PodcastIndex-SDK

## About
Unofficial Type-safe PodcastIndex SDK for Kotlin Multiplatform Supporting Android/Desktop/iOS platforms.
[Podcast Index](https://podcastindex.org/) is a large, categorized, and free index of podcasts. It offers a REST API to access the index. This SDK is a wrapper around the API that enables developers building podcast experiences type-safe access by leveraging Kotlin. Additionally, it handles things like Authentication, Serialization, logging, retrying failed requests, and more out of the box for you.

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

## How to use
1. Initialize client instance by calling `PodcastIndexClient`:
```kotlin
val client = PodcastIndexClient(/*your API key*/, /*your API secret*/, /*User agent (i.e "MyPodcastApp/1.2"*/) {
    // Optionally, this function provides a lambda that allows you to customize/configure the client 
    enableLogging = if (BuildConfig.DEBUG) true else false
    // you can also configure things like loggingTag, maxRetries, defaultTimeout..etc
}
```
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
Similarly, accessing **Podcasts**, **Episodes** endpoints follows the same approach.
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
