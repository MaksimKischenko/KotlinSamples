package com.example.composerules.remember

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable

fun ClickCounter(
    uppercase: Boolean,
    counterValue: Int,
    onCounterClick: () -> Unit
) {
    val evenOdd = remember(uppercase) { EvenOdd(uppercase) }

    Text(
        text = "Clicks: $counterValue ${evenOdd.check(counterValue)}",
        modifier = Modifier.clickable(onClick = onCounterClick)
    )
    Log.d("MyLog", "ClickCounter(counter = $counterValue, uppercase = $uppercase), $evenOdd")
}
class EvenOdd(private val uppercase: Boolean) {
    fun check(value: Int): String {
        var result = if (value % 2 == 0) "even" else "odd"
        if (uppercase) result = result.uppercase()
        return result
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun toString(): String {
        return "EvenOdd(uppercase = $uppercase, hashcode = ${hashCode().toHexString()})"
    }
}