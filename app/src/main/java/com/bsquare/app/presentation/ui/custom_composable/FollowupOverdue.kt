package com.bsquare.app.presentation.ui.custom_composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bsquare.app.R
import com.bsquare.app.presentation.ui.view_models.FollowupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayOverdue(
    followupViewModel: FollowupViewModel,
    page: Int
){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)) {


        followupViewModel.selectPager.value?.let {
            if (it.pagerState == page && it.isVisible){
                OutlinedTextField(
                    value = followupViewModel.overdueTxt.value,
                    onValueChange = followupViewModel::onChangeOverdueTxt,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    },
                    trailingIcon = {
                        if (followupViewModel.usearchTxt.value != "") {
                            IconButton(
                                onClick = {
                                    followupViewModel.usearchTxt.value = ""

                                }
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .size(24.dp)
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RectangleShape,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        leadingIconColor = Color.White,
                        trailingIconColor = Color.White,
                        backgroundColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        }


        LazyColumn( modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(followupViewModel.foverdue){ item ->
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
                                    model = ImageRequest.Builder(LocalContext.current).data(item.companyIconUrl)
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
            onClick = followupViewModel::onBtnClicked,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
            modifier = Modifier.fillMaxWidth().height(50.dp)) {
            Text(text = "Add Task",color = Color.White)
        }
    }

}