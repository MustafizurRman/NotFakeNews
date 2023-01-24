package com.fizz.notfakenews.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.fizz.notfakenews.R
import com.fizz.notfakenews.model.ArticleLocal


class DetailviewFragment : Fragment() {
    //private val args: DetailviewFragmentArgs by navArgs()

    private var article: ArticleLocal? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            article = it.getParcelable("hello")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title: TextView = view.findViewById(R.id.newTitle)
        val description: TextView = view.findViewById(R.id.newsDescription)
        val content: TextView = view.findViewById(R.id.newsContent)
        val imageView: ImageView = view.findViewById(R.id.newsImage)
        val continueButton: Button = view.findViewById(R.id.continueButton)
        if (!TextUtils.isEmpty(article?.title)) {
            title.text = article?.title
        } else {
            title.text = "No Name"
        }
        if (!TextUtils.isEmpty(article?.description)) {
            description.text = article?.description
        } else {
            description.text = "No Description"
        }
        if (!TextUtils.isEmpty(article?.content)) {
            content.text = article?.content
        } else {
            content.text = "No Content"
        }
        if (!TextUtils.isEmpty(article?.content)) {
            Glide.with(requireContext()).load(article?.urlToImage)
                .error(R.drawable.ic_baseline_broken_image_24).into(imageView)

        } else {
            Glide.with(requireContext()).load(R.drawable.ic_baseline_broken_image_24)
        }
        continueButton.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("url",article!!.url)
            view.findNavController().navigate(R.id.webViewFragment,bundle)
        }
    }

}