package com.bsquare.app.presentation.ui.screens.Lead


import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.custom_composable.AppButton
import com.bsquare.app.presentation.ui.view_models.AddLeadViewModel

@Composable
fun AddLeadScreen(
    addLeadViewModel: AddLeadViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    Scaffold(scaffoldState = scaffoldState)
    {   paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(backgroundColor = Color.Red, elevation = 2.dp, title = {
                Text(
                    "Add New Lead",
                    style = TextStyle(
                        color = Color.White, textAlign = TextAlign.Center, fontSize = 20.sp
                    )
                )
            },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Image( modifier = Modifier
                            .size(40.dp)
                            .padding(horizontal = 8.dp),
                            painter = R.drawable.backbutton.resourceImage(), contentDescription = null)
                    }

                })
            
            
            AddClientSection(addLeadViewModel)

            AppButton(
                enable =addLeadViewModel.enableBtn.value ,
                loading =addLeadViewModel.loginLoading.value,
                action = { /*TODO*/ },
                name =R.string.add_now )
        }

    }
}

@Composable
fun AddClientSection(addLeadViewModel: AddLeadViewModel) {
  Surface(
      modifier = Modifier.fillMaxWidth(),
      color = Color.White ) {
      Column(
          modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 12.dp, vertical = 5.dp),
          horizontalAlignment = Alignment.Start,
          verticalArrangement = Arrangement.Top
      ) {
          Text(modifier = Modifier.padding(horizontal = 10.dp),
              text = "Client Name", style = TextStyle(fontWeight = FontWeight.W500))
          OutlinedTextField(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 10.dp, horizontal = 10.dp)
                  .size(height = 60.dp, width = 300.dp),
              value =addLeadViewModel.clientName.value ,
              placeholder = { Text(text = "Enter Client Name ") },
              onValueChange = addLeadViewModel::onChangeName,
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor =Color.Black,
                  unfocusedBorderColor = Color.LightGray
              )
              )
          Text(modifier = Modifier.padding(horizontal = 10.dp),
              text = "Email ID" ,style = TextStyle(fontWeight = FontWeight.W500))
          OutlinedTextField(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 10.dp, horizontal = 10.dp)
                  .size(height = 60.dp, width = 300.dp),

              value =addLeadViewModel.emailId.value ,
              placeholder = { Text(text = "Type email address") },
              onValueChange = addLeadViewModel::onEmailChange,
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor =Color.Black,
                  unfocusedBorderColor = Color.LightGray
              )
          )
          Text(modifier = Modifier.padding(horizontal = 10.dp),
              text = "Phone Number", style = TextStyle(fontWeight = FontWeight.W500))
          OutlinedTextField(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 10.dp, horizontal = 10.dp)
                  .size(height = 60.dp, width = 300.dp),
              value =addLeadViewModel.phoneNumber.value ,
              onValueChange = addLeadViewModel::onNumberChange,
              placeholder = { Text(text = "Enter Phone Number") },
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor =Color.Black,
                  unfocusedBorderColor = Color.LightGray
              )
          )
          Text(modifier = Modifier.padding(horizontal = 10.dp),
              text = "Alternate Phone Number", style = TextStyle(fontWeight = FontWeight.W500))

          OutlinedTextField(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 10.dp, horizontal = 10.dp)
                  .size(height = 60.dp, width = 300.dp),
              value =addLeadViewModel.alternateNumber.value ,
              onValueChange = addLeadViewModel::onAlternateChange,
              placeholder = { Text(text = "Enter Alternate Number") },
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor =Color.Black,
                  unfocusedBorderColor = Color.LightGray
              )
          )

          Text(modifier = Modifier.padding(horizontal = 10.dp),
              text = "Company Name", style = TextStyle(fontWeight = FontWeight.W500))
          OutlinedTextField(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 10.dp, horizontal = 10.dp)
                  .size(height = 60.dp, width = 300.dp),
              value =addLeadViewModel.comName.value ,
              onValueChange = addLeadViewModel::onCompanyChange,
              placeholder = { Text(text = "Enter Company Name") },
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor =Color.Black,
                  unfocusedBorderColor = Color.LightGray
              )
          )
          Text(modifier = Modifier.padding(horizontal = 10.dp),
              text = "Website", style = TextStyle(fontWeight = FontWeight.W500))
          OutlinedTextField(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 10.dp, horizontal = 10.dp)
                  .size(height = 60.dp, width = 300.dp),
              value =addLeadViewModel.websiteName.value ,
              onValueChange = addLeadViewModel::onWebsiteChange,
              placeholder = { Text(text = "Enter website url") },
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor =Color.Black,
                  unfocusedBorderColor = Color.LightGray
              )
          )
          Text(modifier = Modifier.padding(horizontal = 10.dp),
              text = "Sale Value", style = TextStyle(fontWeight = FontWeight.W500))
          OutlinedTextField(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 10.dp, horizontal = 10.dp)
                  .size(height = 60.dp, width = 300.dp),
              value =addLeadViewModel.saleValue.value ,
              onValueChange = addLeadViewModel::onsaleChange,
              placeholder = { Text(text = "Enter sale value") },
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor =Color.Black,
                  unfocusedBorderColor = Color.LightGray
              )
          )
          Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {

              Text(modifier = Modifier.weight(1f),text = "Address", style = TextStyle(fontWeight = FontWeight.W500))
              Row(modifier = Modifier.weight(1f),
                  horizontalArrangement = Arrangement.SpaceBetween
              ) {
                  Text(text = "Current Location", style = TextStyle(fontWeight = FontWeight.W500))
                  SwitchButton()
              }

          }

          //
          Text(modifier = Modifier.padding(horizontal = 10.dp),text = "Notes", style = TextStyle(fontWeight = FontWeight.W500))
          OutlinedTextField(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 10.dp, horizontal = 10.dp)
                  .size(height = 60.dp, width = 300.dp),
              value =addLeadViewModel.notes.value ,
              onValueChange = addLeadViewModel::onChangeNotes,
              placeholder = { Text(text = "Enter Notes") },
              colors = TextFieldDefaults.outlinedTextFieldColors(
                  focusedBorderColor =Color.Black,
                  unfocusedBorderColor = Color.LightGray
              )
          )

      }
    
  }
}

