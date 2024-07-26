package org.coursera.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coursera.littlelemon.data.LittleLemonDatabase
import org.coursera.littlelemon.data.menu.MenuItem
import org.coursera.littlelemon.data.menu.MenuResponse
import org.coursera.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        LittleLemonDatabase.getDatabase(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                LittleLemonNavigation(database)
            }
        }

        lifecycleScope.launch {
            withContext(IO) {
                if (database.menuItemDao().isEmpty()) {
                    val menuItemList = fetchMenu()
                    database.menuItemDao().insertAll(*menuItemList.toTypedArray())
                }
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItem> {
        return httpClient.get(getString(R.string.menu_url)).body<MenuResponse>().menu
    }
}
