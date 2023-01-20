package com.fizz.notfakenews.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fizz.notfakenews.R
import com.fizz.notfakenews.model.Article
import com.fizz.notfakenews.model.ArticleLocal
import com.fizz.notfakenews.overview.OverviewViewModel

private const val TAG="Not a News Adapter"
class NotNewsAdapter(val context: Context,val viewModel: OverviewViewModel,val newsList:ArrayList<ArticleLocal>):RecyclerView.Adapter<NotNewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(private val view:View):RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        val newsAuthor: TextView =view.findViewById(R.id.newsAuthor)
        val newsDetail: TextView =view.findViewById(R.id.detailNews)
        val thumbnail: ImageView =view.findViewById(R.id.thumbnail)
        val card: CardView =view.findViewById(R.id.card_view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(R.layout.fake_news_card,parent,false)
        Log.d(TAG,"Bind View Holder is working I guess")
        return NewsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Log.d("NoT News Adapter","Currently I am in not news adapter")
        val newsItem=newsList[position]
        holder.newsAuthor.text=newsItem.author
        holder.newsDetail.text=newsItem.description
        holder.newsTitle.text=newsItem.category
        Glide.with(context).load(newsItem.urlToImage).into(holder.thumbnail)
    }
    private var onItemClickListener:((Article)->Unit)?=null

    override fun getItemCount(): Int {
        return newsList.size
    }
}