@Composable
fun SwitchButton(
    width: Dp = 60.dp,
    height: Dp = 30.dp,
    checkedTrackColor: Color = Color(0xFFFF5E00),
    uncheckedTrackColor: Color = Color(0xFFe0e0e0),
    gapBetweenThumbAndTrackEdge: Dp = 8.dp,
    borderWidth: Dp = 1.dp,
    cornerSize: Int = 50,
    iconInnerPadding: Dp = 4.dp,
    thumbSize: Dp = 20.dp
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var switchOn by remember {
        mutableStateOf(true)
    }

    val alignment by animateAlignmentAsState(if (switchOn) 1f else -1f)

    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .border(
                width = borderWidth,
                color = if (switchOn) checkedTrackColor else uncheckedTrackColor,
                shape = RoundedCornerShape(percent = cornerSize)
            )
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                switchOn = !switchOn
            },
        contentAlignment = Alignment.Center
    ) {

        // this is to add padding at the each horizontal side
        Box(
            modifier = Modifier
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment
        ) {

            // thumb with icon
            Icon(
                imageVector = if (switchOn) Icons.Filled.Done else Icons.Filled.Close,
                contentDescription = if (switchOn) "Enabled" else "Disabled",
                modifier = Modifier
                    .size(size = thumbSize)
                    .background(
                        color = if (switchOn) checkedTrackColor else uncheckedTrackColor,
                        shape = CircleShape
                    )
                    .padding(all = iconInnerPadding),
                tint = Color.White
            )
        }
    }

    // gap between switch and the text
    Spacer(modifier = Modifier.height(height = 16.dp))

    //Text(text = if (switchOn) "ON" else "OFF")
}



@SuppressLint("UnrememberedMutableState")
@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}
