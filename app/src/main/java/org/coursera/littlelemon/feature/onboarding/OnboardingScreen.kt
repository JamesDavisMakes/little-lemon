package org.coursera.littlelemon.feature.onboarding

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.coursera.littlelemon.R
import org.coursera.littlelemon.feature.shared.isValidUser
import org.coursera.littlelemon.ui.theme.littleLemonFieldDefaults
import org.coursera.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun OnboardingScreen(onUserUpdated: (firstName: String, lastName: String, email: String) -> Unit) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var buttonEnabled by rememberSaveable { mutableStateOf(isValidUser(firstName, lastName, email)) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.onboarding_header),
             textAlign = TextAlign.Center,
             style = MaterialTheme.typography.headlineMedium,
             color = MaterialTheme.colorScheme.onTertiary,
             modifier = Modifier
                 .fillMaxWidth()
                 .background(MaterialTheme.colorScheme.tertiary)
                 .padding(50.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                   .fillMaxSize(1f)
                   .padding(horizontal = 20.dp, vertical = 35.dp)
        ) {
            Text(text = stringResource(R.string.onboarding_form_title),
                 textAlign = TextAlign.Start,
                 style = MaterialTheme.typography.titleLarge,
                 modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(value = firstName,
                              label = { Text(text = stringResource(R.string.onboarding_label_first)) },
                              singleLine = true,
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(top = 20.dp),
                              colors = OutlinedTextFieldDefaults.littleLemonFieldDefaults(),
                              keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                              onValueChange = {
                                  firstName = it
                                  buttonEnabled = isValidUser(firstName, lastName, email)
                              })
            OutlinedTextField(value = lastName,
                              label = { Text(text = stringResource(R.string.onboarding_label_last)) },
                              singleLine = true,
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(top = 10.dp),
                              colors = OutlinedTextFieldDefaults.littleLemonFieldDefaults(),
                              keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                              onValueChange = {
                                  lastName = it
                                  buttonEnabled = isValidUser(firstName, lastName, email)
                              })
            OutlinedTextField(value = email,
                              label = { Text(text = stringResource(R.string.onboarding_label_email)) },
                              singleLine = true,
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(top = 10.dp),
                              colors = OutlinedTextFieldDefaults.littleLemonFieldDefaults(),
                              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,
                                                                imeAction = ImeAction.Done),
                              onValueChange = {
                                  email = it
                                  buttonEnabled = isValidUser(firstName, lastName, email)
                              })
            Button(onClick = { onUserUpdated(firstName, lastName, email) },
                   enabled = buttonEnabled,
                   modifier = Modifier.padding(20.dp)) {
                Text(text = stringResource(R.string.onboarding_button_register))
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    LittleLemonTheme {
        OnboardingScreen { f, l, e -> }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingScreenPreviewDark() {
    LittleLemonTheme {
        OnboardingScreen { f, l, e -> }
    }
}
