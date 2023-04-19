package com.bsquare.app.presentation.ui.screens.Lead

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.view_models.CompanyDetailViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.bsquare.app.presentation.states.ComposeLaunchEffect
import com.bsquare.app.presentation.ui.view_models.BaseViewModel

@Composable
fun CompanyDetailScreen(
    companyDetailViewModel: CompanyDetailViewModel = hiltViewModel(),
    baseViewModel: BaseViewModel
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TopAppBar(
                backgroundColor = Color.Red,
                elevation = 2.dp,
                title = {
                    Text(
                        "Infosys", style = TextStyle(
                            color = Color.White, textAlign = TextAlign.Center, fontSize = 20.sp
                        )
                    )
                }, navigationIcon = {
                    Image(

                        modifier = androidx.compose.ui.Modifier
                            .size(40.dp)
                            .padding(horizontal = 8.dp),
                        painter = R.drawable.backbutton.resourceImage(),
                        contentDescription = null
                    )
                }
            )
            CompanyTypeSection()
            CompanyDetailSection()

        }
    }

    baseViewModel.leadToLeadDetailsArg.ComposeLaunchEffect(
        intentionalCode = {
            if (it.isNotEmpty()) {
                 companyDetailViewModel.initialData(it)
            }
        }, clearance = { "" })

}

@Composable
fun CompanyDetailSection() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Card(modifier = Modifier.padding(bottom = 10.dp)) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 15.dp),
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")

                    Surface(
                        modifier = Modifier
                            .size(width = 150.dp, height = 50.dp)
                            .clip(shape = RoundedCornerShape(40.dp))
                        , elevation = 3.dp, color = Color(0xff1AB1B0))
                    {

                        Text(
                            modifier = Modifier.padding(
                                vertical = 3.dp,
                                horizontal = 5.dp
                            ),
                            text = "35000",
                            style = TextStyle(textAlign = TextAlign.Center,
                                color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold))


                    }

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 25.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        IconButton(modifier = Modifier
                            .size(24.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(color = Color(0xff1ED261)),
                            onClick = {

                            }) {
                            Icon(
                                painter = R.drawable.call.resourceImage(),
                                contentDescription = "call",
                                tint = Color.White

                            )
                        }


                        IconButton(modifier = Modifier
                            .size(24.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(color = Color(0xff03A0E2)),
                            onClick = {
                            }) {
                            Icon(
                                painter = R.drawable.call.resourceImage(),
                                contentDescription = "call",
                                tint = Color.White

                            )
                        }

                        IconButton(modifier = Modifier
                            .size(24.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(color = Color(0xffEF2424)),
                            onClick = {
                            }) {
                            Icon(
                                painter = R.drawable.call.resourceImage(),
                                contentDescription = "call",
                                tint = Color.White

                            )
                        }
                        IconButton(modifier = Modifier
                            .size(24.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(color = Color(0xff3DE912)),
                            onClick = {
                            }) {
                            Icon(
                                painter = R.drawable.call.resourceImage(),
                                contentDescription = "call",
                                tint = Color.White

                            )
                        }

                        IconButton(modifier = Modifier
                            .size(24.dp)
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(color = Color(0xffFF5E00)),
                            onClick = {
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

    }
}

@Composable
fun CompanyTypeSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(horizontal = 20.dp, vertical = 10.dp),
    ) {
        StatusSection()
        LabelSection()
    }
}

@Composable
fun LabelSection() {
    Row() {
        Text(text = "Label:")
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(color = Color.White.copy(alpha = .5f))
        ) {
            Text(
                modifier = androidx.compose.ui.Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                text = "WARM",
                style = TextStyle(
                    color = Color(0xffFF5E00), fontSize = 13.sp, fontWeight = FontWeight.Bold
                ),
            )
        }

    }
}

@Composable
fun StatusSection() {
    Row() {
        Text(text = "Status:")
        Box(
            modifier = androidx.compose.ui.Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(color = Color.White.copy(alpha = .5f))
        ) {
            Text(
                modifier = androidx.compose.ui.Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                text = "type",
                style = TextStyle(
                    color = Color(0xffFF5E00), fontSize = 13.sp, fontWeight = FontWeight.Bold
                ),
            )
        }

    }
}
