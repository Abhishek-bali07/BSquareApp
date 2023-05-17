package com.bsquare.app.presentation.ui.screens.Lead

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.custom_composable.AppButton
import com.bsquare.app.presentation.ui.view_models.AddActivityViewModel
import java.util.*

@Composable
fun AddActivityScreen(
    addActivityViewModel: AddActivityViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()


    Scaffold(scaffoldState = scaffoldState) {paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(state = scrollState)
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(backgroundColor = Color.Red, elevation = 2.dp, title = {
                Text(
                    "Add Activity",
                    style = TextStyle(
                        color = Color.White, textAlign = TextAlign.Center, fontSize = 20.sp
                    )
                )
            },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Image(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(horizontal = 8.dp),
                            painter = R.drawable.backbutton.resourceImage(),
                            contentDescription = null
                        )
                    }


                })

            AddActivitySection(addActivityViewModel)


            AppButton(
                enable = {},
                loading = {},
                action = { /*TODO*/ },
                name = R.string.add_now)



        }

    }
}

@Composable
fun AddActivitySection(
    addActivityViewModel: AddActivityViewModel
) {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()


    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    mCalendar.time = Date()

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            addActivityViewModel.selectedTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )


    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            addActivityViewModel.selectedDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"

        }, mYear, mMonth, mDay
    )


    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp

    Surface( modifier = Modifier.fillMaxWidth(),
        color = Color.White) {

        Column(
            modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top) {


            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Activity For*", style = TextStyle(fontWeight = FontWeight.W500),
                textAlign = TextAlign.Start
            )


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Surface(
                    modifier = Modifier
                        .width(screenWidthDp * .90f)
                        .background(color = Color(0xffDDDDDD)),
                    border = BorderStroke(1.dp, Color(0xff666666)),

                    ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (addActivityViewModel.selectLeads.value.isNotEmpty()){
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),

                                text = addActivityViewModel.selectLeads.value ,
                                style = TextStyle(
                                    color = Color(0xff212121),
                                    fontSize = 16.sp,
                                ),
                            )
                        }else {
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),

                                text = "Select Leads" ,
                                style = TextStyle(
                                    color = Color.LightGray,
                                    fontSize = 16.sp,
                                ),
                            )
                        }

                        IconButton(modifier = Modifier.wrapContentWidth(),
                            onClick = {

                               addActivityViewModel.isExpanded.value = !addActivityViewModel.isExpanded.value

                            }) {
                            Icon(
                                painter = R.drawable.downarrow.resourceImage(),
                                contentDescription = null
                            )
                        }
                    }
                }



            }

        }

    }

}
