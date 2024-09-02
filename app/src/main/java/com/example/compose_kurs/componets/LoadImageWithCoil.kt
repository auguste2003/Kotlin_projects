package com.example.compose_kurs.componets



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.compose_kurs.data.CountryInfo

@Composable
fun CountryCardCoil(countryInfo: CountryInfo) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        // Références pour les éléments dans le ConstraintLayout
        val (flag, commonName, region) = createRefs()
        var topGuideLine = createGuidelineFromTop(0.1f) // ligne
        var startGuideLine = createGuidelineFromStart(2.dp) // colonne

        val imagePainter = painterResource(id = countryInfo.flagId)

        AsyncImage(model = countryInfo.flagId, contentDescription = countryInfo.commonName, contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(70.dp)
                .padding(2.dp)
                .constrainAs(flag) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
        // Image du drapeau
      /*  Image(
            painter = painterResource(id = countryInfo.flagId),
            contentDescription = "Country Flag",
            modifier = Modifier
                .height(70.dp)
                .padding(2.dp)
                .constrainAs(flag) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

       */

        // Nom commun du pays
        Text(
            text = countryInfo.commonName,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(2.dp)
                .constrainAs(commonName) {
                    top.linkTo(flag.bottom, margin = 8.dp) // Le nom est sous le drapeau
                    start.linkTo(flag.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints // Remplir l'espace entre les contraintes
                }
        )

        // Région du pays
        Text(
            text = countryInfo.region,
            fontSize = 15.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(2.dp)
                .constrainAs(region) {
                    top.linkTo(commonName.bottom, margin = 4.dp) // La région est sous le nom commun
                    start.linkTo(commonName.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
    }
}
