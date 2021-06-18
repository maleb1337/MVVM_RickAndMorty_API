package cl.maleb.testbetterfly.utils

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {

    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
    class Success<T>(data: T) : Resource<T>(data)

}
