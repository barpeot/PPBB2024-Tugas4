package hoods.com.jetlogin.ui.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetlogin.R
import hoods.com.jetlogin.ui.components.HeaderText
import hoods.com.jetlogin.ui.components.LoginTextField
import hoods.com.jetlogin.ui.theme.JetLoginTheme

val defaultpadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen() {
    val (userName, setuserName) = rememberSaveable{
        mutableStateOf("")
    }

    val (password, setPassword) = rememberSaveable{
        mutableStateOf("")
    }

    val (checked, onCheckedChange) = rememberSaveable{
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultpadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HeaderText(
            text = "Login",
            modifier = Modifier
                .padding(vertical = defaultpadding)
                .align(Alignment.Start)
        )
        LoginTextField(value = userName, onValueChange = setuserName, labelText = "Username", leadingIcon = Icons.Default.Person, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(itemSpacing))
        LoginTextField(value = password, onValueChange = setPassword, labelText = "Password", leadingIcon = Icons.Default.Lock, keyboardType = KeyboardType.Password, visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange )
                Text(text = "Remember me")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Forgot Password?")
            }
        }
        Spacer(modifier = Modifier.height(itemSpacing))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text("Login")
        }
        AlternativeLoginOptions(
            onIconClick = {index ->
                when(index){
                    0 -> {
                        Toast.makeText(context, "Instagram Login", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(context, "Github Login", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(context, "Google Login", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onSignUpClick = {},
            modifier = Modifier.fillMaxSize().wrapContentSize(align = Alignment.BottomCenter))
    }
}

@Composable
fun AlternativeLoginOptions(
    onIconClick: (index: Int) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val iconList = listOf(
        R.drawable.icon_instagram,
        R.drawable.icon_github,
        R.drawable.icon_google,
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Or sign in with")
        Row (horizontalArrangement = Arrangement.SpaceEvenly){
            iconList.forEachIndexed { index, i ->
                Icon(
                    painter = painterResource(id = i),
                    contentDescription = "Alternative Login",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            onIconClick(index)
                        }
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(defaultpadding))
            }
        }
        Spacer(modifier = Modifier.width(defaultpadding))
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Don't have an account?")
            Spacer(modifier = Modifier.width(defaultpadding))
            TextButton(onClick = onSignUpClick) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPrev(){
    JetLoginTheme {
        LoginScreen()
    }
}