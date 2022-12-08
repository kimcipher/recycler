package com.example.recyclerproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var userName = findViewById<EditText>(R.id.userName)
        var userEmail = findViewById<EditText>(R.id.email)
        var userPhone = findViewById<EditText>(R.id.phone)
        var userPassword = findViewById<EditText>(R.id.password)
        var confirmPass = findViewById<EditText>(R.id.confirm)



        var progressBar = findViewById<ProgressBar>(R.id.progress)
        progressBar.visibility = View.GONE

        var signUpBtn = findViewById<Button>(R.id.submit_button)
        signUpBtn.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            var payload = JSONObject()
            payload.put("user", userName.text.toString())
            payload.put("email", userEmail.text.toString())
            payload.put("phone", userPhone.text.toString())
            payload.put("password", userPassword.text.toString())
            payload.put("confirm", confirmPass.text.toString())

            var conBody = StringEntity(payload.toString())

            var client = AsyncHttpClient(true, 80, 443)
            client.post(this, "https://ghostsec.pythonanywhere.com/register",conBody,
                "application/json",
                object : JsonHttpResponseHandler(){

                    // onSuccess
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        response: JSONObject?
                    ) {

                        when(statusCode) {

                            10 -> Toast.makeText(applicationContext, "Invalid Email", Toast.LENGTH_SHORT).show()
                            20 -> Toast.makeText(applicationContext, "Invalid username", Toast.LENGTH_SHORT).show()
                            30 -> Toast.makeText(applicationContext, "Have at least 8 characters password", Toast.LENGTH_SHORT).show()
                            40 -> Toast.makeText(applicationContext, "Passwords don't match!", Toast.LENGTH_SHORT).show()

                            200 -> {
                                Toast.makeText(
                                    applicationContext,
                                    "Saved Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                var i = Intent(applicationContext, LoginActivity::class.java)
                                startActivity(i)
                                finish()
                            }
                        }

                    }



        } // end Handler



        ) // end client

    }


}
}
