package ru.pugovishnikova.example.vkvideoplayer.domain.util

import retrofit2.Response
import ru.pugovishnikova.example.vkvideoplayer.util.Result

inline fun <reified T> responseToResult(
    response: Response<T>?,
): Result<T, NetworkError> {
    if (response?.isSuccessful == true) return Result.Success(response.body()!!)
    return Result.Error(NetworkError.NO_INTERNET)
}
