package org.coursera.littlelemon.feature.shared

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.coursera.littlelemon.R
import org.coursera.littlelemon.ui.theme.LittleLemonTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LittleLemonAppBar(showBackButton: Boolean = true, showProfileButton: Boolean = true, onProfileClicked: () -> Unit, onBackClicked: () -> Unit) {
    val logoRes = if (isSystemInDarkTheme()) {
            R.drawable.branding_logo_horizontal_text_transparent_dark
        } else {
            R.drawable.branding_logo_horizontal_text_transparent
        }

    TopAppBar(title = { Image(painter = painterResource(id = logoRes),
                              contentDescription = stringResource(R.string.description_logo),
                              modifier = Modifier.fillMaxWidth().size(35.dp)) },
              colors = TopAppBarDefaults.topAppBarColors(
                  containerColor = MaterialTheme.colorScheme.background,
                  titleContentColor = MaterialTheme.colorScheme.onBackground),
              navigationIcon = {
                  IconButton(onClick = { onBackClicked() },
                             enabled = showBackButton,
                             modifier = Modifier.alpha(if (showBackButton) 1.0f else 0.0f)) {
                      Image(
                          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                          contentDescription = "Menu back button",
                          colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground))
                  }
              },
              actions = {
                  IconButton(onClick = { onProfileClicked() },
                             enabled = showProfileButton,
                             modifier = Modifier.alpha(if (showProfileButton) 1.0f else 0.0f)) {
                      Image(
                          imageVector = Icons.Default.AccountCircle,
                          contentDescription = "Profile button",
                          colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                      )
                  }
              })
}

@Preview(showBackground = true)
@Composable
private fun AppBarPreview() {
    LittleLemonTheme {
        LittleLemonAppBar(true, true, {}, {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppBarPreviewDark() {
    LittleLemonTheme {
        LittleLemonAppBar(true, true, {}, {})
    }
}
