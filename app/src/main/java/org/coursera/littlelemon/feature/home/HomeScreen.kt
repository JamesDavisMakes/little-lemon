package org.coursera.littlelemon.feature.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.coursera.littlelemon.R
import org.coursera.littlelemon.data.LittleLemonDatabase
import org.coursera.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun HomeScreen(database: LittleLemonDatabase) {
    val menuItems by database.menuItemDao().getAll().observeAsState(emptyList())
    var searchPhrase by rememberSaveable { mutableStateOf("") }
    val filteredMenuItems = menuItems.filter { item -> item.title.contains(searchPhrase.trim(), true) }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchHeader(searchPhrase, onSearchUpdated = { query -> searchPhrase = query })
        FoodMenu(modifier = Modifier.fillMaxSize(), filteredMenuItems)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    LittleLemonTheme {
        HomeScreen(database = LittleLemonDatabase.getDatabase(LocalContext.current))
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreviewDark() {
    LittleLemonTheme {
        HomeScreen(database = LittleLemonDatabase.getDatabase(LocalContext.current))
    }
}
