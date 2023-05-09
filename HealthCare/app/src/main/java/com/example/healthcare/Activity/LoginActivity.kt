package com.example.healthcare.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.PasswordTransformationMethod
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.*
import com.example.healthcare.Database.Database
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var loginButton : Button
    private lateinit var registerTextView: TextView
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        TextViewColorChange()
        ShowHidePassword()

        email = findViewById(R.id.editTextLoginEmail)
        password = findViewById(R.id.editTextLoginPassword)
        loginButton = findViewById(R.id.loginbutton)
        registerTextView = findViewById(R.id.registerTextview)
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun login(view: View) {
        var email = email.text.toString().trim()
        var password = password.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Giriş yapabilmek için lütfen e-posta veya şifrenizi eksiksiz giriniz!", Toast.LENGTH_SHORT).show()
            return
        }
        val firebaseUser = auth.currentUser

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            // asenkron işlemi
            if (task.isSuccessful) {
                val database = Database(applicationContext)
                database.login(email, password)
                if (firebaseUser != null) {
                    db.collection("users").document(firebaseUser.uid).get()
                        .addOnSuccessListener { document ->
                            val username = document.getString("username")
                            if (username != null) {
                                Toast.makeText(this, "Hoşgeldiniz $username!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                }
        }.addOnFailureListener { exception ->
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }


    fun TextViewColorChange() {
        registerTextView = findViewById(R.id.registerTextview)
        val text = "Don't have an account? Sign Up"
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            ForegroundColorSpan(Color.WHITE),
            0,
            text.indexOf("Sign Up"),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF00FF")),
            text.indexOf("Sign Up"),
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        registerTextView.text = spannableString
    }

    fun ShowHidePassword(){

        password = findViewById(R.id.editTextLoginPassword)
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
}