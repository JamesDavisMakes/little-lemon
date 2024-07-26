package org.coursera.littlelemon

import android.app.Application
import org.coursera.littlelemon.data.LittleLemonDatabase

class LittleLemonApplication : Application() {
    val database by lazy { LittleLemonDatabase.getDatabase(this) }
}