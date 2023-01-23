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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.fizz.notfakenews.R


class DetailviewFragment : Fragment() {
    private val args: DetailviewFragmentArgs by navArgs()

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
        if (!TextUtils.isEmpty(args.article.title)) {
            title.text = args.article.title
        } else {
            title.text = "No Name"
        }
        if (!TextUtils.isEmpty(args.article.description)) {
            description.text = args.article.description
        } else {
            description.text = "No Description"
        }
        if (!TextUtils.isEmpty(args.article.content)) {
            content.text = args.article.content
        } else {
            content.text = "No Content"
        }
        if (!TextUtils.isEmpty(args.article.content)) {
            Glide.with(requireContext()).load(args.article.urlToImage)
                .error(R.drawable.ic_baseline_broken_image_24).into(imageView)

        } else {
            Glide.with(requireContext()).load(R.drawable.ic_baseline_broken_image_24)
        }
        continueButton.setOnClickListener {
/*            if (!TextUtils.isEmpty(args.article.url)) {
                val action =
                    args.article.url?.let { it1 ->
                        DetailedNewsFragmentDirections.actionDetailedNewsFragmentToWebViewFragment(
                            it1
                        )
                    }
                if (action != null) {
                    Navigation.findNavController(view).navigate(action)
                }
            }*/
        }
    }

}