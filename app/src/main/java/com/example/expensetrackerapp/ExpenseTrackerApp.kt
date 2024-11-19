package com.example.expensetrackerapp

import android.app.Application

class ExpenseTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }

}