package com.bsquare.app.presentation.ui.screens.Lead


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.R
import com.bsquare.app.presentation.states.resourceImage
import com.bsquare.app.presentation.ui.view_models.LeadViewModel


@Composable
fun LeadScreen(
    leadViewModel : LeadViewModel =  hiltViewModel(),
){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {paddingValues -> Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        TopAppBar(

            backgroundColor = Color.Red,
            elevation = 2.dp,
            title = { Text("Leads") },
            navigationIcon = {
                Image(

                    modifier = Modifier
                        .size(100.dp)
                        .padding(horizontal = 8.dp),
                    painter =R.drawable.backbutton.resourceImage(), contentDescription = null
                )
            },
            actions = {
                TopAppBarActionButton(
                   painter =  R.drawable.search.resourceImage(),
                    description = "Search",
                ) {}


              TopAppBarActionButton(
                  painter = R.drawable.filter.resourceImage(),
                  description ="filter") {

              }
            }
        )
    }

    }
}

@Composable
fun TopAppBarActionButton(
   painter: Painter,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(painter = painter, contentDescription = description)
    }
}
