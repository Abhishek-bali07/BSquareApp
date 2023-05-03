package com.bsquare.app.presentation.ui.screens.followup

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun AddTaskScreen(
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

            AddTaskSection(addTaskViewModel)

            AppButton(
                enable = addTaskViewModel.enableBtn.value,
                loading = addTaskViewModel.addLoading.value,
                action = { /*TODO*/ },
                name =  R.string.add_now)

        }
    }


}

@Composable
fun AddTaskSection(
    addTaskViewModel: AddTaskViewModel
) {
  Surface(
      modifier = Modifier.fillMaxWidth(),
      color = Color.White) {
      Column( modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 5.dp),
          horizontalAlignment = Alignment.Start,
          verticalArrangement = Arrangement.Top) {


          Text(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
              text = "Task For*", style = TextStyle(fontWeight = FontWeight.W500)
          )


          Row( modifier = Modifier
              .weight(1f)
              .padding(horizontal = 15.dp)) {

              Surface(
                  modifier = Modifier
                      .fillMaxWidth()
                      .padding(horizontal = 10.dp)
                      .background(color = Color(0xffDDDDDD)),
                  border = BorderStroke(1.dp,Color(0xff666666)),

                  ) {
                  Row(
                      horizontalArrangement = Arrangement.Center,
                      verticalAlignment = Alignment.CenterVertically
                  ){
                      Text(
                          modifier = Modifier
                              .weight(5f)
                              .padding(
                                  vertical = 3.dp, horizontal = 5.dp
                              ),
                          text = addTaskViewModel.selectLeads.value,
                          style = TextStyle(
                              color = Color(0xffFF5E00),
                              fontSize = 12.sp,
                              fontWeight = FontWeight.Bold
                          ),
                      )
                      IconButton(modifier = Modifier.weight(1f),onClick = {

                      }) {
                          Icon(painter = R.drawable.downarrow.resourceImage(), contentDescription = null)
                      }
                  }
              }
              DropdownMenu(
                  expanded = addTaskViewModel.isMenuExpanded.value,
                  onDismissRequest = { addTaskViewModel.isMenuExpanded.value = false },
              ) {

              }
          }



          Text(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
              text = "Task Type", style = TextStyle(fontWeight = FontWeight.W500)
          )
          Surface(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 10.dp)
                  .background(color = Color(0xffDDDDDD)),
              border = BorderStroke(1.dp,Color(0xff666666)),

              ) {
              Row(
                  horizontalArrangement = Arrangement.Center,
                  verticalAlignment = Alignment.CenterVertically
              ){
                  Text(
                      modifier = Modifier
                          .weight(5f)
                          .padding(
                              vertical = 3.dp, horizontal = 5.dp
                          ),
                      text = addTaskViewModel.taskType.value,
                      style = TextStyle(
                          color = Color(0xffFF5E00),
                          fontSize = 12.sp,
                          fontWeight = FontWeight.Bold
                      ),
                  )
                  IconButton(modifier = Modifier.weight(1f),onClick = {

                  }) {
                      Icon(painter = R.drawable.downarrow.resourceImage(), contentDescription =null )
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
              border = BorderStroke(1.dp,Color(0xff666666)),

              ) {
              Row(
                  horizontalArrangement = Arrangement.Center,
                  verticalAlignment = Alignment.CenterVertically
              ){
                  Text(
                      modifier = Modifier
                          .weight(5f)
                          .padding(
                              vertical = 3.dp, horizontal = 5.dp
                          ),
                      text = addTaskViewModel.taskType.value,
                      style = TextStyle(
                          color = Color(0xffFF5E00),
                          fontSize = 12.sp,
                          fontWeight = FontWeight.Bold
                      ),
                  )
                  IconButton(
                      modifier = Modifier.weight(1f),
                      onClick = {

                  }) {
                      Icon(painter = R.drawable.sdate.resourceImage(), contentDescription =null )
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
              border = BorderStroke(1.dp,Color(0xff666666)),

              ) {
              Row(
                  horizontalArrangement = Arrangement.Center,
                  verticalAlignment = Alignment.CenterVertically
              ){
                  Text(
                      modifier = Modifier
                          .weight(5f)
                          .padding(
                              vertical = 3.dp, horizontal = 5.dp
                          ),
                      text = addTaskViewModel.taskType.value,
                      style = TextStyle(
                          color = Color(0xffFF5E00),
                          fontSize = 12.sp,
                          fontWeight = FontWeight.Bold
                      ),
                  )
                  IconButton(
                      modifier = Modifier.weight(1f),
                      onClick = {

                      }) {
                      Icon(painter = R.drawable.sdate.resourceImage(), contentDescription =null )
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
              border = BorderStroke(1.dp,Color(0xff666666)),

              ) {
              Row(
                  horizontalArrangement = Arrangement.Center,
                  verticalAlignment = Alignment.CenterVertically
              ){
                  Text(
                      modifier = Modifier
                          .weight(5f)
                          .padding(
                              vertical = 3.dp, horizontal = 5.dp
                          ),
                      text = addTaskViewModel.taskType.value,
                      style = TextStyle(
                          color = Color(0xffFF5E00),
                          fontSize = 12.sp,
                          fontWeight = FontWeight.Bold
                      ),
                  )
                  IconButton(
                      modifier = Modifier.weight(1f),
                      onClick = {

                      }) {
                      Icon(painter = R.drawable.clock.resourceImage(), contentDescription =null )
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
                  .size(height = 60.dp, width = 300.dp),
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
          Surface(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 10.dp)
                  .background(color = Color(0xffDDDDDD)),
              border = BorderStroke(1.dp,Color(0xff666666)),

              ) {
              Row(
                  horizontalArrangement = Arrangement.Center,
                  verticalAlignment = Alignment.CenterVertically
              ){
                  Text(
                      modifier = Modifier
                          .weight(5f)
                          .padding(
                              vertical = 3.dp, horizontal = 5.dp
                          ),
                      text = addTaskViewModel.taskType.value,
                      style = TextStyle(
                          color = Color(0xffFF5E00),
                          fontSize = 12.sp,
                          fontWeight = FontWeight.Bold
                      ),
                  )
                  IconButton(modifier = Modifier.weight(1f),onClick = {

                  }) {
                      Icon(painter = R.drawable.downarrow.resourceImage(), contentDescription =null )
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
              value = addTaskViewModel.repeatVal.value,
              placeholder = { Text(text = "Description") },
              onValueChange = addTaskViewModel::onChangeRepeatVal,
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor = Color(0xff666666),
                  unfocusedBorderColor = Color(0xff666666)
              )
          )








      }
  }
}
