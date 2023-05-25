package com.bsquare.app.presentation.ui.screens.Lead

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.states.ComposeLaunchEffect
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.custom_composable.AddActivity
import com.bsquare.app.presentation.ui.custom_composable.AddInfo
import com.bsquare.app.presentation.ui.custom_composable.AddNote
import com.bsquare.app.presentation.ui.custom_composable.AddTask
import com.bsquare.app.presentation.ui.view_models.BaseViewModel
import com.bsquare.app.presentation.ui.view_models.CompanyDetailViewModel
import com.bsquare.core.utils.helper.AppNavigator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CompanyDetailScreen(
    companyDetailViewModel: CompanyDetailViewModel = hiltViewModel(),
    baseViewModel: BaseViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (companyDetailViewModel.companyDetails.value?.companyName?.isNotEmpty() == true){
                TopAppBar(backgroundColor = Color.Red, elevation = 2.dp, title = {
                    Text(
                        "${companyDetailViewModel.companyDetails.value?.companyName}",
                        style = TextStyle(
                            color = Color.White, textAlign = TextAlign.Center, fontSize = 20.sp
                        )
                    )
                },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                companyDetailViewModel.appNavigator.tryNavigateBack()
                            }) {
                            Image(
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(horizontal = 8.dp),
                                painter = R.drawable.backbutton.resourceImage(),
                                contentDescription = null
                            )
                        }

                    })
                CompanyTypeSection(companyDetailViewModel,baseViewModel)
                CompanyDetailSection(companyDetailViewModel)
                TabBarSection(companyDetailViewModel,baseViewModel)
            }else {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LoadingAnimation3()
                }

               /* CircularProgressIndicator(
                    modifier = Modifier.size(65.dp),
                    color = Color.Black,
                    strokeWidth = 3.dp
                )*/
            }



        }
    }

    BackHandler {
       Log.d("testing", "called")
        baseViewModel.appNavigator.tryNavigateBack(inclusive = true)
    }

    baseViewModel.leadToLeadDetailsArg.ComposeLaunchEffect(intentionalCode = {
        if (it.isNotEmpty()) {
            companyDetailViewModel.initialData(it)
        }
    }) { "" }

}

@Composable
fun LoadingAnimation3(
    circleColor: Color = Color(0xFF35898F),
    circleSize: Dp = 30.dp,
    animationDelay: Int = 400,
    initialAlpha: Float = 0.3f
) {

    // 3 circles
    val circles = listOf(
        remember {
            Animatable(initialValue = initialAlpha)
        },
        remember {
            Animatable(initialValue = initialAlpha)
        },
        remember {
            Animatable(initialValue = initialAlpha)
        }
    )

    circles.forEachIndexed { index, animatable ->

        LaunchedEffect(Unit) {

            // Use coroutine delay to sync animations
            delay(timeMillis = (animationDelay / circles.size).toLong() * index)

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }

    // container for circles
    Row(
        modifier = Modifier
        //.border(width = 2.dp, color = Color.Magenta)
    ) {

        // adding each circle
        circles.forEachIndexed { index, animatable ->

            // gap between the circles
            if (index != 0) {
                Spacer(modifier = Modifier.width(width = 6.dp))
            }

            Box(
                modifier = Modifier
                    .size(size = circleSize)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = animatable.value)
                    )
            ) {
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun ColumnScope.TabBarSection(companyDetailViewModel: CompanyDetailViewModel,baseViewModel: BaseViewModel) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Timeline", fontSize = 20.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(15.dp))
        TabRow(selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .fillMaxWidth()
                        .pagerTabIndicatorOffset(pagerState, tabPositions),color = Color(0xffFF0303)
                )
            }) {
            companyDetailViewModel.pages.forEachIndexed { index, title ->
                Tab(modifier = Modifier.background(color = Color.White),
                    selectedContentColor = Color(0xffFF5E00),
                    unselectedContentColor = Color.Black,
                    text = { Text(title, fontSize = 9.sp) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    })
            }
        }

        HorizontalPager(
            count = companyDetailViewModel.pages.size,
            modifier = Modifier.fillMaxWidth(),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> {
                   AddActivity(companyDetailViewModel,page, baseViewModel.addedActivityArg)
                }
                1 -> {
                    // add task
                    AddTask(companyDetailViewModel,page)
                }
                2 ->{
                    AddNote(companyDetailViewModel,page)
                }

                3 ->{
                    AddInfo(companyDetailViewModel,page)
                }
            }

        }
    }
}


