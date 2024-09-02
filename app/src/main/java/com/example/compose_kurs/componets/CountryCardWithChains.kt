package com.example.compose_kurs.componets





import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.compose_kurs.data.CountryInfo

@Composable
fun CountryCardContraintLayoutChains(countryInfo: CountryInfo) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        // Références pour les éléments dans le ConstraintLayout
        val (flag, commonName, region) = createRefs()
 var verticalChain = createVerticalChain(flag,commonName,region, chainStyle = ChainStyle.Spread) //Spread ?


        // Image du drapeau
        Image(
            painter = painterResource(id = countryInfo.flagId),
            contentDescription = "Country Flag",

            modifier = Modifier
                .fillMaxSize()

                .padding(2.dp)
                .constrainAs(flag) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            contentScale = ContentScale.Crop,
        )

        // Nom commun du pays
        Text(
            text = countryInfo.commonName,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(2.dp)
                .constrainAs(commonName) {
                  start.linkTo(flag.end)
                    end.linkTo(flag.end)
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

                }
        )
    }
}


