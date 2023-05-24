package com.bsquare.app.presentation.ui.screens.Lead

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.bsquare.app.R
import com.bsquare.app.presentation.states.ComposeLaunchEffect
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.custom_composable.AppButton
import com.bsquare.app.presentation.ui.custom_composable.MobileNumberInputField
import com.bsquare.app.presentation.ui.view_models.AddActivityViewModel
import com.bsquare.app.presentation.ui.view_models.BaseViewModel
import com.bsquare.core.common.constants.Destination
import com.bsquare.core.common.enums.NoteTakenBy
import java.util.*

@Composable
fun AddActivityScreen(
    addActivityViewModel: AddActivityViewModel = hiltViewModel(),
    baseViewModel: BaseViewModel

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
                enable = addActivityViewModel.enableBtn.value,
                loading = addActivityViewModel.addLoading.value,
                action = addActivityViewModel::newActivity,
                name = R.string.add_now)



        }

    }

    /////////////////////

   LaunchedEffect(key1 = addActivityViewModel.noteItem){


       if(addActivityViewModel.noteItem.value == NoteTakenBy.SUGGESTION){
                addActivityViewModel.activityNote.value == ""
       }else{
           if (addActivityViewModel.noteItem.value == NoteTakenBy.TEXTFIELD){
           addActivityViewModel.resetNoteItem()
           }
       }
   }

    addActivityViewModel.updateActivityList.ComposeLaunchEffect(intentionalCode = {
      if (it!= null){
          baseViewModel.addedActivityArg.add(it)
          addActivityViewModel.appNavigator.tryNavigateBack()
      }
    }) { null }


}

