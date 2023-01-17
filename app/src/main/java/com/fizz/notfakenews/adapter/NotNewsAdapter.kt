package com.fizz.notfakenews.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fizz.notfakenews.R
import com.fizz.notfakenews.model.Article
import com.fizz.notfakenews.overview.OverviewViewModel

private const val TAG="Not a News Adapter"
class NotNewsAdapter(val context: Context,val viewModel: OverviewViewModel,val newsList:ArrayList<Article>):RecyclerView.Adapter<NotNewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(private val view:View):RecyclerView.ViewHolder(view) {
        val newsTitle= view.findViewById<TextView>(R.id.newsTitle)
        val newsAuthor=view.findViewById<TextView>(R.id.newsAuthor)
        val newsDetail=view.findViewById<TextView>(R.id.detailNews)
        val thumbnail=view.findViewById<ImageView>(R.id.thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(R.layout.fake_news_card,parent,false)
        Log.d(TAG,"Bind View Holder is working I guess")
        return NewsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem=newsList[position]
        holder.newsAuthor.text=newsItem.author
        holder.newsDetail.text=newsItem.description
        holder.newsTitle.text=newsItem.title
        Glide.with(context).load(newsItem.urlToImage).into(holder.thumbnail);
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}