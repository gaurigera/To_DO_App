package com.example.recyclerview_todo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed( {
            if (user != null) {
                val dashboardIntent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(dashboardIntent)
            } else {
                val signInActivity = Intent(this@MainActivity, LoginPage::class.java)
                startActivity(signInActivity)
            }
        }, 1000)
    }
}
