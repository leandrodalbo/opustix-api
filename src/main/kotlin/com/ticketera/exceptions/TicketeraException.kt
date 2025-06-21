package com.ticketera.exceptions


data class TicketeraException(val error: ErrorMessage) : Exception()