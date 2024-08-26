package uqac.dim.tryhardstart.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uqac.dim.tryhardstart.R

val monsterart = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_regular),

)
val aptos = FontFamily(
    Font(R.font.aptos_bold, FontWeight.Bold),
    Font(R.font.aptos_semibold, FontWeight.SemiBold),
    Font(R.font.aptos),

    )
val roboto = FontFamily(
    Font(R.font.roboto)
)
val arial = FontFamily(
    Font(R.font.arial)
)
val poppins = FontFamily(
    Font(R.font.poppins),
    Font(R.font.poppins_bold, FontWeight.Bold)
)
val alatsi = FontFamily(
    Font(R.font.alatsi)
)
val amaranth = FontFamily(
    Font(R.font.amaranth)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = monsterart,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)