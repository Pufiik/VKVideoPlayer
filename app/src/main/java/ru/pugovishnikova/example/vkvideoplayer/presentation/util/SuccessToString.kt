package ru.pugovishnikova.example.vkvideoplayer.presentation.util

import android.content.Context
import ru.pugovishnikova.example.vkvideoplayer.R
import ru.pugovishnikova.example.vkvideoplayer.domain.util.DatabaseSuccess

fun DatabaseSuccess.toString(context: Context): String {
    val resId = when(this) {
        DatabaseSuccess.INSERT_SUCCESS -> R.string.database_success_insert
        DatabaseSuccess.DELETE_SUCCESS -> R.string.database_success_delete
        DatabaseSuccess.DELETE_ALL_SUCCESS -> R.string.database_success_delete_all
    }
    return context.getString(resId)
}