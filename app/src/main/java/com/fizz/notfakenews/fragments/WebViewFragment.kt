package com.fizz.notfakenews.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.fizz.notfakenews.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {
    private var _binding:FragmentWebViewBinding?=null
    val binding get() = _binding
    private val args:WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentWebViewBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.articleWebView.apply { loadUrl(args.url)
        webViewClient= WebViewClient()
            settings.javaScriptEnabled=true
        }
    }


}