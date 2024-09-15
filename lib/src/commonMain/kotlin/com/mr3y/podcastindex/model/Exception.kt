package com.mr3y.podcastindex.model

class BadRequestException(override val message: String) : Exception(message)

class ValidationException(override val message: String) : Exception(message)

class UnknownException(override val message: String) : Exception(message)
