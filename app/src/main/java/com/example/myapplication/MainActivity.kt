package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var textview: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        textview = findViewById(R.id.tV)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.breakingbadapi.com/api/quotes")
            .build()

        val gson = GsonBuilder().create()

        binding.btn1.setOnClickListener {
            client.newCall(request).enqueue(object : Callback{
                override fun onResponse(call: Call, response: Response) {
                    val mail = gson.fromJson(response.body?.string(), infoResponse::class.java)
                    textview?.setText(mail.quote_id.toString() + ' ')
                }
                override fun onFailure(call: Call, e: IOException) {
                }
            })
        }
    }
}