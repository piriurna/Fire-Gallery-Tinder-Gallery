package com.artemissoftware.firegallery.screens.picturedetail.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.common.composables.chip.FGChipSection
import com.artemissoftware.common.composables.text.FGText
import com.artemissoftware.common.models.Chip
import com.artemissoftware.common.theme.FGStyle.TextAlbertSansBold16
import com.artemissoftware.domain.models.Picture
import com.artemissoftware.firegallery.R

@Composable
fun PictureInformation(
    modifier: Modifier = Modifier,
    picture: Picture? = null,
    tags: List<Chip>
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        picture?.let {
            PictureDetail(title = stringResource(R.string.Title), description = "it.title")
            PictureDetail(title = stringResource(R.string.Author), description = "it.author")
            PictureDetail(title = stringResource(R.string.code), description = it.id)
            FGChipSection(chips = tags)
        }
    }

}


@Composable
private fun PictureDetail(title: String, description: String) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        FGText(
            text = description
        )
        FGText(
            text = title,
            style = TextAlbertSansBold16
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PictureDetailPreview() {

    PictureDetail("title", "description")
}


@Preview(showBackground = true)
@Composable
private fun PictureInformationPreview() {

    PictureInformation(modifier= Modifier,Picture.picturesMockList[0], Chip.mockChips)
}
