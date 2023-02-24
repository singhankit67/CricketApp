package com.example.cricketapp.Util

class UtilClass {
    fun countryName(value:String): String{
        if(value == "4"){
            return "IND"
        }
        else if (value == "5") {
            return "NZ"
        }
        else if (value == "7") {
            return "SA"
        }
        else if (value == "6") {
            return "PAK"
        }
        else {
            return "null"
        }
    }
}