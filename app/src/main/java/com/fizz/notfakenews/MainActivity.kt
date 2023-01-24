package com.fizz.notfakenews

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import com.fizz.notfakenews.overview.OverviewViewModel
import com.fizz.notfakenews.util.Constraints
import com.fizz.notfakenews.worker.SyncDataWorker
import com.fizz.notfakenews.worker.WorkManagerUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: OverviewViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNav:BottomNavigationView= findViewById(R.id.bottomNavigationView)
        if(Constraints.checkConnectivity(this)){
            Log.e("tag","networkConnected")
        }

        val workManager = WorkManager.getInstance(applicationContext)
        val dataLoad =
            PeriodicWorkRequest
                .Builder(SyncDataWorker::class.java, 15, TimeUnit.MINUTES)
                .setInitialDelay(1, TimeUnit.MINUTES)
                .addTag("apiCall")
                .build()
        workManager.enqueueUniquePeriodicWork(
            "apiCall",
            ExistingPeriodicWorkPolicy.REPLACE,
            dataLoad
        )

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    Log.d("TAG", "Home")
                    navController.navigate(R.id.homeFragment)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    Log.d("TAG", "Saved")
                    navController.navigate(R.id.savedFragment)
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
}