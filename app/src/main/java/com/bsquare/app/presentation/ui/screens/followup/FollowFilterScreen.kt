package com.bsquare.app.presentation.ui.screens.followup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.presentation.ui.screens.Lead.ChipSection
import com.bsquare.app.presentation.ui.view_models.BaseViewModel
import com.bsquare.app.presentation.ui.view_models.FFilterViewModel

@Composable
fun FollowFilterScreen(
    baseViewModel: BaseViewModel,
    followfilterViewModel: FFilterViewModel = hiltViewModel(),
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
    ){
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {

            TopAppBar(
                backgroundColor = Color(0xffDDDDDD), elevation = 2.dp,
                title = {
                    Text(
                        "Filter", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                            color = Color.Black, textAlign = TextAlign.Center, fontSize = 20.sp,
                        )
                    )
                }, navigationIcon = {
                    IconButton(modifier = Modifier.then(Modifier.size(24.dp)),
                        onClick = { followfilterViewModel.appNavigator.tryNavigateBack() }) {
                        Icon(
                            Icons.Filled.Close, "contentDescription", tint = Color.White
                        )
                    }
                }, actions = {
                    Button(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        onClick = {
                                    followfilterViewModel.onResetBtnClick()
                        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = "RESET")
                    }
                }
            )


            if (followfilterViewModel.loader.value){
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(bottom = 5.dp),
                    color = Color.Black,
                    strokeWidth = 5.dp
                )

            }else{
                Surface(modifier = Modifier.fillMaxSize(1f)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        FChipSection(followfilterViewModel)

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(height = 64.dp),

                            onClick = {
                                followfilterViewModel.appNavigator.tryNavigateBack()
                                baseViewModel.changeToLeadDetailsArg.addAll(followfilterViewModel.selectedItem)
                            }, colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xffFF0303)
                            )
                        ) {
                            Text(
                                text = "Apply Filters", style = TextStyle(
                                    color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.W700
                                )
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun FChipSection(
    followfilterViewModel: FFilterViewModel
) {
   Column(modifier = Modifier.fillMaxWidth()) {
       followfilterViewModel.filterDetail.collectAsState().value?.apply {
           Text(
               text = "DataLead", style = TextStyle(
                   fontWeight = FontWeight.W500, fontSize = 18.sp
               )
           )
           LazyVerticalGrid(columns = GridCells.Fixed(3)) {
               items(dataLead.size){idx->
                   Box(contentAlignment = Alignment.Center,
                       modifier = Modifier
                           .padding(start = 1.dp, top = 5.dp, bottom = 5.dp)
                           .clickable {
                               followfilterViewModel.onClickDataLeadChip(idx)
                           }
                           .clip(RoundedCornerShape(25.dp))
                           .background(
                               color = if (dataLead[idx].isSelected) Color.Red.copy(alpha = .3f) else Color(
                                   0xffDDDDDD
                               )
                           )
                           .padding(10.dp)) {
                       Text(
                           text = dataLead[idx].dataLeadName, style = TextStyle(
                               fontSize = 14.sp
                           )
                       )
                   }

               }
           }

           Text(
               text = "Labels", style = TextStyle(
                   fontWeight = FontWeight.W500, fontSize = 18.sp
               )
           )


           LazyVerticalGrid(columns = GridCells.Fixed(3)) {
               items(dataLabel.size) { idx ->
                   Box(contentAlignment = Alignment.Center,
                       modifier = Modifier
                           .padding(start = 1.dp, top = 5.dp, bottom = 5.dp)
                           .clickable {
                               followfilterViewModel.onClickDataLabelChip(idx)
                           }
                           .clip(RoundedCornerShape(25.dp))
                           .background(
                               color = if (dataLabel[idx].isSelected) Color.Red.copy(alpha = .3f) else Color(
                                   0xffDDDDDD
                               )
                           )
                           .padding(10.dp)

                   ) {
                       Text(
                           text = dataLabel[idx].labelName, style = TextStyle(
                               fontSize = 14.sp
                           )
                       )
                   }

               }
           }


       }
   }
}
