package com.mr3y.podcastindex

@RequiresOptIn(
    "This API is internal and is subject to be changed or removed without any prior notice or migration guide",
    level = RequiresOptIn.Level.WARNING,
)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
@Retention(AnnotationRetention.BINARY)
public annotation class InternalPodcastIndexApi
