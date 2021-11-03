package com.example.meet.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.meet.R
import com.example.meet.util.ConnectionManager
import com.example.meet.util.SessionManager
import com.example.meet.util.Validations
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


/*The tasks performed in this activity are similar to the ones done in the previous activities.
* Task : Try to make out the usage of each line of code and asd appropriate comments yourself
* */


class RegisterActivity : AppCompatActivity() {

    lateinit var btnSignUp: Button
    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var mAuth: FirebaseAuth
    lateinit var mDbRef:DatabaseReference
    lateinit var checkBox1: CheckBox
    lateinit var checkBox2: CheckBox
    lateinit var checkBox3: CheckBox
    lateinit var checkBox4: CheckBox
    lateinit var checkBox5: CheckBox
    lateinit var checkBox6: CheckBox
    lateinit var checkBox7: CheckBox
    lateinit var checkBox8: CheckBox
    lateinit var checkBox9: CheckBox
    lateinit var checkBox10: CheckBox
    lateinit var checkBox11: CheckBox
    lateinit var checkBox12: CheckBox
    lateinit var checkBox13: CheckBox
    lateinit var selectedBox : ArrayList<String>
    lateinit var sessionManager: SessionManager
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()


        sessionManager = SessionManager(this@RegisterActivity)
        sharedPreferences = this@RegisterActivity.getSharedPreferences(sessionManager.PREF_NAME, sessionManager.PRIVATE_MODE)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        checkBox5 = findViewById(R.id.checkBox5)
        checkBox6 = findViewById(R.id.checkBox6)
        checkBox7 = findViewById(R.id.checkBox7)
        checkBox8 = findViewById(R.id.checkBox8)
        checkBox9 = findViewById(R.id.checkBox9)
        checkBox10 = findViewById(R.id.checkBox10)
        checkBox11 = findViewById(R.id.checkBox11)
        checkBox12 = findViewById(R.id.checkBox12)
        checkBox13 = findViewById(R.id.checkBox13)

        if(checkBox1.isChecked){
            selectedBox.add(checkBox1.text.toString())
        }
        if(checkBox2.isChecked){
            selectedBox.add(checkBox2.text.toString())
        }
        if(checkBox3.isChecked){
            selectedBox.add(checkBox3.text.toString())
        }
        if(checkBox4.isChecked){
            selectedBox.add(checkBox4.text.toString())
        }
        if(checkBox5.isChecked){
            selectedBox.add(checkBox5.text.toString())
        }
        if(checkBox6.isChecked){
            selectedBox.add(checkBox6.text.toString())
        }
        if(checkBox7.isChecked){
            selectedBox.add(checkBox7.text.toString())
        }
        if(checkBox8.isChecked){
            selectedBox.add(checkBox8.text.toString())
        }
        if(checkBox9.isChecked){
            selectedBox.add(checkBox9.text.toString())
        }
        if(checkBox10.isChecked){
            selectedBox.add(checkBox10.text.toString())
        }
        if(checkBox11.isChecked){
            selectedBox.add(checkBox11.text.toString())
        }
        if(checkBox12.isChecked){
            selectedBox.add(checkBox12.text.toString())
        }
        if(checkBox13.isChecked){
            selectedBox.add(checkBox13.text.toString())
        }





        btnSignUp.setOnClickListener {

            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()


            if (Validations.validateEmail(etEmail.text.toString()) && Validations.validatePasswordLength(etPassword.text.toString())) {
                if (ConnectionManager().isNetworkAvailable(this@RegisterActivity)) {
                    signUp(name,email,password)
                }else {
                    Toast.makeText(this@RegisterActivity, "No internet Connection", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this@RegisterActivity, "Invalid Credentials", Toast.LENGTH_SHORT)
                    .show()
            }



        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun signUp(
        name: String,
        email: String,
        password: String
    ){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    sessionManager.setLogin(true)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                   Toast.makeText(this, "Some Error Occurred.",
                        Toast.LENGTH_SHORT).show()

                }
            }

    }
    private fun addUserToDatabase(
        name: String,
        email: String,
        uid: String
    ){

        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }
}

