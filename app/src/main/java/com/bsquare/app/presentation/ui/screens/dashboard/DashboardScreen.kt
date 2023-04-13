package com.bsquare.app.presentation.ui.screens.dashboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.states.resourceString
import com.bsquare.app.presentation.states.statusBarColor
import com.bsquare.app.presentation.ui.customise.Features
import com.bsquare.app.presentation.ui.view_models.DashboardViewModel


@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {


    Column(
        modifier = Modifier
            .statusBarColor(color = Color.Red)
            .fillMaxSize()
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        GreetingSection(dashboardViewModel)
        SurfaceSection(List)
    }

}

/*Box(
   modifier = Modifier
      .statusBarColor(color = Color.Red)
      .background(color = Color.Red)
      .fillMaxSize(),
   contentAlignment = Alignment.TopCenter
){

}*/




@Composable
fun GreetingSection(dashboardViewModel: DashboardViewModel) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Red)
            .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Image(
                painter = R.drawable.profile.resourceImage(),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hello ${dashboardViewModel.shortDetail.value?.userName}",
                    style = TextStyle(
                        color = Color.White, fontSize = 18.sp,
                    ),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )

                Text(
                    text = R.string.welcome_to_bsquare.resourceString(),
                    style = TextStyle(
                        color = Color.White, fontSize = 12.sp,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 5.dp)

                )
            }
        }


        Icon(
            painter = R.drawable.bell.resourceImage(),
            tint = Color.White,
            contentDescription = "notify",
            modifier = Modifier
                .size(30.dp)
        )
    }
}


@Composable
fun SurfaceSection(features:List<Features>) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),

        )

    }
}




@Composable
fun FeatureItem(
    features: Features
){
   BoxWithConstraints(
       modifier = Modifier
           .padding(7.5.dp)
           .aspectRatio(1f)
           .clip(RoundedCornerShape(10.dp))
           .background(color = Color.Blue)
   ) {
        val  width = constraints.maxWidth
        val  height = constraints.maxHeight

   }
}