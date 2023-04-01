package com.example.codepathproject5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.getButton)
        val imageView = findViewById<ImageView>(R.id.image)
        val imageTitle = findViewById<TextView>(R.id.imgTitle)
        val imageDate = findViewById<TextView>(R.id.imgYear)

        button.setOnClickListener{
            getImage(imageView)
            getTitle(imageTitle)
            getDate(imageDate)
        }
    }

    private fun getImage(imageView: ImageView){
        val client = AsyncHttpClient()
        client["https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Astronomy Photo Fail", "error fetching photo!")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d("Astronomy Photo Success", "response successful!")
                val photoImageUrl = json?.jsonObject?.getString("url")
                Glide.with(this@MainActivity)
                    .load(photoImageUrl)
                    .fitCenter()
                    .into(imageView)
            }

        }]

    }
    private fun getTitle(imageTitle: TextView){
        val client = AsyncHttpClient()
        client["https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Astronomy Title Fail", "error fetching Title!")
            }

            override fun onSuccess(
                statusCode: Int,
                headers: Headers?,
                json: JSON?
            ) {
                imageTitle.text = "Title: " + json?.jsonObject?.getString("title")
                Log.d("Astronomy Title Success", "Title successful!")
            }

        }]
    }
    private fun getDate(imageDate: TextView){
        val client = AsyncHttpClient()
        client["https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Astronomy Date Fail", "error fetching Date!")
            }

            override fun onSuccess(
                statusCode: Int,
                headers: Headers?,
                json: JSON?
            ) {
                imageDate.text = "Date: " + json?.jsonObject?.getString("date")
                Log.d("Astronomy Date Success", "Date successful!")
            }

        }]
    }
}