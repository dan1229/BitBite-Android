package com.example.daniel.digit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Daniel on 6/13/2018.
 */
@Parcelize
class Place constructor (var name:String, var placeID:String, var description:String,
                         var photoRef:String, var price:Int, var rating:Int,
                         var location:Array<Double>) : Parcelable {

    var googleURL : String
    var lat : Double
    var lng : Double

    // Initialization instructions
    init{
        this.name = name
        this.placeID = placeID
        this.description = description
        this.photoRef = photoRef
        this.price = price
        this.rating = rating
        this.lat = location[0]
        this.lng = location[1]
        googleURL = makeGoogleMapsURL()
    }

    // Public Methods--------------------------------------------------------------------------------

    //*****Functions*****//
    private
    fun makeGoogleMapsURL(): String{
        // https://www.google.com/maps/search/?api=1&parameters
        // Param
        // &query = lat + lng
        // &query_place_id = placeID
        var url =  "https://www.google.com/maps/search/?api=1&" +
                "&query=" + lat + "," + lng +
                "&query_place_id=" + placeID
        return url
    }


    //*****Accessor Methods*****//
    fun get_name(): String {
        return name
    }

    fun get_placeID(): String {
        return placeID
    }

    fun get_description(): String {
        return description
    }

    fun get_photoRef(): String {
        return photoRef
    }

    fun get_googleURL(): String {
        return googleURL
    }

    fun get_price(): Int {
        return price
    }

    fun get_rating(): Int {
        return rating
    }

    fun get_long(): Double {
        return lng
    }

    fun get_lat(): Double {
        return lat
    }

    //*****Mutator Methods*****//
    fun set_name(n: String) {
        name = n
    }

    fun set_placeID(n: String) {
        placeID = n
    }

    fun set_description(n: String) {
        description = n
    }

    fun set_photoRef(n: String) {
        photoRef = n
    }

    fun set_url(n: String) {
        googleURL = n
    }

    fun set_price(n: Int) {
        price = n
    }

    fun set_rating(n: Int) {
        rating = n
    }

    fun set_lng(n: Double) {
        lng = n
    }

    fun set_lat(n: Double) {
        lat = n
    }

} // END CLASS PLACE