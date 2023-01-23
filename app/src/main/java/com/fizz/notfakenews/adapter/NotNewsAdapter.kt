package com.fizz.notfakenews.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fizz.notfakenews.R
import com.fizz.notfakenews.model.ArticleLocal
import com.fizz.notfakenews.overview.OverviewViewModel
import com.fizz.notfakenews.util.Constraints

private const val TAG="Not a News Adapter"
class NotNewsAdapter(val context: Context,val viewModel: OverviewViewModel):RecyclerView.Adapter<NotNewsAdapter.NewsViewHolder>() {

    private var allNews= emptyList<ArticleLocal>()

    class NewsViewHolder(private val view:View):RecyclerView.ViewHolder(view) {
        val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        val newsAuthor: TextView =view.findViewById(R.id.newsAuthor)
        val newsDetail: TextView =view.findViewById(R.id.detailNews)
        val thumbnail: ImageView =view.findViewById(R.id.thumbnail)
        val bookmark:ImageView=view.findViewById(R.id.bookmark)
        val card: CardView =view.findViewById(R.id.card_view)
        val published:TextView=view.findViewById(R.id.time)
    }

    private val differCallBack=object:DiffUtil.ItemCallback<ArticleLocal>(){
        override fun areItemsTheSame(oldItem: ArticleLocal, newItem: ArticleLocal): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: ArticleLocal, newItem: ArticleLocal): Boolean {
            return oldItem==newItem
        }
    }

    //val differ=AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout=LayoutInflater.from(parent.context).inflate(R.layout.fake_news_card,parent,false)
        Log.d(TAG,"Bind View Holder is working I guess")
        return NewsViewHolder(layout)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        Log.d("NoT News Adapter","Currently I am in not news adapter")
        val newsItem=allNews[position]
        holder.newsAuthor.text=newsItem.author
        holder.newsDetail.text=newsItem.description
        holder.newsTitle.text=newsItem.title
        Glide.with(context).load(newsItem.urlToImage).error(R.drawable.ic_baseline_broken_image_24).into(holder.thumbnail)
        holder.published.hint=Constraints.DateToTimeFormat(newsItem.publishedAt)

        //Adding bookmark screen
        if(newsItem.bookmark == true) holder.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
        else holder.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_add_24)

        holder.bookmark.setOnClickListener {
            when(newsItem.bookmark){
                true -> {holder.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_add_24)
                viewModel.updateBookmark(false,newsItem.id)
                }
                else-> {holder.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24)
                viewModel.updateBookmark(true,newsItem.id)}
            }
        }

        holder.card.setOnClickListener {
            val bundle=Bundle()
            bundle.putParcelable("hello",newsItem)
            holder.card.findNavController().navigate(R.id.detailviewFragment, bundle)
        }

    }

/*    private var onItemClickListener:((ArticleLocal)->Unit)?=null

    fun setOnItemClickListener(listener:(ArticleLocal)->Unit){
        onItemClickListener=listener
    }*/

    override fun getItemCount(): Int {
        return allNews.size
    }

    fun setDataset(newsItem: List<ArticleLocal>){
        allNews = newsItem
    }
}