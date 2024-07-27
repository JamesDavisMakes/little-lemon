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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.coursera.littlelemon.R
import org.coursera.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun SearchHeader(searchPhrase: String, onSearchUpdated: (String) -> Unit) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(350.dp)) {
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

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {

                    TextField(value = searchPhrase,
                              onValueChange = { onSearchUpdated(it) },
                              leadingIcon = { Icon(imageVector = Icons.Default.Search,
                                                   contentDescription = "Search icon")
                              },
                              trailingIcon = { IconButton(onClick = { onSearchUpdated("") },
                                                          enabled = searchPhrase.isNotEmpty(),
                                                          modifier = Modifier.alpha(if (searchPhrase.isNotEmpty()) 1f else 0f)) {
                                  Icon(imageVector = Icons.Default.Close,
                                       contentDescription = "Clear text icon",
                                       modifier = Modifier)
                              }
                              },
                              keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                              placeholder = { Text(text = "Enter search phrase") },
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(0.dp)
                                  .clip(RoundedCornerShape(12.dp)))

                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchHeaderPreview() {
    LittleLemonTheme {
        SearchHeader("Preview") {}
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SearchHeaderPreviewDark() {
    LittleLemonTheme {
        SearchHeader("Preview") {}
    }
}