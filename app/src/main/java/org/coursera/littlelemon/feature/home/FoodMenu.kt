package org.coursera.littlelemon.feature.home

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import org.coursera.littlelemon.data.menu.MenuItem
import org.coursera.littlelemon.ui.theme.LittleLemonTheme
import kotlin.random.Random

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FoodMenu(modifier: Modifier = Modifier, menuItems: List<MenuItem>) {
    var selectedCategory by rememberSaveable { mutableStateOf("") }
    val categories = menuItems.map { item -> item.category }.distinct()
    val filteredMenuItems = menuItems.filter { item -> item.category == selectedCategory || selectedCategory.isEmpty() }

    LazyColumn(modifier.fillMaxSize()) {
        item {
            Categories(categories = categories,
                       selectedCategory,
                       onCategorySelected = { newCategory ->
                           selectedCategory = newCategory
                       })
            HorizontalDivider()
        }

        items(filteredMenuItems) { item ->
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(12.dp),) {
                    Text(text = item.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = item.description,
                         style = MaterialTheme.typography.bodyMedium,
                         maxLines = 2,
                         overflow = TextOverflow.Ellipsis)
                    Text(text = "$${item.price}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                }
                GlideImage(modifier = Modifier
                    .height(120.dp)
                    .width(120.dp),
                           model = item.imageUrl,
                           contentDescription = "${item.title} photo",
                           contentScale = ContentScale.Crop)
            }
        }
    }
}

@Composable
fun Categories(categories: List<String>, selectedCategory: String, onCategorySelected: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp)) {
        Text(text = "ORDER FOR DELIVERY",
             style = MaterialTheme.typography.titleMedium,
             fontWeight = FontWeight.Bold,
             modifier = Modifier.padding(horizontal = 12.dp))
        LazyRow {
            items(categories) { category ->
                FilterChip(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    selected = category == selectedCategory,
                    onClick = {
                        onCategorySelected(if (selectedCategory == category) "" else category)
                    },
                    label = { Text(text = category) })
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun FoodMenuPreview() {
    LittleLemonTheme {
        FoodMenu(
            menuItems = listOf(
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true",
                    "Coffee"
                ),
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "",
                    "Coffee"
                ),
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "",
                    "American"
                ),
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "",
                    "American"
                ),
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "",
                    "Indian"
                ),
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "",
                    "Chinese"
                ),
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "",
                    "Chinese"
                ),
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "",
                    "Dessert"
                ),
                MenuItem(
                    1,
                    "Title",
                    "A lengthy description",
                    "9.99",
                    "",
                    "Dessert"
                ),
            )
        )
    }
}
