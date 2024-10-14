package com.mr3y.podcastindex

@RequiresOptIn(
    "This API is internal and isn't meant to be used in downstream projects. It's public only for technical limitations, and it is subject to be changed or removed without any prior notice or migration guide",
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
