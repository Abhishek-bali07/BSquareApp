package com.bsquare.app.presentation.ui.custom_composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bsquare.app.presentation.ui.view_models.FollowupViewModel

@Composable
fun FollowupDone(
    followupViewModel: FollowupViewModel
){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)) {
        LazyColumn( modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(followupViewModel.fdone){ item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Card (
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .height(75.dp),
                        shape = RoundedCornerShape(5.dp), elevation = 10.dp
                    ){
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Start) {
                                AsyncImage(
                                    model = item.companyIconUrl,
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
                                        text = item.companyName, style = TextStyle(
                                            color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold
                                        ), modifier = Modifier.padding(horizontal = 10.dp)
                                    )

                                    Text(
                                        text = item.ownerName, style = TextStyle(
                                            color = Color.Black, fontSize = 16.sp,
                                        ), modifier = Modifier.padding(horizontal = 10.dp)
                                    )
                                }



                            }
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                                    text = item.time,
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                    ),
                                )
                            }
                        }
                    }

                }
            }
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
            modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text(text = "Add Task",color = Color.White)
        }
    }

}