@Composable
fun CompanyDetailSection(
    companyDetailViewModel: CompanyDetailViewModel
) {
    val ctx = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .size(height = 250.dp, width = 400.dp)
                    .padding(10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 15.dp),
                        text = "${companyDetailViewModel.companyDetails.value?.leadDescription}",
                        textAlign = TextAlign.Center
                    )

                    Surface(
                        modifier = Modifier
                            .size(width = 150.dp, height = 50.dp)
                            .clip(shape = RoundedCornerShape(40.dp)),
                        elevation = 3.dp,
                        color = Color(0xff1AB1B0)
                    ) {

                        Text(
                            modifier = Modifier.padding(
                                vertical = 3.dp, horizontal = 5.dp
                            ),
                            text = "${companyDetailViewModel.companyDetails.value?.leadAmount}",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )


                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 25.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(modifier = Modifier
                            .size(35.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = Color(0xff1ED261)),
                            onClick = {
                                val u =
                                    Uri.parse("tel:" + companyDetailViewModel.companyDetails.value?.companyNumber)


                                val i = Intent(Intent.ACTION_DIAL, u)
                                i.resolveActivity(ctx.packageManager).apply {
                                    ctx.startActivity(i)
                                }

                                /*  try {

                                  } catch (s: SecurityException) {
                                      Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                                          .show()
                                  }*/

                            }) {
                            Icon(
                                painter = R.drawable.caller.resourceImage(),
                                contentDescription = "call",
                                tint = Color.White

                            )
                        }


                        IconButton(modifier = Modifier
                            .size(35.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = Color(0xff03A0E2)),
                            onClick = {
                                val sms_uri =
                                    Uri.parse("smsto:${companyDetailViewModel.companyDetails.value?.companyNumber}")
                                val sms_intent = Intent(Intent.ACTION_SENDTO, sms_uri)
                                sms_intent.putExtra("sms_body", "Good Morning ! how r U ?")
                                sms_intent.resolveActivity(ctx.packageManager).apply {
                                    ctx.startActivity(sms_intent)
                                }

                            }) {
                            Icon(
                                painter = R.drawable.message.resourceImage(),
                                contentDescription = "message",
                                tint = Color.White

                            )
                        }

                        IconButton(modifier = Modifier
                            .size(35.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = Color(0xffEF2424)),
                            onClick = {

                                val uri =
                                    Uri.parse("mailto:${companyDetailViewModel.companyDetails.value?.companyEmail}")
                                        .buildUpon().build()

                                val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
                                emailIntent.resolveActivity(ctx.packageManager).apply {
                                    ctx.startActivity(emailIntent)
                                }
                            }) {
                            Icon(
                                painter = R.drawable.email.resourceImage(),
                                contentDescription = "call",
                                tint = Color.White

                            )
                        }
                        IconButton(modifier = Modifier
                            .size(35.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = Color(0xff3DE912)),
                            onClick = {

                                val url =
                                    Uri.parse("https://api.whatsapp.com/send?phone=${companyDetailViewModel.companyDetails.value?.companyNumber}")
                                val i = Intent(Intent.ACTION_VIEW, url)
                                i.resolveActivity(ctx.packageManager).apply {
                                    ctx.startActivity(i)
                                }


                            }) {
                            Icon(
                                painter = R.drawable.wlogo.resourceImage(),
                                contentDescription = "call",
                                tint = Color.White

                            )
                        }

                        IconButton(modifier = Modifier
                            .size(35.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(color = Color(0xffFF5E00)),
                            onClick = {
                                val geoUri = Uri.parse(
                                    "http://maps.google.com/maps?q=loc:${companyDetailViewModel.companyDetails.value?.latLong?.lat}," + "${companyDetailViewModel.companyDetails.value?.latLong?.lng}"
                                )


                                var MapIntent = Intent(
                                    Intent.ACTION_VIEW, geoUri
                                )
                                MapIntent.resolveActivity(ctx.packageManager).apply {
                                    ctx.startActivity(MapIntent)
                                }

                            }) {
                            Icon(
                                painter = R.drawable.location.resourceImage(),
                                contentDescription = "call",
                                tint = Color.White

                            )
                        }


                    }


                }

            }
        }

    }
}

