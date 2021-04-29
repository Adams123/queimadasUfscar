package com.ufscar.queimadas_front.utils

fun <T> Collection<T>?.isNullOrEmpty() = this == null || this.isEmpty()
fun <T> Collection<T>?.isNotNullOrEmpty() = this != null && this.isNotEmpty()