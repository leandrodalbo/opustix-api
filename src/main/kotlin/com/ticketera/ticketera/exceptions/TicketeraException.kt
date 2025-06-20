package com.ticketera.ticketera.exceptions


data class TicketeraException(val error: ErrorMessage) : Exception()