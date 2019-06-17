package api

import geoclase.Geo

data class DataRestaurant(val code : Int, val name : String, var address : String, var geoLocation : Geo)

