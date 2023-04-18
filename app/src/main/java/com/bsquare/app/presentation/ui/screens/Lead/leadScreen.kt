package com.bsquare.app.presentation.ui.screens.Lead


import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.view_models.LeadViewModel
import com.bsquare.core.entities.LeadData
import com.bsquare.core.entities.Leads


@Composable
fun LeadScreen(
    leadViewModel: LeadViewModel = hiltViewModel(),
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
                        "Leads", style = TextStyle(
                            color = Color.White, textAlign = TextAlign.Center
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = R.drawable.search.resourceImage(),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = R.drawable.filter.resourceImage(),
                            contentDescription = null
                        )
                    }


                })

            TodayListSection(leads = leadViewModel.leads)

        }

    }
}

@Composable
fun TodayListSection(leads: List<Leads>) {
    val screenHeightBy40 = LocalConfiguration.current.screenHeightDp * 0.40f
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(leads) { item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //date section
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(12.dp),
                    shape = RectangleShape
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
                    ) {
                        Text(modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp),text = item.date, style = TextStyle.Default.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold))
                        LazyColumn(modifier = Modifier.height(screenHeightBy40.dp)) {
                            items(item.leadData) {
                                LeadDataItem(leadData = it)
                                Spacer(modifier = Modifier.height(8.dp))
                            }


                        }
                    }
                }
            }

        }
    }


}

@Composable
fun LeadDataItem(
    leadData: LeadData
) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(10.dp), elevation = 3.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.5.dp),
        ) {
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Start) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(leadData.companyIconUrl)
                        .decoderFactory(SvgDecoder.Factory()).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(color = Color(0xffFF5E00))
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
                        style = TextStyle(color = Color(0xff1AB13E),fontSize = 12.sp,))

            }
            Row(
                modifier = Modifier
                    .weight(1f).padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(color = Color(0xffFF5E00).copy(alpha = .5f))
                ) {
                    Text(
                        text = leadData.type,
                        style = TextStyle(
                            color = Color(0xffFF5E00), fontSize = 13.sp, fontWeight = FontWeight.Bold
                        ),
                    )
                }


                IconButton(modifier = Modifier
                    .size(24.dp)
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



