/*
package com.fizz.notfakenews.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fizz.notfakenews.model.ArticleDatabase
import com.fizz.notfakenews.model.ArticleLocal
import com.fizz.notfakenews.model.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalViewModel(application: Application):AndroidViewModel(application) {



    init {
        val articleDao= ArticleDatabase.getDatabase(application).articleDao()

        repository= ArticleRepository(articleDao)

    }




}*/
