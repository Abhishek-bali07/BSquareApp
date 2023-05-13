package com.bsquare.app.presentation.ui.screens.Lead

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.presentation.ui.view_models.FilterViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterScreen(
    filterViewModel: FilterViewModel = hiltViewModel(),

    ) {

    Surface(modifier = Modifier.fillMaxSize(1f)) {
        Column(
            modifier = Modifier.padding(10.dp)) {

            ChipSection(filterViewModel)
        }
    }

}

@Composable
fun ChipSection(
    filterViewModel: FilterViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        filterViewModel.filterDetails.collectAsState().value?.apply {
            LazyRow {
                items(dataLead.size) { idx ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                            .clickable {
                                filterViewModel.onClickDataLeadChip(idx)
                            }
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = if (dataLead[idx].isSelected) Color.Red else Color.White)
                            .padding(15.dp)
                    ) {
                        Text(text = dataLead[idx].dataLeadName)
                    }
                }
            }
        }


    }
}