@OptIn(ExperimentalMaterialApi::class)
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
                        .height(55.dp)
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
                DropdownMenu(expanded = addActivityViewModel.isExpanded.value,
                    onDismissRequest = { addActivityViewModel.isExpanded.value = false },
                    modifier = Modifier.width(screenWidthDp * 0.90f),
                    ) {

                            addActivityViewModel.activitylistDetail.value?.activityFor?.forEach{text ->
                                DropdownMenuItem(onClick = {
                                    addActivityViewModel.selectLeads.value = text.activityLeadsName
                                    addActivityViewModel.isExpanded.value = false
                                }) {
                                    Text(text = text.activityLeadsName)
                                }

                            }



                      }



            }


            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                text = "Activity Type", style = TextStyle(fontWeight = FontWeight.W500),
                textAlign = TextAlign.Start
            )


            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Surface(
                    modifier = Modifier
                        .width(screenWidthDp * .90f)
                        .height(55.dp)
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

                                text = addActivityViewModel.activityType.value ,
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

                                text = "Select Activity" ,
                                style = TextStyle(
                                    color = Color.LightGray,
                                    fontSize = 16.sp,
                                ),
                            )
                        }

                        IconButton(modifier = Modifier.wrapContentWidth(),
                            onClick = {

                                addActivityViewModel.isMenuExpanded.value = !addActivityViewModel.isMenuExpanded.value

                            }) {
                            Icon(
                                painter = R.drawable.downarrow.resourceImage(),
                                contentDescription = null
                            )
                        }
                    }
                }
                DropdownMenu(
                    expanded = addActivityViewModel.isMenuExpanded.value, 
                    onDismissRequest = { addActivityViewModel.isMenuExpanded.value = false },
                    modifier = Modifier.width(screenWidthDp * 0.90f),
                    
                    ) {
                    addActivityViewModel.activitylistDetail.value?.activityType?.forEach {text ->
                        DropdownMenuItem(onClick = {
                            addActivityViewModel.activityType.value = text.activitytypeName
                            addActivityViewModel.isMenuExpanded.value = false
                        }) {
                            Text(text = text.activitytypeName)
                        }    
                        
                    }
                }


            }


            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                text = "Activity Date", style = TextStyle(fontWeight = FontWeight.W500),
                textAlign = TextAlign.Start
            )
            
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Surface(
                    modifier = Modifier
                        .width(screenWidthDp * .95f)
                        .height(55.dp)
                        .padding(horizontal = 10.dp)
                        .background(color = Color(0xffDDDDDD)),
                    border = BorderStroke(1.dp, Color(0xff666666)),

                    ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if(addActivityViewModel.selectedDate.value.isNotEmpty()) {
                            Text(
                                modifier = Modifier
                                    .weight(5f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),
                                text = addActivityViewModel.selectedDate.value,
                                style = TextStyle(
                                    color = Color(0xff212121),
                                    fontSize = 16.sp,
                                ),
                            )
                        }else{
                            Text(
                                modifier = Modifier
                                    .weight(5f)
                                    .padding(
                                        vertical = 3.dp, horizontal = 5.dp
                                    ),
                                text = "Select Date",
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
            }


            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                text = "Alternate Phone Number", style = TextStyle(fontWeight = FontWeight.W500),
                textAlign = TextAlign.Start
            )
            MobileNumberInputField(
                onNumberChange = addActivityViewModel::onChangeAlterPhn,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .size(height = 55.dp, width = 300.dp),
                textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff666666),
                    unfocusedBorderColor = Color(0xff666666)
                ))

            /*OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .size(height = 55.dp, width = 300.dp),
                value = addActivityViewModel.alterPhn.value,
                placeholder = { Text(text = "Enter Alternate Number", style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 16.sp,

                )) },
                onValueChange = addActivityViewModel::onChangeAlterPhn,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff666666),
                    unfocusedBorderColor = Color(0xff666666)
                )
            )*/

            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Time", style = TextStyle(fontWeight = FontWeight.W500),
                textAlign = TextAlign.Start
            )
            Surface(
                modifier = Modifier
                    .width(screenWidthDp * .95f)
                    .height(55.dp)
                    .padding(horizontal = 10.dp)
                    .background(color = Color(0xffDDDDDD)),
                border = BorderStroke(1.dp, Color(0xff666666)),

                ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (addActivityViewModel.selectedTime.value.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .weight(5f)
                                .padding(
                                    vertical = 3.dp, horizontal = 5.dp
                                ),
                            text = addActivityViewModel.selectedTime.value,
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
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                text = "Company Name", style = TextStyle(fontWeight = FontWeight.W500),
                textAlign = TextAlign.Start
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .size(height = 55.dp, width = 300.dp),
                value = addActivityViewModel.companyName.value,
                placeholder = { Text(text = "Enter Company Name", style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 16.sp,

                    )) },
                onValueChange = addActivityViewModel::onChangeCompanyName,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff666666),
                    unfocusedBorderColor = Color(0xff666666)
                )
            )

            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                text = "Notes", style = TextStyle(fontWeight = FontWeight.W500),
                textAlign = TextAlign.Start
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .size(height = 65.dp, width = 300.dp),
                value = addActivityViewModel.activityNote.value,
                placeholder = { Text(text = "Enter Notes", style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 16.sp,

                    )) },
                onValueChange = addActivityViewModel::onChangeNote,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff666666),
                    unfocusedBorderColor = Color(0xff666666)
                )
            )

            val noteDetails = addActivityViewModel.noteDetails.collectAsState()

            LazyRow {
                noteDetails.value?.notes?.size?.let {
                    items(it) {idx->
                        Surface(
                            color = Color(android.graphics.Color.parseColor(noteDetails.value!!.notes[idx].bgColor)),
                            modifier = Modifier
                                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                                .clickable {
                                    addActivityViewModel.onClickDataChip(idx)
                                }
                                .clip(RoundedCornerShape(18.dp))
                                .background(
                                    color = Color(android.graphics.Color.parseColor(noteDetails.value!!.notes[idx].bgColor)),
                                )
                                .padding(12.dp)


                        ) {
                            Text(
                                text = addActivityViewModel.noteDetails.collectAsState().value?.notes!![idx].notesName,
                                style =if(addActivityViewModel.noteDetails.collectAsState().value?.notes!![idx].isSelected)
                                    TextStyle(
                                    fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White
                                ) else  TextStyle(
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }
                }
            }





        }


    }

