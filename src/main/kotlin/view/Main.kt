// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import controller.LoginController
import view.PasswordScreen
import view.LoginScreen

import model.Password

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {

//        var data: MutableList<Password> =



        val p = PasswordScreen()
        p.passwordScreen()
//        val p = LoginScreen()
//        p.loginPage()
    }
}
