package com.omdb.cleanmovies.models

data class Data<RequestData>(
    val responseType: Status,
    val data: RequestData? = null,
    val error: Error? = null
) {
    companion object {
        fun <RequestData> loading(): Data<RequestData> {
            return Data(Status.LOADING)
        }

        fun <RequestData> error(throwable: Throwable): Data<RequestData> {
            return Data(
                responseType = Status.ERROR,
                error = Error(throwable.localizedMessage)
            )
        }

        fun <RequestData> success(data: RequestData): Data<RequestData> {
            return Data(
                responseType = Status.SUCCESSFUL,
                data = data
            )
        }
    }
}

enum class Status { SUCCESSFUL, ERROR, LOADING }