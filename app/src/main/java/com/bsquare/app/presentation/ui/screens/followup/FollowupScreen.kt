package com.bsquare.app.presentation.ui.screens.followup

import android.widget.SearchView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.presentation.ui.custom_composable.FollowupDone
import com.bsquare.app.presentation.ui.custom_composable.TodayFollowup
import com.bsquare.app.presentation.ui.custom_composable.TodayOverdue
import com.bsquare.app.presentation.ui.custom_composable.TodayUpcoming
import com.bsquare.app.presentation.ui.view_models.FollowupViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch


@Composable
fun FollowupScreen(
    followupViewModel: FollowupViewModel = hiltViewModel(),
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
            TopAppBar(
                backgroundColor = Color.Red, elevation = 2.dp, title = {
                    Text(
                        "Follow-ups",
                        modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                        style = TextStyle(
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
                        onClick = {
                            followupViewModel.selectPager.value?.let {
                                followupViewModel.selectPager.value =
                                    it.copy(isVisible = !it.isVisible)
                            }
                        }) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = R.drawable.search.resourceImage(),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        followupViewModel.onFilterClicked()
                    }) {
                        Image(
                            modifier = Modifier.size(25.dp),
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
fun TaskListSection(
    followupViewModel: FollowupViewModel
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val isLoading by followupViewModel.isRefreshing.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = followupViewModel::getFollowUpData,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.White,
                contentColor = Color.Red
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            TabRow(selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth(),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .fillMaxWidth()
                            .pagerTabIndicatorOffset(pagerState, tabPositions),
                        color = Color(0xffFF0303)
                    )
                }) {
                followupViewModel.pages.forEachIndexed { index, title ->
                    Tab(modifier = Modifier.background(color = Color.White),
                        selectedContentColor = Color(0xffFF5E00),
                        unselectedContentColor = Color.Black,
                        text = { Text(title, fontSize = 12.sp) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                followupViewModel.selectPager.value =
                                    followupViewModel.selectPager.value?.copy(pagerState = index)
                                pagerState.animateScrollToPage(index)
                            }
                        })
                }
            }

            HorizontalPager(
                count = followupViewModel.pages.size,
                modifier = Modifier.fillMaxWidth(),
                state = pagerState
            ) { page ->
                when (page) {
                    0 -> {
                        TodayFollowup(followupViewModel, page)
                    }
                    1 -> {
                        TodayUpcoming(followupViewModel, page)
                    }
                    2 -> {
                        TodayOverdue(followupViewModel, page)
                    }
                    3 -> {
                        FollowupDone(followupViewModel, page)
                    }
                }
            }
        }
    }


}
