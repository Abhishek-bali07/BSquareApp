package com.bsquare.app.presentation.ui.screens.followup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.presentation.ui.view_models.FollowupViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch


@Composable
fun FollowupScreen(
    followupViewModel: FollowupViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TopAppBar(
                backgroundColor = Color.Red, elevation = 2.dp, title = {
                    Text(
                        "Follow-ups", modifier = androidx.compose.ui.Modifier.fillMaxWidth(), style = TextStyle(
                            color = Color.White, textAlign = TextAlign.Center, fontSize = 20.sp,
                        )
                    )
                }, navigationIcon = {
                    Image(

                        modifier = Modifier
                            .size(40.dp)
                            .padding(horizontal = 8.dp),
                        painter = R.drawable.backbutton.resourceImage(),
                        contentDescription = null
                    )
                }, actions = {
                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = R.drawable.search.resourceImage(),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {

                    }) {
                        Image(
                            modifier =Modifier.size(25.dp),
                            painter = R.drawable.filter.resourceImage(),
                            contentDescription = null
                        )
                    }


                })

            TaskListSection(followupViewModel)
    }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TaskListSection(followupViewModel: FollowupViewModel) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()


    Column( modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
            TabRow(selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth(),
                indicator = {
                    tabPositions ->  TabRowDefaults.Indicator(
                    Modifier
                        .fillMaxWidth()
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                    )
                }) {
                    followupViewModel.pages.forEachIndexed{index, title ->
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
            count = followupViewModel.pages.size,
            modifier = Modifier.fillMaxWidth(),
            state = pagerState
        ) {
            page -> when (page) {
                0-> {

                }
                1->{

                }
            }
        }
    }
}
