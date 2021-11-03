package com.example.meet.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.meet.util.Validations
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.meet.R
import com.example.meet.util.ConnectionManager
import com.example.meet.util.SessionManager
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    /*Declaring the different variables used for this activity*/
    private lateinit var btnSignUp: Button
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var mAuth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sessionManager: SessionManager


    /*Life-cycle method of the activity*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Linking the view*/
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()


        mAuth = FirebaseAuth.getInstance()
        /*Initialising the views with the ones defined in the XML*/
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogin = findViewById(R.id.btnLogin)

        sessionManager = SessionManager(this)
        sharedPreferences =
            this.getSharedPreferences(sessionManager.PREF_NAME, sessionManager.PRIVATE_MODE)

        btnSignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }


        btnLogin.setOnClickListener {
            val email=etEmail.text.toString()
            val password=etPassword.text.toString()

            if (Validations.validateEmail(etEmail.text.toString()) && Validations.validatePasswordLength(etPassword.text.toString())) {
                if (ConnectionManager().isNetworkAvailable(this@LoginActivity)) {
                    login(email, password)
                }else {
                    Toast.makeText(this@LoginActivity, "No internet Connection", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this@LoginActivity, "Invalid Email or Password", Toast.LENGTH_SHORT)
                    .show()
            }



        }


    }
    private fun login(email: String,password: String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    sessionManager.setLogin(true)
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Some Error Occurred.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }
}
