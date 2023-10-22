package com.example.recyclerview_todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview_todo.databinding.LoginPageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class LoginPage : AppCompatActivity() {
    private lateinit var binding : LoginPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signInBtn = findViewById<Button>(R.id.signInButton)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        firebaseAuth = Firebase.auth
        binding.signInButton.setOnClickListener{
            val signInClient = googleSignInClient.signInIntent
            launcher.launch(signInClient)
        }
    }
    private var launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

                if(task.isSuccessful) {
                    val account:GoogleSignInAccount?=task.result
                    val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                    firebaseAuth.signInWithCredential(credential).addOnCompleteListener{
                        if (it.isSuccessful) {
                            Toast.makeText(this,"Login Successful!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity2::class.java)
                            startActivity(intent)

                        } else {
                            Toast.makeText(this,"Login Failed inside!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {

                }
            } else {
                Toast.makeText(this,"${result.resultCode}, $", Toast.LENGTH_SHORT).show()
            }
    }
}