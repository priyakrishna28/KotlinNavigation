package com.example.senthil.kotlinNavigationdrawer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private var btnlogin: Button ? = null
    private var imageview1: ImageView? = null
    private var edit1: EditText? = null
    private var edit2: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnlogin = findViewById<View>(R.id.btnlogin)as Button
        imageview1 = findViewById<View>(R.id.imageView1)as ImageView
        edit1     = findViewById<View>(R.id.edit1)as EditText
        edit2     = findViewById<View>(R.id.edit2)as EditText
        val email = edit1!!.text.toString()
        val password = edit2!!.text.toString()

        btnlogin!!.setOnClickListener{
            IsLogin()
        }
    }
    fun IsLogin()
    {
        if(!IsvalidEmail(edit1!!.text.toString()) && !Ispwdvalid(edit2!!.text.toString()))
        {
            OnLoginFailed()
        }
        else
            OnLoginSuccess()
    }

    fun OnLoginFailed()
    {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
        btnlogin!!.isEnabled = true
    }

    fun OnLoginSuccess()
    {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        //intent.putExtra("Email", etemail!!.getText().toString().trim())
        startActivity(intent)
    }
    fun IsvalidEmail(email: String) : Boolean
    {
        if(email.isEmpty() )
        {
            edit1!!.setError("Your field can't empty")
            return false
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            edit1!!.setError("Please Enter the valid Email")
            return false
        }
        else
        {
            edit1!!.error = null
        }
        return true
    }
    val PASSWORD_PATTERN = Pattern.compile(
            "^" + "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[a-zA-Z])" +
                    "(?=.*[@!#$%^&+=])"
                    + "(?=\\s+$)" +
                    ".{6,}" + "$"
    )
    fun Ispwdvalid(password : String) : Boolean
    {
        if(password.isEmpty() )
        {
            edit2!!.setError("Your field can't empty")
            return false
        }
        else if(!PASSWORD_PATTERN.matcher(password).matches())
        {
            edit2!!.setError("Your Password is too weak")
            return false
        }
        else
        {
            edit2!!.error = null
        }
        return true
    }


}
