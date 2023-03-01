package com.example.githubactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button_Save =findViewById<Button>(R.id.button)
        val persona_name=findViewById<TextView>(R.id.personName)
        val persona_age=findViewById<TextView>(R.id.personAge)
        val persona_id=findViewById<TextView>(R.id.personID)
        button_Save.setOnClickListener {
            var name= persona_name.text.toString()
            var age= persona_age.text.toString()
            var id= persona_id.text.toString()


            val person = hashMapOf(
                "name" to name,
                "age" to age,
                "id" to id
            )
            db.collection("person")
                .add(person).addOnSuccessListener { documentReference ->
                    Toast.makeText(applicationContext,"${documentReference.id}",Toast.LENGTH_LONG).show()
                    // Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext,"$e",Toast.LENGTH_LONG).show()
                    // Log.w(TAG, "Error adding document", e)
                }
        }
    }
}


