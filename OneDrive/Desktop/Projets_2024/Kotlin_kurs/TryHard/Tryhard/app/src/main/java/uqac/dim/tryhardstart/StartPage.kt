package uqac.dim.tryhardstart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.ui.theme.Green
import uqac.dim.tryhardstart.ui.theme.NoirClair
import uqac.dim.tryhardstart.ui.theme.alatsi
import uqac.dim.tryhardstart.ui.theme.bleu
import uqac.dim.tryhardstart.ui.theme.poppins
import uqac.dim.tryhardstart.ui.theme.roboto

@Composable
fun StartPage(){
    //Span de mon title
    val titleTryhard = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Green)){
            append("Try")
        }
        withStyle(style = SpanStyle(color = Color.Black)){
            append("Hard")
        }
    }
    val description = buildAnnotatedString {
        withStyle(style = SpanStyle(color = NoirClair)){
            append("Choisissons votre prochain billet de bus avec ")
        }
        withStyle(style = SpanStyle(color = Green)){
            append("Try")
        }
        withStyle(style = SpanStyle(color = NoirClair,)){
            append("Hard.")
        }

    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text =titleTryhard,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
            )
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),painter = painterResource(id = R.drawable.bravo), contentDescription =null )
            Text(
                text =description,
                fontSize = 24.sp ,
                fontFamily = poppins,
                letterSpacing = 0.4.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id =R.string.description ),
                fontFamily = poppins,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(200)
            )
        }

        SwipeButton {

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PagePreview(){
    StartPage()
}