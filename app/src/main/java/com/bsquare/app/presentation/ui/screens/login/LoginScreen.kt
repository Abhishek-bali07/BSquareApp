package com.bsquare.app.presentation.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.states.ComposeLaunchEffect
import com.bsquare.app.presentation.ui.custom_composable.AppButton
import com.bsquare.app.presentation.ui.custom_composable.MobileNumberValidator
import com.bsquare.app.presentation.ui.view_models.LoginViewModel


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(

                backgroundColor = Color.Transparent,
                elevation = 2.dp,
                title = { Text("") },
                navigationIcon = {
                    Image(

                        modifier = Modifier
                            .size(100.dp)
                            .padding(horizontal = 8.dp),
                        painter = painterResource(id = R.drawable.logox), contentDescription = null
                    )
                },
                actions = {
                    Button(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = "LOGIN")
                    }
                }
            )

            LoginImageSection(R.drawable.gslogo)

            LoginInputSection(loginViewModel)

            AppButton(
                enable = loginViewModel.enableBtn.value,
                loading = loginViewModel.loginLoading.value,
                action = loginViewModel::appLogin,
                name = R.string.login

            )

        }
    }


    loginViewModel.toastNotify.ComposeLaunchEffect(
        intentionalCode = { message ->
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    ) { "" }
}

@Composable
private fun ColumnScope.LoginInputSection(loginViewModel: LoginViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1.2f)
            .padding(horizontal = 12.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Company Name",
            color = Color.Black,
            fontWeight = FontWeight.Bold,

            )
        Spacer(modifier = Modifier.height(10.dp))
        CompanyTextField(loginViewModel)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Mobile No",
            color = Color.Black,
            fontWeight = FontWeight.Bold,

            )
        Spacer(modifier = Modifier.height(10.dp))

        MobileInputTextField(loginViewModel)

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Password",
            color = Color.Black,
            fontWeight = FontWeight.Bold,

            )
        Spacer(modifier = Modifier.height(10.dp))

        PasswordInputTextField(loginViewModel)


    }
}

@Composable
fun PasswordInputTextField(loginViewModel: LoginViewModel) {
    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray
        ),
        modifier = Modifier.fillMaxWidth(),
        value = loginViewModel.password.value,
        onValueChange = loginViewModel::onChangePassword,
        trailingIcon = {
            IconButton(onClick = { loginViewModel.showPassword = !loginViewModel.showPassword }) {
                Icon(
                    imageVector = if (loginViewModel.showPassword) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    contentDescription = if (loginViewModel.showPassword) "Show Password" else "Hide Password"
                )
            }
        },
        visualTransformation = if (loginViewModel.showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )

}

@Composable
fun MobileInputTextField(loginViewModel: LoginViewModel) {
    val focusMgr = LocalFocusManager.current
    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray
        ),
        modifier = Modifier.fillMaxWidth(),
        value = loginViewModel.mobile.value,
        onValueChange = {
            if (it.isEmpty() || it.contains(Regex("^\\d+\$"))) {
                loginViewModel.mobile.value = derivedStateOf {
                    it.take(MobileNumberValidator.MOBILE_NUMBER_PATTERN.count {
                        it == MobileNumberValidator.MASK_CHAR
                    })
                }.value
            }
            if (loginViewModel.mobile.value.length == 10) focusMgr.clearFocus()
        },
        visualTransformation = MobileNumberValidator(),

        )

}

@Composable
fun CompanyTextField(loginViewModel: LoginViewModel) {
    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray
        ),
        modifier = Modifier.fillMaxWidth(),
        value = loginViewModel.companyName.value,
        onValueChange = loginViewModel::onChangeName


    )

}


@Composable
private fun ColumnScope.LoginImageSection(gslogo: Int) {
    Box(modifier = Modifier.weight(0.2f), contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(id = gslogo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(fraction = 1.8f)


        )
    }
}



