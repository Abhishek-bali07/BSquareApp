package com.bsquare.app.presentation.ui.screens.dashboard


import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size
import com.bsquare.app.R
import com.bsquare.app.presentation.states.*
import com.bsquare.app.presentation.ui.activities.MainActivity
import com.bsquare.app.presentation.ui.view_models.DashboardViewModel
import com.bsquare.core.entities.Feature
import kotlinx.coroutines.Dispatchers


@OptIn(ExperimentalFoundationApi::class)
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
        FeatureSection(features = dashboardViewModel.features, dashboardViewModel)


    }

}

@Composable
fun ColumnScope.CalenderSection(
    viewModel: DashboardViewModel
) {
    if (viewModel.features.isNotEmpty()){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 7.5.dp, end = 7.5.dp, bottom = 10.dp, top = 1.dp)
        ) {
            AndroidView(factory = { CalendarView(it) },
                modifier = Modifier.weight(1f),
                update = {
                    it.setOnDateChangeListener { calendarView, year, month, day ->
                        viewModel.date = "$year - ${month + 1}- $day"
                        viewModel.getFeatureData()
                    }
                })
        }
    }

}


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
            if (dashboardViewModel.shortDetail.value != null) {
                dashboardViewModel.shortDetail.value?.apply {
                    Log.d("TESTING", "GreetingSection: requestedImage $userImage")
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .decoderFactory(SvgDecoder.Factory())
                            .data(userImage)
                            .diskCachePolicy(CachePolicy.DISABLED)
                            .size(Size.ORIGINAL)
                            .build(), onError = {
                            Log.d(
                                "TESTING",
                                "GreetingSection: data ${it.result.request.data} result ${it.result.throwable}"
                            )
                        }, contentDescription = null, modifier = Modifier
                            .clip(
                                CircleShape
                            )
                            .size(50.dp)
                    )

                    /*AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://www.v-xplore.com/dev/rohan/assets/jpg/profile.jpg")
                            .crossfade(true)
                            .build(),
                        *//*onError = {
                            Log.d("message", "GreetingSection: data ${it.result.request} ${it.result.throwable}")
                        },*//*
                        contentDescription = null,
                        modifier = Modifier.clip(CircleShape),
                        loading = {
                            CircularProgressIndicator()
                        },
                    )*/
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(65.dp),
                    color = Color.Black,
                    strokeWidth = 3.dp
                )
            }


            /*Image(
                painter = R.drawable.profile.resourceImage(),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )*/
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
        if(dashboardViewModel.shortDetail.value?.userName?.isNotEmpty()  == true){
            Icon(
                painter = R.drawable.bell.resourceImage(),
                tint = Color.White,
                contentDescription = "notify",
                modifier = Modifier
                    .size(30.dp)
            )
        }


    }


}


@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Feature>, dashboardViewModel: DashboardViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            if (features.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = 7.5.dp,
                        end = 7.5.dp,
                        bottom = 20.dp,
                        top = 10.dp
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)

                ) {
                    items(features.size) {
                        FeatureItem(feature = features[it], dashboardViewModel)
                    }

                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(bottom = 5.dp),
                    color = Color.Black,
                    strokeWidth = 5.dp
                )
            }

            CalenderSection(dashboardViewModel)
        }


    }
}


@Composable
fun FeatureItem(
    feature: Feature, dashboardViewModel: DashboardViewModel
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1.2f)
            .clip(RoundedCornerShape(18.dp))
            .background(color = Color(android.graphics.Color.parseColor(feature.bgColor)))
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(feature.identityIconUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .crossfade(enable = true)
                    .error(R.drawable.gslogo)
                    .size(150)
                    .build(),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Text(
                text = feature.quantity.toString(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.TopEnd)

            )

            Text(
                text = feature.type,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            IconButton(
                modifier = Modifier
                    .size(24.dp)
                    .width(0.09.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(color = Color.White)
                    .align(Alignment.BottomEnd),
                onClick = {
                    dashboardViewModel.onBoxClicked(feature)
                }
            ) {
                Icon(
                    painter = R.drawable.eye.resourceImage(),
                    contentDescription = "eye",
                    tint = Color(android.graphics.Color.parseColor(feature.bgColor)),

                    )
            }

        }


    }
}
