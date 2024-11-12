# Changelog

## v0.4.0 - 2024-11-13

- Added: Allow customization of Http client configuration. Thanks to @msimonides for contributing this feature.
- Make Ktor client dependency available automatically on classpath using `api` instead of `implementation` in previous releases.
- Clarify Opt-in annotation message.

## v0.3.6 - 2024-10-15

- BREAKING: Remove `Authentication` from Public API surface. this shouldn't have been used by anyone, it was just used internally, and its usages are refactored now.
- Prompt Users to Opt-in to call `PodcastIndexClient` constructor & `@PodcastIndexConfigDsl` as these aren't supposed to be used by users, and they're just public for technical limitations.

## v0.3.5 - 2024-10-14

- BREAKING: Starting from this version support for Ktor 3 has been added alongside Ktor 2, and as a part of this change the old `io.github.mr3y-the-programmer:podcastindex-sdk` artifact has been replaced by 2 mutually exclusive artifacts: ktor2, ktor3 respectively. 
Update your dependency declaration to use the artifact that matches Ktor version used in your project, Or use whichever one if you don't have Ktor in your project's classpath:
```kotlin
// Ktor 2
implementation("io.github.mr3y-the-programmer:podcastindex-sdk-ktor2:0.3.5")
// Or Ktor 3
implementation("io.github.mr3y-the-programmer:podcastindex-sdk-ktor3:0.3.5")
```
- BREAKING: Update the import statement of `PodcastIndexClient` from `com.mr3y.podcastindex.PodcastIndexClient` to `com.mr3y.podcastindex.ktor2.PodcastIndexClient`/`com.mr3y.podcastindex.ktor3.PodcastIndexClient`

## v0.3.1 - 2024-10-12

- Fixed: Serialization crash when podcastFeed's `explicit` is represented as an Int not a Boolean.

## v0.3.0 - 2024-10-11

- Added: Value service is now supported.
- Added: Recent service is now supported.
- Added: Stats & Categories endpoints/services are now supported.
- Added: Handle case when API rate limit has been reached.

## v0.2.5 - 2024-09-25

- BREAKING: all Ids now are represented as `Long` instead of `Int`.
- Add some missing fields into different models.
- Fix some serialization bugs.

## v0.2.0 - 2024-09-23

- Add IOS support.
- Change `imageUrlHash` type to Long.

## v0.1.1 - 2024-09-23

- Internal improvements & documentation updates

## v0.1.0 - 2024-09-22

- Initial Release
