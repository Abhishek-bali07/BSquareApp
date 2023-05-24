package com.bsquare.app.presentation.ui.custom_composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.view_models.CompanyDetailViewModel
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.entities.ActivityDetails
import com.bsquare.core.utils.helper.AppNavigator


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddActivity(
    companyDetailViewModel: CompanyDetailViewModel,
    tabInt: Int,
    addedActivityArg: SnapshotStateList<ActivityDetails?>) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = "Activity", style = TextStyle(
                fontWeight = FontWeight.W700
            )
            )
            Surface(modifier = Modifier
                .size(width = 130.dp, height = 24.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(color = Color(0xffEF2424)),
                onClick = {
                   companyDetailViewModel.onAddActivity()

                }) {
                Row(
                    modifier = Modifier.background(color =  Color(0xffEF2424)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier.background(color =  Color(0xffEF2424)),
                        painter = R.drawable.plus.resourceImage(),
                        contentDescription = "null"
                    )

                    Text(
                        modifier = Modifier.padding(
                            vertical = 3.dp, horizontal = 5.dp
                        ),
                        text = "Add Activity",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }

            }
        }


//        OutlinedTextField(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 20.dp)
//                .size(height = 100.dp, width = 300.dp),
//            value = companyDetailViewModel.writenActivity.value,
//            onValueChange = companyDetailViewModel::onChangeActivity,
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.LightGray,
//                unfocusedBorderColor = Color.Black
//            )
//        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Log.d("message", "AddActivity: ${addedActivityArg.size}")
                items(addedActivityArg) { item ->
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(12.dp)
                    ) {

                        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            if (item != null) {
                                Text(text = item.activityFor)
                            }
//                            IconButton(onClick = {
//                                if (item != null) {
//                                    companyDetailViewModel.removeActivity(item.activityFor, tabId = tabInt.toString())
//                                }
//                            }) {
//                                Icon(
//                                    painter = R.drawable.mcircle.resourceImage(),
//                                    contentDescription = null
//                                )
//                            }
                        }
                    }
                }


        }
    }

}