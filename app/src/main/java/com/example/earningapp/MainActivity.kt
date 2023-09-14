package com.example.earningapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.earningapp.databinding.ActivityMainBinding
import com.example.earningapp.modelClass.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       binding.signUp.setOnClickListener {
           if(binding.nameMain.text.toString().equals("") ||
               binding.ageMain.text.toString().equals("") ||
               binding.EmailMain.text.toString().equals("")||
               binding.passwordMain.text.toString().equals("") ){
          Toast.makeText(this,"Please fill All The Details very carefully Experimental",Toast.LENGTH_SHORT).show()
           }else{
                    Firebase.auth.createUserWithEmailAndPassword(binding.EmailMain.text.toString(),binding.passwordMain.text.toString())
                        .addOnCompleteListener {
                        if(it.isSuccessful){
                             var user = User(binding.nameMain.text.toString(),
                             Integer.parseInt(binding.ageMain.text.toString()),
                             binding.EmailMain.text.toString(),
                             binding.passwordMain.text.toString()
                             )

                          Firebase.database.reference.child("Users")
                              .child(Firebase.auth.currentUser!!.uid).setValue(user).addOnSuccessListener {

                                  startActivity(Intent(this,HomeActivity::class.java))
                                  finish()
                              }

                        }else{
                            Toast.makeText(this,it.exception?.localizedMessage,Toast.LENGTH_SHORT).show()

                        }
                    }

           }
       }
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }
}