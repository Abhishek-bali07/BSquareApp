package com.bsquare.app.presentation.ui.screens.Lead


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.EnterExitState.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bsquare.app.R
import com.bsquare.app.presentation.states.ComposeLaunchEffect
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.view_models.BaseViewModel
import com.bsquare.app.presentation.ui.view_models.LeadViewModel
import com.bsquare.core.entities.LeadData
import com.bsquare.core.entities.Leads
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LeadScreen(
    leadViewModel: LeadViewModel = hiltViewModel(),
    baseViewModel: BaseViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()
    val fabVisibility by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }



    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            AnimatedVisibility(visible = fabVisibility) {
                if (fabVisibility) {
                    FloatingActionButton(
                        modifier = Modifier.padding(top = 80.dp),

                        onClick = {
                            leadViewModel.onAddLeads()
                        },
                        backgroundColor = Color.White,
                        contentColor = Color.Red
                    ) {
                        Icon(Icons.Filled.Add, "")
                    }
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TopAppBar(
                backgroundColor = Color.Red, elevation = 2.dp, title = {
                    Text(
                        "Leads", modifier = Modifier.fillMaxWidth(), style = TextStyle(
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
                        }) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = R.drawable.search.resourceImage(),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        leadViewModel.onFilterClicked()
                    }) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = R.drawable.filter.resourceImage(),
                            contentDescription = null
                        )
                    }


                })


            SurfaceDemo()
            TodayListSection(leads = leadViewModel.leads, leadViewModel, baseViewModel, listState)


        }

    }
}






@Composable
fun SurfaceDemo() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.LightGray,
        contentColor = Color(0xffFF0303),
        elevation = 8.dp,

    ) {
        Text("*SwipeDown for Refresh Page", style = TextStyle(fontSize = 12.sp), modifier = Modifier.padding(horizontal = 10.dp), textAlign = TextAlign.Center)
    }
}


@Composable
fun TodayListSection(
    leads: List<Leads>,
    leadViewModel: LeadViewModel,
    baseViewModel: BaseViewModel,
    listState: LazyListState
) {

    baseViewModel.refreshLoadDataArg.ComposeLaunchEffect(intentionalCode = {
        if (it) {
            Log.d("testing", "called${leadViewModel.getLeadsData()}")
            leadViewModel.getLeadsData()
        }
    }) { false }

    LaunchedEffect(key1 = baseViewModel.changeToLeadDetailsArg){
       if(baseViewModel.changeToLeadDetailsArg.isNotEmpty()){
           leadViewModel.getLeadsData()
           baseViewModel.changeToLeadDetailsArg.clear()
       }
   }


    val screenHeightBy40 = LocalConfiguration.current.screenHeightDp * 0.40f

    val isLoading by leadViewModel.isRefreshing.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)


    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = leadViewModel::getLeadsData,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.White,
                contentColor = Color.Red
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            items(leads) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Card (
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                            .padding(horizontal = 2.dp, vertical = 5.dp),
                        backgroundColor = MaterialTheme.colors.surface,
                        elevation = 2.dp,
                        shape = RoundedCornerShape(6.dp)){


                        Column(
                            modifier = Modifier.fillMaxWidth(.90f),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 5.dp),
                                text = item.date,
                                style = TextStyle.Default.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            LazyColumn(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .height(screenHeightBy40.dp),userScrollEnabled = false) {
                                items(item.leadData) {
                                    LeadDataItem(leadData = it) {
                                        baseViewModel.leadToLeadDetailsArg.value = it.id
                                        leadViewModel.onCardClicked()
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                }


                            }
                        }
                    }
                    //date section

                }

            }
        }

    }


}


@Composable
fun LeadDataItem(
    leadData: LeadData,
    onItemClicked: () -> Unit
) {
    val ctx = LocalContext.current

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(75.dp)
            .clickable(onClick = onItemClicked),
        backgroundColor = MaterialTheme.colors.surface                                                                                                                              ,
        shape = RoundedCornerShape(6.dp),
        elevation = 2.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Start) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(leadData.companyIconUrl)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xffFF5E09))
                )
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = leadData.companyName, style = TextStyle(
                            color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold
                        ), modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Text(
                        text = "Rs.${leadData.leadAmount}", style = TextStyle(
                            color = Color.Black, fontSize = 16.sp,
                        ), modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }

                if (leadData.isNew)
                    Text(
                        modifier = Modifier.padding(vertical = 5.dp),
                        text = "New",
                        style = TextStyle(color = Color(0xff1AB13E), fontSize = 12.sp)
                    )

            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Surface(
                    modifier = Modifier
                        .size(width = 80.dp, height = 30.dp)
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    contentColor = Color(0xffFF5E00),
                    color = Color(android.graphics.Color.parseColor(leadData.typeColor))
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                        text = leadData.type,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }


                IconButton(modifier = Modifier
                    .size(36.dp)
                    .padding(vertical = 5.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(color = Color(0xff1ED261)),
                    onClick = {
                        val u = Uri.parse("tel:" + leadData.companyNumber)


                        val i = Intent(Intent.ACTION_DIAL, u)
                        try {
                            ctx.startActivity(i)
                        } catch (s: SecurityException) {
                            Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                                .show()
                        }
                    }) {
                    Icon(
                        painter = R.drawable.call.resourceImage(),
                        contentDescription = "call",
                        tint = Color.White

                    )
                }
            }

        }
    }

}



