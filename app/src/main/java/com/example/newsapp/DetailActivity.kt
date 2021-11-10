package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import coil.size.Scale
import com.example.newsapp.databinding.ActivityDetailBinding
import com.example.newsapp.model.ArticlesItem

class DetailActivity : AppCompatActivity() {
    companion object {
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val data = intent.getParcelableExtra<ArticlesItem>(DETAIL_NEWS)
        binding.run {
            setContentView(root)
            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = data!!.title
            imgToolbar.apply {
                load(data.urlToImage){
                    scale(Scale.FILL)
                }

                contentDescription = data!!.description
            }

            txtContent.text = data!!.content
            txtDate.text = data.publishedAt
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}