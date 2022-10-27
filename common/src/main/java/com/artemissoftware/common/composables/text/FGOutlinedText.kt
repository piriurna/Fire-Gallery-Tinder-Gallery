package com.artemissoftware.common.composables.text

import android.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FGOutlinedText(
    text : String,
    textSize: Float = 100F,
    outsideColor: Int = Color.BLACK,
    insideColor: Int = Color.WHITE
) {


    val outsideTextPaintStroke = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = android.graphics.Paint.Style.STROKE
        this.textSize = textSize
        color = outsideColor
        strokeWidth = 4f
        strokeMiter= 4f
        strokeJoin = android.graphics.Paint.Join.ROUND
    }

    val insideTextPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = android.graphics.Paint.Style.FILL
        this.textSize = textSize
        color = insideColor
    }

    val lolo = (textSize/2).toInt()

    Canvas(
        modifier = Modifier
            //.fillMaxSize()
            .fillMaxWidth()
            .height((lolo).dp)
            //.fillMaxHeight(0.35f)
        ,
        onDraw = {
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    text,
                    0F,textSize,
                    //200f,
                    //55.dp.toPx(),
                    outsideTextPaintStroke
                )

                it.nativeCanvas.drawText(
                    text,
                    0F,textSize,
//                    200f,
//                    200.dp.toPx(),
                    insideTextPaint
                )
            }
        }
    )
}

@Preview
@Composable
private fun FGOutlinedTextPreview() {

    Column {
        FGOutlinedText(text = "Fire gallery one")
        FGOutlinedText(text = "Fire gallery two", outsideColor = Color.CYAN, textSize = 12F)
        FGOutlinedText(text = "Fire gallery three", outsideColor = Color.GREEN)
    }

}