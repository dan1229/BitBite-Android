package com.example.daniel.digit

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_results.*
import org.jetbrains.anko.toast

class ResultsActivity : AppCompatActivity() {

    var cardIndeces = IntArray(4)
    var places = ArrayList<Place>()
    var listSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        toolbar_results.title = "Results"
        setSupportActionBar(toolbar_results)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //test places ******************************************************************************************************
        var arr1 = DoubleArray(2)
        arr1[0] = 26.3523517
        arr1[1] = -80.1568702
        var place1 = Place("Sweet Tomatoes", "e5922636c1c678cec92268ce9e03907613f258e6", "Restaurant",
                "CmRaAAAAoawTX5603PlBx7KE3H0OhaD6FkyHRyeqwj_MopXLvtYirZWrvvqrYzpbk2sPzhnEkq-aiXKeozAMshBbPgZSuHpMLTcAtB8aeynfpZ__-o2lJPMrPI-VjYgkySWLwH7dEhAbX5XTWMN6So_5ABc80CRiGhTR3N0t8tOjcAUwpmHRbt77gf9h9w",
                1, 4, arr1)
        var arr2 = DoubleArray(2)
        arr2[0] = 26.362137
        arr2[1] = -80.15299999999999
        var place2 = Place("Bonefish Grill", "3411a36e4d6b0c1c87adab1cd73c3ae0314cebe1", "bar",
                "CmRaAAAAxTcFWTCAoMk0OwYncPFV6J6imuUGTA3B-uX2twxcoFw6Dv9SRpRtZNqVqIW73BqRzwzy9D9jCJxAl0CT-j34kBUB7WzrkVz3s1BuHMZYMt6hzGJycFPe57qgsPLM8MFwEhBfLdBuMYPx6FVjlc9X1yKlGhTnHmkyfRPVRiaTb8RcRSTS-ybBlQ",
                2, 4, arr2)
        var arr3 = DoubleArray(2)
        arr3[0] = 26.4618978
        arr3[1] = -80.07089839999999
        var place3 = Place("The Office Delray", "a53bd95ae1f5779b7a415ec1cddfb6af928b0189", "bar",
                "CmRaAAAAepicIBIE9K8v5awRAUFBgF_FCVGA7j4wJOjvABr2GhgUjxpb361h9MST6OcjGxQ_yImMq2O2QcvWv21_dbuMhPHMLXbZoWzpIEQPG2h8GaNcO_qyVuCGO0j7Z6BNE2TEEhBpbGXT2rrM3Bm4EMbkA70UGhSD5HPWwLdrAZ6qoiMUMdPtX7ilUw",
                2, 4, arr3)
        var arr4 = DoubleArray(2)
        arr3[0] = 26.365652
        arr3[1] = -80.12607299999999
        var place4 = Place("Hooters", "fd78a0c66e03b22525d004c666be633869c827cc", "bar",
                "CmRaAAAAmHecV-otAdP1vBPhCFs3BvEFvLFZHwZ82vvxuD7-wt_6tfSED7AyxjdE5ivAAAGKkjpWEytVSDsHgO7W86ZhPIWBuwfHtd0tKyeu2Dzc40aAmKM6x-6gE1wcRc4CdktPEhBbIYPCE09h5U0vqiXJvc7tGhRmAgR83YO54Bgg7sa9scOeHm0S2g",
                2, 3, arr4)

        places.add(place1)
        places.add(place2)
        places.add(place3)
        places.add(place4)

        listSize = places.size
        // *****************************************************************************************************************


        // Get places ArrayList and size from MainActivity
        val bundle = intent.extras
//        places = bundle.getParcelableArrayList<Place>("EXTRA_PLACES_LIST")
//        listSize = bundle.getInt("EXTRA_LIST_SIZE")


        // ArrayList index
        var index = 0

        // Update all three cards for the first time
        var card = 1
        while((index < listSize) && (card <= 3)) {
            updateCard(card, index)
            index = nextIndex(index)
            ++card
        }

        // Set on click listener for "Next 3" button
        displayNext3.setOnClickListener{
            if (index < listSize) { // Index in bounds, update cards
                for (i in 1..3) {
                    updateCard(i, index)
                    index = nextIndex(index)
                }
            }
            else { // Index out of bounds, display error message
                toast("Out of options.\nPlease go back or search again.")
            }
        }

        // Set on click listeners for cards to send to Google Maps
        card1.setOnClickListener {
            if (cardIndeces[1] != -1)
                openWebPage(places[cardIndeces[1]].makeGoogleMapsURL(), this)
        }

        card2.setOnClickListener {
            if (cardIndeces[2] != -1)
                openWebPage(places[cardIndeces[2]].makeGoogleMapsURL(), this)
        }

