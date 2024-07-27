package org.coursera.littlelemon.feature.profile


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.coursera.littlelemon.R
import org.coursera.littlelemon.feature.shared.Onboarding
import org.coursera.littlelemon.feature.shared.User
import org.coursera.littlelemon.feature.shared.deleteUser
import org.coursera.littlelemon.feature.shared.fetchUser
import org.coursera.littlelemon.feature.shared.toProperName
import org.coursera.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun ProfileScreen(user: User, onLogOut: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 35.dp),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {
        Text(text = stringResource(id = R.string.profile_form_title),
             textAlign = TextAlign.Start,
             style = MaterialTheme.typography.titleLarge,
             modifier = Modifier.fillMaxWidth()
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
            Text(text = "Name: ", fontWeight = FontWeight.Bold)
            Text(text = "${user.firstName.toProperName()} ${user.lastName.toProperName()}")
        }

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
            Text(text = "Email: ", fontWeight = FontWeight.Bold)
            Text(text = user.email)
        }

        Button(onClick = { onLogOut() },
               modifier = Modifier.padding(20.dp)) {
            Text(text = stringResource(R.string.profile_button_logout))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    LittleLemonTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
            ProfileScreen(User("preview", "preview", "preview@coursera.org")) {}
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenPreviewDark() {
    LittleLemonTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
            ProfileScreen(User("preview", "preview", "preview@coursera.org")) {}
        }
    }
}
