package com.endrawan.smartpot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()

    val nilaiKelembapan = database.getReference("kelembapan")
    val wateringStatus = database.getReference("wateringStatus")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nilaiKelembapan.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("database", error.message)
            }

            override fun onDataChange(data: DataSnapshot) {
                Log.d("database", data.value.toString())
                humidity.text = data.getValue(Long::class.java).toString() + "%"
            }
        })

        watering.setOnClickListener {
            wateringStatus.setValue(true)
            Thread.sleep(2000)
            wateringStatus.setValue(false)
        }
    }
}