        card3.setOnClickListener {
            if (cardIndeces[3] != -1)
                openWebPage(places[cardIndeces[3]].makeGoogleMapsURL(), this)
        }
    }

    // Opens web page for Google Maps
    private
    fun openWebPage(urls: String, mContext : Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        mContext.startActivity(intents)
    }

    // Calls update function for each segment of card
    private
    fun updateCard(card : Int, index : Int) {
        var i = index
        if(index >= listSize) { // Check if index is in bounds
            i = -1
        }
        updateName(card, i)
        updatePrice(card, i)
        updatePhoto(card, i)
        updateRating(card, i)
        updateDescription(card, i)
        cardIndeces[card] = index
    }

    // Updates name on the card
    private
    fun updateName(card : Int, index : Int) {
        val textView = getNameView(card)
        if(index >= 0){ // In bounds
            textView.setText(places[index].name)
        } else{ // Out of bounds, default val
            textView.setText(R.string.default_name)
        }
    }

    // Updates price on the card
    private
    fun updatePrice(card : Int, index : Int) {
        var textView = getPriceView(card)
        if(index >= 0){ // In bounds
            textView.setText(priceConversion(places[index].price))
        } else{ // Out of bounds, default val
            textView.setText(R.string.default_price)
        }
    }

    // Updates photo on the card
    private
    fun updatePhoto(card : Int, index : Int) {
        var view = getImageView(card)
        if(index >= 0){ // In bounds
            placePhotoCall(places[index].photoRef, view)
        } else{ // Out of bounds, default photo
            view.setImageDrawable(getResources().getDrawable(R.drawable.main_activity_logo))
        }
    }

    // Updates rating on the card
    private
    fun updateRating(card : Int, index : Int) {
        var textView = getRatingView(card)
        if(index >= 0){ // In bounds
            textView.setText(ratingConversion(places[index].rating))
        } else{ // Out of bounds, default val
            textView.setText(R.string.default_rating)
        }
    }

    // Updates description on the card
    private
    fun updateDescription(card : Int, index : Int) {
        var textView = getDescriptionView(card)
        if(index >= 0){ // In bounds
            textView.setText(places[index].description)
        } else{ // Out of bounds, default val
            textView.setText(R.string.default_description)
        }
    }

    // Gets TextView for name view based on card number
    private
    fun getNameView(card : Int) : TextView {
        var view = findViewById<TextView>(R.id.name1) as TextView
        when(card) {
            1 -> view = findViewById<TextView>(R.id.name1) as TextView
            2 -> view = findViewById<TextView>(R.id.name2) as TextView
            3 -> view = findViewById<TextView>(R.id.name3) as TextView
        }
        return view
    }

    // Gets TextView for price view based on card number
    private
    fun getPriceView(card : Int) : TextView {
        var view = findViewById<TextView>(R.id.price1) as TextView
        when(card) {
            1 -> view = findViewById<TextView>(R.id.price1) as TextView
            2 -> view = findViewById<TextView>(R.id.price2) as TextView
            3 -> view = findViewById<TextView>(R.id.price3) as TextView
        }
        return view
    }

    // Gets ImageView for photo view based on card number
    private
    fun getImageView(card : Int) : ImageView {
        var view = findViewById<ImageView>(R.id.image1) as ImageView
        when(card) {
            1 -> view = findViewById<ImageView>(R.id.image1) as ImageView
            2 -> view = findViewById<ImageView>(R.id.image2) as ImageView
            3 -> view = findViewById<ImageView>(R.id.image3) as ImageView
        }
        return view
    }

    // Gets TextView for rating view based on card number
    private
    fun getRatingView(card : Int) : TextView {
        var view = findViewById<TextView>(R.id.rating1) as TextView
        when(card) {
            1 -> view = findViewById<TextView>(R.id.rating1) as TextView
            2 -> view = findViewById<TextView>(R.id.rating2) as TextView
            3 -> view = findViewById<TextView>(R.id.rating3) as TextView
        }
        return view
    }

    // Gets TextView for descritpion view based on card number
    private
    fun getDescriptionView(card : Int) : TextView {
        var view = findViewById<TextView>(R.id.description1) as TextView
        when(card) {
            1 -> view = findViewById<TextView>(R.id.description1) as TextView
            2 -> view = findViewById<TextView>(R.id.description2) as TextView
            3 -> view = findViewById<TextView>(R.id.description3) as TextView
        }
        return view
    }

    // Converts price to string of "$" based on value
    private
    fun priceConversion(price : Int) : String {
        var res = ""
        when(price) {
            1 -> res = "$"
            2 -> res = "$$"
            3 -> res = "$$$"
            4 -> res = "$$$$"
            5 -> res = "$$$$$"
            else -> res = ""
        }
        return res
    }

    // Converts rating to string of stars based on value
    private
    fun ratingConversion(rating : Int) : String {
        var res = ""
        when (rating) {
            1 -> res = "1/5"
            2 -> res = "2/5"
            3 -> res = "3/5"
            4 -> res = "4/5"
            5 -> res = "5/5"
            else -> res = ""
        }
        return res
    }

    // Calls Place Photo API and returns image
    private
    fun placePhotoCall(ref : String, view : ImageView) {
        Glide.with(this).load(createRequestURL(ref)).into(view)
    }

    // Creates URL for Place Photo API request
    private
    fun createRequestURL(ref : String) : String {
        return  "https://maps.googleapis.com/maps/api/place/photo?" +
                "maxwidth=400" +
                "&photoreference=" + ref +
                "&key=" + getString(R.string.google_api_key)
    }

    // "Iterator" function for index
    private
    fun nextIndex(n : Int) : Int{
        var i = n
        return ++i
    }


    // Test dialog - ya know for testing stuff
    private
    fun testDialog(s : String) {
        // TEST DIALOG
        val builder = AlertDialog.Builder(this@ResultsActivity)
        builder.setMessage(s)
        val dialog: AlertDialog = builder.create()
        dialog.show()
        // TEST DIALOG
    }


} // END CLASS ResultsActivity.kt
