package com.example.healthcare.Activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.PasswordTransformationMethod
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.healthcare.Database.Database
import com.example.healthcare.R
import com.example.healthcare.Singleton.PasswordUtils
import com.example.healthcare.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var registerButton : Button
    private lateinit var loginTextView: TextView
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    private val validations = PasswordUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        username = findViewById(R.id.editTextUsername)
        email = findViewById(R.id.editTextRegEmail)
        password = findViewById(R.id.editTextRegPassword)
        confirmPassword = findViewById(R.id.editTextRegConfirmPassword)
        registerButton = findViewById(R.id.registerbutton)
        loginTextView = findViewById(R.id.loginTextview)

        TextViewColorChange()
        ShowHidePassword()
        ShowHideConfirmPassword()

        loginTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun register(view: View) {

        var username =username.text.toString().trim()
        var email = email.text.toString().trim()
        var password = password.text.toString().trim()
        var confirmPassword = confirmPassword.text.toString().trim()

        if(username.isEmpty()){
            Toast.makeText(this, "Kullanıcı adınızı boş bırakmayınız.", Toast.LENGTH_SHORT).show()
            return
        }

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Kayıt olabilmek için lütfen e-posta veya şifrenizi eksiksiz giriniz!", Toast.LENGTH_SHORT).show()
            return
        }
        if(password != confirmPassword){
            Toast.makeText(this, "Parolalar birbirleriyle eşleşmiyor. Lütfen şifrenizi tekrar eksiksiz giriniz!", Toast.LENGTH_SHORT).show()
            return
        }
        if (!validations.isValid(password)){
            Toast.makeText(this, "Şifreniz en az 8 karakter uzunluğunda olmalı, en az 1 harf, 1 sayı ve 1 özel karakter içermelidir.", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            // Asenkron çalışan kısım
            if (task.isSuccessful) {
                val firebaseUser = auth.currentUser

                if (firebaseUser != null) {
                    // Save the username to Firestore
                    val user = hashMapOf(
                        "username" to username
                    )

                    db.collection("users")
                        .document(firebaseUser.uid)
                        .set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Kayıt işlemi gerçekleşmiştir.", Toast.LENGTH_LONG).show()
                            // Save the username to SQLite database
                            val database = Database(applicationContext)
                            database.register(username, email, password)
                            auth.signOut()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish() // Diğer activite'yi kesin olarak bitirmek için (aktiviteyi destroy etme işlemi)
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
                            // localizedMessage --> Kullanıcının anlayabileceği dilden bir mesaj
                        }
                }
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            // localizedMessage --> Kullanıcının anlayabileceği dilden bir mesaj
        }
    }

    fun TextViewColorChange() {
        loginTextView = findViewById(R.id.loginTextview)
        val text = "Already have an account? Sign In"
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            text.indexOf("Sign In"),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF00FF")),
            text.indexOf("Sign In"),
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        loginTextView.text = spannableString
    }

    fun ShowHidePassword(){
        password = findViewById(R.id.editTextRegPassword)
        val showPasswordButton = findViewById<ImageButton>(R.id.hidebutton)
        val passwordTransformationMethod = PasswordTransformationMethod.getInstance()

        showPasswordButton.setOnClickListener {
            if (password.transformationMethod == passwordTransformationMethod) {
                // Metin gizliyse, şifre alanındaki metni göstermek için TransformationMethod null yapılır.
                password.transformationMethod = null
                showPasswordButton.setImageResource(R.drawable.hide)
            } else {
                // Metin gösteriliyse, şifre alanındaki metni gizlemek için PasswordTransformationMethod kullanılır.
                password.transformationMethod = passwordTransformationMethod
                showPasswordButton.setImageResource(R.drawable.show)
            }
        }
    }
    fun ShowHideConfirmPassword(){
        confirmPassword = findViewById(R.id.editTextRegConfirmPassword)
        val showPasswordButton = findViewById<ImageButton>(R.id.hidebutton2)
        val passwordTransformationMethod = PasswordTransformationMethod.getInstance()

        showPasswordButton.setOnClickListener {
            if (confirmPassword.transformationMethod == passwordTransformationMethod) {
                // Metin gizliyse, şifre alanındaki metni göstermek için TransformationMethod null yapılır.
                confirmPassword.transformationMethod = null
                showPasswordButton.setImageResource(R.drawable.hide)
            } else {
                // Metin gösteriliyse, şifre alanındaki metni gizlemek için PasswordTransformationMethod kullanılır.
                confirmPassword.transformationMethod = passwordTransformationMethod
                showPasswordButton.setImageResource(R.drawable.show)
            }
        }
    }
}