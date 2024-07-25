package org.coursera.littlelemon.feature.shared

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Patterns
import androidx.core.content.edit
import org.coursera.littlelemon.feature.shared.Preferences.Companion.EMAIL
import org.coursera.littlelemon.feature.shared.Preferences.Companion.FILE_NAME
import org.coursera.littlelemon.feature.shared.Preferences.Companion.FIRST_NAME
import org.coursera.littlelemon.feature.shared.Preferences.Companion.LAST_NAME
import java.util.Locale

data class User(
    val firstName: String,
    val lastName: String,
    val email: String
)

abstract class Preferences {
    companion object {
        const val FIRST_NAME = "#first"
        const val LAST_NAME = "#last"
        const val EMAIL = "#email"
        const val FILE_NAME = "User"
    }
}

fun Context.saveUser(firstName: String, lastName: String, email: String): User {
    val user = User(firstName.trim(), lastName.trim(), email.trim())
    getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit {
        putString(FIRST_NAME, user.firstName)
        putString(LAST_NAME, user.lastName)
        putString(EMAIL, user.email)
    }

    return user
}

fun Context.deleteUser() {
    getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit {
        putString(FIRST_NAME, null)
        putString(LAST_NAME, null)
        putString(EMAIL, null)
    }
}

fun Context.fetchUser(): User? {
    val sharedPreferenes = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
    val firstName = sharedPreferenes.getString(FIRST_NAME, "")!!
    val lastName = sharedPreferenes.getString(LAST_NAME, "")!!
    val email = sharedPreferenes.getString(EMAIL, "")!!

    return if (isValidUser(firstName, lastName, email)) {
        User(firstName, lastName, email)
    } else {
        null
    }
}

fun isValidUser(firstName: String, lastName: String, email: String): Boolean {
    var valid = true
    arrayOf(firstName, lastName, email).forEach { valid = valid && it.isNotBlank() }
    valid = valid && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    return valid
}

fun String.toProperName(): String = trim().replaceFirstChar {
    if (it.isLowerCase()) {
        it.titlecase(Locale.getDefault())
    } else {
        it.toString()
    }
}
