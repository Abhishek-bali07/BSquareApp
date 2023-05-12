package com.bsquare.app.presentation.ui.screens.Lead

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bsquare.app.presentation.ui.view_models.FilterViewModel



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterScreen(
    filterViewModel: FilterViewModel = hiltViewModel(),
) {

    Surface(color = Color.White) {
        Column(modifier = Modifier.padding(10.dp)) {

            ChipSection()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChipSection() {
    Text(text = "Data Lead added", style = typography.h6, modifier = Modifier.padding(8.dp))
    Column {
        Row(modifier = Modifier.padding(8.dp)) {
            CustomChip(
                selected = true,
                text = "Chip",
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            CustomChip(
                selected = false,
                text = "Inactive",
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            CustomChip(
                selected = false,
                text = "Inactive",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            CustomChip(
                selected = true,
                text = "Inactive",
                modifier = Modifier.padding(horizontal = 8.dp)
            )


        }
    }



    AssistChip(
        onClick = { /* Do something! */ },
        label = { Text("Assist Chip") },
        leadingIcon = {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}

@Composable
fun CustomChip(selected: Boolean, text: String, modifier: Modifier) {

    Surface(
        color = when {
            selected -> MaterialTheme.colors.onSurface
            else -> Color.Transparent
        },
        contentColor = when {
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.LightGray
        },
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> Color.LightGray
            }
        ),
        modifier = modifier
    ) {
        // Add text to show the data that we passed
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp)
        )

    }

}


