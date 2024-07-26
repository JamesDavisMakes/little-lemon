package org.coursera.littlelemon.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.coursera.littlelemon.R
import org.coursera.littlelemon.data.menu.MenuItem
import org.coursera.littlelemon.ui.theme.LittleLemonTheme
import org.coursera.littlelemon.ui.theme.backgroundLight

@Composable
fun HomeScreen(menuItems: List<MenuItem>, onProfileOpen: () -> Unit) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp))
    {
        Image(painter = painterResource(id = R.drawable.home_header_background),
              contentDescription = "",
              contentScale = ContentScale.Crop,
              modifier = Modifier
                  .fillMaxSize()
                  .blur(4.dp))
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = stringResource(R.string.home_title),
                 style = MaterialTheme.typography.displayMedium,
                 color = MaterialTheme.colorScheme.primary,
                 fontWeight = FontWeight.Bold,
                 fontFamily = FontFamily.Serif,
                 modifier = Modifier
                     .background(MaterialTheme.colorScheme.tertiary)
                     .padding(5.dp)
            )
            Text(text = stringResource(R.string.home_subtitle),
                 style = MaterialTheme.typography.displaySmall,
                 color = MaterialTheme.colorScheme.onTertiary,
                 fontFamily = FontFamily.Serif,
                 modifier = Modifier
                     .background(MaterialTheme.colorScheme.tertiary)
                     .padding(5.dp))

            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxSize()){
                Text(text = stringResource(R.string.home_description_1),
                     style = MaterialTheme.typography.bodyLarge,
                     color = MaterialTheme.colorScheme.onTertiary,
                     modifier = Modifier
                         .background(MaterialTheme.colorScheme.tertiary)
                         .padding(5.dp))
                Text(text = stringResource(R.string.home_description_2),
                     style = MaterialTheme.typography.bodyLarge,
                     color = MaterialTheme.colorScheme.onTertiary,
                     modifier = Modifier
                         .background(MaterialTheme.colorScheme.tertiary)
                         .padding(5.dp))
                Text(text = stringResource(R.string.home_description_3),
                     style = MaterialTheme.typography.bodyLarge,
                     color = MaterialTheme.colorScheme.onTertiary,
                     modifier = Modifier
                         .background(MaterialTheme.colorScheme.tertiary)
                         .padding(5.dp))
                Text(text = "TODO: SEARCH BAR",
                     style = MaterialTheme.typography.bodyLarge,
                     color = MaterialTheme.colorScheme.tertiary,
                     modifier = Modifier
                         .fillMaxWidth()
                         .background(MaterialTheme.colorScheme.onTertiary)
                         .padding(10.dp))

            }



        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    LittleLemonTheme {
        HomeScreen(listOf()) {

        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreviewDark() {
    LittleLemonTheme {
        HomeScreen(listOf()) {

        }
    }
}