@Composable
fun CompanyTypeSection(companyDetailViewModel: CompanyDetailViewModel,baseViewModel: BaseViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(vertical = 8.dp),
    ) {
        StatusSection(companyDetailViewModel,baseViewModel)

        LabelSection(companyDetailViewModel, baseViewModel)
    }
}

@Composable
fun RowScope.LabelSection(companyDetailViewModel: CompanyDetailViewModel, baseViewModel: BaseViewModel) {
    Row(
        modifier = Modifier
            .weight(1f),

        ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(text = "Status:")
            Surface(
                modifier = Modifier
                    .size(width = 110.dp, height = 30.dp)
                    .padding(horizontal = 25.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(color = Color.White.copy(alpha = .5f))
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(
                            vertical = 3.dp, horizontal = 5.dp
                        ),
                        text = companyDetailViewModel.lSelectedText.value,
                        style = TextStyle(
                            color = Color(0xff212121),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )

                    IconButton(onClick = {
                        companyDetailViewModel.isExpanded.value =
                            !companyDetailViewModel.isExpanded.value
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                        )
                    }
                }
            }
        }

        DropdownMenu(
            expanded = companyDetailViewModel.isExpanded.value,
            onDismissRequest = { companyDetailViewModel.isExpanded.value = false },

            ) {
            companyDetailViewModel.companyDetails.value?.leadStatus?.forEach { text ->
                DropdownMenuItem(onClick = {
                    baseViewModel.refreshLoadDataArg.value = true
                    companyDetailViewModel.lSelectedText.value = text.statusName
                    companyDetailViewModel.isExpanded.value = false
                }) {
                    Text(text = text.statusName)
                }

            }
        }

    }
}

@Composable
fun RowScope.StatusSection(companyDetailViewModel: CompanyDetailViewModel,baseViewModel: BaseViewModel) {
    Row(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 15.dp),


        ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Label:")
            Surface(
                modifier = Modifier
                    .size(width = 110.dp, height = 30.dp)
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(color = Color.White.copy(alpha = .5f))
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(
                            vertical = 3.dp, horizontal = 5.dp
                        ),
                        text = companyDetailViewModel.mSelectedText.value,
                        style = TextStyle(
                            color = Color(0xff212121),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )

                    IconButton(onClick = {
                        companyDetailViewModel.isMenuExpanded.value =
                            !companyDetailViewModel.isMenuExpanded.value
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown, contentDescription = ""
                        )
                    }
                }

            }
        }

        DropdownMenu(
            expanded = companyDetailViewModel.isMenuExpanded.value,
            onDismissRequest = { companyDetailViewModel.isMenuExpanded.value = false },

            ) {
            companyDetailViewModel.companyDetails.value?.leadLabel?.forEach { text ->
                DropdownMenuItem(onClick = {
                    baseViewModel.refreshLoadDataArg.value = true
                    companyDetailViewModel.mSelectedText.value = text.labelName
                    companyDetailViewModel.isMenuExpanded.value = false
                }) {
                    Text(text = text.labelName)
                }

            }
        }

    }

}





