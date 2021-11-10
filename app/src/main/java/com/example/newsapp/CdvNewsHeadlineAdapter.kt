package com.example.newsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.example.newsapp.databinding.CdvNewsHeadlineBinding
import com.example.newsapp.model.ArticlesItem

class CdvNewsHeadlineAdapter : RecyclerView.Adapter<CdvNewsHeadlineVH>() {
    private val listData = ArrayList<ArticlesItem>()

    fun addData(items: List<ArticlesItem>) {
        listData.clear()
        listData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlineVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater, parent, false)
        return CdvNewsHeadlineVH(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: CdvNewsHeadlineVH, position: Int) {
        holder.bind(listData[position])
    }
}

class CdvNewsHeadlineVH(private val binding: CdvNewsHeadlineBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(article: ArticlesItem){
        binding.run {
            txtTitle.text = cropText(article.title ?: "Tidak ada judul")
            txtSubtitle.text = article.publishedAt
            imgHeadline.apply {
                load(article.urlToImage){
                    scale(Scale.FILL)
                }
                contentDescription = article.description
            }
            //region buatnya nanti ketika sudah buat detail
            root.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.DETAIL_NEWS, article)
                }
                it.context.startActivity(intent)
            }
            //endregion
        }
    }

}

private fun cropText(text: String): String {
    return text.take(50) + if (text.length > 50) "..." else ""
}
