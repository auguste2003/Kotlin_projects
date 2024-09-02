package com.example.compose_kurs.data

class CountryInfo (val flagId:Int,  // Cette Dataclass contient toutes les informations néccessaires pour le composant .
                   val commonName:String,
                   val officialName:String,
                   val region: String ,
                   val subRegion:String ,
                   val currencySymbol:String
                   , val mobileCode:String
                   , val tld:String){
}