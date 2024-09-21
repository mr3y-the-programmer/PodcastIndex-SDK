package com.mr3y.podcastindex.model

public class BadRequestException(override val message: String) : Exception(message)

public class UnknownException(override val message: String) : Exception(message)
