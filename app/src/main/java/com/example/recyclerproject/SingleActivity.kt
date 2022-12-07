package com.example.recyclerproject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SingleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        // locate the Shared Preference

        val prefs:SharedPreferences = getSharedPreferences("productDB", Context.MODE_PRIVATE)

        val productName = prefs.getString("productName", "")
        val name = findViewById<TextView>(R.id.productName)
        name.text = productName

        val productLearnMore = prefs.getString("productLearnMore", "")
        val learn = findViewById<TextView>(R.id.productLearnMore)
        learn.text = productLearnMore

        val productDesc = prefs.getString("productDesc", "")
        val desc = findViewById<TextView>(R.id.productDescription)
        desc.text = productDesc

        val image = prefs.getString("image", "")
        val imageView = findViewById<ImageView>(R.id.image)
        Glide.with(applicationContext).load(image)
            .apply(RequestOptions().centerCrop())
            .into(imageView)
    }
}