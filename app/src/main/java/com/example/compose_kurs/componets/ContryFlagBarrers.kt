package com.example.compose_kurs.componets





import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.compose_kurs.data.CountryInfo

@Composable
fun CountryCardContraintLayoutBarrier(countryInfo: CountryInfo) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(3.dp, Color.Black)
            .padding(4.dp)
    ) {
        // Références pour les éléments dans le ConstraintLayout
        val (flag, commonName, region) = createRefs()


        // Image du drapeau
        Image(
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
        var topBarrier = createTopBarrier(flag)
        var startBarrier= createStartBarrier(flag)

        // Nom commun du pays
        Text(
            text = countryInfo.commonName,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(2.dp)
                .constrainAs(commonName) {
                    top.linkTo(flag.bottom, margin = 8.dp) // Le nom est sous le drapeau
                    start.linkTo(startBarrier)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints // Remplir l'espace entre les contraintes
                },
            style = MaterialTheme.typography.titleLarge
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
                    start.linkTo(startBarrier)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
    }
}
