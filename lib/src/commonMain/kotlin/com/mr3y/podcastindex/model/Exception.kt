package com.mr3y.podcastindex.model

class BadRequestException(override val message: String) : Exception(message)

class InternalException(override val message: String?, override val cause: Throwable?) : Exception(message, cause)

class UnknownException(override val message: String) : Exception(message)
