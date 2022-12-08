package com.example.recyclerproject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

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

        val buyBtn = findViewById<Button>(R.id.BtnSingle)
        buyBtn.setOnClickListener {
            val client = AsyncHttpClient(true, 80, 443)
            val payload = JSONObject()
            payload.put("phone", "254710638127")
            payload.put("amount", 1)

            val body = StringEntity(payload.toString())

            // loopj

            client.post(this, "https://ghostsec.pythonanywhere.com/payment", body, "application/json",

                object: JsonHttpResponseHandler(){
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        response: JSONObject?
                    ) {
                        if (statusCode == 200){
                            Toast.makeText(applicationContext, "Please Complete Payment$statusCode", Toast.LENGTH_LONG).show()
//                        super.onSuccess(statusCode, headers, response)
                        }
                        else {
                            Toast.makeText(applicationContext, "Payment not successful$statusCode", Toast.LENGTH_LONG).show()
                                            }

                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseString: String?,
                        throwable: Throwable?
                    ) {
                        Toast.makeText(applicationContext, "Please Try Again$statusCode", Toast.LENGTH_LONG).show()
//                        super.onFailure(statusCode, headers, responseString, throwable)
                    }


                } // END HANDLER

                )// end client

        }
    }
}