package api

import geoclase.Geo

data class DataRestaurant(val code : Int, val name : String, var address : String, var description : String, var geoLocation : Geo, var restaurantImage : String)

