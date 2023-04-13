package com.bsquare.app.presentation.ui.screens.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.ui.view_models.SplashViewModel
import com.bsquare.app.utills.helper_impl.SavableMutableState
import com.bsquare.core.common.enums.IntroStatus

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SplashImageSection(R.drawable.logox)

        SplashDescriptionSection(
            onClick = {
               // splashViewModel.onSplashBtnClicked()
            },
            isBtnVisible = splashViewModel.splashBtnStatus,
        )



    }
}

@Composable
private fun ColumnScope.SplashDescriptionSection(
    onClick: () -> Unit,
    isBtnVisible: SavableMutableState<IntroStatus>,
){
    Surface(modifier = Modifier.weight(1f))


    {
        Box(modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.splsh),
                contentScale = ContentScale.FillBounds
            ), contentAlignment = Alignment.Center){
                IconButton(
                    onClick = onClick,
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(color = Color.White),
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Splash button icon",
                        tint = Color.Red,
                        modifier = Modifier.padding(6.dp),
                    )
                }
        }
    }
}

@Composable
private  fun  ColumnScope.SplashImageSection(splash: Int){
    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = splash),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(fraction = 1.8f)



        )
    }
}
