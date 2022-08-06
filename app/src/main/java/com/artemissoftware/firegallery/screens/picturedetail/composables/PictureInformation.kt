package com.artemissoftware.firegallery.screens.picturedetail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.R
import com.artemissoftware.common.composables.chip.FilterChipSection
import com.artemissoftware.common.models.Chip
import com.artemissoftware.common.theme.FGStyle.TextMeOne
import com.artemissoftware.common.theme.FGStyle.TextMeOneBold12
import com.artemissoftware.common.theme.FGStyle.TextMeOne12
import com.artemissoftware.common.theme.FGStyle.TextMeOneBold
import com.artemissoftware.common.theme.FGStyle.TextOswaldBold
import com.artemissoftware.common.theme.FGStyle.TextOswaldMedium

@Composable
fun PictureInformation(
    title: String,
    author: String,
    filters: List<Chip>
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        PictureDetail(title = "Title", description = title)
        PictureDetail(title = "Author", description = author)
        FilterChipSection(filters = filters)

    }

}


@Preview(showBackground = true)
@Composable
private fun PictureInformationPreview() {

    PictureInformation("title", "author", Chip.mockChips)
}

@Composable
private fun PictureDetail(title: String, description: String) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = description,

            style = TextMeOne12
        )
        Text(
            text = title,
            style = TextMeOneBold12
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PictureDetailPreview() {

    PictureDetail("title", "description")
}