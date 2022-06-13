package com.thesearch.response

data class SearchResponse(
    val drinks:ArrayList<Drink>
)

data class Drink(
    var idDrink:String? = null,
    var strDrink:String? = null,
    var strDrinkAlternate:String? = null,
    var strTags:String? = null,
    var strVideo:String? = null,
    var strCategory:String? = null,
    var strIBA:String? = null,
    var strAlcoholic:String? = null,
    var strGlass:String? = null,
    var strInstructions:String? = null,
    var strInstructionsES:String? = null,
    var strInstructionsDE:String? = null,
    var strInstructionsFR:String? = null,
    var strInstructionsIT:String? = null,
    var strDrinkThumb:String? = null,
    var strIngredient1:String? = null,
    var strIngredient2:String? = null,
    var strIngredient3:String? = null,
    var strIngredient4:String? = null,
)
