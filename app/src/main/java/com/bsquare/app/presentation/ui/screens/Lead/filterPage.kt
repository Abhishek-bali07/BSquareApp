package com.bsquare.app.presentation.ui.screens.Lead

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.view_models.BaseViewModel
import com.bsquare.app.presentation.ui.view_models.FilterViewModel
import com.bsquare.core.entities.DataLead
import java.util.*


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterScreen(
    baseViewModel: BaseViewModel,
    filterViewModel: FilterViewModel = hiltViewModel(),

    ) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,


        ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
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
                    onClick = { filterViewModel.appNavigator.tryNavigateBack() }) {
                    Icon(
                        Icons.Filled.Close, "contentDescription", tint = Color.White
                    )
                }
            }, actions = {
                Button(
                    modifier = Modifier.padding(horizontal = 8.dp), onClick = {
                        filterViewModel.onResetBtnClick()
                    }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text(text = "RESET")
                }
            })

            if(filterViewModel.loader.value){
                Log.d("message", "FilterScreen: ${filterViewModel.loader.value} ")
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

                        ChipSection(filterViewModel)

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(height = 64.dp),

                            onClick = {
                                filterViewModel.appNavigator.tryNavigateBack()
                                baseViewModel.changeToLeadDetailsArg.addAll(filterViewModel.selectedItem)
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
fun ChipSection(
    filterViewModel: FilterViewModel
) {


    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)


    val mDatePickerDialog = DatePickerDialog(
        mContext, { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            filterViewModel.onSelectCustomData("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, mYear, mMonth, mDay
    )


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        filterViewModel.filterDetails.collectAsState().value?.apply {
            Text(
                text = "Data Lead added", style = TextStyle(
                    fontWeight = FontWeight.W500, fontSize = 18.sp
                )
            )
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(dataLead.size) { idx ->
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 1.dp, top = 5.dp, bottom = 5.dp)
                            .clickable {
                                filterViewModel.onClickDataLeadChip(idx)
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

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (filterViewModel.selectedDate.value != null) {
                    Text(
                        modifier = Modifier

                            .padding(
                                vertical = 3.dp, horizontal = 5.dp
                            ),
                        text = filterViewModel.selectedDate.value!!.dataLeadName,
                        style = TextStyle(
                            color = Color(0xff1AB1B0),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(
                            vertical = 3.dp, horizontal = 5.dp
                        ),
                        text = "Custom Date",
                        style = TextStyle(
                            color = Color(0xff1AB1B0),
                            fontSize = 16.sp,

                            ),
                    )
                }
                IconButton(

                    onClick = {
                        mDatePickerDialog.show()
                    }) {
                    Icon(
                        painter = R.drawable.sdate.resourceImage(), contentDescription = null
                    )
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
                                filterViewModel.onClickDataLabelChip(idx)
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

            Text(
                text = "Status", style = TextStyle(
                    fontWeight = FontWeight.W500, fontSize = 18.sp
                )
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(dataStatus.size) { idx ->
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 1.dp, top = 5.dp, bottom = 5.dp)
                            .clickable {
                                filterViewModel.onClickDataStatusChip(idx)
                            }
                            .clip(RoundedCornerShape(25.dp))
                            .background(
                                color = if (dataStatus[idx].isSelected) Color.Red.copy(alpha = .3f) else Color(
                                    0xffDDDDDD
                                )
                            )
                            .padding(10.dp)

                    ) {
                        Text(
                            text = dataStatus[idx].statusName, style = TextStyle(
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
            Text(
                text = "Source", style = TextStyle(
                    fontWeight = FontWeight.W500, fontSize = 18.sp
                )
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(dataSource.size) { idx ->
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 1.dp, top = 5.dp, bottom = 5.dp)
                            .clickable {
                                filterViewModel.onClickDataSourceChip(idx)
                            }
                            .clip(RoundedCornerShape(25.dp))
                            .background(
                                color = if (dataSource[idx].isSelected) Color.Red.copy(alpha = .3f) else Color(
                                    0xffDDDDDD
                                )
                            )
                            .padding(10.dp)

                    ) {
                        Text(
                            text = dataSource[idx].dataSourceName, style = TextStyle(
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }



            Text(
                text = "Team Member", style = TextStyle(
                    fontWeight = FontWeight.W500, fontSize = 18.sp
                )
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3)
            ) {
                items(teamMember.size) { idx ->
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 1.dp, top = 5.dp, bottom = 5.dp)
                            .clickable {
                                filterViewModel.onClickTeamMemberChip(idx)
                            }
                            .clip(RoundedCornerShape(25.dp))
                            .background(
                                color = if (teamMember[idx].isSelected) Color.Red.copy(alpha = .3f) else Color(
                                    0xffDDDDDD
                                )
                            )
                            .padding(10.dp)

                    ) {
                        Text(
                            text = teamMember[idx].teamMemberName, style = TextStyle(
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }


        }


    }
}




