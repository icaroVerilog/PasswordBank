// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import controller.LoginController
import view.BackgroundGray
import view.CustomBlue
import kotlin.system.exitProcess

@Composable
fun topBar(){

    TopAppBar(backgroundColor = CustomBlue, modifier = Modifier.height(58.dp)) {

    }
}

@Composable
fun topBarBtn1(){

    Button(
        onClick = {},
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
        colors = ButtonDefaults.buttonColors(Color.Gray),
        modifier = Modifier.width(100.dp).height(58.dp)) {
        Icon(Icons.Default.Add, contentDescription = "Localized description")
        Text("New")
    }

}

@Composable
fun topBarBtn2(){

    Button(
        onClick = {},
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
        colors = ButtonDefaults.buttonColors(Color.Gray),
        modifier = Modifier.width(100.dp).height(58.dp)) {
        Icon(Icons.Default.Search, contentDescription = "Localized description")
        Text("New")
    }

}

@Composable
fun menu(){
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Button(
            onClick = { expanded = true },
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray),
            modifier = Modifier.width(100.dp).height(58.dp)
        )
        {
            Icon(Icons.Default.Menu, contentDescription = "Localized description")
            Text("File")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(100.dp)

        ) {
            DropdownMenuItem(onClick = { /* Handle refresh! */ }) {
                Text("Refresh")
            }
            DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                Text("Settings")
            }
            Divider()
            DropdownMenuItem(onClick = { exitProcess(-1) }) {
                Text("Exit")
            }
        }
    }
}

@Composable
fun LoginField(){

    val loginCTRL = LoginController()

    BoxWithConstraints(){
        val boxWithConstraintsScope2 = this

        val parentMaxWidthStr: String = boxWithConstraintsScope2.maxWidth.toString()
        val parentMaxHeightStr: String = boxWithConstraintsScope2.maxHeight.toString()
        val aux: List<String> = parentMaxWidthStr.split(".")
        val aux2: List<String> = parentMaxHeightStr.split(".")
        val parentMaxWidth: Double = aux[0].toDouble() * 0.80
        val parentMaxHeight: Double = aux2[0].toDouble() * 0.70

        Card(
            modifier = Modifier.width(parentMaxWidth.dp).height(parentMaxHeight.dp).align(Alignment.Center),
            shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp, bottomStart = 2.dp, bottomEnd = 2.dp),
            backgroundColor = CustomBlue
        )
        {
            Column (
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {


                val username = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }

                val emptyUsername = remember { mutableStateOf(false) }
                val emptyPassword = remember { mutableStateOf(false) }

                val passwordVisibility = remember { mutableStateOf(false) }

                val textFieldWidth: Double = parentMaxWidth * 0.8

                TextField(
                    value = username.value,
                    onValueChange = { newLogin -> username.value = newLogin; emptyUsername.value = false},
                    modifier = Modifier.width(textFieldWidth.dp),
                    singleLine = true,
                    placeholder = {
                        if (emptyUsername.value == false){
                            Text("Username")
                        }
                        else {
                            Text("Invalid username")
                        }
                    },
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BackgroundGray,
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.Red,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        errorCursorColor = Color.Black,
                    ),
                    isError = emptyUsername.value,
                    leadingIcon = { Icon(imageVector = Icons.Default.AccountBox, tint = CustomBlue, contentDescription = "Login field")},
                )
                Spacer(modifier = Modifier.size(20.dp))
                TextField(
                    value = password.value,
                    modifier = Modifier.width(textFieldWidth.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                    isError = emptyPassword.value,
                    leadingIcon = { Icon(imageVector = Icons.Default.Lock, tint = CustomBlue, contentDescription = "Password field")},
                    visualTransformation = if(passwordVisibility.value == false) PasswordVisualTransformation('*') else VisualTransformation.None,
                    onValueChange = {
                            newPassword -> password.value = newPassword; emptyPassword.value = false
                    },
                    placeholder = {
                        if (emptyUsername.value == false){
                            Text("Password")
                        }
                        else {
                            Text("Invalid password")
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BackgroundGray,
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.Red,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        errorCursorColor = Color.Black,
                    ),
                    trailingIcon = {
                        IconButton( onClick = { passwordVisibility.value = !passwordVisibility.value})
                        {
                            Icon(
                                imageVector = if(passwordVisibility.value == true) Icons.Default.AccountBox else Icons.Default.Add,
                                contentDescription = "visibility",
                                tint = CustomBlue
                            )
                        }
                    },
                )
                Spacer(modifier = Modifier.size(35.dp))
                Button(
                    onClick = {

                        if (username.value.text == ""){
                            emptyUsername.value = true
                        }
                        if (password.value.text == ""){
                            emptyPassword.value = true
                        }
                        else {
                            loginCTRL.handleLogin(username.value.text, password.value.text)
                        }
                    },
                    shape = RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp, bottomStart = 2.dp, bottomEnd = 2.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundGray),
                    modifier = Modifier.width(100.dp).height(58.dp)) {
                    Text("Login")
                }
            }
        }
    }


}

@Composable
fun loginPage(){
    Column() {
        Box(contentAlignment = Alignment.TopStart){
            topBar()
            Row(horizontalArrangement = Arrangement.Start) {
                Spacer(Modifier.width(5.dp))
                menu()
                Spacer(Modifier.width(5.dp))
                topBarBtn2()
                Spacer(Modifier.width(5.dp))
                topBarBtn1()
            }
        }

        Row (){

            /* Left Pannel */
            BoxWithConstraints(){
                val boxWithConstraintsScope = this

                val parentMaxWidthStr: String = boxWithConstraintsScope.maxWidth.toString()
                val aux: List<String> = parentMaxWidthStr.split(".")
                val parentMaxWidth: Double = aux[0].toDouble() * 0.60

                Card(
                    modifier = Modifier.width(parentMaxWidth.dp).fillMaxHeight(),
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                    backgroundColor = BackgroundGray
                ) {
                    LoginField()
                }
            }

            /* Right Pannel */
            BoxWithConstraints(){
                Card(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                    backgroundColor = Color.White
                ) {
                    val image: Painter = painterResource("drawable/keylock.png")
                    Image(painter = image,contentDescription = "", modifier = Modifier.scale(0.5.toFloat()).alpha(0.5.toFloat()))
                }
            }

        }
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {

        loginPage()

    }
}
