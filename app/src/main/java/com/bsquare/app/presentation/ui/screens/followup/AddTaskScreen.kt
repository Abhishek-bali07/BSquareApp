package com.bsquare.app.presentation.ui.screens.followup

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
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
import com.bsquare.app.presentation.ui.view_models.AddTaskViewModel
import com.bsquare.app.presentation.ui.view_models.BaseViewModel
import java.util.*

@Composable
fun AddTaskScreen(
    baseViewModel: BaseViewModel,
    addTaskViewModel: AddTaskViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()


    Scaffold(scaffoldState = scaffoldState) { paddingValues ->
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
                    "Add Task",
                    style = TextStyle(
                        color = Color.White, textAlign = TextAlign.Center, fontSize = 20.sp
                    )
                )
            },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Image(
                            modifier = androidx.compose.ui.Modifier
                                .size(40.dp)
                                .padding(horizontal = 8.dp),
                            painter = R.drawable.backbutton.resourceImage(),
                            contentDescription = null
                        )
                    }


                })

            AddTaskSection(addTaskViewModel, baseViewModel)

            AppButton(
                enable = addTaskViewModel.enableBtn.value,
                loading = addTaskViewModel.addLoading.value,
                action = addTaskViewModel::newTask,
                name = R.string.add_now
            )

        }
    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTaskSection(
    addTaskViewModel: AddTaskViewModel, baseViewModel: BaseViewModel
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
            addTaskViewModel.selectedTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )


    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            addTaskViewModel.selectedDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"

        }, mYear, mMonth, mDay
    )

    val cDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            addTaskViewModel.dueDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"

        }, mYear, mMonth, mDay
    )


    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {


            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Task For*", style = TextStyle(fontWeight = FontWeight.W500),
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
                        if (addTaskViewModel.selectLeads.value.isNotEmpty()){
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),

                                text = addTaskViewModel.selectLeads.value ,
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

                            addTaskViewModel.isExpanded.value = !addTaskViewModel.isExpanded.value

                        }) {
                            Icon(
                                painter = R.drawable.downarrow.resourceImage(),
                                contentDescription = null
                            )
                        }
                    }
                }


                DropdownMenu(
                    expanded = addTaskViewModel.isExpanded.value,
                    onDismissRequest = { addTaskViewModel.isExpanded.value = false },
                    modifier = Modifier.width(screenWidthDp * 0.90f),

                ) {
                    addTaskViewModel.tasklistDetails.value?.taskFor?.forEach { text ->
                        DropdownMenuItem(onClick = {
                            addTaskViewModel.selectLeads.value = text.taskName
                            addTaskViewModel.isExpanded.value = false
                        }) {
                            Text(text = text.taskName)
                        }
                    }
                }
            }



            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Task Type", style = TextStyle(fontWeight = FontWeight.W500)
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
                        if (addTaskViewModel.taskType.value.isNotEmpty()){
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),
                                text = addTaskViewModel.taskType.value,
                                style = TextStyle(
                                    color = Color(0xff212121),
                                    fontSize = 16.sp,
                                ),
                            )
                        }else{
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),
                                text = "Select Task Type",
                                style = TextStyle(
                                    color = Color.LightGray,
                                    fontSize = 16.sp,
                                ),
                            )
                        }


                        IconButton(modifier = Modifier.wrapContentWidth(), onClick = {

                            addTaskViewModel.isMenuExpanded.value = !addTaskViewModel.isMenuExpanded.value

                        }) {
                            Icon(
                                painter = R.drawable.downarrow.resourceImage(),
                                contentDescription = null
                            )
                        }
                    }
                }


                DropdownMenu(
                    expanded = addTaskViewModel.isMenuExpanded.value,
                    onDismissRequest = { addTaskViewModel.isMenuExpanded.value = false },
                    modifier = Modifier.width(screenWidthDp * 0.90f),

                    ) {
                    addTaskViewModel.tasklistDetails.value?.taskType?.forEach { text ->
                        DropdownMenuItem(onClick = {
                            addTaskViewModel.taskType.value = text.typeName
                            addTaskViewModel.isMenuExpanded.value = false
                        }) {
                            Text(text = text.typeName)
                        }
                    }
                }
            }




            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Due Date", style = TextStyle(fontWeight = FontWeight.W500)
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(color = Color(0xffDDDDDD)),
                border = BorderStroke(1.dp, Color(0xff666666)),

                ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(addTaskViewModel.dueDate.value.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .weight(5f)
                                .padding(
                                    vertical = 3.dp, horizontal = 5.dp
                                ),
                            text = addTaskViewModel.dueDate.value,
                            style = TextStyle(
                                color = Color(0xff212121),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }else{
                        Text(
                            modifier = Modifier
                                .weight(5f)
                                .padding(
                                    vertical = 3.dp, horizontal = 5.dp
                                ),
                            text = "Select Due Date",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 16.sp,

                            ),
                        )
                    }
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            cDatePickerDialog.show()
                        }) {
                        Icon(painter = R.drawable.sdate.resourceImage(), contentDescription = null)
                    }
                }
            }




            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Custom Date", style = TextStyle(fontWeight = FontWeight.W500)
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(color = Color(0xffDDDDDD)),
                border = BorderStroke(1.dp, Color(0xff666666)),

                ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (addTaskViewModel.selectedDate.value.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .weight(5f)
                                .padding(
                                    vertical = 3.dp, horizontal = 5.dp
                                ),
                            text = addTaskViewModel.selectedDate.value,
                            style = TextStyle(
                                color = Color(0xff212121),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }else{
                        Text(
                            modifier = Modifier
                                .weight(5f)
                                .padding(
                                    vertical = 3.dp, horizontal = 5.dp
                                ),
                            text = "Enter Alternate Date",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 16.sp,

                            ),
                        )
                    }
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            mDatePickerDialog.show()
                        }) {
                        Icon(painter = R.drawable.sdate.resourceImage(), contentDescription = null)
                    }
                }
            }



            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Time", style = TextStyle(fontWeight = FontWeight.W500)
            )
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(color = Color(0xffDDDDDD)),
                border = BorderStroke(1.dp, Color(0xff666666)),

                ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (addTaskViewModel.selectedTime.value.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .weight(5f)
                                .padding(
                                    vertical = 3.dp, horizontal = 5.dp
                                ),
                            text = addTaskViewModel.selectedTime.value,
                            style = TextStyle(
                                color = Color(0xff212121),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }else{
                        Text(
                            modifier = Modifier
                                .weight(5f)
                                .padding(
                                    vertical = 3.dp, horizontal = 5.dp
                                ),
                            text = "Select Time",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 16.sp,

                            ),
                        )
                    }
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                              mTimePickerDialog.show()
                        }) {
                        Icon(painter = R.drawable.clock.resourceImage(), contentDescription = null)
                    }
                }
            }


            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Repeat", style = TextStyle(fontWeight = FontWeight.W500)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .size(height = 55.dp, width = 300.dp),
                value = addTaskViewModel.repeatVal.value,
                placeholder = { Text(text = "Repeat ") },
                onValueChange = addTaskViewModel::onChangeRepeatVal,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff666666),
                    unfocusedBorderColor = Color(0xff666666)
                )
            )




            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Task Assign to", style = TextStyle(fontWeight = FontWeight.W500)
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
                        if (addTaskViewModel.taskAssign.value.isNotEmpty()) {
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),
                                text = addTaskViewModel.taskAssign.value,
                                style = TextStyle(
                                    color = Color(0xff212121),
                                    fontSize = 16.sp,
                                ),
                            )
                        }else{
                            Text(
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),
                                text = "Select Task Assign to",
                                style = TextStyle(
                                    color = Color.LightGray,
                                    fontSize = 16.sp,
                                ),
                            )
                        }
                        IconButton(modifier = Modifier.wrapContentWidth(), onClick = {

                            addTaskViewModel.isAssignExpanded.value = !addTaskViewModel.isAssignExpanded.value

                        }) {
                            Icon(
                                painter = R.drawable.downarrow.resourceImage(),
                                contentDescription = null
                            )
                        }
                    }
                }


                DropdownMenu(
                    expanded = addTaskViewModel.isAssignExpanded.value,
                    onDismissRequest = { addTaskViewModel.isAssignExpanded.value = false },
                    modifier = Modifier.width(screenWidthDp * 0.90f),

                    ) {
                    addTaskViewModel.tasklistDetails.value?.taskAssign?.forEach { text ->
                        DropdownMenuItem(onClick = {
                            addTaskViewModel.taskAssign.value = text.assignName
                            addTaskViewModel.isAssignExpanded.value = false
                        }) {
                            Text(text = text.assignName)
                        }
                    }
                }
            }



            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Description", style = TextStyle(fontWeight = FontWeight.W500)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .size(height = 70.dp, width = 300.dp),
                value = addTaskViewModel.taskDesc.value,
                placeholder = { Text(text = "Description") },
                onValueChange = addTaskViewModel::onChangeDescription,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff666666),
                    unfocusedBorderColor = Color(0xff666666)
                )
            )


        }
    }
}
