package com.example.recyclerviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.recyclerviewapplication.Models.Superhero
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_acitivity.*

class DetailAcitivity : AppCompatActivity() {

    private lateinit var ivAvatarDetail: ImageView
    private lateinit var tvSuperheroDetail: TextView
    private lateinit var tvRealNameDetail: TextView
    private lateinit var tvPublisherDetail: TextView
    private lateinit var superhero: Superhero;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_acitivity)

        this.initUi()

        val json: String = intent.getStringExtra("superhero")
        superhero = superhero.fromJson(json)

        ivAvatarDetail.loadUrl(superhero.image)
        tvSuperheroDetail.text = superhero.superhero
        tvRealNameDetail.text = superhero.realName
        tvPublisherDetail.text = superhero.publisher
    }

    private fun initUi() {
        ivAvatarDetail = findViewById(R.id.ivAvatarDetail)
        tvSuperheroDetail = findViewById(R.id.tvSuperheroDetail)
        tvRealNameDetail = findViewById(R.id.tvRealNameDetail)
        tvPublisherDetail = findViewById(R.id.tvPublisherDetail)

        superhero = Superhero("", "", "", "");
    }

    private fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }
}