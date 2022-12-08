package com.example.recyclerproject

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to all views
        var username = findViewById<EditText>(R.id.userName)
        var password = findViewById<EditText>(R.id.password)
        var submit = findViewById<Button>(R.id.submit_button)



        // set on-click listener
        submit.setOnClickListener {
            val username = username.text;
            val password = password.text;
            Toast.makeText(this@LoginActivity, username, Toast.LENGTH_LONG).show()

            // your code to validate the user_name and password combination
            // and verify the same

        }
    }
}
//class LoginActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//    }
//
//    fun onclick(view: View) {
//        login(
//            findViewById<TextInputEditText>(R.id.userName).text.toString(),
//            findViewById<TextInputEditText>(R.id.password).text.toString()
//        )
//    }
//
//    private fun login(userName: String, password: String) {
//        if (userName == "name" && password == "1234") {
//            startActivity(Intent(this, MainActivity::class.java))
//            Toast.makeText(this, "Login Success!", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
